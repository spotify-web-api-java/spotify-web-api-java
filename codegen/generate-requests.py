#!/usr/bin/env python3
"""Generate request classes from spec/openapi.yaml and codegen/requests.yaml.

Run from anywhere:
  ./codegen/generate-requests.py          rewrite the generated request classes
  ./codegen/generate-requests.py --check  exit non-zero if any of them differs from what the spec produces

PROTOTYPE: covers the shapes the albums requests need. An operation using anything else
(body parameters, a request-specific response wrapper, ...) fails loudly instead of guessing.
"""
import pathlib
import re
import sys
import textwrap

import yaml

ROOT = pathlib.Path(__file__).resolve().parent.parent
SPEC = ROOT / "spec/openapi.yaml"
DESCRIPTOR = ROOT / "codegen/requests.yaml"
SOURCES = ROOT / "src/main/java"
MODELS = SOURCES / "se/michaelthelin/spotify/model_objects"
PACKAGE = "se.michaelthelin.spotify.requests.data"
DOCS = "https://developer.spotify.com"
WIDTH = 120

# The spec names a few schemas differently from the model classes, beyond the conventions in model_class().
MODEL_OVERRIDES = {}


class Unsupported(Exception):
    pass


# --- spec access -------------------------------------------------------------------------------------

def load_spec():
    spec = yaml.safe_load(SPEC.read_text())
    operations = {}
    for path, item in spec["paths"].items():
        for verb, op in item.items():
            if isinstance(op, dict) and "operationId" in op:
                operations[op["operationId"]] = (verb.upper(), path, op)
    return spec, operations


def deref(spec, node):
    while isinstance(node, dict) and "$ref" in node:
        section, name = node["$ref"].split("/")[-2:]
        node = spec["components"][section][name]
    return node


def ref_name(node):
    return node["$ref"].split("/")[-1] if isinstance(node, dict) and "$ref" in node else None


# --- types -------------------------------------------------------------------------------------------

def model_class(schema_name):
    """SavedAlbumObject -> SavedAlbum, SimplifiedTrackObject -> TrackSimplified, AlbumObject -> Album."""
    if schema_name in MODEL_OVERRIDES:
        return MODEL_OVERRIDES[schema_name]
    name = re.sub(r"Object$", "", schema_name)
    m = re.match(r"Simplified(\w+)", name)
    if m:
        name = m.group(1) + "Simplified"
    if not list(MODELS.rglob(name + ".java")):
        raise Unsupported(f"schema {schema_name} maps to {name}, which is no model class; add a MODEL_OVERRIDES entry")
    return name


def model_import(name):
    source = next(MODELS.rglob(name + ".java"))
    return ".".join(source.relative_to(SOURCES).with_suffix("").parts)


def paging_item(spec, schema):
    """The item model of a Paging...Object schema, read from its allOf items property."""
    for part in deref(spec, schema).get("allOf", []):
        items = part.get("properties", {}).get("items")
        if items:
            return model_class(ref_name(items["items"]))
    raise Unsupported(f"{ref_name(schema)} has no items")


def response(spec, op):
    """How execute() turns the body into its return value: (java type, expression, imports, paging item)."""
    responses = op["responses"]
    success = next((responses[c] for c in ("200", "201", "202", "204") if c in responses), None)
    schema = deref(spec, success).get("content", {}).get("application/json", {}).get("schema")
    if schema is None:
        return "String", None, set(), None

    def read(method, model, key=None, wrapper=None):
        key_arg = f'"{key}", ' if key else ""
        java = f"{wrapper}<{model}>" if wrapper else f"{model}[]" if method == "array" else model
        imports = {model_import(model), "se.michaelthelin.spotify.internal.ModelObjectJson"}
        if wrapper:
            imports.add("se.michaelthelin.spotify.model_objects.specification." + wrapper)
        return java, f"ModelObjectJson.{method}(JSON, {key_arg}{model}.class)", imports, model if method == "paging" else None

    name = ref_name(schema)
    schema = deref(spec, schema)
    if name and name.startswith("Paging"):
        return read("paging", paging_item(spec, {"$ref": "#/components/schemas/" + name}), wrapper="Paging")
    if name:
        return read("object", model_class(name))
    if schema.get("type") == "array" and schema["items"].get("type") == "boolean":
        return ("Boolean[]", "new Gson().fromJson(JsonParser.parseString(JSON).getAsJsonArray(), Boolean[].class)",
                {"com.google.gson.Gson", "com.google.gson.JsonParser"}, None)
    properties = schema.get("properties", {})
    if schema.get("type") == "object" and len(properties) == 1:
        key, value = next(iter(properties.items()))
        inner = ref_name(value)
        if inner and inner.startswith("Paging"):
            return read("paging", paging_item(spec, value), key=key, wrapper="Paging")
        if value.get("type") == "array" and ref_name(value["items"]):
            return read("array", model_class(ref_name(value["items"])), key=key)
    raise Unsupported(f"response schema of {op['operationId']}")


# --- documentation -----------------------------------------------------------------------------------

def javadoc_text(markdown):
    """The spec's Markdown as Javadoc HTML that passes doclint."""
    text = " ".join(markdown.split())
    text = text.replace("&", "&amp;").replace("@", "{@literal @}")
    text = re.sub(r"<br\s*/?>", "<br>", text)
    text = re.sub(r"`([^`]+)`", r"{@code \1}", text)
    text = re.sub(r"\*\*([^*]+)\*\*", r"<b>\1</b>", text)
    text = re.sub(r"(?<![\w{])_([^_]+)_(?!\w)", r"<i>\1</i>", text)

    def link(m):
        url = m.group(2)
        return f'<a href="{DOCS + url if url.startswith("/") else url}">{m.group(1)}</a>'

    return re.sub(r"\[([^\]]+)\]\(([^)]+)\)", link, text).strip()


def wrap(text, indent, first="", hang=""):
    """A Javadoc paragraph: the first line starts with `first`, continuation lines align under `hang`."""
    prefix = " " * indent + " * "
    lines = textwrap.wrap(text, WIDTH - len(prefix), initial_indent=first, subsequent_indent=hang,
                          break_long_words=False, break_on_hyphens=False)
    return [prefix + line for line in lines]


def doc(indent, *blocks):
    pad = " " * indent
    out = [pad + "/**"]
    for i, block in enumerate(b for b in blocks if b):
        if i:
            out.append(pad + " *")
        out.extend(block)
    out.append(pad + " */")
    return out


# --- parameters --------------------------------------------------------------------------------------

def parameters(spec, op, wanted):
    params = [deref(spec, p) for p in op.get("parameters", [])]
    for p in params:
        if p["in"] not in ("path", "query"):
            raise Unsupported(f"{p['in']} parameter {p['name']}")
    by_name = {p["name"]: p for p in params}
    if wanted is not None:
        params = [by_name[name] for name in wanted]
    return params


def parameter_setter(cls, p, paging):
    name, schema = p["name"], p["schema"]
    description = schema.get("description", "")
    required = "Required." if p.get("required") else "Optional."
    imports = set()

    if name == "market":
        java_type, checks = "CountryCode", [f"assert ({name} != null);"]
        imports.add("se.michaelthelin.spotify.enums.CountryCode")
    elif schema.get("type") == "integer":
        java_type = "Integer"
        low = max(schema.get("minimum", 0), *map(int, re.findall(r"Minimum: (\d+)", description)), 0)
        high = schema.get("maximum")
        checks = [f"assert ({name} != null);",
                  f"assert ({low} <= {name} && {name} <= {high});" if high is not None else f"assert ({name} >= {low});"]
    elif schema.get("type") == "boolean":
        java_type, checks = "Boolean", [f"assert ({name} != null);"]
    elif schema.get("type") == "string":
        java_type, checks = "String", [f"Preconditions.assertNotEmpty({name});"]
        imports.add("se.michaelthelin.spotify.internal.Preconditions")
        most = re.search(r"Maximum: (\d+) IDs|maximum of (\d+) (?:IDs|items)", description)
        if most:
            checks.append(f'assert ({name}.split(",").length <= {most.group(1) or most.group(2)});')
    else:
        raise Unsupported(f"type of parameter {name}")

    method = name + "_" if name in ("public", "default") else name  # setters keep the wire name, as everywhere in this library
    setter = "setPathParameter" if p["in"] == "path" else "setQueryParameter"
    override = paging and name in ("limit", "offset")
    head = f"@param {name} "
    lines = doc(4,
                [f"     * The {name} {p['in']} parameter setter."],
                wrap(f"{required} {javadoc_text(description)}", 4, head, " " * len(head))
                + [f"     * @return A {{@link {cls}.Builder}}."])
    if override:
        lines.append("    @Override")
    lines += [f"    public Builder {method}(final {java_type} {name}) {{"]
    lines += [f"      {c}" for c in checks]
    lines += [f'      return {setter}("{name}", {name});', "    }"]
    return lines, imports


# --- class -------------------------------------------------------------------------------------------

def render(spec, category, cls, entry, operations):
    verb, path, op = operations[entry["operation"]]
    deprecated = op.get("deprecated", False)
    java_type, expression, imports, paging = response(spec, op)
    call = {"GET": "getJson", "POST": "postJson", "PUT": "putJson", "DELETE": "deleteJson"}[verb]
    # a request body is never generated yet: every albums operation also takes its ids as a query parameter
    imports |= {"org.apache.hc.core5.http.ParseException",
                "se.michaelthelin.spotify.exceptions.SpotifyWebApiException",
                PACKAGE + ".AbstractDataRequest"}
    if paging:
        imports.add(PACKAGE + ".AbstractDataPagingRequest")
        builder_base = f"AbstractDataPagingRequest.Builder<{paging}, Builder>"
    else:
        builder_base = f"AbstractDataRequest.Builder<{java_type}, Builder>"

    setters = []
    for p in parameters(spec, op, entry.get("params")):
        lines, extra = parameter_setter(cls, p, paging)
        setters += [""] + lines
        imports |= extra

    deprecation = ["@deprecated This endpoint has been deprecated by Spotify."] if deprecated else None
    body = expression.replace("JSON", call + "()") if expression else call + "()"
    returns = javadoc_text(deref(spec, op["responses"].get("200") or op["responses"].get("201")
                                 or op["responses"].get("202") or op["responses"].get("204"))["description"])
    annotation = ["@Deprecated"] if deprecated else []

    out = [f"package {PACKAGE}.{category};", ""]
    out += [f"import {i};" for i in sorted(i for i in imports if not i.startswith("java."))]
    out += ["", "import java.io.IOException;", "", "// Generated by codegen/generate-requests.py from spec/openapi.yaml. Do not edit."]
    out += doc(0, wrap(javadoc_text(op.get("description") or op["summary"]), 0),
               [" * " + line for line in deprecation] if deprecation else None)
    out += annotation
    out += [f"public class {cls} extends AbstractDataRequest<{java_type}> {{", ""]
    out += doc(2, [f"   * The private {{@link {cls}}} constructor."], [f"   * @param builder A {{@link {cls}.Builder}}."])
    out += [f"  private {cls}(final Builder builder) {{", "    super(builder);", "  }", ""]
    out += doc(2, [f"   * Send the request."],
               wrap(returns, 2, "@return ", "        ")
               + ["   * @throws IOException            In case of networking issues.",
                  "   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause."])
    out += ["  @Override", f"  public {java_type} execute() throws", "    IOException,", "    SpotifyWebApiException,",
            "    ParseException {", f"    return {body};", "  }", ""]
    out += doc(2, [f"   * Builder class for building a {{@link {cls}}}."],
               ["   * " + line for line in deprecation] if deprecation else None)
    out += ["  " + a for a in annotation]
    out += [f"  public static final class Builder extends {builder_base} {{", ""]
    out += doc(4, [f"     * Create a new {{@link {cls}.Builder}} instance."],
               ["     * @param accessToken Required. A valid access token from the Spotify Accounts service."])
    out += ["    public Builder(final String accessToken) {", "      super(accessToken);", "    }"]
    out += setters
    out += [""] + doc(4, ["     * The request build method."], [f"     * @return A custom {{@link {cls}}}."])
    out += ["    @Override", f"    public {cls} build() {{", f'      setPath("/v1{path}");', f"      return new {cls}(this);", "    }", "",
            "    @Override", "    protected Builder self() {", "      return this;", "    }", "  }", "}", ""]
    return "\n".join(out)


def main():
    check = "--check" in sys.argv[1:]
    spec, operations = load_spec()
    descriptor = yaml.safe_load(DESCRIPTOR.read_text())
    stale = []
    for category, requests in descriptor.items():
        for cls, entry in requests.items():
            target = SOURCES / PACKAGE.replace(".", "/") / category / (cls + ".java")
            try:
                source = render(spec, category, cls, entry, operations)
            except Unsupported as e:
                sys.exit(f"{cls}: unsupported {e}")
            if target.exists() and target.read_text() == source:
                continue
            stale.append(target.relative_to(ROOT))
            if not check:
                target.write_text(source)
    verb = "out of date" if check else "written"
    for path in stale:
        print(f"{verb}: {path}")
    if check and stale:
        sys.exit("run ./codegen/generate-requests.py and commit the result")


if __name__ == "__main__":
    main()

#!/usr/bin/env python3
"""Keep the examples, the README, the migration guide and the vendored spec in sync with the code.

Run from the repository root: ./ci/check-docs.py
Exits non-zero and prints every drift it finds.
"""
import pathlib
import re
import sys

ROOT = pathlib.Path(__file__).resolve().parent.parent
API = ROOT / "src/main/java/se/michaelthelin/spotify/SpotifyApi.java"
REQUESTS = ROOT / "src/main/java/se/michaelthelin/spotify/requests"
DATA_REQUESTS = REQUESTS / "data"
EXAMPLES = ROOT / "examples"
README = ROOT / "README.md"
MIGRATION = ROOT / "MIGRATION.md"
SOURCES = ROOT / "src/main/java"
SPEC = ROOT / "spec/openapi.yaml"

# Convenience wrappers this library adds on top of /me/top/{type}, no spec counterpart.
SPEC_EXEMPT = {"GetUsersTopArtistsRequest", "GetUsersTopTracksRequest"}

# The authorization flows talk to accounts.spotify.com and are named after the flow, not the request.
NAME_EXEMPT_DIR = "authorization"

problems = []


def report(kind, message):
    problems.append(f"{kind}: {message}")


def request_class_of(example):
    m = re.search(r"import se\.michaelthelin\.spotify\.requests\.[A-Za-z0-9_.]*\.(\w+Request);", example.read_text())
    return m.group(1) if m else None


def request_source(name):
    return next(REQUESTS.rglob(name + ".java"), None)


def is_deprecated(java_file):
    return bool(re.search(r"^@Deprecated", java_file.read_text(), re.M))


def check_examples_cover_the_api(examples, flat):
    for method in re.findall(r"public [A-Za-z0-9_.]*Request\.Builder (\w+)\(", API.read_text()):
        if f".{method}(" not in flat:
            report("no example", f"SpotifyApi.{method} has no example")


def check_example_names(examples):
    for example in examples:
        if NAME_EXEMPT_DIR in example.parts:
            continue
        request = request_class_of(example)
        if request is None:
            report("no request", f"{rel(example)} imports no request class")
            continue
        expected = request[:-len("Request")] + "Example"
        if example.stem != expected:
            report("stale name", f"{rel(example)} uses {request}, so it should be named {expected}.java")


def check_readme(examples):
    text = README.read_text()
    linked = {}
    for line in text.split("\n"):
        m = re.search(r"\]\((examples/\S*?)\)", line)
        if m:
            linked[m.group(1)] = "⚠" in line
    for path in linked:
        if not (ROOT / path).is_file():
            report("dead link", f"README links {path}, which does not exist")
    for example in examples:
        path = rel(example)
        if path not in linked:
            report("unlisted", f"{path} is not linked from README.md")
            continue
        request = request_class_of(example)
        source = request and request_source(request)
        if source is None:
            continue
        deprecated = is_deprecated(source)
        if deprecated and not linked[path]:
            report("marker", f"{path} uses the deprecated {request} but its README entry has no ⚠️")
        if linked[path] and not deprecated:
            report("marker", f"{path} uses {request}, which is not deprecated, but its README entry has ⚠️")


def current_api():
    """Every name a reader can still write: packages, classes, methods, plus which of them are deprecated."""
    packages, classes, members = set(), {}, set()
    for source in SOURCES.rglob("*.java"):
        text = source.read_text()
        package = re.search(r"^package ([\w.]+);", text, re.M).group(1)
        packages.add(package)
        packages.add(package.replace("se.michaelthelin.spotify.", ""))
        classes[source.stem] = bool(re.search(r"^@Deprecated", text, re.M))
        members.update(re.findall(r"^  public [A-Za-z0-9_.<>\[\], ]+? (\w+)\(", text, re.M))

    methods = {}
    for deprecated, request, method in re.findall(
            r"(@Deprecated\s+)?public ([A-Za-z0-9_.]*Request)\.Builder (\w+)\(", API.read_text()):
        methods.setdefault(method, False)
        methods[method] |= classes.get(request.split(".")[-1], False)
    return packages, classes, methods, members


def check_migration_guide():
    """The guide's current-API side must still resolve, so a later rename cannot leave it lying.

    Columns headed "v9" and code lines under a `// v9` marker describe a released past and are read as-is.
    This proves the names exist and that their ⚠️ matches the code; it cannot prove a mapping is the right one.
    """
    packages, classes, methods, members = current_api()
    text = MIGRATION.read_text()
    legacy = set()
    findings = []

    def resolve(token):
        name = re.match(r"[A-Za-z_][A-Za-z0-9_]*", token).group(0)
        if "." in token.split("(")[0]:
            return token.split("(")[0] in packages or name in classes
        return name in classes or name in methods or name in members

    def tokens(cell):
        # Underscores mark a JSON field or a parameter name, neither of which is a class, method or package.
        return [t for t in re.findall(r"`([^`]+)`", cell)
                if re.fullmatch(r"[A-Za-z][A-Za-z0-9.]*(\(.*\))?", t)]

    fence = marker = None
    header = []
    for number, line in enumerate(text.split("\n"), 1):
        if line.startswith("```"):
            fence, marker = (None, None) if fence else (number, None)
            continue
        if fence:
            if re.fullmatch(r"\s*// v\d+", line):
                marker = line.strip()
            for method in re.findall(r"spotifyApi\.(\w+)\(", line):
                if marker == "// v9":
                    legacy.add(method)
                elif method not in methods:
                    findings.append((number, f"`spotifyApi.{method}` does not exist"))
            continue

        if not line.startswith("|"):
            header = []
            continue
        cells = [c.strip() for c in line.strip("|").split("|")]
        if not header:
            header = ["v9" in c for c in cells]
            continue
        if set("".join(cells)) <= set("-: "):
            continue
        for column, cell in enumerate(cells):
            if column < len(header) and header[column]:
                legacy.update(re.match(r"[A-Za-z_][A-Za-z0-9_.]*", t).group(0) for t in tokens(cell))
                continue
            for token in tokens(cell):
                if not resolve(token):
                    findings.append((number, f"`{token}` does not exist"))
                    continue
                name = re.match(r"[A-Za-z_][A-Za-z0-9_]*", token).group(0)
                if name in methods and methods[name] != ("⚠" in cell):
                    state = "is" if methods[name] else "is not"
                    findings.append((number, f"`{name}` {state} deprecated, which its ⚠️ does not match"))

    # Prose is checked last: by then every v9 name the tables and snippets introduced is known.
    for number, line in enumerate(text.split("\n"), 1):
        if line.startswith("|") or line.startswith("```") or line.startswith("    "):
            continue
        for token in tokens(line):
            # A single all-lowercase word in prose is a JSON field name, never a Java class, method or package.
            if re.fullmatch(r"[a-z][a-z0-9]*", token):
                continue
            name = re.match(r"[A-Za-z_][A-Za-z0-9_]*", token).group(0)
            if name not in legacy and token.split("(")[0] not in legacy and not resolve(token):
                findings.append((number, f"`{token}` does not exist and no table lists it as a v9 name"))

    for target in re.findall(r"\]\((?!http)([^)#]+)", text):
        if not (ROOT / target).exists():
            report("dead link", f"MIGRATION.md links {target}, which does not exist")
    for number, message in findings:
        report("stale guide", f"MIGRATION.md:{number} {message}")


def check_deprecation_matches_spec():
    spec = {}
    path = verb = None
    for line in SPEC.read_text().split("\n"):
        m = re.match(r"^  (/\S*):\s*$", line)
        if m:
            path = re.sub(r"\{[^}]+\}", "{id}", m.group(1))
        m = re.match(r"^    (get|put|post|delete|patch):\s*$", line)
        if m:
            verb = m.group(1).upper()
            spec[(verb, path)] = False
        elif re.match(r"^      deprecated: true", line) and verb:
            spec[(verb, path)] = True

    verbs = (("deleteJson", "DELETE"), ("putJson", "PUT"), ("postJson", "POST"), ("getJson", "GET"))
    for source in sorted(DATA_REQUESTS.rglob("*Request.java")):
        if source.stem in SPEC_EXEMPT:
            continue
        text = source.read_text()
        m = re.search(r'setPath\("([^"]+)"\)', text)
        if not m:
            continue
        endpoint = re.sub(r"\{[^}]+\}", "{id}", m.group(1).replace("/v1", ""))
        verb = next((v for call, v in verbs if re.search(r"\b" + call + r"\(\)", text)), None)
        key = (verb, endpoint)
        if key not in spec:
            report("not in spec", f"{source.stem} calls {verb} {endpoint}, which the vendored spec does not define")
        elif spec[key] != is_deprecated(source):
            state = "deprecated" if spec[key] else "not deprecated"
            report("deprecation", f"{source.stem} disagrees with the spec, which marks {verb} {endpoint} {state}")


def rel(path):
    return str(path.relative_to(ROOT))


def main():
    examples = sorted(EXAMPLES.rglob("*Example.java"))
    flat = "".join(e.read_text() for e in examples).replace(" ", "").replace("\n", "")
    check_examples_cover_the_api(examples, flat)
    check_example_names(examples)
    check_readme(examples)
    check_migration_guide()
    check_deprecation_matches_spec()

    if problems:
        print("\n".join(sorted(problems)))
        print(f"\n{len(problems)} problem(s) found")
        return 1
    print(f"{len(examples)} examples check out against SpotifyApi, README.md, MIGRATION.md and spec/openapi.yaml")
    return 0


if __name__ == "__main__":
    sys.exit(main())

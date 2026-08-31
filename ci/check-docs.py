#!/usr/bin/env python3
"""Keep the examples, the README and the vendored spec in sync with the code.

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
    check_deprecation_matches_spec()

    if problems:
        print("\n".join(sorted(problems)))
        print(f"\n{len(problems)} problem(s) found")
        return 1
    print(f"{len(examples)} examples check out against SpotifyApi, README.md and spec/openapi.yaml")
    return 0


if __name__ == "__main__":
    sys.exit(main())

#!/usr/bin/env python3
"""How far generation reaches: renders every hand-written data request from its spec operation and
compares the public Builder setters and the return type with the class as it is.

Run from anywhere: ./codegen/survey.py
"""
import importlib.util, pathlib, re, sys, collections
ROOT = pathlib.Path(__file__).resolve().parent.parent
spec_ = importlib.util.spec_from_file_location("gen", ROOT / "codegen/generate-requests.py")
gen = importlib.util.module_from_spec(spec_); spec_.loader.exec_module(gen)
spec, operations = gen.load_spec()
by_route = {}
for opid, (verb, path, op) in operations.items():
    by_route[(verb, re.sub(r"\{[^}]+\}", "{id}", path))] = opid
verbs = (("deleteJson", "DELETE"), ("putJson", "PUT"), ("postJson", "POST"), ("getJson", "GET"))
def api(text):
    return sorted(set(re.findall(r"public Builder (\w+)\((?:final )?([\w<>\[\].]+) ", text)))
res = collections.Counter(); reasons = collections.Counter(); diffs = []
for src in sorted((ROOT / "src/main/java/se/michaelthelin/spotify/requests/data").rglob("*Request.java")):
    text = src.read_text()
    m = re.search(r'setPath\("([^"]+)"\)', text)
    verb = next((v for c, v in verbs if re.search(r"\b" + c + r"\(\)", text)), None)
    if not m or not verb: res["no fixed path/verb"] += 1; continue
    opid = by_route.get((verb, re.sub(r"\{[^}]+\}", "{id}", m.group(1).replace("/v1", ""))))
    if not opid: res["no spec operation"] += 1; continue
    try:
        out = gen.render(spec, src.parent.name, src.stem, {"operation": opid}, operations)
    except gen.Unsupported as e:
        res["unsupported"] += 1; reasons[str(e)] += 1; continue
    except Exception as e:
        res["crash"] += 1; reasons["crash " + type(e).__name__ + " " + str(e)[:60]] += 1; continue
    a, b = api(text), api(out)
    rt = re.search(r"extends AbstractDataRequest<(.+?)> \{", text); rt2 = re.search(r"extends AbstractDataRequest<(.+?)> \{", out)
    if a == b and rt and rt2 and rt.group(1) == rt2.group(1):
        res["same public API"] += 1
    else:
        res["API differs"] += 1
        diffs.append((src.stem, sorted(set(a) - set(b)), sorted(set(b) - set(a)), rt and rt.group(1), rt2 and rt2.group(1)))
for k, v in res.most_common():
    print(f"{v:4} {k}")
print("\nunsupported:")
for k, v in reasons.most_common():
    print(f"{v:4} {k}")
print("\npublic API differs (class, only hand-written, only generated, hand-written return, generated return):")
for d in diffs:
    print("  ", d)

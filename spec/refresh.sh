#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
curl -fsSL https://developer.spotify.com/reference/web-api/open-api-schema.yaml -o openapi.yaml

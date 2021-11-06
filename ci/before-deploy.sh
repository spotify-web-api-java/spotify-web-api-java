#!/usr/bin/env bash
echo "$GPG_PASSPHRASE_FILE" | gpg --batch --yes --passphrase-fd 0 --output ci/codesigning.asc --decrypt ci/codesigning.asc.enc
gpg --batch --fast-import ci/codesigning.asc

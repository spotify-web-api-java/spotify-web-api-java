#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_81c98acad902_key -iv $encrypted_81c98acad902_iv -in ci/codesigning.asc.enc -out ci/codesigning.asc -d
gpg --batch --fast-import ci/codesigning.asc

#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_81c98acad902_key -iv $encrypted_81c98acad902_iv -in travis/codesigning.asc.enc -out travis/codesigning.asc -d
gpg --batch --fast-import travis/codesigning.asc

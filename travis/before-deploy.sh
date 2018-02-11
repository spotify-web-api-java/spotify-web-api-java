#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_3e6b49107f67_key -iv $encrypted_3e6b49107f67_iv -in travis/codesigning.asc.enc -out travis/codesigning.asc -d
gpg --batch --fast-import travis/codesigning.asc
#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_3e6b49107f67_key -iv $encrypted_3e6b49107f67_iv -in codesigning.asc.enc -out codesigning.asc -d
gpg --fast-import travis/codesigning.asc

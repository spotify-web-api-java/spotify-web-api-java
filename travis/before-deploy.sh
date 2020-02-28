#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_636eb5daea97_key -iv $encrypted_636eb5daea97_iv -in travis/codesigning.asc.enc -out travis/codesigning.asc -d
gpg --batch --fast-import travis/codesigning.asc

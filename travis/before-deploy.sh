#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_ed1911d66365_key -iv $encrypted_ed1911d66365_iv -in travis/codesigning.asc.enc -out travis/codesigning.asc -d
gpg --batch --fast-import travis/codesigning.asc
#!/usr/bin/env bash
openssl aes-256-cbc -K $encrypted_3e6b49107f67_key -iv $encrypted_3e6b49107f67_iv -in travis/codesigning.asc.enc -out travis/codesigning.asc -d
echo "allow-loopback-pinentry" > ~/.gnupg/gpg-agent.conf
echo "pinentry-mode loopback" > ~/.gnupg/gpg.conf
echo "use-agent" >> ~/.gnupg/gpg.conf
cat ~/.gnupg/gpg.conf
gpg --list-secret-keys
gpg --batch --fast-import travis/codesigning.asc
gpg --list-secret-keys
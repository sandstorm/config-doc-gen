#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    openssl aes-256-cbc -K $encrypted_42338e236d2b_key -iv $encrypted_42338e236d2b_iv -in secret.gpg.enc -out ~\/config-doc-gen-gpg/secret.gpg -d
fi

#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    echo "Decrypt GPG secret from $ENCRYPTED_GPG_KEY_LOCATION to $GPG_KEY_LOCATION"
    openssl aes-256-cbc -K $encrypted_42338e236d2b_key -iv $encrypted_42338e236d2b_iv -in $ENCRYPTED_GPG_KEY_LOCATION -out $GPG_KEY_LOCATION -d
fi

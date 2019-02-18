#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    echo "Sign and Upload Archives"
    ./gradlew publish
    if [ "$MANUAL_RELEASE_TRIGGERED" = "true" ];
    then
        echo "Promote repository"
        ./gradlew closeAndReleaseRepository
    fi
fi

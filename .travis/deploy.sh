#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    echo "Sign and Upload Archives"
    ./gradlew :annotations:publish :core:publish :processor-standalone:publish :processor-spring:publish
    if [ "$MANUAL_RELEASE_TRIGGERED" = "true" ];
    then
        echo "Promote repository"
        ./gradlew :annotations:closeAndReleaseRepository :core:closeAndReleaseRepository :processor-spring:closeAndReleaseRepository :processor-standalone:closeAndReleaseRepository
    fi
fi
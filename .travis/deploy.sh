#!/usr/bin/env sh

if [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ];
then
    echo "Sign Archives - annotations"
    ./gradlew :annotations:signArchives
    echo "Upload Archives - annotations"
    ./gradlew :annotations:uploadArchives
    echo "Sign Archives - core"
    ./gradlew :core:signArchives
    echo "Upload Archives - core"
    ./gradlew :core:uploadArchives
    echo "Sign Archives - processor-spring"
    ./gradlew :processor-spring:signArchives
    echo "Upload Archives - processor-spring"
    ./gradlew :processor-spring:uploadArchives
    echo "Sign Archives - processor-standalone"
    ./gradlew :processor-standalone:signArchives
    echo "Upload Archives - processor-standalone"
    ./gradlew :processor-standalone:uploadArchives
    if [ "$MANUAL_RELEASE_TRIGGERED" = "true" ];
    then
        echo "Promote repository - annotations"
        ./gradlew :annotations:closeAndReleaseRepository
        echo "Promote repository - core"
        ./gradlew :core:closeAndReleaseRepository
        echo "Promote repository - processor-spring"
        ./gradlew :processor-spring:closeAndReleaseRepository
        echo "Promote repository - processor-standalone"
        ./gradlew :processor-standalone:closeAndReleaseRepository
    fi
fi
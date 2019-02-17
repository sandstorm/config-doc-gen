#!/usr/bin/env bash

echo "container command: $COMMAND"

echo "installing dependencies ..."
yarn install

case $COMMAND in
    start)
        yarn start
        ;;
    bash)
        /bin/bash
        ;;
    package-ui)
        yarn build
        find build/static/js -type f -name 'main.*.js' | xargs -i head -1 {} > /target-output/react-ui-compiled/main.js
        find build/static/css -type f -name 'main.*.css' | xargs -i head -1 {} > /target-output/react-ui-compiled/main.css
        ;;
    *)
        echo "unknown command: $COMMAND"
esac


#!/usr/bin/env bash

echo "running command: $COMMAND"

docker run -it --rm \
    -p 3000:3000 \
    -e "COMMAND=$COMMAND" \
    -v "$(pwd)/../../react-ui/.storybook/:/app/.storybook/:ro,cached" \
    -v "$(pwd)/../../react-ui/public/:/app/public/:ro,cached" \
    -v "$(pwd)/../../react-ui/src/:/app/src/:ro,cached" \
    -v "$(pwd)/../../react-ui/src/index.css:/app/src/index.css:cached" \
    -v "$(pwd)/../../react-ui/images.d.ts:/app/images.d.ts:ro,cached" \
    -v "$(pwd)/../../react-ui/package.json:/app/package.json:ro,cached" \
    -v "$(pwd)/../../react-ui/package-lock.json:/app/package-lock.json:cached" \
    -v "$(pwd)/../../react-ui/tsconfig.json:/app/tsconfig.json:ro,cached" \
    -v "$(pwd)/../../react-ui/tsconfig.prod.json:/app/tsconfig.prod.json:ro,cached" \
    -v "$(pwd)/../../react-ui/tsconfig.test.json:/app/tsconfig.test.json:ro,cached" \
    -v "$(pwd)/../../react-ui/tslint.json:/app/tslint.json:ro,cached" \
    -v "$(pwd)/../../react-ui/yarn.lock:/app/yarn.lock:cached" \
    -v "$(pwd)/../../react-ui/yarn-error.log:/app/yarn-error.log:cached" \
    -v "$(pwd)/../../core/src/main/resources/react-ui-compiled/:/target-output/react-ui-compiled:cached" \
    -v "$(pwd)/../../tmp/react-ui/.yarn-cache:/app/.yarn-cache:cached" \
    -v "$(pwd)/../../tmp/react-ui/node_modules:/app/node_modules:cached" \
    config-doc-gen/react-ui-local-dev

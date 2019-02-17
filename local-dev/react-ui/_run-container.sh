#!/usr/bin/env bash

echo "initializing command: $COMMAND"

echo "{\"version\":\"$(git rev-parse --short HEAD)\"}" > ../../react-ui/src/VERSION.json
echo "written VERSION.json"

DOCKER_ARGS=(-e "COMMAND=$COMMAND")

if [[ "$COMMAND" = "start" ]] || [[ "$COMMAND" = 'bash' ]]; then
    DOCKER_ARGS+=(-it -p 3000:3000 -v "$(pwd)/../../tmp/react-ui/.yarn-cache:/app/.yarn-cache:cached")
fi

DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/.storybook/:/app/.storybook/:ro,cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/node_modules:/app/node_modules:cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/public/:/app/public/:ro,cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/src/:/app/src/:cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/images.d.ts:/app/images.d.ts:ro,cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/package.json:/app/package.json:cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/tsconfig.json:/app/tsconfig.json:ro,cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/tsconfig.prod.json:/app/tsconfig.prod.json:ro,cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/tsconfig.test.json:/app/tsconfig.test.json:ro,cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/tslint.json:/app/tslint.json:ro,cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/yarn.lock:/app/yarn.lock:cached")
DOCKER_ARGS+=(-v "$(pwd)/../../react-ui/yarn-error.log:/app/yarn-error.log:cached")
DOCKER_ARGS+=(-v "$(pwd)/../../core/src/main/resources/react-ui-compiled/:/target-output/react-ui-compiled:cached")

echo "running docker container ..."
docker run --rm \
    "${DOCKER_ARGS[@]}" \
    config-doc-gen/react-ui-local-dev

FROM node:11

WORKDIR /app

ADD entrypoint.sh /app
RUN chmod +x /app/entrypoint.sh

RUN yarn config set cache-folder /app/.yarn-cache

CMD ["./entrypoint.sh"]

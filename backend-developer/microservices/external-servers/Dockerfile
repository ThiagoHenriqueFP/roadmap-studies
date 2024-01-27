FROM node:20.11-alpine3.18

EXPOSE 3000

COPY package.json package.json
COPY package-lock.json package-lock.json
COPY .env .env

RUN yarn

COPY src/ src/

ENTRYPOINT ["yarn", "start-prod"]
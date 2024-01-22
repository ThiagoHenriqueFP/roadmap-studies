FROM node:22-alpine3.18

workdir /exser

COPY package.json .
COPY src/ .

ENTRYPOINT ["npm", "run", "dev"]
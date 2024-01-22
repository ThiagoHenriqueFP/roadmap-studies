FROM node:20-alpine3.18

WORKDIR /exser

COPY package.json .
COPY src/ .

ENTRYPOINT ["npm", "run", "dev"]
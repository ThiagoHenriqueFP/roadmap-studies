FROM node:20-alpine3.18

EXPOSE 3000

COPY package.json .
CMD ["npm", "install"]

COPY src/ .

ENTRYPOINT ["npm", "run", "dev"]
FROM node:17.7.2

WORKDIR /app


COPY . .
RUN npm install
RUN npm run build

EXPOSE 3000

ENTRYPOINT [ "npm","run","start" ]
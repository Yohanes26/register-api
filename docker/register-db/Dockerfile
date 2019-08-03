FROM postgres:latest

ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=123456
ENV POSTGRES_DB=register

ADD ./01-schema.sql /docker-entrypoint-initdb.d
ADD ./02-data.sql /docker-entrypoint-initdb.d

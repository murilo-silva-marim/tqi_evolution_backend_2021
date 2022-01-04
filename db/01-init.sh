#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE DATABASE $APP_DB_NAME;
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB_NAME TO $APP_DB_USER;
  \connect $APP_DB_NAME $APP_DB_USER
  BEGIN;
    CREATE TABLE IF NOT EXISTS CLIENTES (
	id SERIAL PRIMARY KEY NOT NULL,
	nome varchar(50),
	email varchar(100),
	cpf varchar (11),
	rg varchar(30),
	endereco varchar(200),
	renda float,
	senha varchar(100),
	role varchar(100)
);

CREATE TABLE IF NOT EXISTS EMPRESTIMOS (
	id SERIAL PRIMARY KEY NOT NULL,
	valor float,
	primeira_parcela DATE,
	quantidade_parcelas integer,
	cliente_id integer,
	FOREIGN KEY(cliente_id) REFERENCES CLIENTES(id)
);

  COMMIT;
EOSQL

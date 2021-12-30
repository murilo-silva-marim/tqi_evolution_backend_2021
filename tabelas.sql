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

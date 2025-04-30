CREATE SCHEMA IF NOT EXISTS apiproposta;

CREATE TABLE IF NOT EXISTS apiproposta.tb_usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    renda NUMERIC(19, 2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS apiproposta.tb_proposta (
    id SERIAL PRIMARY KEY,
    valor_solicitado NUMERIC(19, 2),
    prazo_pagamento INT,
    aprovada BOOLEAN,
    integrada BOOLEAN DEFAULT TRUE,
    observacao TEXT,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES apiproposta.tb_usuario(id)
    );


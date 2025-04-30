CREATE SCHEMA IF NOT EXISTS apiproposta;

CREATE TABLE IF NOT EXISTS apiproposta.tb_proposta (
    id SERIAL PRIMARY KEY,
    valor_solicitado NUMERIC(19, 2),
    prazo_pagamento INT NOT NULL,
    aprovada BOOLEAN,
    integrada BOOLEAN DEFAULT TRUE,
    observacao TEXT,
    usuario_id BIGINT,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
    );
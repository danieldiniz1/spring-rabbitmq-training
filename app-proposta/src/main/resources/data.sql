INSERT INTO apiproposta.tb_proposta (id, aprovada, observacao)
VALUES (1, true, 'Proposta inicial')
ON CONFLICT (id) DO NOTHING;

INSERT INTO apiproposta.tb_usuario (id, nome, sobrenome, cpf, telefone, renda)
VALUES (1, 'Admin', 'User', '00000000000', '999999999', 10000.00)
ON CONFLICT (id) DO NOTHING;
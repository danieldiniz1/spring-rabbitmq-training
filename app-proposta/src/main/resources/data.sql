INSERT INTO apiproposta.tb_usuario (id, nome, sobrenome, cpf, telefone, renda)
VALUES (1, 'Admin', 'User exemplo', '00000000000', '999999999', 10000.00)
ON CONFLICT (id) DO NOTHING;

INSERT INTO apiproposta.tb_proposta (id, aprovada, observacao,usuario_id, valor_solicitado, prazo_pagamento)
VALUES (1, true, 'Proposta inicial exemplo',1,1000,12)
ON CONFLICT (id) DO NOTHING;

SELECT setval('apiproposta.tb_usuario_id_seq', (SELECT MAX(id) FROM apiproposta.tb_usuario));
SELECT setval('apiproposta.tb_proposta_id_seq', (SELECT MAX(id) FROM apiproposta.tb_proposta));
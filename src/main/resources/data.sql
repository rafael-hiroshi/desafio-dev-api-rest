INSERT INTO pessoas (cpf, data_nascimento, nome) VALUES('88370628001', '2000-09-12', 'Rafael Dev');
INSERT INTO pessoas (cpf, data_nascimento, nome) VALUES('10444233660', '2000-11-03', 'Developer Test');
INSERT INTO pessoas (cpf, data_nascimento, nome) VALUES('11122233345', '2000-05-23', 'Test Case');

INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(1, '2021-12-10', 1, 1000, 16000, 2, 1);
INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(2, '2021-12-11', 1, 1000, 2500, 1, 1);
INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(3, '2021-12-11', 1, 1000, 4000, 1, 2);
INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(4, '2021-12-11', 1, 1000, 0, 1, 2);
INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(5, '2021-12-12', 1, 2500, 0, 1, 2);
INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(6, '2021-12-12', 1, 2500, 0, 1, 2);
INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(80, '2021-12-12', 0, 2500, 0, 1, 3);
INSERT INTO contas (id_conta, data_criacao, flag_ativo, limite_saque_diario, saldo, tipo_conta, id_pessoa) VALUES(81, '2021-12-12', 0, 2500, 0, 1, 3);

INSERT INTO transacoes (data_transacao, valor, id_conta) VALUES('2021-12-04 00:00:00', 1000, 1);
INSERT INTO transacoes (data_transacao, valor, id_conta) VALUES('2021-12-05 00:00:00', 1000, 1);
INSERT INTO transacoes (data_transacao, valor, id_conta) VALUES('2021-12-06 00:00:00', -850, 1);
INSERT INTO transacoes (data_transacao, valor, id_conta) VALUES('2021-12-07 00:00:00', -600, 2);
INSERT INTO transacoes (data_transacao, valor, id_conta) VALUES('2021-12-08 00:00:00', 1000, 2);


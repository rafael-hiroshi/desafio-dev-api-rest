CREATE TABLE pessoas (
    `id_pessoa` bigint(20) NOT NULL AUTO_INCREMENT,
    `cpf` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `data_nascimento` date DEFAULT NULL,
    `nome` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id_pessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE contas (
    `id_conta` bigint(20) NOT NULL AUTO_INCREMENT,
    `data_criacao` date DEFAULT NULL,
    `flag_ativo` bit(1) DEFAULT NULL,
    `limite_saque_diario` decimal(19,2) DEFAULT NULL,
    `saldo` decimal(19,2) DEFAULT NULL,
    `tipo_conta` int(11) DEFAULT NULL,
    `id_pessoa` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id_conta`),
    KEY `id_pessoa` (`id_pessoa`),
    CONSTRAINT `fk_id_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE transacoes (
    `id_transacao` bigint(20) NOT NULL AUTO_INCREMENT,
    `data_transacao` datetime(6) DEFAULT NULL,
    `valor` decimal(19,2) DEFAULT NULL,
    `id_conta` bigint(20) NOT NULL,
    PRIMARY KEY (`id_transacao`),
    KEY `id_conta` (`id_conta`),
    CONSTRAINT `fk_id_conta` FOREIGN KEY (`id_conta`) REFERENCES `contas` (`id_conta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

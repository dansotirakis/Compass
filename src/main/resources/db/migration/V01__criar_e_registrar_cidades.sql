CREATE TABLE city (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO city (nome) values ('Belem');
INSERT INTO city (nome) values ('São paulo');
INSERT INTO city (nome) values ('Florianopolis');
INSERT INTO city (nome) values ('Belterra');
INSERT INTO city (nome) values ('Maruda');
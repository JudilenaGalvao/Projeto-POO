CREATE TABLE cliente(
  cpf BIGINT UNIQUE NOT NULL PRIMARY KEY,
  nome TEXT NOT NULL,
  telefone BIGINT,
  senha TEXT
);


CREATE TABLE atracao(
  cod_atracao INT2 PRIMARY KEY,
  nome TEXT,
  data DATE,
  horario TIME,
  genero TEXT
);

CREATE TABLE compra(
  id SERIAL PRIMARY KEY,
  data TEXT,
  valor FLOAT4,
  cod_cadeira INT2,
  FK_Cliente BIGINT, 
  FK_Atracao INT2,	
  FOREIGN KEY(FK_Cliente) REFERENCES cliente(cpf) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(FK_Atracao) REFERENCES atracao(cod_atracao) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE ingresso(
  cod_ingresso SERIAL PRIMARY KEY,
  FK_Compra SERIAL,
  FK_Atracao INT2,
  FOREIGN KEY(FK_Compra) REFERENCES compra(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(FK_Atracao) REFERENCES atracao(cod_atracao) ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE favoritos(
  FK_Cliente BIGINT,
  FK_Atracao INT2,
  favorito BOOLEAN,
  FOREIGN KEY(FK_Cliente) REFERENCES cliente(cpf) ON UPDATE CASCADE,
  FOREIGN KEY(FK_Atracao) REFERENCES atracao(cod_Atracao) ON UPDATE CASCADE
);



SELECT * FROM cliente;
SELECT * FROM compra;
SELECT * FROM ingresso;
SELECT * FROM atracao;
SELECT * FROM favoritos;

DROP TABLE cliente

-----------------------------tabela cliente--------------------------------------------------
INSERT INTO cliente(cpf,nome,telefone,senha) VALUES(12345678987,'Romulo Felipe',8541208423, 'romulo123');
INSERT INTO cliente(cpf,nome,telefone,senha) VALUES(11111111111,'judi',997198653, '123');


-----------------------------tabela atracao--------------------------------------------------
INSERT INTO atracao(cod_atracao,nome,data,horario,genero) VALUES(501,'Queen','2022/12/27','13:00:00','musical');
INSERT INTO atracao(cod_atracao,nome,data,horario,genero) VALUES(502,'Matheus Ceará - Vocês pedem, eu conto!','2023/01/15','21:00:00','stand-up');
INSERT INTO atracao(cod_atracao,nome,data,horario,genero) VALUES(503,'Lago dos cisnes','2023/01/17','09:45:00','musical');
INSERT INTO atracao(cod_atracao,nome,data,horario,genero) VALUES(504,'Alice no país das maravilhas','2023/02/03','16:05:00','ficcao-infantil');


-----------------------------tabela compra----------------------------------------------------
INSERT INTO compra(data,valor,cod_cadeira,FK_Cliente,FK_Atracao) VALUES('2022/12/15', 80.00, 5, 12345678987, 501);
INSERT INTO compra(data,valor,cod_cadeira,FK_Cliente,FK_Atracao) VALUES('2022/12/15', 80.00, 6, 12345678987, 501);
INSERT INTO compra(data,valor,cod_cadeira,FK_Cliente,FK_Atracao) VALUES('2022/12/15', 80.00, 7, 12345678987, 501);
INSERT INTO compra(data,valor,cod_cadeira,FK_Cliente,FK_Atracao) VALUES('2022/12/19', 80.00, 6, 11111111111, 503);


-----------------------------tabela ingresso-------------------------------------------------
INSERT INTO ingresso(FK_Compra,FK_Atracao) VALUES(1, 501);
INSERT INTO ingresso(FK_Compra,FK_Atracao) VALUES(2, 501);
INSERT INTO ingresso(FK_Compra,FK_Atracao) VALUES(3, 501);
INSERT INTO ingresso(FK_Compra,FK_Atracao) VALUES(4, 503);


-----------------------------tabela favoritos------------------------------------------------
INSERT INTO favoritos(FK_Cliente,FK_Atracao,favorito) VALUES(12345678987, 501,'true');
INSERT INTO favoritos(FK_Cliente,FK_Atracao,favorito) VALUES(11111111111, 503,'true');



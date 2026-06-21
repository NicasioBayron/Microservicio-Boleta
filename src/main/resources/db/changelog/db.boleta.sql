--liquibase formatted sql

--changeset ariel:21 create-boletas
CREATE TABLE boletas (
    idBoleta BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPago BIGINT NOT NULL,
    idUsuario BIGINT NOT NULL,
    montoNeto INT NOT NULL,
    impuestoIva INT NOT NULL,
    montoTotal INT NOT NULL,
    fechaEmision TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset ariel:22 insert-boletas
INSERT INTO boletas (idPago, idUsuario, montoNeto, impuestoIva, montoTotal) VALUES
(1, 1, 21008, 3991, 25000),
(2, 2, 12605, 2394, 15000),
(3, 3, 26890, 5109, 32000),
(4, 4, 10504, 1995, 12500),
(5, 5, 16807, 3192, 20000),
(6, 6, 42016, 7993, 50000),
(7, 7, 8403, 1596, 10000),
(8, 8, 25210, 4799, 30000),
(9, 9, 31507, 6000, 37500),
(10, 10, 16814, 3192, 20000);
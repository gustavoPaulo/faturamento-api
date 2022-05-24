CREATE TABLE faturamento (

    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tipo VARCHAR(20) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL,
    lancamento DATETIME,
    descricao VARCHAR(200)
)
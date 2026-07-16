CREATE SCHEMA IF NOT EXISTS precos;

CREATE TABLE IF NOT EXISTS precos.precificacao (
    id                     BIGSERIAL PRIMARY KEY,
    codigo_ativo           VARCHAR(20) NOT NULL,
    preco                  NUMERIC(19, 4) NOT NULL CHECK (preco >= 0),
    data_hora_atualizacao  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_precificacao_ativo_atualizacao
    ON precos.precificacao (codigo_ativo, data_hora_atualizacao DESC);
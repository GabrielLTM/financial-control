-- V1__Create_Initial_Schema.sql

-- Sequences para geração de IDs (otimização do Hibernate)
CREATE SEQUENCE accounts_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE transactions_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 50;

-- Tabela de usuários
CREATE TABLE users
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    fullname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- Tabela de junção para os papéis (roles) do usuário
-- Esta é a correção principal para o problema do List<String>
CREATE TABLE user_roles
(
    user_id BIGINT       NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Tabela de contas
CREATE TABLE accounts
(
    id      BIGINT       NOT NULL,
    name    VARCHAR(255) NOT NULL,
    balance DECIMAL(19, 2),
    user_id BIGINT       NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (id),
    CONSTRAINT fk_accounts_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Tabela de categorias
CREATE TABLE category
(
    id      BIGINT       NOT NULL,
    name    VARCHAR(255) NOT NULL,
    user_id BIGINT       NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id),
    CONSTRAINT fk_category_on_user FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Tabela de transações
CREATE TABLE transactions
(
    id          BIGINT         NOT NULL,
    description VARCHAR(255)   NOT NULL,
    "value"     DECIMAL(19, 2) NOT NULL, -- Usando aspas por ser palavra reservada
    "date"      DATE           NOT NULL, -- Usando aspas por ser palavra reservada
    "type"      SMALLINT,                -- Usando aspas por ser palavra reservada
    account_id  BIGINT         NOT NULL,
    category_id BIGINT         NOT NULL,
    CONSTRAINT pk_transactions PRIMARY KEY (id),
    CONSTRAINT fk_transactions_on_account FOREIGN KEY (account_id) REFERENCES accounts (id),
    CONSTRAINT fk_transactions_on_category FOREIGN KEY (category_id) REFERENCES category (id)
);
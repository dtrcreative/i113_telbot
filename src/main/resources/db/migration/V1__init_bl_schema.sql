create schema if not exists telbot;

SET search_path TO telbot;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id               SERIAL PRIMARY KEY,
    user_id          VARCHAR(200) unique,
    user_secret_code VARCHAR(50) unique,
    chat_id          VARCHAR(100),
    user_name        VARCHAR(100),
    first_name       VARCHAR(100),
    last_name         VARCHAR(100),
    status           VARCHAR(100) default 'NOTCOMFIRMED'
);
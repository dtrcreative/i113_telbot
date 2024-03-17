create schema if not exists telbot;

SET search_path TO telbot;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    chat_id    VARCHAR(100) unique,
    user_name  VARCHAR(100) unique,
    reg_user   VARCHAR(100) unique,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    is_active   boolean default false
);
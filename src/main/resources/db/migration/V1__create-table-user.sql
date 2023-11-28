CREATE TYPE typeUser AS ENUM('ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(14) NOT NULL,
    type_user typeUser NOT NULL,
    password_user VARCHAR(150) NOT NULL
);
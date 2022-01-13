CREATE SEQUENCE sequence_users START 300000;

CREATE TABLE users (
    user_id INTEGER CONSTRAINT users_pk PRIMARY KEY,
    email VARCHAR(30),
    username VARCHAR(15),
    password TEXT,
    location TEXT,
    age INTEGER,
    enabled BOOLEAN
);

ALTER SEQUENCE sequence_users OWNED BY users.user_id;

CREATE SEQUENCE sequence_roles START 1;

CREATE TABLE roles (
    id INTEGER CONSTRAINT roles_pk PRIMARY KEY,
    name VARCHAR(15)
);

ALTER SEQUENCE sequence_roles OWNED BY roles.id;

CREATE TABLE user_roles (
    user_id INTEGER,
    role_id INTEGER,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_roles_user_id_fk FOREIGN KEY(user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE,
    CONSTRAINT user_roles_role_id_fk FOREIGN KEY(role_id)
        REFERENCES roles(id)
);
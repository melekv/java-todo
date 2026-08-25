CREATE TABLE users (
    id BINARY(16) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    PRIMARY KEY (id),
    CONSTRAINT unq_email UNIQUE (email)
);

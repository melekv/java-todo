CREATE TABLE todos (
    id BINARY(16) NOT NULL,
    title VARCHAR(20) NOT NULL,
    description VARCHAR(255) NULL,
    status VARCHAR(20) NOT NULL,
    user_id BINARY(16) NOT NULL,
    category_id BINARY(16) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_todos_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),
    CONSTRAINT fk_todos_category
        FOREIGN KEY (category_id)
        REFERENCES categories(id)
);

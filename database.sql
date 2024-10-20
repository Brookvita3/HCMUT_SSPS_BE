CREATE TABLE IF NOT EXISTS "user"
(
    id          VARCHAR(255) NOT NULL,
    username    VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    role        ENUM('USER', 'ADMIN') NOT NULL,
    PRIMARY KEY (id)
);

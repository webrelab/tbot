CREATE TABLE users (
    user_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    city_id VARCHAR(36) NOT NULL,
    chat_id VARCHAR(10) NOT NULL,
    role VARCHAR(36) NOT NULL,
    functional_role_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id),
    KEY (chat_id),
    FOREIGN KEY (city_id) REFERENCES cities(city_id),
    FOREIGN KEY (functional_role_id) REFERENCES functional_roles(functional_role_id)
);
CREATE TABLE role_requests(
    role_request_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    role VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    role_request_file_path VARCHAR(255),
    role_request_description TEXT,
    PRIMARY KEY (role_request_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
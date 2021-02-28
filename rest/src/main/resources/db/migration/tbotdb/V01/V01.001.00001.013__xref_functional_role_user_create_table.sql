CREATE TABLE xref_functional_role_user (
    functional_role_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (functional_role_id) REFERENCES functional_roles(functional_role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
)
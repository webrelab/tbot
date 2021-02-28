CREATE TABLE xref_formed_group_user (
    formed_group_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (formed_group_id) REFERENCES formed_groups(formed_group_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
)
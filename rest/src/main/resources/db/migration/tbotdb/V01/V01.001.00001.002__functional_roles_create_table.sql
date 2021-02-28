CREATE TABLE functional_roles (
    functional_role_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    functional_role_name VARCHAR(255) NOT NULL,
    functional_role_description VARCHAR(255) NOT NULL,
    functional_role_get_condition VARCHAR(255) NOT NULL,
    PRIMARY KEY (functional_role_id)
)
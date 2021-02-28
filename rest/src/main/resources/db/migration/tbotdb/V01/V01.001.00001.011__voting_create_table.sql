CREATE TABLE voting (
    voting_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    city_id VARCHAR(36) NOT NULL,
    entity_id VARCHAR(36) NOT NULL,
    entity_type VARCHAR(255) NOT NULL,
    role VARCHAR(36) NOT NULL,
    functional_role_id VARCHAR(36) NOT NULL,
    voting_start_date TIMESTAMP NOT NULL,
    voting_end_date TIMESTAMP,
    voting_number_of_accept INT,
    voting_number_of_decline INT,
    voting_number_of_users INT,
    voting_result BOOLEAN,
    PRIMARY KEY (voting_id),
    FOREIGN KEY (city_id) REFERENCES cities(city_id),
    FOREIGN KEY (functional_role_id) REFERENCES functional_roles(functional_role_id)
)
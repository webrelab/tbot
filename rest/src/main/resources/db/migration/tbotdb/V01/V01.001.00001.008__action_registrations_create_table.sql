CREATE TABLE action_registrations(
    action_registration_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    action_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    action_registration_lon DOUBLE NOT NULL,
    action_registration_lat DOUBLE NOT NULL,
    city_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (action_registration_id),
    FOREIGN KEY (action_id) REFERENCES actions(action_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
)
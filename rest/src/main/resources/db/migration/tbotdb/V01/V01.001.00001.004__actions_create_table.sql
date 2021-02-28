CREATE TABLE actions (
    action_id VARCHAR(36) NOT NULL default (uuid()),
    city_id VARCHAR(36) NOT NULL,
    action_from_timestamp TIMESTAMP,
    action_to_timestamp TIMESTAMP,
    PRIMARY KEY (action_id),
    FOREIGN KEY (city_id) REFERENCES cities(city_id)
);
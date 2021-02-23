CREATE TABLE formed_groups (
    formed_group_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    city_id VARCHAR(36) NOT NULL,
    action_id VARCHAR(36) NOT NULL,
    formed_group_start_location_lon DOUBLE NOT NULL,
    formed_group_start_location_lat DOUBLE NOT NULL,
    formed_group_start_address VARCHAR(255) NOT NULL,
    formed_group_destination_location_lon DOUBLE NOT NULL,
    formed_group_destination_location_lat DOUBLE NOT NULL,
    formed_group_destination_address VARCHAR(255) NOT NULL,
    formed_group_people_number INT NOT NULL,
    PRIMARY KEY (formed_group_id),
    FOREIGN KEY (city_id) REFERENCES cities(city_id),
    FOREIGN KEY (action_id) REFERENCES actions(action_id)
)
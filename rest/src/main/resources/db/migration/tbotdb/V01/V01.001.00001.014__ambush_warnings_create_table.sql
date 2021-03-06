CREATE TABLE ambush_warnings (
    ambush_warning_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    formed_group_id VARCHAR(36) NOT NULL,
    ambush_address VARCHAR(255) NOT NULL,
    ambush_location_lat DOUBLE NOT NULL,
    ambush_location_lon DOUBLE NOT NULL,
    PRIMARY KEY (ambush_warning_id),
    FOREIGN KEY (formed_group_id) REFERENCES formed_groups(formed_group_id)
)
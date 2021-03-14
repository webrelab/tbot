CREATE TABLE cities (
    city_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    city_name VARCHAR(255) NOT NULL,
    area VARCHAR(255) NOT NULL,
    federal_district VARCHAR(255) NOT NULL,
    population INT NOT NULL,
    city_lon FLOAT NOT NULL,
    city_lat FLOAT NOT NULL,
    PRIMARY KEY (city_id),
    KEY (city_name)
);
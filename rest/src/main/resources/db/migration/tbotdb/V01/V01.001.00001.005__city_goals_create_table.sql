CREATE TABLE city_goals(
    city_goal_id VARCHAR(36) NOT NULL default (uuid()),
    city_id VARCHAR(36) NOT NULL,
    goal_name VARCHAR(255) NOT NULL,
    goal_description TEXT NOT NULL,
    goal_destination_date DATE,
    PRIMARY KEY (city_goal_id),
    FOREIGN KEY (city_id) REFERENCES cities(city_id)
);
CREATE TABLE action_goals(
    action_goal_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    action_id VARCHAR(36) NOT NULL,
    city_goal_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (action_goal_id),
    FOREIGN KEY (action_id) REFERENCES actions(action_id),
    FOREIGN KEY (city_goal_id) REFERENCES city_goals(city_goal_id)
);
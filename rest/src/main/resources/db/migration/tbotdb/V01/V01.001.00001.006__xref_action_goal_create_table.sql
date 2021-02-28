CREATE TABLE xref_action_goal(
    action_id VARCHAR(36) NOT NULL,
    city_goal_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (action_id) REFERENCES actions(action_id),
    FOREIGN KEY (city_goal_id) REFERENCES city_goals(city_goal_id)
);
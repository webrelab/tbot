CREATE TABLE votes (
    vote_id VARCHAR(36) NOT NULL DEFAULT (uuid()),
    voting_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    vote BOOLEAN NOT NULL,
    vote_lat DOUBLE NOT NULL,
    vote_lon DOUBLE NOT NULL,
    vote_timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY (vote_id),
    FOREIGN KEY (voting_id) REFERENCES voting(voting_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
)

CREATE TABLE feedback(
  feedback_id BIGINT NOT NULL,
  message VARCHAR (200) NOT NULL,
  email VARCHAR (30) NOT NULL,
  message_time TIMESTAMP NOT NULL,
  PRIMARY KEY (feedback_id)
);
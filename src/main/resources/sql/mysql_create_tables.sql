
CREATE TABLE feedback(
  feedback_id BIGINT NOT NULL,
  message VARCHAR (200) NOT NULL,
  email VARCHAR (30) NOT NULL,
  message_time TIMESTAMP NOT NULL,
  PRIMARY KEY (feedback_id)
);

CREATE TABLE transaction(
 transaction_id  BIGINT NOT NULL,
 login_id VARCHAR(20) NOT NULL,
 date_time TIMESTAMP NOT NULL,
 amount DECIMAL(9,2) NOT NULL,
 info VARCHAR(255),
 CONSTRAINT pk_transaction_id
 	PRIMARY KEY (transaction_id),
 CONSTRAINT fk_login_id
 	FOREIGN KEY (login_id)
 	REFERENCES user (login_id)
 	ON DELETE CASCADE
        ON UPDATE CASCADE
);
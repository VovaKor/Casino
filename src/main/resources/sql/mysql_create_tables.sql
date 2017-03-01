CREATE TABLE user (
  login_id        VARCHAR(20)   NOT NULL,
  password        VARCHAR(255)  NOT NULL,
  roles_id        INT           NOT NULL,
  balance         DECIMAL(9, 2) NOT NULL,
  email           VARCHAR(30)   NOT NULL,
  last_login_date TIMESTAMP,
  PRIMARY KEY (login_id)
#   FOREIGN KEY (roles_id) REFERENCES roles(roles_id)
);

CREATE TABLE user_data (
  passport   VARCHAR(20) NOT NULL,
  login_id   VARCHAR(30) NOT NULL,
  name       VARCHAR(15) NOT NULL,
  surname    VARCHAR(15) NOT NULL,
  patronymic VARCHAR(15) NOT NULL,
  gender     BOOLEAN     NOT NULL,
  birth_day  DATETIME    NOT NULL,
  country    VARCHAR(20),
  city       VARCHAR(20),
  address     VARCHAR(40),
  telephone  VARCHAR(25) NOT NULL,
  PRIMARY KEY (passport),
  FOREIGN KEY (login_id) REFERENCES user (login_id)
    ON DELETE CASCADE
);


CREATE TABLE feedback (
  feedback_id  BIGINT       NOT NULL,
  message      VARCHAR(200) NOT NULL,
  email        VARCHAR(30)  NOT NULL,
  message_time TIMESTAMP    NOT NULL,

  PRIMARY KEY (feedback_id)
);

CREATE TABLE transaction (
  transaction_id BIGINT        NOT NULL,
  login_id       VARCHAR(20)   NOT NULL,
  date_time      TIMESTAMP     NOT NULL,
  amount          DECIMAL(9, 2) NOT NULL,
  info           VARCHAR(255),
  CONSTRAINT pk_transaction_id
  PRIMARY KEY (transaction_id),
  CONSTRAINT fk_login_id
  FOREIGN KEY (login_id)
  REFERENCES user (login_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
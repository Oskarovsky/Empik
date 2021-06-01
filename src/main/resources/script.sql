DROP TABLE IF EXISTS request;

CREATE TABLE request (
  login VARCHAR(250) PRIMARY KEY,
  request_count INT DEFAULT NULL,
);
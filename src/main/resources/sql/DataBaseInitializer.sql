create table if not exists users
(
  id          SERIAL       NOT NULL,
  firstName  varchar(250) NOT NULL,
  last_name varchar(250) NOT NULL,
  middleName varchar(250) NOT NULL,
  login    varchar(250) NOT NULL,
  password    varchar(250) NOT NULL,
  email       varchar(250) NOT NULL,
  phone       int          NOT NULL,
  role        varchar(45)  NOT NULL,
    PRIMARY KEY (id, login)
);

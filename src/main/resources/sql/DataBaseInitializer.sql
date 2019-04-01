create table if not exists users
(
  id          SERIAL       NOT NULL,
  first_name  varchar(250) NOT NULL,
  second_name varchar(250) NOT NULL,
  middle_name varchar(250) NOT NULL,
  nickname    varchar(250) NOT NULL,
  password    varchar(250) NOT NULL,
  email       varchar(250) NOT NULL,
  phone       int          NOT NULL,
  role        varchar(45)  NOT NULL
    PRIMARY KEY (id)
);
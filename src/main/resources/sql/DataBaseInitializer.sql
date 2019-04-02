create table if not exists users
(
  id         SERIAL       NOT NULL,
  firstName  varchar(250) NOT NULL,
  last_name  varchar(250) NOT NULL,
  middleName varchar(250) NOT NULL,
  login      varchar(250) NOT NULL,
  password   varchar(250) NOT NULL,
  email      varchar(250) NOT NULL,
  phone      int          NOT NULL,
  role       varchar(45)  NOT NULL,
  PRIMARY KEY (id, email)
);

create table if not exists films
(
  id           SERIAL       NOT NULL,
  name         varchar(250) NOT NULL,
  name_english varchar(250) NOT NULL,
  year         int          NOT NULL,
  description  varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

create table if not exists rooms
(
  id           SERIAL       NOT NULL,
  name         varchar(250) NOT NULL,
  PRIMARY KEY (id)
);





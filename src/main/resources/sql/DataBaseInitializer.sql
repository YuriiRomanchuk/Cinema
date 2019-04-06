create table if not exists users
(
  id         SERIAL       NOT NULL,
  firstName  varchar(250) NOT NULL,
  last_name  varchar(250) NOT NULL,
  middleName varchar(250) NOT NULL,
  login      varchar(250) NOT NULL,
  password   varchar(250) NOT NULL,
  email      varchar(250) NOT NULL UNIQUE,
  phone      int          NOT NULL,
  role       varchar(45)  NOT NULL,
  PRIMARY KEY (id)
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
  id   SERIAL       NOT NULL,
  name varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

create table if not exists places
(
  id      SERIAL NOT NULL,
  row     int    NOT NULL,
  place   int    NOT NULL,
  room_id int    NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT places_room_id_fkey FOREIGN KEY (room_id)
    REFERENCES rooms (id)
);

create table if not exists session
(
  id      SERIAL    NOT NULL,
  film_id int       NOT NULL,
  room_id int       NOT NULL,
  date    timestamp NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT session_film_id_fkey FOREIGN KEY (film_id)
    REFERENCES films (id),
  CONSTRAINT session_room_id_fkey FOREIGN KEY (room_id)
    REFERENCES rooms (id)
);

create table if not exists tickets
(
  id         SERIAL NOT NULL,
  user_id    int    NOT NULL,
  session_id int    NOT NULL,
  place_id   int    NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT tickets_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES films (id),
  CONSTRAINT tickets_session_id_fkey FOREIGN KEY (session_id)
    REFERENCES session (id),
  CONSTRAINT tickets_place_id_fkey FOREIGN KEY (place_id)
    REFERENCES places (id)
);





DROP TABLE TRL_UserRole IF EXISTS;
DROP TABLE TBL_User IF EXISTS;
DROP TABLE TBL_Role IF EXISTS;
DROP TABLE TBL_Office IF EXISTS;
DROP TABLE TBL_Country IF EXISTS;
DROP TABLE TBL_Territory IF EXISTS;

CREATE TABLE TBL_Territory (
  cd_id INTEGER,
  nb_name varchar(50) NOT NULL
);

CREATE TABLE TBL_Country (
  cd_id INTEGER,
  cd_territory INTEGER NOT NULL,
  nb_name varchar(50) NOT NULL
);

CREATE TABLE TBL_Office (
  cd_id INTEGER,
  cd_country INTEGER NOT NULL,
  nb_city varchar(50) NOT NULL,
  nb_phone varchar(50) NOT NULL,
  nb_addressLine1 varchar(50) NOT NULL,
  nb_addressLine2 varchar(50) DEFAULT NULL,
  nb_state varchar(50) DEFAULT NULL,
  nb_postalCode varchar(15) NOT NULL
);

CREATE TABLE TBL_User (
  cd_id INTEGER,
  cd_username varchar(50) NOT NULL,
  cd_email varchar(100) NOT NULL,
  nb_name varchar(50) NOT NULL,
  nb_lastname varchar(50) NOT NULL
);

CREATE TABLE TBL_Role (
  cd_id INTEGER,
  nb_name varchar(50) NOT NULL
);

CREATE TABLE TRL_UserRole (
  cd_user INTEGER NOT NULL,
  cd_role INTEGER NOT NULL
);
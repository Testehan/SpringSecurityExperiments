DROP TABLE IF EXISTS userz;
DROP TABLE IF EXISTS otp;   -- one time passwords table

CREATE TABLE userz (
 username VARCHAR(45) PRIMARY KEY NOT NULL,
 password TEXT NOT NULL
);

CREATE TABLE otp (
 username VARCHAR(45) PRIMARY KEY NOT NULL,
 code VARCHAR(45) NULL
);


DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  enabled INT NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
 id INT AUTO_INCREMENT PRIMARY KEY,
 username VARCHAR(255) NOT NULL,
 authority VARCHAR(255) NOT NULL
);

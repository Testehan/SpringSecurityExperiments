CREATE TABLE IF NOT EXISTS workout (
 id INT AUTO_INCREMENT PRIMARY KEY,
 userr VARCHAR(45) NULL,
 start_time DATETIME NULL,
 end_time DATETIME NULL,
 difficulty INT NULL);
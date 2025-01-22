-- CREATE DATABASE IF NOT EXISTS rooms_exo;
--
-- USE rooms_exo;

CREATE TABLE IF NOT EXISTS rooms (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    is_available BOOLEAN NOT NULL
);
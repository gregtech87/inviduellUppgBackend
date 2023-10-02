
CREATE SCHEMA IF NOT EXISTS memberdb;
USE memberdb;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS address;

CREATE TABLE member(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName   VARCHAR(20) NOT NULL,
    lastName    VARCHAR(30) NOT NULL,
    address     INT NOT NULL,
    email       VARCHAR(45) NOT NULL,
    phone       INT DEFAULT NULL,
    dateOfBirth VARCHAR(20) NOT NULL
);

CREATE TABLE address(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(30) NOT NULL,
    postalCode INT NOT NULL,
    city VARCHAR(20) NOT NULL
);

ALTER TABLE member ADD FOREIGN KEY (address) REFERENCES address (id);

-- SELECT * FROM member RIGHT JOIN address ON member.address = address.id;

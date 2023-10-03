



CREATE TABLE member(
                       id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       first_name   VARCHAR(20) NOT NULL,
                       last_name    VARCHAR(30) NOT NULL,
                       address     INT NOT NULL,
                       email       VARCHAR(45) NOT NULL,
                       phone       INT DEFAULT 1,
                       date_of_birth VARCHAR(20) NOT NULL
);

CREATE TABLE address(
                        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        street VARCHAR(30) NOT NULL,
                        postal_code INT NOT NULL,
                        city VARCHAR(20) NOT NULL
);




INSERT INTO address (street, postal_code, city)
VALUES ('Haspelvägen 3', 87445, 'Växsjö'),
       ('Sjöbotten 1', 46532, 'dddddd');


INSERT INTO member (first_Name, last_name, email, phone, date_of_birth, address)
VALUES ('Tyra', 'Persson', 'Tyra@cat.se)', 015555666, '5/4-2008', 1),
       ('Jerry', 'Persson', 'jerry@cat.se', 66654665, '6/1-1948', 2);





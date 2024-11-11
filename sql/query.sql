USE TestDB;
GO

CREATE TABLE Users (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
);

INSERT INTO Users (id, name, email, password)
VALUES (1, 'yoga', 'yoga@mail.com', 'root');
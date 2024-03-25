CREATE TABLE IF NOT EXISTS Recipe (
    id INT NOT NULL,
    name varchar(250) NOT NULL,
    image varchar(250),
    category VARCHAR(255),
    instructions VARCHAR(255),
    time INT NOT NULL,
    ingredients VARCHAR(255),
    PRIMARY KEY (id)
    );
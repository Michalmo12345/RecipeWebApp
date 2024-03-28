CREATE TABLE IF NOT EXISTS Recipe (
    id INT NOT NULL,
    name varchar(250) NOT NULL,
    image varchar(250),
    category VARCHAR(255),
    instructions VARCHAR(255),
    time INT NOT NULL,
    PRIMARY KEY (id)
    );
CREATE TABLE IF NOT EXISTS Ingredient (
    id INT NOT NULL,
    name varchar(255),
    amount varchar(255),
    recipe_id INT NOT NULL
    );
DROP TABLE IF EXISTS recipe;

CREATE TABLE recipe (
id INT AUTO_INCREMENT,
recipe_name VARCHAR(255) NOT NULL,
ingredients VARCHAR(255) NOT NULL,
diet_friendly BOOLEAN,
PRIMARY KEY (id)
);
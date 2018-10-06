DROP TABLE APP.CLIENT;
CREATE TABLE APP.CLIENT
(
    idClient INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    first_name VARCHAR(45),
    last_name VARCHAR(45),
    city VARCHAR(45),
    street VARCHAR(45),
    home_number INT,
    flat_number INT,
    zip_code INT,
    contact_phone_number INT,
    email VARCHAR(45)
)

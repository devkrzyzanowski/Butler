DROP TABLE APP.CONFIG;
CREATE TABLE APP.CONFIG
(
    idConfig INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    config_name VARCHAR(45) NOT NULL,
    config_value VARCHAR(45) NOT NULL
);

INSERT INTO APP.CONFIG (config_name, config_value) VALUES ('location', 'pl');
INSERT INTO APP.CONFIG (config_name, config_value) VALUES ('show_tips', 'false');
INSERT INTO APP.CONFIG (config_name, config_value) VALUES ('start_of_hotel_day', '11.00');
INSERT INTO APP.CONFIG (config_name, config_value) VALUES ('end_of_hotel_day', '10.30');

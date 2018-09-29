DROP TABLE APP.BOOKING;
CREATE TABLE APP.BOOKING
(
    idBooking INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    begin_of_booking TIMESTAMP,
    end_of_booking TIMESTAMP,
    Client_idClient INT,
    Room_idRoom INT,
    Legend_idLegend INT,
    
    FOREIGN KEY (Client_idClient) REFERENCES APP.CLIENT(idClient),
    FOREIGN KEY (Room_idRoom) REFERENCES APP.ROOM(idRoom),
    FOREIGN KEY (Legend_idLegend) REFERENCES APP.LEGEND(idLegend)
);


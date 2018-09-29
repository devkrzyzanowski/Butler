DROP TABLE APP.BILL;
CREATE TABLE APP.BILL
(
    idBill INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
    installment FLOAT,
    total_price FLOAT,
    current_payment FLOAT,
    percent_discount FLOAT,
    value_discount FLOAT,
    Booking_idBooking INTEGER,
    FOREIGN KEY (Booking_idBooking) REFERENCES APP.BOOKING(idBooking)
)

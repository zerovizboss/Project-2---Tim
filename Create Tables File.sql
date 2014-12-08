CREATE TABLE LOCATION
(
    locationID          int     NOT NULL    AUTO_INCREMENT,
    aisle               int     NOT NULL,
    section             int     NOT NULL,
    PRIMARY KEY(locationID)
);

INSERT INTO LOCATION (aisle,section) VALUES ('0','0');
INSERT INTO LOCATION (aisle,section) VALUES ('1','1');
INSERT INTO LOCATION (aisle,section) VALUES ('1','2');
INSERT INTO LOCATION (aisle,section) VALUES ('2','1');
INSERT INTO LOCATION (aisle,section) VALUES ('2','2');
INSERT INTO LOCATION (aisle,section) VALUES ('3','1');
INSERT INTO LOCATION (aisle,section) VALUES ('3','2');
INSERT INTO LOCATION (aisle,section) VALUES ('4','1');
INSERT INTO LOCATION (aisle,section) VALUES ('4','2');
INSERT INTO LOCATION (aisle,section) VALUES ('5','1');
INSERT INTO LOCATION (aisle,section) VALUES ('5','2');
INSERT INTO LOCATION (aisle,section) VALUES ('6','1');
INSERT INTO LOCATION (aisle,section) VALUES ('6','2');

CREATE TABLE GENRE
(
    genreID     int         NOT NULL    AUTO_INCREMENT,
    genre       varchar(30) NOT NULL,
    PRIMARY KEY(genreID)
);

INSERT INTO GENRE (genre) VALUES ('Action');
INSERT INTO GENRE (genre) VALUES ('Adventure');
INSERT INTO GENRE (genre) VALUES ('Comedy');
INSERT INTO GENRE (genre) VALUES ('Crime');
INSERT INTO GENRE (genre) VALUES ('Cartoon');
INSERT INTO GENRE (genre) VALUES ('Fantasy');
INSERT INTO GENRE (genre) VALUES ('Historical');
INSERT INTO GENRE (genre) VALUES ('Horror');
INSERT INTO GENRE (genre) VALUES ('Sci-Fi');

CREATE TABLE CATALOG
{
    movieID              int         NOT NULL    AUTO_INCREMENT,
    movieTitle      varchar(30) NOT NULL,
    releaseDate     date        NOT NULL,
    rentable        boolean     NOT NULL,
    genreID         int         NOT NULL,
    locationID      int         NOT NULL,
    PRIMARY KEY (movieID),
    FOREIGN KEY (genreID) REFERENCES GENRE(ID),
    FOREIGN KEY (locationID) REFERENCES LOCATION(locationID)
};

INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Anchorman','2014-05-20',TRUE,'3','2');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Psycho','2013-01-04',True,'8','4');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Toy Story','2012-07-08',FALSE,'5','1');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Toy Story 2','2013-09-19',TRUE,'5','8');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Toy Story 3','2014-11-01',TRUE,'5','8');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Star Trek','2013-08-26',TRUE,'9','7');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Star Trek 2','2014-12-10',TRUE,'9','7');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Scareface','1997-02-21',FALSE,'4','1');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Public Enemies','2012-06-14',TRUE,'4','3');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Inside Man','2014-11-28',TRUE,'4','3');
INSERT INTO CATALOG (movieTitle,releaseDate,rentable,genreID,locationID) VALUES ('Avengers','2012-05-04',TRUE,'1','7');

CREATE TABLE INVENTORY
(
    inventoryID     int         NOT NULL    AUTO_INCREMENT,
    movieID         int         NOT NULL,
    copy            int         NOT NULL,
    inStock         boolean     NOT NULL,
    PRIMARY KEY (inventoryID),
    FOREIGN KEY (movieID) REFERENCES CATALOG(movieID)
);

INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('1','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('1','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('2','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('2','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('4','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('5','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('5','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('6','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('6','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('6','3',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('7','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('7','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('7','3',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('9','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('9','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('10','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('10','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('10','3',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('11','1',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('11','2',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('11','3',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('11','4',TRUE);
INSERT INTO INVENTORY (movieID, copy, inStock) VALUES ('11','5',TRUE);

CREATE TABLE RATE
(
    rateID      int         NOT NULL    AUTO_INCREMENT,
    title       varchar(30) NOT NULL,
    fee         number(3,2) NOT NULL,
    PRIMARY KEY (rateID)
);

INSERT INTO RATE (title,fee) VALUES ('New Release','4.50');
INSERT INTO RATE (title,fee) VALUES ('Regular','3.00');
INSERT INTO RATE (title,fee) VALUES ('Cartoon','2.00');

CREATE TABLE CUSTOMER
{
    customerID      int         NOT NULL    AUTO_INCREMENT,
    firstName       varchar(20) NOT NULL,
    lastName        varchar(20) NOT NULL,
    balance         int         NOT NULL,
    totalRentals    int         NOT NULL,
    PRIMARY KEY (customerID)
);

INSERT INTO CUSTOMER (firstName,lastName,balance,totalRentals) VALUES ('Timothy','Folds','0','0');
INSERT INTO CUSTOMER (firstName,lastName,balance,totalRentals) VALUES ('Ash','Ketchum','0','0');
INSERT INTO CUSTOMER (firstName,lastName,balance,totalRentals) VALUES ('Donald','Dedman','0','0');
INSERT INTO CUSTOMER (firstName,lastName,balance,totalRentals) VALUES ('Ashley','Barr','0','0');
INSERT INTO CUSTOMER (firstName,lastName,balance,totalRentals) VALUES ('John','Doe','0','0');
INSERT INTO CUSTOMER (firstName,lastName,balance,totalRentals) VALUES ('Jane','Doe','0','0');

CREATE TABLE INVOICE
(
    invoiceID       int         NOT NULL    AUTO_INCREMENT,
    invoiceDate     date        NOT NULL,
    totalCost       number(4,2) NOT NULL,
    customerID      int         NOT NULL,
    PRIMARY KEY (invoicID),
    FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID)
);

CREATE TABLE LINE
(
    lineID          int     NOT NULL,
    invoiceID       int     NOT NULL,
    expReturnDate   date    NOT NULL,
    rateID          int     NOT NULL,
    dateReturned    date,
    inventoryID     int     NOT NULL,
    PRIMARY KEY (lineID, invoiceID),
    FOREIGN KEY (invoiceID)     REFERENCES INVOICE(invoiceID)
    FOREIGN KEY (rateID)        REFERENCES RATE(rateID)
    FOREIGN KEY (inventoryID)   REFERENCES INVENTORY(inventoryID)
);

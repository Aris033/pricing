drop table if exists PRICES;
drop table if exists BRAND;

CREATE TABLE BRAND (
    BRAND_ID INT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL
);

INSERT INTO BRAND (BRAND_ID, NAME) VALUES
(1, 'ZARA');

CREATE TABLE PRICES (
  BRAND_ID INT NOT NULL,
  START_DATE TIMESTAMP NOT NULL,
  END_DATE TIMESTAMP NOT NULL,
  PRICE_LIST BIGINT NOT NULL,
  PRODUCT_ID INT NOT NULL,
  PRIORITY INT NOT NULL,
  PRICE DECIMAL(10,2) NOT NULL,
  CURR VARCHAR(3) NOT NULL,
  PRIMARY KEY (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY),
  CONSTRAINT FK_PRICES_BRAND
    FOREIGN KEY (BRAND_ID)
    REFERENCES BRAND(BRAND_ID)
);

INSERT INTO PRICES VALUES
    (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
    (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');

-- Precio 35.50 desde el 2023-03-14 00:00:00 hasta el 2023-03-14 12:00:00
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2023-03-14 00:00:00', '2023-03-14 12:00:00', 5, 35455, 0, 35.50, 'EUR');

-- Precio 25.45 desde el 2023-03-14 12:00:00 hasta el 2023-03-15 16:30:00
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2023-03-14 12:00:00', '2023-03-15 16:30:00', 6, 35455, 1, 25.45, 'EUR');

-- Precio 35.50 desde el 2023-03-15 16:30:00 hasta el 2023-03-16 00:00:00
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2023-03-15 16:30:00', '2023-03-16 00:00:00', 7, 35455, 1, 35.50, 'EUR');

-- Precio 30.50 desde el 2023-03-16 00:00:00 hasta el 2023-03-16 11:00:00
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2023-03-16 00:00:00', '2023-03-16 11:00:00', 8, 35455, 1, 30.50, 'EUR');

-- Precio 38.95 desde el 2023-03-16 16:00:00 hasta el 2023-03-31 23:59:59
INSERT INTO PRICES (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2023-03-16 16:00:00', '2023-03-31 23:59:59', 9, 35455, 1, 38.95, 'EUR');
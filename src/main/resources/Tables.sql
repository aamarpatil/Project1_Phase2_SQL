DROP TABLE PRODUCT IF EXISTS;
DROP TABLE SELLER IF EXISTS;
CREATE TABLE SELLER (
    seller_id int primary key,
    sellerName varchar(255) not null
);
CREATE TABLE PRODUCT (
    product_id int primary key,
    productDesc varchar(255) not null,
    price int,
    sold_by int references SELLER(sold_id)
);
INSERT INTO SELLER (seller_id, sellerName)
VALUES
(1, 'John Deere'),
(2, 'Blue Sky'),
(3, 'Pelle'),
(4, 'GE');
INSERT INTO PRODUCT (product_id, productDesc, price, sold_by) VALUES
(1, 'Mower', 2500, 2),
(2, 'Snow Blower', 2700, 1),
(3, 'Window Glasses', 250, 3),
(4, 'Oven', 5000, 4);
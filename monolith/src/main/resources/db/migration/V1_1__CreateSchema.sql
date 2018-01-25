create table INVENTORY (
    itemId varchar(255) not null,
    link varchar(255),
    location varchar(255),
    quantity int4,
    primary key (itemId)
);

create table ORDERS (
    orderId int8 not null,
    customerEmail varchar(255),
    customerName varchar(255),
    discount float8 not null,
    orderValue float8 not null,
    retailPrice float8 not null,
    shippingDiscount float8 not null,
    shippingFee float8 not null,
    primary key (orderId)
);

create table ORDER_ITEMS (
    ID int8 not null,
    productId varchar(255),
    quantity int4 not null,
    ORDER_ID int8,
    primary key (ID)
);

create table PRODUCT_CATALOG (
    itemId varchar(255) not null,
    description text,
    name varchar(80),
    price float8,
    primary key (itemId)
);

alter table ORDER_ITEMS
    add constraint FK2BFF474F65938E8
    foreign key (ORDER_ID)
    references ORDERS;

create sequence hibernate_sequence;
drop table sale_date;
drop table customer;
drop table location;
drop Table Product;
drop table Staff;
drop Table payment_type;
 
create table sale_date (
	sale_date DATE NOT NULL unique,    

    CONSTRAINT PRIMARY KEY (sale_date)
);
 
create table customer (
    customer_id NUMBER UNIQUE NOT null,
    first_name varchar2(100),
    last_name varchar2(100),
    balance number,
    loyalty_balance number,
    address varchar2(100),
    city varchar2(100),
    postcode varchar2(100),
    country varchar2(100),
    
    CONSTRAINT Primary Key (customer_id)
);
 
create Table location (
    counrty varchar2(100),
    state varchar2(100),
    city varchar2(100),
    address varchar2(100),
    postcode varchar2(100),
    outlet_name varchar2(100) UNIQUE NOT null,
    
    CONSTRAINT Primary Key (outlet_name)
);    
 
create Table product (
    product_id NUMBER UNIQUE NOT null,
    name varchar2(100),
    product_type varchar2(100),
    brand varchar2(100),
    unit_cost number,
    unit_price number,
    loyalty_value number,
    
    CONSTRAINT Primary Key (product_id)
);
 
create table staff (
    user_id NUMBER UNIQUE NOT null,
    username Varchar2(100),
    display_name Varchar2(100),
    account_type Varchar2(100),
    
    CONSTRAINT Primary Key (user_id)
); 
 
create table payment_type (
    payment_type Varchar2(100) UNIQUE NOT null,
    
    CONSTRAINT primary key (Payment_type)
);    
 
/* Fact table */
create table Sales (
    sale_id NUMBER UNIQUE NOT null,
    quantity number NOT null,
    price number NOT null,
    discount number,
    
    /* Foreign keys */
    user_id number,
    customer_id number, 
    outlet_name Varchar2(100),
    product_id number, 
    payment_type Varchar2(100),
    sale_date DATE,
    
    CONSTRAINT Foreign Key (user_id) References staff(user_id),
    CONSTRAINT Foreign Key (customer_id) References customer(customer_id),
    CONSTRAINT Foreign Key (outlet_name) References location(outlet_name),
    CONSTRAINT Foreign Key (product_id) References product(product_id),
    CONSTRAINT Foreign Key (payment_type) References payment_type(payment_type),
    CONSTRAINT FOREIGN KEY (sale_date) REFERENCES sale_date(sale_date),
 
    CONSTRAINT Primary Key (sale_id)
);
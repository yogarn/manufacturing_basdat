CREATE DATABASE manufacturing;
GO

USE manufacturing;
GO

CREATE TABLE products
(
    sku VARCHAR(9) PRIMARY KEY NOT NULL,
    product_type VARCHAR(30) NOT NULL,
    price FLOAT NOT NULL
)

CREATE TABLE sales
(
    sku VARCHAR(9) NOT NULL,
    availability INT NOT NULL,
    num_products_sold INT NOT NULL,
    revenue_generated FLOAT NOT NULL,
    FOREIGN KEY(sku) REFERENCES products(sku)
)

CREATE TABLE customers
(
    sku VARCHAR(9) NOT NULL,
    customer_demographics VARCHAR(10) NOT NULL,
    FOREIGN KEY(sku) REFERENCES products(sku)
)

CREATE TABLE shipping
(
    sku VARCHAR(9) NOT NULL,
    shipping_carriers VARCHAR(9) NOT NULL,
    shipping_costs FLOAT NOT NULL,
    shipping_times INT NOT NULL,
    transportation_modes VARCHAR(4) NOT NULL,
    routes VARCHAR(7) NOT NULL,
    FOREIGN KEY(sku) REFERENCES products(sku)
)

CREATE TABLE suppliers
(
    sku VARCHAR(9) NOT NULL,
    supplier_name VARCHAR(10) NOT NULL,
    location VARCHAR(20) NOT NULL,
    lead_time INT NOT NULL,
    production_volumes INT NOT NULL,
    manufacturing_lead_time INT NOT NULL,
    manufacturing_costs FLOAT NOT NULL,
    FOREIGN KEY(sku) REFERENCES products(sku)
)

CREATE TABLE inspections
(
    sku VARCHAR(9) NOT NULL,
    inspection_results VARCHAR(7) NOT NULL,
    defect_rates FLOAT NOT NULL,
    FOREIGN KEY(sku) REFERENCES products(sku)
)

GO

-- DROP DATABASE
-- USE master;
-- alter database manufacturing set single_user with rollback immediate
-- DROP DATABASE manufacturing;
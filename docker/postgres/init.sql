CREATE USER jee WITH ENCRYPTED PASSWORD 'jee';
ALTER USER jee WITH SUPERUSER;

CREATE DATABASE orders;

\connect orders;
CREATE SCHEMA orders;

GRANT CONNECT ON DATABASE orders TO orders;

GRANT ALL PRIVILEGES ON DATABASE orders TO jee;

\connect orders;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA orders TO jee;

\connect orders;
SET search_path TO orders;

CREATE TABLE orders.order
(
  id VARCHAR PRIMARY KEY,
  orderDate TIMESTAMP NOT NULL,
  FOREIGN KEY (persion_id) REFERENCES orders.person (id),
  orderItemIds text[]
);

CREATE TABLE orders.orderItem
(
  id VARCHAR KEY,
  ammount INT NOT NULL,
  product VARCHAR,
  pieceCostInCent INT
);

CREATE TABLE orders.person
(
  id VARCHAR PRIMARY KEY,
  name  VARCHAR NOT NULL
);

CREATE INDEX orders_id
	on orders.order (id);


CREATE INDEX orderItems_id
	on orders.orderItems (id);

CREATE INDEX person_id
	on orders.person (id);
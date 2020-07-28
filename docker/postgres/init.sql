ALTER USER jee WITH SUPERUSER;

\connect orders;
CREATE SCHEMA orders;

GRANT CONNECT ON DATABASE orders TO jee;

GRANT ALL PRIVILEGES ON DATABASE orders TO jee;

\connect orders;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA orders TO jee;

\connect orders;
SET search_path TO orders;

CREATE TABLE orders.person
(
  id VARCHAR PRIMARY KEY,
  name  VARCHAR NOT NULL
);

CREATE TABLE orders.order
(
  id VARCHAR PRIMARY KEY,
  orderDate TIMESTAMP NOT NULL,
  persion_id VARCHAR,
  orderItemIds text[]
);

CREATE TABLE orders.orderItem
(
  id VARCHAR PRIMARY KEY,
  ammount INT NOT NULL,
  product VARCHAR,
  pieceCostInCent INT
);

CREATE UNIQUE INDEX orders_id_index
	on orders.order (id);


CREATE UNIQUE INDEX orderItem_id_index
	on orders.orderItem (id);

CREATE UNIQUE INDEX person_id_index
	on orders.person (id);
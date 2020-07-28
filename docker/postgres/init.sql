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
  primary_key BIGSERIAL PRIMARY KEY,
  id VARCHAR NOT NULL,
  name  VARCHAR NOT NULL
);

CREATE TABLE orders.order
(
  primary_key BIGSERIAL PRIMARY KEY,
  id VARCHAR NOT NULL,
  orderDate TIMESTAMP NOT NULL,
  person BIGINT NOT NULL,
  orderItem BIGINT[] NOT NULL,
  FOREIGN KEY (person) REFERENCES orders.person (primary_key)
); 

CREATE TABLE orders.order_item
(
  primary_key BIGSERIAL PRIMARY KEY,
  id VARCHAR NOT NULL,
  ammount INT NOT NULL,
  product VARCHAR,
  piece_cost_in_cent INT
);

CREATE UNIQUE INDEX orders_id_index
	on orders.order (id);


CREATE UNIQUE INDEX order_item_id_index
	on orders.order_item (id);

CREATE UNIQUE INDEX person_id_index
	on orders.person (id);
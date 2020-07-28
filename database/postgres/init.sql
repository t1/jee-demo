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
  id BIGSERIAL PRIMARY KEY,
  name  VARCHAR NOT NULL
);

CREATE TABLE orders.order
(
  id BIGSERIAL PRIMARY KEY,
  orderDate TIMESTAMP NOT NULL,
  person BIGINT NOT NULL,
  
  FOREIGN KEY (person) REFERENCES orders.person (id)
); 

CREATE TABLE orders.order_item
(
  id BIGSERIAL PRIMARY KEY,
  order_id BIGINT,
  ammount INT NOT NULL,
  product VARCHAR,
  piece_cost_in_cent INT,

  FOREIGN KEY (order_id) REFERENCES orders.order (id)
);

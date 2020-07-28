ALTER USER jee WITH SUPERUSER;

CREATE TABLE person(
  id BIGSERIAL PRIMARY KEY,
  name  VARCHAR NOT NULL
);

CREATE TABLE orders(
  id BIGSERIAL PRIMARY KEY,
  orderDate TIMESTAMP NOT NULL,
  customer_id BIGINT NOT NULL,
  
  FOREIGN KEY (customer_id) REFERENCES person (id)
); 

CREATE TABLE order_item(
  id BIGSERIAL PRIMARY KEY,
  order_id BIGINT,
  count INT NOT NULL,
  product VARCHAR,
  piece_cost_in_cent INT,

  FOREIGN KEY (order_id) REFERENCES orders (id)
);

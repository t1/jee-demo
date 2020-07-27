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

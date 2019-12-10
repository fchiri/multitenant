--------------------------DB Configurazione ----------------------
CREATE DATABASE test
    WITH 
    OWNER = gfy_dev
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
CREATE TABLE if not exists public.DATASOURCECONFIG (
	id bigint PRIMARY KEY,
	driverclassname VARCHAR(255),
	url VARCHAR(255),
	name VARCHAR(255),
	username VARCHAR(255),
	password VARCHAR(255),
	initialize BOOLEAN
);

INSERT INTO DATASOURCECONFIG VALUES (1, 'org.postgresql.Driver', 'jdbc:postgresql://10.10.252.169:5432/test1?currentSchema=test1&ApplicationName=MultiTenant', 'test1', 'gfy_dev1', 'gfy_dev1', true);
INSERT INTO DATASOURCECONFIG VALUES (2, 'org.postgresql.Driver', 'jdbc:postgresql://10.10.252.169:5432/test2?currentSchema=test2&ApplicationName=MultiTenant', 'test2', 'gfy_dev2', 'gfy_dev2', true);


--------------------------DB Tenant 1 ----------------------
CREATE DATABASE test1
    WITH 
    OWNER = gfy_dev1
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

create schema if not exists test1;

create table test1.city(id bigint, name varchar(200));

CREATE SEQUENCE "test1".hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--------------------------DB Tenant 2 ----------------------
CREATE DATABASE test2
    WITH 
    OWNER = gfy_dev2
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

create schema if not exists test2;

create table test2.city(id bigint, name varchar(200));

CREATE SEQUENCE "test2".hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


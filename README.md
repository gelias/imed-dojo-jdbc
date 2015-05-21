# imed-dojo-jdbc

  Download [driver] (http://jdbc.postgresql.org/download.html) JDBC Postgres 9.4

  Create database juris
  			
  			CREATE DATABASE gelias
			  WITH OWNER = gelias
			       ENCODING = 'UTF8'
			       TABLESPACE = pg_default
			       LC_COLLATE = 'C'
			       LC_CTYPE = 'C'
			       CONNECTION LIMIT = -1;

  Create table customer
			
			CREATE TABLE customer
			(
			  codigo bigint NOT NULL,
			  nome character varying NOT NULL,
			  CONSTRAINT pk_customer PRIMARY KEY (codigo)
			)
			WITH (
			  OIDS=FALSE
			);
			ALTER TABLE customer
			  OWNER TO postgres;

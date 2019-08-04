--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4 (Debian 10.4-1.pgdg90+1)
-- Dumped by pg_dump version 10.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
--
--

--
-- Name: customers; Type: SCHEMA; Schema: -; Owner: root
--

CREATE SCHEMA customers;


ALTER SCHEMA customers OWNER TO root;

--
--
--

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

--
--
--

--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';



SET default_tablespace = '';

SET default_with_oids = false;

--
--
--

--
-- Name: customers; Type: TABLE; Schema: customers; Owner: root
--

CREATE TABLE customers.customers (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    age bigint NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE customers.customers OWNER TO root;

--
--
--

--
-- Name: customers_seq; Type: SEQUENCE; Schema: customers; Owner: root
--

CREATE SEQUENCE customers.customers_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers.customers_seq OWNER TO root;

--
--
--

--
-- Name: customers_pkey; Type: CONSTRAINT; Schema: customers; Owner: root
--

ALTER TABLE ONLY customers.customers ADD CONSTRAINT customers_pkey PRIMARY KEY (id);

--
--
--

--
-- Name: customers_idx; Type: INDEX; Schema: customers; Owner: root
--

create index customers_idx on customers.customers (id);


--
--
--


--
-- Name: geolocation; Type: TABLE; Schema: customers; Owner: root
--

CREATE TABLE customers.geolocation (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    ipv4 character varying(20),
    continent_name character varying(55),
    country_name character varying(55),
    subdivision_1_name character varying(55),
    subdivision_2_name character varying(55),
    city_name character varying(55),
    latitude character varying(30),
    longitude character varying(30),
    created_at timestamp without time zone NOT NULL
);

ALTER TABLE customers.geolocation OWNER TO root;


--
--
--


--
-- Name: geolocation_seq; Type: SEQUENCE; Schema: customers; Owner: root
--

CREATE SEQUENCE customers.geolocation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers.geolocation_seq OWNER TO root;

--
--
--


--
-- Name: geolocation_pkey; Type: CONSTRAINT; Schema: customers; Owner: root
--

ALTER TABLE ONLY customers.geolocation ADD CONSTRAINT geolocation_pkey PRIMARY KEY (id);

--
--
--


--
-- Name: geolocation_fkey; Type: CONSTRAINT; Schema: customers; Owner: root
--

ALTER TABLE ONLY customers.geolocation
    ADD CONSTRAINT geolocation_fkey FOREIGN KEY (customer_id) REFERENCES customers.customers(id) ON DELETE CASCADE;

--
--
--


--
-- Name: woeid; Type: TABLE; Schema: customers; Owner: root
--

CREATE TABLE customers.woeid (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    distance bigint,
    title character varying(120),
    location_type character varying(120),
    woeid bigint,
    latt_long character varying(80),
    created_at timestamp without time zone NOT NULL
);

ALTER TABLE customers.woeid OWNER TO root;

--
--
--


--
-- Name: woeid_seq; Type: SEQUENCE; Schema: customers; Owner: root
--

CREATE SEQUENCE customers.woeid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers.woeid_seq OWNER TO root;

--
--
--


--
-- Name: woeid_pkey; Type: CONSTRAINT; Schema: customers; Owner: root
--

ALTER TABLE ONLY customers.woeid ADD CONSTRAINT woeid_pkey PRIMARY KEY (id);

--
--
--


--
-- Name: woeid_fkey; Type: CONSTRAINT; Schema: customers; Owner: root
--

ALTER TABLE ONLY customers.woeid
    ADD CONSTRAINT woeid_fkey FOREIGN KEY (customer_id) REFERENCES customers.customers(id) ON DELETE CASCADE;

--
--
--


--
-- Name: weather; Type: TABLE; Schema: customers; Owner: root
--

CREATE TABLE customers.weather (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    max_temp DECIMAL(7,5),
    min_temp DECIMAL(7,5),
    applicable_date timestamp without time zone,
    created_at timestamp without time zone NOT NULL
);

ALTER TABLE customers.weather OWNER TO root;

--
--
--


--
-- Name: weather_seq; Type: SEQUENCE; Schema: customers; Owner: root
--

CREATE SEQUENCE customers.weather_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers.weather_seq OWNER TO root;

--
--
--


--
-- Name: weather_pkey; Type: CONSTRAINT; Schema: customers; Owner: root
--

ALTER TABLE ONLY customers.weather ADD CONSTRAINT weather_pkey PRIMARY KEY (id);

--
--
--


--
-- Name: weather_fkey; Type: CONSTRAINT; Schema: customers; Owner: root
--

ALTER TABLE ONLY customers.weather
    ADD CONSTRAINT weather_fkey FOREIGN KEY (customer_id) REFERENCES customers.customers(id) ON DELETE CASCADE;

--
--
--


--
-- PostgreSQL database dump complete
--

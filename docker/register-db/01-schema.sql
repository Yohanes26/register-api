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
-- Name: clients; Type: SCHEMA; Schema: -; Owner: root
--

CREATE SCHEMA clients;


ALTER SCHEMA clients OWNER TO root;

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
-- Name: clients; Type: TABLE; Schema: clients; Owner: root
--

CREATE TABLE clients.clients (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    age integer(3) NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE clients.clients OWNER TO root;

--
--
--

--
-- Name: clients_seq; Type: SEQUENCE; Schema: clients; Owner: root
--

CREATE SEQUENCE clients.clients_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients.clients_seq OWNER TO root;

--
--
--

--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: clients; Owner: root
--

ALTER TABLE ONLY clients.clients ADD CONSTRAINT clients_pkey PRIMARY KEY (id);

--
--
--

--
-- Name: clients_idx; Type: INDEX; Schema: clients; Owner: root
--

create index clients_idx on clients.clients (id);


--
--
--


--
-- Name: geolocation; Type: TABLE; Schema: clients; Owner: root
--

CREATE TABLE clients.geolocation (
    id bigint NOT NULL,
    client_id bigint NOT NULL,
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

ALTER TABLE clients.geolocation OWNER TO root;


--
--
--


--
-- Name: geolocation_seq; Type: SEQUENCE; Schema: clients; Owner: root
--

CREATE SEQUENCE clients.geolocation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients.geolocation_seq OWNER TO root;

--
--
--


--
-- Name: geolocation_pkey; Type: CONSTRAINT; Schema: clients; Owner: root
--

ALTER TABLE ONLY clients.geolocation ADD CONSTRAINT geolocation_pkey PRIMARY KEY (id);

--
--
--


--
-- Name: geolocation_fkey; Type: CONSTRAINT; Schema: clients; Owner: root
--

ALTER TABLE ONLY clients.geolocation
    ADD CONSTRAINT geolocation_fkey FOREIGN KEY (client_id) REFERENCES clients.clients(id) ON DELETE CASCADE;

--
--
--


--
-- Name: woeid; Type: TABLE; Schema: clients; Owner: root
--

CREATE TABLE clients.woeid (
    id bigint NOT NULL,
    client_id bigint NOT NULL,
    distance bigint,
    title character varying(120),
    location_type character varying(120),
    woeid bigint,
    latt_long character varying(80),
    created_at timestamp without time zone NOT NULL
);

ALTER TABLE clients.woeid OWNER TO root;

--
--
--


--
-- Name: woeid_seq; Type: SEQUENCE; Schema: clients; Owner: root
--

CREATE SEQUENCE clients.woeid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients.woeid_seq OWNER TO root;

--
--
--


--
-- Name: woeid_pkey; Type: CONSTRAINT; Schema: clients; Owner: root
--

ALTER TABLE ONLY clients.woeid ADD CONSTRAINT woeid_pkey PRIMARY KEY (id);

--
--
--


--
-- Name: woeid_fkey; Type: CONSTRAINT; Schema: clients; Owner: root
--

ALTER TABLE ONLY clients.woeid
    ADD CONSTRAINT woeid_fkey FOREIGN KEY (client_id) REFERENCES clients.clients(id) ON DELETE CASCADE;

--
--
--


--
-- Name: weather; Type: TABLE; Schema: clients; Owner: root
--

CREATE TABLE clients.weather (
    id bigint NOT NULL,
    client_id bigint NOT NULL,
    max_temp DECIMAL(7,5),
    min_temp DECIMAL(7,5),
    woeid bigint,
    latt_long character varying(80),
    applicable_date timestamp without time zone,
    created_at timestamp without time zone NOT NULL
);

ALTER TABLE clients.weather OWNER TO root;

--
--
--


--
-- Name: weather_seq; Type: SEQUENCE; Schema: clients; Owner: root
--

CREATE SEQUENCE clients.weather_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients.weather_seq OWNER TO root;

--
--
--


--
-- Name: weather_pkey; Type: CONSTRAINT; Schema: clients; Owner: root
--

ALTER TABLE ONLY clients.weather ADD CONSTRAINT weather_pkey PRIMARY KEY (id);

--
--
--


--
-- Name: weather_fkey; Type: CONSTRAINT; Schema: clients; Owner: root
--

ALTER TABLE ONLY clients.weather
    ADD CONSTRAINT weather_fkey FOREIGN KEY (client_id) REFERENCES clients.clients(id) ON DELETE CASCADE;

--
--
--


--
-- PostgreSQL database dump complete
--

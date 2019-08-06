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
-- Data for Name: customers; Type: TABLE DATA; Schema: customers; Owner: root
--

INSERT INTO customers.customers (id, name, age, created_at) values (nextval('customers.customer_seq'), 'Joaquim Floriano', 35, '2019-08-02 01:29:00.575000');

--
-- Data for Name: geolocation; Type: TABLE DATA; Schema: customers; Owner: root
--

INSERT INTO customers.geolocation (id, customer_id, ipv4, latitude, longitude, created_at)     values (nextval('customers.geolocation_seq'), 1, '8.8.8.8', '37.38600', '-122.08380', '2019-08-02 01:29:00.575000');

--
-- Data for Name: woeid; Type: TABLE DATA; Schema: customers; Owner: root
--

INSERT INTO customers.woeid (id, customer_id, woeid, created_at) 
    values (nextval('customers.woeid_seq'), 1, 2455920, '2019-08-02 01:29:00.575000');

--
-- Data for Name: weather; Type: TABLE DATA; Schema: customers; Owner: root
--

INSERT INTO customers.weather (id, customer_id, max_temp, min_temp, created_at) 
    values (nextval('customers.weather_seq'), 1, '26.14', '16.055', '2019-08-02 01:29:00.575000');

--
-- PostgreSQL database dump complete
--


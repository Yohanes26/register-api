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
-- Data for Name: clients; Type: TABLE DATA; Schema: clients; Owner: root
--

INSERT INTO clients.clients (id, name, age, created_at) values (nextval('clients.clients_seq'), 'Joaquim Floriano', 35, '2019-08-02 01:29:00.575000');

--
-- Data for Name: geolocation; Type: TABLE DATA; Schema: clients; Owner: root
--

INSERT INTO clients.geolocation (id, client_id, ipv4, continent_name, country_name, subdivision_1_name, subdivision_2_name, city_name, latitude, longitude, created_at)     values (nextval('clients.geolocation_seq'), 1, '8.8.8.8', 'North America', 'United States', 'California', null, 'Mountain View', '37.38600', '-122.08380', '2019-08-02 01:29:00.575000');

--
-- Data for Name: woeid; Type: TABLE DATA; Schema: clients; Owner: root
--

INSERT INTO clients.woeid (id, client_id, distance, title, location_type, woeid, latt_long, created_at) 
    values (nextval('clients.woeid_seq'), 1, 1599, 'Mountain View', 'City', 2455920, '37.39999,-122.079552', '2019-08-02 01:29:00.575000');

--
-- Data for Name: weather; Type: TABLE DATA; Schema: clients; Owner: root
--

INSERT INTO clients.weather (id, client_id, max_temp, min_temp, applicable_date, created_at) 
    values (nextval('clients.weather_seq'), 1, 26.14, 16.055, '2019-08-02', '2019-08-02 01:29:00.575000');

--
-- PostgreSQL database dump complete
--


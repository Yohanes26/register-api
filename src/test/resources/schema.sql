CREATE SCHEMA IF NOT EXISTS customers;

--
-- Name: customers; Type: TABLE; Schema: customers; Owner: root
--

CREATE TABLE IF NOT EXISTS customers.customers (
    id int,
    name varchar(255),
    age int,
    created_at timestamp
);

--
--
--


--
-- Name: customer_seq; Type: SEQUENCE; Schema: customers; Owner: root
--

CREATE SEQUENCE IF NOT EXISTS customers.customer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
--
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2016-08-17 11:46:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE "interacLocal";
--
-- TOC entry 2068 (class 1262 OID 16734)
-- Name: interacLocal; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "interacLocal" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';


ALTER DATABASE "interacLocal" OWNER TO postgres;

\connect "interacLocal"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 178 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 178
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 179 (class 3079 OID 16897)
-- Name: dblink; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dblink WITH SCHEMA public;


--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 179
-- Name: EXTENSION dblink; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dblink IS 'connect to other PostgreSQL databases from within a database';


SET search_path = public, pg_catalog;

--
-- TOC entry 233 (class 1255 OID 24947)
-- Name: insert_analitics(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insert_analitics() RETURNS void
    LANGUAGE plpgsql
    AS $$
  
BEGIN
  INSERT INTO analiticalocal(
            camara_date, slider_inicio, slider_fin, slider_img, 
            genero, edad, expresion, id_modulo)
    SELECT cl.fec_captura,sl.fec_inicio_slide, sl.fec_fin_slide, sl.contenido_name,cl.genero,cl.edad,  cl.expresion, cl.id_modulo  
  FROM camara_log cl,slider_log sl WHERE cl.fec_captura>=sl.fec_inicio_slide and cl.fec_captura<sl.fec_fin_slide and cl.id_modulo=sl.id_modulo and cl.estado='SinProcesar ';
  UPDATE camara_log SET estado='Procesado' WHERE estado='SinProcesar ';
  
END;
$$;


ALTER FUNCTION public.insert_analitics() OWNER TO postgres;

--
-- TOC entry 234 (class 1255 OID 24935)
-- Name: recorrer_cursor_for(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION recorrer_cursor_for() RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
  r record;
  c CURSOR FOR SELECT id_analitica,camara_date,slider_inicio, slider_fin, slider_img, 
            genero, edad, expresion, id_modulo FROM analiticalocal WHERE estado='pendiente';
BEGIN
  FOR r IN c LOOP
  
        PERFORM dblink_exec('host=54.208.243.25 dbname=interac-dev user=postgres password=carter2315 port=5432','INSERT INTO analitica(camara_date, slider_inicio, slider_fin, slider_img, 
            genero, edad, expresion, id_modulo) Values('''||r.camara_date||''','''||r.slider_inicio||''','''||r.slider_fin||''','''||r.slider_img||''','''||r.genero||''','''||r.edad||''','''||r.expresion||''','''||r.id_modulo||''')');

        UPDATE analiticalocal SET estado='Transferido' WHERE id_analitica=r.id_analitica;
  END LOOP;
END;
$$;


ALTER FUNCTION public.recorrer_cursor_for() OWNER TO postgres;

--
-- TOC entry 235 (class 1255 OID 24975)
-- Name: tr_insert_analitica(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION tr_insert_analitica() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
perform recorrer_cursor_for(); 
return new;
end
$$;


ALTER FUNCTION public.tr_insert_analitica() OWNER TO postgres;

--
-- TOC entry 1609 (class 2328 OID 16943)
-- Name: postgresql; Type: FOREIGN DATA WRAPPER; Schema: -; Owner: postgres
--

CREATE FOREIGN DATA WRAPPER postgresql VALIDATOR postgresql_fdw_validator;


ALTER FOREIGN DATA WRAPPER postgresql OWNER TO postgres;

--
-- TOC entry 1610 (class 1417 OID 16944)
-- Name: interac; Type: SERVER; Schema: -; Owner: postgres
--

CREATE SERVER interac FOREIGN DATA WRAPPER postgresql OPTIONS (
    dbname 'interac-dev',
    hostaddr '54.208.243.25',
    port '5432'
);


ALTER SERVER interac OWNER TO postgres;

--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 1610
-- Name: USER MAPPING postgres SERVER interac; Type: USER MAPPING; Schema: -; Owner: postgres
--

CREATE USER MAPPING FOR postgres SERVER interac OPTIONS (
    password 'cater2315',
    "user" 'postgres'
);


--
-- TOC entry 1611 (class 1417 OID 24926)
-- Name: myserver; Type: SERVER; Schema: -; Owner: postgres
--

CREATE SERVER myserver FOREIGN DATA WRAPPER dblink_fdw OPTIONS (
    dbname 'interac-dev',
    hostaddr '54.208.243.25'
);


ALTER SERVER myserver OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 16758)
-- Name: analiticalocal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE analiticalocal (
    id_analitica integer NOT NULL,
    camara_date timestamp without time zone,
    slider_inicio timestamp without time zone,
    slider_fin timestamp without time zone,
    slider_img character varying(80),
    genero character varying(80),
    edad integer,
    expresion character varying(80),
    id_modulo integer,
    estado character varying(256) DEFAULT 'pendiente'::character varying NOT NULL
);


ALTER TABLE analiticalocal OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16756)
-- Name: analitica_id_analitica_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE analitica_id_analitica_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE analitica_id_analitica_seq OWNER TO postgres;

--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 175
-- Name: analitica_id_analitica_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE analitica_id_analitica_seq OWNED BY analiticalocal.id_analitica;


--
-- TOC entry 172 (class 1259 OID 16747)
-- Name: camara_log; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE camara_log (
    fec_captura timestamp without time zone,
    body_count integer,
    face_count integer,
    edad integer,
    conf_edad integer,
    genero character varying(80),
    conf_genero integer,
    expresion character varying(80),
    id_modulo integer,
    estado character varying(256) DEFAULT 'SinProcesar'::character varying
);


ALTER TABLE camara_log OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16752)
-- Name: slider_log; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE slider_log (
    id_slider_log integer NOT NULL,
    contenido_name character varying(80),
    fec_inicio_slide timestamp without time zone,
    fec_fin_slide timestamp without time zone,
    id_modulo integer
);


ALTER TABLE slider_log OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16750)
-- Name: slider_log_id_slider_log_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE slider_log_id_slider_log_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE slider_log_id_slider_log_seq1 OWNER TO postgres;

--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 173
-- Name: slider_log_id_slider_log_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE slider_log_id_slider_log_seq1 OWNED BY slider_log.id_slider_log;


--
-- TOC entry 1947 (class 2604 OID 16761)
-- Name: id_analitica; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY analiticalocal ALTER COLUMN id_analitica SET DEFAULT nextval('analitica_id_analitica_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 16755)
-- Name: id_slider_log; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY slider_log ALTER COLUMN id_slider_log SET DEFAULT nextval('slider_log_id_slider_log_seq1'::regclass);


--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 175
-- Name: analitica_id_analitica_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('analitica_id_analitica_seq', 1, true);



SELECT pg_catalog.setval('slider_log_id_slider_log_seq1', 1, true);


--
-- TOC entry 1949 (class 2620 OID 24976)
-- Name: insert_to_remote; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_to_remote AFTER INSERT ON analiticalocal FOR EACH ROW EXECUTE PROCEDURE tr_insert_analitica();


REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-08-17 11:46:55

--
-- PostgreSQL database dump complete
--


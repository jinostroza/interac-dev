--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-11-16 16:09:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 194 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 194
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 193 (class 3079 OID 26475)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2139 (class 0 OID 0)
-- Dependencies: 193
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 26484)
-- Name: campana; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE campana (
    idcampana integer NOT NULL,
    fechacreacion date,
    fechafin date,
    fechainicio date,
    pasadas integer,
    idcontenido integer,
    nombrecampana character varying(50),
    estado character varying(100) DEFAULT 'Esperando Aprobacion'::character varying NOT NULL,
    idestablecimiento integer
);


ALTER TABLE campana OWNER TO postgres;

--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN campana.estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN campana.estado IS 'la wea del estado de las weas de campanas';


--
-- TOC entry 173 (class 1259 OID 26487)
-- Name: campana_idcampana_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE campana_idcampana_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE campana_idcampana_seq OWNER TO postgres;

--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 173
-- Name: campana_idcampana_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE campana_idcampana_seq OWNED BY campana.idcampana;


--
-- TOC entry 174 (class 1259 OID 26489)
-- Name: campatotem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE campatotem (
    idcampana integer,
    idtotem integer
);


ALTER TABLE campatotem OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 26492)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categoria (
    idcategoria integer NOT NULL,
    desccategoria character varying(45)
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 26495)
-- Name: categoria_idcategoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categoria_idcategoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categoria_idcategoria_seq OWNER TO postgres;

--
-- TOC entry 2142 (class 0 OID 0)
-- Dependencies: 176
-- Name: categoria_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_idcategoria_seq OWNED BY categoria.idcategoria;


--
-- TOC entry 177 (class 1259 OID 26497)
-- Name: contenido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contenido (
    idcontenido integer NOT NULL,
    path character varying(1000) NOT NULL,
    idusuario integer NOT NULL,
    nombrecont character varying(50),
    idcategoria integer,
    estado character varying(50) DEFAULT 'Validando'::character varying NOT NULL
);


ALTER TABLE contenido OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 26504)
-- Name: contenido_idcontenido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contenido_idcontenido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contenido_idcontenido_seq OWNER TO postgres;

--
-- TOC entry 2143 (class 0 OID 0)
-- Dependencies: 178
-- Name: contenido_idcontenido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contenido_idcontenido_seq OWNED BY contenido.idcontenido;


--
-- TOC entry 179 (class 1259 OID 26506)
-- Name: establecimiento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE establecimiento (
    idestablecimiento integer NOT NULL,
    desestablecimiento character varying(200) NOT NULL,
    fono character varying(12),
    direccion character varying(50),
    idusuario integer NOT NULL,
    idubicacion integer,
    latitud double precision,
    longitud double precision,
    valormensual integer,
    horatermino time without time zone,
    horainicio time without time zone,
    slots integer,
    numeropantallas integer,
    fk_rubro integer,
    urlimagen character varying
);


ALTER TABLE establecimiento OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 26512)
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE establecimiento_idestablecimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE establecimiento_idestablecimiento_seq OWNER TO postgres;

--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 180
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE establecimiento_idestablecimiento_seq OWNED BY establecimiento.idestablecimiento;


--
-- TOC entry 181 (class 1259 OID 26514)
-- Name: marcapantalla; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE marcapantalla (
    idmarca integer NOT NULL,
    nombre character varying NOT NULL
);


ALTER TABLE marcapantalla OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 26520)
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE marcapantallas_idmarca_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE marcapantallas_idmarca_seq OWNER TO postgres;

--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 182
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE marcapantallas_idmarca_seq OWNED BY marcapantalla.idmarca;


--
-- TOC entry 183 (class 1259 OID 26522)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rol (
    id_rol integer NOT NULL,
    namerol character varying(25) NOT NULL
);


ALTER TABLE rol OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 26525)
-- Name: rol_idrol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE rol_idrol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rol_idrol_seq OWNER TO postgres;

--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 184
-- Name: rol_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE rol_idrol_seq OWNED BY rol.id_rol;


--
-- TOC entry 185 (class 1259 OID 26527)
-- Name: tipototem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipototem (
    idtipo integer NOT NULL,
    destipo character varying(50)
);


ALTER TABLE tipototem OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 26530)
-- Name: tipototem_idtipo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipototem_idtipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipototem_idtipo_seq OWNER TO postgres;

--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 186
-- Name: tipototem_idtipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipototem_idtipo_seq OWNED BY tipototem.idtipo;


--
-- TOC entry 187 (class 1259 OID 26532)
-- Name: totem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE totem (
    idtotem integer NOT NULL,
    noserie character varying(45),
    idtipo integer NOT NULL,
    idestablecimiento integer NOT NULL,
    latitud double precision,
    longitud double precision,
    pulgadas character varying,
    orientacion character varying,
    modelo character varying,
    marca integer
);


ALTER TABLE totem OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 26538)
-- Name: totem_idtotem_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE totem_idtotem_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE totem_idtotem_seq OWNER TO postgres;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 188
-- Name: totem_idtotem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE totem_idtotem_seq OWNED BY totem.idtotem;


--
-- TOC entry 189 (class 1259 OID 26540)
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ubicacion (
    idubicacion integer NOT NULL,
    descubicacion character varying(45)
);


ALTER TABLE ubicacion OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 26543)
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ubicacion_idubicacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ubicacion_idubicacion_seq OWNER TO postgres;

--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 190
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ubicacion_idubicacion_seq OWNED BY ubicacion.idubicacion;


--
-- TOC entry 191 (class 1259 OID 26545)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    idusuario integer NOT NULL,
    username character varying(45),
    password character varying(45),
    correo character varying(45),
    empresa character varying(45),
    idrol integer DEFAULT 4
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 26549)
-- Name: usuario_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_idusuario_seq OWNER TO postgres;

--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 192
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 1944 (class 2604 OID 26551)
-- Name: idcampana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana ALTER COLUMN idcampana SET DEFAULT nextval('campana_idcampana_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 26552)
-- Name: idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN idcategoria SET DEFAULT nextval('categoria_idcategoria_seq'::regclass);


--
-- TOC entry 1948 (class 2604 OID 26553)
-- Name: idcontenido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contenido ALTER COLUMN idcontenido SET DEFAULT nextval('contenido_idcontenido_seq'::regclass);


--
-- TOC entry 1949 (class 2604 OID 26554)
-- Name: idestablecimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento ALTER COLUMN idestablecimiento SET DEFAULT nextval('establecimiento_idestablecimiento_seq'::regclass);


--
-- TOC entry 1950 (class 2604 OID 26555)
-- Name: idmarca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marcapantalla ALTER COLUMN idmarca SET DEFAULT nextval('marcapantallas_idmarca_seq'::regclass);


--
-- TOC entry 1951 (class 2604 OID 26556)
-- Name: id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rol ALTER COLUMN id_rol SET DEFAULT nextval('rol_idrol_seq'::regclass);


--
-- TOC entry 1952 (class 2604 OID 26557)
-- Name: idtipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipototem ALTER COLUMN idtipo SET DEFAULT nextval('tipototem_idtipo_seq'::regclass);


--
-- TOC entry 1953 (class 2604 OID 26558)
-- Name: idtotem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem ALTER COLUMN idtotem SET DEFAULT nextval('totem_idtotem_seq'::regclass);


--
-- TOC entry 1954 (class 2604 OID 26559)
-- Name: idubicacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ubicacion ALTER COLUMN idubicacion SET DEFAULT nextval('ubicacion_idubicacion_seq'::regclass);


--
-- TOC entry 1956 (class 2604 OID 26560)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 2110 (class 0 OID 26484)
-- Dependencies: 172
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campana VALUES (83, '2015-11-13', '2015-11-30', '2015-11-14', NULL, 335, NULL, 'Esperando Validacion', 43);
INSERT INTO campana VALUES (84, '2015-11-16', '2015-11-30', '2015-11-17', NULL, 338, NULL, 'Esperando Validacion', 60);
INSERT INTO campana VALUES (85, '2015-11-16', '2015-11-30', '2015-11-18', NULL, 339, NULL, 'Esperando Validacion', NULL);
INSERT INTO campana VALUES (86, '2015-11-16', '2015-11-30', '2015-11-19', NULL, 340, NULL, 'Esperando Validacion', NULL);
INSERT INTO campana VALUES (88, '2015-11-16', '2015-11-19', '2015-11-19', NULL, 310, NULL, 'Esperando Validacion', NULL);
INSERT INTO campana VALUES (89, '2015-11-16', '2015-11-27', '2015-11-17', NULL, 310, NULL, 'Esperando Validacion', 43);
INSERT INTO campana VALUES (90, '2015-11-16', '2015-11-20', '2015-11-19', NULL, 311, NULL, 'Esperando Validacion', NULL);
INSERT INTO campana VALUES (91, '2015-11-16', '2015-11-26', '2015-11-20', NULL, 310, NULL, 'Esperando Validacion', 63);
INSERT INTO campana VALUES (92, '2015-11-16', '2015-11-25', '2015-11-20', NULL, 310, NULL, 'Esperando Validacion', 1);
INSERT INTO campana VALUES (93, '2015-11-16', '2015-11-24', '2015-11-20', NULL, 312, NULL, 'Esperando Validacion', 63);
INSERT INTO campana VALUES (94, '2015-11-16', '2015-11-24', '2015-11-20', NULL, 312, NULL, 'Esperando Validacion', 63);
INSERT INTO campana VALUES (95, '2015-11-16', '2015-11-29', '2015-11-26', NULL, 310, NULL, 'Esperando Validacion', 47);


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 173
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 95, true);


--
-- TOC entry 2112 (class 0 OID 26489)
-- Dependencies: 174
-- Data for Name: campatotem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2113 (class 0 OID 26492)
-- Dependencies: 175
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO categoria VALUES (8, 'MULTI TIENDAS');
INSERT INTO categoria VALUES (9, 'ABARROTES');
INSERT INTO categoria VALUES (1, 'POLITICA');
INSERT INTO categoria VALUES (10, 'RETAIL');
INSERT INTO categoria VALUES (3, 'SUPERMERCADO');
INSERT INTO categoria VALUES (11, 'EDUCACION');
INSERT INTO categoria VALUES (12, 'ESTILO DE VIDA');
INSERT INTO categoria VALUES (13, 'PUBLICIDAD');
INSERT INTO categoria VALUES (7, 'FERRETERIAs');


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 176
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 13, true);


--
-- TOC entry 2115 (class 0 OID 26497)
-- Dependencies: 177
-- Data for Name: contenido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contenido VALUES (309, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/1.jpg', 30, 'Test3', 7, 'Validando');
INSERT INTO contenido VALUES (310, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/2.jpg', 2, 'Test3', 9, 'Validando');
INSERT INTO contenido VALUES (311, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/1.jpg', 2, 'Test3', 10, 'Validando');
INSERT INTO contenido VALUES (312, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/afiche-interac-01.png', 2, 'maker', 11, 'Validando');
INSERT INTO contenido VALUES (313, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/2030 fim (1).jpg', 2, '2030', 9, 'Validando');
INSERT INTO contenido VALUES (314, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/downloaded_optimus (1).jpg', 30, '2030', 11, 'Validando');
INSERT INTO contenido VALUES (315, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/info_parte_3.jpg', 30, '2030', 7, 'Validando');
INSERT INTO contenido VALUES (316, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/2030 fim.jpg', 2, '22', 10, 'Validando');
INSERT INTO contenido VALUES (317, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/afiche-interac-02.png', 2, 'maker3', 1, 'Validando');
INSERT INTO contenido VALUES (318, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/Afiche spin offs.jpg', 2, 'spin', 7, 'Validando');
INSERT INTO contenido VALUES (319, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/2030 fim (2).jpg', 2, '2031', 13, 'Validando');
INSERT INTO contenido VALUES (320, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/afiche-interac-03.png', 32, 'maker4', 8, 'Validando');
INSERT INTO contenido VALUES (308, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/1.jpg', 2, 'Test3', 8, 'Esperando Aprobacion');
INSERT INTO contenido VALUES (322, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/afiche-interac-03.png', 2, NULL, NULL, 'Validando');
INSERT INTO contenido VALUES (323, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/2030 fim.jpg', 2, NULL, NULL, 'Validando');
INSERT INTO contenido VALUES (324, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/Imprenta publicidad.jpg', 2, NULL, NULL, 'Validando');
INSERT INTO contenido VALUES (325, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/2030 fim (2).jpg', 2, NULL, NULL, 'Validando');
INSERT INTO contenido VALUES (326, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/DAL_TOTEM.jpg', 2, NULL, NULL, 'Validando');
INSERT INTO contenido VALUES (327, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/2030 fim (1).jpg', 2, 'nuevo', 11, 'Esperando Validacion');
INSERT INTO contenido VALUES (328, '20151112.052101.jpg', 2, 'nuevo', 9, 'Esperando Validacion');
INSERT INTO contenido VALUES (330, '20151112.061513.png', 2, 'nuevo', 1, 'Validando');
INSERT INTO contenido VALUES (331, '20151112.061839.jpg', 2, 'nuevo', 1, 'Validando');
INSERT INTO contenido VALUES (332, '20151112.062023.jpg', 2, 'nuevo', 13, 'Validando');
INSERT INTO contenido VALUES (333, '20151113.110230.jpg', 2, 'nuevo', 11, 'Validando');
INSERT INTO contenido VALUES (334, '20151113.111126.jpg', 2, 'test2', 11, 'Validando');
INSERT INTO contenido VALUES (335, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/Graficas_InteracCharlas.png', 2, 'nuevo', 1, 'Esperando Validacion');
INSERT INTO contenido VALUES (336, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/Afiche spin offs.jpg', 2, 'test2', 13, 'Esperando Validacion');
INSERT INTO contenido VALUES (338, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/41604_39570404312_4595_n.jpg', 2, 'nuevo', 11, 'Esperando Validacion');
INSERT INTO contenido VALUES (339, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/5659126388_3f56c8807f_o.gif', 2, 'sad', 9, 'Esperando Validacion');
INSERT INTO contenido VALUES (340, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/5681914434_23b9b55e71_o.gif', 2, 'Test1', 1, 'Esperando Validacion');
INSERT INTO contenido VALUES (342, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/5530553658_cf0a5dd64d.jpg', 2, 'zx', 3, 'Esperando Validacion');
INSERT INTO contenido VALUES (343, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/5530553658_cf0a5dd64d.jpg', 2, 'sad', 12, 'Esperando Validacion');
INSERT INTO contenido VALUES (344, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/5530553658_cf0a5dd64d.jpg', 2, 'sad', 10, 'Esperando Validacion');


--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 178
-- Name: contenido_idcontenido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contenido_idcontenido_seq', 344, true);


--
-- TOC entry 2117 (class 0 OID 26506)
-- Dependencies: 179
-- Data for Name: establecimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO establecimiento VALUES (43, 'Ripley', '5675675', 'Libertad 3232', 2, 3, -33.008648000000001, -71.548533000000006, NULL, NULL, NULL, NULL, NULL, 10, 'http://www.mallmarinarauco.cl/wp-content/uploads/2015/01/ripley-23.jpg');
INSERT INTO establecimiento VALUES (48, 'Jumbo', '89889898', '1 norte 1022', 2, 3, -33.035592000000001, -71.523809, NULL, NULL, NULL, NULL, NULL, 10, 'https://waltsan.files.wordpress.com/2009/01/438840000_82f49016a8.jpg');
INSERT INTO establecimiento VALUES (63, 'Aiep', '65565', 'La Torre 12', 2, 3, -33.027048000000001, -71.549038999999993, NULL, NULL, NULL, NULL, NULL, 10, 'http://neufert-cdn.archdaily.net.s3.amazonaws.com/uploads/photo/image/17429/large_1379380063-018.jpg');
INSERT INTO establecimiento VALUES (1, 'PUCV Esc.Ingenieria', '989898989', 'avda. Brasil 13232', 2, 2, -33.044668399999999, -71.613844400000005, NULL, NULL, NULL, NULL, NULL, 10, 'http://eii.pucv.cl/wp-content/uploads/2008/04/escuela.jpg');
INSERT INTO establecimiento VALUES (61, 'Nike Store', '678909876', 'avda. Brasil 8989', 2, 1, -33.008713999999998, -71.547582000000006, NULL, NULL, NULL, NULL, NULL, 10, 'http://www.runclub.cl/site/wp-content/uploads/2013/10/307167_10151102841910413_1297851950_n1.jpg');
INSERT INTO establecimiento VALUES (47, 'Paris', '7898678', 'Freire 767', 2, 4, -33.043987999999999, -71.420207000000005, NULL, NULL, NULL, NULL, NULL, 10, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg/220px-Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg');
INSERT INTO establecimiento VALUES (62, 'Hiper Lider', '786543232', 'Covadonga 2323', 2, 4, -33.044666999999997, -71.417856, NULL, NULL, NULL, NULL, NULL, 10, 'http://www.tecsa.cl/experiencia/retail/13.jpg');
INSERT INTO establecimiento VALUES (60, 'Starbuck''s', '23456789', '14 norte 12121', 2, 3, -33.017507000000002, -71.558122999999995, NULL, NULL, NULL, NULL, NULL, 10, 'http://adm.1.cl/galeriasitios/Z33/2013/2/19/Z33__Fl-5203-Starbucks-Ff.jpg');
INSERT INTO establecimiento VALUES (64, 'lapizlopez', '2429820', 'portales 1234', 1, 4, 3333333, 3333333, NULL, '22:00:00', '08:00:00', 12, 2, NULL, NULL);


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 180
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('establecimiento_idestablecimiento_seq', 64, true);


--
-- TOC entry 2119 (class 0 OID 26514)
-- Dependencies: 181
-- Data for Name: marcapantalla; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO marcapantalla VALUES (1, 'Samsung');
INSERT INTO marcapantalla VALUES (2, 'LG');
INSERT INTO marcapantalla VALUES (3, 'AOC');
INSERT INTO marcapantalla VALUES (4, 'Sony');
INSERT INTO marcapantalla VALUES (5, 'Phillips');


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 182
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marcapantallas_idmarca_seq', 6, true);


--
-- TOC entry 2121 (class 0 OID 26522)
-- Dependencies: 183
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rol VALUES (1, 'anunciante');
INSERT INTO rol VALUES (2, 'cliente');
INSERT INTO rol VALUES (3, 'administrador');
INSERT INTO rol VALUES (4, 'invitado');


--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 184
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rol_idrol_seq', 3, true);


--
-- TOC entry 2123 (class 0 OID 26527)
-- Dependencies: 185
-- Data for Name: tipototem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipototem VALUES (1, 'touch');
INSERT INTO tipototem VALUES (2, 'led');


--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 186
-- Name: tipototem_idtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipototem_idtipo_seq', 2, true);


--
-- TOC entry 2125 (class 0 OID 26532)
-- Dependencies: 187
-- Data for Name: totem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO totem VALUES (204, '2323232', 1, 43, 10, 10, '32', 'Horizontal', NULL, 2);
INSERT INTO totem VALUES (203, '54321678', 1, 43, 0, 0, '42', 'Horizontal', NULL, 3);
INSERT INTO totem VALUES (202, '23243545', 1, 47, 1, 1, '42', 'Vertical', NULL, 3);
INSERT INTO totem VALUES (201, 'PUCV2', 1, 1, -33.044668399999999, -71.613844400000005, '40', 'Horizontal', NULL, 1);
INSERT INTO totem VALUES (206, '6565656', 2, 47, -33.043987999999999, -71.420207000000005, '32', 'Vertical', NULL, 5);
INSERT INTO totem VALUES (207, '32323223', 2, 63, -33.027048000000001, -71.549038999999993, '55', 'Horizontal', NULL, 4);
INSERT INTO totem VALUES (205, '34343434', 2, 1, 3333, 3333, '40', 'Vertical', NULL, 2);
INSERT INTO totem VALUES (200, '1234533', 1, 1, -33.044679000000002, -71.613966000000005, '55', 'Vertical', NULL, 4);


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 188
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 207, true);


--
-- TOC entry 2127 (class 0 OID 26540)
-- Dependencies: 189
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ubicacion VALUES (2, 'valparaiso');
INSERT INTO ubicacion VALUES (3, 'viña del mar');
INSERT INTO ubicacion VALUES (4, 'quilpue');
INSERT INTO ubicacion VALUES (5, 'limache');
INSERT INTO ubicacion VALUES (6, 'trovolhue');
INSERT INTO ubicacion VALUES (1, 'santiago');


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 190
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 6, true);


--
-- TOC entry 2129 (class 0 OID 26545)
-- Dependencies: 191
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario VALUES (32, 'jjchacon', '1q2w3e4r', 'joaco@gnm.cl', 'Joaquin', 1);
INSERT INTO usuario VALUES (2, 'jchacon', '1q2w3e4r', 'samsung@interac.cl', 'Samsung', 2);
INSERT INTO usuario VALUES (33, 'qwer', 'qwer', 'qwr@aasdaf.cl', 'SQM', 1);
INSERT INTO usuario VALUES (34, 'uncuncuna', '12345', '123456@1234.cl', 'Corpesca SA', 1);
INSERT INTO usuario VALUES (4, 'edggar', 'edggar', 'papasconmayo@interoski.cl', 'edggar', 1);
INSERT INTO usuario VALUES (15, 'jiu', 'jiu', 'jinostroza@interac.cl', 'Interac', 1);
INSERT INTO usuario VALUES (35, 'demoPucv', 'demo', 'pucv@pucv.cl', 'Escuela de Ingenieria', 1);
INSERT INTO usuario VALUES (36, 'nuevo', '1234', 'nuevo@new.cl', 'nuevo', 4);
INSERT INTO usuario VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 'Claudio Olivares', 3);
INSERT INTO usuario VALUES (5, 'dreamteam', 'dreamteam', 'badassness@dreamteam.org', 'DreamTeam Inc', 3);
INSERT INTO usuario VALUES (13, 'huehuweree_06', '12345', 'wwde', '2134321', 1);
INSERT INTO usuario VALUES (3, 'huehue_06', '12345', 'nomecomopendejas@spiniak.cl', 'SQM', 3);
INSERT INTO usuario VALUES (14, 'pillitopioxd', '12345', 'hueheu@huehue.cl', 'cortana', 1);
INSERT INTO usuario VALUES (16, 'qwer', 'qwer', 'qber@qber.cl', 'hue', 1);
INSERT INTO usuario VALUES (21, 'gato', 'gato', 'yoNoLeFormatieElPcAlGuatonDavalos@Caval.cl', 'Caval', 1);
INSERT INTO usuario VALUES (22, '10dejulio', '123456', 'qwr@qwe.cl', 'Nayaret bisturi S.A.', 1);
INSERT INTO usuario VALUES (23, '12345', '12345', '1234@zxcv.cl', 'qwer', 1);
INSERT INTO usuario VALUES (30, 'admin', '1q2w3e4r', '123456@1234.cl', 'unamuno', 3);
INSERT INTO usuario VALUES (10, 'PPastene', '1234', 'pe.pastene@gmail.com', 'Seguros Patito', 2);


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 192
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 36, true);


--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-11-16 16:09:30

--
-- PostgreSQL database dump complete
--


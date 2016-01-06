--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.4.4
-- Started on 2016-01-06 14:52:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 192 (class 3079 OID 12648)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 192
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 168 (class 1259 OID 36861)
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
    idestablecimiento integer,
    valor integer
);


ALTER TABLE campana OWNER TO postgres;

--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 168
-- Name: COLUMN campana.estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN campana.estado IS 'la wea del estado de las weas de campanas';


--
-- TOC entry 169 (class 1259 OID 36865)
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
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 169
-- Name: campana_idcampana_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE campana_idcampana_seq OWNED BY campana.idcampana;


--
-- TOC entry 170 (class 1259 OID 36867)
-- Name: campatotem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE campatotem (
    idcampana integer,
    idtotem integer
);


ALTER TABLE campatotem OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 36870)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categoria (
    idcategoria integer NOT NULL,
    desccategoria character varying(45)
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 36873)
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
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 172
-- Name: categoria_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_idcategoria_seq OWNED BY categoria.idcategoria;


--
-- TOC entry 173 (class 1259 OID 36875)
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
-- TOC entry 174 (class 1259 OID 36882)
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
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 174
-- Name: contenido_idcontenido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contenido_idcontenido_seq OWNED BY contenido.idcontenido;


--
-- TOC entry 191 (class 1259 OID 37057)
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE empresa (
    nombre character varying,
    razonsocial character varying,
    rut character varying,
    direccion character varying,
    telefono character varying,
    idempresa integer NOT NULL
);


ALTER TABLE empresa OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 37055)
-- Name: empresa_idempresa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE empresa_idempresa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa_idempresa_seq OWNER TO postgres;

--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 190
-- Name: empresa_idempresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE empresa_idempresa_seq OWNED BY empresa.idempresa;


--
-- TOC entry 175 (class 1259 OID 36884)
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
    valor integer,
    horatermino time without time zone,
    horainicio time without time zone,
    slots integer,
    numeropantallas integer,
    fk_rubro integer,
    urlimagen character varying,
    carpetaftp character varying(1000),
    estado character varying,
    empresa integer
);


ALTER TABLE establecimiento OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 36890)
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
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 176
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE establecimiento_idestablecimiento_seq OWNED BY establecimiento.idestablecimiento;


--
-- TOC entry 177 (class 1259 OID 36892)
-- Name: marcapantalla; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE marcapantalla (
    idmarca integer NOT NULL,
    nombre character varying NOT NULL
);


ALTER TABLE marcapantalla OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 36898)
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
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 178
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE marcapantallas_idmarca_seq OWNED BY marcapantalla.idmarca;


--
-- TOC entry 179 (class 1259 OID 36900)
-- Name: meses; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE meses (
    idmes integer NOT NULL,
    mes character varying(50)
);


ALTER TABLE meses OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 36903)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rol (
    id_rol integer NOT NULL,
    namerol character varying(25) NOT NULL
);


ALTER TABLE rol OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 36906)
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
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 181
-- Name: rol_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE rol_idrol_seq OWNED BY rol.id_rol;


--
-- TOC entry 182 (class 1259 OID 36908)
-- Name: tipototem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipototem (
    idtipo integer NOT NULL,
    destipo character varying(50)
);


ALTER TABLE tipototem OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 36911)
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
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 183
-- Name: tipototem_idtipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipototem_idtipo_seq OWNED BY tipototem.idtipo;


--
-- TOC entry 184 (class 1259 OID 36913)
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
    marca integer,
    estado character varying,
    imagen character varying
);


ALTER TABLE totem OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 36919)
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
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 185
-- Name: totem_idtotem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE totem_idtotem_seq OWNED BY totem.idtotem;


--
-- TOC entry 186 (class 1259 OID 36921)
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ubicacion (
    idubicacion integer NOT NULL,
    descubicacion character varying(45)
);


ALTER TABLE ubicacion OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 36924)
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
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 187
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ubicacion_idubicacion_seq OWNED BY ubicacion.idubicacion;


--
-- TOC entry 188 (class 1259 OID 36926)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    idusuario integer NOT NULL,
    username character varying(45),
    password character varying(45),
    correo character varying(250),
    idrol integer DEFAULT 4,
    empresa integer,
    nombre character varying,
    apellido character varying
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 36930)
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
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 189
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 2804 (class 2604 OID 37045)
-- Name: idcampana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana ALTER COLUMN idcampana SET DEFAULT nextval('campana_idcampana_seq'::regclass);


--
-- TOC entry 2805 (class 2604 OID 37046)
-- Name: idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN idcategoria SET DEFAULT nextval('categoria_idcategoria_seq'::regclass);


--
-- TOC entry 2807 (class 2604 OID 37047)
-- Name: idcontenido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contenido ALTER COLUMN idcontenido SET DEFAULT nextval('contenido_idcontenido_seq'::regclass);


--
-- TOC entry 2816 (class 2604 OID 37060)
-- Name: idempresa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa ALTER COLUMN idempresa SET DEFAULT nextval('empresa_idempresa_seq'::regclass);


--
-- TOC entry 2808 (class 2604 OID 37048)
-- Name: idestablecimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento ALTER COLUMN idestablecimiento SET DEFAULT nextval('establecimiento_idestablecimiento_seq'::regclass);


--
-- TOC entry 2809 (class 2604 OID 37049)
-- Name: idmarca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marcapantalla ALTER COLUMN idmarca SET DEFAULT nextval('marcapantallas_idmarca_seq'::regclass);


--
-- TOC entry 2810 (class 2604 OID 37050)
-- Name: id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rol ALTER COLUMN id_rol SET DEFAULT nextval('rol_idrol_seq'::regclass);


--
-- TOC entry 2811 (class 2604 OID 37051)
-- Name: idtipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipototem ALTER COLUMN idtipo SET DEFAULT nextval('tipototem_idtipo_seq'::regclass);


--
-- TOC entry 2812 (class 2604 OID 37052)
-- Name: idtotem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem ALTER COLUMN idtotem SET DEFAULT nextval('totem_idtotem_seq'::regclass);


--
-- TOC entry 2813 (class 2604 OID 37053)
-- Name: idubicacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ubicacion ALTER COLUMN idubicacion SET DEFAULT nextval('ubicacion_idubicacion_seq'::regclass);


--
-- TOC entry 2815 (class 2604 OID 37054)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 2975 (class 0 OID 36861)
-- Dependencies: 168
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (153, '2015-12-18', '2016-01-02', '2015-12-31', 6883993, 377, 'asa', 'Aprobado', 63, 41303958);
INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (154, '2015-12-18', '2015-12-31', '2015-12-25', 330, 372, 'nuevo5', 'Aprobado', 63, 4620);
INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (152, '2015-12-14', '2015-12-31', '2015-12-18', 5040, 368, 'ee', 'Aprobado', 1, NULL);
INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (148, '2015-12-10', '2015-12-31', '2015-12-11', 7560, 364, 'nuevo', 'Aprobado', 47, NULL);
INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (149, '2015-12-10', '2016-01-02', '2015-12-14', 7200, 365, '232', 'Aprobado', 47, NULL);
INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (151, '2015-12-14', '2015-12-31', '2015-12-15', 6120, 367, 'Un nombre', 'Aprobado', 1, NULL);
INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (150, '2015-12-10', '2015-12-31', '2015-12-11', 7560, 366, 'r', 'Aprobado', 47, NULL);
INSERT INTO campana (idcampana, fechacreacion, fechafin, fechainicio, pasadas, idcontenido, nombrecampana, estado, idestablecimiento, valor) VALUES (155, '2015-12-21', '2015-12-31', '2015-12-22', 330, 379, '5', 'Esperando Aprobacion', 63, 0);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 169
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 155, true);


--
-- TOC entry 2977 (class 0 OID 36867)
-- Dependencies: 170
-- Data for Name: campatotem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2978 (class 0 OID 36870)
-- Dependencies: 171
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO categoria (idcategoria, desccategoria) VALUES (8, 'MULTI TIENDAS');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (9, 'ABARROTES');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (1, 'POLITICA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (10, 'RETAIL');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (3, 'SUPERMERCADO');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (11, 'EDUCACION');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (12, 'ESTILO DE VIDA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (13, 'PUBLICIDAD');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (7, 'FERRETERIAs');


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 172
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 13, true);


--
-- TOC entry 2980 (class 0 OID 36875)
-- Dependencies: 173
-- Data for Name: contenido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (363, '20151210.025844.jpg', 2, '1', 11, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (364, '20151210.085129.jpg', 2, 'nuevo', 1, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (368, '20151214.064454.jpg', 30, 'ee', 1, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (367, '20151214.064021.jpg', 10, 'Un nombre', 8, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (369, '20151218.071651.gif', 2, 'uno', 3, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (373, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/696.jpg', 2, 'nuevo5', 11, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (375, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/696.jpg', 2, 're', 1, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (377, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/batman_arkham_origins_joker_games_montreal_rocksteady_studios_101157_1080x1920.jpg', 2, 'asa', 12, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (379, '20151221.073207.jpg', 2, '5', 11, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (361, '20151209.091722.jpg', 2, 'nuevo', 12, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (365, '20151210.090308.jpg', 2, '232', 3, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (366, '20151210.091503.jpg', 2, 'r', 11, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (372, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/696.jpg', 2, 'nuevo5', 9, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (374, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/5681914434_23b9b55e71_o.gif', 2, 'nuevo7', 11, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (376, 'C:\apache-tomcat-7.0.61/static/interac/tmp/contenido/batman_arkham_origins_deathstroke_warner_bros_games_montreal_dc_comics_arkham_origins_video_games_95421_1080x1920.jpg', 2, 'fgfg', 8, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (378, '20151221.031345.gif', 2, 'nuevotest', 11, 'Validando');


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 174
-- Name: contenido_idcontenido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contenido_idcontenido_seq', 379, true);


--
-- TOC entry 2998 (class 0 OID 37057)
-- Dependencies: 191
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO empresa (nombre, razonsocial, rut, direccion, telefono, idempresa) VALUES ('sds', 'sdsd', '3434323', 'sdsasd', '3422', 2);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 190
-- Name: empresa_idempresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_idempresa_seq', 1, true);


--
-- TOC entry 2982 (class 0 OID 36884)
-- Dependencies: 175
-- Data for Name: establecimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (64, 'lapizlopez', '2429820', 'portales 1234', 1, 4, 3333333, 3333333, 2, '22:00:00', '08:00:00', 12, 2, 8, 'http://www.portalcentro.cl/wp-content/uploads/2014/06/IMG_0500-710x375.jpg', 'demoPublicidad', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (1, 'PUCV Esc.Ingenieria', '989898989', 'avda. Brasil 13232', 2, 2, -33.0446683999999991, -71.613844400000005, 3, '20:00:00', '09:00:00', 16, 3, 10, 'http://eii.pucv.cl/wp-content/uploads/2008/04/escuela.jpg', 'colivares', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (48, 'Jumbo', '89889898', '1 norte 1022', 2, 3, -33.0355920000000012, -71.523809, 3, '20:00:00', '09:00:00', 14, 3, 10, 'https://waltsan.files.wordpress.com/2009/01/438840000_82f49016a8.jpg', 'demoPublicidad', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (60, 'Starbuck''s', '23456789', '14 norte 12121', 2, 3, -33.0175070000000019, -71.5581229999999948, 2, '20:00:00', '09:00:00', 14, 3, 10, 'http://adm.1.cl/galeriasitios/Z33/2013/2/19/Z33__Fl-5203-Starbucks-Ff.jpg', 'demo2', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (65, 'Inacap', '676767', 'av.España 1000', 1, 2, -370000, -730000, 2, '20:00:00', '10:00:00', 12, 2, 11, 'http://www.inacap.cl/tportal/portales/tp35f4fead3a341/uploadImg/Image/alumnosextranjeros.jpg', 'demoPublicidad', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (62, 'Hiper Lider', '786543232', 'Covadonga 2323', 2, 4, -33.0446669999999969, -71.4178560000000004, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.tecsa.cl/experiencia/retail/13.jpg', 'demo2', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (63, 'Aiep', '65565', 'La Torre 12', 2, 3, -33.0270480000000006, -71.5490389999999934, 2, '20:00:00', '09:00:00', 12, 2, 10, 'http://neufert-cdn.archdaily.net.s3.amazonaws.com/uploads/photo/image/17429/large_1379380063-018.jpg', 'demo2', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (61, 'Nike Store', '678909876', 'avda. Brasil 8989', 2, 1, -33.0087139999999977, -71.5475820000000056, 2, '20:00:00', '09:00:00', 12, 3, 10, 'http://www.runclub.cl/site/wp-content/uploads/2013/10/307167_10151102841910413_1297851950_n1.jpg', 'demoPublicidad', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (43, 'Ripley', '5675675', 'Libertad 3232', 2, 3, -33.0086480000000009, -71.5485330000000062, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.mallmarinarauco.cl/wp-content/uploads/2015/01/ripley-23.jpg', 'demo2', NULL, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa) VALUES (47, 'Paris', '7898678', 'Freire 767', 2, 4, -33.0439879999999988, -71.4202070000000049, 3, '20:00:00', '09:00:00', 16, 2, 10, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg/220px-Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg', 'demoPublicidad', NULL, 2);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 176
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('establecimiento_idestablecimiento_seq', 65, true);


--
-- TOC entry 2984 (class 0 OID 36892)
-- Dependencies: 177
-- Data for Name: marcapantalla; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO marcapantalla (idmarca, nombre) VALUES (1, 'Samsung');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (2, 'LG');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (3, 'AOC');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (4, 'Sony');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (5, 'Phillips');


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 178
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marcapantallas_idmarca_seq', 6, true);


--
-- TOC entry 2986 (class 0 OID 36900)
-- Dependencies: 179
-- Data for Name: meses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO meses (idmes, mes) VALUES (1, 'Enero');
INSERT INTO meses (idmes, mes) VALUES (2, 'Febrero');
INSERT INTO meses (idmes, mes) VALUES (3, 'Marzo');
INSERT INTO meses (idmes, mes) VALUES (4, 'Abril');
INSERT INTO meses (idmes, mes) VALUES (5, 'Mayo');
INSERT INTO meses (idmes, mes) VALUES (6, 'Junio');
INSERT INTO meses (idmes, mes) VALUES (7, 'Julio');
INSERT INTO meses (idmes, mes) VALUES (8, 'Agosto');
INSERT INTO meses (idmes, mes) VALUES (9, 'Septiembre');
INSERT INTO meses (idmes, mes) VALUES (10, 'Octubre');
INSERT INTO meses (idmes, mes) VALUES (12, 'Diciembre');
INSERT INTO meses (idmes, mes) VALUES (11, 'Noviembre');


--
-- TOC entry 2987 (class 0 OID 36903)
-- Dependencies: 180
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rol (id_rol, namerol) VALUES (1, 'anunciante');
INSERT INTO rol (id_rol, namerol) VALUES (2, 'cliente');
INSERT INTO rol (id_rol, namerol) VALUES (3, 'administrador');
INSERT INTO rol (id_rol, namerol) VALUES (4, 'invitado');
INSERT INTO rol (id_rol, namerol) VALUES (5, 'pucv');


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 181
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rol_idrol_seq', 3, true);


--
-- TOC entry 2989 (class 0 OID 36908)
-- Dependencies: 182
-- Data for Name: tipototem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipototem (idtipo, destipo) VALUES (1, 'touch');
INSERT INTO tipototem (idtipo, destipo) VALUES (2, 'led');


--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 183
-- Name: tipototem_idtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipototem_idtipo_seq', 2, true);


--
-- TOC entry 2991 (class 0 OID 36913)
-- Dependencies: 184
-- Data for Name: totem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (204, '2323232', 1, 43, 10, 10, '32', 'Horizontal', NULL, 2, NULL, NULL);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (203, '54321678', 1, 43, 0, 0, '42', 'Horizontal', NULL, 3, NULL, NULL);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (202, '23243545', 1, 47, 1, 1, '42', 'Vertical', NULL, 3, NULL, NULL);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (201, 'PUCV2', 1, 1, -33.0446683999999991, -71.613844400000005, '40', 'Horizontal', NULL, 1, NULL, NULL);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (206, '6565656', 2, 47, -33.0439879999999988, -71.4202070000000049, '32', 'Vertical', NULL, 5, NULL, NULL);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (207, '32323223', 2, 63, -33.0270480000000006, -71.5490389999999934, '55', 'Horizontal', NULL, 4, NULL, NULL);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (205, '34343434', 2, 1, 3333, 3333, '40', 'Vertical', NULL, 2, NULL, NULL);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento, latitud, longitud, pulgadas, orientacion, modelo, marca, estado, imagen) VALUES (200, '1234533', 1, 1, -33.0446790000000021, -71.6139660000000049, '55', 'Vertical', NULL, 4, NULL, NULL);


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 185
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 207, true);


--
-- TOC entry 2993 (class 0 OID 36921)
-- Dependencies: 186
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (2, 'valparaiso');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (3, 'viña del mar');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (4, 'quilpue');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (5, 'limache');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (6, 'trovolhue');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (1, 'santiago');


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 187
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 6, true);


--
-- TOC entry 2995 (class 0 OID 36926)
-- Dependencies: 188
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (32, 'jjchacon', '1q2w3e4r', 'joaco@gnm.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (33, 'qwer', 'qwer', 'qwr@aasdaf.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (34, 'uncuncuna', '12345', '123456@1234.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (4, 'edggar', 'edggar', 'papasconmayo@interoski.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (36, 'nuevo', '1234', 'nuevo@new.cl', 4, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 3, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (3, 'huehue_06', '12345', 'nomecomopendejas@spiniak.cl', 3, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (16, 'qwer', 'qwer', 'qber@qber.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (21, 'gato', 'gato', 'yoNoLeFormatieElPcAlGuatonDavalos@Caval.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (22, '10dejulio', '123456', 'qwr@qwe.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (23, '12345', '12345', '1234@zxcv.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (35, 'demoPucv', 'demo', 'pucv@pucv.cl', 5, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (13, 'huehuweree_06', '12345', 'jchac', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (2, 'jchacon', '1q2w3e4r', 'jchacon@interac.cl', 2, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (30, 'admin', 'admin', 'pe.pastene@gmail.com', 3, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (10, 'ppablo', '1234', 'pe.pastene22@gmail.com', 2, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (15, 'jiu', 'jiu', 'jinostroza@interac.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (5, 'dreamteam', 'dreamteam', 'badassness@dreamteam.org', 3, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (14, 'pillitopioxd', '12345', 'hueheu@huehue.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (37, '<zxz', '<zx', '<<z<<z', 4, NULL, 'zaszx', 'z<x<zx');
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (38, '1', '1', '1@1.cl', NULL, NULL, '1', '1');


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 189
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 38, true);


--
-- TOC entry 2825 (class 2606 OID 36943)
-- Name: contenido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contenido
    ADD CONSTRAINT contenido_pkey PRIMARY KEY (idcontenido);


--
-- TOC entry 2848 (class 2606 OID 50994)
-- Name: correoid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT correoid UNIQUE (correo);


--
-- TOC entry 2829 (class 2606 OID 36945)
-- Name: establecimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT establecimiento_pkey PRIMARY KEY (idestablecimiento);


--
-- TOC entry 2834 (class 2606 OID 36947)
-- Name: idmes; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY meses
    ADD CONSTRAINT idmes PRIMARY KEY (idmes);


--
-- TOC entry 2832 (class 2606 OID 36949)
-- Name: marcapantallas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY marcapantalla
    ADD CONSTRAINT marcapantallas_pkey PRIMARY KEY (idmarca);


--
-- TOC entry 2820 (class 2606 OID 36951)
-- Name: pk_campana; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY campana
    ADD CONSTRAINT pk_campana PRIMARY KEY (idcampana);


--
-- TOC entry 2823 (class 2606 OID 36953)
-- Name: pk_categoria; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT pk_categoria PRIMARY KEY (idcategoria);


--
-- TOC entry 2852 (class 2606 OID 37065)
-- Name: pk_idempresa; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT pk_idempresa PRIMARY KEY (idempresa);


--
-- TOC entry 2841 (class 2606 OID 36955)
-- Name: pk_totem; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY totem
    ADD CONSTRAINT pk_totem PRIMARY KEY (idtotem);


--
-- TOC entry 2844 (class 2606 OID 36957)
-- Name: pk_ubicacion; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ubicacion
    ADD CONSTRAINT pk_ubicacion PRIMARY KEY (idubicacion);


--
-- TOC entry 2850 (class 2606 OID 36959)
-- Name: pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (idusuario);


--
-- TOC entry 2836 (class 2606 OID 36961)
-- Name: rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id_rol);


--
-- TOC entry 2838 (class 2606 OID 36963)
-- Name: tipototem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipototem
    ADD CONSTRAINT tipototem_pkey PRIMARY KEY (idtipo);


--
-- TOC entry 2827 (class 2606 OID 36965)
-- Name: unique_idcontenido; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contenido
    ADD CONSTRAINT unique_idcontenido UNIQUE (idcontenido);


--
-- TOC entry 2817 (class 1259 OID 36966)
-- Name: campana_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX campana_pk ON campana USING btree (idcampana);


--
-- TOC entry 2821 (class 1259 OID 36967)
-- Name: categoria_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX categoria_pk ON categoria USING btree (idcategoria);


--
-- TOC entry 2846 (class 1259 OID 36968)
-- Name: cliente_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX cliente_pk ON usuario USING btree (idusuario);


--
-- TOC entry 2818 (class 1259 OID 36969)
-- Name: fki_establecimiento; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_establecimiento ON campana USING btree (idestablecimiento);


--
-- TOC entry 2839 (class 1259 OID 36970)
-- Name: fki_marca; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_marca ON totem USING btree (marca);


--
-- TOC entry 2830 (class 1259 OID 36971)
-- Name: fki_rubro; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_rubro ON establecimiento USING btree (fk_rubro);


--
-- TOC entry 2842 (class 1259 OID 36972)
-- Name: totem_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX totem_pk ON totem USING btree (idtotem);


--
-- TOC entry 2845 (class 1259 OID 36973)
-- Name: ubicacion_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX ubicacion_pk ON ubicacion USING btree (idubicacion);


--
-- TOC entry 2853 (class 2606 OID 36974)
-- Name: campana_idcontendo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana
    ADD CONSTRAINT campana_idcontendo_fkey FOREIGN KEY (idcontenido) REFERENCES contenido(idcontenido);


--
-- TOC entry 2855 (class 2606 OID 36979)
-- Name: campatotem_idcampana_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campatotem
    ADD CONSTRAINT campatotem_idcampana_fkey FOREIGN KEY (idcampana) REFERENCES campana(idcampana);


--
-- TOC entry 2856 (class 2606 OID 36984)
-- Name: campatotem_idtotem_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campatotem
    ADD CONSTRAINT campatotem_idtotem_fkey FOREIGN KEY (idtotem) REFERENCES totem(idtotem);


--
-- TOC entry 2857 (class 2606 OID 36989)
-- Name: contenido_idcategoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contenido
    ADD CONSTRAINT contenido_idcategoria_fkey FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria);


--
-- TOC entry 2854 (class 2606 OID 36994)
-- Name: establecimiento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana
    ADD CONSTRAINT establecimiento FOREIGN KEY (idestablecimiento) REFERENCES establecimiento(idestablecimiento);


--
-- TOC entry 2859 (class 2606 OID 36999)
-- Name: establecimiento_idubicacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT establecimiento_idubicacion_fkey FOREIGN KEY (idubicacion) REFERENCES ubicacion(idubicacion);


--
-- TOC entry 2860 (class 2606 OID 37004)
-- Name: establecimiento_idusuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT establecimiento_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 2862 (class 2606 OID 37068)
-- Name: fk_empresa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT fk_empresa FOREIGN KEY (empresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2868 (class 2606 OID 37073)
-- Name: fk_empresa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_empresa FOREIGN KEY (empresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2863 (class 2606 OID 37009)
-- Name: fk_marca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem
    ADD CONSTRAINT fk_marca FOREIGN KEY (marca) REFERENCES marcapantalla(idmarca);


--
-- TOC entry 2861 (class 2606 OID 37014)
-- Name: fk_rubro; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT fk_rubro FOREIGN KEY (fk_rubro) REFERENCES categoria(idcategoria);


--
-- TOC entry 2864 (class 2606 OID 37019)
-- Name: totem_idestablecimiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem
    ADD CONSTRAINT totem_idestablecimiento_fkey FOREIGN KEY (idestablecimiento) REFERENCES establecimiento(idestablecimiento);


--
-- TOC entry 2865 (class 2606 OID 37024)
-- Name: totem_tipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem
    ADD CONSTRAINT totem_tipo_fkey FOREIGN KEY (idtipo) REFERENCES tipototem(idtipo);


--
-- TOC entry 2866 (class 2606 OID 37029)
-- Name: usuario_idrol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_idrol_fkey FOREIGN KEY (idrol) REFERENCES rol(id_rol);


--
-- TOC entry 2867 (class 2606 OID 37034)
-- Name: usuario_idrol_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_idrol_fkey1 FOREIGN KEY (idrol) REFERENCES rol(id_rol);


--
-- TOC entry 2858 (class 2606 OID 37039)
-- Name: usuarios; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contenido
    ADD CONSTRAINT usuarios FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-01-06 14:53:28

--
-- PostgreSQL database dump complete
--


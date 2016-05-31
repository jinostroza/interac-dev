--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-04-14 18:09:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12648)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 193 (class 1259 OID 58724)
-- Name: campaconte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE campaconte (
    idcontenido integer,
    idcampana integer
);


ALTER TABLE campaconte OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 36861)
-- Name: campana; Type: TABLE; Schema: public; Owner: postgres
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
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 169
-- Name: COLUMN campana.estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN campana.estado IS 'la wea del estado de las weas de campanas';


--
-- TOC entry 170 (class 1259 OID 36865)
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
-- TOC entry 3077 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE campana_idcampana_seq OWNED BY campana.idcampana;


--
-- TOC entry 171 (class 1259 OID 36867)
-- Name: campatotem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE campatotem (
    idcampana integer,
    idtotem integer
);


ALTER TABLE campatotem OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 58737)
-- Name: campestab; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE campestab (
    idcampana integer,
    idestablecimiento integer,
    estado character varying DEFAULT 'Esperando Aprobacion'::character varying,
    idcam_est integer NOT NULL
);


ALTER TABLE campestab OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 58754)
-- Name: campestab_idcam-est_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "campestab_idcam-est_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "campestab_idcam-est_seq" OWNER TO postgres;

--
-- TOC entry 3078 (class 0 OID 0)
-- Dependencies: 195
-- Name: campestab_idcam-est_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "campestab_idcam-est_seq" OWNED BY campestab.idcam_est;


--
-- TOC entry 172 (class 1259 OID 36870)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE categoria (
    idcategoria integer NOT NULL,
    desccategoria character varying(45)
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 36873)
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
-- TOC entry 3079 (class 0 OID 0)
-- Dependencies: 173
-- Name: categoria_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_idcategoria_seq OWNED BY categoria.idcategoria;


--
-- TOC entry 196 (class 1259 OID 58780)
-- Name: comunas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE comunas (
    comuna_id integer NOT NULL,
    comuna_nombre character varying(64) NOT NULL,
    provincia_id integer NOT NULL
);


ALTER TABLE comunas OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 58783)
-- Name: comunas_comuna_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE comunas_comuna_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comunas_comuna_id_seq OWNER TO postgres;

--
-- TOC entry 3080 (class 0 OID 0)
-- Dependencies: 197
-- Name: comunas_comuna_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE comunas_comuna_id_seq OWNED BY comunas.comuna_id;


--
-- TOC entry 174 (class 1259 OID 36875)
-- Name: contenido; Type: TABLE; Schema: public; Owner: postgres
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
-- TOC entry 175 (class 1259 OID 36882)
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
-- TOC entry 3081 (class 0 OID 0)
-- Dependencies: 175
-- Name: contenido_idcontenido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contenido_idcontenido_seq OWNED BY contenido.idcontenido;


--
-- TOC entry 192 (class 1259 OID 37057)
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
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
-- TOC entry 191 (class 1259 OID 37055)
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
-- TOC entry 3082 (class 0 OID 0)
-- Dependencies: 191
-- Name: empresa_idempresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE empresa_idempresa_seq OWNED BY empresa.idempresa;


--
-- TOC entry 176 (class 1259 OID 36884)
-- Name: establecimiento; Type: TABLE; Schema: public; Owner: postgres
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
    empresa integer,
    orientacion character varying,
    idprovincia integer,
    idregion integer
);


ALTER TABLE establecimiento OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 36890)
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
-- TOC entry 3083 (class 0 OID 0)
-- Dependencies: 177
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE establecimiento_idestablecimiento_seq OWNED BY establecimiento.idestablecimiento;


--
-- TOC entry 178 (class 1259 OID 36892)
-- Name: marcapantalla; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE marcapantalla (
    idmarca integer NOT NULL,
    nombre character varying NOT NULL
);


ALTER TABLE marcapantalla OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 36898)
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
-- TOC entry 3084 (class 0 OID 0)
-- Dependencies: 179
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE marcapantallas_idmarca_seq OWNED BY marcapantalla.idmarca;


--
-- TOC entry 180 (class 1259 OID 36900)
-- Name: meses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE meses (
    idmes integer NOT NULL,
    mes character varying(50)
);


ALTER TABLE meses OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 58785)
-- Name: provincias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE provincias (
    provincia_id integer NOT NULL,
    provincia_nombre character varying(64) NOT NULL,
    region_id integer NOT NULL
);


ALTER TABLE provincias OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 58788)
-- Name: provincias_provincia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE provincias_provincia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE provincias_provincia_id_seq OWNER TO postgres;

--
-- TOC entry 3085 (class 0 OID 0)
-- Dependencies: 199
-- Name: provincias_provincia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE provincias_provincia_id_seq OWNED BY provincias.provincia_id;


--
-- TOC entry 200 (class 1259 OID 58790)
-- Name: regiones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE regiones (
    region_id integer NOT NULL,
    region_nombre character varying(64) NOT NULL,
    region_ordinal character varying(4) NOT NULL
);


ALTER TABLE regiones OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 58793)
-- Name: regiones_region_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE regiones_region_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE regiones_region_id_seq OWNER TO postgres;

--
-- TOC entry 3086 (class 0 OID 0)
-- Dependencies: 201
-- Name: regiones_region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE regiones_region_id_seq OWNED BY regiones.region_id;


--
-- TOC entry 181 (class 1259 OID 36903)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE rol (
    id_rol integer NOT NULL,
    namerol character varying(25) NOT NULL
);


ALTER TABLE rol OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 36906)
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
-- TOC entry 3087 (class 0 OID 0)
-- Dependencies: 182
-- Name: rol_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE rol_idrol_seq OWNED BY rol.id_rol;


--
-- TOC entry 203 (class 1259 OID 58858)
-- Name: taller; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE taller (
    id_taller integer NOT NULL,
    sede character varying(250),
    fecha date,
    hora character varying(250),
    link character varying(1000),
    nombre character varying(256)
);


ALTER TABLE taller OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 58856)
-- Name: taller_id_taller_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE taller_id_taller_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE taller_id_taller_seq OWNER TO postgres;

--
-- TOC entry 3088 (class 0 OID 0)
-- Dependencies: 202
-- Name: taller_id_taller_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE taller_id_taller_seq OWNED BY taller.id_taller;


--
-- TOC entry 183 (class 1259 OID 36908)
-- Name: tipototem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tipototem (
    idtipo integer NOT NULL,
    destipo character varying(50)
);


ALTER TABLE tipototem OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 36911)
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
-- TOC entry 3089 (class 0 OID 0)
-- Dependencies: 184
-- Name: tipototem_idtipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipototem_idtipo_seq OWNED BY tipototem.idtipo;


--
-- TOC entry 185 (class 1259 OID 36913)
-- Name: totem; Type: TABLE; Schema: public; Owner: postgres
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
-- TOC entry 186 (class 1259 OID 36919)
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
-- TOC entry 3090 (class 0 OID 0)
-- Dependencies: 186
-- Name: totem_idtotem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE totem_idtotem_seq OWNED BY totem.idtotem;


--
-- TOC entry 187 (class 1259 OID 36921)
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ubicacion (
    idubicacion integer NOT NULL,
    descubicacion character varying(45)
);


ALTER TABLE ubicacion OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 36924)
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
-- TOC entry 3091 (class 0 OID 0)
-- Dependencies: 188
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ubicacion_idubicacion_seq OWNED BY ubicacion.idubicacion;


--
-- TOC entry 189 (class 1259 OID 36926)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
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
-- TOC entry 190 (class 1259 OID 36930)
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
-- TOC entry 3092 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 2840 (class 2604 OID 37045)
-- Name: idcampana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana ALTER COLUMN idcampana SET DEFAULT nextval('campana_idcampana_seq'::regclass);


--
-- TOC entry 2853 (class 2604 OID 58756)
-- Name: idcam_est; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campestab ALTER COLUMN idcam_est SET DEFAULT nextval('"campestab_idcam-est_seq"'::regclass);


--
-- TOC entry 2841 (class 2604 OID 37046)
-- Name: idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN idcategoria SET DEFAULT nextval('categoria_idcategoria_seq'::regclass);


--
-- TOC entry 2855 (class 2604 OID 58795)
-- Name: comuna_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comunas ALTER COLUMN comuna_id SET DEFAULT nextval('comunas_comuna_id_seq'::regclass);


--
-- TOC entry 2843 (class 2604 OID 37047)
-- Name: idcontenido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contenido ALTER COLUMN idcontenido SET DEFAULT nextval('contenido_idcontenido_seq'::regclass);


--
-- TOC entry 2852 (class 2604 OID 37060)
-- Name: idempresa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa ALTER COLUMN idempresa SET DEFAULT nextval('empresa_idempresa_seq'::regclass);


--
-- TOC entry 2844 (class 2604 OID 37048)
-- Name: idestablecimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento ALTER COLUMN idestablecimiento SET DEFAULT nextval('establecimiento_idestablecimiento_seq'::regclass);


--
-- TOC entry 2845 (class 2604 OID 37049)
-- Name: idmarca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marcapantalla ALTER COLUMN idmarca SET DEFAULT nextval('marcapantallas_idmarca_seq'::regclass);


--
-- TOC entry 2856 (class 2604 OID 58796)
-- Name: provincia_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY provincias ALTER COLUMN provincia_id SET DEFAULT nextval('provincias_provincia_id_seq'::regclass);


--
-- TOC entry 2857 (class 2604 OID 58797)
-- Name: region_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY regiones ALTER COLUMN region_id SET DEFAULT nextval('regiones_region_id_seq'::regclass);


--
-- TOC entry 2846 (class 2604 OID 37050)
-- Name: id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rol ALTER COLUMN id_rol SET DEFAULT nextval('rol_idrol_seq'::regclass);


--
-- TOC entry 2858 (class 2604 OID 58861)
-- Name: id_taller; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY taller ALTER COLUMN id_taller SET DEFAULT nextval('taller_id_taller_seq'::regclass);


--
-- TOC entry 2847 (class 2604 OID 37051)
-- Name: idtipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipototem ALTER COLUMN idtipo SET DEFAULT nextval('tipototem_idtipo_seq'::regclass);


--
-- TOC entry 2848 (class 2604 OID 37052)
-- Name: idtotem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem ALTER COLUMN idtotem SET DEFAULT nextval('totem_idtotem_seq'::regclass);


--
-- TOC entry 2849 (class 2604 OID 37053)
-- Name: idubicacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ubicacion ALTER COLUMN idubicacion SET DEFAULT nextval('ubicacion_idubicacion_seq'::regclass);


--
-- TOC entry 2851 (class 2604 OID 37054)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 3057 (class 0 OID 58724)
-- Dependencies: 193
-- Data for Name: campaconte; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campaconte VALUES (418, 236);
INSERT INTO campaconte VALUES (417, 236);
INSERT INTO campaconte VALUES (415, 237);
INSERT INTO campaconte VALUES (416, 237);
INSERT INTO campaconte VALUES (415, 238);
INSERT INTO campaconte VALUES (416, 238);


--
-- TOC entry 3033 (class 0 OID 36861)
-- Dependencies: 169
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campana VALUES (236, '2016-04-05', '2016-04-28', '2016-04-06', 300, NULL, 'CampañaEjemplo', 'Esperando Aprobacion', NULL, 0);
INSERT INTO campana VALUES (237, '2016-04-06', '2016-04-17', '2016-04-15', 330, NULL, 'yyy', 'Esperando Aprobacion', NULL, 0);
INSERT INTO campana VALUES (238, '2016-04-06', '2016-04-22', '2016-04-07', 300, NULL, 'CampañaEjemplo', 'Esperando Aprobacion', NULL, 0);


--
-- TOC entry 3093 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 238, true);


--
-- TOC entry 3035 (class 0 OID 36867)
-- Dependencies: 171
-- Data for Name: campatotem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3058 (class 0 OID 58737)
-- Dependencies: 194
-- Data for Name: campestab; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campestab VALUES (236, 60, 'Esperando Aprobacion', 247);
INSERT INTO campestab VALUES (236, 65, 'Aprobado', 248);
INSERT INTO campestab VALUES (237, 64, 'Esperando Aprobacion', 251);
INSERT INTO campestab VALUES (237, 61, 'Esperando Aprobacion', 252);
INSERT INTO campestab VALUES (238, 62, 'Esperando Aprobacion', 255);
INSERT INTO campestab VALUES (238, 65, 'Esperando Aprobacion', 256);


--
-- TOC entry 3094 (class 0 OID 0)
-- Dependencies: 195
-- Name: campestab_idcam-est_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"campestab_idcam-est_seq"', 256, true);


--
-- TOC entry 3036 (class 0 OID 36870)
-- Dependencies: 172
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
-- TOC entry 3095 (class 0 OID 0)
-- Dependencies: 173
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 13, true);


--
-- TOC entry 3060 (class 0 OID 58780)
-- Dependencies: 196
-- Data for Name: comunas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO comunas VALUES (1, 'Arica', 1);
INSERT INTO comunas VALUES (2, 'Camarones', 1);
INSERT INTO comunas VALUES (3, 'General Lagos', 2);
INSERT INTO comunas VALUES (4, 'Putre', 2);
INSERT INTO comunas VALUES (5, 'Alto Hospicio', 3);
INSERT INTO comunas VALUES (6, 'Iquique', 3);
INSERT INTO comunas VALUES (7, 'Camiña', 4);
INSERT INTO comunas VALUES (8, 'Colchane', 4);
INSERT INTO comunas VALUES (9, 'Huara', 4);
INSERT INTO comunas VALUES (10, 'Pica', 4);
INSERT INTO comunas VALUES (11, 'Pozo Almonte', 4);
INSERT INTO comunas VALUES (12, 'Antofagasta', 5);
INSERT INTO comunas VALUES (13, 'Mejillones', 5);
INSERT INTO comunas VALUES (14, 'Sierra Gorda', 5);
INSERT INTO comunas VALUES (15, 'Taltal', 5);
INSERT INTO comunas VALUES (16, 'Calama', 6);
INSERT INTO comunas VALUES (17, 'Ollague', 6);
INSERT INTO comunas VALUES (18, 'San Pedro de Atacama', 6);
INSERT INTO comunas VALUES (19, 'María Elena', 7);
INSERT INTO comunas VALUES (20, 'Tocopilla', 7);
INSERT INTO comunas VALUES (21, 'Chañaral', 8);
INSERT INTO comunas VALUES (22, 'Diego de Almagro', 8);
INSERT INTO comunas VALUES (23, 'Caldera', 9);
INSERT INTO comunas VALUES (24, 'Copiapó', 9);
INSERT INTO comunas VALUES (25, 'Tierra Amarilla', 9);
INSERT INTO comunas VALUES (26, 'Alto del Carmen', 10);
INSERT INTO comunas VALUES (27, 'Freirina', 10);
INSERT INTO comunas VALUES (28, 'Huasco', 10);
INSERT INTO comunas VALUES (29, 'Vallenar', 10);
INSERT INTO comunas VALUES (30, 'Canela', 11);
INSERT INTO comunas VALUES (31, 'Illapel', 11);
INSERT INTO comunas VALUES (32, 'Los Vilos', 11);
INSERT INTO comunas VALUES (33, 'Salamanca', 11);
INSERT INTO comunas VALUES (34, 'Andacollo', 12);
INSERT INTO comunas VALUES (35, 'Coquimbo', 12);
INSERT INTO comunas VALUES (36, 'La Higuera', 12);
INSERT INTO comunas VALUES (37, 'La Serena', 12);
INSERT INTO comunas VALUES (38, 'Paihuaco', 12);
INSERT INTO comunas VALUES (39, 'Vicuña', 12);
INSERT INTO comunas VALUES (40, 'Combarbalá', 13);
INSERT INTO comunas VALUES (41, 'Monte Patria', 13);
INSERT INTO comunas VALUES (42, 'Ovalle', 13);
INSERT INTO comunas VALUES (43, 'Punitaqui', 13);
INSERT INTO comunas VALUES (44, 'Río Hurtado', 13);
INSERT INTO comunas VALUES (45, 'Isla de Pascua', 14);
INSERT INTO comunas VALUES (46, 'Calle Larga', 15);
INSERT INTO comunas VALUES (47, 'Los Andes', 15);
INSERT INTO comunas VALUES (48, 'Rinconada', 15);
INSERT INTO comunas VALUES (49, 'San Esteban', 15);
INSERT INTO comunas VALUES (50, 'La Ligua', 16);
INSERT INTO comunas VALUES (51, 'Papudo', 16);
INSERT INTO comunas VALUES (52, 'Petorca', 16);
INSERT INTO comunas VALUES (53, 'Zapallar', 16);
INSERT INTO comunas VALUES (54, 'Hijuelas', 17);
INSERT INTO comunas VALUES (55, 'La Calera', 17);
INSERT INTO comunas VALUES (56, 'La Cruz', 17);
INSERT INTO comunas VALUES (57, 'Limache', 17);
INSERT INTO comunas VALUES (58, 'Nogales', 17);
INSERT INTO comunas VALUES (59, 'Olmué', 17);
INSERT INTO comunas VALUES (60, 'Quillota', 17);
INSERT INTO comunas VALUES (61, 'Algarrobo', 18);
INSERT INTO comunas VALUES (62, 'Cartagena', 18);
INSERT INTO comunas VALUES (63, 'El Quisco', 18);
INSERT INTO comunas VALUES (64, 'El Tabo', 18);
INSERT INTO comunas VALUES (65, 'San Antonio', 18);
INSERT INTO comunas VALUES (66, 'Santo Domingo', 18);
INSERT INTO comunas VALUES (67, 'Catemu', 19);
INSERT INTO comunas VALUES (68, 'Llaillay', 19);
INSERT INTO comunas VALUES (69, 'Panquehue', 19);
INSERT INTO comunas VALUES (70, 'Putaendo', 19);
INSERT INTO comunas VALUES (71, 'San Felipe', 19);
INSERT INTO comunas VALUES (72, 'Santa María', 19);
INSERT INTO comunas VALUES (73, 'Casablanca', 20);
INSERT INTO comunas VALUES (74, 'Concón', 20);
INSERT INTO comunas VALUES (75, 'Juan Fernández', 20);
INSERT INTO comunas VALUES (76, 'Puchuncaví', 20);
INSERT INTO comunas VALUES (77, 'Quilpué', 20);
INSERT INTO comunas VALUES (78, 'Quintero', 20);
INSERT INTO comunas VALUES (79, 'Valparaíso', 20);
INSERT INTO comunas VALUES (80, 'Villa Alemana', 20);
INSERT INTO comunas VALUES (81, 'Viña del Mar', 20);
INSERT INTO comunas VALUES (82, 'Colina', 21);
INSERT INTO comunas VALUES (83, 'Lampa', 21);
INSERT INTO comunas VALUES (84, 'Tiltil', 21);
INSERT INTO comunas VALUES (85, 'Pirque', 22);
INSERT INTO comunas VALUES (86, 'Puente Alto', 22);
INSERT INTO comunas VALUES (87, 'San José de Maipo', 22);
INSERT INTO comunas VALUES (88, 'Buin', 23);
INSERT INTO comunas VALUES (89, 'Calera de Tango', 23);
INSERT INTO comunas VALUES (90, 'Paine', 23);
INSERT INTO comunas VALUES (91, 'San Bernardo', 23);
INSERT INTO comunas VALUES (92, 'Alhué', 24);
INSERT INTO comunas VALUES (93, 'Curacaví', 24);
INSERT INTO comunas VALUES (94, 'María Pinto', 24);
INSERT INTO comunas VALUES (95, 'Melipilla', 24);
INSERT INTO comunas VALUES (96, 'San Pedro', 24);
INSERT INTO comunas VALUES (97, 'Cerrillos', 25);
INSERT INTO comunas VALUES (98, 'Cerro Navia', 25);
INSERT INTO comunas VALUES (99, 'Conchalí', 25);
INSERT INTO comunas VALUES (100, 'El Bosque', 25);
INSERT INTO comunas VALUES (101, 'Estación Central', 25);
INSERT INTO comunas VALUES (102, 'Huechuraba', 25);
INSERT INTO comunas VALUES (103, 'Independencia', 25);
INSERT INTO comunas VALUES (104, 'La Cisterna', 25);
INSERT INTO comunas VALUES (105, 'La Granja', 25);
INSERT INTO comunas VALUES (106, 'La Florida', 25);
INSERT INTO comunas VALUES (107, 'La Pintana', 25);
INSERT INTO comunas VALUES (108, 'La Reina', 25);
INSERT INTO comunas VALUES (109, 'Las Condes', 25);
INSERT INTO comunas VALUES (110, 'Lo Barnechea', 25);
INSERT INTO comunas VALUES (111, 'Lo Espejo', 25);
INSERT INTO comunas VALUES (112, 'Lo Prado', 25);
INSERT INTO comunas VALUES (113, 'Macul', 25);
INSERT INTO comunas VALUES (114, 'Maipú', 25);
INSERT INTO comunas VALUES (115, 'Ñuñoa', 25);
INSERT INTO comunas VALUES (116, 'Pedro Aguirre Cerda', 25);
INSERT INTO comunas VALUES (117, 'Peñalolén', 25);
INSERT INTO comunas VALUES (118, 'Providencia', 25);
INSERT INTO comunas VALUES (119, 'Pudahuel', 25);
INSERT INTO comunas VALUES (120, 'Quilicura', 25);
INSERT INTO comunas VALUES (121, 'Quinta Normal', 25);
INSERT INTO comunas VALUES (122, 'Recoleta', 25);
INSERT INTO comunas VALUES (123, 'Renca', 25);
INSERT INTO comunas VALUES (124, 'San Miguel', 25);
INSERT INTO comunas VALUES (125, 'San Joaquín', 25);
INSERT INTO comunas VALUES (126, 'San Ramón', 25);
INSERT INTO comunas VALUES (127, 'Santiago', 25);
INSERT INTO comunas VALUES (128, 'Vitacura', 25);
INSERT INTO comunas VALUES (129, 'El Monte', 26);
INSERT INTO comunas VALUES (130, 'Isla de Maipo', 26);
INSERT INTO comunas VALUES (131, 'Padre Hurtado', 26);
INSERT INTO comunas VALUES (132, 'Peñaflor', 26);
INSERT INTO comunas VALUES (133, 'Talagante', 26);
INSERT INTO comunas VALUES (134, 'Codegua', 27);
INSERT INTO comunas VALUES (135, 'Coínco', 27);
INSERT INTO comunas VALUES (136, 'Coltauco', 27);
INSERT INTO comunas VALUES (137, 'Doñihue', 27);
INSERT INTO comunas VALUES (138, 'Graneros', 27);
INSERT INTO comunas VALUES (139, 'Las Cabras', 27);
INSERT INTO comunas VALUES (140, 'Machalí', 27);
INSERT INTO comunas VALUES (141, 'Malloa', 27);
INSERT INTO comunas VALUES (142, 'Mostazal', 27);
INSERT INTO comunas VALUES (143, 'Olivar', 27);
INSERT INTO comunas VALUES (144, 'Peumo', 27);
INSERT INTO comunas VALUES (145, 'Pichidegua', 27);
INSERT INTO comunas VALUES (146, 'Quinta de Tilcoco', 27);
INSERT INTO comunas VALUES (147, 'Rancagua', 27);
INSERT INTO comunas VALUES (148, 'Rengo', 27);
INSERT INTO comunas VALUES (149, 'Requínoa', 27);
INSERT INTO comunas VALUES (150, 'San Vicente de Tagua Tagua', 27);
INSERT INTO comunas VALUES (151, 'La Estrella', 28);
INSERT INTO comunas VALUES (152, 'Litueche', 28);
INSERT INTO comunas VALUES (153, 'Marchihue', 28);
INSERT INTO comunas VALUES (154, 'Navidad', 28);
INSERT INTO comunas VALUES (155, 'Peredones', 28);
INSERT INTO comunas VALUES (156, 'Pichilemu', 28);
INSERT INTO comunas VALUES (157, 'Chépica', 29);
INSERT INTO comunas VALUES (158, 'Chimbarongo', 29);
INSERT INTO comunas VALUES (159, 'Lolol', 29);
INSERT INTO comunas VALUES (160, 'Nancagua', 29);
INSERT INTO comunas VALUES (161, 'Palmilla', 29);
INSERT INTO comunas VALUES (162, 'Peralillo', 29);
INSERT INTO comunas VALUES (163, 'Placilla', 29);
INSERT INTO comunas VALUES (164, 'Pumanque', 29);
INSERT INTO comunas VALUES (165, 'San Fernando', 29);
INSERT INTO comunas VALUES (166, 'Santa Cruz', 29);
INSERT INTO comunas VALUES (167, 'Cauquenes', 30);
INSERT INTO comunas VALUES (168, 'Chanco', 30);
INSERT INTO comunas VALUES (169, 'Pelluhue', 30);
INSERT INTO comunas VALUES (170, 'Curicó', 31);
INSERT INTO comunas VALUES (171, 'Hualañé', 31);
INSERT INTO comunas VALUES (172, 'Licantén', 31);
INSERT INTO comunas VALUES (173, 'Molina', 31);
INSERT INTO comunas VALUES (174, 'Rauco', 31);
INSERT INTO comunas VALUES (175, 'Romeral', 31);
INSERT INTO comunas VALUES (176, 'Sagrada Familia', 31);
INSERT INTO comunas VALUES (177, 'Teno', 31);
INSERT INTO comunas VALUES (178, 'Vichuquén', 31);
INSERT INTO comunas VALUES (179, 'Colbún', 32);
INSERT INTO comunas VALUES (180, 'Linares', 32);
INSERT INTO comunas VALUES (181, 'Longaví', 32);
INSERT INTO comunas VALUES (182, 'Parral', 32);
INSERT INTO comunas VALUES (183, 'Retiro', 32);
INSERT INTO comunas VALUES (184, 'San Javier', 32);
INSERT INTO comunas VALUES (185, 'Villa Alegre', 32);
INSERT INTO comunas VALUES (186, 'Yerbas Buenas', 32);
INSERT INTO comunas VALUES (187, 'Constitución', 33);
INSERT INTO comunas VALUES (188, 'Curepto', 33);
INSERT INTO comunas VALUES (189, 'Empedrado', 33);
INSERT INTO comunas VALUES (190, 'Maule', 33);
INSERT INTO comunas VALUES (191, 'Pelarco', 33);
INSERT INTO comunas VALUES (192, 'Pencahue', 33);
INSERT INTO comunas VALUES (193, 'Río Claro', 33);
INSERT INTO comunas VALUES (194, 'San Clemente', 33);
INSERT INTO comunas VALUES (195, 'San Rafael', 33);
INSERT INTO comunas VALUES (196, 'Talca', 33);
INSERT INTO comunas VALUES (197, 'Arauco', 34);
INSERT INTO comunas VALUES (198, 'Cañete', 34);
INSERT INTO comunas VALUES (199, 'Contulmo', 34);
INSERT INTO comunas VALUES (200, 'Curanilahue', 34);
INSERT INTO comunas VALUES (201, 'Lebu', 34);
INSERT INTO comunas VALUES (202, 'Los Álamos', 34);
INSERT INTO comunas VALUES (203, 'Tirúa', 34);
INSERT INTO comunas VALUES (204, 'Alto Biobío', 35);
INSERT INTO comunas VALUES (205, 'Antuco', 35);
INSERT INTO comunas VALUES (206, 'Cabrero', 35);
INSERT INTO comunas VALUES (207, 'Laja', 35);
INSERT INTO comunas VALUES (208, 'Los Ángeles', 35);
INSERT INTO comunas VALUES (209, 'Mulchén', 35);
INSERT INTO comunas VALUES (210, 'Nacimiento', 35);
INSERT INTO comunas VALUES (211, 'Negrete', 35);
INSERT INTO comunas VALUES (212, 'Quilaco', 35);
INSERT INTO comunas VALUES (213, 'Quilleco', 35);
INSERT INTO comunas VALUES (214, 'San Rosendo', 35);
INSERT INTO comunas VALUES (215, 'Santa Bárbara', 35);
INSERT INTO comunas VALUES (216, 'Tucapel', 35);
INSERT INTO comunas VALUES (217, 'Yumbel', 35);
INSERT INTO comunas VALUES (218, 'Chiguayante', 36);
INSERT INTO comunas VALUES (219, 'Concepción', 36);
INSERT INTO comunas VALUES (220, 'Coronel', 36);
INSERT INTO comunas VALUES (221, 'Florida', 36);
INSERT INTO comunas VALUES (222, 'Hualpén', 36);
INSERT INTO comunas VALUES (223, 'Hualqui', 36);
INSERT INTO comunas VALUES (224, 'Lota', 36);
INSERT INTO comunas VALUES (225, 'Penco', 36);
INSERT INTO comunas VALUES (226, 'San Pedro de La Paz', 36);
INSERT INTO comunas VALUES (227, 'Santa Juana', 36);
INSERT INTO comunas VALUES (228, 'Talcahuano', 36);
INSERT INTO comunas VALUES (229, 'Tomé', 36);
INSERT INTO comunas VALUES (230, 'Bulnes', 37);
INSERT INTO comunas VALUES (231, 'Chillán', 37);
INSERT INTO comunas VALUES (232, 'Chillán Viejo', 37);
INSERT INTO comunas VALUES (233, 'Cobquecura', 37);
INSERT INTO comunas VALUES (234, 'Coelemu', 37);
INSERT INTO comunas VALUES (235, 'Coihueco', 37);
INSERT INTO comunas VALUES (236, 'El Carmen', 37);
INSERT INTO comunas VALUES (237, 'Ninhue', 37);
INSERT INTO comunas VALUES (238, 'Ñiquen', 37);
INSERT INTO comunas VALUES (239, 'Pemuco', 37);
INSERT INTO comunas VALUES (240, 'Pinto', 37);
INSERT INTO comunas VALUES (241, 'Portezuelo', 37);
INSERT INTO comunas VALUES (242, 'Quillón', 37);
INSERT INTO comunas VALUES (243, 'Quirihue', 37);
INSERT INTO comunas VALUES (244, 'Ránquil', 37);
INSERT INTO comunas VALUES (245, 'San Carlos', 37);
INSERT INTO comunas VALUES (246, 'San Fabián', 37);
INSERT INTO comunas VALUES (247, 'San Ignacio', 37);
INSERT INTO comunas VALUES (248, 'San Nicolás', 37);
INSERT INTO comunas VALUES (249, 'Treguaco', 37);
INSERT INTO comunas VALUES (250, 'Yungay', 37);
INSERT INTO comunas VALUES (251, 'Carahue', 38);
INSERT INTO comunas VALUES (252, 'Cholchol', 38);
INSERT INTO comunas VALUES (253, 'Cunco', 38);
INSERT INTO comunas VALUES (254, 'Curarrehue', 38);
INSERT INTO comunas VALUES (255, 'Freire', 38);
INSERT INTO comunas VALUES (256, 'Galvarino', 38);
INSERT INTO comunas VALUES (257, 'Gorbea', 38);
INSERT INTO comunas VALUES (258, 'Lautaro', 38);
INSERT INTO comunas VALUES (259, 'Loncoche', 38);
INSERT INTO comunas VALUES (260, 'Melipeuco', 38);
INSERT INTO comunas VALUES (261, 'Nueva Imperial', 38);
INSERT INTO comunas VALUES (262, 'Padre Las Casas', 38);
INSERT INTO comunas VALUES (263, 'Perquenco', 38);
INSERT INTO comunas VALUES (264, 'Pitrufquén', 38);
INSERT INTO comunas VALUES (265, 'Pucón', 38);
INSERT INTO comunas VALUES (266, 'Saavedra', 38);
INSERT INTO comunas VALUES (267, 'Temuco', 38);
INSERT INTO comunas VALUES (268, 'Teodoro Schmidt', 38);
INSERT INTO comunas VALUES (269, 'Toltén', 38);
INSERT INTO comunas VALUES (270, 'Vilcún', 38);
INSERT INTO comunas VALUES (271, 'Villarrica', 38);
INSERT INTO comunas VALUES (272, 'Angol', 39);
INSERT INTO comunas VALUES (273, 'Collipulli', 39);
INSERT INTO comunas VALUES (274, 'Curacautín', 39);
INSERT INTO comunas VALUES (275, 'Ercilla', 39);
INSERT INTO comunas VALUES (276, 'Lonquimay', 39);
INSERT INTO comunas VALUES (277, 'Los Sauces', 39);
INSERT INTO comunas VALUES (278, 'Lumaco', 39);
INSERT INTO comunas VALUES (279, 'Purén', 39);
INSERT INTO comunas VALUES (280, 'Renaico', 39);
INSERT INTO comunas VALUES (281, 'Traiguén', 39);
INSERT INTO comunas VALUES (282, 'Victoria', 39);
INSERT INTO comunas VALUES (283, 'Corral', 40);
INSERT INTO comunas VALUES (284, 'Lanco', 40);
INSERT INTO comunas VALUES (285, 'Los Lagos', 40);
INSERT INTO comunas VALUES (286, 'Máfil', 40);
INSERT INTO comunas VALUES (287, 'Mariquina', 40);
INSERT INTO comunas VALUES (288, 'Paillaco', 40);
INSERT INTO comunas VALUES (289, 'Panguipulli', 40);
INSERT INTO comunas VALUES (290, 'Valdivia', 40);
INSERT INTO comunas VALUES (291, 'Futrono', 41);
INSERT INTO comunas VALUES (292, 'La Unión', 41);
INSERT INTO comunas VALUES (293, 'Lago Ranco', 41);
INSERT INTO comunas VALUES (294, 'Río Bueno', 41);
INSERT INTO comunas VALUES (295, 'Ancud', 42);
INSERT INTO comunas VALUES (296, 'Castro', 42);
INSERT INTO comunas VALUES (297, 'Chonchi', 42);
INSERT INTO comunas VALUES (298, 'Curaco de Vélez', 42);
INSERT INTO comunas VALUES (299, 'Dalcahue', 42);
INSERT INTO comunas VALUES (300, 'Puqueldón', 42);
INSERT INTO comunas VALUES (301, 'Queilén', 42);
INSERT INTO comunas VALUES (302, 'Quemchi', 42);
INSERT INTO comunas VALUES (303, 'Quellón', 42);
INSERT INTO comunas VALUES (304, 'Quinchao', 42);
INSERT INTO comunas VALUES (305, 'Calbuco', 43);
INSERT INTO comunas VALUES (306, 'Cochamó', 43);
INSERT INTO comunas VALUES (307, 'Fresia', 43);
INSERT INTO comunas VALUES (308, 'Frutillar', 43);
INSERT INTO comunas VALUES (309, 'Llanquihue', 43);
INSERT INTO comunas VALUES (310, 'Los Muermos', 43);
INSERT INTO comunas VALUES (311, 'Maullín', 43);
INSERT INTO comunas VALUES (312, 'Puerto Montt', 43);
INSERT INTO comunas VALUES (313, 'Puerto Varas', 43);
INSERT INTO comunas VALUES (314, 'Osorno', 44);
INSERT INTO comunas VALUES (315, 'Puero Octay', 44);
INSERT INTO comunas VALUES (316, 'Purranque', 44);
INSERT INTO comunas VALUES (317, 'Puyehue', 44);
INSERT INTO comunas VALUES (318, 'Río Negro', 44);
INSERT INTO comunas VALUES (319, 'San Juan de la Costa', 44);
INSERT INTO comunas VALUES (320, 'San Pablo', 44);
INSERT INTO comunas VALUES (321, 'Chaitén', 45);
INSERT INTO comunas VALUES (322, 'Futaleufú', 45);
INSERT INTO comunas VALUES (323, 'Hualaihué', 45);
INSERT INTO comunas VALUES (324, 'Palena', 45);
INSERT INTO comunas VALUES (325, 'Aisén', 46);
INSERT INTO comunas VALUES (326, 'Cisnes', 46);
INSERT INTO comunas VALUES (327, 'Guaitecas', 46);
INSERT INTO comunas VALUES (328, 'Cochrane', 47);
INSERT INTO comunas VALUES (329, 'O`higgins', 47);
INSERT INTO comunas VALUES (330, 'Tortel', 47);
INSERT INTO comunas VALUES (331, 'Coihaique', 48);
INSERT INTO comunas VALUES (332, 'Lago Verde', 48);
INSERT INTO comunas VALUES (333, 'Chile Chico', 49);
INSERT INTO comunas VALUES (334, 'Río Ibáñez', 49);
INSERT INTO comunas VALUES (335, 'Antártica', 50);
INSERT INTO comunas VALUES (336, 'Cabo de Hornos', 50);
INSERT INTO comunas VALUES (337, 'Laguna Blanca', 51);
INSERT INTO comunas VALUES (338, 'Punta Arenas', 51);
INSERT INTO comunas VALUES (339, 'Río Verde', 51);
INSERT INTO comunas VALUES (340, 'San Gregorio', 51);
INSERT INTO comunas VALUES (341, 'Porvenir', 52);
INSERT INTO comunas VALUES (342, 'Primavera', 52);
INSERT INTO comunas VALUES (343, 'Timaukel', 52);
INSERT INTO comunas VALUES (344, 'Natales', 53);
INSERT INTO comunas VALUES (345, 'Torres del Paine', 53);


--
-- TOC entry 3096 (class 0 OID 0)
-- Dependencies: 197
-- Name: comunas_comuna_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('comunas_comuna_id_seq', 1, false);


--
-- TOC entry 3038 (class 0 OID 36875)
-- Dependencies: 174
-- Data for Name: contenido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contenido VALUES (415, '20160315.033450.jpg', 2, 'nuevo1', 11, 'Validando');
INSERT INTO contenido VALUES (416, '20160315.033756.jpg', 2, 'nuevo2', 10, 'Validando');
INSERT INTO contenido VALUES (417, '20160315.083538.png', 2, '22', 9, 'Validando');
INSERT INTO contenido VALUES (418, '20160315.083607.jpg', 2, '2222', 3, 'Validando');
INSERT INTO contenido VALUES (419, '20160322.031857.jpg', 2, 'weq3', 3, 'Validando');
INSERT INTO contenido VALUES (420, '20160322.031922.jpg', 2, 'weq4', 9, 'Validando');
INSERT INTO contenido VALUES (421, '20160323.084931.jpg', 2, 'zsa', 10, 'Validando');
INSERT INTO contenido VALUES (422, '20160329.041502.png', 2, 'dd', 9, 'Validando');
INSERT INTO contenido VALUES (423, '20160331.081245.jpg', 2, 'ahsha', 3, 'Validando');
INSERT INTO contenido VALUES (425, '20160404.043151.jpg', 2, 'imagen1', 3, 'Validando');
INSERT INTO contenido VALUES (426, '20160404.074138.jpg', 2, 'aa', 11, 'Validando');


--
-- TOC entry 3097 (class 0 OID 0)
-- Dependencies: 175
-- Name: contenido_idcontenido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contenido_idcontenido_seq', 426, true);


--
-- TOC entry 3056 (class 0 OID 37057)
-- Dependencies: 192
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO empresa VALUES ('sds', 'sdsd', '3434323', 'sdsasd', '3422', 2);


--
-- TOC entry 3098 (class 0 OID 0)
-- Dependencies: 191
-- Name: empresa_idempresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_idempresa_seq', 1, true);


--
-- TOC entry 3040 (class 0 OID 36884)
-- Dependencies: 176
-- Data for Name: establecimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO establecimiento VALUES (62, 'Hiper Lider', '786543232', 'Covadonga 2323', 2, 4, -33.0446669999999969, -71.4178560000000004, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.tecsa.cl/experiencia/retail/13.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento VALUES (65, 'Inacap', '676767', 'av.España 1000', 2, 2, -370000, -730000, 2, '20:00:00', '10:00:00', 12, 2, 11, 'http://www.inacap.cl/tportal/portales/tp35f4fead3a341/uploadImg/Image/alumnosextranjeros.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento VALUES (60, 'Starbuck''s', '23456789', '14 norte 12121', 2, 3, -33.0175070000000019, -71.5581229999999948, 2, '20:00:00', '09:00:00', 14, 3, 10, 'http://adm.1.cl/galeriasitios/Z33/2013/2/19/Z33__Fl-5203-Starbucks-Ff.jpg', 'interac', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento VALUES (1, 'PUCV Esc.Ingenieria', '989898989', 'avda. Brasil 13232', 2, 2, -33.0446683999999991, -71.613844400000005, 3, '20:00:00', '09:00:00', 16, 3, 10, 'http://eii.pucv.cl/wp-content/uploads/2008/04/escuela.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento VALUES (43, 'Ripley', '5675675', 'Libertad 3232', 2, 3, -33.0086480000000009, -71.5485330000000062, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.mallmarinarauco.cl/wp-content/uploads/2015/01/ripley-23.jpg', 'testing', 'Activado', 2, 'Vertical
', NULL, NULL);
INSERT INTO establecimiento VALUES (61, 'Nike Store', '678909876', 'avda. Brasil 8989', 2, 1, -33.0087139999999977, -71.5475820000000056, 2, '20:00:00', '09:00:00', 12, 3, 10, 'http://www.runclub.cl/site/wp-content/uploads/2013/10/307167_10151102841910413_1297851950_n1.jpg', 'testing', 'Activado', 2, 'Horizontal', NULL, NULL);
INSERT INTO establecimiento VALUES (64, 'lapizlopez', '2429820', 'portales 1234', 1, 4, 3333333, 3333333, 2, '22:00:00', '08:00:00', 12, 2, 8, 'http://www.portalcentro.cl/wp-content/uploads/2014/06/IMG_0500-710x375.jpg', 'testing', 'Activado', 2, 'Horizontal', NULL, NULL);
INSERT INTO establecimiento VALUES (63, 'Aiep', '65565', 'La Torre 12', 2, 3, -33.0270480000000006, -71.5490389999999934, 2, '20:00:00', '09:00:00', 12, 2, 10, 'http://neufert-cdn.archdaily.net.s3.amazonaws.com/uploads/photo/image/17429/large_1379380063-018.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento VALUES (48, 'Jumbo', '89889898', '1 norte 1022', 2, 3, -33.0355920000000012, -71.523809, 3, '20:00:00', '09:00:00', 14, 3, 10, 'https://waltsan.files.wordpress.com/2009/01/438840000_82f49016a8.jpg', 'testing', 'Activado', 2, 'Horizontal', NULL, NULL);
INSERT INTO establecimiento VALUES (47, 'Paris', '7898678', 'Freire 767', 2, 4, -33.0439879999999988, -71.4202070000000049, 3, '20:00:00', '09:00:00', 16, 2, 10, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg/220px-Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);


--
-- TOC entry 3099 (class 0 OID 0)
-- Dependencies: 177
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('establecimiento_idestablecimiento_seq', 65, true);


--
-- TOC entry 3042 (class 0 OID 36892)
-- Dependencies: 178
-- Data for Name: marcapantalla; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO marcapantalla VALUES (1, 'Samsung');
INSERT INTO marcapantalla VALUES (2, 'LG');
INSERT INTO marcapantalla VALUES (3, 'AOC');
INSERT INTO marcapantalla VALUES (4, 'Sony');
INSERT INTO marcapantalla VALUES (5, 'Phillips');


--
-- TOC entry 3100 (class 0 OID 0)
-- Dependencies: 179
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marcapantallas_idmarca_seq', 6, true);


--
-- TOC entry 3044 (class 0 OID 36900)
-- Dependencies: 180
-- Data for Name: meses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO meses VALUES (1, 'Enero');
INSERT INTO meses VALUES (2, 'Febrero');
INSERT INTO meses VALUES (3, 'Marzo');
INSERT INTO meses VALUES (4, 'Abril');
INSERT INTO meses VALUES (5, 'Mayo');
INSERT INTO meses VALUES (6, 'Junio');
INSERT INTO meses VALUES (7, 'Julio');
INSERT INTO meses VALUES (8, 'Agosto');
INSERT INTO meses VALUES (9, 'Septiembre');
INSERT INTO meses VALUES (10, 'Octubre');
INSERT INTO meses VALUES (12, 'Diciembre');
INSERT INTO meses VALUES (11, 'Noviembre');


--
-- TOC entry 3062 (class 0 OID 58785)
-- Dependencies: 198
-- Data for Name: provincias; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO provincias VALUES (1, 'Arica', 1);
INSERT INTO provincias VALUES (2, 'Parinacota', 1);
INSERT INTO provincias VALUES (3, 'Iquique', 2);
INSERT INTO provincias VALUES (4, 'El Tamarugal', 2);
INSERT INTO provincias VALUES (5, 'Antofagasta', 3);
INSERT INTO provincias VALUES (6, 'El Loa', 3);
INSERT INTO provincias VALUES (7, 'Tocopilla', 3);
INSERT INTO provincias VALUES (8, 'Chañaral', 4);
INSERT INTO provincias VALUES (9, 'Copiapó', 4);
INSERT INTO provincias VALUES (10, 'Huasco', 4);
INSERT INTO provincias VALUES (11, 'Choapa', 5);
INSERT INTO provincias VALUES (12, 'Elqui', 5);
INSERT INTO provincias VALUES (13, 'Limarí', 5);
INSERT INTO provincias VALUES (14, 'Isla de Pascua', 6);
INSERT INTO provincias VALUES (15, 'Los Andes', 6);
INSERT INTO provincias VALUES (16, 'Petorca', 6);
INSERT INTO provincias VALUES (17, 'Quillota', 6);
INSERT INTO provincias VALUES (18, 'San Antonio', 6);
INSERT INTO provincias VALUES (19, 'San Felipe de Aconcagua', 6);
INSERT INTO provincias VALUES (20, 'Valparaiso', 6);
INSERT INTO provincias VALUES (21, 'Chacabuco', 7);
INSERT INTO provincias VALUES (22, 'Cordillera', 7);
INSERT INTO provincias VALUES (23, 'Maipo', 7);
INSERT INTO provincias VALUES (24, 'Melipilla', 7);
INSERT INTO provincias VALUES (25, 'Santiago', 7);
INSERT INTO provincias VALUES (26, 'Talagante', 7);
INSERT INTO provincias VALUES (27, 'Cachapoal', 8);
INSERT INTO provincias VALUES (28, 'Cardenal Caro', 8);
INSERT INTO provincias VALUES (29, 'Colchagua', 8);
INSERT INTO provincias VALUES (30, 'Cauquenes', 9);
INSERT INTO provincias VALUES (31, 'Curicó', 9);
INSERT INTO provincias VALUES (32, 'Linares', 9);
INSERT INTO provincias VALUES (33, 'Talca', 9);
INSERT INTO provincias VALUES (34, 'Arauco', 10);
INSERT INTO provincias VALUES (35, 'Bio Bío', 10);
INSERT INTO provincias VALUES (36, 'Concepción', 10);
INSERT INTO provincias VALUES (37, 'Ñuble', 10);
INSERT INTO provincias VALUES (38, 'Cautín', 11);
INSERT INTO provincias VALUES (39, 'Malleco', 11);
INSERT INTO provincias VALUES (40, 'Valdivia', 12);
INSERT INTO provincias VALUES (41, 'Ranco', 12);
INSERT INTO provincias VALUES (42, 'Chiloé', 13);
INSERT INTO provincias VALUES (43, 'Llanquihue', 13);
INSERT INTO provincias VALUES (44, 'Osorno', 13);
INSERT INTO provincias VALUES (45, 'Palena', 13);
INSERT INTO provincias VALUES (46, 'Aisén', 14);
INSERT INTO provincias VALUES (47, 'Capitán Prat', 14);
INSERT INTO provincias VALUES (48, 'Coihaique', 14);
INSERT INTO provincias VALUES (49, 'General Carrera', 14);
INSERT INTO provincias VALUES (50, 'Antártica Chilena', 15);
INSERT INTO provincias VALUES (51, 'Magallanes', 15);
INSERT INTO provincias VALUES (52, 'Tierra del Fuego', 15);
INSERT INTO provincias VALUES (53, 'Última Esperanza', 15);


--
-- TOC entry 3101 (class 0 OID 0)
-- Dependencies: 199
-- Name: provincias_provincia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('provincias_provincia_id_seq', 1, false);


--
-- TOC entry 3064 (class 0 OID 58790)
-- Dependencies: 200
-- Data for Name: regiones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO regiones VALUES (1, 'Arica y Parinacota', 'XV');
INSERT INTO regiones VALUES (2, 'Tarapacá', 'I');
INSERT INTO regiones VALUES (3, 'Antofagasta', 'II');
INSERT INTO regiones VALUES (4, 'Atacama', 'III');
INSERT INTO regiones VALUES (5, 'Coquimbo', 'IV');
INSERT INTO regiones VALUES (6, 'Valparaiso', 'V');
INSERT INTO regiones VALUES (7, 'Metropolitana de Santiago', 'RM');
INSERT INTO regiones VALUES (8, 'Libertador General Bernardo O`Higgins', 'VI');
INSERT INTO regiones VALUES (9, 'Maule', 'VII');
INSERT INTO regiones VALUES (10, 'Biobío', 'VIII');
INSERT INTO regiones VALUES (11, 'La Araucanía', 'IX');
INSERT INTO regiones VALUES (12, 'Los Ríos', 'XIV');
INSERT INTO regiones VALUES (13, 'Los Lagos', 'X');
INSERT INTO regiones VALUES (14, 'Aisén del General Carlos Ibáñez del Campo', 'XI');
INSERT INTO regiones VALUES (15, 'Magallanes y de la Antártica Chilena', 'XII');


--
-- TOC entry 3102 (class 0 OID 0)
-- Dependencies: 201
-- Name: regiones_region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('regiones_region_id_seq', 1, false);


--
-- TOC entry 3045 (class 0 OID 36903)
-- Dependencies: 181
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rol VALUES (1, 'anunciante');
INSERT INTO rol VALUES (2, 'cliente');
INSERT INTO rol VALUES (3, 'administrador');
INSERT INTO rol VALUES (4, 'invitado');
INSERT INTO rol VALUES (5, 'pucv');


--
-- TOC entry 3103 (class 0 OID 0)
-- Dependencies: 182
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rol_idrol_seq', 3, true);


--
-- TOC entry 3067 (class 0 OID 58858)
-- Dependencies: 203
-- Data for Name: taller; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO taller VALUES (1, 'Pucv FIN', '2016-04-18', '16:00hrs', 'https://docs.google.com/forms/d/1sDpjv7CSG7DOI_t-wd-uDy_QMmzIRoV4_qxshzpYYuM/viewform', 'Te apoyamos en tus ideas emprendedoras');


--
-- TOC entry 3104 (class 0 OID 0)
-- Dependencies: 202
-- Name: taller_id_taller_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('taller_id_taller_seq', 4, true);


--
-- TOC entry 3047 (class 0 OID 36908)
-- Dependencies: 183
-- Data for Name: tipototem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipototem VALUES (1, 'touch');
INSERT INTO tipototem VALUES (2, 'led');


--
-- TOC entry 3105 (class 0 OID 0)
-- Dependencies: 184
-- Name: tipototem_idtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipototem_idtipo_seq', 2, true);


--
-- TOC entry 3049 (class 0 OID 36913)
-- Dependencies: 185
-- Data for Name: totem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO totem VALUES (204, '2323232', 1, 43, 10, 10, '32', 'Horizontal', NULL, 2, NULL, NULL);
INSERT INTO totem VALUES (203, '54321678', 1, 43, 0, 0, '42', 'Horizontal', NULL, 3, NULL, NULL);
INSERT INTO totem VALUES (202, '23243545', 1, 47, 1, 1, '42', 'Vertical', NULL, 3, NULL, NULL);
INSERT INTO totem VALUES (201, 'PUCV2', 1, 1, -33.0446683999999991, -71.613844400000005, '40', 'Horizontal', NULL, 1, NULL, NULL);
INSERT INTO totem VALUES (206, '6565656', 2, 47, -33.0439879999999988, -71.4202070000000049, '32', 'Vertical', NULL, 5, NULL, NULL);
INSERT INTO totem VALUES (207, '32323223', 2, 63, -33.0270480000000006, -71.5490389999999934, '55', 'Horizontal', NULL, 4, NULL, NULL);
INSERT INTO totem VALUES (205, '34343434', 2, 1, 3333, 3333, '40', 'Vertical', NULL, 2, NULL, NULL);
INSERT INTO totem VALUES (200, '1234533', 1, 1, -33.0446790000000021, -71.6139660000000049, '55', 'Vertical', NULL, 4, NULL, NULL);


--
-- TOC entry 3106 (class 0 OID 0)
-- Dependencies: 186
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 207, true);


--
-- TOC entry 3051 (class 0 OID 36921)
-- Dependencies: 187
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ubicacion VALUES (2, 'valparaiso');
INSERT INTO ubicacion VALUES (3, 'viña del mar');
INSERT INTO ubicacion VALUES (4, 'quilpue');
INSERT INTO ubicacion VALUES (5, 'limache');
INSERT INTO ubicacion VALUES (6, 'trovolhue');
INSERT INTO ubicacion VALUES (1, 'santiago');


--
-- TOC entry 3107 (class 0 OID 0)
-- Dependencies: 188
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 6, true);


--
-- TOC entry 3053 (class 0 OID 36926)
-- Dependencies: 189
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 3, 2, NULL, NULL);
INSERT INTO usuario VALUES (35, 'demoPucv', 'demo', 'pucv@pucv.cl', 5, 2, NULL, NULL);
INSERT INTO usuario VALUES (15, 'jiu', 'jiu', 'jinostroza@interac.cl', 1, 2, NULL, NULL);
INSERT INTO usuario VALUES (2, 'jchacon', '5416d7cd6ef195a0f7622a9c56b55e84', 'joacoch@hotmail.cl', 2, 2, NULL, NULL);
INSERT INTO usuario VALUES (32, 'jjchacon', '5416d7cd6ef195a0f7622a9c56b55e84', 'joaquin.chacon.a@gmail.com', 1, 2, NULL, NULL);
INSERT INTO usuario VALUES (30, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'jchacon@interac.cl', 3, 2, NULL, NULL);


--
-- TOC entry 3108 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 38, true);


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-04-14 18:11:15

--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-05-24 16:57:55

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
-- TOC entry 3096 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 206 (class 1259 OID 66989)
-- Name: analitica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE analitica (
    fechahora timestamp without time zone,
    cuerpos integer,
    face integer,
    edad integer,
    sexo character varying,
    expresion character varying,
    modulo character varying
);


ALTER TABLE analitica OWNER TO postgres;

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
-- TOC entry 3097 (class 0 OID 0)
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
-- TOC entry 3098 (class 0 OID 0)
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
-- TOC entry 3099 (class 0 OID 0)
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
-- TOC entry 3100 (class 0 OID 0)
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
-- TOC entry 3101 (class 0 OID 0)
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
-- TOC entry 3102 (class 0 OID 0)
-- Dependencies: 175
-- Name: contenido_idcontenido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contenido_idcontenido_seq OWNED BY contenido.idcontenido;


--
-- TOC entry 205 (class 1259 OID 66958)
-- Name: contrato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE contrato (
    idcontrato integer NOT NULL,
    idcliente integer,
    idempresa integer,
    finicio date,
    ffin date,
    path character varying(100),
    firma character varying(200),
    usuario character varying(50)
);


ALTER TABLE contrato OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 66956)
-- Name: contrato_idcontrato_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contrato_idcontrato_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contrato_idcontrato_seq OWNER TO postgres;

--
-- TOC entry 3103 (class 0 OID 0)
-- Dependencies: 204
-- Name: contrato_idcontrato_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contrato_idcontrato_seq OWNED BY contrato.idcontrato;


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
-- TOC entry 3104 (class 0 OID 0)
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
-- TOC entry 3105 (class 0 OID 0)
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
-- TOC entry 3106 (class 0 OID 0)
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
-- TOC entry 3107 (class 0 OID 0)
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
-- TOC entry 3108 (class 0 OID 0)
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
-- TOC entry 3109 (class 0 OID 0)
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
    nombre character varying(256),
    idusuario integer
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
-- TOC entry 3110 (class 0 OID 0)
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
-- TOC entry 3111 (class 0 OID 0)
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
-- TOC entry 3112 (class 0 OID 0)
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
-- TOC entry 3113 (class 0 OID 0)
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
-- TOC entry 3114 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 2851 (class 2604 OID 37045)
-- Name: idcampana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana ALTER COLUMN idcampana SET DEFAULT nextval('campana_idcampana_seq'::regclass);


--
-- TOC entry 2864 (class 2604 OID 58756)
-- Name: idcam_est; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campestab ALTER COLUMN idcam_est SET DEFAULT nextval('"campestab_idcam-est_seq"'::regclass);


--
-- TOC entry 2852 (class 2604 OID 37046)
-- Name: idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN idcategoria SET DEFAULT nextval('categoria_idcategoria_seq'::regclass);


--
-- TOC entry 2866 (class 2604 OID 58795)
-- Name: comuna_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comunas ALTER COLUMN comuna_id SET DEFAULT nextval('comunas_comuna_id_seq'::regclass);


--
-- TOC entry 2854 (class 2604 OID 37047)
-- Name: idcontenido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contenido ALTER COLUMN idcontenido SET DEFAULT nextval('contenido_idcontenido_seq'::regclass);


--
-- TOC entry 2870 (class 2604 OID 66961)
-- Name: idcontrato; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contrato ALTER COLUMN idcontrato SET DEFAULT nextval('contrato_idcontrato_seq'::regclass);


--
-- TOC entry 2863 (class 2604 OID 37060)
-- Name: idempresa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa ALTER COLUMN idempresa SET DEFAULT nextval('empresa_idempresa_seq'::regclass);


--
-- TOC entry 2855 (class 2604 OID 37048)
-- Name: idestablecimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento ALTER COLUMN idestablecimiento SET DEFAULT nextval('establecimiento_idestablecimiento_seq'::regclass);


--
-- TOC entry 2856 (class 2604 OID 37049)
-- Name: idmarca; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marcapantalla ALTER COLUMN idmarca SET DEFAULT nextval('marcapantallas_idmarca_seq'::regclass);


--
-- TOC entry 2867 (class 2604 OID 58796)
-- Name: provincia_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY provincias ALTER COLUMN provincia_id SET DEFAULT nextval('provincias_provincia_id_seq'::regclass);


--
-- TOC entry 2868 (class 2604 OID 58797)
-- Name: region_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY regiones ALTER COLUMN region_id SET DEFAULT nextval('regiones_region_id_seq'::regclass);


--
-- TOC entry 2857 (class 2604 OID 37050)
-- Name: id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rol ALTER COLUMN id_rol SET DEFAULT nextval('rol_idrol_seq'::regclass);


--
-- TOC entry 2869 (class 2604 OID 58861)
-- Name: id_taller; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY taller ALTER COLUMN id_taller SET DEFAULT nextval('taller_id_taller_seq'::regclass);


--
-- TOC entry 2858 (class 2604 OID 37051)
-- Name: idtipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipototem ALTER COLUMN idtipo SET DEFAULT nextval('tipototem_idtipo_seq'::regclass);


--
-- TOC entry 2859 (class 2604 OID 37052)
-- Name: idtotem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem ALTER COLUMN idtotem SET DEFAULT nextval('totem_idtotem_seq'::regclass);


--
-- TOC entry 2860 (class 2604 OID 37053)
-- Name: idubicacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ubicacion ALTER COLUMN idubicacion SET DEFAULT nextval('ubicacion_idubicacion_seq'::regclass);


--
-- TOC entry 2862 (class 2604 OID 37054)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 3088 (class 0 OID 66989)
-- Dependencies: 206
-- Data for Name: analitica; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:51:31', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:51:36', 3, 1, 26, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:51:40', 2, 1, 19, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:51:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:51:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:51:57', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:52:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:52:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:52:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:52:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:52:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:52:58', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:53:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:42', 2, 1, 30, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:46', 1, 1, 31, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:54:57', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:03', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:11', 1, 1, 34, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:15', 0, 1, 45, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:20', 0, 1, 28, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:24', 0, 1, 27, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:28', 0, 1, 28, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:32', 0, 1, 31, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:55:36', 0, 1, 28, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:56:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:56:46', 1, 1, 19, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:56:50', 1, 1, 17, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:56:55', 1, 1, 24, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:56:59', 2, 1, 18, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:03', 1, 1, 23, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:08', 0, 1, 23, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:12', 1, 1, 23, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:17', 2, 1, 19, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:21', 1, 1, 19, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:25', 1, 1, 24, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:30', 2, 1, 23, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:34', 1, 1, 25, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:38', 1, 1, 27, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:43', 1, 1, 22, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:47', 1, 1, 26, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:51', 2, 1, 26, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:57:56', 1, 1, 21, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:00', 1, 1, 18, 'Mujer ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:04', 1, 1, 22, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:09', 2, 1, 22, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:13', 1, 1, 21, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:17', 2, 1, 20, 'Mujer ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:22', 1, 1, 30, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:26', 1, 1, 17, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:31', 0, 1, 24, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:35', 2, 1, 20, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:39', 0, 1, 21, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:48', 2, 1, 24, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:52', 2, 1, 20, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:58:57', 0, 1, 21, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:01', 2, 1, 15, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:05', 2, 1, 22, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:10', 1, 1, 25, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:14', 0, 1, 19, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:18', 0, 1, 22, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:32', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:36', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:46', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:50', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 15:59:57', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:04', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:07', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:31', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:00:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:06', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:44', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:51', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:01:58', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:01', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:15', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:02:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:03:56', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:04:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:23', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:27', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:30', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:44', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:51', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:55', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:05:58', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:16', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:20', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:23', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:30', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:45', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:48', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:06:56', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:03', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:10', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:25', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:32', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:46', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:07:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:25', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:42', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:46', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:08:56', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:31', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:09:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:20', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:10:37', 1, 1, 31, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:12', 1, 1, 43, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:17', 1, 1, 27, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:21', 1, 1, 31, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:25', 2, 1, 28, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:29', 2, 2, 26, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:34', 0, 1, 26, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:38', 1, 1, 28, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:42', 0, 1, 27, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:49', 1, 1, 29, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:56', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:13:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:42', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:49', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:55', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:14:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:25', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:15:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:16:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:15', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:17:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:18:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:37', 1, 1, 46, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:19:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:51', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:54', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:20:58', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:01', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:04', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:11', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:18', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:31', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:38', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:45', 2, 1, 30, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:21:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:16', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:22', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:26', 5, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:32', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:39', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:22:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:09', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:23:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:46', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:24:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:03', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:46', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:25:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:03', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:10', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:23', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:27', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:41', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:54', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:26:57', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:01', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:04', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:18', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:51', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:27:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:15', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:18', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:25', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:42', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:28:59', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:02', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:05', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:09', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:15', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:22', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:26', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:43', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:46', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:53', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:29:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:00', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:03', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:06', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:10', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:16', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:27', 1, 1, 28, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:31', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:38', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:51', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:54', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:30:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:01', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:04', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:11', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:15', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:18', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:25', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:28', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:31', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:35', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:38', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:42', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:45', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:48', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:52', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:55', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:31:58', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:02', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:09', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:15', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:22', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:25', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:32:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:33', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:37', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:50', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:54', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:33:57', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:04', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:11', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:21', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:55', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:34:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:09', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:15', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:35:56', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:00', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:10', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:27', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:37', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:54', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:36:58', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:01', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:12', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:15', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:22', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:29', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:39', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:46', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:50', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:37:57', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:04', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:10', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:14', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:17', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:31', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:48', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:52', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:38:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:16', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:22', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:29', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:53', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:39:56', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:06', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:10', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:16', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:23', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:30', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:37', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:40', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:43', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:47', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:50', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:54', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:40:57', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:04', 5, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:07', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:11', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:14', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:18', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:21', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:28', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:31', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:34', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:38', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:48', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:51', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:55', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:41:58', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:25', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:42', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:49', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:52', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:56', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:42:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:06', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:16', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:23', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:29', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:40', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:46', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:50', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:43:57', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:04', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:11', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:14', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:17', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:28', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:31', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:38', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:42', 6, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:48', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:52', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:44:59', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:02', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:06', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:29', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:45:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:30', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:46', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:50', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:46:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:23', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:44', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:47', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:51', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:47:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:34', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:48', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:51', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:48:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:01', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:05', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:56', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:49:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:50:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:50:06', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:50:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:50:12', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:50:16', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 16:50:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:06:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:15', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:18', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:25', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:49', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:52', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:07:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:08:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:06', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:09:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:37', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:54', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:10:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:08', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:42', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:11:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:22', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:12:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:16', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:23', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:27', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:30', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:33', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:37', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:40', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:51', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:54', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:07', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:16', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:23', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:27', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:30', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:33', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:37', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:40', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:51', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:54', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:13:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:07', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:11', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:21', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:24', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:34', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:48', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:51', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:14:58', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:18', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:31', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:15:58', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:16:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:19', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:39', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:42', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:17:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:09', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:22', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:43', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:46', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:49', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:18:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:03', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:06', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:10', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:13', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:40', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:50', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:19:56', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:10', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:13', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:20', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:24', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:27', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:34', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:54', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:20:57', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:14', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:28', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:51', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:21:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:45', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:49', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:22:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:23', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:26', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:23:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:10', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:24:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:15', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:25:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:26:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:26:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:26:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:26:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:26:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:26:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:26:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:27:03', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:27:20', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:27:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:27:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:27:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:27:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:27:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:28:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:18', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:45', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:29:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:30:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:31:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:31:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:32:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:33:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:33:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:33:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:33:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:33:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:34:05', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:34:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:34:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:34:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:34:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:35:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:36:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:37:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:38:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:38:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:38:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:38:43', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:38:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:38:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:38:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:04', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:39:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:40:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:40:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:40:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:40:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:40:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:40:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:35', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:46', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:41:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:10', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:13', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:24', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:37', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:51', 4, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:42:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:08', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:43:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:44:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:44:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:44:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:44:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:44:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:44:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:31', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:45:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:46:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:46:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:46:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:46:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:46:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:46:54', 0, 1, 32, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:47:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:47:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:47:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:48:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:48:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:48:30', 0, 1, 29, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 17:48:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:23', 0, 1, 41, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:27', 1, 1, 46, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:31', 1, 1, 31, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:51', 2, 1, 35, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:55', 1, 1, 37, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:01:59', 2, 1, 36, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:04', 1, 1, 34, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:27', 1, 1, 39, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:41', 0, 1, 33, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:02:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:10', 1, 1, 25, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:14', 0, 1, 35, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:18', 0, 1, 23, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:22', 1, 1, 30, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:26', 1, 1, 34, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:30', 1, 1, 28, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:34', 1, 1, 33, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:38', 0, 1, 41, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:03:42', 1, 1, 32, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:04:04', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:04:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:04:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:04:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:04:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:04:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:04:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:05:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:05:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:05:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:05:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:05:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:00', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:06:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:42', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:07:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:08:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:09', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:09:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:20', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:33', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:43', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:10:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:28', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:35', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:11:58', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:14', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:30', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:51', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:54', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:12:57', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:04', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:07', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:21', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:38', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:42', 1, 1, 29, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:46', 1, 1, 31, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:50', 1, 1, 30, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:55', 0, 1, 27, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:13:59', 0, 1, 28, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:03', 0, 1, 30, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:08', 0, 1, 30, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:12', 1, 1, 33, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:19', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:14:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:23', 0, 1, 30, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:37', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:48', 1, 1, 29, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:53', 1, 1, 31, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:15:57', 1, 1, 27, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:24', 1, 1, 29, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:31', 2, 1, 32, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:35', 2, 1, 29, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:40', 1, 1, 32, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:44', 3, 1, 33, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:48', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:52', 1, 1, 44, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:16:56', 1, 1, 30, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:17:01', 1, 1, 40, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:17:05', 2, 1, 37, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:17:09', 3, 1, 36, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:18:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:18:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:18:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:18:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:18:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:19:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:19:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:19:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:19:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:19:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:28', 0, 1, 25, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:33', 0, 1, 31, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:37', 0, 1, 35, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:41', 0, 1, 40, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:46', 0, 1, 35, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:50', 0, 1, 34, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:55', 1, 1, 33, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:20:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:24', 1, 1, 43, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:28', 1, 1, 32, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:32', 0, 1, 35, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:37', 2, 1, 38, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:41', 1, 1, 28, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:46', 2, 1, 33, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:50', 2, 1, 35, 'Hombre ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:55', 1, 1, 28, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:21:59', 1, 1, 31, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:03', 2, 1, 36, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:11', 1, 1, 32, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:19', 2, 1, 39, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:27', 0, 1, 21, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:31', 1, 1, 24, 'Mujer ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:36', 1, 1, 29, 'Mujer ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:40', 1, 1, 28, 'Mujer ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:52', 1, 1, 33, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:22:57', 1, 1, 31, 'Mujer ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:01', 1, 1, 23, 'Mujer ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:05', 2, 1, 28, 'Mujer ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:10', 1, 1, 29, 'Mujer ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:14', 2, 1, 27, 'Hombre ', 'Sorpresa ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:19', 2, 1, 29, 'Mujer ', 'Neutral ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:29', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:33', 1, 1, 25, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:38', 1, 1, 28, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:43', 1, 1, 30, 'Hombre ', 'Feliz ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:47', 1, 1, 29, 'Hombre ', 'Triste ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:52', 2, 1, 40, 'Hombre ', 'Enojado ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:56', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:23:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:03', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:31', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:34', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:41', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:45', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:48', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:24:59', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:02', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:16', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:23', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:27', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:25:55', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:09', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:12', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:16', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:26', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:30', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:33', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:37', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:44', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:47', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:26:58', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:27:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:27:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:27:40', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:27:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:27:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:32', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:36', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:46', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:50', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:28:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:04', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:07', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:11', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:45', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:29:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:06', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:17', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:24', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:52', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:55', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:30:59', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:02', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:13', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:23', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:27', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:37', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:40', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:51', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:31:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:32:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:32:26', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:32:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:32:47', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:32:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:01', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:11', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:15', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:18', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:25', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:39', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:42', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:46', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:49', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:53', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:33:57', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:00', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:07', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:17', 3, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:34', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:41', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:44', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:54', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:34:58', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:01', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:05', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:08', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:12', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:15', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:19', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:22', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:29', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:32', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:36', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:39', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:49', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:53', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:35:56', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:03', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:10', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:14', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:17', 2, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:21', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:28', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:38', 1, 0, 0, 'None ', 'None ', '201 ');
INSERT INTO analitica (fechahora, cuerpos, face, edad, sexo, expresion, modulo) VALUES ('2016-05-23 18:36:41', 1, 0, 0, 'None ', 'None ', '201 ');


--
-- TOC entry 3075 (class 0 OID 58724)
-- Dependencies: 193
-- Data for Name: campaconte; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3051 (class 0 OID 36861)
-- Dependencies: 169
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3115 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 238, true);


--
-- TOC entry 3053 (class 0 OID 36867)
-- Dependencies: 171
-- Data for Name: campatotem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3076 (class 0 OID 58737)
-- Dependencies: 194
-- Data for Name: campestab; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3116 (class 0 OID 0)
-- Dependencies: 195
-- Name: campestab_idcam-est_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"campestab_idcam-est_seq"', 256, true);


--
-- TOC entry 3054 (class 0 OID 36870)
-- Dependencies: 172
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
-- TOC entry 3117 (class 0 OID 0)
-- Dependencies: 173
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 13, true);


--
-- TOC entry 3078 (class 0 OID 58780)
-- Dependencies: 196
-- Data for Name: comunas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (1, 'Arica', 1);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (2, 'Camarones', 1);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (3, 'General Lagos', 2);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (4, 'Putre', 2);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (5, 'Alto Hospicio', 3);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (6, 'Iquique', 3);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (7, 'Camiña', 4);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (8, 'Colchane', 4);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (9, 'Huara', 4);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (10, 'Pica', 4);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (11, 'Pozo Almonte', 4);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (12, 'Antofagasta', 5);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (13, 'Mejillones', 5);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (14, 'Sierra Gorda', 5);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (15, 'Taltal', 5);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (16, 'Calama', 6);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (17, 'Ollague', 6);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (18, 'San Pedro de Atacama', 6);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (19, 'María Elena', 7);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (20, 'Tocopilla', 7);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (21, 'Chañaral', 8);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (22, 'Diego de Almagro', 8);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (23, 'Caldera', 9);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (24, 'Copiapó', 9);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (25, 'Tierra Amarilla', 9);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (26, 'Alto del Carmen', 10);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (27, 'Freirina', 10);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (28, 'Huasco', 10);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (29, 'Vallenar', 10);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (30, 'Canela', 11);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (31, 'Illapel', 11);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (32, 'Los Vilos', 11);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (33, 'Salamanca', 11);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (34, 'Andacollo', 12);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (35, 'Coquimbo', 12);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (36, 'La Higuera', 12);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (37, 'La Serena', 12);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (38, 'Paihuaco', 12);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (39, 'Vicuña', 12);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (40, 'Combarbalá', 13);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (41, 'Monte Patria', 13);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (42, 'Ovalle', 13);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (43, 'Punitaqui', 13);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (44, 'Río Hurtado', 13);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (45, 'Isla de Pascua', 14);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (46, 'Calle Larga', 15);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (47, 'Los Andes', 15);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (48, 'Rinconada', 15);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (49, 'San Esteban', 15);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (50, 'La Ligua', 16);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (51, 'Papudo', 16);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (52, 'Petorca', 16);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (53, 'Zapallar', 16);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (54, 'Hijuelas', 17);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (55, 'La Calera', 17);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (56, 'La Cruz', 17);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (57, 'Limache', 17);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (58, 'Nogales', 17);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (59, 'Olmué', 17);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (60, 'Quillota', 17);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (61, 'Algarrobo', 18);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (62, 'Cartagena', 18);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (63, 'El Quisco', 18);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (64, 'El Tabo', 18);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (65, 'San Antonio', 18);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (66, 'Santo Domingo', 18);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (67, 'Catemu', 19);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (68, 'Llaillay', 19);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (69, 'Panquehue', 19);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (70, 'Putaendo', 19);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (71, 'San Felipe', 19);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (72, 'Santa María', 19);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (73, 'Casablanca', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (74, 'Concón', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (75, 'Juan Fernández', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (76, 'Puchuncaví', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (77, 'Quilpué', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (78, 'Quintero', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (79, 'Valparaíso', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (80, 'Villa Alemana', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (81, 'Viña del Mar', 20);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (82, 'Colina', 21);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (83, 'Lampa', 21);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (84, 'Tiltil', 21);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (85, 'Pirque', 22);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (86, 'Puente Alto', 22);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (87, 'San José de Maipo', 22);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (88, 'Buin', 23);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (89, 'Calera de Tango', 23);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (90, 'Paine', 23);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (91, 'San Bernardo', 23);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (92, 'Alhué', 24);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (93, 'Curacaví', 24);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (94, 'María Pinto', 24);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (95, 'Melipilla', 24);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (96, 'San Pedro', 24);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (97, 'Cerrillos', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (98, 'Cerro Navia', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (99, 'Conchalí', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (100, 'El Bosque', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (101, 'Estación Central', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (102, 'Huechuraba', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (103, 'Independencia', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (104, 'La Cisterna', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (105, 'La Granja', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (106, 'La Florida', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (107, 'La Pintana', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (108, 'La Reina', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (109, 'Las Condes', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (110, 'Lo Barnechea', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (111, 'Lo Espejo', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (112, 'Lo Prado', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (113, 'Macul', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (114, 'Maipú', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (115, 'Ñuñoa', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (116, 'Pedro Aguirre Cerda', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (117, 'Peñalolén', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (118, 'Providencia', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (119, 'Pudahuel', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (120, 'Quilicura', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (121, 'Quinta Normal', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (122, 'Recoleta', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (123, 'Renca', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (124, 'San Miguel', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (125, 'San Joaquín', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (126, 'San Ramón', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (127, 'Santiago', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (128, 'Vitacura', 25);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (129, 'El Monte', 26);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (130, 'Isla de Maipo', 26);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (131, 'Padre Hurtado', 26);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (132, 'Peñaflor', 26);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (133, 'Talagante', 26);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (134, 'Codegua', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (135, 'Coínco', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (136, 'Coltauco', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (137, 'Doñihue', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (138, 'Graneros', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (139, 'Las Cabras', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (140, 'Machalí', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (141, 'Malloa', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (142, 'Mostazal', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (143, 'Olivar', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (144, 'Peumo', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (145, 'Pichidegua', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (146, 'Quinta de Tilcoco', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (147, 'Rancagua', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (148, 'Rengo', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (149, 'Requínoa', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (150, 'San Vicente de Tagua Tagua', 27);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (151, 'La Estrella', 28);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (152, 'Litueche', 28);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (153, 'Marchihue', 28);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (154, 'Navidad', 28);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (155, 'Peredones', 28);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (156, 'Pichilemu', 28);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (157, 'Chépica', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (158, 'Chimbarongo', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (159, 'Lolol', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (160, 'Nancagua', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (161, 'Palmilla', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (162, 'Peralillo', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (163, 'Placilla', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (164, 'Pumanque', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (165, 'San Fernando', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (166, 'Santa Cruz', 29);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (167, 'Cauquenes', 30);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (168, 'Chanco', 30);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (169, 'Pelluhue', 30);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (170, 'Curicó', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (171, 'Hualañé', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (172, 'Licantén', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (173, 'Molina', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (174, 'Rauco', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (175, 'Romeral', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (176, 'Sagrada Familia', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (177, 'Teno', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (178, 'Vichuquén', 31);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (179, 'Colbún', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (180, 'Linares', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (181, 'Longaví', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (182, 'Parral', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (183, 'Retiro', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (184, 'San Javier', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (185, 'Villa Alegre', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (186, 'Yerbas Buenas', 32);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (187, 'Constitución', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (188, 'Curepto', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (189, 'Empedrado', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (190, 'Maule', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (191, 'Pelarco', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (192, 'Pencahue', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (193, 'Río Claro', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (194, 'San Clemente', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (195, 'San Rafael', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (196, 'Talca', 33);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (197, 'Arauco', 34);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (198, 'Cañete', 34);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (199, 'Contulmo', 34);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (200, 'Curanilahue', 34);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (201, 'Lebu', 34);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (202, 'Los Álamos', 34);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (203, 'Tirúa', 34);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (204, 'Alto Biobío', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (205, 'Antuco', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (206, 'Cabrero', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (207, 'Laja', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (208, 'Los Ángeles', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (209, 'Mulchén', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (210, 'Nacimiento', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (211, 'Negrete', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (212, 'Quilaco', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (213, 'Quilleco', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (214, 'San Rosendo', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (215, 'Santa Bárbara', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (216, 'Tucapel', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (217, 'Yumbel', 35);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (218, 'Chiguayante', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (219, 'Concepción', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (220, 'Coronel', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (221, 'Florida', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (222, 'Hualpén', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (223, 'Hualqui', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (224, 'Lota', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (225, 'Penco', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (226, 'San Pedro de La Paz', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (227, 'Santa Juana', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (228, 'Talcahuano', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (229, 'Tomé', 36);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (230, 'Bulnes', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (231, 'Chillán', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (232, 'Chillán Viejo', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (233, 'Cobquecura', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (234, 'Coelemu', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (235, 'Coihueco', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (236, 'El Carmen', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (237, 'Ninhue', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (238, 'Ñiquen', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (239, 'Pemuco', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (240, 'Pinto', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (241, 'Portezuelo', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (242, 'Quillón', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (243, 'Quirihue', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (244, 'Ránquil', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (245, 'San Carlos', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (246, 'San Fabián', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (247, 'San Ignacio', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (248, 'San Nicolás', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (249, 'Treguaco', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (250, 'Yungay', 37);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (251, 'Carahue', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (252, 'Cholchol', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (253, 'Cunco', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (254, 'Curarrehue', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (255, 'Freire', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (256, 'Galvarino', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (257, 'Gorbea', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (258, 'Lautaro', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (259, 'Loncoche', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (260, 'Melipeuco', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (261, 'Nueva Imperial', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (262, 'Padre Las Casas', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (263, 'Perquenco', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (264, 'Pitrufquén', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (265, 'Pucón', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (266, 'Saavedra', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (267, 'Temuco', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (268, 'Teodoro Schmidt', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (269, 'Toltén', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (270, 'Vilcún', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (271, 'Villarrica', 38);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (272, 'Angol', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (273, 'Collipulli', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (274, 'Curacautín', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (275, 'Ercilla', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (276, 'Lonquimay', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (277, 'Los Sauces', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (278, 'Lumaco', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (279, 'Purén', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (280, 'Renaico', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (281, 'Traiguén', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (282, 'Victoria', 39);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (283, 'Corral', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (284, 'Lanco', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (285, 'Los Lagos', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (286, 'Máfil', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (287, 'Mariquina', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (288, 'Paillaco', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (289, 'Panguipulli', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (290, 'Valdivia', 40);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (291, 'Futrono', 41);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (292, 'La Unión', 41);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (293, 'Lago Ranco', 41);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (294, 'Río Bueno', 41);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (295, 'Ancud', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (296, 'Castro', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (297, 'Chonchi', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (298, 'Curaco de Vélez', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (299, 'Dalcahue', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (300, 'Puqueldón', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (301, 'Queilén', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (302, 'Quemchi', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (303, 'Quellón', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (304, 'Quinchao', 42);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (305, 'Calbuco', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (306, 'Cochamó', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (307, 'Fresia', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (308, 'Frutillar', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (309, 'Llanquihue', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (310, 'Los Muermos', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (311, 'Maullín', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (312, 'Puerto Montt', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (313, 'Puerto Varas', 43);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (314, 'Osorno', 44);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (315, 'Puero Octay', 44);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (316, 'Purranque', 44);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (317, 'Puyehue', 44);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (318, 'Río Negro', 44);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (319, 'San Juan de la Costa', 44);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (320, 'San Pablo', 44);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (321, 'Chaitén', 45);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (322, 'Futaleufú', 45);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (323, 'Hualaihué', 45);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (324, 'Palena', 45);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (325, 'Aisén', 46);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (326, 'Cisnes', 46);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (327, 'Guaitecas', 46);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (328, 'Cochrane', 47);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (329, 'O`higgins', 47);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (330, 'Tortel', 47);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (331, 'Coihaique', 48);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (332, 'Lago Verde', 48);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (333, 'Chile Chico', 49);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (334, 'Río Ibáñez', 49);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (335, 'Antártica', 50);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (336, 'Cabo de Hornos', 50);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (337, 'Laguna Blanca', 51);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (338, 'Punta Arenas', 51);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (339, 'Río Verde', 51);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (340, 'San Gregorio', 51);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (341, 'Porvenir', 52);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (342, 'Primavera', 52);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (343, 'Timaukel', 52);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (344, 'Natales', 53);
INSERT INTO comunas (comuna_id, comuna_nombre, provincia_id) VALUES (345, 'Torres del Paine', 53);


--
-- TOC entry 3118 (class 0 OID 0)
-- Dependencies: 197
-- Name: comunas_comuna_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('comunas_comuna_id_seq', 1, false);


--
-- TOC entry 3056 (class 0 OID 36875)
-- Dependencies: 174
-- Data for Name: contenido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (415, '20160315.033450.jpg', 2, 'nuevo1', 11, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (416, '20160315.033756.jpg', 2, 'nuevo2', 10, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (417, '20160315.083538.png', 2, '22', 9, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (418, '20160315.083607.jpg', 2, '2222', 3, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (419, '20160322.031857.jpg', 2, 'weq3', 3, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (420, '20160322.031922.jpg', 2, 'weq4', 9, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (421, '20160323.084931.jpg', 2, 'zsa', 10, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (422, '20160329.041502.png', 2, 'dd', 9, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (423, '20160331.081245.jpg', 2, 'ahsha', 3, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (425, '20160404.043151.jpg', 2, 'imagen1', 3, 'Validando');
INSERT INTO contenido (idcontenido, path, idusuario, nombrecont, idcategoria, estado) VALUES (426, '20160404.074138.jpg', 2, 'aa', 11, 'Validando');


--
-- TOC entry 3119 (class 0 OID 0)
-- Dependencies: 175
-- Name: contenido_idcontenido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contenido_idcontenido_seq', 426, true);


--
-- TOC entry 3087 (class 0 OID 66958)
-- Dependencies: 205
-- Data for Name: contrato; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contrato (idcontrato, idcliente, idempresa, finicio, ffin, path, firma, usuario) VALUES (4, 30, 2, '2016-05-06', '2016-05-27', '20160511.052214.jpg', 'jose aa', 'admin');
INSERT INTO contrato (idcontrato, idcliente, idempresa, finicio, ffin, path, firma, usuario) VALUES (5, 1, 2, '2016-05-05', '2016-05-31', '20160511.053150.jpg', 'Juan Perez', 'admin');


--
-- TOC entry 3120 (class 0 OID 0)
-- Dependencies: 204
-- Name: contrato_idcontrato_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contrato_idcontrato_seq', 5, true);


--
-- TOC entry 3074 (class 0 OID 37057)
-- Dependencies: 192
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO empresa (nombre, razonsocial, rut, direccion, telefono, idempresa) VALUES ('sds', 'sdsd', '3434323', 'sdsasd', '3422', 2);


--
-- TOC entry 3121 (class 0 OID 0)
-- Dependencies: 191
-- Name: empresa_idempresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_idempresa_seq', 1, true);


--
-- TOC entry 3058 (class 0 OID 36884)
-- Dependencies: 176
-- Data for Name: establecimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (62, 'Hiper Lider', '786543232', 'Covadonga 2323', 2, 4, -33.0446669999999969, -71.4178560000000004, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.tecsa.cl/experiencia/retail/13.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (65, 'Inacap', '676767', 'av.España 1000', 2, 2, -370000, -730000, 2, '20:00:00', '10:00:00', 12, 2, 11, 'http://www.inacap.cl/tportal/portales/tp35f4fead3a341/uploadImg/Image/alumnosextranjeros.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (60, 'Starbuck''s', '23456789', '14 norte 12121', 2, 3, -33.0175070000000019, -71.5581229999999948, 2, '20:00:00', '09:00:00', 14, 3, 10, 'http://adm.1.cl/galeriasitios/Z33/2013/2/19/Z33__Fl-5203-Starbucks-Ff.jpg', 'interac', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (1, 'PUCV Esc.Ingenieria', '989898989', 'avda. Brasil 13232', 2, 2, -33.0446683999999991, -71.613844400000005, 3, '20:00:00', '09:00:00', 16, 3, 10, 'http://eii.pucv.cl/wp-content/uploads/2008/04/escuela.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (43, 'Ripley', '5675675', 'Libertad 3232', 2, 3, -33.0086480000000009, -71.5485330000000062, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.mallmarinarauco.cl/wp-content/uploads/2015/01/ripley-23.jpg', 'testing', 'Activado', 2, 'Vertical
', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (61, 'Nike Store', '678909876', 'avda. Brasil 8989', 2, 1, -33.0087139999999977, -71.5475820000000056, 2, '20:00:00', '09:00:00', 12, 3, 10, 'http://www.runclub.cl/site/wp-content/uploads/2013/10/307167_10151102841910413_1297851950_n1.jpg', 'testing', 'Activado', 2, 'Horizontal', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (64, 'lapizlopez', '2429820', 'portales 1234', 1, 4, 3333333, 3333333, 2, '22:00:00', '08:00:00', 12, 2, 8, 'http://www.portalcentro.cl/wp-content/uploads/2014/06/IMG_0500-710x375.jpg', 'testing', 'Activado', 2, 'Horizontal', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (63, 'Aiep', '65565', 'La Torre 12', 2, 3, -33.0270480000000006, -71.5490389999999934, 2, '20:00:00', '09:00:00', 12, 2, 10, 'http://neufert-cdn.archdaily.net.s3.amazonaws.com/uploads/photo/image/17429/large_1379380063-018.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (48, 'Jumbo', '89889898', '1 norte 1022', 2, 3, -33.0355920000000012, -71.523809, 3, '20:00:00', '09:00:00', 14, 3, 10, 'https://waltsan.files.wordpress.com/2009/01/438840000_82f49016a8.jpg', 'testing', 'Activado', 2, 'Horizontal', NULL, NULL);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion, latitud, longitud, valor, horatermino, horainicio, slots, numeropantallas, fk_rubro, urlimagen, carpetaftp, estado, empresa, orientacion, idprovincia, idregion) VALUES (47, 'Paris', '7898678', 'Freire 767', 2, 4, -33.0439879999999988, -71.4202070000000049, 3, '20:00:00', '09:00:00', 16, 2, 10, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg/220px-Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg', 'testing', 'Activado', 2, 'Vertical', NULL, NULL);


--
-- TOC entry 3122 (class 0 OID 0)
-- Dependencies: 177
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('establecimiento_idestablecimiento_seq', 65, true);


--
-- TOC entry 3060 (class 0 OID 36892)
-- Dependencies: 178
-- Data for Name: marcapantalla; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO marcapantalla (idmarca, nombre) VALUES (1, 'Samsung');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (2, 'LG');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (3, 'AOC');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (4, 'Sony');
INSERT INTO marcapantalla (idmarca, nombre) VALUES (5, 'Phillips');


--
-- TOC entry 3123 (class 0 OID 0)
-- Dependencies: 179
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marcapantallas_idmarca_seq', 6, true);


--
-- TOC entry 3062 (class 0 OID 36900)
-- Dependencies: 180
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
-- TOC entry 3080 (class 0 OID 58785)
-- Dependencies: 198
-- Data for Name: provincias; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (1, 'Arica', 1);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (2, 'Parinacota', 1);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (3, 'Iquique', 2);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (4, 'El Tamarugal', 2);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (5, 'Antofagasta', 3);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (6, 'El Loa', 3);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (7, 'Tocopilla', 3);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (8, 'Chañaral', 4);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (9, 'Copiapó', 4);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (10, 'Huasco', 4);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (11, 'Choapa', 5);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (12, 'Elqui', 5);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (13, 'Limarí', 5);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (14, 'Isla de Pascua', 6);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (15, 'Los Andes', 6);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (16, 'Petorca', 6);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (17, 'Quillota', 6);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (18, 'San Antonio', 6);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (19, 'San Felipe de Aconcagua', 6);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (20, 'Valparaiso', 6);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (21, 'Chacabuco', 7);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (22, 'Cordillera', 7);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (23, 'Maipo', 7);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (24, 'Melipilla', 7);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (25, 'Santiago', 7);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (26, 'Talagante', 7);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (27, 'Cachapoal', 8);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (28, 'Cardenal Caro', 8);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (29, 'Colchagua', 8);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (30, 'Cauquenes', 9);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (31, 'Curicó', 9);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (32, 'Linares', 9);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (33, 'Talca', 9);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (34, 'Arauco', 10);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (35, 'Bio Bío', 10);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (36, 'Concepción', 10);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (37, 'Ñuble', 10);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (38, 'Cautín', 11);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (39, 'Malleco', 11);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (40, 'Valdivia', 12);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (41, 'Ranco', 12);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (42, 'Chiloé', 13);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (43, 'Llanquihue', 13);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (44, 'Osorno', 13);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (45, 'Palena', 13);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (46, 'Aisén', 14);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (47, 'Capitán Prat', 14);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (48, 'Coihaique', 14);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (49, 'General Carrera', 14);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (50, 'Antártica Chilena', 15);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (51, 'Magallanes', 15);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (52, 'Tierra del Fuego', 15);
INSERT INTO provincias (provincia_id, provincia_nombre, region_id) VALUES (53, 'Última Esperanza', 15);


--
-- TOC entry 3124 (class 0 OID 0)
-- Dependencies: 199
-- Name: provincias_provincia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('provincias_provincia_id_seq', 1, false);


--
-- TOC entry 3082 (class 0 OID 58790)
-- Dependencies: 200
-- Data for Name: regiones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (1, 'Arica y Parinacota', 'XV');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (2, 'Tarapacá', 'I');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (3, 'Antofagasta', 'II');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (4, 'Atacama', 'III');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (5, 'Coquimbo', 'IV');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (6, 'Valparaiso', 'V');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (7, 'Metropolitana de Santiago', 'RM');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (8, 'Libertador General Bernardo O`Higgins', 'VI');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (9, 'Maule', 'VII');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (10, 'Biobío', 'VIII');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (11, 'La Araucanía', 'IX');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (12, 'Los Ríos', 'XIV');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (13, 'Los Lagos', 'X');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (14, 'Aisén del General Carlos Ibáñez del Campo', 'XI');
INSERT INTO regiones (region_id, region_nombre, region_ordinal) VALUES (15, 'Magallanes y de la Antártica Chilena', 'XII');


--
-- TOC entry 3125 (class 0 OID 0)
-- Dependencies: 201
-- Name: regiones_region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('regiones_region_id_seq', 1, false);


--
-- TOC entry 3063 (class 0 OID 36903)
-- Dependencies: 181
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rol (id_rol, namerol) VALUES (1, 'anunciante');
INSERT INTO rol (id_rol, namerol) VALUES (2, 'cliente');
INSERT INTO rol (id_rol, namerol) VALUES (3, 'administrador');
INSERT INTO rol (id_rol, namerol) VALUES (4, 'invitado');
INSERT INTO rol (id_rol, namerol) VALUES (5, 'pucv');


--
-- TOC entry 3126 (class 0 OID 0)
-- Dependencies: 182
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rol_idrol_seq', 3, true);


--
-- TOC entry 3085 (class 0 OID 58858)
-- Dependencies: 203
-- Data for Name: taller; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO taller (id_taller, sede, fecha, hora, link, nombre, idusuario) VALUES (1, 'Pucv FIN', '2016-04-18', '16:00hrs', 'https://docs.google.com/forms/d/1sDpjv7CSG7DOI_t-wd-uDy_QMmzIRoV4_qxshzpYYuM/viewform', 'Te apoyamos en tus ideas emprendedoras', 2);


--
-- TOC entry 3127 (class 0 OID 0)
-- Dependencies: 202
-- Name: taller_id_taller_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('taller_id_taller_seq', 4, true);


--
-- TOC entry 3065 (class 0 OID 36908)
-- Dependencies: 183
-- Data for Name: tipototem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipototem (idtipo, destipo) VALUES (1, 'touch');
INSERT INTO tipototem (idtipo, destipo) VALUES (2, 'led');


--
-- TOC entry 3128 (class 0 OID 0)
-- Dependencies: 184
-- Name: tipototem_idtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipototem_idtipo_seq', 2, true);


--
-- TOC entry 3067 (class 0 OID 36913)
-- Dependencies: 185
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
-- TOC entry 3129 (class 0 OID 0)
-- Dependencies: 186
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 207, true);


--
-- TOC entry 3069 (class 0 OID 36921)
-- Dependencies: 187
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (2, 'valparaiso');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (3, 'viña del mar');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (4, 'quilpue');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (5, 'limache');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (6, 'trovolhue');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (1, 'santiago');


--
-- TOC entry 3130 (class 0 OID 0)
-- Dependencies: 188
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 6, true);


--
-- TOC entry 3071 (class 0 OID 36926)
-- Dependencies: 189
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 3, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (35, 'demoPucv', 'demo', 'pucv@pucv.cl', 5, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (15, 'jiu', 'jiu', 'jinostroza@interac.cl', 1, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (2, 'jchacon', '5416d7cd6ef195a0f7622a9c56b55e84', 'joacoch@hotmail.cl', 2, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (30, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'jchacon@interac.cl', 3, 2, NULL, NULL);
INSERT INTO usuario (idusuario, username, password, correo, idrol, empresa, nombre, apellido) VALUES (32, 'jjchacon', '5416d7cd6ef195a0f7622a9c56b55e84', 'joaquin.chacon.a@gmail.com', 5, 2, NULL, NULL);


--
-- TOC entry 3131 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 38, true);


--
-- TOC entry 3095 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-05-24 16:59:45

--
-- PostgreSQL database dump complete
--


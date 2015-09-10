--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.4.1
-- Started on 2015-08-26 15:03:22 CLT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 189 (class 3079 OID 12648)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 189
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 17818)
-- Name: anuncio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE anuncio (
    id_anuncio integer NOT NULL,
    descanuncio character varying(250),
    nombre character varying(45),
    idcontenido integer NOT NULL,
    idcategoria integer NOT NULL
);


ALTER TABLE anuncio OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 17816)
-- Name: anuncio_idanuncio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE anuncio_idanuncio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anuncio_idanuncio_seq OWNER TO postgres;

--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 168
-- Name: anuncio_idanuncio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE anuncio_idanuncio_seq OWNED BY anuncio.id_anuncio;


--
-- TOC entry 171 (class 1259 OID 17829)
-- Name: campana; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE campana (
    idcampana integer NOT NULL,
    idcliente integer NOT NULL,
    fechaspasadas date,
    idtotem integer NOT NULL
);


ALTER TABLE campana OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 17827)
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
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE campana_idcampana_seq OWNED BY campana.idcampana;


--
-- TOC entry 188 (class 1259 OID 34288)
-- Name: campanun; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE campanun (
    idcampana integer,
    idanuncio integer,
    idcamanun integer NOT NULL
);


ALTER TABLE campanun OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 17840)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categoria (
    idcategoria integer NOT NULL,
    desccategoria character varying(45)
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 17838)
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
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 172
-- Name: categoria_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_idcategoria_seq OWNED BY categoria.idcategoria;


--
-- TOC entry 187 (class 1259 OID 34219)
-- Name: contenido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contenido (
    idcontenido integer NOT NULL,
    path character varying(1000) NOT NULL,
    idusuario integer NOT NULL
);


ALTER TABLE contenido OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 34217)
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
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 186
-- Name: contenido_idcontenido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contenido_idcontenido_seq OWNED BY contenido.idcontenido;


--
-- TOC entry 183 (class 1259 OID 17981)
-- Name: establecimiento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE establecimiento (
    idestablecimiento integer NOT NULL,
    desestablecimiento character varying(200) NOT NULL,
    fono character varying(12),
    direccion character varying(50),
    idusuario integer NOT NULL,
    idubicacion integer
);


ALTER TABLE establecimiento OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 17979)
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
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 182
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE establecimiento_idestablecimiento_seq OWNED BY establecimiento.idestablecimiento;


--
-- TOC entry 181 (class 1259 OID 17925)
-- Name: rol; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rol (
    id_rol integer NOT NULL,
    namerol character varying(25) NOT NULL
);


ALTER TABLE rol OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 17923)
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
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 180
-- Name: rol_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE rol_idrol_seq OWNED BY rol.id_rol;


--
-- TOC entry 185 (class 1259 OID 34152)
-- Name: tipototem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipototem (
    idtipo integer NOT NULL,
    destipo character varying(50)
);


ALTER TABLE tipototem OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 34150)
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
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 184
-- Name: tipototem_idtipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipototem_idtipo_seq OWNED BY tipototem.idtipo;


--
-- TOC entry 175 (class 1259 OID 17849)
-- Name: totem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE totem (
    idtotem integer NOT NULL,
    noserie character varying(45),
    idtipo integer NOT NULL,
    idestablecimiento integer NOT NULL
);


ALTER TABLE totem OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 17847)
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
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 174
-- Name: totem_idtotem_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE totem_idtotem_seq OWNED BY totem.idtotem;


--
-- TOC entry 177 (class 1259 OID 17858)
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ubicacion (
    idubicacion integer NOT NULL,
    descubicacion character varying(45)
);


ALTER TABLE ubicacion OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 17856)
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
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 176
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ubicacion_idubicacion_seq OWNED BY ubicacion.idubicacion;


--
-- TOC entry 179 (class 1259 OID 17868)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    idusuario integer NOT NULL,
    username character varying(45),
    password character varying(45),
    correo character varying(45),
    empresa character varying(45),
    idrol integer
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 17866)
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
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 178
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 2788 (class 2604 OID 17821)
-- Name: id_anuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anuncio ALTER COLUMN id_anuncio SET DEFAULT nextval('anuncio_idanuncio_seq'::regclass);


--
-- TOC entry 2789 (class 2604 OID 17832)
-- Name: idcampana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana ALTER COLUMN idcampana SET DEFAULT nextval('campana_idcampana_seq'::regclass);


--
-- TOC entry 2790 (class 2604 OID 17843)
-- Name: idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN idcategoria SET DEFAULT nextval('categoria_idcategoria_seq'::regclass);


--
-- TOC entry 2797 (class 2604 OID 34222)
-- Name: idcontenido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contenido ALTER COLUMN idcontenido SET DEFAULT nextval('contenido_idcontenido_seq'::regclass);


--
-- TOC entry 2795 (class 2604 OID 17984)
-- Name: idestablecimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento ALTER COLUMN idestablecimiento SET DEFAULT nextval('establecimiento_idestablecimiento_seq'::regclass);


--
-- TOC entry 2794 (class 2604 OID 17928)
-- Name: id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rol ALTER COLUMN id_rol SET DEFAULT nextval('rol_idrol_seq'::regclass);


--
-- TOC entry 2796 (class 2604 OID 34155)
-- Name: idtipo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipototem ALTER COLUMN idtipo SET DEFAULT nextval('tipototem_idtipo_seq'::regclass);


--
-- TOC entry 2791 (class 2604 OID 17852)
-- Name: idtotem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem ALTER COLUMN idtotem SET DEFAULT nextval('totem_idtotem_seq'::regclass);


--
-- TOC entry 2792 (class 2604 OID 17861)
-- Name: idubicacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ubicacion ALTER COLUMN idubicacion SET DEFAULT nextval('ubicacion_idubicacion_seq'::regclass);


--
-- TOC entry 2793 (class 2604 OID 17871)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 2952 (class 0 OID 17818)
-- Dependencies: 169
-- Data for Name: anuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO anuncio (id_anuncio, descanuncio, nombre, idcontenido, idcategoria) VALUES (39, 'anuncios1', 'anuncio1', 30, 3);
INSERT INTO anuncio (id_anuncio, descanuncio, nombre, idcontenido, idcategoria) VALUES (56, '1243ewrewer', '1231243', 32, 1);
INSERT INTO anuncio (id_anuncio, descanuncio, nombre, idcontenido, idcategoria) VALUES (57, 'sada', 'sad', 30, 10);
INSERT INTO anuncio (id_anuncio, descanuncio, nombre, idcontenido, idcategoria) VALUES (64, 'ds', 'ds', 63, 5);
INSERT INTO anuncio (id_anuncio, descanuncio, nombre, idcontenido, idcategoria) VALUES (65, 'as', 'Patagonia', 31, 10);


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 168
-- Name: anuncio_idanuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('anuncio_idanuncio_seq', 65, true);


--
-- TOC entry 2954 (class 0 OID 17829)
-- Dependencies: 171
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campana (idcampana, idcliente, fechaspasadas, idtotem) VALUES (9, 3, '2015-06-11', 24);


--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 9, true);


--
-- TOC entry 2971 (class 0 OID 34288)
-- Dependencies: 188
-- Data for Name: campanun; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2956 (class 0 OID 17840)
-- Dependencies: 173
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO categoria (idcategoria, desccategoria) VALUES (5, 'ENSEÑANZA MEDIA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (6, 'ENSEÑANZA SUPERIOR');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (7, 'FERRETERIA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (8, 'MULTI TIENDAS');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (9, 'ABARROTES');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (1, 'POLITICA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (10, 'RETAIL');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (3, 'SUPERMERCADO2');


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 172
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 10, true);


--
-- TOC entry 2970 (class 0 OID 34219)
-- Dependencies: 187
-- Data for Name: contenido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contenido (idcontenido, path, idusuario) VALUES (14, '/static/interac/anuncios//Atraer-dinero.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (15, '/static/interac/anuncios//giphy.gif', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (16, '/Users/mary/Documents/Interac/interac-dev/src/main/webapp/images/anuncios//check2.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (17, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//metro-icon-delete.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (18, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//red-hair-woman.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (19, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//Home.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (20, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//Net Framework 4.5.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (21, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//Search.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (22, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//metro-icon-delete.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (23, 'C:\apache-tomcat-8.0.24/static/interac/anuncios//tumblr_nsodfi6OCi1qkkq8io1_540.jpg', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (24, 'C:\apache-tomcat-8.0.24/static/interac/anuncios//windows-10-error-2-040815.jpg', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (25, 'C:\apache-tomcat-8.0.24/static/interac/anuncios//tumblr_nsodfi6OCi1qkkq8io1_540.jpg', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (26, 'torta_1380921808.jpg', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (27, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//delete1.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (28, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//Calendar.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (29, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//Home.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (30, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//red-hair-woman.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (31, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//colegio-vert-1-01.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (32, '650_1000_los-gamers-nos-hemos-convertido-en-pichones-1.jpghuehue_06', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (33, 'tumblr_nsodfi6OCi1qkkq8io1_540.jpg', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (34, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//colegio-vert-1-01.png', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (35, 'C:\apache-tomcat-7.0.61/static/interac/anuncios//IMG_5562.JPG', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (36, '650_1000_los-gamers-nos-hemos-convertido-en-pichones-1.jpg', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (37, '650_1000_los-gamers-nos-hemos-convertido-en-pichones-1.jpg', 3);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (46, 'Desert.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (47, '41604_39570404312_4595_n.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (48, 'ceremonia.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (50, '41604_39570404312_4595_n.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (51, 'ceremonia.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (52, '41604_39570404312_4595_n.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (53, 'ceremonia.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (54, '41604_39570404312_4595_n.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (55, 'IMG_5564.JPG', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (56, '41604_39570404312_4595_n.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (57, 'ceremonia.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (58, '41604_39570404312_4595_n.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (59, '41604_39570404312_4595_n.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (60, 'Hydrangeas.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (61, 'Penguins.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (63, 'Chrysanthemum.jpg', 2);
INSERT INTO contenido (idcontenido, path, idusuario) VALUES (64, 'Hydrangeas.jpg', 2);


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 186
-- Name: contenido_idcontenido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contenido_idcontenido_seq', 64, true);


--
-- TOC entry 2966 (class 0 OID 17981)
-- Dependencies: 183
-- Data for Name: establecimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (43, 'Ripley', '5675675', 'Libertad 3232', 2, 3);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (47, 'Paris', '7898678', 'Freire 767', 2, 4);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (48, 'Jumbo', '89889898', '1 norte 1022', 2, 3);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (61, 'Nike Store', '678909876', 'avda. Brasil 8989', 2, 1);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (62, 'Hiper Lider', '786543232', 'Covadonga 2323', 2, 4);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (60, 'Starbuck''s', '23456789', '14 norte 12121', 2, 3);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (1, 'PUCV Esc.Ingenieria', '989898989', 'avda. Brasil 13232', 2, 2);
INSERT INTO establecimiento (idestablecimiento, desestablecimiento, fono, direccion, idusuario, idubicacion) VALUES (63, 'Aiep', '65565', 'La Torre 12', 2, 3);


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 182
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('establecimiento_idestablecimiento_seq', 63, true);


--
-- TOC entry 2964 (class 0 OID 17925)
-- Dependencies: 181
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rol (id_rol, namerol) VALUES (1, 'anunciante');
INSERT INTO rol (id_rol, namerol) VALUES (2, 'cliente');
INSERT INTO rol (id_rol, namerol) VALUES (3, 'administrador');


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 180
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rol_idrol_seq', 3, true);


--
-- TOC entry 2968 (class 0 OID 34152)
-- Dependencies: 185
-- Data for Name: tipototem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipototem (idtipo, destipo) VALUES (1, 'touch');
INSERT INTO tipototem (idtipo, destipo) VALUES (2, 'led');


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 184
-- Name: tipototem_idtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipototem_idtipo_seq', 2, true);


--
-- TOC entry 2958 (class 0 OID 17849)
-- Dependencies: 175
-- Data for Name: totem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento) VALUES (198, 'y23478y32t6gweqywefbjwef', 2, 60);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento) VALUES (168, '0987654321', 1, 1);
INSERT INTO totem (idtotem, noserie, idtipo, idestablecimiento) VALUES (199, '74846y694037', 2, 60);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 174
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 199, true);


--
-- TOC entry 2960 (class 0 OID 17858)
-- Dependencies: 177
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (2, 'valparaiso');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (3, 'viña del mar');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (4, 'quilpue');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (5, 'limache');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (6, 'trovolhue');
INSERT INTO ubicacion (idubicacion, descubicacion) VALUES (1, 'santiago');


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 176
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 6, true);


--
-- TOC entry 2962 (class 0 OID 17868)
-- Dependencies: 179
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (2, 'jchacon', '1q2w3e4r', 'samsung@interac.cl', 'Samsung', 2);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (4, 'edggar', 'edggar', 'papasconmayo@interoski.cl', 'edggar', 1);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 'Claudio Olivares', 3);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (5, 'dreamteam', 'dreamteam', 'badassness@dreamteam.org', 'DreamTeam Inc', 3);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (10, 'PPastene', '1234', 'pe.pastene@gmail.com', 'Seguros Patito', 3);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (13, 'huehuweree_06', '12345', 'wwde', '2134321', 1);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (14, 'pillitopioxd', '12345', 'hueheu@huehue.cl', 'cortana', 3);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (15, 'jiu', 'jiu', 'jinostroza@interac.cl', 'Interac Ltda.', 3);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (3, 'huehue_06', '12345', 'nomecomopendejas@spiniak.cl', 'SQM', 3);


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 178
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 15, true);


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-08-26 15:05:30 CLT

--
-- PostgreSQL database dump complete
--


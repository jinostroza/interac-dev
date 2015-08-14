--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-08-13 15:26:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2952 (class 1262 OID 17815)
-- Name: Plataforma; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "Plataforma" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE "Plataforma" OWNER TO postgres;

\connect "Plataforma"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 186 (class 3079 OID 12648)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 186
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 18031)
-- Name: afiche; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE afiche (
    idafiche integer NOT NULL,
    path character varying(20) NOT NULL
);


ALTER TABLE afiche OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 17818)
-- Name: anuncio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE anuncio (
    id_anuncio integer NOT NULL,
    descanuncio character varying(250),
    idcampana integer NOT NULL,
    rubro character varying(45),
    idafiche integer NOT NULL,
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
-- TOC entry 2956 (class 0 OID 0)
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
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE campana_idcampana_seq OWNED BY campana.idcampana;


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
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 172
-- Name: categoria_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_idcategoria_seq OWNED BY categoria.idcategoria;


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
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 182
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE establecimiento_idestablecimiento_seq OWNED BY establecimiento.idestablecimiento;


--
-- TOC entry 184 (class 1259 OID 18029)
-- Name: imagen_id_imagen_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE imagen_id_imagen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE imagen_id_imagen_seq OWNER TO postgres;

--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 184
-- Name: imagen_id_imagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE imagen_id_imagen_seq OWNED BY afiche.idafiche;


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
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 180
-- Name: rol_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE rol_idrol_seq OWNED BY rol.id_rol;


--
-- TOC entry 175 (class 1259 OID 17849)
-- Name: totem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE totem (
    idtotem integer NOT NULL,
    noserie character varying(45),
    tipo character varying(20),
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
-- TOC entry 2962 (class 0 OID 0)
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
-- TOC entry 2963 (class 0 OID 0)
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
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 178
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 2786 (class 2604 OID 18034)
-- Name: idafiche; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY afiche ALTER COLUMN idafiche SET DEFAULT nextval('imagen_id_imagen_seq'::regclass);


--
-- TOC entry 2778 (class 2604 OID 17821)
-- Name: id_anuncio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anuncio ALTER COLUMN id_anuncio SET DEFAULT nextval('anuncio_idanuncio_seq'::regclass);


--
-- TOC entry 2779 (class 2604 OID 17832)
-- Name: idcampana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana ALTER COLUMN idcampana SET DEFAULT nextval('campana_idcampana_seq'::regclass);


--
-- TOC entry 2780 (class 2604 OID 17843)
-- Name: idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN idcategoria SET DEFAULT nextval('categoria_idcategoria_seq'::regclass);


--
-- TOC entry 2785 (class 2604 OID 17984)
-- Name: idestablecimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento ALTER COLUMN idestablecimiento SET DEFAULT nextval('establecimiento_idestablecimiento_seq'::regclass);


--
-- TOC entry 2784 (class 2604 OID 17928)
-- Name: id_rol; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rol ALTER COLUMN id_rol SET DEFAULT nextval('rol_idrol_seq'::regclass);


--
-- TOC entry 2781 (class 2604 OID 17852)
-- Name: idtotem; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem ALTER COLUMN idtotem SET DEFAULT nextval('totem_idtotem_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 17861)
-- Name: idubicacion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ubicacion ALTER COLUMN idubicacion SET DEFAULT nextval('ubicacion_idubicacion_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 17871)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 2947 (class 0 OID 18031)
-- Dependencies: 185
-- Data for Name: afiche; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO afiche (idafiche, path) VALUES (1, 'anuncios');


--
-- TOC entry 2931 (class 0 OID 17818)
-- Dependencies: 169
-- Data for Name: anuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO anuncio (id_anuncio, descanuncio, idcampana, rubro, idafiche, idcategoria) VALUES (11, 'huehuehue?', 9, 'internet', 1, 1);
INSERT INTO anuncio (id_anuncio, descanuncio, idcampana, rubro, idafiche, idcategoria) VALUES (15, 'kkkkk', 9, 'ocio', 1, 3);


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 168
-- Name: anuncio_idanuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('anuncio_idanuncio_seq', 15, true);


--
-- TOC entry 2933 (class 0 OID 17829)
-- Dependencies: 171
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campana (idcampana, idcliente, fechaspasadas, idtotem) VALUES (9, 3, '2015-06-11', 24);


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 9, true);


--
-- TOC entry 2935 (class 0 OID 17840)
-- Dependencies: 173
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO categoria (idcategoria, desccategoria) VALUES (2, 'RESTAURANT');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (3, 'SUPERMERCADO');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (4, 'ENSEÑANZA BASICA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (5, 'ENSEÑANZA MEDIA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (6, 'ENSEÑANZA SUPERIOR');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (7, 'FERRETERIA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (8, 'MULTI TIENDAS');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (9, 'ABARROTES');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (1, 'POLITICA');
INSERT INTO categoria (idcategoria, desccategoria) VALUES (10, 'RETAIL');


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 172
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 10, true);


--
-- TOC entry 2945 (class 0 OID 17981)
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
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 182
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('establecimiento_idestablecimiento_seq', 63, true);


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 184
-- Name: imagen_id_imagen_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('imagen_id_imagen_seq', 1, true);


--
-- TOC entry 2943 (class 0 OID 17925)
-- Dependencies: 181
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rol (id_rol, namerol) VALUES (1, 'anunciante');
INSERT INTO rol (id_rol, namerol) VALUES (2, 'administrador');
INSERT INTO rol (id_rol, namerol) VALUES (3, 'anfitrion');
INSERT INTO rol (id_rol, namerol) VALUES (4, 'usuario_web');


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 180
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rol_idrol_seq', 3, true);


--
-- TOC entry 2937 (class 0 OID 17849)
-- Dependencies: 175
-- Data for Name: totem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO totem (idtotem, noserie, tipo, idestablecimiento) VALUES (131, '1231', '2', 47);
INSERT INTO totem (idtotem, noserie, tipo, idestablecimiento) VALUES (133, '123457', '1', 62);
INSERT INTO totem (idtotem, noserie, tipo, idestablecimiento) VALUES (166, '217831736', 'totem porfiado', 63);
INSERT INTO totem (idtotem, noserie, tipo, idestablecimiento) VALUES (24, '12345623842', 'pantalla rota', 1);
INSERT INTO totem (idtotem, noserie, tipo, idestablecimiento) VALUES (113, '637y42', 'lastre', 43);
INSERT INTO totem (idtotem, noserie, tipo, idestablecimiento) VALUES (167, '1432142', 'sin pantalla', 61);


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 174
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 167, true);


--
-- TOC entry 2939 (class 0 OID 17858)
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
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 176
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 6, true);


--
-- TOC entry 2941 (class 0 OID 17868)
-- Dependencies: 179
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 'Claudio Olivares', 2);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (3, 'huehue_06', 'ladesiempre', 'data@data.cl', 'SQM', 2);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (2, 'jchacon', '1q2w3e4r', 'samsung@interac.cl', 'Samsung', 2);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (5, 'dreamteam', 'dreamteam', 'badassness@dreamteam.org', 'DreamTeam Inc', 2);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (4, 'edggar', 'edggar', 'papasconmayo@interoski.cl', 'edggar', 1);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (10, 'PPastene', '1234', 'pe.pastene@gmail.com', 'Seguros Patito', NULL);
INSERT INTO usuario (idusuario, username, password, correo, empresa, idrol) VALUES (11, 'ppastene', '1234', 'pe.pastene@gmail.com', 'Empresas Patito', NULL);


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 178
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 12, true);


--
-- TOC entry 2811 (class 2606 OID 17998)
-- Name: establecimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT establecimiento_pkey PRIMARY KEY (idestablecimiento);


--
-- TOC entry 2813 (class 2606 OID 18036)
-- Name: imagen_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY afiche
    ADD CONSTRAINT imagen_pkey PRIMARY KEY (idafiche);


--
-- TOC entry 2790 (class 2606 OID 17823)
-- Name: pk_anuncio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY anuncio
    ADD CONSTRAINT pk_anuncio PRIMARY KEY (id_anuncio);


--
-- TOC entry 2794 (class 2606 OID 17834)
-- Name: pk_campana; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY campana
    ADD CONSTRAINT pk_campana PRIMARY KEY (idcampana);


--
-- TOC entry 2798 (class 2606 OID 17845)
-- Name: pk_categoria; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT pk_categoria PRIMARY KEY (idcategoria);


--
-- TOC entry 2800 (class 2606 OID 17854)
-- Name: pk_totem; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY totem
    ADD CONSTRAINT pk_totem PRIMARY KEY (idtotem);


--
-- TOC entry 2803 (class 2606 OID 17863)
-- Name: pk_ubicacion; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ubicacion
    ADD CONSTRAINT pk_ubicacion PRIMARY KEY (idubicacion);


--
-- TOC entry 2807 (class 2606 OID 17873)
-- Name: pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (idusuario);


--
-- TOC entry 2809 (class 2606 OID 17930)
-- Name: rol_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id_rol);


--
-- TOC entry 2787 (class 1259 OID 17824)
-- Name: anuncio_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX anuncio_pk ON anuncio USING btree (id_anuncio);


--
-- TOC entry 2791 (class 1259 OID 17835)
-- Name: campana_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX campana_pk ON campana USING btree (idcampana);


--
-- TOC entry 2788 (class 1259 OID 17826)
-- Name: campanaanuncio_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX campanaanuncio_fk ON anuncio USING btree (idcampana);


--
-- TOC entry 2796 (class 1259 OID 17846)
-- Name: categoria_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX categoria_pk ON categoria USING btree (idcategoria);


--
-- TOC entry 2805 (class 1259 OID 17874)
-- Name: cliente_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX cliente_pk ON usuario USING btree (idusuario);


--
-- TOC entry 2792 (class 1259 OID 17836)
-- Name: clientecampana_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX clientecampana_fk ON campana USING btree (idcliente);


--
-- TOC entry 2801 (class 1259 OID 17855)
-- Name: totem_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX totem_pk ON totem USING btree (idtotem);


--
-- TOC entry 2795 (class 1259 OID 17837)
-- Name: totemcampana_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX totemcampana_fk ON campana USING btree (idtotem);


--
-- TOC entry 2804 (class 1259 OID 17864)
-- Name: ubicacion_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX ubicacion_pk ON ubicacion USING btree (idubicacion);


--
-- TOC entry 2815 (class 2606 OID 18037)
-- Name: anuncio_idafiche_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anuncio
    ADD CONSTRAINT anuncio_idafiche_fkey FOREIGN KEY (idafiche) REFERENCES afiche(idafiche);


--
-- TOC entry 2816 (class 2606 OID 18047)
-- Name: anuncio_idcategoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anuncio
    ADD CONSTRAINT anuncio_idcategoria_fkey FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria);


--
-- TOC entry 2823 (class 2606 OID 18014)
-- Name: establecimiento_idubicacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT establecimiento_idubicacion_fkey FOREIGN KEY (idubicacion) REFERENCES ubicacion(idubicacion);


--
-- TOC entry 2822 (class 2606 OID 17987)
-- Name: establecimiento_idusuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY establecimiento
    ADD CONSTRAINT establecimiento_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 2814 (class 2606 OID 17880)
-- Name: fk_anuncio_campanaan_campana; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY anuncio
    ADD CONSTRAINT fk_anuncio_campanaan_campana FOREIGN KEY (idcampana) REFERENCES campana(idcampana) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2817 (class 2606 OID 17885)
-- Name: fk_campana_clienteca_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana
    ADD CONSTRAINT fk_campana_clienteca_usuario FOREIGN KEY (idcliente) REFERENCES usuario(idusuario) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2818 (class 2606 OID 17890)
-- Name: fk_campana_totemcamp_totem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campana
    ADD CONSTRAINT fk_campana_totemcamp_totem FOREIGN KEY (idtotem) REFERENCES totem(idtotem) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2819 (class 2606 OID 18004)
-- Name: totem_idestablecimiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY totem
    ADD CONSTRAINT totem_idestablecimiento_fkey FOREIGN KEY (idestablecimiento) REFERENCES establecimiento(idestablecimiento);


--
-- TOC entry 2820 (class 2606 OID 17933)
-- Name: usuario_idrol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_idrol_fkey FOREIGN KEY (idrol) REFERENCES rol(id_rol);


--
-- TOC entry 2821 (class 2606 OID 17952)
-- Name: usuario_idrol_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_idrol_fkey1 FOREIGN KEY (idrol) REFERENCES rol(id_rol);


--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-08-13 15:27:37

--
-- PostgreSQL database dump complete
--


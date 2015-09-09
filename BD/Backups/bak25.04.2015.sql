--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.3.1
-- Started on 2015-04-25 16:58:00 CLST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2900 (class 0 OID 17818)
-- Dependencies: 169
-- Data for Name: anuncio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 168
-- Name: anuncio_idanuncio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('anuncio_idanuncio_seq', 1, false);


--
-- TOC entry 2902 (class 0 OID 17829)
-- Dependencies: 171
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 1, false);


--
-- TOC entry 2904 (class 0 OID 17840)
-- Dependencies: 173
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 172
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 1, false);


--
-- TOC entry 2906 (class 0 OID 17849)
-- Dependencies: 175
-- Data for Name: totem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 174
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 1, false);


--
-- TOC entry 2908 (class 0 OID 17858)
-- Dependencies: 177
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 176
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 1, false);


--
-- TOC entry 2910 (class 0 OID 17868)
-- Dependencies: 179
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (idusuario, username, password, correo, empresa, rol) VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 'Claudio Olivares', NULL);
INSERT INTO usuario (idusuario, username, password, correo, empresa, rol) VALUES (2, 'jchacon', '1q2w3e4r', 'samsung@interac.cl', 'Samsung', NULL);
INSERT INTO usuario (idusuario, username, password, correo, empresa, rol) VALUES (3, 'huehue_06', 'ladesiempre', 'data@data.cl', 'SQM', NULL);
INSERT INTO usuario (idusuario, username, password, correo, empresa, rol) VALUES (4, 'edggar', 'edggar', 'papasconmayo@interoski.cl', 'edggar', NULL);
INSERT INTO usuario (idusuario, username, password, correo, empresa, rol) VALUES (5, 'dreamteam', 'dreamteam', 'badassness@dreamteam.org', 'DreamTeam Inc', NULL);


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 178
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 5, true);


-- Completed on 2015-04-25 16:58:39 CLST

--
-- PostgreSQL database dump complete
--


--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-04-14 18:07:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2973 (class 0 OID 50768)
-- Dependencies: 169
-- Data for Name: campana; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO campana VALUES (198, '2016-04-05', '2016-04-12', '2016-04-06', 247, 413, 'SEMINARIO SMARTCITY', 'Aprobado', 1, 0);
INSERT INTO campana VALUES (184, '2016-03-10', '2016-04-30', '2016-03-11', NULL, 399, 'PROMO REDES SOCIALES ', 'Aprobado', 1, 0);
INSERT INTO campana VALUES (193, '2016-03-23', '2016-03-28', '2016-03-24', NULL, 407, 'instituto', 'Aprobado', 67, 0);
INSERT INTO campana VALUES (199, '2016-04-12', '2016-04-16', '2016-04-12', NULL, 414, 'semana novata', 'Aprobado', 67, NULL);
INSERT INTO campana VALUES (187, '2016-03-10', '2016-03-25', '2016-03-11', 330, 402, 'h', 'Aprobado', 63, 0);
INSERT INTO campana VALUES (188, '2016-03-10', '2016-04-30', '2016-03-11', 247, 403, 'PROMO 2030 CURSOS PREGRADO ', 'Aprobado', 1, 0);
INSERT INTO campana VALUES (189, '2016-03-10', '2016-04-30', '2016-03-11', 247, 404, 'PROMO 2030 GENERAL 1', 'Aprobado', 1, 0);
INSERT INTO campana VALUES (191, '2016-03-15', '2016-04-16', '2016-03-16', 300, 405, 'Marzo', 'Aprobado', 66, 0);
INSERT INTO campana VALUES (192, '2016-03-22', '2016-04-08', '2016-03-23', 247, 406, 'BOOTCAMP', 'Aprobado', 1, 0);
INSERT INTO campana VALUES (194, '2016-03-30', '2016-04-08', '2016-04-01', 300, 408, 'Jgh', 'Esperando Aprobacion', 65, 4800);
INSERT INTO campana VALUES (195, '2016-03-31', '2016-04-07', '2016-04-01', 270, 409, 'diplomado arte ', 'Aprobado', 67, 0);
INSERT INTO campana VALUES (196, '2016-03-31', '2016-04-07', '2016-04-01', 270, 410, 'inauguración diplomado ', 'Aprobado', 67, 0);
INSERT INTO campana VALUES (197, '2016-03-31', '2016-04-18', '2016-04-01', 247, 411, 'IDEAS EMPRENDEDORAS', 'Aprobado', 1, 0);


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 170
-- Name: campana_idcampana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('campana_idcampana_seq', 199, true);


--
-- TOC entry 2975 (class 0 OID 50774)
-- Dependencies: 171
-- Data for Name: campatotem; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2976 (class 0 OID 50777)
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
INSERT INTO categoria VALUES (14, 'TIENDA');


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 173
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 14, true);


--
-- TOC entry 2978 (class 0 OID 50782)
-- Dependencies: 174
-- Data for Name: contenido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO contenido VALUES (381, '20151230.023832.jpg', 35, '2030 -1', 11, 'Validando');
INSERT INTO contenido VALUES (382, '20151230.023934.jpg', 35, '2030 -2', 11, 'Validando');
INSERT INTO contenido VALUES (383, '20151230.024016.jpg', 35, '2030 -3', 11, 'Validando');
INSERT INTO contenido VALUES (384, '20151230.024228.jpg', 35, '2030 -4', 11, 'Validando');
INSERT INTO contenido VALUES (385, '20151230.024437.jpg', 30, 'Interac -1', 13, 'Validando');
INSERT INTO contenido VALUES (386, '20151230.024524.jpg', 30, 'Interac -2', 13, 'Validando');
INSERT INTO contenido VALUES (387, '20151230.024625.jpg', 30, 'Quality', 13, 'Validando');
INSERT INTO contenido VALUES (389, '20160111.015444.jpg', 30, 'Anuncio Restaurant', 9, 'Validando');
INSERT INTO contenido VALUES (390, '20160115.082038.jpg', 2, 'nuevo', 11, 'Validando');
INSERT INTO contenido VALUES (391, '20160202.064337.jpg', 15, '1', 8, 'Validando');
INSERT INTO contenido VALUES (393, '20160210.063317.mp4', 37, 'Video2', 14, 'Validando');
INSERT INTO contenido VALUES (395, '20160216.044333.mp4', 37, 'campaña2', 14, 'Validando');
INSERT INTO contenido VALUES (396, '20160216.045422.mp4', 37, 'febrero2', 14, 'Validando');
INSERT INTO contenido VALUES (398, '20160308.093534.jpg', 35, 'TALLER SPIN OFFS', 11, 'Validando');
INSERT INTO contenido VALUES (399, '20160310.055303.png', 35, 'PROMO REDES SOCIALES ', 11, 'Validando');
INSERT INTO contenido VALUES (400, '20160310.060224.png', 35, 'PROMO INGENIERIA PUCV', 11, 'Validando');
INSERT INTO contenido VALUES (401, '20160310.070932.jpg', 2, 'histiria2', 11, 'Validando');
INSERT INTO contenido VALUES (402, '20160310.071126.jpg', 2, 'h', 11, 'Validando');
INSERT INTO contenido VALUES (403, '20160310.081042.png', 35, 'PROMO 2030 CURSOS PREGRADO ', 11, 'Validando');
INSERT INTO contenido VALUES (404, '20160310.081434.png', 35, 'PROMO 2030 GENERAL 1', 11, 'Validando');
INSERT INTO contenido VALUES (405, '20160315.064700.mp4', 37, 'Marzo', 14, 'Validando');
INSERT INTO contenido VALUES (406, '20160322.084706.jpg', 35, 'BOOTCAMP', 11, 'Validando');
INSERT INTO contenido VALUES (407, '20160323.020742.JPG', 39, 'instituto', 11, 'Validando');
INSERT INTO contenido VALUES (408, '20160330.103434.jpg', 2, 'Jgh', 3, 'Validando');
INSERT INTO contenido VALUES (409, '20160331.125030.jpg', 39, 'diplomado arte ', 11, 'Validando');
INSERT INTO contenido VALUES (410, '20160331.125240.jpg', 39, 'inauguración diplomado ', 11, 'Validando');
INSERT INTO contenido VALUES (411, '20160331.065303.jpg', 35, 'IDEAS EMPRENDEDORAS', 11, 'Validando');
INSERT INTO contenido VALUES (412, '20160405.035059.jpg', 35, 'SEMINARIO SMARTCITY', 11, 'Validando');
INSERT INTO contenido VALUES (413, '20160405.094325.jpg', 35, 'SEMINARIO SMARTCITY', 11, 'Validando');
INSERT INTO contenido VALUES (414, '20160412.013106.jpg', 39, 'semana novata', 11, 'Validando');


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 175
-- Name: contenido_idcontenido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contenido_idcontenido_seq', 414, true);


--
-- TOC entry 2980 (class 0 OID 50791)
-- Dependencies: 176
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO empresa VALUES ('sds', 'sdsd', '3434323', 'sdsasd', '3422', 2);
INSERT INTO empresa VALUES ('Interac', 'Interac Ltda.', '76.260.869-3', 'calle SUIZA 920', '0000000', 1);
INSERT INTO empresa VALUES ('PUCV', 'Pontificia Universidad Catolica de Valparaiso', '81.669.200-8', 'Av. Brasil 2950', '32-2273530', 3);
INSERT INTO empresa VALUES ('Oro Cash', 'Soluciones S.A.', '000000-0', 'Agustinas 1022 Of. 426', '(+562) 25976190', 4);


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 177
-- Name: empresa_idempresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_idempresa_seq', 4, true);


--
-- TOC entry 2982 (class 0 OID 50799)
-- Dependencies: 178
-- Data for Name: establecimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO establecimiento VALUES (67, 'Instituto Historia Pucv', '(56 32) 2274', 'Paseo Valle #396', 39, 3, -33.0266749999999973, -71.558242000000007, 2, '20:00:00', '08:00:00', 16, 1, 11, 'http://www.vriea.ucv.cl/wp-content/uploads/2012/06/Instituto-de-Historia-de-la-PUCV.jpg', 'historiaPucv', 'Activado', 3);
INSERT INTO establecimiento VALUES (43, 'Ripley', '5675675', 'Libertad 3232', 2, 3, -33.0086480000000009, -71.5485330000000062, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.mallmarinarauco.cl/wp-content/uploads/2015/01/ripley-23.jpg', 'demo2', NULL, 1);
INSERT INTO establecimiento VALUES (47, 'Paris', '7898678', 'Freire 767', 2, 4, -33.0439879999999988, -71.4202070000000049, 3, '20:00:00', '09:00:00', 16, 2, 10, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg/220px-Par%C3%ADs_-_Mall_Arauco_Maip%C3%BA.jpg', 'demoPublicidad', NULL, 1);
INSERT INTO establecimiento VALUES (48, 'Jumbo', '89889898', '1 norte 1022', 2, 3, -33.0355920000000012, -71.523809, 3, '20:00:00', '09:00:00', 14, 3, 10, 'https://waltsan.files.wordpress.com/2009/01/438840000_82f49016a8.jpg', 'demoPublicidad', NULL, 1);
INSERT INTO establecimiento VALUES (60, 'Starbuck''s', '23456789', '14 norte 12121', 2, 3, -33.0175070000000019, -71.5581229999999948, 2, '20:00:00', '09:00:00', 14, 3, 10, 'http://adm.1.cl/galeriasitios/Z33/2013/2/19/Z33__Fl-5203-Starbucks-Ff.jpg', 'demo2', NULL, 1);
INSERT INTO establecimiento VALUES (61, 'Nike Store', '678909876', 'avda. Brasil 8989', 2, 1, -33.0087139999999977, -71.5475820000000056, 2, '20:00:00', '09:00:00', 12, 3, 10, 'http://www.runclub.cl/site/wp-content/uploads/2013/10/307167_10151102841910413_1297851950_n1.jpg', 'demoPublicidad', NULL, 1);
INSERT INTO establecimiento VALUES (62, 'Hiper Lider', '786543232', 'Covadonga 2323', 2, 4, -33.0446669999999969, -71.4178560000000004, 2, '20:00:00', '09:00:00', 18, 2, 10, 'http://www.tecsa.cl/experiencia/retail/13.jpg', 'demo2', NULL, 1);
INSERT INTO establecimiento VALUES (63, 'Aiep', '65565', 'La Torre 12', 2, 3, -33.0270480000000006, -71.5490389999999934, 2, '20:00:00', '09:00:00', 12, 2, 10, 'http://neufert-cdn.archdaily.net.s3.amazonaws.com/uploads/photo/image/17429/large_1379380063-018.jpg', 'demo2', NULL, 1);
INSERT INTO establecimiento VALUES (64, 'lapizlopez', '2429820', 'portales 1234', 1, 4, 3333333, 3333333, 2, '22:00:00', '08:00:00', 12, 2, 8, 'http://www.portalcentro.cl/wp-content/uploads/2014/06/IMG_0500-710x375.jpg', 'demoPublicidad', NULL, 1);
INSERT INTO establecimiento VALUES (65, 'Inacap', '676767', 'av.España 1000', 1, 2, -370000, -730000, 2, '20:00:00', '10:00:00', 12, 2, 11, 'http://www.inacap.cl/tportal/portales/tp35f4fead3a341/uploadImg/Image/alumnosextranjeros.jpg', 'demoPublicidad', NULL, 1);
INSERT INTO establecimiento VALUES (1, 'PUCV Esc.Ingenieria', '989898989', 'avda. Brasil 2147', 35, 2, -33.0446683999999991, -71.613844400000005, 3, '20:00:00', '09:00:00', 16, 2, 11, 'http://eii.pucv.cl/wp-content/uploads/2008/04/escuela.jpg', 'publicidadPucv', NULL, 3);
INSERT INTO establecimiento VALUES (66, 'OroCash San Agustin', '(2)-25976190', 'San Antonio #144, L- 51', 37, 1, -33.4411899999999989, -70.6479309999999998, 2, '22:00:00', '12:30:00', 12, 1, 14, 'http://www.orocash.cl/dinamicos/sucursales/sucursal-rm-2.jpg', 'orocash', 'Activado', 4);


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 179
-- Name: establecimiento_idestablecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('establecimiento_idestablecimiento_seq', 66, true);


--
-- TOC entry 2984 (class 0 OID 50807)
-- Dependencies: 180
-- Data for Name: marcapantalla; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO marcapantalla VALUES (1, 'Samsung');
INSERT INTO marcapantalla VALUES (2, 'LG');
INSERT INTO marcapantalla VALUES (3, 'AOC');
INSERT INTO marcapantalla VALUES (4, 'Sony');
INSERT INTO marcapantalla VALUES (5, 'Phillips');
INSERT INTO marcapantalla VALUES (6, 'Otra');


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 181
-- Name: marcapantallas_idmarca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marcapantallas_idmarca_seq', 6, true);


--
-- TOC entry 2986 (class 0 OID 50815)
-- Dependencies: 182
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
-- TOC entry 2987 (class 0 OID 50818)
-- Dependencies: 183
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rol VALUES (1, 'anunciante');
INSERT INTO rol VALUES (2, 'cliente');
INSERT INTO rol VALUES (3, 'administrador');
INSERT INTO rol VALUES (4, 'invitado');
INSERT INTO rol VALUES (5, 'pucv');


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 184
-- Name: rol_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('rol_idrol_seq', 3, true);


--
-- TOC entry 2989 (class 0 OID 50823)
-- Dependencies: 185
-- Data for Name: tipototem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipototem VALUES (1, 'touch');
INSERT INTO tipototem VALUES (2, 'led');


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 186
-- Name: tipototem_idtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipototem_idtipo_seq', 2, true);


--
-- TOC entry 2991 (class 0 OID 50828)
-- Dependencies: 187
-- Data for Name: totem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO totem VALUES (204, '2323232', 1, 43, 10, 10, '32', 'Horizontal', NULL, 2, NULL, NULL);
INSERT INTO totem VALUES (203, '54321678', 1, 43, 0, 0, '42', 'Horizontal', NULL, 3, NULL, NULL);
INSERT INTO totem VALUES (202, '23243545', 1, 47, 1, 1, '42', 'Vertical', NULL, 3, NULL, NULL);
INSERT INTO totem VALUES (206, '6565656', 2, 47, -33.0439879999999988, -71.4202070000000049, '32', 'Vertical', NULL, 5, NULL, NULL);
INSERT INTO totem VALUES (207, '32323223', 2, 63, -33.0270480000000006, -71.5490389999999934, '55', 'Horizontal', NULL, 4, NULL, NULL);
INSERT INTO totem VALUES (200, 'PucvIBC', 1, 1, -33.044713999999999, -71.6124970000000047, '42', 'Vertical', NULL, 2, 'activa', NULL);
INSERT INTO totem VALUES (201, 'PUCVIngenieria', 1, 1, -33.0446683999999991, -71.613844400000005, '42', 'Vertical', NULL, 6, 'activa', NULL);


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 188
-- Name: totem_idtotem_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('totem_idtotem_seq', 207, true);


--
-- TOC entry 2993 (class 0 OID 50836)
-- Dependencies: 189
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO ubicacion VALUES (6, 'Villa Alemana');
INSERT INTO ubicacion VALUES (4, 'Quilpue');
INSERT INTO ubicacion VALUES (5, 'Limache');
INSERT INTO ubicacion VALUES (3, 'Viña del mar');
INSERT INTO ubicacion VALUES (2, 'Valparaiso');
INSERT INTO ubicacion VALUES (1, 'Santiago');


--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 190
-- Name: ubicacion_idubicacion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ubicacion_idubicacion_seq', 6, true);


--
-- TOC entry 2995 (class 0 OID 50841)
-- Dependencies: 191
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario VALUES (1, 'colivares', 'colivares', 'claudio.pol.olivares@gmail.com', 3, 1, NULL, NULL);
INSERT INTO usuario VALUES (2, 'jchacon', '1q2w3e4r', 'jchacon@interac.cl', 2, 1, NULL, NULL);
INSERT INTO usuario VALUES (10, 'ppablo', '1234', 'pe.pastene@gmail.com', 2, 1, NULL, NULL);
INSERT INTO usuario VALUES (15, 'jiu', 'jiu', 'jinostroza@interac.cl', 1, 1, NULL, NULL);
INSERT INTO usuario VALUES (35, 'pucv2030', '2030pucv', 'vanessa.varas@pucv.cl', 2, 3, NULL, NULL);
INSERT INTO usuario VALUES (37, 'orocash', 'orocash', 'contacto@orocash.cl', 2, 4, NULL, NULL);
INSERT INTO usuario VALUES (30, 'admin', 'admin', 'contacto@interac.cl', 3, 1, NULL, NULL);
INSERT INTO usuario VALUES (38, 'interac', '1q2w3e4r', 'joacoch@hotmail.cl', 4, NULL, 'joaco', 'chacon');
INSERT INTO usuario VALUES (39, 'historiapucv', 'pucvhistoria', 'carolinaibarrap@gmail.com', 2, 3, 'Carolina', 'Ibarra');


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 192
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 38, true);


-- Completed on 2016-04-14 18:08:06

--
-- PostgreSQL database dump complete
--


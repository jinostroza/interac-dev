CREATE TABLE campana
(
    idcampana SERIAL PRIMARY KEY NOT NULL,
    fechacreacion DATE,
    fechafin DATE,
    fechainicio DATE,
    pasadas INT,
    idtotem INT,
    idcontenido INT,
    nombrecampana VARCHAR(50)
);
CREATE TABLE campana_idcampana_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE categoria
(
    idcategoria SERIAL PRIMARY KEY NOT NULL,
    desccategoria VARCHAR(45)
);
CREATE TABLE categoria_idcategoria_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE contenido
(
    idcontenido SERIAL PRIMARY KEY NOT NULL,
    path VARCHAR(1000) NOT NULL,
    idusuario INT NOT NULL,
    nombrecont VARCHAR(50),
    idcategoria INT
);
CREATE TABLE contenido_idcontenido_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE establecimiento
(
    idestablecimiento SERIAL PRIMARY KEY NOT NULL,
    desestablecimiento VARCHAR(200) NOT NULL,
    fono VARCHAR(12),
    direccion VARCHAR(50),
    idusuario INT NOT NULL,
    idubicacion INT
);
CREATE TABLE establecimiento_idestablecimiento_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE rol
(
    id_rol SERIAL PRIMARY KEY NOT NULL,
    namerol VARCHAR(25) NOT NULL
);
CREATE TABLE rol_idrol_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE tipototem
(
    idtipo SERIAL PRIMARY KEY NOT NULL,
    destipo VARCHAR(50)
);
CREATE TABLE tipototem_idtipo_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE totem
(
    idtotem SERIAL PRIMARY KEY NOT NULL,
    noserie VARCHAR(45),
    idtipo INT NOT NULL,
    idestablecimiento INT NOT NULL,
    latitud DOUBLE PRECISION,
    longitud DOUBLE PRECISION
);
CREATE TABLE totem_idtotem_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE ubicacion
(
    idubicacion SERIAL PRIMARY KEY NOT NULL,
    descubicacion VARCHAR(45)
);
CREATE TABLE ubicacion_idubicacion_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
CREATE TABLE usuario
(
    idusuario SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(45),
    password VARCHAR(45),
    correo VARCHAR(45),
    empresa VARCHAR(45),
    idrol INT DEFAULT 4
);
CREATE TABLE usuario_idusuario_seq
(
    sequence_name VARCHAR NOT NULL,
    last_value BIGINT NOT NULL,
    start_value BIGINT NOT NULL,
    increment_by BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    min_value BIGINT NOT NULL,
    cache_value BIGINT NOT NULL,
    log_cnt BIGINT NOT NULL,
    is_cycled BOOL NOT NULL,
    is_called BOOL NOT NULL
);
ALTER TABLE campana ADD FOREIGN KEY (idcontenido) REFERENCES contenido (idcontenido);
ALTER TABLE campana ADD FOREIGN KEY (idtotem) REFERENCES totem (idtotem);
CREATE UNIQUE INDEX campana_pk ON campana (idcampana);
CREATE UNIQUE INDEX categoria_pk ON categoria (idcategoria);
ALTER TABLE contenido ADD FOREIGN KEY (idcategoria) REFERENCES categoria (idcategoria);
ALTER TABLE contenido ADD FOREIGN KEY (idusuario) REFERENCES usuario (idusuario);
CREATE UNIQUE INDEX unique_idcontenido ON contenido (idcontenido);
ALTER TABLE establecimiento ADD FOREIGN KEY (idubicacion) REFERENCES ubicacion (idubicacion);
ALTER TABLE establecimiento ADD FOREIGN KEY (idusuario) REFERENCES usuario (idusuario);
ALTER TABLE totem ADD FOREIGN KEY (idestablecimiento) REFERENCES establecimiento (idestablecimiento);
ALTER TABLE totem ADD FOREIGN KEY (idtipo) REFERENCES tipototem (idtipo);
CREATE UNIQUE INDEX totem_pk ON totem (idtotem);
CREATE UNIQUE INDEX ubicacion_pk ON ubicacion (idubicacion);
ALTER TABLE usuario ADD FOREIGN KEY (idrol) REFERENCES rol (id_rol);
ALTER TABLE usuario ADD FOREIGN KEY (idrol) REFERENCES rol (id_rol);
CREATE UNIQUE INDEX cliente_pk ON usuario (idusuario);

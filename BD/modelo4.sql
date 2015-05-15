/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     20-04-2015 16:49:41                          */
/*==============================================================*/


drop index CAMPANAANUNCIO_FK;

drop index ANUNCIOCATEGERIA_FK;

drop index ANUNCIO_PK;

drop table ANUNCIO;

drop index TOTEMCAMPANA_FK;

drop index CLIENTECAMPANA_FK;

drop index CAMPANA_PK;

drop table CAMPANA;

drop index CATEGORIA_PK;

drop table CATEGORIA;

drop index TOTEM_PK;

drop table TOTEM;

drop index TOTEMUBICACION_FK;

drop index UBICACION_PK;

drop table UBICACION;

drop index CLIENTE_PK;

drop table USUARIO;

/*==============================================================*/
/* Table: ANUNCIO                                               */
/*==============================================================*/
create table ANUNCIO (
   ID_ANUNCIO            SERIAL               not null,
   DESCANUNCIO          VARCHAR(45)          null,
   IDCAMPANA            INT4                 not null,
   IDCATEGORIA          INT4                 not null,
   MEDIA                CHAR(254)            null,
   RUBRO                VARCHAR(45)          null,
   constraint PK_ANUNCIO primary key (IDANUNCIO)
);

/*==============================================================*/
/* Index: ANUNCIO_PK                                            */
/*==============================================================*/
create unique index ANUNCIO_PK on ANUNCIO (
IDANUNCIO
);

/*==============================================================*/
/* Index: ANUNCIOCATEGERIA_FK                                   */
/*==============================================================*/
create  index ANUNCIOCATEGERIA_FK on ANUNCIO (
IDCATEGORIA
);

/*==============================================================*/
/* Index: CAMPANAANUNCIO_FK                                     */
/*==============================================================*/
create  index CAMPANAANUNCIO_FK on ANUNCIO (
IDCAMPANA
);

/*==============================================================*/
/* Table: CAMPANA                                               */
/*==============================================================*/
create table CAMPANA (
   IDCAMPANA            SERIAL               not null,
   IDCLIENTE            INT4                 not null,
   DESCCAMPANA          VARCHAR(45)          null,
   IDTOTEM              INT4                 not null,
   constraint PK_CAMPANA primary key (IDCAMPANA)
);

/*==============================================================*/
/* Index: CAMPANA_PK                                            */
/*==============================================================*/
create unique index CAMPANA_PK on CAMPANA (
IDCAMPANA
);

/*==============================================================*/
/* Index: CLIENTECAMPANA_FK                                     */
/*==============================================================*/
create  index CLIENTECAMPANA_FK on CAMPANA (
IDCLIENTE
);

/*==============================================================*/
/* Index: TOTEMCAMPANA_FK                                       */
/*==============================================================*/
create  index TOTEMCAMPANA_FK on CAMPANA (
IDTOTEM
);

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA (
   IDCATEGORIA          SERIAL               not null,
   DESCCATEGORIA        VARCHAR(45)          null,
   constraint PK_CATEGORIA primary key (IDCATEGORIA)
);

/*==============================================================*/
/* Index: CATEGORIA_PK                                          */
/*==============================================================*/
create unique index CATEGORIA_PK on CATEGORIA (
IDCATEGORIA
);

/*==============================================================*/
/* Table: TOTEM                                                 */
/*==============================================================*/
create table TOTEM (
   IDTOTEM              serial                 not null,
   NOMBRE               VARCHAR(45)          null,
   IDUBICACION          INT4                 null,
   TIPO                 VARCHAR(45)          null,
   constraint PK_TOTEM primary key (IDTOTEM)
);

/*==============================================================*/
/* Index: TOTEM_PK                                              */
/*==============================================================*/
create unique index TOTEM_PK on TOTEM (
IDTOTEM
);

/*==============================================================*/
/* Table: UBICACION                                             */
/*==============================================================*/
create table UBICACION (
   IDUBICACION          SERIAL               not null,
   IDTOTEM              INT4                 not null,
   DESCUBICACION        VARCHAR(45)          null,
   constraint PK_UBICACION primary key (IDUBICACION)
);

/*==============================================================*/
/* Index: UBICACION_PK                                          */
/*==============================================================*/
create unique index UBICACION_PK on UBICACION (
IDUBICACION
);

/*==============================================================*/
/* Index: TOTEMUBICACION_FK                                     */
/*==============================================================*/
create  index TOTEMUBICACION_FK on UBICACION (
IDTOTEM
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   IDUSUARIO            SERIAL               not null,
   USERNAME               VARCHAR(45)          null,
   PASSWORD             VARCHAR(45)          null,
   CORREO               VARCHAR(45)          null,
   EMPRESA              VARCHAR(45)          null,
   ROL                  VARCHAR(45)          null,
   constraint PK_USUARIO primary key (IDUSUARIO)
);

CREATE TABLE rol
(
    id_rol SERIAL PRIMARY KEY NOT NULL,
    namerol VARCHAR(25) NOT NULL
);


/*==============================================================*/
/* Index: CLIENTE_PK                                            */
/*==============================================================*/
create unique index CLIENTE_PK on USUARIO (
IDUSUARIO
);

alter table ANUNCIO
   add constraint FK_ANUNCIO_ANUNCIOCA_CATEGORI foreign key (IDCATEGORIA)
      references CATEGORIA (IDCATEGORIA)
      on delete restrict on update restrict;

alter table ANUNCIO
   add constraint FK_ANUNCIO_CAMPANAAN_CAMPANA foreign key (IDCAMPANA)
      references CAMPANA (IDCAMPANA)
      on delete restrict on update restrict;

alter table CAMPANA
   add constraint FK_CAMPANA_CLIENTECA_USUARIO foreign key (IDCLIENTE)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

alter table CAMPANA
   add constraint FK_CAMPANA_TOTEMCAMP_TOTEM foreign key (IDTOTEM)
      references TOTEM (IDTOTEM)
      on delete restrict on update restrict;

alter table UBICACION
   add constraint FK_UBICACIO_TOTEMUBIC_TOTEM foreign key (IDTOTEM)
      references TOTEM (IDTOTEM)
      on delete restrict on update restrict;

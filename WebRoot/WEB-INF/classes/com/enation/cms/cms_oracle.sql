/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2011-11-4 19:18:19                           */
/*==============================================================*/


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_DATA_CAT cascade constraints';
	EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_DATA_FIELD cascade constraints';
	EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_DATA_MODEL cascade constraints';
	EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_DATA_CAT';
	EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_DATA_FIELD';
	EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_DATA_MODEL';
	EXCEPTION WHEN OTHERS THEN NULL;
END;
go

create sequence S_ES_DATA_CAT
go

create sequence S_ES_DATA_FIELD
go

create sequence S_ES_DATA_MODEL
go

/*==============================================================*/
/* Table: ES_DATA_CAT                                           */
/*==============================================================*/
create table ES_DATA_CAT  (
   CAT_ID               NUMBER(6)                       not null,
   NAME                 VARCHAR2(255),
   PARENT_ID            INTEGER,
   CAT_PATH             VARCHAR2(255),
   CAT_ORDER            SMALLINT,
   MODEL_ID             INTEGER,
   IF_AUDIT             INTEGER,
   URL                  VARCHAR2(255),
   DETAIL_URL           VARCHAR2(255),
   TOSITEMAP            SMALLINT                       default 0,
   constraint PK_ES_DATA_CAT primary key (CAT_ID)
)
go

/*==============================================================*/
/* Table: ES_DATA_FIELD                                         */
/*==============================================================*/
create table ES_DATA_FIELD  (
   FIELD_ID             NUMBER(6)                       not null,
   CHINA_NAME           VARCHAR2(255),
   ENGLISH_NAME         VARCHAR2(255),
   DATA_TYPE            INTEGER,
   DATA_SIZE            VARCHAR2(20),
   SHOW_FORM            VARCHAR2(255),
   SHOW_VALUE           VARCHAR2(400),
   ADD_TIME             INTEGER,
   MODEL_ID             INTEGER,
   SAVE_VALUE           CLOB,
   IS_VALIDATE          SMALLINT,
   TAXIS                INTEGER,
   DICT_ID              INTEGER,
   IS_SHOW              SMALLINT
)
go

/*==============================================================*/
/* Table: ES_DATA_MODEL                                         */
/*==============================================================*/
create table ES_DATA_MODEL  (
   MODEL_ID             NUMBER(6)                       not null,
   CHINA_NAME           VARCHAR2(255),
   ENGLISH_NAME         VARCHAR2(255),
   ADD_TIME             INTEGER,
   PROJECT_NAME         VARCHAR2(255),
   BRIEF                VARCHAR2(400),
   IF_AUDIT             INTEGER
)
go

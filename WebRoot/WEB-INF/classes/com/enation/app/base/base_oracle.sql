/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2011-11-4 17:46:08                           */
/*==============================================================*/


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_WIDGETBUNDLE cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_USER_ROLE cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_THEMEURI cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_THEME cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_SITE_MENU cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_SITE cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_SHORT_MSG cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_SETTINGS cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_ROLE_AUTH cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_ROLE cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_MENU cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_INDEX_ITEM cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_GUESTBOOK cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_FRIENDS_LINK cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_BORDER cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_AUTH_ACTION cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_ADV cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_ADMINUSER cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_ADMINTHEME cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_ADCOLUMN cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ES_ACCESS cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_REPLY cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_DATA_LOG cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_ASK cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_ASK';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_DATA_LOG';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_REPLY';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_ACCESS';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_ADCOLUMN';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_ADMINTHEME';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_ADMINUSER';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_ADV';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_AUTH_ACTION';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_BORDER';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_FRIENDS_LINK';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_GUESTBOOK';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_INDEX_ITEM';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_MENU';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_ROLE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_ROLE_AUTH';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_SETTINGS';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_SHORT_MSG';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_SITE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_SITE_MENU';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_THEME';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_THEMEURI';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_USER_ROLE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_ES_WIDGETBUNDLE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_ASK';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_DATA_LOG';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_REPLY';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_ACCESS';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_ADCOLUMN';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_ADMINTHEME';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_ADMINUSER';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_ADV';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_AUTH_ACTION';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_BORDER';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_FRIENDS_LINK';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_GUESTBOOK';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_INDEX_ITEM';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_MENU';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_ROLE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_ROLE_AUTH';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_SETTINGS';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_SHORT_MSG';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_SITE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_SITE_MENU';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_THEME';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_THEMEURI';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_USER_ROLE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_ES_WIDGETBUNDLE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

create sequence S_EOP_ASK
go

create sequence S_EOP_DATA_LOG
go

create sequence S_EOP_REPLY
go

create sequence S_ES_ACCESS
go

create sequence S_ES_ADCOLUMN
go

create sequence S_ES_ADMINTHEME
go

create sequence S_ES_ADMINUSER
go

create sequence S_ES_ADV
go

create sequence S_ES_AUTH_ACTION
go

create sequence S_ES_BORDER
go

create sequence S_ES_FRIENDS_LINK
go

create sequence S_ES_GUESTBOOK
go

create sequence S_ES_INDEX_ITEM
go

create sequence S_ES_MENU
go

create sequence S_ES_ROLE
go

create sequence S_ES_ROLE_AUTH
go

create sequence S_ES_SETTINGS
go

create sequence S_ES_SHORT_MSG
go

create sequence S_ES_SITE
go

create sequence S_ES_SITE_MENU
go

create sequence S_ES_THEME
go

create sequence S_ES_THEMEURI
go

create sequence S_ES_USER_ROLE
go

create sequence S_ES_WIDGETBUNDLE
go

/*==============================================================*/
/* Table: EOP_ASK                                               */
/*==============================================================*/
create table EOP_ASK  (
   ASKID                NUMBER(6)                       not null,
   TITLE                VARCHAR2(255),
   CONTENT              CLOB,
   DATELINE             INTEGER,
   ISREPLY              SMALLINT,
   USERID               INTEGER,
   SITEID               INTEGER,
   DOMAIN               VARCHAR2(255),
   SITENAME             VARCHAR2(255),
   USERNAME             VARCHAR2(255),
   constraint PK_EOP_ASK primary key (ASKID)
)
go

/*==============================================================*/
/* Table: EOP_DATA_LOG                                          */
/*==============================================================*/
create table EOP_DATA_LOG  (
   ID                   NUMBER(6)                       not null,
   CONTENT              CLOB,
   URL                  VARCHAR2(255),
   PICS                 CLOB,
   SITENAME             VARCHAR2(255),
   DOMAIN               VARCHAR2(255),
   LOGTYPE              VARCHAR2(50),
   OPTYPE               VARCHAR2(50),
   DATELINE             INTEGER,
   USERID               INTEGER,
   SITEID               INTEGER,
   constraint PK_EOP_DATA_LOG primary key (ID)
)
go

/*==============================================================*/
/* Table: EOP_REPLY                                             */
/*==============================================================*/
create table EOP_REPLY  (
   REPLYID              NUMBER(6)                       not null,
   ASKID                INTEGER,
   CONTENT              CLOB,
   USERNAME             VARCHAR2(255),
   DATELINE             INTEGER,
   constraint PK_EOP_REPLY primary key (REPLYID)
)
go
/*==============================================================*/
/* Table: ES_ACCESS                                             */
/*==============================================================*/
create table ES_ACCESS  (
   ID                   NUMBER(6)                       not null,
   IP                   VARCHAR2(255),
   URL                  VARCHAR2(255),
   PAGE                 VARCHAR2(255),
   AREA                 VARCHAR2(255),
   ACCESS_TIME          INTEGER,
   STAY_TIME            INTEGER,
   POINT                INTEGER                        default 0,
   MEMBERNAME           VARCHAR2(255)                  default '0',
   constraint PK_ES_ACCESS primary key (ID)
)
go

/*==============================================================*/
/* Index: INDEX_IP                                              */
/*==============================================================*/
create index INDEX_IP on ES_ACCESS (
   IP ASC
)
go

/*==============================================================*/
/* Index: INDEX_URL                                             */
/*==============================================================*/
create index INDEX_URL on ES_ACCESS (
   URL ASC
)
go

/*==============================================================*/
/* Table: ES_ADCOLUMN                                           */
/*==============================================================*/
create table ES_ADCOLUMN  (
   ACID                 NUMBER(6)                       not null,
   CNAME                VARCHAR2(255),
   WIDTH                VARCHAR2(50),
   HEIGHT               VARCHAR2(50),
   DESCRIPTION          VARCHAR2(255),
   ANUMBER              INTEGER,
   ATYPE                INTEGER,
   RULE                 INTEGER,
   DISABLED             INTEGER  				        default 0,
   constraint PK_ES_ADCOLUMN primary key (ACID)
)
go

/*==============================================================*/
/* Table: ES_ADMINUSER                                          */
/*==============================================================*/
create table ES_ADMINUSER  (
   USERID               NUMBER(6)                       not null,
   USERNAME             VARCHAR2(255),
   PASSWORD             VARCHAR2(255),
   STATE                SMALLINT,
   REALNAME             VARCHAR2(255),
   USERNO               VARCHAR2(255),
   USERDEPT             VARCHAR2(255),
   REMARK               VARCHAR2(255),
   DATELINE             INTEGER,
   FOUNDER              SMALLINT,
   SITEID               INTEGER,
   constraint PK_ES_ADMINUSER primary key (USERID)
)
go

/*==============================================================*/
/* Table: ES_ADV                                                */
/*==============================================================*/
create table ES_ADV  (
   AID                  NUMBER(6)                       not null,
   ACID                 INTEGER,
   ATYPE                INTEGER,
   BEGINTIME            INTEGER,
   ENDTIME              INTEGER,
   ISCLOSE              INTEGER,
   ATTACHMENT           VARCHAR2(50),
   ATTURL               VARCHAR2(255),
   URL                  VARCHAR2(255),
   ANAME                VARCHAR2(255),
   CLICKCOUNT           INTEGER                        default 0,
   LINKMAN              VARCHAR2(50),
   COMPANY              VARCHAR2(255),
   CONTACT              VARCHAR2(255),
   DISABLED             INTEGER                        default 0,
   constraint PK_ES_ADV primary key (AID)
)
go

/*==============================================================*/
/* Table: ES_AUTH_ACTION                                        */
/*==============================================================*/
create table ES_AUTH_ACTION  (
   ACTID                NUMBER(6)                       not null,
   NAME                 VARCHAR2(255),
   TYPE                 VARCHAR2(255),
   OBJVALUE             CLOB
)
go

/*==============================================================*/
/* Table: ES_BORDER                                             */
/*==============================================================*/
create table ES_BORDER  (
   ID                   NUMBER(6)                       not null,
   BORDERID             VARCHAR2(50),
   BORDERNAME           VARCHAR2(50),
   THEMEPATH            VARCHAR2(50),
   DELETEFLAG           SMALLINT                       default 0,
   constraint PK_ES_BORDER primary key (ID)
)
go


/*==============================================================*/
/* Table: ES_FRIENDS_LINK                                       */
/*==============================================================*/
create table ES_FRIENDS_LINK  (
   LINK_ID              NUMBER(6)                       not null,
   NAME                 VARCHAR2(100),
   URL                  VARCHAR2(100),
   LOGO                 VARCHAR2(255),
   SORT                 SMALLINT
)
go

/*==============================================================*/
/* Table: ES_GUESTBOOK                                          */
/*==============================================================*/
create table ES_GUESTBOOK  (
   ID                   NUMBER(6)                       not null,
   TITLE                VARCHAR2(255),
   CONTENT              CLOB,
   PARENTID             INTEGER,
   DATELINE             INTEGER,
   ISSUBJECT            SMALLINT,
   USERNAME             VARCHAR2(255),
   EMAIL                VARCHAR2(255),
   QQ                   VARCHAR2(255),
   TEL                  VARCHAR2(255),
   SEX                  SMALLINT,
   IP                   VARCHAR2(255),
   AREA                 VARCHAR2(255),
   constraint PK_ES_GUESTBOOK primary key (ID)
)
go

/*==============================================================*/
/* Table: ES_INDEX_ITEM                                         */
/*==============================================================*/
create table ES_INDEX_ITEM  (
   ID                   NUMBER(6)                       not null,
   TITLE                VARCHAR2(255),
   URL                  VARCHAR2(255),
   SORT                 INTEGER
)
go

/*==============================================================*/
/* Table: ES_MENU                                               */
/*==============================================================*/
create table ES_MENU  (
   ID                   NUMBER(6)                       not null,
   APPID                VARCHAR2(50),
   PID                  INTEGER,
   TITLE                VARCHAR2(50),
   URL                  VARCHAR2(255),
   TARGET               VARCHAR2(255),
   SORDER               INTEGER                        default 50,
   MENUTYPE             INTEGER,
   DATATYPE             VARCHAR2(50),
   SELECTED             SMALLINT                       default 0,
   DELETEFLAG           SMALLINT                       default 0,
   constraint PK_ES_MENU primary key (ID)
)
go

/*==============================================================*/
/* Table: ES_ROLE                                               */
/*==============================================================*/
create table ES_ROLE  (
   ROLEID               NUMBER(6)                       not null,
   ROLENAME             VARCHAR2(255),
   ROLEMEMO             VARCHAR2(255),
   constraint PK_ES_ROLE primary key (ROLEID)
)
go

/*==============================================================*/
/* Table: ES_ROLE_AUTH                                          */
/*==============================================================*/
create table ES_ROLE_AUTH  (
   ID                   NUMBER(6)                       not null,
   ROLEID               INTEGER,
   AUTHID               INTEGER,
   constraint PK_ES_ROLE_AUTH primary key (ID)
)
go

/*==============================================================*/
/* Table: ES_SETTINGS                                           */
/*==============================================================*/
create table ES_SETTINGS  (
   ID                   NUMBER(6)                       not null,
   CODE                 VARCHAR2(50),
   CFG_VALUE            VARCHAR2(255),
   CFG_GROUP            VARCHAR2(50),
   constraint PK_ES_SETTINGS primary key (ID)
)
go

/*==============================================================*/
/* Table: ES_SHORT_MSG                                          */
/*==============================================================*/
create table ES_SHORT_MSG  (
   MSGID                NUMBER(6)                       not null,
   USERID               INTEGER,
   TITLE                VARCHAR2(255),
   CONTENT              VARCHAR2(255),
   URL                  VARCHAR2(255),
   ISREAD               SMALLINT,
   DATELINE             INTEGER,
   TARGET               VARCHAR2(255),
   constraint PK_ES_SHORT_MSG primary key (MSGID)
)
go

/*==============================================================*/
/* Index: ES_SHORT_MUID                                         */
/*==============================================================*/
create index ES_SHORT_MUID on ES_SHORT_MSG (
   USERID ASC
)
go

/*==============================================================*/
/* Table: ES_SITE                                               */
/*==============================================================*/
create table ES_SITE  (
   SITEID               NUMBER(6)                       not null,
   PARENTID             INTEGER,
   CODE                 INTEGER,
   NAME                 VARCHAR2(255),
   DOMAIN               VARCHAR2(255),
   THEMEID              INTEGER,
   "LEVEL"              SMALLINT,
   constraint PK_ES_SITE primary key (SITEID)
)
go

/*==============================================================*/
/* Table: ES_SITE_MENU                                          */
/*==============================================================*/
create table ES_SITE_MENU  (
   MENUID               NUMBER(6)                       not null,
   PARENTID             INTEGER,
   NAME                 VARCHAR2(50),
   URL                  VARCHAR2(255),
   TARGET               VARCHAR2(50),
   SORT                 INTEGER,
   constraint PK_ES_SITE_MENU primary key (MENUID)
)
go

/*==============================================================*/
/* Table: ES_THEME                                              */
/*==============================================================*/
create table ES_THEME  (
   ID                   NUMBER(6)                       not null,
   APPID                VARCHAR2(50),
   THEMENAME            VARCHAR2(50),
   PATH                 VARCHAR2(255),
   AUTHOR               VARCHAR2(50),
   VERSION              VARCHAR2(50),
   DELETEFLAG           SMALLINT                       default 0,
   THUMB                VARCHAR2(50),
   SITEID               INTEGER,
   constraint PK_ES_THEME primary key (ID)
)
go

/*==============================================================*/
/* Table: ES_THEMEURI                                           */
/*==============================================================*/
create table ES_THEMEURI  (
   ID                   NUMBER(6)                       not null,
   THEMEID              INTEGER,
   URI                  VARCHAR2(255),
   PATH                 VARCHAR2(255),
   DELETEFLAG           SMALLINT                       default 0,
   PAGENAME             VARCHAR2(255),
   POINT                INTEGER,
   SITEMAPTYPE          INTEGER                        default 0,
   KEYWORDS             VARCHAR2(255),
   DESCRIPTION          CLOB,
   constraint PK_ES_THEMEURI primary key (ID)
)
go

/*==============================================================*/
/* Table: ES_USER_ROLE                                          */
/*==============================================================*/
create table ES_USER_ROLE  (
   ID                   NUMBER(6)                       not null,
   USERID               INTEGER,
   ROLEID               INTEGER,
   constraint PK_ES_USER_ROLE primary key (ID)
)
go

/*==============================================================*/
/* Table: ES_WIDGETBUNDLE                                       */
/*==============================================================*/
create table ES_WIDGETBUNDLE  (
   ID                   NUMBER(6)                       not null,
   WIDGETNAME           VARCHAR2(50),
   WIDGETTYPE           VARCHAR2(50),
   SETTINGURL           VARCHAR2(255),
   DELETEFLAG           SMALLINT                       default 0
)
go
/*==============================================================*/
/* Table: es_admintheme                                         */
/*==============================================================*/
create table ES_ADMINTHEME
(
   ID                   NUMBER(6)                       not null,
   APPID                VARCHAR2(50),
   SITEID               INTEGER,
   THEMENAME            VARCHAR2(50),
   PATH                 VARCHAR2(255),
   USERID               INTEGER,
   AUTHOR               VARCHAR2(50),
   VERSION              VARCHAR2(50),
   FRAMEMODE            INTEGER default 0,
   DELETEFLAG           INTEGER default 0,
   THUMB                VARCHAR2(50),
   constraint PK_ES_ADMINTHEME primary key (ID)
)
go

CREATE TRIGGER "TIB_EOP_ASK" BEFORE INSERT
ON EOP_ASK FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_EOP_ASK.NEXTVAL INTO :NEW.ASKID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_EOP_DATA_LOG" BEFORE INSERT
ON EOP_DATA_LOG FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_EOP_DATA_LOG.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_EOP_REPLY" BEFORE INSERT
ON EOP_REPLY FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_EOP_REPLY.NEXTVAL INTO :NEW.REPLYID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_ACCESS" BEFORE INSERT
ON ES_ACCESS FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_ACCESS.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_ADCOLUMN" BEFORE INSERT
ON ES_ADCOLUMN FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_ADCOLUMN.NEXTVAL INTO :NEW.ACID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_ADMINTHEME" BEFORE INSERT
ON ES_ADMINTHEME FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_ADMINTHEME.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_ADMINUSER" BEFORE INSERT
ON ES_ADMINUSER FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_ADMINUSER.NEXTVAL INTO :NEW.USERID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_ADV" BEFORE INSERT
ON ES_ADV FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_ADV.NEXTVAL INTO :NEW.AID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_AUTH_ACTION" BEFORE INSERT
ON ES_AUTH_ACTION FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_AUTH_ACTION.NEXTVAL INTO :NEW.ACTID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_BORDER" BEFORE INSERT
ON ES_BORDER FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_BORDER.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_FRIENDS_LINK" BEFORE INSERT
ON ES_FRIENDS_LINK FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_FRIENDS_LINK.NEXTVAL INTO :NEW.LINK_ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_GUESTBOOK" BEFORE INSERT
ON ES_GUESTBOOK FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_GUESTBOOK.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_INDEX_ITEM" BEFORE INSERT
ON ES_INDEX_ITEM FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_INDEX_ITEM.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_MENU" BEFORE INSERT
ON ES_MENU FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_MENU.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_ROLE" BEFORE INSERT
ON ES_ROLE FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_ROLE.NEXTVAL INTO :NEW.ROLEID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_ROLE_AUTH" BEFORE INSERT
ON ES_ROLE_AUTH FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_ROLE_AUTH.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_SETTINGS" BEFORE INSERT
ON ES_SETTINGS FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_SETTINGS.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_SHORT_MSG" BEFORE INSERT
ON ES_SHORT_MSG FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_SHORT_MSG.NEXTVAL INTO :NEW.MSGID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_SITE" BEFORE INSERT
ON ES_SITE FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_SITE.NEXTVAL INTO :NEW.SITEID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_SITE_MENU" BEFORE INSERT
ON ES_SITE_MENU FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_SITE_MENU.NEXTVAL INTO :NEW.MENUID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_THEME" BEFORE INSERT
ON ES_THEME FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_THEME.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_THEMEURI" BEFORE INSERT
ON ES_THEMEURI FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_THEMEURI.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_USER_ROLE" BEFORE INSERT
ON ES_USER_ROLE FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_USER_ROLE.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_ES_WIDGETBUNDLE" BEFORE INSERT
ON ES_WIDGETBUNDLE FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_WIDGETBUNDLE.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go
/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2011-11-3 17:32:09                           */
/*==============================================================*/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_APP cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_SITE cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_SITEDOMAIN cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_USER cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EOP_USERDETAIL cascade constraints';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_APP';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_SITE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_SITEDOMAIN';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_USER';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop sequence S_EOP_USERDETAIL';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_APP';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_SITE';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_SITEDOMAIN';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_USER';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

BEGIN
    EXECUTE IMMEDIATE 'drop trigger TIB_EOP_USERDETAIL';
    EXCEPTION WHEN OTHERS THEN NULL;
END;
go

create sequence S_EOP_APP
go

create sequence S_EOP_SITE
go

create sequence S_EOP_SITEDOMAIN
go

create sequence S_EOP_USER
go

create sequence S_EOP_USERDETAIL
go

/*==============================================================*/
/* Table: EOP_APP                                               */
/*==============================================================*/
create table EOP_APP  (
   ID                   NUMBER(6)                       not null,
   APPID                VARCHAR2(50),
   APP_NAME             VARCHAR2(50),
   AUTHOR               VARCHAR2(50),
   DESCRIPT             CLOB,
   DEPLOYMENT           INTEGER                        default 1,
   PATH                 VARCHAR2(255),
   AUTHCODE             VARCHAR2(50),
   INSTALLURI           VARCHAR2(255),
   DELETEFLAG           SMALLINT                       default 0,
   VERSION              VARCHAR2(255)
)
go

/*==============================================================*/
/* Table: EOP_SITE                                              */
/*==============================================================*/
create table EOP_SITE  (
   ID                   NUMBER(6)                       not null,
   USERID				INTEGER,
   SITENAME             VARCHAR2(255),
   PRODUCTID            VARCHAR2(50),
   DESCRIPT             CLOB,
   ICOFILE              VARCHAR2(255),
   LOGOFILE             VARCHAR2(255),
   DELETEFLAG           SMALLINT                       default 0,
   KEYWORDS             VARCHAR2(255),
   THEMEPATH            VARCHAR2(50),
   ADMINTHEMEID         INTEGER,
   THEMEID              INTEGER,
   POINT                INTEGER                        default 0,
   CREATETIME           INTEGER,
   LASTLOGIN            INTEGER,
   LASTGETPOINT         INTEGER,
   LOGINCOUNT           INTEGER,
   BKLOGINPICFILE       VARCHAR2(255),
   BKLOGOFILE           VARCHAR2(255),
   SUMPOINT             INTEGER                        default 0,
   SUMACCESS            INTEGER                        default 0,
   TITLE                VARCHAR2(255),
   USERNAME             VARCHAR2(255),
   USERSEX              INTEGER,
   USERTEL              VARCHAR2(50),
   USERMOBILE           VARCHAR2(50),
   USERTEL1             VARCHAR2(50),
   USEREMAIL            VARCHAR2(50),
   STATE                SMALLINT                       default 1,
   QQLIST               VARCHAR2(255)                  default '25106942:客户服务,52560956:技术支持',
   MSNLIST              VARCHAR2(255),
   WWLIST               VARCHAR2(255),
   TELLIST              VARCHAR2(255),
   WORKTIME             VARCHAR2(255)                  default '9:00到18:00',
   SITEON               SMALLINT                       default 0,
   CLOSERESON           VARCHAR2(255),
   COPYRIGHT            VARCHAR2(255)                 default 'Copyright &copy; 2010-2012 本公司版权所有',
   ICP                  VARCHAR2(255)                  default '京ICP备05037293号',
   ADDRESS              VARCHAR2(255)                  default '北京市某区某街某号',
   ZIPCODE              VARCHAR2(50)                   default '000000',
   QQ                   INTEGER                        default 1,
   MSN                  INTEGER                        default 0,
   WW                   INTEGER                        default 0,
   TEL                  INTEGER                        default 0,
   WT                   INTEGER                        default 1,
   LINKMAN              VARCHAR2(255)                  default '刘先生',
   LINKTEL              VARCHAR2(255)                  default '010-61750491',
   EMAIL                VARCHAR2(255)                  default 'enation@126.com',
   MULTI_SITE           SMALLINT                       default 0,
   RELID                VARCHAR2(255),
   SITESTATE            SMALLINT                       default 0,
   ISIMPORTED           SMALLINT                       default 0,
   IMPTYPE              SMALLINT                       default 0
)
go

/*==============================================================*/
/* Table: EOP_SITEDOMAIN                                        */
/*==============================================================*/
create table EOP_SITEDOMAIN  (
   ID                   NUMBER(6)                       not null,
   DOMAIN               VARCHAR2(255),
   DOMAINTYPE           SMALLINT                       default 0,
   SITEID				INTEGER,
   USERID               INTEGER,
   STATUS               SMALLINT                       default 0
)
go


/*==============================================================*/
/* Table: EOP_USER                                              */
/*==============================================================*/
create table EOP_USER  (
   ID                   NUMBER(6)                       not null,
   USERNAME             VARCHAR2(50),
   COMPANYNAME          VARCHAR2(50),
   PASSWORD             VARCHAR2(50),
   ADDRESS              VARCHAR2(255),
   LEGALPERSON          VARCHAR2(50),
   LINKMAN              VARCHAR2(50),
   TEL                  VARCHAR2(50),
   MOBILE               VARCHAR2(50),
   EMAIL                VARCHAR2(50),
   LOGOFILE             VARCHAR2(255),
   LICENSEFILE          VARCHAR2(255),
   DEFAULTSITEID        INTEGER,
   DELETEFLAG           SMALLINT                       default 0,
   USERTYPE             SMALLINT,
   CREATETIME           INTEGER
)
go

/*==============================================================*/
/* Table: EOP_USERDETAIL                                        */
/*==============================================================*/
create table EOP_USERDETAIL  (
   ID                   NUMBER(6)                       not null,
   BUSSINESSSCOPE       CLOB,
   REGADDRESS           VARCHAR2(255),
   REGDATE              INTEGER,
   CORPSCOPE            SMALLINT                       default 0,
   CORPDESCRIPT         CLOB
)
go

CREATE TRIGGER "TIB_EOP_APP" BEFORE INSERT
ON EOP_APP FOR EACH ROW
DECLARE
    INTEGRITY_ERROR  EXCEPTION;
    ERRNO            INTEGER;
    ERRMSG           CHAR(200);
    DUMMY            INTEGER;
    FOUND            BOOLEAN;
BEGIN
    SELECT S_EOP_APP.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
    WHEN INTEGRITY_ERROR THEN
       RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_EOP_SITE" BEFORE INSERT
ON EOP_SITE FOR EACH ROW
DECLARE
    INTEGRITY_ERROR  EXCEPTION;
    ERRNO            INTEGER;
    ERRMSG           CHAR(200);
    DUMMY            INTEGER;
    FOUND            BOOLEAN;
BEGIN
    SELECT S_EOP_SITE.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
    WHEN INTEGRITY_ERROR THEN
       RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_EOP_SITEDOMAIN" BEFORE INSERT
ON EOP_SITEDOMAIN FOR EACH ROW
DECLARE
    INTEGRITY_ERROR  EXCEPTION;
    ERRNO            INTEGER;
    ERRMSG           CHAR(200);
    DUMMY            INTEGER;
    FOUND            BOOLEAN;
BEGIN
    SELECT S_EOP_SITEDOMAIN.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
    WHEN INTEGRITY_ERROR THEN
       RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_EOP_USER" BEFORE INSERT
ON EOP_USER FOR EACH ROW
DECLARE
    INTEGRITY_ERROR  EXCEPTION;
    ERRNO            INTEGER;
    ERRMSG           CHAR(200);
    DUMMY            INTEGER;
    FOUND            BOOLEAN;
BEGIN
    SELECT S_EOP_USER.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
    WHEN INTEGRITY_ERROR THEN
       RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

CREATE TRIGGER "TIB_EOP_USERDETAIL" BEFORE INSERT
ON EOP_USERDETAIL FOR EACH ROW
DECLARE
    INTEGRITY_ERROR  EXCEPTION;
    ERRNO            INTEGER;
    ERRMSG           CHAR(200);
    DUMMY            INTEGER;
    FOUND            BOOLEAN;
BEGIN
    SELECT S_EOP_USERDETAIL.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
    WHEN INTEGRITY_ERROR THEN
       RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go
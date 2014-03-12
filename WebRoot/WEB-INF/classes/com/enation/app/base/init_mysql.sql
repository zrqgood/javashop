drop table if exists eop_app;

drop table if exists eop_site;

drop table if exists eop_sitedomain;

drop table if exists eop_user;

drop table if exists eop_userdetail;

/*==============================================================*/
/* Table: eop_app                                               */
/*==============================================================*/
create table eop_app
(
   id                   int not null auto_increment,
   appid                varchar(50),
   app_name             varchar(50),
   author               varchar(50),
   descript             Text,
   deployment           int default 1 comment '0:本地；1：远程',
   path                 varchar(255) comment '对本地是目录，对远程是地址',
   authorizationcode    varchar(50),
   installuri           varchar(255),
   deleteflag           smallint default 0,
   version              varchar(255),
   primary key (id)
);
create table eop_site
(
   id                   int not null auto_increment,
   userid               int,
   sitename             varchar(255),
   productid            varchar(50),
   descript             text,
   icofile              varchar(255),
   logofile             varchar(255),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   keywords             varchar(255),
   themepath            varchar(50),
   adminthemeid         int,
   themeid              int,
   point                int default 0 comment '-1为不限制访问',
   createtime           bigint,
   lastlogin            bigint,
   lastgetpoint         bigint,
   logincount           int,
   bkloginpicfile       varchar(255),
   bklogofile           varchar(255),
   sumpoint             bigint default 0,
   sumaccess            bigint default 0,
   title                varchar(255),
   username             varchar(255),
   usersex              int,
   usertel              varchar(50),
   usermobile            varchar(50),
   usertel1              varchar(50),
   useremail             varchar(50),
   state                smallint default 1 comment '0: 关闭;1:开启
            ',
   qqlist               varchar(255) default '25106942:客户服务,52560956:技术支持',
   msnlist              varchar(255),
   wwlist               varchar(255),
   tellist              varchar(255),
   worktime             varchar(255) default '9:00到18:00',
   siteon               smallint default 0 comment '0开启;1关闭',
   closereson           varchar(255),
   copyright            varchar(8000) default 'Copyright &copy; 2010-2012 本公司版权所有',
   icp                  varchar(255) default '京ICP备05037293号',
   address              varchar(255) default '北京市某区某街某号',
   zipcode               varchar(50) default '000000',
   qq                   int default 1 comment '0:not show;1:show',
   msn                  int default 0 comment '0:not show;1:show',
   ww                   int default 0 comment '0:not show;1:show',
   tel                  int default 0 comment '0:not show;1:show',
   wt                   int default 1 comment '0:not show;1:show',
   linkman              varchar(255) default '刘先生',
   linktel              varchar(255) default '010-61750491',
   email                varchar(255) default 'enation@126.com',
   multi_site           smallint(1) default 0 comment '0未开启
            1开启',
   relid                varchar(255),
   sitestate            smallint(1) default 0 comment '0正在试用1已开通',
   isimported           smallint(1) default 0 comment '0否1是',
   imptype              smallint default 0 ,
   primary key (id)
);

/*==============================================================*/
/* Table: eop_sitedomain                                        */
/*==============================================================*/
create table eop_sitedomain
(
   id                   int not null auto_increment,
   domain               varchar(255),
   domaintype           smallint default 0 comment '0:二级域名;1:独立域名',
   siteid               int,
   userid               int,
   status               smallint default 0 comment '0:已开启;1:已关闭',
   primary key (id)
);

/*==============================================================*/
/* Table: eop_user                                              */
/*==============================================================*/
create table eop_user
(
   id                   int not null auto_increment,
   username             varchar(50),
   companyname          varchar(50),
   password             varchar(50),
   address              varchar(255),
   legalrepresentative  varchar(50),
   linkman              varchar(50),
   tel                  varchar(50),
   mobile               varchar(50),
   email                varchar(50),
   logofile             varchar(255),
   licensefile          varchar(255),
   defaultsiteid        int,
   deleteflag           smallint default 0 comment '0：正常；1：已删除;2:未审核,默认是未审核',
   usertype             smallint comment '0个人
            1公司',
   createtime           int,
   primary key (id)
);

/*==============================================================*/
/* Table: eop_userdetail                                        */
/*==============================================================*/
create table eop_userdetail
(
   id                   int not null auto_increment,
   userid               int,
   bussinessscope       text,
   regaddress           varchar(255),
   regdate              bigint(8),
   corpscope            smallint default 0 comment '0:未知;1:1-10人;2:11-50人;3:51-100人;4:101-500人;5:501-1000人;6:1000人以上',
   corpdescript         text,
   primary key (id)
);
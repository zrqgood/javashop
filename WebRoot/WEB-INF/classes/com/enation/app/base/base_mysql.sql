drop table if exists eop_ask;

drop table if exists eop_data_log;

drop table if exists eop_reply;

drop table if exists es_access;

drop table if exists es_adcolumn;

drop table if exists es_admintheme;

drop table if exists es_adminuser;

drop table if exists es_adv;

drop table if exists es_auth_action;

drop table if exists es_border;

drop table if exists es_friends_link;

drop table if exists es_guestbook;

drop table if exists es_index_item;

drop table if exists es_menu;

drop table if exists es_role;

drop table if exists es_role_auth;

drop table if exists es_settings;

drop table if exists es_site;

drop table if exists es_site_menu;

drop table if exists es_theme;

drop table if exists es_themeuri;

drop table if exists es_user_role;

drop table if exists es_widgetbundle;

/*==============================================================*/
/* Table: eop_ask                                               */
/*==============================================================*/
create table eop_ask
(
   askid                mediumint(8) not null auto_increment,
   title                varchar(255),
   content              text,
   dateline             bigint,
   isreply              smallint(1) comment '0未回复
            1未回复
            2用户有新回复',
   userid               mediumint(8),
   siteid               mediumint(8),
   domain               varchar(255),
   sitename             varchar(255),
   username             varchar(255),
   primary key (askid)
);

/*==============================================================*/
/* Table: eop_data_log                                          */
/*==============================================================*/
create table eop_data_log
(
   id                   int not null auto_increment,
   content              text,
   url                  varchar(255),
   pics                 text comment '格式：
            缩略图|大图,缩略图|大图',
   sitename             varchar(255),
   domain               varchar(255),
   logtype              varchar(50),
   optype               varchar(50),
   dateline             bigint,
   userid               bigint,
   siteid               bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: eop_reply                                             */
/*==============================================================*/
create table eop_reply
(
   replyid              mediumint(8) not null auto_increment,
   askid                mediumint(8),
   content              text,
   username             varchar(255),
   dateline             bigint,
   primary key (replyid)
);

/*==============================================================*/
/* Table: es_access                                             */
/*==============================================================*/
create table es_access
(
   id                   mediumint not null auto_increment,
   ip                   varchar(255),
   url                  varchar(1000),
   page                 varchar(255),
   area                 varchar(255),
   access_time          int,
   stay_time            int,
   point                int default 0,
   membername           varchar(255) default '0',
   primary key (id)
);

/*==============================================================*/
/* Table: es_adcolumn                                           */
/*==============================================================*/
create table es_adcolumn
(
   acid                 bigint not null auto_increment,
   cname                varchar(255),
   width                varchar(50),
   height               varchar(50),
   description          varchar(255),
   number               bigint,
   atype                int,
   rule                 bigint,
   disabled             enum('false','true') default 'false',
   primary key (acid)
);

/*==============================================================*/
/* Table: es_admintheme                                         */
/*==============================================================*/
create table es_admintheme
(
   id                   int not null auto_increment,
   appid                varchar(50),
   siteid               int,
   themename            varchar(50),
   path                 varchar(255) comment '对本地是目录，对远程是地址',
   userid               int,
   author               varchar(50),
   version              varchar(50),
   framemode            smallint default 0 comment '/**
            	 * 0否
            	 * 1是
            	 */',
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   thumb                varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: es_adminuser                                          */
/*==============================================================*/
create table es_adminuser
(
   userid               int not null auto_increment,
   username             varchar(255),
   password             varchar(255),
   state                smallint comment '0禁用
            1开启',
   realname             varchar(255),
   userno               varchar(255),
   userdept             varchar(255),
   remark               varchar(255),
   dateline             int,
   founder              smallint(1) comment '0否
            1是',
   siteid               int,
   primary key (userid)
);

/*==============================================================*/
/* Table: es_adv                                                */
/*==============================================================*/
create table es_adv
(
   aid                  bigint not null auto_increment,
   acid                 bigint,
   atype                int comment '0图片1swf',
   begintime            bigint(8),
   endtime              bigint(8),
   isclose              int comment '0开启1关闭',
   attachment           varchar(50),
   atturl               varchar(255),
   url                  varchar(255),
   aname                varchar(255),
   clickcount           int default 0,
   linkman              varchar(50),
   company              varchar(255),
   contact              varchar(255),
   disabled             enum('false','true') default 'false',
   primary key (aid)
);

/*==============================================================*/
/* Table: es_auth_action                                        */
/*==============================================================*/
create table es_auth_action
(
   actid                int not null auto_increment,
   name                 varchar(255),
   type                 varchar(255),
   objvalue             text,
   primary key (actid)
);

/*==============================================================*/
/* Table: es_border                                             */
/*==============================================================*/
create table es_border
(
   id                   int not null auto_increment,
   borderid             varchar(50),
   bordername           varchar(50),
   themepath            varchar(50),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   primary key (id)
);

/*==============================================================*/
/* Table: es_friends_link                                       */
/*==============================================================*/
create table es_friends_link
(
   link_id              mediumint(8) not null auto_increment,
   name                 varchar(100),
   url                  varchar(100),
   logo                 varchar(255),
   sort                 smallint(1),
   primary key (link_id)
);

/*==============================================================*/
/* Table: es_guestbook                                          */
/*==============================================================*/
create table es_guestbook
(
   id                   mediumint(8) not null auto_increment,
   title                varchar(255),
   content              text,
   parentid             mediumint(8),
   dateline             bigint,
   issubject            smallint(1) comment '0不是主题
            1是主题',
   username             varchar(255),
   email                varchar(255),
   qq                   varchar(255),
   tel                  varchar(255),
   sex                  smallint(1) comment '0女
            1男',
   ip                   varchar(255),
   area                 varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: es_index_item                                         */
/*==============================================================*/
create table es_index_item
(
   id                   int not null auto_increment,
   title                varchar(255),
   url                  varchar(255),
   sort                 int,
   primary key (id)
);

/*==============================================================*/
/* Table: es_menu                                               */
/*==============================================================*/
create table es_menu
(
   id                   int not null auto_increment,
   appid                varchar(50),
   pid                  int,
   title                varchar(50),
   url                  varchar(255),
   target               varchar(255),
   sorder               int default 50,
   menutype             integer,
   datatype             varchar(50),
   selected             smallint default 0 comment '0:False;1:True default 0',
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   primary key (id)
);

/*==============================================================*/
/* Table: es_role                                               */
/*==============================================================*/
create table es_role
(
   roleid               int not null auto_increment,
   rolename             varchar(255),
   rolememo             varchar(255),
   primary key (roleid)
);

/*==============================================================*/
/* Table: es_role_auth                                          */
/*==============================================================*/
create table es_role_auth
(
   id                   int not null auto_increment,
   roleid               int,
   authid               int,
   primary key (id)
);

/*==============================================================*/
/* Table: es_settings                                           */
/*==============================================================*/
create table es_settings
(
   id                   mediumint(8) not null auto_increment,
   code                 varchar(50),
   cfg_value            varchar(1000),
   cfg_group            varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: es_site                                               */
/*==============================================================*/
create table es_site
(
   siteid               int not null auto_increment,
   parentid             int,
   code                 bigint,
   name                 varchar(255),
   domain               varchar(255),
   themeid              int,
   level                smallint(1),
   primary key (siteid)
);

/*==============================================================*/
/* Table: es_site_menu                                          */
/*==============================================================*/
create table es_site_menu
(
   menuid               mediumint(8) not null auto_increment,
   parentid             mediumint(8),
   name                 varchar(50),
   url                  varchar(255),
   target               varchar(255),   
   sort                 int,
   primary key (menuid)
);

/*==============================================================*/
/* Table: es_theme                                              */
/*==============================================================*/
create table es_theme
(
   id                   int not null auto_increment,
   appid                varchar(50),
   themename            varchar(50),
   path                 varchar(255) comment '对本地是目录，对远程是地址',
   author               varchar(50),
   version              varchar(50),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   thumb                varchar(50),
   siteid               int,
   primary key (id)
);

/*==============================================================*/
/* Table: es_themeuri                                           */
/*==============================================================*/
create table es_themeuri
(
   id                   int not null auto_increment,
   themeid              int,
   uri                  varchar(255),
   path                 varchar(255),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   pagename             varchar(255),
   point                int,
   sitemaptype          int default 0 comment '0:不加入;1:加入',
   keywords             varchar(255),
   description          text,
   primary key (id)
);

/*==============================================================*/
/* Table: es_user_role                                          */
/*==============================================================*/
create table es_user_role
(
   id                   int not null auto_increment,
   userid               int,
   roleid               int,
   primary key (id)
);

/*==============================================================*/
/* Table: es_widgetbundle                                       */
/*==============================================================*/
create table es_widgetbundle
(
   id                   int not null auto_increment,
   widgetname           varchar(50),
   widgettype           varchar(50),
   settingurl           varchar(255),
   deleteflag           smallint default 0 comment '0:正常;1:已删除',
   primary key (id)
);

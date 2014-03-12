drop table if exists es_data_cat;

drop table if exists es_data_field;

drop table if exists es_data_model;

/*==============================================================*/
/* Table: es_data_cat                                           */
/*==============================================================*/
create table es_data_cat
(
   cat_id               mediumint(8) not null auto_increment,
   name                 varchar(255),
   parent_id            mediumint(8),
   cat_path             varchar(255),
   cat_order            smallint(5),
   model_id             mediumint(8),
   if_audit             mediumint(2) comment '0：否，1：是',
   url                  varchar(255),
   detail_url           varchar(255),
   tositemap            mediumint(2) default 0 comment '0：否，1：是' ,
   primary key (cat_id)
);


/*==============================================================*/
/* Table: es_data_field                                         */
/*==============================================================*/
create table es_data_field
(
   field_id             mediumint(8) not null auto_increment,
   china_name           varchar(255),
   english_name         varchar(255),
   data_type            int comment '1:字符串
            2:整数
            3:浮点数
            4:文本
            5:日期
            6:图片',
   data_size            varchar(20),
   show_form            varchar(255) comment '1:文本域
            2:文本区域
            3:单选按钮
            4:复选框
            5:列表菜单
            6:FCK文本编辑器
            7:图片 
            8:日期
            9:颜色选择器
            10:数据字典',
   show_value           varchar(400) comment '逗号隔开',
   add_time             bigint,
   model_id             mediumint(8),
   save_value           text comment '逗号隔开,与展现值要品要匹配',
   is_validate          smallint(1),
   taxis                mediumint(4),
   dict_id              mediumint(8),
   is_show              smallint(1) comment '0为不显示
            1为显示',
   primary key (field_id)
);

/*==============================================================*/
/* Table: es_data_model                                         */
/*==============================================================*/
create table es_data_model
(
   model_id             mediumint(8) not null auto_increment,
   china_name           varchar(255),
   english_name         varchar(255),
   add_time             bigint,
   project_name         varchar(255),
   brief                varchar(400),
   if_audit             mediumint(8) comment '0:否,1是',
   primary key (model_id)
);

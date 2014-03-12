if exists (select 1
            from  sysobjects
           where  id = object_id('es_data_cat')
            and   type = 'U')
   drop table es_data_cat
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_data_field')
            and   type = 'U')
   drop table es_data_field
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_data_model')
            and   type = 'U')
   drop table es_data_model
go

/*==============================================================*/
/* Table: es_data_cat                                           */
/*==============================================================*/
create table es_data_cat (
   cat_id               int                  identity,
   name                 varchar(255)         null,
   parent_id            int                  null,
   cat_path             varchar(255)         null,
   cat_order            smallint             null,
   model_id             int                  null,
   if_audit             int                  null,
   url                  varchar(255)         null,
   detail_url           varchar(255),
   tositemap            int,
   constraint PK_ES_DATA_CAT primary key nonclustered (cat_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0：否，1：是',
   'user', @CurrentUser, 'table', 'es_data_cat', 'column', 'if_audit'
go

/*==============================================================*/
/* Table: es_data_field                                         */
/*==============================================================*/
create table es_data_field (
   field_id             int                  identity,
   china_name           varchar(255)         null,
   english_name         varchar(255)         null,
   data_type            int                  null,
   data_size            varchar(20)          null,
   show_form            varchar(255)         null,
   show_value           varchar(400)         null,
   add_time             bigint               null,
   model_id             int                  null,
   save_value           text                 null,
   is_validate          smallint             null,
   taxis                int                  null,
   dict_id              int                  null,
   is_show              smallint             null,
   constraint PK_ES_DATA_FIELD primary key nonclustered (field_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1:字符串
   2:整数
   3:浮点数
   4:文本
   5:日期
   6:图片',
   'user', @CurrentUser, 'table', 'es_data_field', 'column', 'data_type'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1:文本域
   2:文本区域
   3:单选按钮
   4:复选框
   5:列表菜单
   6:FCK文本编辑器
   7:图片 
   8:日期
   9:颜色选择器
   10:数据字典',
   'user', @CurrentUser, 'table', 'es_data_field', 'column', 'show_form'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '逗号隔开',
   'user', @CurrentUser, 'table', 'es_data_field', 'column', 'show_value'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '逗号隔开,与展现值要品要匹配',
   'user', @CurrentUser, 'table', 'es_data_field', 'column', 'save_value'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0为不显示
   1为显示',
   'user', @CurrentUser, 'table', 'es_data_field', 'column', 'is_show'
go

/*==============================================================*/
/* Table: es_data_model                                         */
/*==============================================================*/
create table es_data_model (
   model_id             int                  identity,
   china_name           varchar(255)         null,
   english_name         varchar(255)         null,
   add_time             bigint               null,
   project_name         varchar(255)         null,
   brief                varchar(400)         null,
   if_audit             int                  null,
   constraint PK_ES_DATA_MODEL primary key nonclustered (model_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:否,1是',
   'user', @CurrentUser, 'table', 'es_data_model', 'column', 'if_audit'
go

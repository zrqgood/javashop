if exists (select 1
            from  sysobjects
           where  id = object_id('es_adcolumn')
            and   type = 'U')
   drop table es_adcolumn
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_adv')
            and   type = 'U')
   drop table es_adv
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_advance_logs')
            and   type = 'U')
   drop table es_advance_logs
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_article')
            and   type = 'U')
   drop table es_article
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_article_cat')
            and   type = 'U')
   drop table es_article_cat
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_brand')
            and   type = 'U')
   drop table es_brand
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_cart')
            and   type = 'U')
   drop table es_cart
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_comments')
            and   type = 'U')
   drop table es_comments
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_coupons')
            and   type = 'U')
   drop table es_coupons
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_delivery')
            and   type = 'U')
   drop table es_delivery
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_delivery_item')
            and   type = 'U')
   drop table es_delivery_item
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_dly_area')
            and   type = 'U')
   drop table es_dly_area
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_dly_center')
            and   type = 'U')
   drop table es_dly_center
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_dly_type')
            and   type = 'U')
   drop table es_dly_type
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_dly_type_area')
            and   type = 'U')
   drop table es_dly_type_area
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_favorite')
            and   type = 'U')
   drop table es_favorite
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_freeoffer')
            and   type = 'U')
   drop table es_freeoffer
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_freeoffer_category')
            and   type = 'U')
   drop table es_freeoffer_category
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_friends_link')
            and   type = 'U')
   drop table es_friends_link
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_gnotify')
            and   type = 'U')
   drop table es_gnotify
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_goods')
            and   type = 'U')
   drop table es_goods
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_goods_adjunct')
            and   type = 'U')
   drop table es_goods_adjunct
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_goods_cat')
            and   type = 'U')
   drop table es_goods_cat
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_goods_complex')
            and   type = 'U')
   drop table es_goods_complex
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_goods_spec')
            and   type = 'U')
   drop table es_goods_spec
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_goods_type')
            and   type = 'U')
   drop table es_goods_type
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_logi_company')
            and   type = 'U')
   drop table es_logi_company
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_member')
            and   type = 'U')
   drop table es_member
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_member_address')
            and   type = 'U')
   drop table es_member_address
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_member_coupon')
            and   type = 'U')
   drop table es_member_coupon
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_member_lv')
            and   type = 'U')
   drop table es_member_lv
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_message')
            and   type = 'U')
   drop table es_message
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_order')
            and   type = 'U')
   drop table es_order
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_order_gift')
            and   type = 'U')
   drop table es_order_gift
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_order_items')
            and   type = 'U')
   drop table es_order_items
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_order_log')
            and   type = 'U')
   drop table es_order_log
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_order_pmt')
            and   type = 'U')
   drop table es_order_pmt
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_package_product')
            and   type = 'U')
   drop table es_package_product
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_payment_cfg')
            and   type = 'U')
   drop table es_payment_cfg
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_payment_logs')
            and   type = 'U')
   drop table es_payment_logs
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_pmt_goods')
            and   type = 'U')
   drop table es_pmt_goods
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_pmt_member_lv')
            and   type = 'U')
   drop table es_pmt_member_lv
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_point_history')
            and   type = 'U')
   drop table es_point_history
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_print_tmpl')
            and   type = 'U')
   drop table es_print_tmpl
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_product')
            and   type = 'U')
   drop table es_product
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_promotion')
            and   type = 'U')
   drop table es_promotion
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_promotion_activity')
            and   type = 'U')
   drop table es_promotion_activity
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_regions')
            and   type = 'U')
   drop table es_regions
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_settings')
            and   type = 'U')
   drop table es_settings
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_site_menu')
            and   type = 'U')
   drop table es_site_menu
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_spec_values')
            and   type = 'U')
   drop table es_spec_values
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_specification')
            and   type = 'U')
   drop table es_specification
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_tag_rel')
            and   type = 'U')
   drop table es_tag_rel
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_tags')
            and   type = 'U')
   drop table es_tags
go

if exists (select 1
            from  sysobjects
           where  id = object_id('es_type_brand')
            and   type = 'U')
   drop table es_type_brand
go

/*==============================================================*/
/* Table: es_adcolumn                                           */
/*==============================================================*/
create table es_adcolumn (
   acid                 bigint               identity,
   cname                varchar(255)         null,
   width                varchar(50)          null,
   height               varchar(50)          null,
   description          varchar(255)         null,
   number               bigint               null,
   atype                int                  null,
   "rule"               bigint               null,
   disabled             varchar(50)          null default 'false',
   constraint PK_ES_ADCOLUMN primary key nonclustered (acid)
)
go

/*==============================================================*/
/* Table: es_adv                                                */
/*==============================================================*/
create table es_adv (
   aid                  bigint               identity,
   acid                 bigint               null,
   atype                int                  null,
   begintime            bigint               null,
   endtime              bigint               null,
   isclose              int                  null,
   attachment           varchar(50)          null,
   atturl               varchar(255)         null,
   url                  varchar(255)         null,
   aname                varchar(255)         null,
   clickcount           int                  null default 0,
   linkman              varchar(50)          null,
   company              varchar(255)         null,
   contact              varchar(255)         null,
   disabled             varchar(50)          null default 'false',
   constraint PK_ES_ADV primary key nonclustered (aid)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0图片1swf',
   'user', @CurrentUser, 'table', 'es_adv', 'column', 'atype'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0开启1关闭',
   'user', @CurrentUser, 'table', 'es_adv', 'column', 'isclose'
go

/*==============================================================*/
/* Table: es_advance_logs                                       */
/*==============================================================*/
create table es_advance_logs (
   log_id               int                  identity,
   member_id            int                  not null,
   money                decimal(20,3)        not null,
   message              varchar(255)         null default NULL,
   mtime                bigint               not null,
   payment_id           varchar(20)          null default NULL,
   order_id             varchar(20)          null default NULL,
   paymethod            varchar(100)         null default NULL,
   memo                 varchar(100)         null default NULL,
   import_money         decimal(20,3)        not null default 0.000,
   explode_money        decimal(20,3)        not null default 0.000,
   member_advance       decimal(20,3)        not null default 0.000,
   shop_advance         decimal(20,3)        not null default 0.000,
   disabled             varchar(20)          not null default 'false',
   constraint PK_ES_ADVANCE_LOGS primary key nonclustered (log_id)
)
go

/*==============================================================*/
/* Table: es_article                                            */
/*==============================================================*/
create table es_article (
   id                   int                  identity,
   title                varchar(255)         null,
   content              text                 null,
   create_time          bigint               null,
   cat_id               int                  null,
   constraint PK_ES_ARTICLE primary key nonclustered (id)
)
go

/*==============================================================*/
/* Table: es_article_cat                                        */
/*==============================================================*/
create table es_article_cat (
   cat_id               int                  identity,
   name                 varchar(200)         null,
   parent_id            int                  null,
   cat_path             varchar(200)         null,
   cat_order            smallint             null,
   constraint PK_ES_ARTICLE_CAT primary key nonclustered (cat_id)
)
go

/*==============================================================*/
/* Table: es_brand                                              */
/*==============================================================*/
create table es_brand (
   brand_id             int                  identity,
   name                 varchar(50)          null,
   logo                 varchar(255)         null,
   keywords             text                 null,
   brief                text                 null,
   url                  varchar(255)         null,
   disabled             smallint             null,
   constraint PK_ES_BRAND primary key nonclustered (brand_id)
)
go

/*==============================================================*/
/* Table: es_cart                                               */
/*==============================================================*/
create table es_cart (
   cart_id              int                  identity,
   product_id           int                  null,
   itemtype             smallint             null default 0,
   num                  int                  null,
   weight               decimal(20,3)        null,
   session_id           varchar(50)          null,
   name                 varchar(255)         null,
   price                decimal(20,3)        null,
   addon                text                 null,
   constraint PK_ES_CART primary key nonclustered (cart_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '      0商品
         1捆绑商品
         2赠品
   ',
   'user', @CurrentUser, 'table', 'es_cart', 'column', 'itemtype'
go

/*==============================================================*/
/* Table: es_comments                                           */
/*==============================================================*/
create table es_comments (
   comment_id           int                  identity,
   for_comment_id       int                  null default NULL,
   object_id            int                  not null,
   object_type          varchar(50)          not null default 'ask',
   author_id            int                  null default NULL,
   author               varchar(100)         null default NULL,
   levelname            varchar(50)          null default NULL,
   contact              varchar(255)         null default NULL,
   mem_read_status      varchar(50)          not null default 'false',
   adm_read_status      varchar(50)          not null default 'false',
   time                 bigint               null,
   lastreply            bigint               null,
   reply_name           varchar(100)         null default NULL,
   title                varchar(255)         null default NULL,
   comment              text                 null,
   ip                   varchar(15)          null default NULL,
   display              varchar(50)          not null default 'false',
   p_index              tinyint              null default NULL,
   disabled             varchar(50)          null default 'false',
   commenttype          varchar(50)          null,
   constraint PK_ES_COMMENTS primary key nonclustered (comment_id)
)
go

/*==============================================================*/
/* Table: es_coupons                                            */
/*==============================================================*/
create table es_coupons (
   cpns_id              int                  identity,
   cpns_name            varchar(255)         null default NULL,
   pmt_id               int                  null default NULL,
   cpns_prefix          varchar(50)          null,
   cpns_gen_quantity    int                  null default 0,
   cpns_key             varchar(20)          null,
   cpns_status          varchar(20)          null default '1',
   cpns_type            varchar(20)          null default '1',
   cpns_point           int                  null default NULL,
   disabled             varchar(20)          null default 'false',
   constraint PK_ES_COUPONS primary key nonclustered (cpns_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0为禁用状态
   1为启用状态',
   'user', @CurrentUser, 'table', 'es_coupons', 'column', 'cpns_status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0为a类优惠卷
   1为b类优惠卷',
   'user', @CurrentUser, 'table', 'es_coupons', 'column', 'cpns_type'
go

/*==============================================================*/
/* Table: es_delivery                                           */
/*==============================================================*/
create table es_delivery (
   delivery_id          int                  identity,
   type                 tinyint              null,
   order_id             int                  null,
   member_id            int                  null,
   money                decimal(20,3)        null,
   ship_type            varchar(255)         null,
   is_protect           tinyint              null,
   protect_price        decimal(20,3)        null,
   logi_id              int                  null,
   logi_name            varchar(255)         null,
   logi_no              varchar(255)         null,
   ship_name            varchar(255)         null,
   province_id          int                  null default NULL,
   city_id              int                  null default NULL,
   region_id            int                  null default NULL,
   region               varchar(50)          null,
   city                 varchar(50)          null,
   province             varchar(50)          null,
   ship_addr            varchar(255)         null,
   ship_zip             varchar(50)          null,
   ship_tel             varchar(50)          null,
   ship_mobile          varchar(50)          null,
   ship_email           varchar(50)          null,
   op_name              varchar(255)         null,
   remark               text                 null,
   create_time          bigint               null,
   reason               varchar(255)         null,
   constraint PK_ES_DELIVERY primary key nonclustered (delivery_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1发货
   2退货',
   'user', @CurrentUser, 'table', 'es_delivery', 'column', 'type'
go

/*==============================================================*/
/* Table: es_delivery_item                                      */
/*==============================================================*/
create table es_delivery_item (
   item_id              int                  identity,
   delivery_id          int                  null,
   goods_id             int                  null,
   product_id           int                  null,
   sn                   varchar(50)           null,
   name                 varchar(255)         null,
   num                  int                  null,
   itemtype             smallint             null default 0,
   constraint PK_ES_DELIVERY_ITEM primary key nonclustered (item_id)
)
go

/*==============================================================*/
/* Table: es_dly_area                                           */
/*==============================================================*/
create table es_dly_area (
   area_id              int                  identity,
   name                 varchar(100)         null,
   constraint PK_ES_DLY_AREA primary key nonclustered (area_id)
)
go

/*==============================================================*/
/* Table: es_dly_center                                         */
/*==============================================================*/
create table es_dly_center (
   dly_center_id        int                  identity,
   name                 varchar(50)          not null,
   address              varchar(200)         null default NULL,
   province             varchar(100)         null default NULL,
   city                 varchar(100)         null default NULL,
   region               varchar(100)         null default NULL,
   province_id          int                  null default NULL,
   city_id              int                  null default NULL,
   region_id            int                  null default NULL,
   zip                  varchar(6)           null default NULL,
   phone                varchar(100)         null default NULL,
   uname                varchar(100)         null default NULL,
   cellphone            varchar(100)         null default NULL,
   sex                  varchar(10)          null default NULL,
   memo                 text                 null,
   disabled             varchar(10)          not null default 'false',
   constraint PK_ES_DLY_CENTER primary key nonclustered (dly_center_id)
)
go

/*==============================================================*/
/* Table: es_dly_type                                           */
/*==============================================================*/
create table es_dly_type (
   type_id              int                  identity,
   name                 varchar(200)         null,
   protect              smallint             null,
   protect_rate         float(6)             null,
   has_cod              tinyint              null,
   min_price            float(10)            null,
   detail               text                 null,
   corp_id              int                  null,
   ordernum             int                  null,
   disabled             smallint             null,
   is_same              smallint             null,
   config               text                 null,
   expressions          text                 null,
   constraint PK_ES_DLY_TYPE primary key nonclustered (type_id)
)
go

/*==============================================================*/
/* Table: es_dly_type_area                                      */
/*==============================================================*/
create table es_dly_type_area (
   type_id              int                  null,
   area_id_group        text                 null,
   area_name_group      text                 null,
   expressions          text                 null,
   has_cod              smallint             null,
   config               text                 null
)
go

/*==============================================================*/
/* Table: es_favorite                                           */
/*==============================================================*/
create table es_favorite (
   favorite_id          int                  identity,
   member_id            int                  null,
   goods_id             int                  null,
   constraint PK_ES_FAVORITE primary key nonclustered (favorite_id)
)
go

/*==============================================================*/
/* Table: es_freeoffer                                          */
/*==============================================================*/
create table es_freeoffer (
   fo_id                int                  identity,
   fo_category_id       int                  null,
   fo_name              varchar(50)          null,
   publishable          smallint             null default 0,
   recommend            smallint             null default 1,
   sorder               smallint             null,
   limit_purchases      smallint             null,
   startdate            bigint               null,
   enddate              bigint               null,
   lv_ids               varchar(50)          null,
   price                decimal(20,3)        null,
   synopsis             varchar(255)         null,
   list_thumb           text                 null,
   pic                  text                 null,
   score                int                  null,
   weight               decimal(20,3)        null,
   repertory            int                  null,
   descript             text                 null,
   disabled             smallint             null default 0,
   constraint PK_ES_FREEOFFER primary key nonclustered (fo_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:是1:否',
   'user', @CurrentUser, 'table', 'es_freeoffer', 'column', 'publishable'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:正常;1:回收站',
   'user', @CurrentUser, 'table', 'es_freeoffer', 'column', 'disabled'
go

/*==============================================================*/
/* Table: es_freeoffer_category                                 */
/*==============================================================*/
create table es_freeoffer_category (
   cat_id               int                  identity,
   cat_name             varchar(50)          null,
   publishable          smallint             null default 0,
   sorder               int                  null,
   disabled             smallint             null default 0,
   constraint PK_ES_FREEOFFER_CATEGORY primary key nonclustered (cat_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:是1:否',
   'user', @CurrentUser, 'table', 'es_freeoffer_category', 'column', 'publishable'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:正常;1:回收站',
   'user', @CurrentUser, 'table', 'es_freeoffer_category', 'column', 'disabled'
go

/*==============================================================*/
/* Table: es_friends_link                                       */
/*==============================================================*/
create table es_friends_link (
   link_id              int                  identity,
   name                 varchar(100)         null,
   url                  varchar(100)         null,
   logo                 varchar(255)         null,
   sort                 smallint             null,
   constraint PK_ES_FRIENDS_LINK primary key nonclustered (link_id)
)
go

/*==============================================================*/
/* Table: es_gnotify                                            */
/*==============================================================*/
create table es_gnotify (
   gnotify_id           int                  identity,
   goods_id             int                  null default NULL,
   member_id            int                  null default NULL,
   product_id           int                  null default NULL,
   email                varchar(200)         null default NULL,
   status               varchar(10)          not null default 'ready',
   send_time            bigint               null default NULL,
   create_time          bigint               null default NULL,
   disabled             varchar(10)          not null default 'false',
   remark               text                 null,
   constraint PK_ES_GNOTIFY primary key nonclustered (gnotify_id)
)
go

/*==============================================================*/
/* Table: es_goods                                              */
/*==============================================================*/
create table es_goods (
   goods_id             int                  identity,
   name                 varchar(200)         null,
   sn                   varchar(200)         null,
   brand_id             int                  null,
   cat_id               int                  null,
   type_id              int                  null,
   goods_type           varchar(20)          null default 'normal',
   unit                 varchar(20)          null,
   weight               decimal(20,3)        null,
   market_enable        smallint             null,
   image_default        text                 null,
   image_file           text                 null,
   brief                varchar(255)         null,
   intro                text                 null,
   price                decimal(20,3)        null,
   cost                 decimal(20,3)        null,
   mktprice             decimal(20,3)        null,
   params               text                 null,
   specs                text                 null,
   have_spec            smallint             null,
   adjuncts             text                 null,
   create_time          bigint               null,
   last_modify          bigint               null,
   view_count           int                  null,
   buy_count            int                  null,
   disabled             smallint             null,
   store                int                  null,
   point                int                  null,
   page_title           varchar(255)         null,
   meta_keywords        varchar(1000)        null,
   meta_description     varchar(1000)        null,
   p20                  varchar(255)         null,
   p19                  varchar(255)         null,
   p18                  varchar(255)         null,
   p17                  varchar(255)         null,
   p16                  varchar(255)         null,
   p15                  varchar(255)         null,
   p14                  varchar(255)         null,
   p13                  varchar(255)         null,
   p12                  varchar(255)         null,
   p11                  varchar(255)         null,
   p10                  varchar(255)         null,
   p9                   varchar(255)         null,
   p8                   varchar(255)         null,
   p7                   varchar(255)         null,
   p6                   varchar(255)         null,
   p5                   varchar(255)         null,
   p4                   varchar(255)         null,
   p3                   varchar(255)         null,
   p2                   varchar(255)         null,
   p1                   varchar(255)         null,
   sord                 int                  null default 0,
   constraint PK_ES_GOODS primary key nonclustered (goods_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0为未上架
   1为上架了
   ',
   'user', @CurrentUser, 'table', 'es_goods', 'column', 'market_enable'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用于显示在列表上的',
   'user', @CurrentUser, 'table', 'es_goods', 'column', 'image_default'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '商品相册中的图片',
   'user', @CurrentUser, 'table', 'es_goods', 'column', 'image_file'
go

/*==============================================================*/
/* Index: goods_cat_id                                          */
/*==============================================================*/
create index goods_cat_id on es_goods (
cat_id ASC
)
go

/*==============================================================*/
/* Index: gppds_brand_id                                        */
/*==============================================================*/
create index gppds_brand_id on es_goods (
brand_id ASC
)
go

/*==============================================================*/
/* Index: goods_name                                            */
/*==============================================================*/
create index goods_name on es_goods (
name ASC
)
go

/*==============================================================*/
/* Index: goods_sn                                              */
/*==============================================================*/
create index goods_sn on es_goods (
sn ASC
)
go

/*==============================================================*/
/* Table: es_goods_adjunct                                      */
/*==============================================================*/
create table es_goods_adjunct (
   adjunct_id           int                  identity,
   goods_id             int                  null,
   adjunct_name         varchar(50)          null,
   min_num              int                  null,
   max_num              int                  null,
   set_price            varchar(50)          null,
   price                varchar(50)          null,
   items                text                 null,
   constraint PK_ES_GOODS_ADJUNCT primary key nonclustered (adjunct_id)
)
go

/*==============================================================*/
/* Table: es_goods_cat                                          */
/*==============================================================*/
create table es_goods_cat (
   cat_id               int                  identity,
   name                 varchar(200)         null,
   parent_id            int                  null,
   cat_path             varchar(200)         null,
   goods_count          int                  null,
   cat_order            smallint             null,
   type_id              int                  null,
   list_show            varchar(10)          null default '1',
   image                varchar(255)         null,
   constraint PK_ES_GOODS_CAT primary key nonclustered (cat_id)
)
go

/*==============================================================*/
/* Table: es_goods_complex                                      */
/*==============================================================*/
create table es_goods_complex (
   goods_1              int                  not null,
   goods_2              int                  not null,
   manual               varchar(50)          null default NULL,
   rate                 int                  not null default 1
)
go

/*==============================================================*/
/* Table: es_goods_spec                                         */
/*==============================================================*/
create table es_goods_spec (
   spec_id              int                  not null,
   spec_value_id        int                  not null,
   goods_id             int                  null,
   product_id           int                  not null,
   constraint PK_ES_GOODS_SPEC primary key nonclustered (spec_id, spec_value_id, product_id)
)
go

/*==============================================================*/
/* Index: fk_spec_goods_index                                   */
/*==============================================================*/
create index fk_spec_goods_index on es_goods_spec (
goods_id ASC
)
go

/*==============================================================*/
/* Index: fk_spec_index                                         */
/*==============================================================*/
create index fk_spec_index on es_goods_spec (
spec_id ASC
)
go

/*==============================================================*/
/* Index: fk_spec_products                                      */
/*==============================================================*/
create index fk_spec_products on es_goods_spec (
product_id ASC
)
go

/*==============================================================*/
/* Index: fk_spec_value_index                                   */
/*==============================================================*/
create index fk_spec_value_index on es_goods_spec (
spec_id ASC,
spec_value_id ASC
)
go

/*==============================================================*/
/* Table: es_goods_type                                         */
/*==============================================================*/
create table es_goods_type (
   type_id              int                  identity,
   name                 varchar(100)         null,
   props                text                 null,
   params               text                 null,
   disabled             smallint             null,
   is_physical          smallint             null,
   have_prop            smallint             null,
   have_parm            smallint             null,
   join_brand           smallint             null,
   constraint PK_ES_GOODS_TYPE primary key nonclustered (type_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1输入项 可搜索 
   2输入项 不可搜索
   3选择项 渐进式搜索 
   4选择项 普通搜索 
   5选择项 不可搜索 ',
   'user', @CurrentUser, 'table', 'es_goods_type', 'column', 'props'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1是
   0否',
   'user', @CurrentUser, 'table', 'es_goods_type', 'column', 'have_prop'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1是
   0否',
   'user', @CurrentUser, 'table', 'es_goods_type', 'column', 'have_parm'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1是
   0否',
   'user', @CurrentUser, 'table', 'es_goods_type', 'column', 'join_brand'
go

/*==============================================================*/
/* Table: es_logi_company                                       */
/*==============================================================*/
create table es_logi_company (
   id                   int                  identity,
   name                 varchar(255)         null,
   constraint PK_ES_LOGI_COMPANY primary key nonclustered (id)
)
go

/*==============================================================*/
/* Table: es_member                                             */
/*==============================================================*/
create table es_member (
   member_id            int                  identity,
   lv_id                int                  null,
   uname                varchar(50)          null,
   email                varchar(50)          null,
   password             varchar(50)          null,
   regtime              bigint               null,
   pw_answer            varchar(255)         null,
   pw_question          varchar(255)         null,
   name                 varchar(255)         null,
   sex                  smallint             null,
   birthday             bigint               null,
   advance              decimal(20,3)        null default 0,
   province_id          int                  null,
   city_id              int                  null,
   region_id            int                  null,
   province             varchar(50)          null,
   city                 varchar(50)          null,
   region               varchar(50)          null,
   address              varchar(255)         null,
   zip                  varchar(50)          null,
   mobile               varchar(50)          null,
   tel                  varchar(50)          null,
   point                int                  null default 0,
   QQ                   varchar(50)          null,
   msn                  varchar(50)          null,
   remark               text                 null,
   lastlogin            int                  null default 1280629569,
   logincount           int                  null default 0,
   constraint PK_ES_MEMBER primary key nonclustered (member_id)
)
go

/*==============================================================*/
/* Table: es_member_address                                     */
/*==============================================================*/
create table es_member_address (
   addr_id              int                  identity,
   member_id            int                  not null default 0,
   name                 varchar(50)          null default NULL,
   country              varchar(30)          null default NULL,
   province_id          int                  null default NULL,
   city_id              int                  null default NULL,
   region_id            int                  null default NULL,
   region               varchar(50)          null,
   city                 varchar(50)          null,
   province             varchar(50)          null,
   addr                 varchar(255)         null default NULL,
   zip                  varchar(20)          null default NULL,
   tel                  varchar(30)          null default NULL,
   mobile               varchar(30)          null default NULL,
   def_addr             int                  null default 0,
   constraint PK_ES_MEMBER_ADDRESS primary key nonclustered (addr_id),
   constraint fk_member_addr unique (member_id)
)
go

/*==============================================================*/
/* Table: es_member_coupon                                      */
/*==============================================================*/
create table es_member_coupon (
   memc_code            varchar(255)         not null,
   cpns_id              int                  not null,
   member_id            int                  not null,
   memc_gen_orderid     varchar(15)          null default NULL,
   memc_source          varchar(15)          not null default 'a',
   memc_enabled         varchar(15)          not null default 'true',
   memc_used_times      int                  null default 0,
   memc_gen_time        bigint               null default NULL,
   constraint PK_ES_MEMBER_COUPON primary key nonclustered (memc_code)
)
go

/*==============================================================*/
/* Table: es_member_lv                                          */
/*==============================================================*/
create table es_member_lv (
   lv_id                int                  identity,
   name                 varchar(50)          null,
   default_lv           tinyint              null,
   constraint PK_ES_MEMBER_LV primary key nonclustered (lv_id)
)
go

/*==============================================================*/
/* Table: es_message                                            */
/*==============================================================*/
create table es_message (
   msg_id               int                  identity,
   for_id               int                  not null default 0,
   msg_from             varchar(30)          not null default 'anonymous',
   from_id              int                  null default 0,
   from_type            smallint             not null default 0,
   to_id                int                  not null default 0,
   msg_to               varchar(50)          null,
   to_type              smallint             not null default 0,
   unread               int                  not null default 0,
   folder               varchar(50)          not null default 'inbox',
   email                varchar(255)         null default NULL,
   tel                  varchar(30)          null default NULL,
   subject              varchar(100)         not null,
   message              text                 not null,
   rel_order            bigint               null default 0,
   date_line            bigint               not null default 0,
   is_sec               varchar(50)          not null default 'true',
   del_status           int                  null default 0,
   disabled             varchar(50)          not null default 'false',
   msg_ip               varchar(20)          not null default '',
   msg_type             varchar(20)          not null default 'default',
   constraint PK_ES_MESSAGE primary key nonclustered (msg_id)
)
go

/*==============================================================*/
/* Table: es_order                                              */
/*==============================================================*/
create table es_order (
   order_id             int                  identity,
   sn                   varchar(50)          null,
   member_id            int                  null,
   status               smallint             null,
   pay_status           smallint             null,
   ship_status          smallint             null,
   shipping_id          int                  null,
   shipping_type        varchar(255)         null,
   shipping_area        varchar(255)         null,
   payment_id           int                  null,
   payment_name         varchar(50)          null,
   payment_type         varchar(50)          null,
   paymoney             decimal(20,2)        null,
   goods                text                 null,
   create_time          bigint               null,
   ship_name            varchar(255)         null,
   ship_addr            varchar(255)         null,
   ship_zip             varchar(20)          null,
   ship_email           varchar(50)          null,
   ship_mobile          varchar(50)          null,
   ship_tel             varchar(50)          null,
   ship_day             varchar(255)         null,
   ship_time            varchar(255)         null,
   is_protect           tinyint              null,
   protect_price        decimal(20,3)        null,
   goods_amount         decimal(20,2)        null,
   shipping_amount      decimal(20,3)        null,
   order_amount         decimal(20,3)        null,
   weight               decimal(20,3)        null,
   goods_num            int                  null,
   gainedpoint          int                  null default 0,
   consumepoint         int                  null default 0,
   remark               text                 null,
   disabled             tinyint              null,
   discount             decimal(20,3)        null,
   constraint PK_ES_ORDER primary key nonclustered (order_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '-2退货
   -1退款
   0未付款
   1已付款
   2已发货
   3.完成
   4作废',
   'user', @CurrentUser, 'table', 'es_order', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0未付款
   1已付款
   2已经退款
   3部分退款
   4部分付款',
   'user', @CurrentUser, 'table', 'es_order', 'column', 'pay_status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0未发货
   1已发货
   2.已退货
   3 部分退货
   4 部分发货',
   'user', @CurrentUser, 'table', 'es_order', 'column', 'ship_status'
go

/*==============================================================*/
/* Table: es_order_gift                                         */
/*==============================================================*/
create table es_order_gift (
   order_id             int                  null,
   gift_id              int                  null,
   gift_name            varchar(255)         null,
   getmethod            varchar(10)          null,
   point                int                  null,
   num                  int                  null default 0,
   shipnum              int                  null default 0
)
go

/*==============================================================*/
/* Table: es_order_items                                        */
/*==============================================================*/
create table es_order_items (
   item_id              int                  identity,
   order_id             int                  null,
   goods_id             int                  null,
   spec_id              int                  null,
   num                  int                  null,
   ship_num             int                  null,
   name                 varchar(255)         null,
   price                decimal(20,3)        null,
   gainedpoint          int                  null default 0,
   addon                text                 null,
   constraint PK_ES_ORDER_ITEMS primary key nonclustered (item_id)
)
go

/*==============================================================*/
/* Table: es_order_log                                          */
/*==============================================================*/
create table es_order_log (
   log_id               int                  identity,
   order_id             int                  null,
   op_id                int                  null,
   op_name              varchar(50)          null,
   message              text                 null,
   op_time              bigint               null,
   constraint PK_ES_ORDER_LOG primary key nonclustered (log_id)
)
go

/*==============================================================*/
/* Table: es_order_pmt                                          */
/*==============================================================*/
create table es_order_pmt (
   pmt_id               int                  null,
   order_id             int                  null,
   pmt_amount           decimal(20,3)        null,
   pmt_describe         varchar(255)         null
)
go

/*==============================================================*/
/* Table: es_package_product                                    */
/*==============================================================*/
create table es_package_product (
   product_id           int                  not null,
   goods_id             int                  not null,
   discount             decimal(5,3)         null default NULL,
   pkgnum               int                  not null default 1,
   constraint PK_ES_PACKAGE_PRODUCT primary key nonclustered (product_id, goods_id)
)
go

/*==============================================================*/
/* Table: es_payment_cfg                                        */
/*==============================================================*/
create table es_payment_cfg (
   id                   int                  identity,
   name                 varchar(255)         null,
   config               text                 null,
   biref                text                 null,
   pay_fee              decimal(20,2)        null,
   type                 varchar(255)         null,
   constraint PK_ES_PAYMENT_CFG primary key nonclustered (id)
)
go

/*==============================================================*/
/* Table: es_payment_logs                                       */
/*==============================================================*/
create table es_payment_logs (
   payment_id           int                  identity,
   order_id             int                  null,
   member_id            int                  null,
   account              varchar(50)          null,
   bank                 varchar(50)          null,
   pay_user             varchar(50)          null,
   money                decimal(20,3)        null,
   pay_cost             decimal(20,3)        null,
   pay_type             varchar(50)          null,
   pay_method           varchar(50)          null,
   remark               text                 null,
   op_id                int                  null,
   type                 smallint             null,
   status               smallint             null,
   create_time          bigint               null,
   constraint PK_ES_PAYMENT_LOGS primary key nonclustered (payment_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1收款
   2退款',
   'user', @CurrentUser, 'table', 'es_payment_logs', 'column', 'type'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1成功
   2进行中',
   'user', @CurrentUser, 'table', 'es_payment_logs', 'column', 'status'
go

/*==============================================================*/
/* Table: es_pmt_goods                                          */
/*==============================================================*/
create table es_pmt_goods (
   pmt_id               int                  null,
   goods_id             int                  null
)
go

/*==============================================================*/
/* Table: es_pmt_member_lv                                      */
/*==============================================================*/
create table es_pmt_member_lv (
   pmt_id               int                  null,
   lv_id                int                  null
)
go

/*==============================================================*/
/* Table: es_point_history                                      */
/*==============================================================*/
create table es_point_history (
   id                   int                  identity,
   member_id            int                  not null,
   point                int                  not null,
   time                 bigint               not null,
   reason               varchar(50)          not null,
   related_id           bigint               null default NULL,
   type                 smallint             not null,
   operator             varchar(50)          null default NULL,
   constraint PK_ES_POINT_HISTORY primary key nonclustered (id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '相关的订单ID/会员ID',
   'user', @CurrentUser, 'table', 'es_point_history', 'column', 'related_id'
go

/*==============================================================*/
/* Table: es_print_tmpl                                         */
/*==============================================================*/
create table es_print_tmpl (
   prt_tmpl_id          int                  identity,
   prt_tmpl_title       varchar(100)         not null,
   shortcut             varchar(10)          null default 'false',
   disabled             varchar(10)          null default 'false',
   prt_tmpl_width       tinyint              not null default 100,
   prt_tmpl_height      tinyint              not null default 100,
   prt_tmpl_data        text                 null,
   bgimage              varchar(255)         null,
   constraint PK_ES_PRINT_TMPL primary key nonclustered (prt_tmpl_id)
)
go

/*==============================================================*/
/* Table: es_product                                            */
/*==============================================================*/
create table es_product (
   product_id           int                  identity,
   goods_id             int                  null,
   name                 varchar(50)          null,
   sn                   varchar(50)          null,
   store                int                  null,
   price                decimal(20,3)        null,
   specs                text                 null,
   cost                 decimal(20,3)        null,
   weight               decimal(20,3)        null,
   constraint PK_ES_PRODUCT primary key nonclustered (product_id)
)
go

/*==============================================================*/
/* Table: es_promotion                                          */
/*==============================================================*/
create table es_promotion (
   pmt_id               int                  identity,
   pmts_id              varchar(255)         not null,
   pmta_id              int                  null default NULL,
   pmt_time_begin       bigint               null default NULL,
   pmt_time_end         bigint               null default NULL,
   order_money_from     decimal(20,3)        not null default 0.000,
   order_money_to       decimal(20,3)        not null default 9999999.000,
   seq                  smallint             not null default 0,
   pmt_type             smallint             not null default 0,
   pmt_belong           smallint             not null default 0,
   pmt_bond_type        smallint             not null,
   pmt_describe         text                 null,
   pmt_solution         text                 null,
   pmt_ifcoupon         smallint             not null default 1,
   pmt_update_time      bigint               null default 0,
   pmt_basic_type       varchar(10)          null default 'goods',
   disabled             varchar(10)          null default 'false',
   pmt_ifsale           varchar(10)          not null default 'true',
   pmt_distype          smallint             not null default 0,
   constraint PK_ES_PROMOTION primary key nonclustered (pmt_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0为促销活动规则
   1为优惠促销规则',
   'user', @CurrentUser, 'table', 'es_promotion', 'column', 'pmt_type'
go

/*==============================================================*/
/* Table: es_promotion_activity                                 */
/*==============================================================*/
create table es_promotion_activity (
   id                   int                  identity,
   name                 varchar(200)         null,
   enable               smallint             null,
   begin_time           bigint               null,
   end_time             bigint               null,
   brief                text                 null,
   disabled             smallint             null,
   constraint PK_ES_PROMOTION_ACTIVITY primary key nonclustered (id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0为不可用
   1为可用',
   'user', @CurrentUser, 'table', 'es_promotion_activity', 'column', 'enable'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '删除标注：
   0为未删除
   1为删除',
   'user', @CurrentUser, 'table', 'es_promotion_activity', 'column', 'disabled'
go

/*==============================================================*/
/* Table: es_regions                                            */
/*==============================================================*/
create table es_regions (
   region_id            int                  identity,
   p_region_id          int                  null default NULL,
   region_path          varchar(255)         null default NULL,
   region_grade         int                  null default NULL,
   local_name           varchar(50)          not null,
   constraint PK_ES_REGIONS primary key nonclustered (region_id)
)
go

/*==============================================================*/
/* Table: es_settings                                           */
/*==============================================================*/
create table es_settings (
   id                   int                  identity,
   code                 varchar(50)          null,
   cfg_value            varchar(1000)        null,
   cfg_group            varchar(50)          null,
   constraint PK_ES_SETTINGS primary key nonclustered (id)
)
go

/*==============================================================*/
/* Table: es_site_menu                                          */
/*==============================================================*/
create table es_site_menu (
   menuid               int                  identity,
   parentid             int                  null,
   name                 varchar(50)          null,
   url                  varchar(255)         null,
   sort                 int                  null,
   constraint PK_ES_SITE_MENU primary key nonclustered (menuid)
)
go

/*==============================================================*/
/* Table: es_spec_values                                        */
/*==============================================================*/
create table es_spec_values (
   spec_value_id        int                  identity,
   spec_id              int                  null,
   spec_value           varchar(100)         null,
   spec_image           varchar(255)         null,
   spec_order           int                  null,
   spec_type            smallint             null,
   constraint PK_ES_SPEC_VALUES primary key nonclustered (spec_value_id)
)
go

/*==============================================================*/
/* Index: fk_spec_value                                         */
/*==============================================================*/
create index fk_spec_value on es_spec_values (
spec_id ASC
)
go

/*==============================================================*/
/* Table: es_specification                                      */
/*==============================================================*/
create table es_specification (
   spec_id              int                  identity,
   spec_name            varchar(50)          null,
   spec_show_type       smallint             null,
   spec_type            smallint             null,
   spec_memo            varchar(50)          null,
   spec_order           int                  null,
   disabled             smallint             null,
   constraint PK_ES_SPECIFICATION primary key nonclustered (spec_id)
)
go

/*==============================================================*/
/* Table: es_tag_rel                                            */
/*==============================================================*/
create table es_tag_rel (
   tag_id               int                  null,
   rel_id               int                  null
)
go

/*==============================================================*/
/* Table: es_tags                                               */
/*==============================================================*/
create table es_tags (
   tag_id               int                  identity,
   tag_name             varchar(255)         null,
   rel_count            int                  null,
   constraint PK_ES_TAGS primary key nonclustered (tag_id)
)
go

/*==============================================================*/
/* Table: es_type_brand                                         */
/*==============================================================*/
create table es_type_brand (
   type_id              int                  null,
   brand_id             int                  null
)
go

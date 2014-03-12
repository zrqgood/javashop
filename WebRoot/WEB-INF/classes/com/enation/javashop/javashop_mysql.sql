drop table if exists es_advance_logs;

drop table if exists es_agent;

drop table if exists es_agent_transfer;

drop table if exists es_article;

drop table if exists es_article_cat;

drop table if exists es_brand;

drop table if exists es_cart;

drop table if exists es_cart_optic;

drop table if exists es_comments;

drop table if exists es_coupons;

drop table if exists es_delivery;

drop table if exists es_delivery_item;

drop table if exists es_dly_area;

drop table if exists es_dly_center;

drop table if exists es_dly_type;

drop table if exists es_dly_type_area;

drop table if exists es_favorite;

drop table if exists es_freeoffer;

drop table if exists es_freeoffer_category;

drop table if exists es_gnotify;

drop table if exists es_goods;

drop table if exists es_goods_adjunct;

drop table if exists es_goods_articles;

drop table if exists es_goods_cat;

drop table if exists es_goods_complex;

drop table if exists es_goods_field;

drop table if exists es_goods_lv_price;

drop table if exists es_goods_spec;

drop table if exists es_goods_type;

drop table if exists es_group_buy;

drop table if exists es_group_buy_count;

drop table if exists es_invoice_apply;

drop table if exists es_limitbuy;

drop table if exists es_limitbuy_goods;

drop table if exists es_logi_company;

drop table if exists es_member;

drop table if exists es_member_address;

drop table if exists es_member_coupon;

drop table if exists es_member_lv;

drop table if exists es_message;

drop table if exists es_order;

drop table if exists es_order_gift;

drop table if exists es_order_items;

drop table if exists es_order_log;

drop table if exists es_order_pmt;

drop table if exists es_package_product;

drop table if exists es_payment_cfg;

drop table if exists es_payment_logs;

drop table if exists es_pmt_goods;

drop table if exists es_pmt_member_lv;

drop table if exists es_point_history;

drop table if exists es_print_tmpl;

drop table if exists es_product;

drop table if exists es_product_color;

drop table if exists es_promotion;

drop table if exists es_promotion_activity;

drop table if exists es_regions;

drop table if exists es_returns_order;

drop table if exists es_spec_values;

drop table if exists es_specification;

drop table if exists es_tag_rel;

drop table if exists es_tags;

drop table if exists es_type_brand;

/*==============================================================*/
/* Table: es_advance_logs                                       */
/*==============================================================*/
create table es_advance_logs
(
   log_id               mediumint(8) unsigned not null auto_increment,
   member_id            mediumint(8) unsigned not null,
   money                decimal(20,3) not null,
   message              varchar(255) default NULL,
   mtime                bigint(8) unsigned not null,
   payment_id           varchar(20) default NULL,
   order_id             varchar(20) default NULL,
   paymethod            varchar(100) default NULL,
   memo                 varchar(100) default NULL,
   import_money         decimal(20,3) not null default 0.000,
   explode_money        decimal(20,3) not null default 0.000,
   member_advance       decimal(20,3) not null default 0.000,
   shop_advance         decimal(20,3) not null default 0.000,
   disabled             enum('true','false') not null default 'false',
   primary key (log_id)
);

/*==============================================================*/
/* Table: es_agent                                              */
/*==============================================================*/
create table es_agent
(
   agentid              int not null auto_increment,
   parentid             int,
   username             varchar(50),
   tel                  varchar(50),
   mobile               varchar(50),
   sex                  smallint default 0 comment '0女1男',
   zip                  varchar(50),
   address              varchar(255),
   city                 varchar(50),
   email                varchar(50),
   qq                   varchar(50),
   ww                   varchar(50),
   msn                  varchar(50),
   id_card              varchar(50),
   bank_account         varchar(50),
   bank_username        varchar(50),
   bank_name            varchar(50),
   bank_city            varchar(50),
   shop_url             varchar(50),
   state                smallint default 0 comment '0未通过审核
            1.通过审核',
   dateline             int,
   primary key (agentid)
);

/*==============================================================*/
/* Table: es_agent_transfer                                     */
/*==============================================================*/
create table es_agent_transfer
(
   id                   int not null auto_increment,
   memberid             int,
   price                decimal(20,3),
   is_transfer          smallint(1) comment '0未转
            1已转',
   blank_account        varchar(50),
   blank_username       varchar(50),
   blank_name           varchar(50),
   blank_city           varchar(50),
   apply_time           int,
   transfer_time        int,
   primary key (id)
);

/*==============================================================*/
/* Table: es_article                                            */
/*==============================================================*/
create table es_article
(
   id                   mediumint(8) not null auto_increment,
   title                varchar(255),
   content              longtext,
   create_time          bigint,
   cat_id               mediumint(8),
   primary key (id)
);

/*==============================================================*/
/* Table: es_article_cat                                        */
/*==============================================================*/
create table es_article_cat
(
   cat_id               mediumint(8) not null auto_increment,
   name                 varchar(200),
   parent_id            mediumint(8),
   cat_path             varchar(200),
   cat_order            smallint(5),
   primary key (cat_id)
);

/*==============================================================*/
/* Table: es_brand                                              */
/*==============================================================*/
create table es_brand
(
   brand_id             mediumint(8) not null auto_increment,
   name                 varchar(50),
   logo                 varchar(255),
   keywords             longtext,
   brief                longtext,
   url                  varchar(255),
   disabled             smallint(1),
   primary key (brand_id)
);

/*==============================================================*/
/* Table: es_cart                                               */
/*==============================================================*/
create table es_cart
(
   cart_id              mediumint(8) not null auto_increment,
   product_id           mediumint,
   itemtype             smallint default 0 comment '      0商品
                  1捆绑商品
                  2赠品
            ',
   num                  int,
   weight               decimal(20,3),
   session_id           varchar(50),
   name                 varchar(255),
   price                decimal(20,3),
   addon                text,
   primary key (cart_id)
);

/*==============================================================*/
/* Table: es_cart_optic                                         */
/*==============================================================*/
create table es_cart_optic
(
   id                   int not null auto_increment,
   rel_id               int,
   opticid              int,
   sessionid            varchar(50),
   left_type            smallint,
   left_sph             varchar(50),
   left_cyl             varchar(50),
   left_axis            varchar(50),
   right_type           smallint,
   right_sph            varchar(50),
   right_cyl            varchar(50),
   right_axis           varchar(50),
   pd                   varchar(50),
   remark               text,
   primary key (id)
);

/*==============================================================*/
/* Table: es_comments                                           */
/*==============================================================*/
create table es_comments
(
   comment_id           mediumint(8) unsigned not null auto_increment,
   for_comment_id       mediumint(8) unsigned default NULL,
   object_id            mediumint(8) unsigned not null,
   object_type          varchar(50) not null default 'ask',
   author_id            mediumint(8) unsigned default NULL,
   author               varchar(100) default NULL,
   levelname            varchar(50) default NULL,
   contact              varchar(255) default NULL,
   mem_read_status      enum('false','true') not null default 'false',
   adm_read_status      enum('false','true') not null default 'false',
   time                 bigint(8),
   lastreply            bigint(8),
   reply_name           varchar(100) default NULL,
   title                varchar(255) default NULL,
   comment              longtext,
   ip                   varchar(15) default NULL,
   display              enum('false','true') not null default 'false',
   p_index              tinyint(2) default NULL,
   disabled             enum('false','true') default 'false',
   commenttype          varchar(50),
   grade                int,
   img                  varchar(255),
   primary key (comment_id)
);

/*==============================================================*/
/* Table: es_coupons                                            */
/*==============================================================*/
create table es_coupons
(
   cpns_id              mediumint(8) unsigned not null auto_increment,
   cpns_name            varchar(255) default NULL,
   pmt_id               mediumint(8) unsigned default NULL,
   cpns_prefix          varchar(50),
   cpns_gen_quantity    int(8) unsigned default 0,
   cpns_key             varchar(20),
   cpns_status          enum('0','1') default '1' comment '0为禁用状态
            1为启用状态',
   cpns_type            enum('0','1','2') default '1' comment '0为a类优惠卷
            1为b类优惠卷',
   cpns_point           int(10) default NULL,
   disabled             enum('true','false') default 'false',
   primary key (cpns_id)
);

/*==============================================================*/
/* Table: es_delivery                                           */
/*==============================================================*/
create table es_delivery
(
   delivery_id          mediumint(8) not null auto_increment,
   type                 tinyint(1) comment '1发货
            2退货
            3换货',
   order_id             mediumint(8),
   member_id            mediumint(8),
   money                decimal(20,3),
   ship_type            varchar(255),
   is_protect           tinyint,
   protect_price        decimal(20,3),
   logi_id              mediumint(8),
   logi_name            varchar(255),
   logi_no              varchar(255),
   ship_name            varchar(255),
   province_id          int default NULL,
   city_id              int default NULL,
   region_id            int default NULL,
   region               varchar(50),
   city                 varchar(50),
   province             varchar(50),
   ship_addr            varchar(255),
   ship_zip             varchar(50),
   ship_tel             varchar(50),
   ship_mobile          varchar(50),
   ship_email           varchar(50),
   op_name              varchar(255),
   remark               longtext,
   create_time          bigint(8),
   reason               varchar(255),
   primary key (delivery_id)
);

/*==============================================================*/
/* Table: es_delivery_item                                      */
/*==============================================================*/
create table es_delivery_item
(
   item_id              mediumint(8) not null auto_increment,
   delivery_id          mediumint(8),
   goods_id             mediumint(8),
   product_id           mediumint(8),
   sn                   varchar(50),
   name                 varchar(255),
   num                  mediumint(8),
   itemtype             smallint(1) default 0,
   primary key (item_id)
);

/*==============================================================*/
/* Table: es_dly_area                                           */
/*==============================================================*/
create table es_dly_area
(
   area_id              mediumint(8) not null auto_increment,
   name                 varchar(100),
   primary key (area_id)
);

/*==============================================================*/
/* Table: es_dly_center                                         */
/*==============================================================*/
create table es_dly_center
(
   dly_center_id        int(10) unsigned not null auto_increment,
   name                 varchar(50) not null,
   address              varchar(200) default NULL,
   province             varchar(100) default NULL,
   city                 varchar(100) default NULL,
   region               varchar(100) default NULL,
   province_id          int default NULL,
   city_id              int default NULL,
   region_id            int default NULL,
   zip                  varchar(6) default NULL,
   phone                varchar(100) default NULL,
   uname                varchar(100) default NULL,
   cellphone            varchar(100) default NULL,
   sex                  enum('male','famale') default NULL,
   memo                 longtext,
   disabled             enum('true','false') not null default 'false',
   primary key (dly_center_id)
);

/*==============================================================*/
/* Table: es_dly_type                                           */
/*==============================================================*/
create table es_dly_type
(
   type_id              mediumint(8) not null auto_increment,
   name                 varchar(200),
   protect              smallint(1),
   protect_rate         float(6,3),
   has_cod              tinyint(1),
   min_price            float(10,2),
   detail               text,
   corp_id              mediumint(8),
   ordernum             int(10),
   disabled             smallint(1),
   is_same              smallint(1),
   config               text,
   expressions          text,
   primary key (type_id)
);

/*==============================================================*/
/* Table: es_dly_type_area                                      */
/*==============================================================*/
create table es_dly_type_area
(
   type_id              mediumint(8),
   area_id_group        text,
   area_name_group      text,
   expressions          text,
   has_cod              smallint(1),
   config               text
);

/*==============================================================*/
/* Table: es_favorite                                           */
/*==============================================================*/
create table es_favorite
(
   favorite_id          int not null auto_increment,
   member_id            int,
   goods_id             int,
   primary key (favorite_id)
);

/*==============================================================*/
/* Table: es_freeoffer                                          */
/*==============================================================*/
create table es_freeoffer
(
   fo_id                mediumint(8) not null auto_increment,
   fo_category_id       mediumint(8),
   fo_name              varchar(50),
   publishable          smallint default 0 comment '0:是1:否',
   recommend            smallint default 1,
   sorder               smallint,
   limit_purchases      smallint,
   startdate            bigint,
   enddate              bigint,
   lv_ids               varchar(50),
   price                decimal(20,3),
   synopsis             varchar(255),
   list_thumb           longtext,
   pic                  longtext,
   score                mediumint(8),
   weight               decimal(20,3),
   repertory            mediumint(8),
   descript             text,
   disabled             smallint default 0 comment '0:正常;1:回收站',
   primary key (fo_id)
);

/*==============================================================*/
/* Table: es_freeoffer_category                                 */
/*==============================================================*/
create table es_freeoffer_category
(
   cat_id               int not null auto_increment,
   cat_name             varchar(50),
   publishable          smallint default 0 comment '0:是1:否',
   sorder               int,
   disabled             smallint default 0 comment '0:正常;1:回收站',
   primary key (cat_id)
);

/*==============================================================*/
/* Table: es_gnotify                                            */
/*==============================================================*/
create table es_gnotify
(
   gnotify_id           mediumint(8) unsigned not null auto_increment,
   goods_id             mediumint(8) unsigned default NULL,
   member_id            mediumint(8) unsigned default NULL,
   product_id           mediumint(8) unsigned default NULL,
   email                varchar(200) default NULL,
   status               enum('ready','send','progress') not null default 'ready',
   send_time            bigint(8) unsigned default NULL,
   create_time          bigint(8) unsigned default NULL,
   disabled             enum('false','true') not null default 'false',
   remark               longtext,
   primary key (gnotify_id)
);

/*==============================================================*/
/* Table: es_goods                                              */
/*==============================================================*/
create table es_goods
(
   goods_id             mediumint(8) not null auto_increment,
   name                 varchar(200),
   sn                   varchar(200),
   brand_id             mediumint(8),
   cat_id               mediumint(8),
   type_id              mediumint(8),
   goods_type           enum('normal','bind') default 'normal',
   unit                 varchar(20),
   weight               decimal(20,3),
   market_enable        smallint(1) comment '0为未上架
            1为上架了
            ',
   image_default        longtext comment '用于显示在列表上的',
   image_file           longtext comment '商品相册中的图片',
   brief                varchar(255),
   intro                longtext,
   price                decimal(20,3),
   cost                 decimal(20,3),
   mktprice             decimal(20,3),
   params               longtext,
   specs                longtext,
   have_spec            smallint(1),
   adjuncts             longtext,
   create_time          bigint(8),
   last_modify          bigint(8),
   view_count           int(10),
   buy_count            int(10),
   disabled             smallint(1),
   store                mediumint(8),
   point                int(10),
   page_title           varchar(255),
   meta_keywords        varchar(1000),
   meta_description     varchar(1000),
   p20                  varchar(255),
   p19                  varchar(255),
   p18                  varchar(255),
   p17                  varchar(255),
   p16                  varchar(255),
   p15                  varchar(255),
   p14                  varchar(255),
   p13                  varchar(255),
   p12                  varchar(255),
   p11                  varchar(255),
   p10                  varchar(255),
   p9                   varchar(255),
   p8                   varchar(255),
   p7                   varchar(255),
   p6                   varchar(255),
   p5                   varchar(255),
   p4                   varchar(255),
   p3                   varchar(255),
   p2                   varchar(255),
   p1                   varchar(255),
   sord                 int default 0,
   have_field           smallint(1) default 0,
   isgroup              smallint(1) default 0,
   islimit              smallint(1) default 0,
   grade                int default 0,
   primary key (goods_id)
);

/*==============================================================*/
/* Index: goods_cat_id                                          */
/*==============================================================*/
create index goods_cat_id on es_goods
(
   cat_id
);

/*==============================================================*/
/* Index: gppds_brand_id                                        */
/*==============================================================*/
create index gppds_brand_id on es_goods
(
   brand_id
);

/*==============================================================*/
/* Index: goods_name                                            */
/*==============================================================*/
create index goods_name on es_goods
(
   name
);

/*==============================================================*/
/* Index: goods_sn                                              */
/*==============================================================*/
create index goods_sn on es_goods
(
   sn
);

/*==============================================================*/
/* Table: es_goods_adjunct                                      */
/*==============================================================*/
create table es_goods_adjunct
(
   adjunct_id           int not null auto_increment,
   goods_id             int,
   adjunct_name         varchar(50),
   min_num              int,
   max_num              int,
   set_price            enum('discount','minus'),
   price                numeric(20,3),
   items                text,
   primary key (adjunct_id)
);

/*==============================================================*/
/* Table: es_goods_articles                                     */
/*==============================================================*/
create table es_goods_articles
(
   id                   int not null auto_increment,
   goodsid              int,
   articleid            int,
   title                varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: es_goods_cat                                          */
/*==============================================================*/
create table es_goods_cat
(
   cat_id               mediumint(8) not null auto_increment,
   name                 varchar(200),
   parent_id            mediumint(8),
   cat_path             varchar(200),
   goods_count          mediumint(8),
   cat_order            smallint(5),
   type_id              mediumint(8),
   list_show            enum('0','1') default '1',
   image                varchar(255),
   primary key (cat_id)
);

/*==============================================================*/
/* Table: es_goods_complex                                      */
/*==============================================================*/
create table es_goods_complex
(
   goods_1              mediumint(8) unsigned not null,
   goods_2              mediumint(8) unsigned not null,
   manual               enum('left','both') default NULL,
   rate                 mediumint(8) unsigned not null default 1
);

/*==============================================================*/
/* Table: es_goods_field                                        */
/*==============================================================*/
create table es_goods_field
(
   field_id             int not null auto_increment,
   china_name           varchar(255),
   english_name         varchar(255),
   pluginid             varchar(255),
   config               text,
   add_time             bigint,
   type_id              mediumint(8),
   is_validate          smallint(1),
   field_sort           int,
   is_show              smallint(1) comment '0为不显示
            1为显示',
   primary key (field_id)
);

/*==============================================================*/
/* Table: es_goods_lv_price                                     */
/*==============================================================*/
create table es_goods_lv_price
(
   id                   int not null auto_increment,
   productid            int,
   goodsid              int,
   lvid                 int,
   price                decimal(20,3),
   primary key (id)
);

/*==============================================================*/
/* Table: es_goods_spec                                         */
/*==============================================================*/
create table es_goods_spec
(
   spec_id              mediumint(8) not null,
   spec_value_id        mediumint(8) not null,
   goods_id             mediumint(8),
   product_id           mediumint(8) not null,
   primary key (spec_id, spec_value_id, product_id)
);

/*==============================================================*/
/* Index: fk_spec_goods_index                                   */
/*==============================================================*/
create index fk_spec_goods_index on es_goods_spec
(
   goods_id
);

/*==============================================================*/
/* Index: fk_spec_index                                         */
/*==============================================================*/
create index fk_spec_index on es_goods_spec
(
   spec_id
);

/*==============================================================*/
/* Index: fk_spec_products                                      */
/*==============================================================*/
create index fk_spec_products on es_goods_spec
(
   product_id
);

/*==============================================================*/
/* Index: fk_spec_value_index                                   */
/*==============================================================*/
create index fk_spec_value_index on es_goods_spec
(
   spec_id,
   spec_value_id
);

/*==============================================================*/
/* Table: es_goods_type                                         */
/*==============================================================*/
create table es_goods_type
(
   type_id              mediumint(8) not null auto_increment,
   name                 varchar(100),
   props                longtext comment '1输入项 可搜索 
            2输入项 不可搜索
            3选择项 渐进式搜索 
            4选择项 普通搜索 
            5选择项 不可搜索 ',
   params               longtext,
   disabled             smallint(1),
   is_physical          smallint(1),
   have_prop            smallint(1) comment '1是
            0否',
   have_parm            smallint(1) comment '1是
            0否',
   join_brand           smallint(1) comment '1是
            0否',
   have_field           smallint(1) default 0,
   primary key (type_id)
);

/*==============================================================*/
/* Table: es_group_buy                                          */
/*==============================================================*/
create table es_group_buy
(
   groupid              int not null auto_increment,
   title                varchar(255),
   descript				varchar(255),
   content              text,
   discount             double,
   price                decimal(20,3),
   start_time           int,
   end_time             int,
   goodsid              int,
   buy_count            int,
   add_time             int,
   dis_type             int,
   img                  varchar(255),
   is_index             smallint(1) default 0,
   primary key (groupid)
);

/*==============================================================*/
/* Table: es_group_buy_count                                    */
/*==============================================================*/
create table es_group_buy_count
(
   ruleid               int not null auto_increment,
   groupid              int,
   start_time           int,
   end_time             int,
   num                  int,
   primary key (ruleid)
);

/*==============================================================*/
/* Table: es_invoice_apply                                      */
/*==============================================================*/
create table es_invoice_apply
(
   id                   int not null auto_increment,
   title                varchar(255),
   orderid              int,
   add_time             bigint,
   content              varchar(255),
   state                smallint(1) default 0 comment '0已申请
            1已通过',
   refuse_reson         text,
   primary key (id)
);

/*==============================================================*/
/* Table: es_limitbuy                                           */
/*==============================================================*/
create table es_limitbuy
(
   id                   int not null auto_increment,
   name                 varchar(255),
   start_time           int,
   end_time             int,
   add_time             int,
   img                  varchar(255),
   is_index             smallint(1) default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: es_limitbuy_goods                                     */
/*==============================================================*/
create table es_limitbuy_goods
(
   id                   int not null auto_increment,
   limitbuyid           int,
   goodsid              int,
   price                int,
   primary key (id)
);

/*==============================================================*/
/* Table: es_logi_company                                       */
/*==============================================================*/
create table es_logi_company
(
   id                   mediumint not null auto_increment,
   name                 varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: es_member                                             */
/*==============================================================*/
create table es_member
(
   member_id            mediumint(8) not null auto_increment,
   agentid              int,
   parentid             int default 0,
   lv_id                mediumint(8),
   uname                varchar(50),
   email                varchar(50),
   password             varchar(50),
   regtime              bigint(8),
   pw_answer            varchar(255),
   pw_question          varchar(255),
   name                 varchar(255),
   sex                  smallint(1) comment '0女
            1男',
   birthday             bigint(8),
   advance              decimal(20,3) default 0,
   province_id          int,
   city_id              int,
   region_id            int,
   province             varchar(50),
   city                 varchar(50),
   region               varchar(50),
   address              varchar(255),
   zip                  varchar(50),
   mobile               varchar(50),
   tel                  varchar(50),
   point                int default 0,
   mp                   int default 0,
   QQ                   varchar(50),
   msn                  varchar(50),
   remark               text,
   lastlogin            bigint default 1280629569,
   is_agent             smallint(1) default 0 comment '1.是
            0否',
   logincount           int default 0,
   is_cheked            smallint(1) default 0,
   registerip           varchar(255),
   primary key (member_id)
);

/*==============================================================*/
/* Table: es_member_address                                     */
/*==============================================================*/
create table es_member_address
(
   addr_id              mediumint(8) unsigned not null auto_increment,
   member_id            mediumint(8) unsigned not null default 0,
   name                 varchar(50) default NULL,
   country              varchar(30) default NULL,
   province_id          int default NULL,
   city_id              int default NULL,
   region_id            int default NULL,
   region               varchar(50),
   city                 varchar(50),
   province             varchar(50),
   addr                 varchar(255) default NULL,
   zip                  varchar(20) default NULL,
   tel                  varchar(30) default NULL,
   mobile               varchar(30) default NULL,
   def_addr             int default 0,
   primary key (addr_id),
   key fk_member_addr (member_id)
);

/*==============================================================*/
/* Table: es_member_coupon                                      */
/*==============================================================*/
create table es_member_coupon
(
   memc_code            varchar(255) not null,
   cpns_id              mediumint(8) unsigned not null,
   member_id            mediumint(8) unsigned not null,
   memc_gen_orderid     varchar(15) default NULL,
   memc_source          enum('a','b','c') not null default 'a',
   memc_enabled         enum('true','false') not null default 'true',
   memc_used_times      mediumint(9) default 0,
   memc_gen_time        bigint(8) default NULL,
   primary key (memc_code)
);

/*==============================================================*/
/* Table: es_member_lv                                          */
/*==============================================================*/
create table es_member_lv
(
   lv_id                mediumint(8) not null auto_increment,
   name                 varchar(50),
   default_lv           tinyint(1),
   discount             int comment '如80%为8折',
   point                int default 0,
   primary key (lv_id)
);

/*==============================================================*/
/* Table: es_message                                            */
/*==============================================================*/
create table es_message
(
   msg_id               mediumint(8) unsigned not null auto_increment,
   for_id               mediumint(8) unsigned not null default 0,
   msg_from             varchar(30) not null default 'anonymous',
   from_id              mediumint(8) unsigned default 0,
   from_type            smallint unsigned not null default 0,
   to_id                mediumint(8) unsigned not null default 0,
   msg_to               varchar(50),
   to_type              smallint unsigned not null default 0,
   unread               enum('1','0') not null default '0',
   folder               enum('inbox','outbox') not null default 'inbox',
   email                varchar(255) default NULL,
   tel                  varchar(30) default NULL,
   subject              varchar(100) not null,
   message              longtext not null,
   rel_order            bigint(20) unsigned default 0,
   date_line            bigint(8) not null default 0,
   is_sec               enum('true','false') not null default 'true',
   del_status           enum('0','1','2') default '0',
   disabled             enum('true','false') not null default 'false',
   msg_ip               varchar(20) not null default '',
   msg_type             enum('default','payment') not null default 'default',
   primary key (msg_id)
);

/*==============================================================*/
/* Table: es_order                                              */
/*==============================================================*/
create table es_order
(
   order_id             mediumint(8) not null auto_increment,
   sn                   varchar(50),
   member_id            mediumint(8),
   status               smallint(1) comment '-2退货
            -1退款
            0未付款
            1已付款
            2已发货
            3.完成
            4作废',
   pay_status           smallint(1) comment '0未付款
            1已付款
            2已经退款
            3部分退款
            4部分付款',
   ship_status          smallint(1) comment '0未发货
            1已发货
            2.已退货
            3 部分退货
            4 部分发货',
   shipping_id          mediumint(8),
   shipping_type        varchar(255),
   shipping_area        varchar(255),
   payment_id           mediumint(8),
   payment_name         varchar(50),
   payment_type         varchar(50),
   paymoney             decimal(20,2),
   goods                text,
   create_time          bigint(8),
   ship_name            varchar(255),
   ship_addr            varchar(255),
   ship_zip             varchar(20),
   ship_email           varchar(50),
   ship_mobile          varchar(50),
   ship_tel             varchar(50),
   ship_day             varchar(255),
   ship_time            varchar(255),
   is_protect           tinyint(1),
   protect_price        decimal(20,3),
   goods_amount         decimal(20,2),
   shipping_amount      decimal(20,3),
   order_amount         decimal(20,3),
   weight               decimal(20,3),
   goods_num            mediumint(8),
   gainedpoint          int default 0,
   consumepoint         int default 0,
   remark               longtext,
   disabled             tinyint(1),
   discount             decimal(20,3),
   imported             smallint(1) default 0 comment '0未转账
            1已转账',
   pimported            smallint(1) default 0,
   complete_time        int default 0,
   primary key (order_id)
);

/*==============================================================*/
/* Table: es_order_gift                                         */
/*==============================================================*/
create table es_order_gift
(
   order_id             mediumint(8),
   gift_id              mediumint(8),
   gift_name            varchar(255),
   getmethod            enum('present','exchange'),
   point                int(10),
   num                  int(10) default 0,
   shipnum              int(10) default 0
);

/*==============================================================*/
/* Table: es_order_items                                        */
/*==============================================================*/
create table es_order_items
(
   item_id              mediumint(8) not null auto_increment,
   order_id             mediumint(8),
   goods_id             mediumint(8),
   spec_id              mediumint(8),
   num                  mediumint(8),
   ship_num             mediumint(8),
   name                 varchar(255),
   price                decimal(20,3),
   gainedpoint          int default 0,
   addon                text,
   state                smallint(1) default 0 comment '状态:
            0正常
            1申请退货
            2已退货
            3申请换货
            4已换货',
   primary key (item_id)
);

/*==============================================================*/
/* Table: es_order_log                                          */
/*==============================================================*/
create table es_order_log
(
   log_id               mediumint(8) not null auto_increment,
   order_id             mediumint(8),
   op_id                mediumint(8),
   op_name              varchar(50),
   message              longtext,
   op_time              bigint(8),
   primary key (log_id)
);

/*==============================================================*/
/* Table: es_order_pmt                                          */
/*==============================================================*/
create table es_order_pmt
(
   pmt_id               mediumint(8),
   order_id             mediumint(8),
   pmt_amount           decimal(20,3),
   pmt_describe         varchar(255)
);

/*==============================================================*/
/* Table: es_package_product                                    */
/*==============================================================*/
create table es_package_product
(
   product_id           mediumint(8) unsigned not null,
   goods_id             mediumint(8) unsigned not null,
   discount             decimal(5,3) default NULL,
   pkgnum               mediumint(8) unsigned not null default 1,
   primary key (product_id, goods_id)
);

/*==============================================================*/
/* Table: es_payment_cfg                                        */
/*==============================================================*/
create table es_payment_cfg
(
   id                   mediumint(8) not null auto_increment,
   name                 varchar(255),
   config               longtext,
   biref                longtext,
   pay_fee              decimal(20,2),
   type                 varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: es_payment_logs                                       */
/*==============================================================*/
create table es_payment_logs
(
   payment_id           mediumint(8) not null auto_increment,
   order_id             mediumint(8),
   member_id            mediumint(8),
   account              varchar(50),
   bank                 varchar(50),
   pay_user             varchar(50),
   money                decimal(20,3),
   pay_cost             decimal(20,3),
   pay_type             varchar(50),
   pay_method           varchar(50),
   remark               longtext,
   op_id                mediumint(8),
   type                 smallint(1) comment '1收款
            2退款',
   status               smallint(1) comment '1成功
            2进行中',
   create_time          bigint(8),
   primary key (payment_id)
);

/*==============================================================*/
/* Table: es_pmt_goods                                          */
/*==============================================================*/
create table es_pmt_goods
(
   pmt_id               mediumint(8),
   goods_id             mediumint(8)
);

/*==============================================================*/
/* Table: es_pmt_member_lv                                      */
/*==============================================================*/
create table es_pmt_member_lv
(
   pmt_id               mediumint(8),
   lv_id                mediumint(8)
);

/*==============================================================*/
/* Table: es_point_history                                      */
/*==============================================================*/
create table es_point_history
(
   id                   mediumint(8) unsigned not null auto_increment,
   member_id            mediumint(9) not null,
   point                int(10) not null,
   time                 bigint(8) not null,
   reason               varchar(50) not null,
   related_id           bigint(20) unsigned default NULL comment '相关的订单ID/会员ID',
   type                 smallint(1) not null comment '1为获得
            2为消费
            ',
   operator             varchar(50) default NULL,
   point_type           smallint(1) default 0 comment '0等级积分 1为消费积分',
   primary key (id)
);


/*==============================================================*/
/* Table: es_print_tmpl                                         */
/*==============================================================*/
create table es_print_tmpl
(
   prt_tmpl_id          int(10) unsigned not null auto_increment,
   prt_tmpl_title       varchar(100) not null,
   shortcut             enum('false','true') default 'false',
   disabled             enum('false','true') default 'false',
   prt_tmpl_width       tinyint(3) unsigned not null default 100,
   prt_tmpl_height      tinyint(3) unsigned not null default 100,
   prt_tmpl_data        longtext,
   bgimage              varchar(255),
   primary key (prt_tmpl_id)
);

/*==============================================================*/
/* Table: es_product                                            */
/*==============================================================*/
create table es_product
(
   product_id           mediumint(8) not null auto_increment,
   goods_id             mediumint(8),
   name                 varchar(255),
   sn                   varchar(50),
   store                mediumint(8),
   price                decimal(20,3),
   specs                longtext,
   cost                 decimal(20,3),
   weight               decimal(20,3),
   primary key (product_id)
);

/*==============================================================*/
/* Table: es_product_color                                      */
/*==============================================================*/
create table es_product_color
(
   id                   int not null auto_increment,
   productid            int,
   color                varchar(255),
   colorid              int,
   images               text,
   intro                text,
   default_image        smallint default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: es_promotion                                          */
/*==============================================================*/
create table es_promotion
(
   pmt_id               mediumint(8) unsigned not null auto_increment,
   pmts_id              varchar(255) not null,
   pmta_id              mediumint(8) unsigned default NULL,
   pmt_time_begin       bigint(8) default NULL,
   pmt_time_end         bigint(8) default NULL,
   order_money_from     decimal(20,3) not null default 0.000,
   order_money_to       decimal(20,3) not null default 9999999.000,
   seq                  smallint(3) unsigned not null default 0,
   pmt_type             enum('0','1','2') not null default '0' comment '0为促销活动规则
            1为优惠促销规则',
   pmt_belong           enum('0','1') not null default '0',
   pmt_bond_type        enum('0','1','2') not null,
   pmt_describe         longtext,
   pmt_solution         longtext,
   pmt_ifcoupon         smallint(3) unsigned not null default 1,
   pmt_update_time      bigint(8) default 0,
   pmt_basic_type       enum('goods','order') default 'goods',
   disabled             enum('true','false') default 'false',
   pmt_ifsale           enum('true','false') not null default 'true',
   pmt_distype          smallint(3) unsigned not null default 0,
   primary key (pmt_id)
);

/*==============================================================*/
/* Table: es_promotion_activity                                 */
/*==============================================================*/
create table es_promotion_activity
(
   id                   mediumint(8) not null auto_increment,
   name                 varchar(200),
   enable               smallint(1) comment '0为不可用
            1为可用',
   begin_time           bigint(8),
   end_time             bigint(8),
   brief                longtext,
   disabled             smallint(1) comment '删除标注：
            0为未删除
            1为删除',
   primary key (id)
);

/*==============================================================*/
/* Table: es_regions                                            */
/*==============================================================*/
create table es_regions
(
   region_id            int(10) unsigned not null auto_increment,
   p_region_id          int(10) unsigned default NULL,
   region_path          varchar(255) default NULL,
   region_grade         mediumint(8) unsigned default NULL,
   local_name           varchar(50) not null,
   primary key (region_id)
);

/*==============================================================*/
/* Table: es_returns_order                                      */
/*==============================================================*/
create table es_returns_order
(
   id                   int not null auto_increment,
   ordersn              varchar(50),
   orderid              int,
   memberid             int,
   state                smallint(1) comment '0已申请
            1已受理
            2被拒绝
            3进行中
            4.完成
            ',
   type                 smallint comment '0退货	
            1换货
            2返修',
   linkman              varchar(255),
   linktel              varchar(255),
   address              varchar(255),
   attachment           varchar(255),
   facade               smallint(1),
   wrap                 smallint(1),
   invoice              smallint(1) default 0,
   shiptype             varchar(255),
   remark               text,
   add_time             bigint,
   refuse_reson         text,
   primary key (id)
);

/*==============================================================*/
/* Table: es_spec_values                                        */
/*==============================================================*/
create table es_spec_values
(
   spec_value_id        mediumint(8) not null auto_increment,
   spec_id              mediumint(8),
   spec_value           varchar(100),
   spec_image           varchar(255),
   spec_order           mediumint(8),
   spec_type            smallint(1),
   primary key (spec_value_id)
);

/*==============================================================*/
/* Index: fk_spec_value                                         */
/*==============================================================*/
create index fk_spec_value on es_spec_values
(
   spec_id
);

/*==============================================================*/
/* Table: es_specification                                      */
/*==============================================================*/
create table es_specification
(
   spec_id              mediumint(8) not null auto_increment,
   spec_name            varchar(50),
   spec_show_type       smallint(1),
   spec_type            smallint(1),
   spec_memo            varchar(50),
   spec_order           mediumint(8),
   disabled             smallint(1),
   primary key (spec_id)
);

/*==============================================================*/
/* Table: es_tag_rel                                            */
/*==============================================================*/
create table es_tag_rel
(
   tag_id               mediumint(8),
   rel_id               mediumint(8)
);

/*==============================================================*/
/* Table: es_tags                                               */
/*==============================================================*/
create table es_tags
(
   tag_id               mediumint(8) not null auto_increment,
   tag_name             varchar(255),
   rel_count            mediumint(8),
   primary key (tag_id)
);

/*==============================================================*/
/* Table: es_type_brand                                         */
/*==============================================================*/
create table es_type_brand
(
   type_id              mediumint(8),
   brand_id             mediumint(8)
);

drop table if exists es_order_meta;

/*==============================================================*/
/* Table: es_order_meta                                         */
/*==============================================================*/
create table es_order_meta
(
   metaid               int not null auto_increment,
   orderid              int,
   meta_key             varchar(255),
   meta_value           text,
   primary key (metaid)
)
ENGINE = MYISAM;

/*==============================================================*/
/* Index: es_ind_orderex_orderid                                */
/*==============================================================*/
create index es_ind_orderex_orderid on es_order_meta
(
   orderid
);

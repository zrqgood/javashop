INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (1,1,null,1272643200000,1433001600000,0,null,'fs:/attachment/adv/201009211255055900.jpg','../index.html','首页轮换1',0,'','','',0);
INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (2,1,null,1272643200000,1433001600000,0,null,'fs:/attachment/adv/201009211254109510.jpg','../index.html','首页轮换3',3,'','','',0);
INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (13,1,null,1284652800000,1600272000000,0,null,'fs:/attachment/adv/201009211253500869.jpg','../index.html','首页轮换2',1,'','','',0);
INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (14,2,null,1285776000000,1569772800000,0,null,'fs:/attachment/adv/201009301515295573.jpg','../index.html','蓝色经典广告图片1',2,'','','',0);
INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (15,4,null,1312214400000,2574518400000,0,null,'fs:/attachment/adv/201108021410470508.jpg','../index.html','橙色激情广告图片',0,'','','',0);
INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (16,3,null,1312214400000,2574518400000,0,null,'fs:/attachment/adv/201108021411479293.jpg','../index.html','绿色情怡广告图片',1,'','','',0);
INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (17,5,null,1313424000000,1629043200000,0,null,'fs:/attachment/adv/201108222137587830.jpg','../index.html','粉色广告图片一',0,'','','',0);
INSERT INTO es_adv_<userid>_<siteid> (aid,acid,atype,begintime,endtime,isclose,attachment,atturl,url,aname,clickcount,linkman,company,contact,disabled) VALUES (18,5,null,1313424000000,1629043200000,0,null,'fs:/attachment/adv/201108222138413409.jpg','../index.html','粉色广告图片二',0,'','','',0);
INSERT INTO es_adcolumn_<userid>_<siteid> (acid,cname,width,height,description,anumber,atype,rule,disabled) VALUES (1,'红色模板广告位','731px','260px',null,null,0,null,0);
INSERT INTO es_adcolumn_<userid>_<siteid> (acid,cname,width,height,description,anumber,atype,rule,disabled) VALUES (2,'蓝色经典广告位','1002px','252px',null,null,0,null,0);
INSERT INTO es_adcolumn_<userid>_<siteid> (acid,cname,width,height,description,anumber,atype,rule,disabled) VALUES (3,'绿色情怡广告位','968px','365px',null,null,0,null,0);
INSERT INTO es_adcolumn_<userid>_<siteid> (acid,cname,width,height,description,anumber,atype,rule,disabled) VALUES (4,'橙色浪漫广告位','990px','246px',null,null,0,null,0);
INSERT INTO es_adcolumn_<userid>_<siteid> (acid,cname,width,height,description,anumber,atype,rule,disabled) VALUES (5,'粉色童话广告位','980px','290px',null,null,0,null,0);
INSERT INTO es_settings_<userid>_<siteid> (id,code,cfg_value,cfg_group) VALUES (1,null,'{"detail_pic_height":"260","thumbnail_pic_height":"75","album_pic_height":"600","thumbnail_pic_width":"100","album_pic_width":"800","detail_pic_width":"347"}','photo');
INSERT INTO es_settings_<userid>_<siteid> (id,code,cfg_value,cfg_group) VALUES (2,null,'{"anonymous":"0","validcode":"1","pageSize":"5","directShow":"0"}','comments');
INSERT INTO es_settings_<userid>_<siteid> (id,code,cfg_value,cfg_group) VALUES (3,null,'{"state":"close"}','widgetCache');
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (1,0,'首页','index.html',null,0);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (2,0,'最新动态','news-2-1.html',null,1);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (3,0,'产品展示','product-7-1.html',null,2);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (4,0,'品牌营销','brand.html',null,5);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (5,0,'成功案例','cases-12-1.html',null,4);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (6,0,'解决方案','solution-13-1.html',null,3);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (7,0,'客服中心','service.html',null,6);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (8,0,'联系我们','contact.html',null,8);
INSERT INTO es_site_menu_<userid>_<siteid> (menuid,parentid,name,url,target,sort) VALUES (9,0,'人才招聘','job-11-1.html',null,7);
update eop_site set sitename='company新建站点',logofile='fs:/images/default/logo.gif',icofile='fs:/images/default/favicon.ico',keywords='',descript='' where userid=<userid> and id=<siteid>;

create sequence S_ES_CAR_ARTICLE_<userid>_<siteid>
go

CREATE TABLE ES_CAR_ARTICLE_<userid>_<siteid> (
  ID NUMBER(6)  not null,
  SORT INTEGER default 0,
  ADD_TIME INTEGER default 0,
  HIT INTEGER default NULL,
  ABLE_TIME INTEGER default NULL,
  STATE INTEGER default NULL,
  USER_ID INTEGER default NULL,
  CAT_ID INTEGER default NULL,
  IS_COMMEND INTEGER default NULL,
  TITLE VARCHAR2(255) default NULL,
  IMG VARCHAR2(255) default NULL,
  CONTENT CLOB,
  SYS_LOCK INTEGER default 0,
  LASTMODIFIED INTEGER default 0,
  PAGE_TITLE VARCHAR2(255) default NULL,
  PAGE_KEYWORDS VARCHAR2(255) default NULL,
  PAGE_DESCRIPTION CLOB,
  SITE_CODE INTEGER default 100000,
  SITEIDLIST VARCHAR2(255) default NULL,
  constraint PK_ES_CAR_ARTICLE_<userid>_<siteid> primary key (ID)
);

CREATE TRIGGER "TIB_ES_CAR_ARTICLE_<userid>_<siteid>" BEFORE INSERT
ON ES_CAR_ARTICLE_<userid>_<siteid> FOR EACH ROW
DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	DUMMY            INTEGER;
	FOUND            BOOLEAN;
BEGIN
	SELECT S_ES_CAR_ARTICLE_<userid>_<siteid>.NEXTVAL INTO :NEW.ID FROM DUAL;
EXCEPTION
	WHEN INTEGRITY_ERROR THEN
		RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;
go

INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (1,'关于我们',0,'0|1|',8,1,null,'about.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (2,'最新动态',0,'0|2|',1,1,null,'news-2-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (3,'公司新闻',2,'0|2|3|',11,1,null,'news-3-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (4,'行业动态',2,'0|2|4|',12,1,null,'news-4-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (7,'产品展示',0,'0|7|',2,1,null,'product-7-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (8,'品牌营销',0,'0|8|',5,1,null,'brand.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (10,'其它文章',0,'0|10|',6,1,null,'newslist-10-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (11,'人才招聘',0,'0|11|',7,1,null,'job.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (12,'成功案例',0,'0|12|',4,1,null,'cases.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (13,'解决方案',0,'0|13|',3,1,null,'solution-13-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (14,'客服中心',0,'0|14|',6,1,null,'service.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (15,'简约时尚系列',7,'0|7|15|',21,1,null,'product-15-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (16,'自然主义系列',7,'0|7|16|',22,1,null,'product-16-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (17,'色彩斑斓系列',7,'0|7|17|',23,1,null,'product-17-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (18,'家居空间',13,'0|13|18|',32,1,null,'solution-18-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (19,'办公空间',13,'0|13|19|',33,1,null,'solution-19-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (20,'娱乐空间',13,'0|13|20|',31,1,null,'solution-20-1.html','',0);
INSERT INTO es_data_cat_<userid>_<siteid> (cat_id,name,parent_id,cat_path,cat_order,model_id,if_audit,url,detail_url,tositemap) VALUES (21,'商业空间',13,'0|13|21|',34,1,null,'solution-21-1.html','',0);
INSERT INTO es_data_model_<userid>_<siteid> (model_id,china_name,english_name,add_time,project_name,brief,if_audit) VALUES (1,'普通文章','car_article',1284636354948,null,'',0);
INSERT INTO es_data_field_<userid>_<siteid> (field_id,china_name,english_name,data_type,data_size,show_form,show_value,add_time,model_id,save_value,is_validate,taxis,dict_id,is_show) VALUES (1,'标题','title',1,null,'input',null,1284636743486,1,'',1,1,null,1);
INSERT INTO es_data_field_<userid>_<siteid> (field_id,china_name,english_name,data_type,data_size,show_form,show_value,add_time,model_id,save_value,is_validate,taxis,dict_id,is_show) VALUES (2,'图片','img',1,null,'image',null,1284636765602,1,'',0,2,null,0);
INSERT INTO es_data_field_<userid>_<siteid> (field_id,china_name,english_name,data_type,data_size,show_form,show_value,add_time,model_id,save_value,is_validate,taxis,dict_id,is_show) VALUES (3,'内容','content',2,null,'richedit',null,1284636783994,1,'',0,3,null,0);
INSERT INTO es_car_article_<userid>_<siteid> (id,sort,add_time,hit,able_time,state,user_id,cat_id,is_commend,title,img,content,sys_lock,lastmodified,page_title,page_keywords,page_description,site_code,siteidlist) VALUES (1,0,0,null,null,null,null,1,null,'公司简介','fs:/attachment/cms/201009201321221727.gif','<p>	北京市某某办公家具公司是一家系统办公家具公司，系统是产品齐全，系统是服务全程，节约客户宝贵时间，勇担责任，引导客户做出合理决策。本公司主要生产课桌 椅、阶梯椅、剧场椅、橱柜，餐桌、屏风、钢架床，学生公寓家俱，办公家俱等。产品适用于政府机关、学校、宾馆、酒店、家庭厨房，医院、银行等。 公司拥有高效的团队，科学的管理，先进的设备，完美的设计，结合优质的材料，保障公司所生产的每件产品都展示了对人性的充分尊重，使顾客均能享受人性化设 计所带来的健康与舒适。公司运营目标是：质量保证，价格实惠，服务细致，便捷！做办公家具行业的专家是孜孜不倦的目标！专业销售团队：服务就是天职，在质 量保证的基础上，我们最大的销售就是服务，一切为了客户便捷！所有客户咨询留言，我们必将在8个工作小时内答复！本着品位人生，品味生活的态度，对每一件 产品的设计，生产，销售及售后服务设定一套完美而严格的管理制度，以&ldquo;质量第一，客户至上&rdquo;为宗旨，以精益求精的态度对待生产过程中的每一个环节。<br />	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 公司坚持&ldquo;质优价廉&rdquo;的经营原则，使广大客户放心采购。公司将一如既往地竭诚为广大用户提供所值的产品，周到满意的服务。唯美不仅是我们的追求，我们更专 注于设计的自我表现，使丰富的线条让艺术与浪漫在这里牵手，让舒适与温馨在这里对话。 现代线条，人体工程学与装饰纹理，制造出舒适的美丽空间，让不同的风格需求魅力尽现。家具以优雅的造型，艳丽的色彩，体现快乐活泼的气氛。崇尚社会责任， 把绿色环保作为另一个工作重点来突破，打造一个清新健康的工作，生活环境，来回报每一个用户。在服务方面，将时时刻刻执行5年保用，终身诚信服务。艺术的 神韵在这里流转 设计的灵感在这里诞生 我们一直在追寻 我们一直在实践 我们一直在超越，引领家具设计风格迈向个性化时代。</p>',1,0,'','','',100000,null);
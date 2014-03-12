package com.enation.javashop.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.GoodsParam;
import com.enation.javashop.core.model.GoodsType;
import com.enation.javashop.core.model.support.GoodsTypeDTO;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.core.service.IGoodsFieldManager;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.plugin.standard.type.GoodsTypeUtil;

/**
 * 商品类型管理
 * @author kingapex
 * 2010-1-10下午05:53:40
 */
public class GoodsTypeManager extends BaseSupport<GoodsType> implements IGoodsTypeManager {
	private static final Log loger = LogFactory.getLog(GoodsTypeManager.class  );
	private SimpleJdbcTemplate simpleJdbcTemplate;
	private IGoodsFieldManager goodsFieldManager;
	public List listAll() {
		String sql   = "select * from goods_type where disabled=0";
		List typeList = this.baseDaoSupport.queryForList(sql,GoodsType.class);
		
		return typeList;
	}
	/**
	 * 商品类型列表
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	
	public Page pageType(String order, int page, int pageSize) {
		order  = order==null?" type_id desc":order;
		
		String sql  = "select * from goods_type where disabled=0";
		sql+="  order by ";
		sql+=order;
		
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}
	
	
	/**
	 * 商品类型回收站列表
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageTrashType(String order,int page,int pageSize){
		order  = order==null?" type_id desc":order;
		
		String sql  = "select * from goods_type where disabled=1";
		sql+="  order by ";
		sql+=order;
		
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}
	
	
	
	
	/**
	 * 读取一个类型的信息
	 * @param type_id
	 * @return
	 */	
	
	public GoodsTypeDTO get(Integer type_id) {
		String sql ="select * from goods_type where type_id=?";
		GoodsTypeDTO type=(GoodsTypeDTO) baseDaoSupport.queryForObject(sql, GoodsTypeDTO.class, type_id);//(sql, new GoodsTypeMapper(), type_id);该DTO继承商品类型对象，因为要转换类型的属性、参数、品牌值并返回
		if(type==null){
			return null;
		}
		List<Attribute> propList = GoodsTypeUtil.converAttrFormString(type.getProps());
		ParamGroup[] paramGroups  = GoodsTypeUtil.converFormString(type.getParams());
		List brandList  = this.getBrandListByTypeId(type_id);
		type.setPropList(propList);
		type.setParamGroups(paramGroups);
		type.setBrandList(brandList);
		return type;
	}
	
	
	
	/**
	 * 读取某个类型关联的品牌
	 * 
	 * @param type_id
	 * @return
	 */
	public List getBrandListByTypeId(int type_id) {
		String sql ="select b.name name ,b.brand_id brand_id,0 as num from "+this.getTableName("type_brand")+" tb inner join "+this.getTableName("brand")+" b  on    b.brand_id = tb.brand_id where tb.type_id=? and b.disabled=0";

		List list = this.daoSupport.queryForList(sql,type_id);
		return list;
	}



	public List listByTypeId(Integer typeid) {
		String sql ="select b.* from "+this.getTableName("type_brand")+" tb inner join "+this.getTableName("brand")+" b  on    b.brand_id = tb.brand_id where tb.type_id=? and b.disabled=0";
		
		List list =	this.daoSupport.queryForList(sql,  typeid);
	 
		return list;
	}
	
	
	
 

	/**
	 * 将一个json字串转为list
	 * @param props
	 * @return
	 */
	public static List<Attribute> converAttrFormString(String props){
		if (props == null || props.equals(""))
			return new ArrayList();

		JSONArray jsonArray = JSONArray.fromObject(props);
		List<Attribute> list = (List) JSONArray.toCollection(jsonArray,
				Attribute.class);
		
		return list;
	}
	
	
	
	/**
	 * 读取某个类型的属性信息定义
	 * 
	 * @param type_id
	 * @return
	 */
	public List<Attribute> getAttrListByTypeId(int type_id) {
		GoodsTypeDTO type = this.get(type_id);
		if(type.getHave_prop()==0) return new ArrayList<Attribute>();
		return type.getPropList();
	 
	}
	
	

	/**
	 * 读取某个类型的参数信息定义
	 * 
	 * @param type_id
	 * @return
	 */
	public ParamGroup[] getParamArByTypeId(int type_id) {
		String params = getParamsByTypeId(type_id);
		return GoodsTypeUtil.converFormString(params);

	}
	
 

	/**
	 * 读取某个类型的参数定义
	 * 
	 * @param type_id
	 * @return
	 */
	private String getParamsByTypeId(int type_id) {
		
		String sql ="select params from goods_type where disabled=0 and type_id="+type_id;
		IDaoSupport<String> strDaoSuport=(IDaoSupport)this.baseDaoSupport;
	 
		String props = 	strDaoSuport.queryForString(sql);
 
		return props;
	}

	/**
	 * 
	 * 将一个 Attribute 对象的List 生成Json字串,<br/>此字串将会保存到数据库goods_type表的props字段 List
	 * 是根据客户端页面用户输入的属性信息生成的
	 * 
	 * @param propnames
	 * @param proptypes
	 * @param options
	 * @return
	 */
	public String getPropsString(String[] propnames, int[] proptypes,
			String[] options) {
		List list = toAttrList(propnames, proptypes, options);
		JSONArray jsonarray = JSONArray.fromObject(list);

		return jsonarray.toString();
	}

	/**
	 * 将一个ParamGroup 对像的List 生成json字串 <br/> 此字串将会保存在数据库goods_type的params字段 或
	 * js_goods中的params字段
	 * 不同的是：goods_type字段中的参数信息无参数值信息，而js_goods表中的params字段有参数值信息。 <br/> List
	 * 是根据是根据客户端页面用户输入的参数信息生成的
	 * 
	 * @param paramnum
	 * @param groupnames
	 * @param paramnames
	 * @return
	 */
	public String getParamString(String[] paramnums, String[] groupnames,
			String[] paramnames, String[] paramvalues) {
		List list = toParamList(paramnums, groupnames, paramnames, paramvalues);
		JSONArray jsonarray = JSONArray.fromObject(list);
		return jsonarray.toString();
	}

	
	
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public Integer save(GoodsType type) {
		String typeTableName ="goods_type";
		String tbTableName = "type_brand";
		Integer[] brand_id = type.getBrand_ids();

		type.setBrand_ids(null);
		if(type.getParams()!=null && type.getParams().equals("[]")){
			type.setParams(null);
		}
		Integer type_id=null;
		if(type.getType_id()!=null){
			type_id = type.getType_id();
			if(type.getHave_prop()==0){
				type.setProps(null);
			}
			if(type.getHave_parm()==0){
				type.setParams(null);
			}
			this.baseDaoSupport.update(typeTableName, type, "type_id="+ type_id);
			String sql ="delete from "+this.getTableName("type_brand")+" where type_id = ?";
			this.simpleJdbcTemplate.update(sql,type_id);
			
		}else{ //新增
			baseDaoSupport.insert(typeTableName, type);
			type_id  = this.baseDaoSupport.getLastId(typeTableName);
			if(loger.isDebugEnabled()){
				loger.debug("增加商品类型成功 , id is " + type_id);
			}
			
		}

		if (brand_id != null) {
			for (int i = 0; i < brand_id.length; i++) {
				 
				String sql = "insert into  "+this.getTableName("type_brand")+"(type_id,brand_id) values(?,?)";
				simpleJdbcTemplate.update(sql, type_id, brand_id[i]);
			}
		}	
		
		return type_id;
	}


	/**
	 * 根据页面用户输入的信息形成 Attribute 对象的List
	 * 
	 * @param propnames
	 * @param proptypes
	 * @param options
	 * @return
	 */
	private List<Attribute> toAttrList(String[] propnames, int[] proptypes,
			String[] options) {
		List<Attribute> attrList = new ArrayList<Attribute>();
		
		if(propnames!=null && proptypes!= null && options!= null){
			for (int i = 0; i < propnames.length; i++) {
	
				Attribute attribute = new Attribute();
				String name = propnames[i];
				String option = options[i];
				int type = proptypes[i];
	
				attribute.setName(name);
				attribute.setOptions(option);
				attribute.setType(type);
				attrList.add(attribute);
			}
		}
		return attrList;
	}

	/**
	 * 根据页面用户的参数信息 生成 ParamGroup 实体的List
	 * 
	 * @param paramnum
	 * @param groupnames
	 * @param paramnames
	 * @return
	 */
	private List<ParamGroup> toParamList(String[] ar_paramnum,
			String[] groupnames, String[] paramnames, String[] paramvalues) {

		List<ParamGroup> list = new ArrayList<ParamGroup>();
		if (groupnames != null) {
			for (int i = 0; i < groupnames.length; i++) {
				ParamGroup paramGroup = new ParamGroup();
				paramGroup.setName(groupnames[i]);
				List<GoodsParam> paramList = getParamList(ar_paramnum,
						paramnames, paramvalues, i);
				paramGroup.setParamList(paramList);
				list.add(paramGroup);
			}
		}
		return list;
	}

	/**
	 * 根据页面用户输入信息生成GoodsParam 对象的List,此list将被保存在相应的ParamGroup对象中 <br/>
	 * GoodsParam对象只有name 属性会被赋值,value属性不会被处理.
	 * 
	 * @param ar_paramnum
	 * @param paramnames
	 * @param index
	 * @return
	 */
	private List<GoodsParam> getParamList(String[] ar_paramnum,
			String[] paramnames, String[] paramvalues, int groupindex) {
		int[] pos = this.countPos(groupindex, ar_paramnum);
		List<GoodsParam> list = new ArrayList<GoodsParam>();
		for (int i = pos[0]; i < pos[1]; i++) {
			GoodsParam goodsParam = new GoodsParam();

			goodsParam.setName(paramnames[i]);

			if (paramvalues != null) {
				String value = paramvalues[i];
				goodsParam.setValue(value);
			}

			list.add(goodsParam);
		}
		return list;
	}

	/**
	 * 计算 某个参数组 的参数 在 从页面传递过来的paramnames 数组的位置
	 * 
	 * @param groupindex
	 * @param ar_paramnum
	 * @return
	 */
	private int[] countPos(int groupindex, String[] ar_paramnum) {

		int index = 0;
		int start = 0;
		int end = 0;
		int[] r = new int[2];
		for (int i = 0; i <= groupindex; i++) {
			int p_num = Integer.valueOf(ar_paramnum[i]);

			index = index + p_num;
			if (i == groupindex - 1) { // 上一个参数组的参数 结束
				start = index;
			}

			if (i == groupindex) { // 当前的 本参数组的参数开始
				end = index;
			}

		}

		r[0] = start;
		r[1] = end;

		return r;
	}
	
	
	
	/**
	 * 查询类型是否已经被类别关联
	 * @param type_ids
	 * @return
	 */
	private boolean checkUsed(Integer[] type_ids){
		String sql="select count(0) from goods_cat where type_id in";
		return false;
	}
	
	/**
	 * 如果已经被使用返回0
	 * 删除成功返回1
	 */
	public int delete(Integer[] type_ids) {
		
		if(type_ids==null) return 1;
		
		String ids = "";
		for (int i = 0; i < type_ids.length; i++) {
			if(i!=0)
				ids+=",";
			ids+=type_ids[i];
		}
		String sql  ="select count(0) from " + this.getTableName("goods") +" where type_id in ("+ids+")";
		int count = this.daoSupport.queryForInt(sql);
		
		 sql="select count(0) from goods_cat where type_id in ("+ids+")";
		 int catcout=this.baseDaoSupport.queryForInt(sql);
		if(catcout>0){
			return 0;
		} 
		
		if(count==0){
			sql  = "update  goods_type set disabled=1  where type_id in ("+ids+")";
			this.baseDaoSupport.execute(sql) ;
			return 1;
		}
		return 0;
		
	}

	
	
	
	/**
	 * 清空商品类型及其关联的品牌
	 * @param type_id
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	public void clean(Integer[] type_ids){
		if(type_ids==null) return ;
		String ids = "";
		for (int i = 0; i < type_ids.length; i++) {
			if(i!=0)
				ids+=",";
			ids+=type_ids[i];
		}
		String sql  ="delete from goods_type where type_id in("+ids+")";
		this.baseDaoSupport.execute(sql);
		
		sql="delete from "+this.getTableName("type_brand")+" where type_id in("+ids+")";
		this.simpleJdbcTemplate.update(sql);
		
		this.goodsFieldManager.delete(ids);
		
	}
		
	
	/**
	 * 将商品类型从回收站中还原
	 * @param type_ids
	 */
	public void revert(Integer[] type_ids){
		
		if(type_ids==null) return ;
		String ids = "";
		for (int i = 0; i < type_ids.length; i++) {
			if(i!=0)
				ids+=",";
			ids+=type_ids[i];
		}
		String sql  = "update  goods_type set disabled=0  where type_id in ("+  ids +")";
		this.baseDaoSupport.execute(sql);
	}


	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	public boolean checkname(String name, Integer typeid) {
		if(name!=null)name=name.trim();
		String sql  ="select count(0) from goods_type where name=? and type_id!=? and disabled=0";
		if(typeid==null) typeid= 0;
		int count = this.baseDaoSupport.queryForInt(sql, name,typeid);
		if(count>0)
			return true;
		else
			return false;
	}
	public IGoodsFieldManager getGoodsFieldManager() {
		return goodsFieldManager;
	}
	public void setGoodsFieldManager(IGoodsFieldManager goodsFieldManager) {
		this.goodsFieldManager = goodsFieldManager;
	}



	
}

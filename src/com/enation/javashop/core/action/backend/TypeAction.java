package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.GoodsType;
import com.enation.javashop.core.model.support.GoodsTypeDTO;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.core.model.support.TypeSaveState;
import com.enation.javashop.core.service.IBrandManager;
import com.enation.javashop.core.service.IGoodsTypeManager;

/**
 * 类型action 负责商品类型的添加和修改 <br/>
 * 负责类型插件相关业务管理
 * 
 * @author apexking
 * 
 */
public class TypeAction extends WWAction {

	private IGoodsTypeManager goodsTypeManager;
	private IBrandManager brandManager;
	private List brandlist;
	private GoodsTypeDTO goodsType;

	/** ****用于属性信息的读取***** */
	private String[] propnames; // 参数名数组

	private int[] proptypes; // 属性类型数组

	private String[] options; // 可选值列表

	/** ****用于参数信息的读取***** */
	private String paramnum; // 参数组中参数个数信息

	private String[] groupnames; // 参数组名数组

	private String[] paramnames; // 参数名数组

	private Integer type_id;

	private int is_edit;

	private Integer[] id;// 清空商品类型使用

	// 用户选择关联的品牌
	private Integer[] chhoose_brands;

	private static String GOODSTYPE_SESSION_KEY = "goods_type_in_session";

	private static String GOODSTYPE_STATE_SESSION_KEY = "goods_type_state_in_session";

	private String order;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String checkname(){
	
		 if(this.goodsTypeManager.checkname( this.goodsType.getName(),goodsType.getType_id() )){
			 this.json="{result:1}"; //存在返回1
		 }else{
			 this.json="{result:0}";
		 }
		 return this.JSON_MESSAGE;
	}
	
	
	public String list() {
		this.webpage = this.goodsTypeManager.pageType(order, this.getPage(),
				this.getPageSize());
		return "list";
	}

	//
	public String trash_list() {

		this.webpage = this.goodsTypeManager.pageTrashType(order, this
				.getPage(), this.getPageSize());
		return "trash_list";
	}

	

	
	
	//
	public String step1() {

		return "step1";
	}

	//
	public String step2() {

		// *步骤的状态，存在session中，在每步进行更改这个状态*//
		TypeSaveState saveState = new TypeSaveState();
		this.session.put(GOODSTYPE_STATE_SESSION_KEY, saveState);

		GoodsType tempType = getTypeFromSession();
		if (tempType == null) {

			this.session.put(GOODSTYPE_SESSION_KEY, goodsType); // 用页面上收集的信息
		} else { // 用于编辑的时候，先从session取出从数据库里读取的类型信息，然后根据页面收集用户选择的情况改变session中的信息。

			if (is_edit == 1) {
				tempType.setHave_parm(goodsType.getHave_parm());
				tempType.setHave_prop(goodsType.getHave_prop());
				tempType.setJoin_brand(goodsType.getJoin_brand());
				tempType.setIs_physical(goodsType.getIs_physical());
				tempType.setHave_field(goodsType.getHave_field());
				tempType.setName(goodsType.getName());
			} else {
				this.session.put(GOODSTYPE_SESSION_KEY, goodsType);
			}
		}

		String result = getResult();
		if (result == null) {
			this.renderText("参数不正确！");
		}
		return result;
	}

	/**
	 * 编辑类型
	 * 
	 * @return
	 */
	public String edit() {

		this.goodsType = this.goodsTypeManager.get(type_id);
		this.session.put(GOODSTYPE_SESSION_KEY, goodsType);
		this.is_edit = 1;
		return "edit";
	}

	/**
	 * 保存参数信息
	 * 
	 * @return
	 */
	public String saveParams() {
		String[] paramnums = new String[] {};
		if (paramnum != null) {
			if (paramnum.indexOf(",-1") >= 0) {// 检查是否有删除的参数组
				paramnum = paramnum.replaceAll(",-1", "");
			}
			paramnums = paramnum.split(",");
		}

		String params = this.goodsTypeManager.getParamString(paramnums,
				groupnames, paramnames, null);
		GoodsType tempType = getTypeFromSession();
		TypeSaveState saveState = getSaveStateFromSession();
		tempType.setParams(params);
		saveState.setDo_save_params(1);
		return getResult();
	}

	/**
	 * 保存属性信息
	 * 
	 * @return
	 */
	public String saveProps() {

		String props = this.goodsTypeManager.getPropsString(propnames,
				proptypes, options);
		GoodsType tempType = getTypeFromSession();
		tempType.setProps(props);

		// *标志流程属性保存状态为已保存*//
		TypeSaveState saveState = getSaveStateFromSession();
		saveState.setDo_save_props(1);
		return getResult();
	}

	/**.
	 * 保存品牌数据
	 * 
	 * @return
	 */
	public String saveBrand() {
		GoodsType tempType = getTypeFromSession();
		tempType.setBrand_ids(this.chhoose_brands);

		// *标志流程品牌保存状态为已保存*//
		TypeSaveState saveState = getSaveStateFromSession();
		saveState.setDo_save_brand(1);
		return getResult();
	}
	
	
	

	public String save() {
		GoodsTypeDTO tempType = getTypeFromSession();
		tempType.setDisabled(0);
		tempType.setBrandList(null);
		tempType.setPropList(null);
		tempType.setParamGroups(null);
		
		this.type_id =this.goodsTypeManager.save(tempType);
		this.session.remove(GOODSTYPE_SESSION_KEY);
		
		//没有自定义商品字段直接保存,并提示
		if(tempType.getHave_field() ==0){
			this.msgs.add("商品类型保存成功");
			this.urls.put("商品类型列表", "type!list.do");	
			return this.MESSAGE;
		}else{//定义了商品字段，到商品字段定义页面
			return "field";	
		}
		
	}

	
	
	//
	private GoodsTypeDTO getTypeFromSession() {
		Object obj = this.session.get(GOODSTYPE_SESSION_KEY);

		if (obj == null) {
			// this.renderText("参数不正确");
			return null;
		}

		GoodsTypeDTO tempType = (GoodsTypeDTO) obj;

		return tempType;
	}

	//
	/**
	 * 当前流程的保存状态
	 * 
	 * @return
	 */
	private TypeSaveState getSaveStateFromSession() {

		// *从session中取出收集的类型数据*//
		Object obj = this.session.get(GOODSTYPE_STATE_SESSION_KEY);
		if (obj == null) {
			this.renderText("参数不正确");
			return null;
		}
		TypeSaveState tempType = (TypeSaveState) obj;
		return tempType;
	}

	//
	/**
	 * 根据流程状态以及在第一步时定义的
	 * 
	 * @return
	 */
	private String getResult() {

		GoodsType tempType = getTypeFromSession();
		this.goodsType = getTypeFromSession();
		TypeSaveState saveState = getSaveStateFromSession();
		String result = null;

		if (tempType.getHave_prop() != 0 && saveState.getDo_save_props() == 0) { // 用户选择了此类型有属性，同时还没有保存过
			result = "add_props";
		} else if (tempType.getHave_parm() != 0
				&& saveState.getDo_save_params() == 0) { // 用户选择了此类型有参数，同时还没有保存过
			result = "add_parms";
		} else if (tempType.getJoin_brand() != 0
				&& saveState.getDo_save_brand() == 0) { // 用户选择了此类型有品牌，同时还没有保存过
			brandlist= this.brandManager.list();
			result = "join_brand";
		} else {

			result = save();
		}

		return result;
	}

	/**
	 * 将商品类型放入回收站
	 * 
	 * @return
	 */
	public String delete() {
		try {
			int result  = goodsTypeManager.delete(id);
			if(result ==1)
			this.json = "{'result':0,'message':'删除成功'}";
			else
			this.json ="{'result':1,'message':'此类型存在与之关联的商品或类别，不能删除。'}";
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 清空商品类型
	 * 
	 * @return
	 */
	public String clean() {
		try {
			goodsTypeManager.clean(id);
			this.json = "{'result':0,'message':'清除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'清除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 从回收站中还原商品类型
	 * 
	 * @return
	 */
	public String revert() {
		try {
			goodsTypeManager.revert(id);
			this.json = "{'result':0,'message':'还原成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'还原失败'}";
		}
		return this.JSON_MESSAGE;
	}

	private List attrList;// 某个类型的属性定义列表
	private ParamGroup[] paramAr; // 某个类型的参数定义列表
	//

	// 读取某个类型下的商品属性定义并形成输入html
	// 被ajax抓取用
	public String disPropsInput() {
		attrList = this.goodsTypeManager.getAttrListByTypeId(type_id);
		attrList =attrList==null || attrList.isEmpty() ?null:attrList;
		return "props_input"; 
	}

	//
	// 读取某个类型下的商品参数定义并形成输入html
	// 被ajax抓取用
	public String disParamsInput() {
		this.paramAr = this.goodsTypeManager.getParamArByTypeId(type_id);
		return "params_input";
	}


	//添加或修改商品时异步读取品牌列表
	public String listBrand(){
		this.brandlist = this.goodsTypeManager.listByTypeId(type_id);
		return "brand_list";
	}	

	public List getAttrList() {
		return attrList;
	}

	public void setAttrList(List attrList) {
		this.attrList = attrList;
	}

	public ParamGroup[] getParamAr() {
		return paramAr;
	}

	public void setParamAr(ParamGroup[] paramAr) {
		this.paramAr = paramAr;
	}

	public GoodsTypeDTO getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsTypeDTO goodsType) {
		this.goodsType = goodsType;
	}

	public String[] getPropnames() {
		return propnames;
	}

	public void setPropnames(String[] propnames) {
		this.propnames = propnames;
	}

	public int[] getProptypes() {
		return proptypes;
	}

	public void setProptypes(int[] proptypes) {
		this.proptypes = proptypes;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public IGoodsTypeManager getGoodsTypeManager() {
		return goodsTypeManager;
	}

	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}

	public String[] getGroupnames() {
		return groupnames;
	}

	public void setGroupnames(String[] groupnames) {
		this.groupnames = groupnames;
	}

	public String[] getParamnames() {
		return paramnames;
	}

	public void setParamnames(String[] paramnames) {
		this.paramnames = paramnames;
	}

	public String getParamnum() {
		return paramnum;
	}

	public void setParamnum(String paramnum) {
		this.paramnum = paramnum;
	}

	public Integer[] getChhoose_brands() {
		return chhoose_brands;
	}

	public void setChhoose_brands(Integer[] chhoose_brands) {
		this.chhoose_brands = chhoose_brands;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public int getIs_edit() {
		return is_edit;
	}

	public void setIs_edit(int is_edit) {
		this.is_edit = is_edit;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public List getBrandlist() {
		return brandlist;
	}

	public void setBrandlist(List brandlist) {
		this.brandlist = brandlist;
	}

}

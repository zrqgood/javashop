package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.DlyType;
import com.enation.javashop.core.model.support.DlyTypeConfig;
import com.enation.javashop.core.model.support.TypeAreaConfig;
import com.enation.javashop.core.service.IAreaManager;
import com.enation.javashop.core.service.IDlyTypeManager;
import com.enation.javashop.core.service.ILogiManager;
 
/**
 * 配送方式管理
 * @author kingapex
 *2010-3-26上午08:14:49
 */
public class DlyTypeAction extends WWAction {

	private Integer type_id;

	private String id; // 删除用

	private List logiList;
	private DlyType type;

	private IDlyTypeManager dlyTypeManager;
	private ILogiManager logiManager;	
	
	
	private Integer firstunit;
	private Integer continueunit;
	private Double[] firstprice;
	private Double[] continueprice;
	private String[] areaGroupName;
	private String[] areaGroupId;
	private Integer[] has_cod;//是否支持货到付款 
	private Integer[] useexp; //是否使用公式
	private String[] expressions;
	private String exp; //异步校验所用
	
	
	private Integer defAreaFee; // 是否启用默认配置
	private Boolean isEdit;
	
	public String add_type(){
		isEdit = false;
		logiList =this.logiManager.list();
		return "add";
	}
	
	public String edit(){
		isEdit= true;
		type = this.dlyTypeManager.getDlyTypeById(type_id);
		logiList =this.logiManager.list();
		return "edit";
	}
	
	public String list(){
		this.webpage = this.dlyTypeManager.pageDlyType( this.getPage(), this.getPageSize());
		return "list";
	}

	/**
	 *  校验公式
	 * @return
	 */
	public String vaildateExp(){
		
		return null;
	}
	
	
	public String saveAdd() {
		
		if(type.getIs_same().intValue()==1){
			this.saveSame(false);
		}
		
		if(type.getIs_same().intValue()==0){
			this.saveDiff(false);
		}
		
		this.msgs.add("配送方式添加成功");
		this.urls.put("配送方式列表", "dlyType!list.do");
		return this.MESSAGE;
	}
	
 
	
	/**
	 * 添加统一式配置
	 */
	private void saveSame(boolean isUpdate){
		
		DlyTypeConfig config = new DlyTypeConfig();
		config.setFirstunit(this.firstunit); //首重 
		config.setContinueunit(this.continueunit); //续重 
	
		config.setFirstprice(this.firstprice[0]); //首重费用
		config.setContinueprice(this.continueprice[0]); //续重费用
		
		//启用公式
		if( this.useexp[0].intValue() ==1 ){
			config.setExpression(expressions[0]);
		}
		
		type.setHas_cod(this.has_cod[0]);
		config.setHave_cod(type.getHas_cod());
		
		if(isUpdate){
			this.dlyTypeManager.edit(type, config);
		}else{
			this.dlyTypeManager.add(type, config);
		}
		
	}
	
	private void saveDiff(boolean isUpdate){
		DlyTypeConfig config = new DlyTypeConfig();
		
		config.setFirstunit(this.firstunit); //首重 
		config.setContinueunit(this.continueunit); //续重 		
		config.setDefAreaFee(this.defAreaFee);//默认费用设置
		
		//启用默认费用配置,费用数组第一个元素
		if(defAreaFee!=null &&defAreaFee.intValue()==1){
			config.setFirstprice(this.firstprice[0]);
			config.setContinueprice(this.continueprice[0]);
			if( this.useexp[0].intValue() ==1 ){
				config.setExpression(expressions[0]);
			}
			
		}
		TypeAreaConfig[] configArray= new TypeAreaConfig[this.areaGroupId.length];
		int price_index=0;
		
		for(int i=0;i<this.areaGroupId.length;i++){
			
			//开启了默认费用配置,则费用数组会比地区数组大一维
			if(defAreaFee!=null &&this.defAreaFee.intValue()==1){
				price_index=i+1;
			}else{
				price_index =i;
			}
			
			TypeAreaConfig areaConfig = new TypeAreaConfig();
			
			//首重和续重使用统一的设置
			areaConfig.setContinueunit(config.getContinueunit());
			areaConfig.setFirstunit(config.getFirstunit());
			areaConfig.setUseexp(this.useexp[price_index]);
			
			areaConfig.setAreaId(this.areaGroupId[i]);
			areaConfig.setAreaName(this.areaGroupName[i]);
			
			//启用公式
			if(this.useexp[price_index].intValue()==1){
				areaConfig.setExpression(expressions[price_index]);
			}else{
				areaConfig.setFirstprice(this.firstprice[price_index]);
				areaConfig.setContinueprice(this.continueprice[price_index]);
			}
			areaConfig.setHave_cod(this.has_cod[i]);

			configArray[i]=areaConfig;
		}
		if(isUpdate){
			this.dlyTypeManager.edit(type, config, configArray);
		}else{
			this.dlyTypeManager.add(type, config, configArray);
		}
	}
	
	
	public String saveEdit() {
 
		
		if(type.getIs_same().intValue()==1){
			this.saveSame(true);
		}
		
		if(type.getIs_same().intValue()==0){
			this.saveDiff(true);
		}
		
		
		this.msgs.add("配送方式修改成功");
		this.urls.put("配送方式列表", "dlyType!list.do");
		return this.MESSAGE;
	}

	public String delete() {
		this.dlyTypeManager.delete(id);
		this.json = "{'result':0,'message':'删除成功'}";
		return this.JSON_MESSAGE;
	}

	 

	public IDlyTypeManager getDlyTypeManager() {
		return dlyTypeManager;
	}

	public void setDlyTypeManager(IDlyTypeManager dlyTypeManager) {
		this.dlyTypeManager = dlyTypeManager;
	}

	public DlyType getType() {
		return type;
	}

	public void setType(DlyType type) {
		this.type = type;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

 
	public List getLogiList() {
		return logiList;
	}

	public void setLogiList(List logiList) {
		this.logiList = logiList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public ILogiManager getLogiManager() {
		return logiManager;
	}

	public void setLogiManager(ILogiManager logiManager) {
		this.logiManager = logiManager;
	}

	public Integer getFirstunit() {
		return firstunit;
	}

	public void setFirstunit(Integer firstunit) {
		this.firstunit = firstunit;
	}

	public Integer getContinueunit() {
		return continueunit;
	}

	public void setContinueunit(Integer continueunit) {
		this.continueunit = continueunit;
	}

	public Double[] getFirstprice() {
		return firstprice;
	}

	public void setFirstprice(Double[] firstprice) {
		this.firstprice = firstprice;
	}

	public Double[] getContinueprice() {
		return continueprice;
	}

	public void setContinueprice(Double[] continueprice) {
		this.continueprice = continueprice;
	}

	public Integer getDefAreaFee() {
		return defAreaFee;
	}

	public void setDefAreaFee(Integer defAreaFee) {
		this.defAreaFee = defAreaFee;
	}

	public String[] getAreaGroupName() {
		return areaGroupName;
	}

	public void setAreaGroupName(String[] areaGroupName) {
		this.areaGroupName = areaGroupName;
	}

	public String[] getAreaGroupId() {
		return areaGroupId;
	}

	public void setAreaGroupId(String[] areaGroupId) {
		this.areaGroupId = areaGroupId;
	}

	public Integer[] getUseexp() {
		return useexp;
	}

	public void setUseexp(Integer[] useexp) {
		this.useexp = useexp;
	}

	public String[] getExpressions() {
		return expressions;
	}

	public void setExpressions(String[] expressions) {
		this.expressions = expressions;
	}

	public Integer[] getHas_cod() {
		return has_cod;
	}

	public void setHas_cod(Integer[] hasCod) {
		has_cod = hasCod;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}
 
}

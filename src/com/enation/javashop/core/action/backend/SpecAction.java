package com.enation.javashop.core.action.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.SpecValue;
import com.enation.javashop.core.model.Specification;
import com.enation.javashop.core.service.ISpecManager;
import com.enation.javashop.core.service.ISpecValueManager;

/**
 * 规格ation
 * @author kingapex
 *2010-3-7下午06:50:20
 */
public class SpecAction extends WWAction {
	
	private ISpecManager specManager;
	private ISpecValueManager specValueManager;
	private Integer spec_id;
	private Map specView;
	private List specList ;
	private List valueList;
	private Specification spec;
	private String[] valueArray;
	private String[] imageArray;
	private Integer[] id;
	
	public String checkUsed(){
		if(this.specManager.checkUsed(id)){
			this.json ="{result:1}";
		}else{
			this.json ="{result:0}";
		}
		
		return this.JSON_MESSAGE;
	}
	
	public String list(){
		specList = specManager.list();
		return "list";
	}
	
	public String add(){
		return this.INPUT;
	}
	
	public String saveAdd(){
		this.fillSpecValueList();
		this.specManager.add(spec, valueList);
		this.msgs.add("规格添加成功");
		this.urls.put("规格列表", "spec!list.do");
		return this.MESSAGE;
	}
	
	public String edit(){
		specView = this.specManager.get(spec_id);
		this.valueList = this.specValueManager.list(spec_id);
		return this.INPUT;
	}
	
	public String saveEdit(){
		this.fillSpecValueList();
		this.specManager.edit(spec, valueList);
		this.msgs.add("规格添加成功");
		this.urls.put("规格列表", "spec!list.do");
		return this.MESSAGE;
	}
	
	private List<SpecValue> fillSpecValueList(){
		valueList = new ArrayList<SpecValue>();
		
		if(valueArray!=null ){
			for(int i=0;i<valueArray.length;i++){
				String value =valueArray[i];
	
				SpecValue specValue = new SpecValue();
				specValue.setSpec_value(value);
				if( imageArray!=null){
					String image = imageArray[i];
					if(image == null || image.equals("")) image ="../shop/admin/spec/image/spec_def.gif";
					else
					image  =UploadUtil.replacePath(image);
					specValue.setSpec_image(image);
				}else{
					specValue.setSpec_image( "../shop/admin/spec/image/spec_def.gif" );
				}
				valueList.add(specValue);
			}
		}
		return valueList;
	}
	
		
	public String delete(){
		this.specManager.delete(id);
		this.json ="{'result':0,'message':'规格删除成功'}";
		return this.JSON_MESSAGE;
	}

 
	
	public ISpecManager getSpecManager() {
		return specManager;
	}

	public void setSpecManager(ISpecManager specManager) {
		this.specManager = specManager;
	}

	public ISpecValueManager getSpecValueManager() {
		return specValueManager;
	}

	public void setSpecValueManager(ISpecValueManager specValueManager) {
		this.specValueManager = specValueManager;
	}

	public List getSpecList() {
		return specList;
	}

	public void setSpecList(List specList) {
		this.specList = specList;
	}

	public Specification getSpec() {
		return spec;
	}

	public void setSpec(Specification spec) {
		this.spec = spec;
	}

	public String[] getValueArray() {
		return valueArray;
	}

	public void setValueArray(String[] valueArray) {
		this.valueArray = valueArray;
	}

	public String[] getImageArray() {
		return imageArray;
	}

	public void setImageArray(String[] imageArray) {
		this.imageArray = imageArray;
	}

	public Integer getSpec_id() {
		return spec_id;
	}

	public void setSpec_id(Integer specId) {
		spec_id = specId;
	}

	public Map getSpecView() {
		return specView;
	}

	public void setSpecView(Map specView) {
		this.specView = specView;
	}

	public List getValueList() {
		return valueList;
	}

	public void setValueList(List valueList) {
		this.valueList = valueList;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}
	
	
	
	
}

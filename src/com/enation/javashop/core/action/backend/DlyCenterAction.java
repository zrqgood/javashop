package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.DlyCenter;
import com.enation.javashop.core.service.IDlyCenterManager;
import com.enation.javashop.core.service.IRegionsManager;

public class DlyCenterAction extends WWAction {
	
	private IDlyCenterManager dlyCenterManager;
	private IRegionsManager regionsManager;
	
	private DlyCenter dlyCenter;
	private Integer dlyCenterId;
	private Integer[] id;
	private List<DlyCenter> list;
	private List provinceList;
	private List cityList;
	private List regionList;
	
	public String add(){
		provinceList = this.regionsManager.listProvince();
		return "add";
	}
	
	public String edit(){
		dlyCenter = dlyCenterManager.get(dlyCenterId);
		provinceList = this.regionsManager.listProvince();
		if (dlyCenter.getProvince_id() != null) {
			cityList = this.regionsManager.listCity(dlyCenter.getProvince_id());
		}
		if (dlyCenter.getCity_id() != null) {
			regionList = this.regionsManager.listRegion(dlyCenter.getCity_id());
		}
		return "edit";
	}
	
	public String list(){
		list = dlyCenterManager.list();
		return "list";
	}
	
	public String delete(){
		try {
			this.dlyCenterManager.delete(id);
			this.json = "{result:0,message:'发货信息删除成功'}";
		} catch (RuntimeException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e);
			}
			this.json = "{result:1,message:\"发货信息删除失败：" + e.getMessage() + "\"}";

		}
		return this.JSON_MESSAGE;
	}
	
	public String saveAdd(){
		try{
			dlyCenterManager.add(dlyCenter);
			this.msgs.add("发货信息添加成功");
			
		}catch(Exception e){
			e.printStackTrace();
			this.msgs.add("发货信息添加失败");
		}
		this.urls.put("发货信息管理", "dlyCenter!list.do");
		return this.MESSAGE;
	}
	
	public String saveEdit(){
		try{
			dlyCenterManager.edit(dlyCenter);
			this.msgs.add("发货信息修改成功");
			
		}catch(Exception e){
			e.printStackTrace();
			this.msgs.add("发货信息修改失败");
		}
		this.urls.put("发货信息管理", "dlyCenter!list.do");
		return this.MESSAGE;
	}

	public IDlyCenterManager getDlyCenterManager() {
		return dlyCenterManager;
	}

	public void setDlyCenterManager(IDlyCenterManager dlyCenterManager) {
		this.dlyCenterManager = dlyCenterManager;
	}

	public DlyCenter getDlyCenter() {
		return dlyCenter;
	}

	public void setDlyCenter(DlyCenter dlyCenter) {
		this.dlyCenter = dlyCenter;
	}

	public Integer getDlyCenterId() {
		return dlyCenterId;
	}

	public void setDlyCenterId(Integer dlyCenterId) {
		this.dlyCenterId = dlyCenterId;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public List<DlyCenter> getList() {
		return list;
	}

	public void setList(List<DlyCenter> list) {
		this.list = list;
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

	public List getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List provinceList) {
		this.provinceList = provinceList;
	}

	public List getCityList() {
		return cityList;
	}

	public void setCityList(List cityList) {
		this.cityList = cityList;
	}

	public List getRegionList() {
		return regionList;
	}

	public void setRegionList(List regionList) {
		this.regionList = regionList;
	}

}

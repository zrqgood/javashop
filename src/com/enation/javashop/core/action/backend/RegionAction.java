package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Regions;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 地区管理
 * 
 * @author lzf<br/>
 *         2010-4-22下午12:52:49<br/>
 *         version 1.0
 */
public class RegionAction extends WWAction {
	private IRegionsManager regionsManager;
	private List listRegion;
	private Integer parentid;
	private Regions regions;
	private Integer region_id;
	private Integer regiongrade;
	
	public String list(){
		return "list";
	}
	
	public String listChildren(){
		listRegion = regionsManager.listChildren(parentid);
		return "listChildren";
	}
	
	public String add(){
		return "add";
	}
	
	public String saveAdd(){
		try{
			regionsManager.add(regions);
			this.msgs.add("地区添加成功");
			
		}catch(Exception e){
			e.printStackTrace();
			this.msgs.add("地区添加失败");
		}
		this.urls.put("地区管理", "region!list.do");
		return this.MESSAGE;
	}
	
	public String edit(){
		regions = regionsManager.get(region_id);
		return "edit";
	}
	
	public String saveEdit(){
		try{
			regionsManager.update(regions);
			this.msgs.add("地区修改成功");
			
		}catch(Exception e){
			e.printStackTrace();
			this.msgs.add("地区修改失败");
		}
		this.urls.put("地区管理", "region!list.do");
		return this.MESSAGE;
	}
	
	public String delete(){
		try {
			this.regionsManager.delete(region_id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;
	}
	
	public String reset(){
		try {
			this.regionsManager.reset();
			this.json = "{'result':0,'message':'成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'失败'}";
			e.printStackTrace();
		}
		return  this.JSON_MESSAGE;
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

	public List getListRegion() {
		return listRegion;
	}

	public void setListRegion(List listRegion) {
		this.listRegion = listRegion;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Regions getRegions() {
		return regions;
	}

	public void setRegions(Regions regions) {
		this.regions = regions;
	}

	public Integer getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Integer regionId) {
		region_id = regionId;
	}

	public Integer getRegiongrade() {
		return regiongrade;
	}

	public void setRegiongrade(Integer regiongrade) {
		this.regiongrade = regiongrade;
	}
}

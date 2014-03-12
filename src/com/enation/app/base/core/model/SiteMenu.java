package com.enation.app.base.core.model;

import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 站点菜单
 * @author kingapex
 *
 */
public class SiteMenu {
	
	private Integer menuid;
	private Integer parentid;
	private String name;
	private String url;
	private String target;
	private Integer sort; 
	public  SiteMenu(){
		hasChildren =false;
	}
	
	//子列表，非数据库字段
	private List<SiteMenu> children;
	
	//是否有子，非数据库字段
	private boolean hasChildren;
	
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	@NotDbField
	public List<SiteMenu> getChildren() {
		return children;
	}
	public void setChildren(List<SiteMenu> children) {
		this.children = children;
	}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@NotDbField
	public boolean getHasChildren() {
		hasChildren = this.children==null|| this.children.isEmpty() ?false:true;
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	
}

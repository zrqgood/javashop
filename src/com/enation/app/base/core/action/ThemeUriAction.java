package com.enation.app.base.core.action;

import java.util.ArrayList;
import java.util.List;

import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.framework.action.WWAction;

public class ThemeUriAction extends WWAction {
	private IThemeUriManager themeUriManager;
	private List uriList;
	private ThemeUri themeUri;
	private int id;
	private int[] ids;
	private String[] uri;
	private String[] path;
	private String[] pagename;
	private int[] point;
	
	public String list(){
		uriList  = themeUriManager.list();
		return "list";
	}
	
	
	public String add(){
		return  "input";
	}
	public String edit(){
		themeUri = this.themeUriManager.get(id);
		return "input";
	}
	
	public String saveAdd(){
		try{
			this.themeUriManager.add(themeUri);
			this.json ="{result:1}";
		}catch(RuntimeException e){
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	
	public String saveEdit(){
		try{
			this.themeUriManager.edit(themeUri);
			this.json ="{result:1}";
		}catch(RuntimeException e){
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}		
		return this.JSON_MESSAGE;
	}
	
	
	public String batchEdit(){
		try{
			List<ThemeUri> uriList  = new ArrayList<ThemeUri>();
			if(uri!=null ){
				for(int i=0;i<uri.length;i++){
					ThemeUri themeUri  = new ThemeUri();
					themeUri.setUri( uri[i] );
					themeUri.setId(ids[i]);
					themeUri.setPath(path[i]);
					themeUri.setPagename(pagename[i]);
					themeUri.setPoint(point[i]);
					uriList.add(themeUri);
				}
			}
			this.themeUriManager.edit(uriList);
			this.json ="{result:1}";
		}catch(RuntimeException e){
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	
	public String delete(){
		try{
			this.themeUriManager.delete(id);
			this.json ="{result:1}";
		}catch(RuntimeException e){
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		
		return this.JSON_MESSAGE;
	}
	
	public IThemeUriManager getThemeUriManager() {
		return themeUriManager;
	}

	public void setThemeUriManager(IThemeUriManager themeUriManager) {
		this.themeUriManager = themeUriManager;
	}

	public List getUriList() {
		return uriList;
	}

	public void setUriList(List uriList) {
		this.uriList = uriList;
	}

	public ThemeUri getThemeUri() {
		return themeUri;
	}

	public void setThemeUri(ThemeUri themeUri) {
		this.themeUri = themeUri;
	}

	public String[] getUri() {
		return uri;
	}

	public void setUri(String[] uri) {
		this.uri = uri;
	}

	public String[] getPath() {
		return path;
	}

	public void setPath(String[] path) {
		this.path = path;
	}

	public String[] getPagename() {
		return pagename;
	}

	public void setPagename(String[] pagename) {
		this.pagename = pagename;
	}

	public int[] getPoint() {
		return point;
	}

	public void setPoint(int[] point) {
		this.point = point;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}
	
}

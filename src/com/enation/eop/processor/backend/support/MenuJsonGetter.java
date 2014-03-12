package com.enation.eop.processor.backend.support;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.AuthAction;
import com.enation.app.base.core.service.auth.IAdminUserManager;
import com.enation.app.base.core.service.auth.IPermissionManager;
import com.enation.eop.processor.AbstractFacadeProcessor;
import com.enation.eop.processor.FacadePage;
import com.enation.eop.processor.core.Response;
import com.enation.eop.processor.core.StringResponse;
import com.enation.eop.resource.IMenuManager;
import com.enation.eop.resource.model.AdminUser;
import com.enation.eop.resource.model.Menu;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;

public class MenuJsonGetter extends AbstractFacadeProcessor {
	public MenuJsonGetter (FacadePage page) {
		super(page);
	}

	
	protected Response process() {		
	 
		Response response = new StringResponse();
		String menu = getMenuJson();
		response.setContent(menu);
		return response;
	}
	
	public  String getMenuJson(){
		StringBuffer json = new StringBuffer();
		
		/*
		 * 调用核心api读取站点的菜单
		 */
		IMenuManager menuManager = SpringContextHolder.getBean("menuManager");
		List<Menu> tempMenuList  = menuManager.getMenuList();
		List<Menu> menuList=  new ArrayList<Menu>();
		IPermissionManager permissionManager = SpringContextHolder.getBean("permissionManager");
		IAdminUserManager adminUserManager =  SpringContextHolder.getBean("adminUserManager");
		AdminUser user  =adminUserManager.getCurrentUser();
		user = adminUserManager.get(user.getUserid());
		List<AuthAction> authList = permissionManager.getUesrAct(user.getUserid(), "menu");
		
		for(Menu menu:tempMenuList){
			if(menu.getMenutype().intValue() == Menu.MENU_TYPE_APP){

				if(user.getFounder()!=1){
					if( !checkPermssion(menu,authList) ){
						continue; 
					}
				}
			} 
			
			menuList.add(menu); 
		}
		List<Menu> syslist  = getMenuList(Menu.MENU_TYPE_SYS,menuList);
		List<Menu> applist  = getMenuList(Menu.MENU_TYPE_APP,menuList);
		List<Menu> extlist  = getMenuList(Menu.MENU_TYPE_EXT,menuList);
		
		json.append("var menu ={");
			json.append("'sys':[");
			json.append(toJson(syslist,menuList));
			json.append("]");
			
			json.append(",'app':[");
			json.append(toJson(applist,menuList));
			json.append("]");	
	
			json.append(",'ext':[");
			json.append(toJson(extlist,menuList));
			json.append("]");	
		json.append("};");
		HttpServletRequest request  =ThreadContextHolder.getHttpRequest();
		json.append("var mainpage=true;");
		json.append("var domain='"+request.getServerName()+"';");
		json.append("var runmode="+EopSetting.RUNMODE+";");
		json.append("var app_path='"+request.getContextPath()+"';");
		
		return json.toString();
		

	}
	
	/**
	 * 将一个menuList转为json数组
	 * @param menuList
	 * @return
	 */
	public  String toJson(List<Menu> menuList,List<Menu> allList){
		StringBuffer menuItem = new StringBuffer();
		int i =0;
		
		for(Menu menu:menuList ){
			
			if(i!=0) menuItem.append(",");
			menuItem.append( toJson (menu,allList));
			i++;
			
		}
		
		return menuItem.toString();
	}
	
	
	private boolean checkPermssion(Menu menu,List<AuthAction> authList){
		for(AuthAction auth:authList){
			String values =  auth.getObjvalue();
			if(values!=null){
				String[] value_ar = values.split(",");
				for(String v:value_ar){
					if(v.equals(""+menu.getId().intValue())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 根据menu实体生成json字串
	 * @param menu
	 * @return
	 */
	private  String toJson(Menu menu,List<Menu> menuList){
		

		String title  =  menu.getTitle();
		String url = menu.getUrl();
		Integer selected = menu.getSelected();
		String type  = menu.getDatatype();
		String target=menu.getTarget();
 
		if(!"_blank".equals(target)){
			String ctx= EopSetting.CONTEXT_PATH;
			ctx=ctx.equals("/")?"":ctx;
			url=ctx + url;
		}
		
 
		StringBuffer menuItem = new StringBuffer();
		
		menuItem.append("{");
		
		menuItem.append("id:");
		menuItem.append(menu.getId());
		
		
		menuItem.append(",text:'");
		menuItem.append(title);
		menuItem.append("'");
		
		menuItem.append(",url:'");
		menuItem.append(url);
		menuItem.append("'");
		
		
		menuItem.append(",'default':");
		menuItem.append(selected);
 
		menuItem.append(",children:");
		menuItem.append(getChildrenJson(menu.getId(), menuList));
		
		menuItem.append(",type:'");
		menuItem.append(type);
		menuItem.append("'");
		
		menuItem.append(",target:'");
		menuItem.append(target);
		menuItem.append("'");
		
		menuItem.append("}");
		
		return menuItem.toString();
	}
	
	
	/**
	 * 根据菜单某种类型过滤出menuList
	 * @param menuType
	 * @param menuList
	 * @return
	 */
	public  List<Menu> getMenuList(int menuType,List<Menu> menuList){
		List<Menu> mlist = new ArrayList<Menu>();
		
		for(Menu menu :menuList ){
			if(menu.getMenutype().intValue() ==  menuType &&  menu.getPid().intValue()==0){
				mlist.add(menu);
			}
		}
		
		return mlist;
	}
	
	
	/**
	 * 读取
	 * @param menuId
	 * @param menuList
	 * @return
	 */
	private  String  getChildrenJson(Integer menuId,List<Menu> menuList){
		StringBuffer json = new StringBuffer(); 
		json.append("[");
		int i=0;
		for (Menu menu : menuList) {
			
			if (menuId.intValue() == menu.getPid().intValue()) {
				if(i!=0)
					json.append(",");
				json.append(toJson(menu, menuList));
				i++;
			}
		}
		json.append("]");
		return json.toString();
	}

	

	
	

}

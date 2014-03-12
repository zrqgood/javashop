package com.enation.app.base.core.action;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.service.auth.IAdminUserManager;
import com.enation.app.base.core.service.auth.IPermissionManager;
import com.enation.app.base.core.service.auth.IRoleManager;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.AdminUser;
import com.enation.eop.resource.model.SiteManagerView;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;

/**
 * 站点管理员管理
 * @author kingapex
 * 2010-11-5下午04:28:22新增角色管理
 */
public class UserAdminAction extends WWAction {

	private IAdminUserManager adminUserManager;
	private IRoleManager roleManager;
	private IPermissionManager permissionManager;
	private AdminUser adminUser;
	private Integer id;
	private List roleList;
	private List userRoles;
	private int[] roleids;
	private List userList;
	private String newPassword; //新密码
	private String updatePwd;//是否修改密码
	private int multiSite;
	public String execute() {
		userList= this.adminUserManager.list();
		return SUCCESS;
	}

	public String add() throws Exception {
		multiSite =EopContext.getContext().getCurrentSite().getMulti_site();
		roleList = roleManager.list();
		return "add";
	}
	
	public String addSave() throws Exception {
		try{
			adminUser.setRoleids(roleids);
			adminUserManager.add(adminUser);
			this.msgs.add("新增管理员成功");
			this.urls.put("管理员列表", "userAdmin.do");
		 } catch (RuntimeException e) {
			this.msgs.add(e.getMessage());
		}	
			return this.MESSAGE;
	}

	public String edit() throws Exception {
		multiSite =EopContext.getContext().getCurrentSite().getMulti_site();
		roleList = roleManager.list();
		this.userRoles =permissionManager.getUserRoles(id);
		adminUser = this.adminUserManager.get(id);
		return "edit";
	}

	public String editSave() throws Exception {
		try {
			if(updatePwd!=null){
				adminUser.setPassword(newPassword);
			}
			adminUser.setRoleids(roleids);
			this.adminUserManager.edit(adminUser);
			this.msgs.add("修改管理员成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.logger.error(e,e.fillInStackTrace());
			this.msgs.add("管理员修改失败:"+e.getMessage());
		}
		this.urls.put("管理员列表", "userAdmin.do");

		return this.MESSAGE;
	}
	

	public String delete() throws Exception {
		try {
			this.adminUserManager.delete(id);
			this.msgs.add("管理员删除成功");
			this.urls.put("管理员列表", "userAdmin.do");
		} catch (RuntimeException e) {
			this.msgs.add("管理员删除失败:"+e.getMessage());
			this.urls.put("管理员列表", "userAdmin.do");
		}

		return this.MESSAGE;
	}

	public String editPassword() throws Exception {
		return "editPassword";
	}



	public IAdminUserManager getAdminUserManager() {
		return adminUserManager;
	}

	public void setAdminUserManager(IAdminUserManager adminUserManager) {
		this.adminUserManager = adminUserManager;
	}

	public IRoleManager getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(IRoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public IPermissionManager getPermissionManager() {
		return permissionManager;
	}

	public void setPermissionManager(IPermissionManager permissionManager) {
		this.permissionManager = permissionManager;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List userRoles) {
		this.userRoles = userRoles;
	}

	public int[] getRoleids() {
		return roleids;
	}

	public void setRoleids(int[] roleids) {
		this.roleids = roleids;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUpdatePwd() {
		return updatePwd;
	}

	public void setUpdatePwd(String updatePwd) {
		this.updatePwd = updatePwd;
	}

	public int getMultiSite() {
		return multiSite;
	}

	public void setMultiSite(int multiSite) {
		this.multiSite = multiSite;
	}

 
	
}

package com.enation.app.base.core.action;

import java.util.List;

import com.enation.app.base.core.model.Role;
import com.enation.app.base.core.service.auth.IAuthActionManager;
import com.enation.app.base.core.service.auth.IRoleManager;
import com.enation.framework.action.WWAction;

/**
 * 角色管理
 * @author kingapex
 * 2010-11-4下午05:25:48
 */
public class RoleAction extends WWAction {
	
	private IRoleManager roleManager;
	private IAuthActionManager authActionManager;
	
	private List roleList;
	private List authList;
	private int roleid;
	private Role role;
	private int[] acts;
	private int isEdit;
	
	
	//读取角色列表
	public String list(){
		roleList = roleManager.list();
		return "list";
	}
	
	
	
	//到添加页面
	public String add(){
		authList = authActionManager.list();
		return this.INPUT;
	}
	
	
	//到修改页面
	public String edit(){
		authList = authActionManager.list();
		isEdit= 1;
		this.role = this.roleManager.get(roleid);
		return this.INPUT;
	}
	
	
	
	//保存添加
	public String saveAdd(){
		
		this.roleManager.add(role, acts);
		this.msgs.add("角色添加成功");
		this.urls.put("角色列表", "role!list.do");	
	
		return this.MESSAGE;
	}
	
	
	//保存修改
	public String saveEdit(){
		this.roleManager.edit(role, acts);
		this.msgs.add("角色修改成功");
		this.urls.put("角色列表", "role!list.do");		
		return this.MESSAGE;
	}
	
	//删除角色
	public String delete(){
		this.roleManager.delete(roleid);
		this.msgs.add("角色删除成功");
		this.urls.put("角色列表", "role!list.do");		
		return this.MESSAGE;
	}
	
	
	public IRoleManager getRoleManager() {
		return roleManager;
	}
	public void setRoleManager(IRoleManager roleManager) {
		this.roleManager = roleManager;
	}



	public IAuthActionManager getAuthActionManager() {
		return authActionManager;
	}



	public void setAuthActionManager(IAuthActionManager authActionManager) {
		this.authActionManager = authActionManager;
	}



	public List getRoleList() {
		return roleList;
	}



	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}



	public List getAuthList() {
		return authList;
	}



	public void setAuthList(List authList) {
		this.authList = authList;
	}



	public int getRoleid() {
		return roleid;
	}



	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int[] getActs() {
		return acts;
	}
	public void setActs(int[] acts) {
		this.acts = acts;
	}

	public int getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}
	
	
	
	
}

package com.enation.eop.resource.dto;

import com.enation.eop.processor.core.EopException;
import com.enation.eop.resource.model.AdminUser;
import com.enation.eop.resource.model.EopUser;
import com.enation.eop.resource.model.EopUserDetail;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-10 下午05:53:44
 *         </p>
 * @version 1.0
 */
public class UserDTO {
	private EopUser user;
	private EopUserDetail userDetail;
	private AdminUser userAdmin;
	private SiteDTO siteDTO;
	private Integer siteid;
	
//	public UserDTO(){
//		user = new EopUser();
//		userDetail = new EopUserDetail();
//		userAdminList = new ArrayList<EopUserAdmin>();
//		siteList = new ArrayList<SiteDTO>();
//	}
	
	public void vaild(){
		
		if(this.userAdmin==null){
			throw new EopException("用户管理员不能为空！");
		}		
		if(this.userDetail==null){
			throw new EopException("用户详细信息不能为空！");
		}
		if(this.siteDTO==null){
			throw new EopException("用户站点不能为空！");
		}
		siteDTO.vaild();
	}
	
	public void setUserId(Integer userid){
		this.userDetail.setUserid(userid);
		userAdmin.setUserid(userid);
		siteDTO.setUserId(userid);
	}

	public EopUser getUser() {
		return user;
	}

	public void setUser(EopUser user) {
		this.user = user;
	}

	public EopUserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(EopUserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public AdminUser getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(AdminUser userAdmin) {
		this.userAdmin = userAdmin;
	}

	public SiteDTO getSiteDTO() {
		return siteDTO;
	}

	public void setSiteDTO(SiteDTO siteDTO) {
		this.siteDTO = siteDTO;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteDTO.setSiteId(siteid);
		this.siteid = siteid;
	}
}

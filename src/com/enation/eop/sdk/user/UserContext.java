package com.enation.eop.sdk.user;

import java.io.Serializable;

/**
 * 用户上下文
 * @author kingapex
 *
 */
public class UserContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 752513002L;
	public static final String CONTEXT_KEY= "usercontext";
	private Integer userid;
	private Integer siteid;
	private Integer managerid; 
	
	public  UserContext(){}
	
	public  UserContext(Integer userid,Integer siteid,Integer managerid){
		this.userid = userid;
		this.siteid = siteid;
		this.managerid = managerid;
	}
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getSiteid() {
		return siteid;
	}
	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

 
	
}

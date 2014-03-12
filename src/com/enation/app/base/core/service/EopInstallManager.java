package com.enation.app.base.core.service;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.service.dbsolution.DBSolutionFactory;
import com.enation.app.base.core.service.dbsolution.IDBSolution;
import com.enation.app.base.core.service.solution.ISolutionInstaller;
import com.enation.eop.resource.IAppManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopUser;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.database.ISqlFileExecutor;

public class EopInstallManager {
	private JdbcTemplate jdbcTemplate;
	private IAppManager appManager;
	private ApplicationContext context;
	private IUserManager userManager ;
	private ISiteManager siteManager; 
	private ISolutionInstaller solutionInstaller ;
	public void install(String username,String password,String domain,String productid){
		
		EopSite site  = new EopSite();
		site.setUserid(1);
		site.setId(1);
		EopContext context = new EopContext();
		context.setCurrentSite(site);
		EopContext.setContext(context);
 
		this.installUser( username, password, domain,productid);
 
	}
 

	/*
	 * 初始化EOP 平台的用户
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void installUser(String username,String password,String domain,String productid){
		IDBSolution dbsolution = DBSolutionFactory.getDBSolution();

		dbsolution.dbImport("file:com/enation/app/base/init.xml");
		
		EopUser eopUser = new EopUser();
		eopUser.setAddress("在这里输入详细地址");
		eopUser.setUsername(username);
		eopUser.setCompanyname("单机版用户");
		eopUser.setLinkman("在这里输入联系人信息");
		eopUser.setTel("010-12345678");
		eopUser.setMobile("13888888888");
		eopUser.setEmail("youmail@domain.com");
		eopUser.setUsertype(1);
		eopUser.setPassword(password);
		Integer userid = userManager.createUser(eopUser);
		userManager.login(username, password);
		  
		EopSite site = new EopSite();
		site.setSitename("javashop");
		site.setThemeid(1);
		site.setAdminthemeid(1);
		site.setSitename(productid + "新建站点");
		site.setUserid(userid);
		site.setUsername(eopUser.getUsername());
		site.setUsertel(eopUser.getTel());
		site.setUsermobile(eopUser.getMobile());
		site.setUseremail(eopUser.getEmail());
		
		Integer siteid = siteManager.add(site,domain);
		
		solutionInstaller.install(userid,siteid, productid);
		solutionInstaller.install(userid, siteid, "base");
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public IAppManager getAppManager() {
		return appManager;
	}
	public void setAppManager(IAppManager appManager) {
		this.appManager = appManager;
	}
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
 
 
	public ISolutionInstaller getSolutionInstaller() {
		return solutionInstaller;
	}


	public void setSolutionInstaller(ISolutionInstaller solutionInstaller) {
		this.solutionInstaller = solutionInstaller;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
	
	
}

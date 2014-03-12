package com.enation.eop.sdk;

import org.dom4j.Document;

import com.enation.eop.resource.model.EopSite;

/**
 * 应用接口
 * @author kingapex
 * 2010-1-23下午06:18:11
 */
public interface IApp {
	
	/**
	 * 在系统初始化时会调用此方法
	 */
	public void install();
	
	
	/**
	 * 在saas式运行状态下，
	 * 安装某个解决方案时会调用此方法
	 */
	public void saasInstall();
	
	
	/**
	 * 本应用导出sql
	 * @return
	 */
	public String dumpSql();
	
	
	
	public String dumpSql(Document setup);
	
	public String dumpXml(Document setup);
	
	/**
	 * session失效 事件
	 */
	public void sessionDestroyed(String sessionid,EopSite site );
	
	/**
	 * 应用的名称
	 * @return
	 */
	public String getName();
	
	
	/**
	 * 应用的id
	 * @return
	 */
	public String getId();
	
	
	/**
	 * 应用的命名空间
	 * @return
	 */
	public String getNameSpace();
	
}

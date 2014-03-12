package com.enation.eop.sdk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.enation.eop.resource.ISiteManager;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.util.StringUtil;

/**
 * EopLinstener 负责初始化站点缓存
 * 只有saas版本有效
 * @author kingapex
 * 2010-7-18下午04:01:16
 */
public class EopContextLoaderListener implements ServletContextListener {

	
	public void contextDestroyed(ServletContextEvent event) {

	}
	
	public void contextInitialized(ServletContextEvent event) {
		//saas式并且已经安装完成
		if("2".equals(EopSetting.RUNMODE) &&  EopSetting.INSTALL_LOCK.toUpperCase().equals("YES")  ){
			ISiteManager siteManager = SpringContextHolder.getBean("siteManager");
			siteManager.getDnsList();
		}
	}

}

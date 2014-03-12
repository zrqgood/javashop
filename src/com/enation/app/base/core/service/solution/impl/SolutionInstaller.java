package com.enation.app.base.core.service.solution.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.enation.app.base.core.service.dbsolution.DBSolutionFactory;
import com.enation.app.base.core.service.dbsolution.IDBSolution;
import com.enation.app.base.core.service.solution.IInstaller;
import com.enation.app.base.core.service.solution.IProfileLoader;
import com.enation.app.base.core.service.solution.ISetupLoader;
import com.enation.app.base.core.service.solution.ISolutionInstaller;
import com.enation.app.base.core.service.solution.InstallerFactory;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.model.EopProduct;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.FileUtil;

/**
 * 解决方案安装器实现
 * 
 * @author kingapex
 * 
 */
public class SolutionInstaller implements ISolutionInstaller {
	protected final Logger logger = Logger.getLogger(getClass());
	private IDaoSupport<EopProduct> daoSupport;
	private ISiteManager siteManager;
	private IProfileLoader profileLoader;
	private InstallerFactory installerFactory;
	private ISetupLoader setupLoader;

	@Transactional(propagation = Propagation.REQUIRED)
	public void install(Integer userid, Integer siteid, String productId) {

		// 将对应的productid写入到eop_site表的productid字段中
		if (!productId.toUpperCase().equals("BASE")
				&& !productId.startsWith("temp_")) {
			siteManager.setSiteProduct(userid, siteid, productId);
		}

		final String[] types = { InstallerFactory.TYPE_APP,
				InstallerFactory.TYPE_MENU, InstallerFactory.TYPE_ADMINTHEME,
				InstallerFactory.TYPE_THEME, InstallerFactory.TYPE_URL,
				InstallerFactory.TYPE_WIDGET, InstallerFactory.TYPE_INDEX_ITEM };

		// 加载产品的profile.xml文件
		Document proFileDoc = profileLoader.load(productId);

		// 调用所有安装器进行安装，具体安装内容由profile.xml决定
		for (String type : types) {
			if (logger.isDebugEnabled()) {
				logger.debug("install [" + type + "]");
			}

			NodeList nodeList = proFileDoc.getElementsByTagName(type);
			if (nodeList == null || nodeList.getLength() <= 0)
				continue;

			if (nodeList != null) {
				IInstaller installer = installerFactory.getInstaller(type);
				if (logger.isDebugEnabled()) {
					logger.debug("user installer [" + installer + "]");
				}
				installer.install(productId, nodeList.item(0));
			}
		}

		// 加载产品的setup.xml文件
		org.dom4j.Document setupDoc = setupLoader.load(productId);

		if (logger.isDebugEnabled()) {
			logger.debug("execute setup.xml...");
		}

		String tablenameperfix = "";
		if ("2".equals(EopSetting.RUNMODE)) {
			EopSite site = EopContext.getContext().getCurrentSite();
			tablenameperfix = "_" + site.getUserid() + "_" + site.getId();
		}

		/*
		 * 已经在app中的初始化安装sql中执行了重新创建表操作，不用在这里再clean List listClean =
		 * setupDoc.getRootElement().element("clean").elements(); for(Object
		 * o:listClean){ org.dom4j.Element table = (org.dom4j.Element)o;
		 * this.daoSupport.execute("truncate table " + table.getText() +
		 * tablenameperfix); }
		 */

		List listRecreate = setupDoc.getRootElement().element("recreate")
				.elements();
		IDBSolution dbsolution = DBSolutionFactory.getDBSolution();

		for (Object o : listRecreate) {
			org.dom4j.Element table = (org.dom4j.Element) o;
			dbsolution.deleteTable(table.getText() + tablenameperfix);
		}

		// 示例数据安装
		IInstaller installer = SpringContextHolder
				.getBean("exampleDataInstaller");
		installer.install(productId, null);

		// 安装管理员数据
		installer = SpringContextHolder.getBean("adminUserInstaller");
		installer.install(productId, null);

		if (EopSetting.RUNMODE.equals("2")) {
			// 将此产品的profile.xml至站点下
			if (!"base".equals(productId)) {
				FileUtil.copyFile(EopSetting.PRODUCTS_STORAGE_PATH + "/"
						+ productId + "/profile.xml", EopSetting.EOP_PATH
						+ EopContext.getContext().getContextPath()
						+ "/profile.xml");
			}
		}
	}

	public IDaoSupport<EopProduct> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopProduct> daoSupport) {
		this.daoSupport = daoSupport;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public IProfileLoader getProfileLoader() {
		return profileLoader;
	}

	public void setProfileLoader(IProfileLoader profileLoader) {
		this.profileLoader = profileLoader;
	}

	public InstallerFactory getInstallerFactory() {
		return installerFactory;
	}

	public void setInstallerFactory(InstallerFactory installerFactory) {
		this.installerFactory = installerFactory;
	}

	public ISetupLoader getSetupLoader() {
		return setupLoader;
	}

	public void setSetupLoader(ISetupLoader setupLoader) {
		this.setupLoader = setupLoader;
	}

	public Logger getLogger() {
		return logger;
	}

}

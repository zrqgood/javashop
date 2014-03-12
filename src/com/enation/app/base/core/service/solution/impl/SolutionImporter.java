package com.enation.app.base.core.service.solution.impl;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.service.solution.ISolutionExporter;
import com.enation.app.base.core.service.solution.ISolutionImporter;
import com.enation.app.base.core.service.solution.ISolutionInstaller;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.FileUtil;

/**
 * 解决方案导入器
 * @author kingapex
 *
 */
public class SolutionImporter implements ISolutionImporter {
	protected final Logger logger = Logger.getLogger(getClass());
	private ISolutionInstaller solutionInstaller;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void imported(String productid, String zipPath,boolean cleanZip) {
		try {
			if(EopSetting.RUNMODE.equals("1")){ //在独立版导入的时候锁定安装
				EopSetting.INSTALL_LOCK ="NO";
			}
			if("1".equals( EopSetting.RESOURCEMODE )){ //静态资源分离
				FileUtil.delete(EopSetting.EOP_PATH+EopContext.getContext().getContextPath() +"/themes");
				FileUtil.delete(EopSetting.IMG_SERVER_PATH+EopContext.getContext().getContextPath() +"/themes");
			}
			
			if("2".equals( EopSetting.RESOURCEMODE )){ //静态资源合并
				FileUtil.delete(EopSetting.EOP_PATH+EopContext.getContext().getContextPath() +"/themes");
			}
			
			
			String temppath  =   this.expand(productid, zipPath, cleanZip);
			File tempdir = new File(temppath);
			
			EopSite site  = EopContext.getContext().getCurrentSite();
			
			
			solutionInstaller.install(site.getUserid(), site.getId(), productid);
			solutionInstaller.install(site.getUserid(), site.getId(), "base");
			
			Project prj = new Project();
			//清除安装文件
			Delete delete = new Delete();
			delete.setProject(prj);
			delete.setDir(tempdir);
			delete.execute();
			
			if(EopSetting.RUNMODE.equals("1")){
				EopSetting.INSTALL_LOCK ="YES";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error("导入解决方案",e.fillInStackTrace());
			if(EopSetting.RUNMODE.equals("1")){
				EopSetting.INSTALL_LOCK ="YES";
			}
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 将zip包解压到为指定的解决方案
	 * @param productid
	 * @param zipPath
	 * @return 解压后的全路径
	 */
	private String expand(String productid,String zipPath,boolean cleanZip){
		String temppath =  EopSetting.PRODUCTS_STORAGE_PATH+"/" +productid;
		File tempdir = new File(temppath);
		tempdir.mkdirs();

		File zipFile = new File(zipPath);
		Project prj = new Project();
		Expand expand = new Expand();
		expand.setEncoding("UTF-8");
		expand.setProject(prj);
		expand.setSrc(zipFile);
		expand.setOverwrite(true);
		expand.setDest(tempdir);
		expand.execute();
		
		if(cleanZip){
			//清除zip包
			Delete delete = new Delete();
			delete.setDir(zipFile);
			delete.execute();		
		}
		
		return temppath;
	}

	public ISolutionInstaller getSolutionInstaller() {
		return solutionInstaller;
	}

	public void setSolutionInstaller(ISolutionInstaller solutionInstaller) {
		this.solutionInstaller = solutionInstaller;
	}
	

}

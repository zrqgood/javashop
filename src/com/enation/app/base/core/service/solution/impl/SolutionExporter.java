package com.enation.app.base.core.service.solution.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.service.solution.ISetupCreator;
import com.enation.app.base.core.service.solution.ISolutionExporter;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.sdk.IApp;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.util.FileUtil;

/**
 * 解决方案导出器实现
 * @author kingapex
 *
 */
public class SolutionExporter implements ISolutionExporter {

	protected final Logger logger = Logger.getLogger(getClass());
 	private SqlExportService sqlExportService;
 	private ProfileCreator profileCreator;
 	private ISetupCreator setupCreator;
 	private ISiteManager siteManager;
 	
 	@Transactional(propagation = Propagation.REQUIRED)
	public void export(String name, boolean exportData, boolean exportTheme,
			boolean exportProfile, boolean exportAttr) {
		
		
		//导出文件要存放的位置
		String datapath = EopSetting.IMG_SERVER_PATH+"/"+EopContext.getContext().getContextPath()+"/backup/";
		
		//打包文件存放临时目录，以当前日期的毫秒数生成的
		String temppath = datapath+System.currentTimeMillis();
		File tempdir = new File(temppath);
		tempdir.mkdirs();
		
		//检查，如果存在当前命名的zip包，则先清除
		File target = new File(datapath+name+".zip");
		if(target.exists()) target.delete();
		
		 
		//开始导出工作
		try {
			
			/*
			 *		==============
			 *		导出数据
			 *		============== 
			 */
			if(exportData){
				
				StringBuffer sqlContent = new StringBuffer();
				StringBuffer xmlFile = new StringBuffer();
				xmlFile.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				xmlFile.append("<dbsolution>\n");
				//生成示例数据，由各个app自己负责自己的示例数据（base应用除外，它自己未生成示例数据，在后而由本方法内提供）
				//生成setup.xml
				org.dom4j.Document setup = setupCreator.createSetup(temppath + "/setup.xml");
				List<EopApp> appList  = this.siteManager.getSiteApps();
				for(EopApp eopApp:appList){
					String appid = eopApp.getAppid();
					IApp app = SpringContextHolder.getBean(appid);
					if("1".equals(EopSetting.DBTYPE))
						sqlContent.append(app.dumpSql(setup));
					xmlFile.append(app.dumpXml(setup));
				}
				xmlFile.append("</dbsolution>");
				if("1".equals(EopSetting.DBTYPE)){
					FileUtil.write(temppath +"/example_data_mysql.sql", sqlContent.toString());
				}
				setupCreator.save(setup, temppath + "/setup.xml");
				FileUtil.write(temppath + "/example_data.xml", xmlFile.toString());
			}
			
			/*
			 *		==============
			 *		拷贝模板文件
			 *		============== 
			 */
			if(exportTheme){
				
				if("1".equals( EopSetting.RESOURCEMODE )){ //静态资源分离
					FileUtil.copyFolder(EopSetting.EOP_PATH+EopContext.getContext().getContextPath() +"/themes", temppath+"/themes");
					FileUtil.copyFolder(EopSetting.IMG_SERVER_PATH+EopContext.getContext().getContextPath() +"/themes", temppath+"/themes");
				}
				
				if("2".equals( EopSetting.RESOURCEMODE )){ //静态资源合并
					FileUtil.copyFolder(EopSetting.EOP_PATH+EopContext.getContext().getContextPath() +"/themes", temppath+"/themes");
				}
				
			}
			
			/*
			 *		==============
			 *		创建配置文件
			 *		============== 
			 */
			if(exportProfile){
				//创建配置文件
				this.profileCreator.createProfile(temppath+"/profile.xml");
			}
			
			/*
			 *		==============
			 *		拷贝附件
			 *		============== 
			 */
			if(exportAttr){
				//拷贝附件
				FileUtil.copyFolder(EopSetting.IMG_SERVER_PATH+EopContext.getContext().getContextPath()+"/attachment", temppath+"/attachment");				
			}
			
			Project prj = new Project();  
			Zip zip = new Zip();  
			zip.setEncoding("UTF-8");
			zip.setProject(prj);  
			zip.setDestFile(target);  
			FileSet fileSet = new FileSet();  
			fileSet.setProject(prj);  
			fileSet.setDir(tempdir); 
			zip.addFileset(fileSet);  
			zip.execute();  
			Delete delete = new Delete();
			delete.setDir(tempdir);
			delete.execute();

		} catch (Exception e) {
			 e.printStackTrace();
			 this.logger.error("导出解决方案出错",e.fillInStackTrace());
			 throw new  RuntimeException("导出解决方案出错["+e.getMessage()+"]",e);
		}
	}

	public SqlExportService getSqlExportService() {
		return sqlExportService;
	}

	public void setSqlExportService(SqlExportService sqlExportService) {
		this.sqlExportService = sqlExportService;
	}

	public ProfileCreator getProfileCreator() {
		return profileCreator;
	}

	public void setProfileCreator(ProfileCreator profileCreator) {
		this.profileCreator = profileCreator;
	}

	public ISetupCreator getSetupCreator() {
		return setupCreator;
	}

	public void setSetupCreator(ISetupCreator setupCreator) {
		this.setupCreator = setupCreator;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
}

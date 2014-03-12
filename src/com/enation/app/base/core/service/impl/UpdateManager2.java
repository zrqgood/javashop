package com.enation.app.base.core.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.base.core.model.UpdateLog;
import com.enation.app.base.core.model.VersionState;
import com.enation.app.base.core.service.IBatchSqlExecutor;
import com.enation.app.base.core.service.IUpdateManager;
import com.enation.eop.processor.core.RemoteRequest;
import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.Response;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;
import com.enation.framework.util.XMLUtil;

/**
 * 新的升级程序
 * @author kingapex
 *
 */
public class UpdateManager2 implements IUpdateManager {
	private IDaoSupport daoSupport ;
	protected final Logger logger = Logger.getLogger(getClass());
	
	//站点管理，将用于站点应用的获取
	private ISiteManager siteManager;
	private ISqlFileExecutor sqlFileExecutor;
	private IBatchSqlExecutor batchSqlExecutor;	
	
	
	/**
	 * 检测当前产品是否有新版本
	 */
	public VersionState checkNewVersion() {
		
		if(logger.isDebugEnabled()){
			logger.debug("检测当前产品是否有新版本...");
		}
		
		VersionState versionState = new VersionState();
		String newVersion = this.getNewVersion();
		String currentVersion = EopSetting.VERSION;
		
		
		if(logger.isDebugEnabled()){
			logger.debug("产品当前版本为：["+currentVersion+"]最新版本为：["+newVersion+"]");
		}
		
		
		if(!StringUtil.isEmpty(newVersion)){
			//如果新版本号大有新版本
			if( this.versionLargerThen(newVersion, currentVersion)  ){
				versionState.setHaveNewVersion(true);
				versionState.setNewVersion(newVersion);
				List<UpdateLog> updateLogList = getUpdateLog();
				versionState.setUpdateLogList(updateLogList);
			}
			
		}
		
		
		if(logger.isDebugEnabled()){
			logger.debug("检测新版本完成.");
		}
		
		
		
		return versionState;
	}
	
	

	/**
	 * 执行升级操作
	 */
	public void update() {
		if(logger.isDebugEnabled()){
			logger.debug("执行升级操作...");
		}
		
		
		
		String newVersion = this.getNewVersion();
		if(logger.isDebugEnabled()){
			logger.debug("产品最新版本为：["+newVersion+"]");
		}
		
		
		
		//加载升级xml文件
		Document document = this.loadUpdateXmlDoc("/version.xml");
		
		//找到apps结点
		Element appsNode = XMLUtil.getChildByTagName(document, "apps");
		
		
		//获取此产品的应用
		List<EopApp> appList = this.siteManager.getSiteApps();
		
		
		for(EopApp app : appList){
			
			
			//获取每个应用的版本
			String appVersion  = app.getVersion();
			
			//获取远程服务器上version.xml中的此应用节点
			Element appNode = XMLUtil.getChildByTagName(appsNode, app.getAppid());
			
			//此应用的最新版本
			String appLastVersion=appNode.getAttribute("lastversion");
			
			if(logger.isDebugEnabled()){
				logger.debug("升级应用["+app.getAppid()+"]，产品中此应用当前版本["+appVersion+"]，最新版本["+appLastVersion+"]");
			}
			
			this.update(app.getAppid(), appVersion, appNode);
			
			//记录app以便以后更新
			//app.setVersion(appLastVersion);
			
			
			//更新站点的应用版本 
			daoSupport.execute("update eop_app set version=? where appid=?", appLastVersion,app.getAppid());
			
			if(logger.isDebugEnabled()){
				logger.debug("升级应用["+app.getAppid()+"]完成。");
			}
		}
		
	
		
	//	updateProfile(appList); //更新profile.xml
		this.updateVersion(newVersion); //更新eop.properties中的version
		
		if(logger.isDebugEnabled()){
			logger.debug("产品升级完成，当前版本为:["+EopSetting.VERSION+"]");
		}		

		
	}
	
	
	
	
	/**
	 * 升级某个应用的所有版本 
	 * @param appid 要升级的应用id
	 * @param currentVersion 当前版本
	 * @param appNode version.xml中此app的节点
	 */
	private void update(String appid,String currentVersion,Element appNode){
		
		if(logger.isDebugEnabled()){
			logger.debug("升级应用["+appid+"]...");
		}
		
		NodeList versionNodeList = appNode.getChildNodes();
		
		//应用版本列表为空返回null
		if(versionNodeList== null || versionNodeList.getLength()==0){ 
			if(logger.isDebugEnabled()){
				logger.debug("服务器端应用["+appid+"]的版本列表为空...");
			}
			return ;
		}
		
		
		
		for( int i=0;i<versionNodeList.getLength();i++){
			Node node  =versionNodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				String version = node.getTextContent();

				//如果此版本大于当前版本，获取本版本更新日志
				if(versionLargerThen(version,currentVersion)){
					if(logger.isDebugEnabled()){
						logger.debug("为应用["+appid+"]升级版本["+version+"]...");
					}
					//更新应用某个版本,下载，解压缩，执行
					this.update(appid, version);
				}else{
					if(logger.isDebugEnabled()){
						logger.debug("应用["+appid+"]版本小于此版本，["+version+"]跳过...");
					}
				}
			}
		}
		
	}
	
	
	
	

	/**
	 * 升级应用的某个版本
	 * @param appid 应用id
	 * @param version 要升级的版本
	 */
	private void update(String appid,String version){
		this.download(appid, version);
		this.unZip(appid, version);
		this.exeUpdate(appid, version);
	}
	
	
	/**
	 * 执行升级操作
	 * @param appid
	 * @param version
	 */
	private void exeUpdate(String appid,String version) {

		String patchPath = EopSetting.EOP_PATH + "/patch/"+appid+"/" + version;
		String updateSql = patchPath + "/sql/update.sql";
		String cmsUpdateSql = patchPath + "/sql/cms_update.sql";

		
		
		
		
		
		// 执行升级数据库脚本
		if (new File(updateSql).exists()) {
			
			if(logger.isDebugEnabled()){
				logger.debug("执行["+updateSql+"]...");
			}
			
			String content = FileUtil.read(updateSql, "UTF-8");
			this.executeSql(content);
			
			if(logger.isDebugEnabled()){
				logger.debug("执行["+updateSql+"]成功.");
			}
			
			
		}else{
			if(logger.isDebugEnabled()){
				logger.debug("["+updateSql+"]不存在跳过.");
			}
		}

		
		
		
		
		
		// 执行cms相应升级数据库脚本
		if (new File(cmsUpdateSql).exists()) {
			if(logger.isDebugEnabled()){
				logger.debug("执行["+cmsUpdateSql+"]...");
			}
						
			String content = FileUtil.read(cmsUpdateSql, "UTF-8");
			
			if(logger.isDebugEnabled()){
				logger.debug("执行内容["+content+"]...");
			}
						
			this.batchSqlExecutor.executeForCms(content);
			
			if(logger.isDebugEnabled()){
				logger.debug("执行["+cmsUpdateSql+"]成功.");
			}
			
		}
		
		
		if(logger.isDebugEnabled()){
			logger.debug("复制["+patchPath + "/web"+"]...");
		}
		
		FileUtil.copyFolder(patchPath + "/web", EopSetting.EOP_PATH);
		if(logger.isDebugEnabled()){
			logger.debug("复制["+patchPath + "/web"+"]成功.");
		}
	

	}
	
	//升级成功后更新版本号
	private void updateVersion(String version){
		
		
		if(logger.isDebugEnabled()){
			logger.debug("更新eop.properties中的版本信息...");
		}

		try {
			String path = StringUtil.getRootPath("eop.properties");
			File file  = new File(path);	
		 
			//更新eop.properties
			EopSetting.VERSION=version;
			InputStream in  = new FileInputStream(file) ;//FileUtil.getResourceAsStream("eop.properties");
			Properties props = new Properties();			
			props.load(in);
			props.setProperty("version", version);
			props.store(new FileOutputStream(file), "eop.properties");
			if(logger.isDebugEnabled()){
				logger.debug("更新eop.properties中的版本成功.");
			}
		} catch (Exception e) {
			logger.error("更新eop.properties出错",e.fillInStackTrace());
			e.printStackTrace();
		}
		
		
		
	}
	
 
	
	/**
	 * 为数据库执行升级脚本
	 * @param content
	 */
	private void executeSql(String content){
		if("2".equals(EopSetting.RUNMODE) ){
			batchSqlExecutor.exeucte(content);
		}else{
			content = content.replaceAll("_<userid>","");
			content = content.replaceAll("_<siteid>","");		
			content = content.replaceAll("/user/<userid>/<siteid>","");
			content = content.replaceAll("<userid>","1");
			content = content.replaceAll("<siteid>","1");	
			this.sqlFileExecutor.execute(content);
		}
	}
	
	
	
	/**
	 * 下载压缩包
	 * 
	 * @param zipName
	 * @throws Exception
	 */
	private void download(String appid,String version) {
		//由远程服务器<server>/<appid>/patch/<version>.zip下载补丁包
		String remoteZip = EopSetting.SERVICE_DOMAIN_NAME+"/"+appid+"/patch/"+version+".zip";
		
		try{
			

			if(this.logger.isDebugEnabled()){
				this.logger.debug("由["+remoteZip+"]下载升级包...");
			}
			
			URL url = new URL(remoteZip);
			String outPath = EopSetting.EOP_PATH+"/patch/"+appid;
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			byte[] bts = new byte[2048];
			File file = new File(outPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			
			//写入到<eop.path>/patch/<appid>/<version>.zip
			FileOutputStream fout = new FileOutputStream(outPath+"/"+version+".zip");
			int n;
			while ((n = is.read(bts)) != -1) {
				fout.write(bts, 0, n);
				fout.flush();
				bts = new byte[2048];
			}
		}catch(Exception e)	{
			this.logger.error("下载升级包["+remoteZip+"]出错");
			e.printStackTrace();
			throw new RuntimeException("下载升级版本zip出错",e);
		}
	}
	
	
	
	/**
	 * 解开升级包
	 * @param zipName
	 */
	private void unZip(String appid,String version){
		
		String zipPath = EopSetting.EOP_PATH+"/patch/" + appid+"/"+version+".zip";
		if(this.logger.isDebugEnabled()){
			this.logger.debug("解压升级包["+zipPath+"]...");
		}
		
		FileUtil.unZip(zipPath, EopSetting.EOP_PATH+"/patch/"+appid+"/"+version, true);
		if(this.logger.isDebugEnabled()){
			this.logger.debug("解压升级包["+zipPath+"]完成");
		}
	}
	
	
	
	/**
	 * 获取此产品所有应用的更新日志<br> 
	 * @return
	 */
	private List<UpdateLog> getUpdateLog(){
		
		if(this.logger.isDebugEnabled()){
			this.logger.debug("获取产品所有应用的更新日志...");
		}
		
		//声明要返回的容器
		 List<UpdateLog> updateLogList  = new ArrayList<UpdateLog>();
		 
		
		//加载升级xml文件
		Document document = this.loadUpdateXmlDoc("/version.xml");
		
		//找到apps结点
		Element appsNode = XMLUtil.getChildByTagName(document, "apps");
		
		
		//获取此产品的应用
		List<EopApp> appList = this.siteManager.getSiteApps();
		for(EopApp app : appList){
			//获取每个应用的版本
			String verison  = app.getVersion();
			
			//获取远程服务器上version.xml中的此应用节点
			Element appNode = XMLUtil.getChildByTagName(appsNode, app.getAppid());
			
			//获取此应用的所有版本历史的更新日志
			UpdateLog updateLog = this.getAppUpdateLog(app.getAppid(), verison, appNode);
			if(updateLog!=null){
				updateLogList.add(updateLog);
			}
		}
		
		
		if(this.logger.isDebugEnabled()){
			this.logger.debug("获取产品所有应用的更新日志完成.");
		}
		
		
		return updateLogList;
	}
	
	
	/**
	 * 获取某个应用的更新日志<br>
	 * 会读取由某个版本至最新版的更新日志
	 * @param currentVersion 当前版本
	 * @param appNode  products节点中的app结点
	 * @return 如果无更新返回null，有更新则返回更新日志
	 */
	private UpdateLog getAppUpdateLog(String appid,String currentVersion,Node  appNode) {
		
		if(this.logger.isDebugEnabled()){
			this.logger.debug("获取获取应用["+appid+"]的日志...");
		}
		
		NodeList versionNodeList = appNode.getChildNodes();
		
		//应用版本列表为空返回null
		if(versionNodeList== null || versionNodeList.getLength()==0) return null;
		UpdateLog updateLog = new UpdateLog();
		updateLog.setAppId(appid);
		List<String> logList = new ArrayList<String>();
		for( int i=0;i<versionNodeList.getLength();i++){
			Node node  =versionNodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				String version = node.getTextContent();
				//如果此版本大于当前版本，获取本版本更新日志
				if(versionLargerThen(version,currentVersion)){
					//由远程服务器更新读取日志
					List<String> oneLogList  = this.getVersionUpdateLog(appid, version);
					logList.addAll(oneLogList);
					
				}
			}
		}
		updateLog.setLogList(logList);
		if(this.logger.isDebugEnabled()){
			this.logger.debug("获取获取应用["+appid+"]的日志成功.");
		}
		
		return updateLog;
	}
	
	/**
	 * 获取某应用某个版本的更新日志<br>
	 * 由远程服务器加载传入版本号的xml文件<br>
	 * 读取文件中的更新日期压入List返回
	 * @param appid
	 * @param version
	 * @return
	 */
	private List<String> getVersionUpdateLog(String appid ,String version){
		if(this.logger.isDebugEnabled()){
			this.logger.debug("获取获取应用["+appid+"]版本["+version+"]的日志...");
		}		
		List<String> logList  = new ArrayList<String>();
		Document doc  =  this.loadUpdateXmlDoc("/"+appid+"/"+version+".xml");//加载此版本的xml文档
		NodeList itemList  =doc.getElementsByTagName("item");
		
		if(itemList!=null){
			for(int i=0;i<itemList.getLength();i++){
				Node node  = itemList.item(i);
				if(node.getNodeType() ==  Node.ELEMENT_NODE){
					String log =node.getTextContent();
					logList.add(log);
				}
			}
		}
		if(this.logger.isDebugEnabled()){
			this.logger.debug("获取获取应用["+appid+"]版本["+version+"]的日志成功.");
		}		
		return logList;
	}
	
	
	
	
	/**
	 * 返回当前软件产品最新版本<br>
	 * 产品id由EopSetting.PRODUCTID 获取<br>
	 * 然后由服务器version.xml获取此产品的最新版本
	 * @return
	 */
	private String getNewVersion(){
		String productid  = EopSetting.PRODUCTID;
		  	Document document =loadUpdateXmlDoc("/version.xml");
		  	
		    Node productNode  = XMLUtil.getChildByTagName(document, "products");
		    NodeList productList = productNode.getChildNodes();
		    for(int i=0;i<productList.getLength();i++){
		    	Node proNode = productList.item(i);
		    	if(proNode.getNodeType() == Node.ELEMENT_NODE ){
		    		String proName = proNode.getNodeName();
		    		if( productid.equals( proName ) ){
		    			return proNode.getTextContent();
		    		}
		    	}			    	
		    }
		    throw new RuntimeException("产品ID不正确");
	}
	
	
	
	
	/**
	 * 由远程更新服务器获取版本xml文件
	 * @param filePath
	 * @return
	 */
	private Document loadUpdateXmlDoc(String filePath){
	
		//配置的版本升级服务地址
		String serviceDomain = EopSetting.SERVICE_DOMAIN_NAME;
		
		//由远程业务服务器请求得到新版本号
		Request request  = new RemoteRequest();
		Response response =request.execute(serviceDomain+filePath);
		if(response!=null){
			InputStream xmlStream = response.getInputStream();
			try {
				
			    DocumentBuilderFactory factory = 
			    DocumentBuilderFactory.newInstance();
			    DocumentBuilder builder = factory.newDocumentBuilder();
			    Document document = builder.parse(xmlStream);
			    return document;
			}
			catch (Exception e) {
				//e.printStackTrace();
				throw new RuntimeException("load version.xml error" );
			}
		}	else{
			throw new  RuntimeException("load version.xml error" );
		}			
		
	}

	
	/**
	 * 判断ver1是否比ver2大
	 * @param ver1
	 * @param ver2
	 * @return
	 */
	private boolean versionLargerThen(String ver1,String ver2){
		if( StringUtil.isEmpty(ver1) ) throw new IllegalArgumentException( "ver1版本不能为空");
		if( StringUtil.isEmpty(ver2) ) throw new IllegalArgumentException("ver2版本不能为空");
		if(ver1.length()!= ver2.length()) throw new  IllegalArgumentException("ver2与ver2版本号格式不相同");
		if(ver1.length()!=5) throw new  IllegalArgumentException("版本号格式不正确，应为如：2.1.0");
		
		String[] ver1a = ver1.split("\\.");
		Integer ver1i = Integer.valueOf(ver1a[0])*1000000 + Integer.valueOf(ver1a[1])*1000 + Integer.valueOf(ver1a[2]);
		String[] ver2a = ver2.split("\\.");
		Integer ver2i = Integer.valueOf(ver2a[0])*1000000 + Integer.valueOf(ver2a[1])*1000 + Integer.valueOf(ver2a[2]);
		
		return ver1i > ver2i;
	}
		
 
	
	
	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}



	public ISqlFileExecutor getSqlFileExecutor() {
		return sqlFileExecutor;
	}



	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}



	public IBatchSqlExecutor getBatchSqlExecutor() {
		return batchSqlExecutor;
	}



	public void setBatchSqlExecutor(IBatchSqlExecutor batchSqlExecutor) {
		this.batchSqlExecutor = batchSqlExecutor;
	}



	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}



	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}

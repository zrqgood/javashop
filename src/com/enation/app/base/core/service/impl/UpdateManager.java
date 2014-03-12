package com.enation.app.base.core.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.enation.app.base.core.model.VersionState;
import com.enation.app.base.core.service.IBatchSqlExecutor;
import com.enation.app.base.core.service.IUpdateManager;
import com.enation.eop.processor.core.RemoteRequest;
import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.Response;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;


/**
 * 版本更新实现类
 * @author kingapex
 *
 */
public class UpdateManager implements IUpdateManager {

	private ISqlFileExecutor sqlFileExecutor;
	private IBatchSqlExecutor batchSqlExecutor;
	
	/**
	 * 检测是否有新版本
	 */
	public VersionState checkNewVersion() {
		VersionState versionState = new VersionState();
		
		//配置的版本升级服务地址
		String serviceDomain = EopSetting.SERVICE_DOMAIN_NAME;
		
		//由远程业务服务器请求得到新版本号
		Request request  = new RemoteRequest();
		Response response =request.execute(serviceDomain+"/lastVersion.txt");
		if(response!=null){
			String currentVersion = EopSetting.VERSION;
			String newVersion = response.getContent();
			if(!StringUtil.isEmpty(newVersion)){
				//如果新版本号大有新版本
				if( this.versionLargerThen(newVersion, currentVersion)  ){
					versionState.setHaveNewVersion(true);
					versionState.setNewVersion(newVersion);
					response =request.execute(serviceDomain+"/lastUpdate.txt");
					versionState.setUpdateLog(response.getContent());
				}			
				
			}
		}
			
		return versionState;
	}

	
	/**
	 * 升级版本
	 */
	public void update() {
		
		//检测是否有新版本
		VersionState versionState =this.checkNewVersion();
		if(versionState.getHaveNewVersion()){
			//配置的版本升级服务地址
			String serviceDomain = EopSetting.SERVICE_DOMAIN_NAME;
			Request request  = new RemoteRequest();
			Response response =request.execute(serviceDomain+"/patch/update/");
			String content  = response.getContent();
			List<String> zipList  = findPatchZipList(content);
			this.update(zipList);
		}
	}
	
	
	/**
	 * 通过一组给定的升级包名称，完成下载、解压缩、执行sql、覆盖升级文件等一系列操作。
	 * @param zipList
	 */
	private void update(List<String> zipList){
		
		try {
			for(String version: zipList){
				String  zipName = version+".zip";
			   //下载升级包
			   this.download(zipName);
			   //解压缩升级包
			   this.unZip(zipName);
			   //执行升级操作
			   this.update(version);
			}
		} catch (Exception e) {
			e.printStackTrace();
		    throw new RuntimeException("更新版本文件出错", e) ;
		}
	}
	
	
	private void update(String version) throws Exception{
		
		
		String patchPath = EopSetting.EOP_PATH+"/patch/"+version;
		String	updateSql = patchPath+"/sql/update.sql";
		String  cmsUpdateSql=  patchPath+"/sql/cms_update.sql";
		
		
		
		//执行升级数据库脚本
		if(new File(updateSql).exists()){
			String content  = FileUtil.read(updateSql, "UTF-8");
			this.executeSql(content);
		}
		
		
		//执行cms相应升级数据库脚本
		if(new File(cmsUpdateSql).exists()){
			String content  = FileUtil.read(cmsUpdateSql, "UTF-8");
			this.batchSqlExecutor.executeForCms(content);
		}
		
		FileUtil.copyFolder(patchPath+"/web", EopSetting.EOP_PATH);
		
		this.updateVersion(version);
		
		
	}
	
	//升级成功后更新版本号
	private void updateVersion(String version){
		EopSetting.VERSION=version;
		InputStream in  = FileUtil.getResourceAsStream("eop.properties");
		Properties props = new Properties();
		try {
			props.load(in);
			props.setProperty("version", version);
			String path = StringUtil.getRootPath("eop.properties");
			File file  = new File(path);		
			props.store(new FileOutputStream(file), "eop.properties");
			
		} catch (IOException e) {
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
	 * @param zipName
	 * @throws Exception
	 */
	private void download(String zipName) throws  Exception{
			
			URL url = new URL(EopSetting.SERVICE_DOMAIN_NAME+"/patch/update/"+zipName);
			String outPath = EopSetting.EOP_PATH+"/patch";
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			byte[] bts = new byte[2048];
			File file = new File(outPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fout = new FileOutputStream(outPath+"/"+zipName);
			int n;
			while ((n = is.read(bts)) != -1) {
				fout.write(bts, 0, n);
				fout.flush();
				bts = new byte[2048];
			}
	}
	
	
	
	/**
	 * 解开升级包
	 * @param zipName
	 */
	private void unZip(String zipName){
		String zipPath = EopSetting.EOP_PATH+"/patch/" + zipName;
		FileUtil.unZip(zipPath, EopSetting.EOP_PATH+"/patch/"+zipName.replaceAll(".zip", ""), true);
		
	}
	
	/**
	 * 根据html内容查找补丁包内容，并按版本号由小到大排序
	 * @param content
	 * @return
	 */
	private List<String> findPatchZipList(String content){
		List<String> zipList = new ArrayList<String>();
		String pattern = "<a href=\"(.*?).zip\">(.*?).zip<\\/a>";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(content);
	    while(m.find()){
	       String item  = m.group();
	       zipList.add(this.findZipName(item));
	    }
	    String currentVersion = EopSetting.VERSION;
	    zipList=  this.sortVersionList(currentVersion,zipList);
		return zipList;
	}
	
	
	/**
	 * 在一段html代码中如：<a href="2.1.6_sp1.zip">2.1.6.zip</a>  匹配出2.1.6
	 * @param item
	 * @return
	 */
	private String findZipName(String item){
		
		String pattern = "<a href=\"(.*?).zip\">(.*?).zip<\\/a>";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(item);
	    if(m.find()){
	       	return  m.replaceAll("$2").trim();       
	    }	
		return null;
	}
	
	
	/**
	 * 版本号排序<br>
	 * @param currentVersion 当前系统版本
	 * @param zipList 
	 * @return 返回从小到大排序的版本号列表，由当前版本的后一个版本开始（不含当前版本）
	 */
	public List<String> sortVersionList(String currentVersion,List<String> zipList){
		
		
		List<Map> lst = new ArrayList<Map>();
		
		for(String n:zipList){
			String[] num = n.split("\\.");
			Integer result = Integer.valueOf(num[0])*1000000 + Integer.valueOf(num[1])*1000 + Integer.valueOf(num[2]);
			Map m = new HashMap();
			m.put("string", n);
			m.put("result", result);
			lst.add(m);
		}
		
		List<String> list = new ArrayList<String>();
		
		for(int i = 0;i<lst.size();i++){
			for(int j = i; j<lst.size();j++){
				if(Integer.valueOf(lst.get(i).get("result").toString())>Integer.valueOf(lst.get(j).get("result").toString())){
					Map m = new HashMap();
					m.put("string", lst.get(i).get("string").toString());
					m.put("result", lst.get(i).get("result").toString());
					lst.get(i).put("string", lst.get(j).get("string"));
					lst.get(i).put("result", lst.get(j).get("result"));
					lst.get(j).put("string", m.get("string"));
					lst.get(j).put("result", m.get("result"));
				}
			}
			if(versionLargerThen(lst.get(i).get("string").toString(), currentVersion))
				list.add(lst.get(i).get("string").toString());
		}
		
		return list;
	}
	
	/**
	 * 判断ver1是否比ver2大
	 * @param ver1
	 * @param ver2
	 * @return
	 */
	public boolean versionLargerThen(String ver1,String ver2){
		String[] ver1a = ver1.split("\\.");
		Integer ver1i = Integer.valueOf(ver1a[0])*1000000 + Integer.valueOf(ver1a[1])*1000 + Integer.valueOf(ver1a[2]);
		String[] ver2a = ver2.split("\\.");
		Integer ver2i = Integer.valueOf(ver2a[0])*1000000 + Integer.valueOf(ver2a[1])*1000 + Integer.valueOf(ver2a[2]);
		return ver1i > ver2i;
	}
	
	public static void main(String[] args){
		
		List<String> zipList = new ArrayList<String>();
		zipList.add("2.1.6");
		zipList.add("2.2.0");
		zipList.add("2.1.3");
		zipList.add("2.1.2");
		zipList.add("2.2.6");
		zipList.add("2.3.14");
		zipList.add("2.0.6");
		zipList.add("1.1.6");
		zipList.add("2.1.16");
		UpdateManager manager = new UpdateManager();
		zipList = manager.sortVersionList("2.1.6",zipList);
		for(String zipName:zipList)	{
			System.out.println(zipName);
		}
		
		
		
//		String content = FileUtil.read("d:/test.txt", "UTF-8");
//		String pattern = "<a href=\"(.*?)\">(.*?).zip<\\/a>";
//		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
//		Matcher m = p.matcher(content);
//	   while(m.find()){
//		   System.out.println(m.group());
//		   System.out.println("--------------------------------------------------------");
//	   }
		
	 
		
		
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

}

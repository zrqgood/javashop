package com.enation.app.base.core.service.solution.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.base.core.model.ProductMapper;
import com.enation.app.base.core.service.solution.IInstaller;
import com.enation.app.base.core.service.solution.IProductService;
import com.enation.app.base.core.service.solution.IProfileLoader;
import com.enation.app.base.core.service.solution.ISetupCreator;
import com.enation.app.base.core.service.solution.ISetupLoader;
import com.enation.app.base.core.service.solution.InstallerFactory;
import com.enation.eop.resource.IAppManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.resource.model.EopProduct;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.IApp;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;

/**
 * 产品(解决方案)业务逻辑
 * @author kingapex
 * 2010-1-21上午10:58:12
 */
public class ProductServiceImpl implements IProductService {
	protected final Logger logger = Logger.getLogger(getClass());
	private IProfileLoader profileLoader;
	private ISetupLoader setupLoader;
	private InstallerFactory installerFactory;
 	private ISiteManager siteManager;
 	private IDaoSupport<EopProduct> daoSupport;
 	private SqlExportService sqlExportService;
 	private ProfileCreator profileCreator;
 	private IDBRouter baseDBRouter;
 	private ISetupCreator setupCreator;
 	private IAppManager appManager;
 	
	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
	
	public List listType(){
		String sql ="select * from es_en_pro_type_1_1 order by sort";
		return this.daoSupport.queryForList(sql);
	}
	
	public EopProduct getProduct(Integer id) {
		return (EopProduct) daoSupport.queryForList(
				"select * from eop_product where id = ?", new ProductMapper(), id).get(0);
	 
	}
	
	public EopProduct getProduct(String productId) {
		return (EopProduct) this.daoSupport.queryForList(
				"select * from eop_product where productid = ?",
				 new  ProductMapper(), productId).get(0);
	}

	/* 暂时废弃 - 2011-08-13 by Liuzy
	public Page pageProduct(Integer catid,Integer typeid, Integer state, String order, int page, int pageSize) {
		order = order == null ? " id desc" : order;
		String sql = "select * from eop_product";
		
		if(catid!=null){
			sql+=" where catid="+catid;
		}
		
		if(typeid!=null){
			sql+= " where typeid="+ typeid;
		}
		
		sql += " order by  " + order;

		Page webpage = this.daoSupport.queryForPage(sql,page, pageSize, new ProductMapper());
//		Page webpage = this.daoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}
	*/
	
	public void setProfileLoader(IProfileLoader profileLoader) {
		this.profileLoader = profileLoader;
	}
	
	public void setInstallerFactory(InstallerFactory installerFactory) {
		this.installerFactory = installerFactory;
	}

	public IDaoSupport<EopProduct> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopProduct> daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	/**
	 * 将zip包解压到为指定的解决方案
	 * @param productid
	 * @param zipPath
	 * @return 解压后的全路径
	 */
	private String expand(String productid,String zipPath){
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
		
		//清除zip包
		Delete delete = new Delete();
		delete.setDir(zipFile);
		delete.execute();		
		
		return temppath;
	}
	
	public static void main(String[] args){
		  Project prj=new Project();
		  Expand expand=new Expand();
		  expand.setProject(prj);
		  expand.setSrc(new File("d:/z.zip"));
		  expand.setOverwrite(true);
		  expand.setDest(new File("d:/temp"));
		  expand.execute();
	}
	
	/**
	 * 添加解决方案<br/>
	 * 注意！此处不再修改product_cat表中的数量，而是在数据库中以触发器的形式解决
	 * @param product 解决方案实体，其id属性和名称属性不能为空，否则将会抛出 {@link IllegalArgumentException}
	 * @param zipPath 解决方案zip包文件绝对路径，添加时不能为空，否则将会抛出 {@link IllegalArgumentException}
	 * @return 1成功 0解决方案id已经存在
	 */	
	public int add(EopProduct product, String zipPath) {
		
		if(StringUtil.isEmpty( zipPath )){
			throw new IllegalArgumentException("解决方案文件不能为空");
		}
		
		if(this.checkIdExist(product.getProductid())){
			return 0;
		}
		
		//product.setPreview("fs:/attachment/product/preview.png");
		this.expand(product.getProductid(), zipPath);
		product.setCreatetime(System.currentTimeMillis());
		this.daoSupport.insert("eop_product", product);	
		//String sql  ="update eop_product_cat set num=num+1 where id=?";
		//this.daoSupport.execute(sql, product.getCatid());
		return 1;
	}

	/**
	 * 检测解决方案id是否存在
	 * @param productid
	 * @return 存在返回真，不存在返回假
	 */
	private boolean checkIdExist(String productid){
		String sql  ="select count(0) from eop_product where productid=?";
		int count  = this.daoSupport.queryForInt(sql, productid);
		if(count>0)
			return true;
		else
			return false; 
	}
 
	/**
	 * 更新解决方案<br/>
	 * 注意！此处不再修改product_cat表中的数量，而是在数据库中以触发器的形式解决
	 * @param product 解决方案实体，其id属性和名称属性不能为空，否则将会抛出 {@link IllegalArgumentException}
	 * @param zipPath 解决方案zip包文件绝对路径, 当zipPath为空时则只更新解决方案的基本信息。
	 * @throws  {@link IllegalArgumentException}
	 */	
	public void  update(EopProduct product, String zipPath){
		if(!StringUtil.isEmpty(zipPath)){
			this.expand(product.getProductid(), zipPath);
		}
		this.daoSupport.update("eop_product", product,"productid='" + product.getProductid()+"'");	
	}
	
	/**
	 * 删除解决方案<br/>
	 * 注意！此处不再修改product_cat表中的数量，而是在数据库中以触发器的形式解决
	 * 将会删除数据库信息和解决方案文件
	 * @param productid 解决方案id
	 * @throws  {@link IllegalArgumentException} 当productid为空时
	 */
	public void delete(String productid) {
		if(productid==null) throw new IllegalArgumentException("product argument is null");
		String sql ="delete from eop_product where productid=?";
		Project prj = new Project();
		Delete del = new Delete();
		del.setProject(prj);
		del.setDir(new File(EopSetting.PRODUCTS_STORAGE_PATH + "/"+productid));
		del.execute();
		
		this.daoSupport.execute(sql, productid);
	}
	
	public void updateSort(Integer[] sorts,Integer ids[]){
		if(sorts==null || ids==null || sorts.length != ids.length)
			throw new IllegalArgumentException("传递参数不正确");
		
		String sql ="update eop_product set sort=? where id=?";
		
		for(int i=0;i<sorts.length;i++){
			this.daoSupport.execute(sql, sorts[i],ids[i]);
		}
		 
	}
 
	public Map getType(Integer typeid) {
		 String sql  ="select * from es_en_pro_type_1_1 where id=?";
		return this.daoSupport.queryForMap(sql, typeid);
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

	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}

	public Page pageProduct(Integer catid, Integer colorid, Integer typeid, Integer state,
			String order, int page, int pageSize) {
		order = order == null ? " id desc" : order;
		String sql = "select * from eop_product where ";
		sql = sql + ((state == null) ? "pstate>0" : "pstate=" + state);
		
		if(catid!=null){
			sql+=" and catid="+catid;
		}
		
		if(colorid!=null){
			sql+= " and colorid="+ colorid;
		}
		
		if(typeid!=null){
			sql+= " and typeid="+ typeid;
		}
		
		sql += " order by  " + order;

		Page webpage = this.daoSupport.queryForPage(sql,page, pageSize, new ProductMapper());
//		Page webpage = this.daoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	public void setSetupLoader(ISetupLoader setupLoader) {
		this.setupLoader = setupLoader;
	}

	public void setSetupCreator(ISetupCreator setupCreator) {
		this.setupCreator = setupCreator;
	}

	public IAppManager getAppManager() {
		return appManager;
	}

	public void setAppManager(IAppManager appManager) {
		this.appManager = appManager;
	}
}

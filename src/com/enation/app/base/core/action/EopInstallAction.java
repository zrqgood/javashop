package com.enation.app.base.core.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.enation.app.base.core.service.EopInstallManager;
import com.enation.app.base.core.service.IDataSourceCreator;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 安装action
 * @author kingapex
 * 2010-6-4下午04:54:44
 */
/**
 * @author kingapex
 * 2010-6-9下午05:21:50
 */
public class EopInstallAction extends WWAction {
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcTemplate simpleJdbcTemplate;
	private EopInstallManager eopInstallManager;
	private DataSource dataSource;
	private IDataSourceCreator dataSourceCreator;
	private String dbhost;
	private String uname;
	private String pwd;
	private String dbtype;
	private String dbname;
	private String domain;
	private String productid;
	
	private String staticdomain; //静态资源域名
	private String staticpath;   //静态资源磁盘目录
	private String solutionpath; //解决方案磁盘目录
	
	private int resourcemode;
	
	
	private String osVersion;
	private String javaVersion;
	
	/**
	 * 显示协议
	 */
	public String execute(){
		
		return "step1";
	}
	
	
	/**
	 * 显示数据库设置页
	 * @return
	 */
	public String step2(){

		return "step2";
	}
	
	/**
	 * 保存存数据库设置
	 * 切换至新的数据源
	 * @return
	 */
	public String step3(){		
		saveEopParams();
		if("mysql".equals(dbtype))
			this.saveMysqlDBParams();
		else if("oracle".equals(dbtype))
			this.saveOracleDBParams();
		else if("sqlserver".equals(dbtype))
			this.saveSQLServerDBParams();
		
		Properties props=System.getProperties();  
		this.osVersion = props.getProperty("os.name")+"("+props.getProperty("os.version")+")";
		this.javaVersion = props.getProperty("java.version");
		return "step3";
	}
	
	public String installSuccess(){
		FileUtil.write(EopSetting.EOP_PATH+"/install/install.lock", "如果要重新安装，请删除此文件，并重新起动web容器");
		EopSetting.INSTALL_LOCK ="yes";
		return this.SUCCESS;
	}
	
	
	/**
	 * 保存mysql数据设置
	 */
	private void saveMysqlDBParams(){
		Properties props = new Properties();
		props.setProperty("jdbc.driverClassName", "com.mysql.jdbc.Driver");
		props.setProperty("jdbc.url", "jdbc:mysql://"+this.dbhost+"/"+this.dbname+"?useUnicode=true&characterEncoding=utf8");
		props.setProperty("jdbc.username", this.uname);
		props.setProperty("jdbc.password", this.pwd);
		saveProperties(props);	
	}

	/**
	 * 保存到jdbc.properties文件
	 * @param props
	 */
	private void saveProperties(Properties props){
		try {
			String path = StringUtil.getRootPath("config/jdbc.properties");
			File file  = new File(path);
			  
    		props.store(new FileOutputStream(file), "jdbc.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存Oracle数据设置
	 */
	private void saveOracleDBParams(){
		Properties props = new Properties();
		props.setProperty("jdbc.driverClassName", "oracle.jdbc.driver.OracleDriver");
		props.setProperty("jdbc.url", "jdbc:oracle:thin:@" + this.dbhost+ ":" + this.dbname);
		props.setProperty("jdbc.username", this.uname);
		props.setProperty("jdbc.password", this.pwd);
		saveProperties(props);
	}
	
	/**
	 * 保存SQLServer数据设置
	 */
	private void saveSQLServerDBParams(){
		Properties props = new Properties();
		props.setProperty("jdbc.driverClassName", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		props.setProperty("jdbc.url", "jdbc:sqlserver://" + this.dbhost+ ";databaseName=" + this.dbname);
		props.setProperty("jdbc.username", this.uname);
		props.setProperty("jdbc.password", this.pwd);
		saveProperties(props);
	}
	
	/**
	 * 处理路径最后不要以/结尾
	 */
	private void prosessPath(){
		if(this.staticpath!=null)
			staticpath = staticpath.endsWith("/")?staticpath.substring(0,staticpath.length()-1): staticpath;
		
		if(solutionpath!=null)
			solutionpath = solutionpath.endsWith("/")?solutionpath.substring(0,solutionpath.length()-1): solutionpath;

		
		if(staticdomain!=null)
			staticdomain = staticdomain.endsWith("/")?staticdomain.substring(0,staticdomain.length()-1): staticdomain;			
	}
	
	/**
	 * 保存eop参数配置
	 */
	private void saveEopParams(){
		this.prosessPath();
		InputStream in = FileUtil.getResourceAsStream("eop.properties");
		String webroot = StringUtil.getRootPath();
		Properties props = new Properties();
		try {
			props.load(in);
			
			//静态资源合并
			if(this.resourcemode==2 ){
				props.setProperty("path.imageserver", webroot+"/statics");
				props.setProperty("domain.imageserver", "http://"+ this.getRequest().getServerName()+":"+this.getRequest().getLocalPort() + this.getRequest().getContextPath() +"/statics"  );
			}

			//静态资源分离
			if(this.resourcemode==1 ){
				try{
					FileUtil.copyFolder( webroot+"/statics", this.staticpath);
					props.setProperty("path.imageserver", this.staticpath);
					props.setProperty("domain.imageserver", this.staticdomain);
				}catch(Exception e){
					throw new RuntimeException();
				}
			}
			
			//saas方式可以自定义解决方案所在目录
			if("2".equals(EopSetting.RUNMODE ) ){
				props.setProperty("storage.app_data", this.solutionpath+"/commons");
				props.setProperty("storage.products", this.solutionpath);
			}else{
				props.setProperty("storage.app_data", webroot+"/products/commons");
				props.setProperty("storage.products", webroot+"/products");
			}
			
			if("mysql".equals(dbtype))
				props.setProperty("dbtype", "1");
			else if("oracle".equals(dbtype))
				props.setProperty("dbtype", "2");
			else if("sqlserver".equals(dbtype))
				props.setProperty("dbtype", "3");
			props.setProperty("storage.EOPServer", webroot);
			props.setProperty("path.context_path", this.getRequest().getContextPath());
			
			String path = StringUtil.getRootPath("eop.properties");
			File file  = new File(path);		
			props.store(new FileOutputStream(file), "eop.properties");
			EopSetting.init(props);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String doInstall(){
		try{
			//saas模式可以自定义域名
			if("2".equals(EopSetting.RUNMODE)){
				eopInstallManager.install(uname,pwd,domain,productid);
			}else{
				eopInstallManager.install(uname,pwd,"localhost",productid);
			}
			this.json="{result:1}";
		}catch (RuntimeException e) {
			e.printStackTrace();
			this.json="{result:0}";
		}	
		return this.JSON_MESSAGE;
	}

	/**
	 * 测试数据库连接
	 * 和test库建立连接，检测用户试图创建的数据库是否存在，如果不存在则建立相应数据库。
	 * 创建后返回相应的数据源给jdbctemplate
	 * 然后进行一个数据库操作测试以证明数据库建立并连接成功
	 * @return
	 */
	private boolean createAndTest(String driver,String url){
		try{
			DataSource newDataSource = this.dataSourceCreator.createDataSource(driver,url,this.uname,this.pwd);
		
			if("mysql".equals(dbtype)) {	//	只有MySQL尝试建库
				this.jdbcTemplate.setDataSource(newDataSource);
				this.jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS `" + this.dbname +"` DEFAULT CHARACTER SET UTF8");
				newDataSource = this.dataSourceCreator.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://"+dbhost+"/"+dbname+"?useUnicode=true&characterEncoding=utf8", this.uname, this.pwd);
				this.jdbcTemplate.execute("use "+ this.dbname);
			}
			
			this.dataSource= newDataSource;
			this.jdbcTemplate.setDataSource(newDataSource);
			this.simpleJdbcTemplate = new SimpleJdbcTemplate(newDataSource);
			this.jdbcTemplate.execute("CREATE TABLE JAVAMALLTESTTABLE (ID INT not null)");
			this.jdbcTemplate.execute("DROP TABLE JAVAMALLTESTTABLE");

			return true;
			
		}catch(RuntimeException e){
			e.printStackTrace();
			this.logger.error(e.fillInStackTrace());
			return false;
		}
	}
	
	/**
	 * 测试MySQL连接
	 * @return
	 */
	
	private boolean mysqlTestConnection(){
		return createAndTest("com.mysql.jdbc.Driver", "jdbc:mysql://"+dbhost+"/test?useUnicode=true&characterEncoding=utf8");
	}
	
	/**
	 * 测试Oracle连接
	 * @return
	 */

	private boolean oracleTestConnection(){
		return createAndTest("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@" + dbhost + ":" + dbname);
	}
	
	private boolean sqlserverTestConnection(){
		return createAndTest("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://" + dbhost + ";databaseName=" + dbname);
	}
	
	public String testConnection(){
		boolean result = false;
		
		if("mysql".equals(dbtype))
			result = this.mysqlTestConnection();
		else if("oracle".equals(dbtype))
			result = this.oracleTestConnection();
		else if("sqlserver".equals(dbtype))
			result = this.sqlserverTestConnection();
			
		if(result){
			this.json="{result:1}";
		}else{
			this.json="{result:0}";
		}
		
		return this.JSON_MESSAGE;
	}
	
	public String testReady(){
		try{
			if("mysql".equals(dbtype))
				this.jdbcTemplate.execute("drop table if exists test");
			this.json="{result:1}";
			
		}catch(RuntimeException e){
			this.json="{result:0}";
		}		
		
		return this.JSON_MESSAGE;
	}
	
 
	/**
	 * 切换至新的数据源
	 * @return
	 */
	private DataSource switchNewDBSource(){
		this.testConnection();
		DataSource newDataSource = this.dataSourceCreator.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://"+dbhost+"/"+this.dbname+"?useUnicode=true&characterEncoding=utf8", this.uname, this.pwd);
		this.dataSource= newDataSource;
		this.jdbcTemplate.setDataSource(newDataSource);
		this.jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS `" + this.dbname +"` DEFAULT CHARACTER SET UTF8");
		this.jdbcTemplate.execute("use "+ this.dbname);
		return newDataSource;
	}
		
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getDbhost() {
		return dbhost;
	}

	public void setDbhost(String dbhost) {
		this.dbhost = dbhost;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getDbtype() {
		return dbtype;
	}


	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}


	public String getDbname() {
		return dbname;
	}


	public void setDbname(String dbname) {
		this.dbname = dbname;
	}


	public EopInstallManager getEopInstallManager() {
		return eopInstallManager;
	}


	public void setEopInstallManager(EopInstallManager eopInstallManager) {
		this.eopInstallManager = eopInstallManager;
	}


	public String getOsVersion() {
		return osVersion;
	}


	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}


	public String getJavaVersion() {
		return javaVersion;
	}


	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getProductid() {
		return productid;
	}


	public void setProductid(String productid) {
		this.productid = productid;
	}


	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}


	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public String getStaticdomain() {
		return staticdomain;
	}


	public void setStaticdomain(String staticdomain) {
		this.staticdomain = staticdomain;
	}


	public String getStaticpath() {
		return staticpath;
	}


	public void setStaticpath(String staticpath) {
		this.staticpath = staticpath;
	}


	public int getResourcemode() {
		return resourcemode;
	}


	public void setResourcemode(int resourcemode) {
		this.resourcemode = resourcemode;
	}


	public String getSolutionpath() {
		return solutionpath;
	}


	public void setSolutionpath(String solutionpath) {
		this.solutionpath = solutionpath;
	}


	public IDataSourceCreator getDataSourceCreator() {
		return dataSourceCreator;
	}


	public void setDataSourceCreator(IDataSourceCreator dataSourceCreator) {
		this.dataSourceCreator = dataSourceCreator;
	}

	
}

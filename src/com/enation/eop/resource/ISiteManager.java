package com.enation.eop.resource;

import java.util.List;
import java.util.Map;

import com.enation.eop.resource.model.Dns;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopSiteDomain;
import com.enation.eop.resource.model.EopUser;
import com.enation.framework.database.Page;

/**
 * 站点管理
 * @author lzf
 *         <p>
 *         created_time 2009-12-11 下午02:04:55
 *         </p>
 * @version 1.0
 */
public interface ISiteManager {

	/**
	 * 创建站点
	 * 
	 * @param site 站点信息
	 * @param domain 站点域名
	 * @return
	 */
	public Integer add(EopSite site,String domain);
	public Integer add(EopUser user, EopSite site, String domain) ;
	
/*	*//**
	 * 创建站点
	 * @param user 站点的所属用户 
	 * @param site 要创建的站点信息
	 * @param domain 域名
	 * @return
	 *//*
	public Integer add(EopUser user,EopSite site,String domain);
	*/
	
	/**
	 * 分页取得某User的所有站点
	 * @param pageNo
	 * @param pageSize
	 * @param userid
	 * @param order
	 * @param search
	 * @return
	 */
	public Page list(int pageNo, int pageSize, String order,
			String search);
	
	
	/**
	 * 读取某用户的所有站点列表
	 * @param userid
	 * @return
	 */
	public List list(Integer userid);
	
	

	/**
	 * 分页读取所有站点<br>
	 * 可以按关键字搜索
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @param keyword  关键字:模糊匹配域名站点名，用户名
	 * @return
	 */
	public Page list(String keyword,int pageNo,int pageSize);
	
	
	/**
	 * 读取所有站点列表
	 * @return
	 */
	public  List<Map>   list();
	
	
	
	/**
	 * 取得站点与域名的对照表
	 * 
	 * @return List<Dns>, @see com.enation.eop.api.core.model.Dns
	 */
	public List<Dns> getDnsList();

	/**
	 * 根据id取得一个具体的Site
	 * 
	 * @param id
	 * @return
	 */
	public EopSite get(Integer id);

	/**
	 * 根据域名取得站点
	 * 
	 * @param domain
	 * @return
	 */
	public EopSite get(String domain);
	
	
	
	/**
	 * 检测域名是否存在
	 * @param domain
	 * @return
	 */
	public Boolean checkInDomain(String domain);
	
	
	

	/**
	 * 修改站点信息
	 * 
	 * @param eopSite
	 * @return
	 */
	public void edit(EopSite eopSite);
	
	
	
	

	/**
	 * 删除指定站点
	 * 
	 * @param id
	 * @return
	 */
	public void delete(Integer id);
	
	
	

	/**
	 * 增加一个域名
	 * @param eopSiteDomain
	 * @return 
	 * @throws IllegalArgumentException 如果域名已经存在
	 */
	public int addDomain(EopSiteDomain eopSiteDomain);
	
	
	

	/**
	 *根据域名id 删除一个域名
	 * 
	 * @param domainid
	 * @return
	 */
	public void  deleteDomain(Integer domainid);
 
	
	/**
	 * 删除域名
	 * @param domain
	 */
	public void  deleteDomain(String domain);
	
	
	/**
	 * 读取某用户某站点的所有域名列表
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List listDomain(Integer userid,Integer siteid);
	
	
	/**
	 * 修改域名
	 * @param domain_old
	 * @param domain_new
	 */
	public void editDomain(String domain_old, String domain_new);
	
	
	
	/**
	 * 设置站点的产品id
	 * @param userid
	 * @param siteid
	 * @param productid
	 */
	public void setSiteProduct(Integer userid, Integer siteid, String productid);

	 
	
	
	
	/**
	 * 切换后台主题 
	 * @param themeid
	 */
	public void changeAdminTheme(Integer themeid);
	
	
	
	
	
	/**
	 * 切换前台主题
	 * @param themeid
	 */
	public void changeTheme(Integer themeid);
	
	
	/**
	 * 更新站点的积分
	 * @param id
	 * @param point
	 */
	public void updatePoint(Integer id,int point,int sitestate); 
	
	
	/**
	 * 领取积分
	 * @param id
	 * @param point
	 * @return 1:领取成功 0本月已经领取过，不能再领取
	 */
	public int getPoint(Integer id,int point);
	
	
	/**
	 * 初始化当前站点数据
	 * @throws RuntimeException  本站点初始化脚本不存在
	 */
	public void initData();
	
	
	/**
	 * 获取当前站点的应用列表
	 * 一般由站点下的profile.xml读取
	 * @return
	 */
	public List<EopApp> getSiteApps();
	
	
}

package com.enation.app.base.core.service.solution;

import java.util.List;
import java.util.Map;

import com.enation.eop.resource.model.EopProduct;
import com.enation.framework.database.Page;

/**
 * 产品业务逻辑
 * @author kingapex
 * 2010-1-20下午07:11:34
 */
public interface IProductService {
	
	 
//	public Page pageProduct(Integer catid,Integer typeid,String order, int page, int pageSize);
	
	public Page pageProduct(Integer catid,Integer colorid,Integer typeid,Integer state,String order,int page,int pageSize);
	
	
	public EopProduct getProduct(Integer id);
	
	
	public EopProduct getProduct(String product_id);
	
	
	
  
	
	
	/**
	 * 添加解决方案
	 * @param product 解决方案实体，其id属性和名称属性不能为空，否则将会抛出 {@link IllegalArgumentException}
	 * @param zipPath 解决方案zip包文件绝对路径，添加时不能为空，否则将会抛出 {@link IllegalArgumentException}
	 * @return 1成功 0解决方案id已经存在
	 */
	public int  add(EopProduct product, String zipPath);
	
	
	
	
	/**
	 * 更新解决方案<br/>
	 * @param product 解决方案实体，其id属性和名称属性不能为空，否则将会抛出 {@link IllegalArgumentException}
	 * @param zipPath 解决方案zip包文件绝对路径, 当zipPath为空时则只更新解决方案的基本信息。
	 * @throws  {@link IllegalArgumentException}
	 */
	public void update(EopProduct product, String zipPath);
	
	
	
	
	
	/**
	 * 删除解决方案<br>
	 * 将会删除数据库信息和解决方案文件
	 * @param productid 解决方案id
	 * @throws  {@link IllegalArgumentException} 当productid为空时
	 */
	public void delete(String productid);
	
	
	/**
	 * 读取类型列表
	 * @return
	 */
	public List listType();
	
	
	/**
	 * 获取方案类型详细
	 * @param typeid
	 * @return
	 */
	public Map getType(Integer typeid);
	
	
	
	/**
	 * 保存排序
	 * @param sorts 排序值数组
	 * @param ids	id数组
	 * @throws IllegalArgumentException 当sorts为空或ids为空或sort的长度不等于ids的长度
	 */
	public void updateSort(Integer[] sorts,Integer[] ids);
	
	
	
 
	
}

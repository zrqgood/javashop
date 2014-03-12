package com.enation.app.base.core.service.solution;

import java.util.List;

import com.enation.app.base.core.model.ProductCat;
import com.enation.framework.database.Page;

/**
 * 解决方案类别业务管理
 * @author kingapex
 * 2010-9-1下午01:48:17
 */
public interface IProductCatService {

	
	
	/**
	 * 获取分类
	 * @param id
	 * @return
	 */
	 public ProductCat get(int id) ;
	
	
	
	
	/**
	 * 添加分类
	 * @param cat
	 */
	public  void add(ProductCat cat );
	
	
	
	/**
	 * 修改分类
	 * @param cat
	 */
	public void edit(ProductCat cat );
	
	
	/**
	 * 删除分类
	 * @param id
	 * @return 1:删除成功,2：分类下有解决方案不能删除
	 */
	public int detete(int id);
	
	
	
	/**
	 * 读取所有分类列表 
	 * @return
	 */
	public List<ProductCat> list();
	
	
	
	/**
	 * 分页读取分页列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page list(int pageNo,int pageSize);
}

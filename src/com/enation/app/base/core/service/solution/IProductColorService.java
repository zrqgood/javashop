package com.enation.app.base.core.service.solution;

import java.util.List;

import com.enation.app.base.core.model.ProductCat;
import com.enation.app.base.core.model.ProductColor;
import com.enation.framework.database.Page;

/**
 * 解决方案类别业务管理
 * @author kingapex
 * 2010-9-1下午01:48:17
 */
public interface IProductColorService {

	
	
	/**
	 * 获取颜色
	 * @param id
	 * @return
	 */
	 public ProductColor get(int id) ;
	
	
	
	
	/**
	 * 添加颜色
	 * @param color
	 */
	public  void add(ProductColor color );
	
	
	
	/**
	 * 修改颜色
	 * @param color
	 */
	public void edit(ProductColor color );
	
	
	/**
	 * 删除颜色
	 * @param id
	 * @return 1:删除成功,2：分类下有解决方案不能删除
	 */
	public int detete(int id);
	
	
	
	/**
	 * 读取所有分类列表 
	 * @return
	 */
	public List<ProductColor> list();
	
	
	
	/**
	 * 分页读取分页列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page list(int pageNo,int pageSize);
}

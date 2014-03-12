package com.enation.cms.core.service;

import java.util.List;

import com.enation.cms.core.model.DataCat;

/**
 * 数据分类管理
 * @author kingapex
 * 2010-7-5上午07:19:57
 */
public interface IDataCatManager {
	
	/**
	 * 添加一个分类
	 * @param cat
	 */
	public void add(DataCat cat);
	
	/**
	 * 修改分类
	 * @param cat
	 */
	public void edit(DataCat cat);
	
	/**
	 * 删除分类
	 * @param catid
	 * @return
	 */
	public int delete(Integer catid);
	
	
	/**
	 * 获取一个文章分类详细
	 * @param catid
	 * @return
	 */
	public DataCat get(Integer catid);
	
	
	/**
	 * 读取某个分类下的所有子类，包括 子孙结眯
	 * @param parentid 分类id
	 * @return
	 */
	public List<DataCat> listAllChildren(Integer parentid);
	
	
	
	/**
	 * 更新分类的排序
	 * @param cat_ids
	 * @param cat_sorts
	 */
	public void saveSort(int[] cat_ids, int[] cat_sorts);
	
	
	/**
	 * 查询某类别的所有父并形成列表，列表的最后一个为此类别本身
	 * @param catid
	 * @return
	 */
	public List<DataCat> getParents(Integer catid );
	
}

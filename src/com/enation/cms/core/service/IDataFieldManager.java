package com.enation.cms.core.service;

import java.util.List;

import com.enation.cms.core.model.DataField;

public interface IDataFieldManager {
	
	/**
	 * 添加某字段
	 * @param dataField
	 * @return
	 */
	public Integer add(DataField dataField );
	
	
	
	
	
	/**
	 * 修改某字段信息
	 * @param dataField
	 */
	public void edit(DataField dataField );
	
	
	/**
	 * 删除某模型的所有字段
	 * @param modelid
	 */
	public void delete(Integer modelid);
	
	
	
	/**
	 * 查询某模型下的字段列表
	 * @param modelid
	 * @return
	 */
	public List<DataField> list(Integer modelid);
	
	
	/**
	 * 读取某模型可以显示在列表中的字段列表
	 * @param modelid
	 * @return
	 */
	public List<DataField> listIsShow(Integer modelid);
	
	
	/**
	 * 获取某字段的字段详细
	 * @param fieldid
	 * @return
	 */
	public DataField get(Integer fieldid);
	
	
	/**
	 * 查询某类别下的字段列表
	 * @param catid
	 * @return
	 */
	public List<DataField> listByCatId(Integer catid);
	
	
	/**
	 *  更新字段排序
	 * @param ids
	 * @param sorts
	 */
	public void saveSort(Integer[] ids,Integer sorts[]);
	
}

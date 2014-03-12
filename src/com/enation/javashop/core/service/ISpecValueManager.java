package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.javashop.core.model.SpecValue;

/**
 * 规格值管理
 * @author kingapex
 *2010-3-7下午12:32:13
 */
public interface ISpecValueManager {
	
	/**
	 * 添加一个规格值 
	 * @param value
	 */
	public void add(SpecValue value);

	
	/**
	 * 读取某个规格的规格值
	 * @param spec_id
	 * @return
	 */
	public List<SpecValue> list(Integer spec_id);

	
	/**
	 * 根据id获取规格值的详细
	 * @param value_id
	 * @return
	 */
	public Map get(Integer value_id);
	
	
}

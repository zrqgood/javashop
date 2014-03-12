package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.DlyCenter;

/**
 * 发货中心
 * 
 * @author lzf<br/>
 *         2010-4-30上午10:12:50<br/>
 *         version 1.0
 */
public interface IDlyCenterManager {

	public List<DlyCenter> list();
	
	public DlyCenter get(Integer dly_center_id);

	public void add(DlyCenter dlyCenter);

	public void edit(DlyCenter dlyCenter);

	public void delete(Integer[] id);

}

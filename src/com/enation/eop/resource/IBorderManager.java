package com.enation.eop.resource;

import java.util.List;

import com.enation.eop.resource.model.Border;

/**
 * 边框管理
 * @author kingapex
 * 2010-1-28下午04:41:16
 */
public interface IBorderManager {
	
	public void add(Border border);
	public void update(Border border);
	public void delete(Integer id);
	public  List<Border> list();
	
	/**
	 * 清除某站点的边框数据
	 * 一般用于安装边框时的清除数据
	 */
	public void clean();
	
}

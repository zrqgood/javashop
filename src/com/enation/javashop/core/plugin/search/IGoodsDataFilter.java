package com.enation.javashop.core.plugin.search;

import java.sql.ResultSet;
import java.util.Map;

/**
 * 过滤商品数据接口
 * @author kingapex
 *
 */
public interface IGoodsDataFilter {
	public void filter(Map<String,Object> goods,ResultSet rs);
}

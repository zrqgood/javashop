package com.enation.javashop.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.javashop.core.model.support.GoodsView;

/**
 * 商品列表mapper
 * @author kingapex
 * 2010-7-16下午01:48:59
 */
public class GoodsListMapper implements RowMapper {

	/**
	 * 返回{@link GooodsView}
	 * 在本方法中对属性进行了读取和处理，并放入到了 propMap属性
	 */
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GoodsView  goods = new GoodsView();
		goods.setName(rs.getString("name"));
		goods.setGoods_id(rs.getInt("goods_id"));
		goods.setImage_default(rs.getString("image_default"));
		goods.setMktprice(rs.getDouble("mktprice"));
		goods.setPrice(rs.getDouble("price"));
		goods.setCreate_time(rs.getLong("create_time"));
		goods.setLast_modify(rs.getLong("last_modify"));
		goods.setType_id(rs.getInt("type_id"));
		goods.setPoint(rs.getInt("point"));
		goods.setStore(rs.getInt("store"));
		goods.setCat_id(rs.getInt("cat_id"));
		
		goods.setSn(rs.getString("sn"));
		goods.setIntro(rs.getString("intro"));
		goods.setStore(rs.getInt("store"));
		goods.setImage_file(UploadUtil.replacePath(rs.getString("image_file")));
		
		Map propMap = new HashMap();
		
		for(int i=0;i<20;i++){
			String value = rs.getString("p" + (i+1));
			propMap.put("p"+(i+1),value);
		}
		goods.setPropMap(propMap);
	
		return goods;
	}

} 

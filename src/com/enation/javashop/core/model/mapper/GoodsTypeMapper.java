package com.enation.javashop.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.enation.javashop.core.model.GoodsType;

public class GoodsTypeMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GoodsType goodsType = new GoodsType();
		goodsType.setType_id(rs.getInt("type_id"));
		goodsType.setName(rs.getString("name"));
		goodsType.setHave_parm(rs.getInt("have_parm"));
		goodsType.setHave_prop(rs.getInt("have_prop"));
		goodsType.setIs_physical(rs.getInt("is_physical"));
		goodsType.setJoin_brand(rs.getInt("join_brand"));
		goodsType.setProps(rs.getString("props"));
		goodsType.setParams(rs.getString("params"));
		
		return goodsType;
		
	}

}

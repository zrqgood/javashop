package com.enation.javashop.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.RowMapper;

import com.enation.javashop.core.model.support.TypeArea;
import com.enation.javashop.core.model.support.TypeAreaConfig;

/**
 * 配送地区-配送方式关联表mapper
 * @author kingapex
 *2010-3-30上午08:58:02
 */
public class TypeAreaMapper implements RowMapper {

	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TypeArea typeArea = new TypeArea();
		typeArea.setArea_id_group(rs.getString("area_id_group"));
		typeArea.setArea_name_group(rs.getString("area_name_group"));
		typeArea.setConfig(rs.getString("config"));
		typeArea.setExpressions(rs.getString("expressions"));
		typeArea.setHas_cod(rs.getInt("has_cod"));
		typeArea.setType_id(rs.getInt("type_id"));
		JSONObject configJsonObject = JSONObject.fromObject(typeArea.getConfig());
		typeArea.setTypeAreaConfig((TypeAreaConfig) JSONObject.toBean(configJsonObject,TypeAreaConfig.class));
		return typeArea;
	}

}

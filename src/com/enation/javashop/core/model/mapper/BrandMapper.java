package com.enation.javashop.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.javashop.core.model.Brand;

/**
 * 品牌Mapper<br>
 * 会将本地文件存储的图片地址前缀替换为静态资源服务器地址。
 * @author kingapex
 * 2010-7-16下午03:17:28
 */
public class BrandMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Brand brand = new Brand();
		brand.setBrand_id(rs.getInt("brand_id"));
		brand.setBrief(rs.getString("brief"));
		String logo = rs.getString("logo");
		if(logo!=null){
			logo  =UploadUtil.replacePath(logo);
		}
		brand.setLogo(logo);
		brand.setName(rs.getString("name"));
		brand.setUrl(rs.getString("url"));
		return brand;
	}

}

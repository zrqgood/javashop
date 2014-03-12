package com.enation.app.base.core.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
/**
 * 广告mapper<br>
 * 将附件url本地格式转为静态资源服务器地址<br>
 * 读取非adv表以外的字段cname,在sql语句中必须给出
 * @author kingapex
 * 2010-7-17上午11:02:24
 */
public class AdvMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Adv adv = new Adv();
		adv.setAcid(rs.getInt("acid"));
		adv.setAid(rs.getInt("aid"));
		adv.setAname(rs.getString("aname"));
		String atturl = rs.getString("atturl");
		if(atturl!=null)  atturl  =UploadUtil.replacePath(atturl);
		adv.setAtturl(atturl);
		adv.setAtype(rs.getInt("atype"));
		adv.setBegintime(rs.getLong("begintime"));
		adv.setClickcount(rs.getInt("clickcount"));
		adv.setCompany(rs.getString("company"));
		adv.setContact(rs.getString("contact"));
		adv.setDisabled(rs.getString("disabled"));
		adv.setEndtime(rs.getLong("endtime"));
		adv.setIsclose(rs.getInt("isclose"));
		adv.setLinkman(rs.getString("linkman"));
		adv.setUrl(rs.getString("url"));
		
		adv.setCname(rs.getString("cname"));
		
		return adv;
	}

}

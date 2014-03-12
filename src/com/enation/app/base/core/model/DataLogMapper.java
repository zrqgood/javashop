package com.enation.app.base.core.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.model.Image;

/**
 * 数据日志mapper
 * @author kingapex
 * 2010-10-20上午09:59:22
 */
public class DataLogMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		
		DataLog datalog = new DataLog();
		datalog.setContent(rs.getString("content"));
		datalog.setDateline(rs.getInt("dateline"));
		datalog.setDomain(rs.getString("domain"));
		datalog.setId(rs.getInt("id"));
		datalog.setLogtype(rs.getString("logtype"));
		datalog.setOptype(rs.getString("optype"));
		String pics = rs.getString("pics");
		datalog.setPics(pics);
		datalog.setSitename(rs.getString("sitename"));
		datalog.setSiteid(rs.getInt("siteid"));
		datalog.setUserid(rs.getInt("userid"));
		datalog.setUrl(rs.getString("url"));
		
		//处理图片列表
		if(pics!=null && !"".equals(pics)){
			List<Image> imgList = new ArrayList<Image>();
			String[] picar = pics.split(",");
			
			for(String picstr:picar){
				String[] pic= picstr.split("\\|");
				
				String thumbpic  = pic[0];
				String originalpic = pic[1];
				
				if(thumbpic!=null){
					thumbpic = thumbpic.replaceAll(EopSetting.FILE_STORE_PREFIX,EopSetting.IMG_SERVER_DOMAIN+"/user/"+ datalog.getUserid()+"/"+ datalog.getSiteid() );
				}
				
				if(originalpic!=null){
					originalpic= originalpic.replaceAll(EopSetting.FILE_STORE_PREFIX,EopSetting.IMG_SERVER_DOMAIN+"/user/"+ datalog.getUserid()+"/"+ datalog.getSiteid() );
				}
				Image image  = new Image();
				image.setOriginal(originalpic);
				image.setThumb(thumbpic);
				imgList.add(image);
			}
			datalog.setPicList(imgList);
		}
		
		return datalog;
	}

}

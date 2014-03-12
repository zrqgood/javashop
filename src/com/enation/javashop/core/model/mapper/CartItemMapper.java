package com.enation.javashop.core.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.support.CartItem;

public class CartItemMapper implements RowMapper {

	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CartItem cartItem = new CartItem();
		cartItem.setId(rs.getInt("cart_id"));
		cartItem.setProduct_id(rs.getInt("product_id"));
		cartItem.setGoods_id(rs.getInt("goods_id"));
		cartItem.setName(rs.getString("name"));
		cartItem.setMktprice(rs.getDouble("mktprice"));
		cartItem.setPrice(rs.getDouble("price"));
		cartItem.setCoupPrice(rs.getDouble("price")); //优惠价格默认为销售价，则优惠规则去改变
	//	cartItem.setCatid(rs.getInt("catid"));
		
		String image_default =  rs.getString("image_default");
		
		if(image_default!=null ){
			image_default  =UploadUtil.replacePath(image_default);
		}
		cartItem.setImage_default(image_default);
		cartItem.setNum(rs.getInt("num"));
		cartItem.setPoint(rs.getInt("point"));
		cartItem.setItemtype(rs.getInt("itemtype"));
		if( cartItem.getItemtype().intValue() ==  0){
			cartItem.setAddon(rs.getString("addon"));
		}
		//赠品设置其限购数量
		if( cartItem.getItemtype().intValue() ==  2){
			cartItem.setLimitnum(rs.getInt("limitnum"));
		}
		 
		if( cartItem.getItemtype().intValue() ==  1){
			cartItem.setSn(rs.getString("sn"));
		}
		
		if( cartItem.getItemtype().intValue() ==  0){
			String specs = rs.getString("specs");
			cartItem.setSpecs(specs);
			if(StringUtil.isEmpty(specs)) 
				cartItem.setName(  cartItem.getName() );
			else
				cartItem.setName(  cartItem.getName() +"("+ specs +")" );
		}		
		
		return cartItem;
	}

}

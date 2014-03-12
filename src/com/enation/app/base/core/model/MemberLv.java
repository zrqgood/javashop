package com.enation.app.base.core.model;

import com.enation.framework.database.NotDbField;



/**
 * 会员级别
 * @author kingapex
 *
 */
public class MemberLv  implements java.io.Serializable {


    // Fields 
	
	public MemberLv(){};
	public MemberLv(Integer lv_id, String name){
		this.lv_id = lv_id;
		this.name = name;
	}

     private Integer lv_id;
     private String name;
     private Integer default_lv;
     private Integer discount;
     private Double lvPrice;
     private int point;
     private boolean selected;
     
	public Integer getDefault_lv() {
		return default_lv;
	}
	public void setDefault_lv(Integer default_lv) {
		this.default_lv = default_lv;
	}
	public Integer getLv_id() {
		return lv_id;
	}
	public void setLv_id(Integer lv_id) {
		this.lv_id = lv_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotDbField
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	@NotDbField
	public Double getLvPrice() {
		return lvPrice;
	}
	public void setLvPrice(Double lvPrice) {
		this.lvPrice = lvPrice;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	} 

}
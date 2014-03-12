package com.enation.javashop.core.model;

/**
 * 商品类型
 * @author apexking
 *
 */
public class GoodsType  implements java.io.Serializable {


    // Fields    

     private Integer type_id;
     private String name;
     private String props;
     private String params;
     private int disabled;
     private int is_physical;
     private int have_prop;
     private int have_parm;
     private int join_brand;
     private int have_field; //2011/03/21新增:是否有自定义字段
     private Integer[] brand_ids;

	 
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	public int getHave_parm() {
		return have_parm;
	}
	public void setHave_parm(int have_parm) {
		this.have_parm = have_parm;
	}
	public int getHave_prop() {
		return have_prop;
	}
	public void setHave_prop(int have_prop) {
		this.have_prop = have_prop;
	}
	public int getIs_physical() {
		return is_physical;
	}
	public void setIs_physical(int is_physical) {
		this.is_physical = is_physical;
	}
	public int getJoin_brand() {
		return join_brand;
	}
	public void setJoin_brand(int join_brand) {
		this.join_brand = join_brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer[] getBrand_ids() {
		return brand_ids;
	}
	public void setBrand_ids(Integer[] brand_ids) {
		this.brand_ids = brand_ids;
	}
	public int getHave_field() {
		return have_field;
	}
	public void setHave_field(int haveField) {
		have_field = haveField;
	}


	

}
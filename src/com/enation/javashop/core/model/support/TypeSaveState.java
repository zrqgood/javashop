package com.enation.javashop.core.model.support;

/**
 * 商品类型在流程中的保存状态
 * @author apexking
 *
 */
public class TypeSaveState   {

 

     
     /*****以下参数控制流程所用。******/
	 private int do_save_props; //是否完成保存属性
	 private int do_save_params; //是否完成保存参数
	 private int do_save_brand; //是否完成保存品牌关联 
	 
	 
	public int getDo_save_brand() {
		return do_save_brand;
	}
	public void setDo_save_brand(int do_save_brand) {
		this.do_save_brand = do_save_brand;
	}
	public int getDo_save_params() {
		return do_save_params;
	}
	public void setDo_save_params(int do_save_params) {
		this.do_save_params = do_save_params;
	}
	public int getDo_save_props() {
		return do_save_props;
	}
	public void setDo_save_props(int do_save_props) {
		this.do_save_props = do_save_props;
	}

	

}
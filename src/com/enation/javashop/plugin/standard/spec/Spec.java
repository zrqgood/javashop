package com.enation.javashop.plugin.standard.spec;




/**
 * 商品规格
 * @author apexking
 *
 */
public class Spec  implements java.io.Serializable {


    // Fields    

     private Integer spec_id;
     private Integer goods_id;
     private String sn;
     private Integer store;
     private Double price;
     private String specs; //存储是什么样的规格组合
      
     
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	public String[] getSpecAr(){
		String[] spec_ar =null;
		
		if(specs!=null){
			String[] tempAr = specs.split(",");
			spec_ar= new String[tempAr.length];
			
			for(int i=0;i<tempAr.length;i++){
				spec_ar[i] = tempAr[i].replaceAll("\"", "");
			}
			
		}
		
		return spec_ar;
	}
 


}
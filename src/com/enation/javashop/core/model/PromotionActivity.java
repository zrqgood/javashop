package com.enation.javashop.core.model;

/**
 * 促销活动
 * @author kingapex
 *2010-4-15上午11:57:56
 */
public class PromotionActivity {
	private Integer id;	      
	private String  name;	   	
	private int enable;		
	private Long  begin_time;	
	private Long  end_time;	
	private String brief;		
	private int disabled ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Long getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Long beginTime) {
		begin_time = beginTime;
	}
	public Long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Long endTime) {
		end_time = endTime;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	} 	
	
	
}

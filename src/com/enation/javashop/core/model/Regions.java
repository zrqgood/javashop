package com.enation.javashop.core.model;

/**
 * 行政区划实体
 * @author lzf<br/>
 * 2010-3-16 下午03:02:33<br/>
 * version 1.0<br/>
 */
public class Regions implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793615515414923123L;
	private int region_id;
	private Integer p_region_id;
	private String region_path;
	private int region_grade;
	private String local_name;
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int regionId) {
		region_id = regionId;
	}
	
	public Integer getP_region_id() {
		return p_region_id;
	}
	public void setP_region_id(Integer pRegionId) {
		p_region_id = pRegionId;
	}
	public String getRegion_path() {
		return region_path;
	}
	public void setRegion_path(String regionPath) {
		region_path = regionPath;
	}
	public int getRegion_grade() {
		return region_grade;
	}
	public void setRegion_grade(int regionGrade) {
		region_grade = regionGrade;
	}
	public String getLocal_name() {
		return local_name;
	}
	public void setLocal_name(String localName) {
		local_name = localName;
	}
	
}

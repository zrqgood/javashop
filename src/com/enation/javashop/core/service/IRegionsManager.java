package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.Regions;

/**
 * 行政区划管理接口
 * 
 * @author lzf<br/>
 *         2010-3-16 下午03:05:17<br/>
 *         version 1.0<br/>
 */
public interface IRegionsManager {
	
	/**
	 * 省级单位列表
	 * @return
	 */
	public List listProvince();
	
		
	/**
	 * 某省内城市列表
	 * @param province_id
	 * @return
	 */
	public List listCity(int province_id);
	
	/**
	 * 某市行政区列表
	 * @param city_id
	 * @return
	 */
	public List listRegion(int city_id);
	
	
	/**
	 * 读取某个区域的子区域
	 * @param regionid
	 * @return
	 */
	public List listChildren(Integer regionid);
	
	/**
	 * 根据某些区域id串（","号分隔）查询全部子地区
	 * @param regionid
	 * @return
	 */
	public List listChildren(String regionid);
	
	
	/**
	 * 获取某个区域的子区域json串
	 * @param regionid
	 * @return
	 */
	public String getChildrenJson(Integer regionid);
	
	/**
	 * 取得一个地区
	 * @param region_id
	 * @return
	 */
	public Regions get(int region_id);
	
	
	/**
	 * 根据名字获取地区
	 * @param name
	 * @return
	 */
	public Regions getByName(String name);
	
	
	/**
	 * 新增
	 * @param regions
	 */
	public void add(Regions regions);
	
	/**
	 * 删除
	 * @param region_id
	 */
	public void delete(int region_id);
	
	/**
	 * 修改
	 * @param regions
	 */
	public void update(Regions regions);
	
	/**
	 * 初始化/恢复地区数据。
	 */
	public void reset();
	
	/**
	 * 根据给定的串取得省、市、区信息
	 * @param area, 如：北京-北京市-东城区
	 * @return result[0]是省，result[1]为市，result[2]为区
	 */
	public Regions[] get(String area);

}

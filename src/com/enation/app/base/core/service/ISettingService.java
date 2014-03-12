package com.enation.app.base.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ISettingService {

 
	/**
	 * 更新系统设置
	 * 
	 * @param code
	 * @param value
	 * @throws SettingRuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public abstract void save(Map<String,Map<String,String>> settings ) throws SettingRuntimeException;

	
	/**
	 * 读取全部设置
	 * 
	 * @param group
	 * @param code
	 * @return
	 */
	public abstract Map<String,Map<String,String>>  getSetting();
	
	
	/**
	 * 设置输入页面显示事件
	 * @return
	 */
	public abstract List<String> onInputShow();
	
	
	/**
	 * 读取某项设置值
	 * @param name 参数名
	 * @return 参数值
	 */
	public abstract String getSetting(String group,String name);

}
package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.plugin.IPlugin;
import com.enation.javashop.core.model.PayCfg;

/**
 * 支付方式管理接口
 * @author kingapex
 *2010-4-6下午03:24:02
 */
public interface IPaymentManager {
	
	/**
	 * 获取配送方式列表
	 * @return
	 */
	public List list();
	
	
	/**
	 * 获取某个配送方式
	 * @param id 
	 * @return
	 */
	public PayCfg get(Integer id);
	
	
	
	/**
	 * 通过插件id获取配置详细
	 * @param pluginid
	 * @return
	 */
	public PayCfg get(String pluginid) ;
	
	
	
	/**
	 * 计算支付费用
	 * @param id
	 * @return
	 */
	public Double countPayPrice(Integer id);
	
	
	
	
	
	/**
	 * 用户安装一个支付方式
	 * @param name 支付方式名称
	 * @param type 支付方式类型，此参数为支付方式的重要标识，其值和支付方式插件bean名称要一至。
	 * @param biref 支付方式描述，可为空
	 * @param configParmas  支付方式配置参数名称和参数值对照map
	 * @throws IllegalArgumentException 如果name、type或configParmas其中一项为空时抛出此异常。
	 * @see PaymentTest#testAdd
	 */
	public void add(String name,String type,String biref,Map<String,String> configParmas);
	
	
	
	/**
	 * 修改某个支付方式
	 * @param paymentId 要修改的支付方式id
	 * @param name 支付方式名称
	 * @param biref 支付方式描述，可为空
	 * @param configParmas  支付方式配置参数名称和参数值对照map
	 * @throws IllegalArgumentException 如果name、type或configParmas其中一项为空时抛出此异常。
	 * @see PaymentTest#testEdit
	 */
	public void edit(Integer paymentId,String name,String biref,Map<String,String> configParmas);
	
	
	
	/**
	 * 读取个支付方式的配置参数
	 * @param paymentId 支付方式id
	 * @return 配置参数名和参数值的对照map
	 */
	public Map<String,String> getConfigParams(Integer paymentId);
	
	
	
	/**
	 * 读取个支付方式的配置参数
	 * @param paymentId 支付方式插件id
	 * @return 配置参数名和参数值的对照map
	 */	
	public Map<String,String> getConfigParams(String pluginid);
	
	
	
	/**
	 * 批量删除支付方式
	 * @param idArray 支付方式id数组,如果无空则不进行任何操作
	 */
	public void delete(Integer idArray[]);

	
	/**
	 * 读取支付方式插件列表
	 * @return 系统中注册的所有支付方式插件
	 */
	public  List<IPlugin>  listAvailablePlugins();
	
	
	/**
	 * 获取支付方式安装参数的html
	 * @param pluginId 插件id
	 * @param 安装后的支付方式id，未安装则传入null
	 * @return 插件安装时的html
	 */
	public String getPluginInstallHtml(String pluginId,Integer paymentId);
	
	
}
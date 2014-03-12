package com.enation.javashop.core.plugin.field;

import java.util.List;
import java.util.Map;

import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;
import com.enation.javashop.core.model.Goods;

/**
 * 商品字段插件桩
 * @author kingapex
 *
 */
public class GoodsFieldPluginBundle extends AutoRegisterPluginsBundle {

	@Override
	public String getName() {
		
		return "商品字段插件桩";
	}

	
	/**
	 * 激发商品字段数据保存事件<br>
	 * 调用所有的商品插件的保存事件，并传递给相应的GoodsField(每个插件都会对相应用goodsField的)
	 * @param goods
	 * @param goodsField
	 */
	public void onSave(Map goods,GoodsField goodsField){
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof AbstractGoodsFieldPlugin) {
						AbstractGoodsFieldPlugin fieldPlugin = (AbstractGoodsFieldPlugin) plugin;
						if(fieldPlugin.getId().equals(  goodsField.getPluginid() )){
							fieldPlugin.onSave(goods,goodsField);
						}
					}  
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 激发字段保存后事件
	 * @param goods
	 * @param goodsField
	 */
	public void onAfterSave(Map goods,GoodsField goodsField){
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IFieldAfterSaveEvent) {
						IFieldAfterSaveEvent fieldPlugin = (IFieldAfterSaveEvent) plugin;
						if(plugin.getId().equals(  goodsField.getPluginid() )){
							fieldPlugin.onAfterSave(goods,goodsField);
						}
					}  
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
	
	
	
	/**
	 * 获取某个字段的字段插件
	 * @param goodsField
	 * @return
	 */
	public AbstractGoodsFieldPlugin getFieldPlugin(GoodsField goodsField){
		
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof AbstractGoodsFieldPlugin) {
					AbstractGoodsFieldPlugin fieldPlugin = (AbstractGoodsFieldPlugin) plugin;
					if(fieldPlugin.getId().equals(goodsField.getPluginid()) ){
						return fieldPlugin;
					}
				}  
				
			}
		}
		
		return null;
		
	}

	public String onDisplay(GoodsField field,Map goods){
		try {

			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IFieldDispalyEvent) {
						
						IFieldDispalyEvent fieldPlugin = (IFieldDispalyEvent) plugin;
						if(plugin.getId().equals(   field.getPluginid()  )){
							return  fieldPlugin.onDisplay(field, goods);
						}
					}
				}
			}

			return "输入项"+ field.getPluginid() +"未找到插件解析";
		} catch (Exception e) {
			e.printStackTrace();
			return "输入项"+field.getPluginid()+"发生错误";
		}		
	}
	
	
	public void onCreate(GoodsField field){
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof IFieldCreateEvent) {
					IFieldCreateEvent fieldPlugin = (IFieldCreateEvent) plugin;
					if(plugin.getId().equals(field.getPluginid()) ){
						fieldPlugin.onCreate(field);
					}
				}  
				
			}
		}
	}
	
	
	public List getPlugins(){
		return this.plugins;
	}
}

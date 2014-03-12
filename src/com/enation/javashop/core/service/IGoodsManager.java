package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.support.GoodsEditDTO;


/**
 * 商品管理接口
 * @author kingapex
 *
 */
public interface IGoodsManager {
	
	
	public static final String plugin_type_berforeadd= "goods_add_berforeadd" ;
	public static final String plugin_type_afteradd= "goods_add_afteradd" ;
	
	
	
	/**
	 * 填充添加前的数据
	 *
	 */
	public  List<String> fillAddInputData();
	
	
	/**
	 * 读取一个商品的详细
	 * @param Goods_id
	 * @return
	 */
	public Map get(Integer goods_id);
	
	public Goods getGoods(Integer goods_id);
	
	/**
	 * 修改时获取数据
	 * @param goods_id
	 * @return
	 */
	public GoodsEditDTO getGoodsEditData(Integer goods_id);
	
	
	
	/**
	 * 添加商品
	 * @param goods
	 */
	public void add(Goods goods);
	
	
	/**
	 * 修改商品
	 * @param goods
	 */
	public void edit(Goods goods);

	public Page searchGoods(Integer catid,String name,String sn,Integer market_enable,Integer[] tagid,String order,int page,int pageSize);
	public Page searchBindGoods(String name,String sn,String order,int page,int pageSize);
	public Page pageTrash(String name,String sn,String order,int page,int pageSize);
	public void delete(Integer[] ids);
	public void  revert(Integer[] ids);
	public void clean(Integer[] ids);
	
	/**
	 * 根据商品id数组读取商品列表
	 * @param ids
	 * @return
	 */
	public List list(Integer[] ids);
	
	/**
	 * 按分类id列表商品
	 * @param catid
	 * @return
	 */
	public List listByCat(Integer catid);
	
	/**
	 * 按标签id列表商品
	 * 如果tagid为空则列表全部
	 * @param tagid
	 * @return
	 */
	public List listByTag(Integer[] tagid);
	
	/**
	 * 不分页、不分类别读取所有有效商品，包含捆绑商品
	 * @return
	 */
	public List<Map> list();
	
	/**
	 * 批量编辑商品
	 */
	public void batchEdit();
	
	
	/**
	 * 商品信息统计
	 * @return
	 */
	public Map census();
	
	
	/**
	 * 更新某个商品的字段值
	 * @param filedname 字段名称
	 * @param value 字段值
	 * @param goodsid 商品id
	 */
	public void updateField(String filedname,Object value,Integer goodsid);
	
	/**
	 * 获取某个商品的位置信息
	 */
	public void getNavdata(Map goods);
}

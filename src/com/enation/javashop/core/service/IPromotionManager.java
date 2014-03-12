package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.ObjectNotFoundException;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.model.support.DiscountPrice;
import com.enation.javashop.core.plugin.promotion.IPromotionPlugin;

/**
 * 促销规则管理接口
 * @author kingapex
 *2010-4-15下午01:36:57
 */
public interface IPromotionManager {
	
	
	/**
	 * 添加促销规则<br>
	 * 同时处理会员级别与规则关联、货品与规则关联<br>
	 *  pmt_basic_type 为插件定义的type
	 * @param promotion 规则实体
	 * @param memberLvIdArray 会员级别 id数组，表示限定在优惠范围的会员级别<br>
	 * 如果此数组不为空则进行会员级别与促销规则关联
	 * @param productIdArray 货品id数组，表示在此范围的货品可享受优惠<br>
	 * 如果此数组不为空，则进行货品与促销规则关联
	 *  @throws IllegalArgumentException 如有下列情况之一：
	 *  <li>promotion为null</li>
	 *   <li>pmt_describe为null</li>
	 *  <li>pmts_id(插件id)为null</li>
	 *  <li>pmt_time_begin为null</li> 
	 *  <li>pmt_time_end为null</li>  
	 * @return 添加成功后的id     
	 */
	public Integer add(Promotion promotion,Integer[] memberLvIdArray,Integer[] goodsIdArray);
	
	/**
	 * @param promotion
	 * @param memberLvIdArray
	 * @param goodsIdArray
	 * @param type, 1:选定商品;2:全场
	 * @return
	 */
	public Integer add(Promotion promotion,Integer[] memberLvIdArray, int type, Integer[] goodsIdArray, Integer[] goodsCatIdArray, Integer[] tagIdArray);

	
	/**
	 * 读取某商品可享受优惠规则列表<br>
	 * @param goodsid 商品id
	 * @param memberLvId 会员级别id
	 * @return 某会员级别，此商品可享受的促销规则
	 */
	public List<Promotion> list(Integer goodsid,Integer memberLvId);
	
	
	
	
	/**
	 * 读取订单可享受的优惠规则列表
	 * @param orderPrice 订单价格
	 * @param memberLvId 会员级别id
	 * @param coupCode
	 * @return 某会员级别，此订单价格可享受的促销规则
	 */		
	public List<Promotion> list(Double orderPrice,Integer memberLvId);
	
	
	/**
	 * 对购物车中的货物应用优惠规则<br>
	 * <li>将货物可享受的优惠规则压入到cartItem.pmtList中</li>
	 * <li>执行优惠方式，如果对货物的价格有影响，则直接计算优惠后赋给cartItem.coupPrice</li>
	 * @param list 购物车扣货物列表 ，如果为空则不做任何处理
	 * @param memberLvId 当前购买人的会员级别
	 */
	public void applyGoodsPmt(List<CartItem>  list,Integer memberLvId);	
	
	
	
	/**
	 * 应用订单优惠规则
	 * @param orderPrice 订单原始价格
	 * @param shipFee 配送费用
	 * @param point 订单可获得的积分
	 * @param memberLvId  会员级别id
	 * @return 优惠价格实体，包含:
	 *  优惠后的订单价格，
	 *  优惠后的配送费用,
	 *  优惠后的积分值
	 *  @see {@link DiscountPrice} 
	 */
	public DiscountPrice  applyOrderPmt(Double orderPrice,Double shipFee,Integer point,Integer memberLvId);
	 
	
	
	
	/**
	 * 应用订单优惠，送出优惠券和赠品
	 * @param orderId
	 * @param memberLvId
	 */
	public void applyOrderPmt(Integer orderId,Double orderPrice,Integer memberLvId);
	
	
	
	
	
	/**
	 * 计算某些促销规则中赠品的列表
	 * @param pmtList 要计算的促销规则列表
	 * @return 规则中送的赠品
	 */
	public List listGift(List<Promotion> pmtList);
	
	
	/**
	 * 列出所有规则插件
	 * @return 插件列表
	 */
	public List listPmtPlugins();
	
	
	
	/**
	 * 获取某个促销插件的详细
	 * @param pluginid 
	 * @return 如果没找到则返回 null
	 */
	public IPromotionPlugin getPlugin(String pluginid);
	
	/**
	 * 读取某促销活动的规则列表
	 * @param activityid 促销活动id
	 * @return 促销规则列表
	 * @throws IllegalArgumentException 如果促销活动id为空
	 */
	public List listByActivityId(Integer activityid);
	
	
	/**
	 * 批量删除规则
	 * @param pmtidArray
	 */
	public void delete(Integer[] pmtidArray);
	
	
	/**
	 * 根据促销规则id读取促销规则
	 * @param pmtid
	 * @return
	 */
	public Promotion  get(Integer pmtid);
	
	
	
	
	/**
	 * 读取某个促销规则关联的会员级别id列表
	 * @param pmtid 促销规则id
	 * @return  关联的会员级别id列表
	 */
	public List<Integer> listMemberLvId(Integer pmtid);
	
	
	
	
	
	
	/**
	 * 读取某个促销规则关联的商品列表
	 * @param pmtid 促销规则id
	 * @return 关联的商品id列表
	 */
	public List listGoodsId(Integer pmtid);
	
	
	
	/**
	 * 修改促销规则<br>
	 * 同时处理会员级别与规则关联、货品与规则关联，将删除之前的关联，重新建立<br>
	 *  pmt_basic_type 为插件定义的type
	 * @param promotion 规则实体
	 * @param memberLvIdArray 会员级别 id数组，表示限定在优惠范围的会员级别<br>
	 * 如果此数组不为空则进行会员级别与促销规则关联
	 * @param productIdArray 货品id数组，表示在此范围的货品可享受优惠<br>
	 * 如果此数组不为空，则进行货品与促销规则关联
	 *  @throws IllegalArgumentException 如有下列情况之一：
	 *  <li>promotion为null</li>
	 *  <li>pmt_id为null</li>
	 *   <li>pmt_describe为null</li>
	 *  <li>pmts_id(插件id)为null</li>
	 *  <li>pmt_time_begin为null</li> 
	 *  <li>pmt_time_end为null</li>  
	 *      
	 */	
	public  void edit(Promotion promotion,Integer[] memberLvIdArray,Integer[] goodsIdArray);
	
	
	
	/**
	 * 会员使用优惠卷
	 * @param code
	 * @param memberId
	 * @throws ObjectNotFoundException 输入的优惠卷号码未找到
	 */
	public void   useCoupon(String code,Integer memberId);
	
	
	
	/**
	 * 读取某订单的优惠方案
	 * @param orderid
	 * @return
	 */
	public List<Map> listOrderPmt(Integer orderid);
}

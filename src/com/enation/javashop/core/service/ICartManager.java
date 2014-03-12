package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.Cart;
import com.enation.javashop.core.model.support.OrderPrice;


/**
 * 购物车业务接口
 * @author kingapex
 * @see com.enation.test.shop.cart.CartTest#testAdd
 *2010-3-23下午03:26:12
 */
public interface ICartManager {
	
	/**
	 * 添加一个购物项
	 * @param cart
	 * 
	 */
	public void add(Cart cart);
	
	
	
	
	/**
	 * 计算购物车中货物总数
	 * @param sessionid
	 * @return
	 */
	public Integer countItemNum(String sessionid);
	
	
	
	/**
	 * 读取某用户的购物车中项列表
	 * @param sessionid
	 * @return
	 */
	public List listGoods(String sessionid);
	
	
	
	/**
	 * 读取购物车中的赠品货物列表
	 * @param sessionid
	 * @return
	 */
	public List listGift(String sessionid);
	
	
	
	/**
	 * 读取购物车中捆绑促销的货物列 表
	 * @param sessionid
	 * @return
	 */
	public List listPgk(String sessionid);
	
	/**
	 * 读取团购列表
	 * @param sessionid
	 * @return
	 */
	public List listGroupBuy(String sessionid);
	
	
	/**
	 * 清空某用户的购物车
	 * @param sessionid
	 */
	public void  clean(String sessionid);
	
	
	public void clean(String sessionid,Integer userid,Integer siteid);
	
	/**
	 * 更新购物数量
	 * @param sessionid
	 * @param cartid
	 */
	public void updateNum(String sessionid,Integer cartid,Integer num);
	
	
	/**
	 * 删除购物车中的一项
	 * @param cartid
	 */
	public void delete(String sessionid,Integer cartid);
	
	/**
	 * 计算购物商品货物总价(原始的，未处理优惠数据的)
	 * @param sessionid
	 * @return
	 */
	public Double countGoodsTotal(String sessionid);
	
	
	/**
	 * 计算捆绑商品总价格
	 * @param sessionid
	 * @return
	 */
	public Double countPgkTotal(String sessionid);
	
	
	/**
	 * 计算团购总价
	 * @param sessionid
	 * @return
	 */
	public Double countGroupBuyTotal(String sessionid);
	
	
	/**
	 * 计算购物真正结算价格<br>
	 * 即应用了商品促销规则的价格后 
	 * @param sessionid
	 * @return
	 */
	public Double  countGoodsDiscountTotal(String sessionid);
	
	
	/**
	 * 计算购买商品重量，包括商品、捆绑商品、赠品
	 * @param sessionid
	 * @return
	 */
	public Double countGoodsWeight(String sessionid);
	
 
	/**
	 * 计算购物车中货物的总积分
	 * @param sessionid
	 * @return
	 */
	public  Integer countPoint(String sessionid);
	
	/**
	 * 计算购物车费用
	 * @param sessionid
	 * @param shippingid
	 * @param regionid
	 * @param isProtected
	 * @return 订单价格
	 */
	public OrderPrice countPrice(String sessionid,Integer shippingid,String regionid,Boolean isProtected );
}

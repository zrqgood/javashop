package com.enation.javashop.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.support.OrderPrice;

/**
 * 订单管理
 * 
 * @author kingapex 2010-4-6上午11:09:53
 */
public interface IOrderManager {
	
	/**
	 * 修改订单价格
	 * @param price
	 * @param orderid
	 */
	public void savePrice(double price,int orderid);
	
	
	
	/**
	 * 创建订单 计算如下业务逻辑：</br> <li>为订单创建唯一的sn(订单号)</li> <li>
	 * 根据sessionid读取购物车计算订商品价格及商品重量，填充以下信息:</br> goods_amount
	 * 商品总额,shipping_amount 配送费用,order_amount 订单总额,weight商品重量,商品数量：goods_num</li>
	 * <li>根据shipping_id(配送方式id)、regionid(收货地区id)及is_protect(是否保价) 计算
	 * protect_price</li> <li>根据payment_id(支付方式id)计算paymoney(支付费用)</li> <li>
	 * 读取当前买家是否为会员或匿名购买并填充member_id字段</li> <li>计算获得积分和消费积分</li>
	 * 
	 * @param order
	 *            订单实体:<br/>
	 *            <li>shipping_id(配送方式id):需要填充用户选择的配送方式id</li> <li>
	 *            regionid(收货地区id)</li> <li>是否保价is_protect</li>
	 *            shipping_area(配送地区):需要填充以下格式数据：北京-北京市-昌平区 </li>
	 * 
	 *            <li>
	 *            payment_id(支付方式id):需要填充为用户选择的支付方式</li>
	 * 
	 *            <li>填充以下收货信息：</br> ship_name(收货人姓名)</br> ship_addr(收货地址)</br>
	 *            ship_zip(收货人邮编)</br> ship_email(收货人邮箱 ) ship_mobile( 收货人手机)
	 *            ship_tel (收货人电话 ) ship_day (送货日期 ) ship_time ( 送货时间 )
	 * 
	 *            </li> <li>remark为买家附言</li>
	 * 
	 * @param sessionid
	 *            会员的sessionid
	 * @throws IllegalStateException 会员尚未登录,不能兑换赠品!           
	 * @return 创建的新订单实体，已经赋予order id
	 */
	public Order add(Order order, String sessionid);
	
	/**
	 * 修改订单信息
	 * @param order
	 */
	public void edit(Order order);

	/**
	 * 分页读取订单列表
	 * 
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页显示数量
	 * @param disabled
	 *            是否读回收站列表(1为读取回收站列表,0为读取正常订单列表)
	 * @param searchkey
	 *            搜索项
	 * @param searchValue
	 *            搜索值
	 * @param order
	 *            排序值
	 * @return 订单分页列表对象
	 */
	public Page list(int page, int pageSize, int disabled, String searchkey,
			String searchValue, String order);

	/**
	 * 根据订单id获取订单详细
	 * 
	 * @param orderId
	 *            订单id
	 * @return 订单实体 对象
	 * @throws ObjectNotFoundException
	 *             当订单不存在时
	 */
	public Order get(Integer orderId);

	
	
	/**
	 * 根据订单号获取订单
	 * @param ordersn
	 * @return
	 */
	public Order get(String ordersn);
	
	
	
	/**
	 * 读取某个订单的商品货物列表
	 * 
	 * @param orderId
	 *            订单id
	 * @return list中为map，对应order_items表
	 */
	public List<Map> listGoodsItems(Integer orderId);

	
	
	/**
	 * 读取赠品货物列表
	 * @param orderId
	 * @return 订单赠品对应表order_gift
	 */
	public List<Map> listGiftItems(Integer orderId);
	
	
	
	/**
	 * 读取某订单的订单日志
	 * 
	 * @param orderId
	 * @return lisgt中为map ，对应order_log表
	 */
	public List listLogs(Integer orderId);

	/**
	 * 批量将某些订单放入回收站<br>
	 * 
	 * @param orderId
	 *            要删除的订单Id数组
	 */
	public void delete(Integer[] orderId);

	/**
	 * 彻底删除某些订单 <br>
	 * 同时删除以下信息： <li>订单购物项</li> <li>订单日志</li> <li>订单支付、退款数据</li> <li>订单发货、退货数据</li>
	 * 
	 * @param orderId
	 *            要删除的订单Id数组
	 */
	public void clean(Integer[] orderId);

	/**
	 * 批量还原某些订单
	 * 
	 * @param orderId
	 */
	public void revert(Integer[] orderId);

	/**
	 * 列表某会员的订单<br/>
	 * lzf add
	 * 
	 * @param member_id
	 * @return
	 */
	public List listOrderByMemberId(int member_id);
	
	/**
	 * 取某一会员的关于订单的统计信息
	 * @param member_id
	 * @return
	 */
	public Map mapOrderByMemberId(int member_id);

	
	
	/**
	 * 读取某订单的配件发货项
	 * @param orderid
	 * @return
	 */
	public List<Map> listAdjItem(Integer orderid);
	
	//已废弃，使用CartManager.countPrice
	//public OrderPrice countPrice(String sessionid,Integer shippingid,String regionid,boolean isProtected );
	
	
	/**
	 * 统计订单状态
	 * @return key为状态值 ，value为此状态订单的数量
	 */
	public Map  censusState();

	/**
	 * 导出订单为excel
	 * @param start 下单日期范围开始
	 * @param end   下单日期范围结束 
	 * @return 返回导出的excel下载路径
	 */
	public String export(Date start,Date end);
}

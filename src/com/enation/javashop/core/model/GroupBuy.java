package com.enation.javashop.core.model;

import java.util.List;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.database.NotDbField;

/**
 * 团购实体
 * @author kingapex
 *
 */
public class GroupBuy {
	private Integer groupid;
	private Double price;
	private Double discount;
	
	private String title;		//	团购标题
	private String descript;	//	说明文字
	private String content;		//	详细说明
	private long start_time;		//	开始时间
	private long end_time;		//	结束时间
	private int goodsid;		//	商品ID
	private int buy_count;		//	购买数量
	private long add_time;
	private int dis_type;
	private String img;			//	团购图片，使用image可返回完整路径
	private int is_index;
	private double oldprice;	//	商品的原始价格，非数据库字段 
	
	private int catid;			//商品所在的类别
	

	private Goods goods;
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	private List<GroupBuyCount> countRuleList; //数量增长规则

	@NotDbField
	public final List<GroupBuyCount> getCountRuleList() {
		return countRuleList;
	}

	public final void setCountRuleList(List<GroupBuyCount> countRuleList) {
		this.countRuleList = countRuleList;
	}
	
	@NotDbField
	public final Goods getGoods() {
		return goods;
	}

	public final void setGoods(Goods goods) {
		this.goods = goods;
	}

	public   Integer getGroupid() {
		return groupid;
	}

	public   void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public  String getTitle() {
		return title;
	}

	public  void setTitle(String title) {
		this.title = title;
	}

	public  String getContent() {
		return content;
	}

	public  void setContent(String content) {
		this.content = content;
	}

	public  long getStart_time() {
		return start_time;
	}

	public  void setStart_time(long startTime) {
		start_time = startTime;
	}

	public  long getEnd_time() {
		return end_time;
	}

	public  void setEnd_time(long endTime) {
		end_time = endTime;
	}

	public  int getGoodsid() {
		return goodsid;
	}

	public  void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public  int getBuy_count() {
		return buy_count;
	}

	public  void setBuy_count(int buyCount) {
		buy_count = buyCount;
	}

	public  long getAdd_time() {
		return add_time;
	}

	public  void setAdd_time(long addTime) {
		add_time = addTime;
	}

	public  Double getPrice() {
		return price;
	}

	public  void setPrice(Double price) {
		this.price = price;
	}

	public  Double getDiscount() {
		return discount;
	}

	public  void setDiscount(Double discount) {
		this.discount = discount;
	}

	public   int getDis_type() {
		return dis_type;
	}

	public   void setDis_type(int disType) {
		dis_type = disType;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@NotDbField
	public String getImage(){
		return UploadUtil.replacePath(img);
		
	}

	public int getIs_index() {
		return is_index;
	}

	public void setIs_index(int isIndex) {
		is_index = isIndex;
	}

	@NotDbField
	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	@NotDbField
	public double getOldprice() {
		return oldprice;
	}

	public void setOldprice(double oldprice) {
		this.oldprice = oldprice;
	}

}

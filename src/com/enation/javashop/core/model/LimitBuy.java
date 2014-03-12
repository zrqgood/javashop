package com.enation.javashop.core.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.database.NotDbField;
import com.enation.framework.util.DateUtil;

/**
 * 限时抢购
 * 
 * @author kingapex
 * 
 */
public class LimitBuy {
	private Integer id;
	private String name;
	private long start_time;
	private long end_time;
	private long add_time;
	private String img;
	private int is_index;
	
	//限时购买商品对照列表，非数据库字段
	private List<LimitBuyGoods> limitBuyGoodsList;
	private List<Map> goodsList;
	
	@NotDbField
	public String getEndTime(){
		
		return DateUtil.toString(new Date( end_time*1000), "yyyy/MM/dd,HH:00:00");
	}
	
	@NotDbField
	public List<Map> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Map> goodsList) {
		this.goodsList = goodsList;
	}

	@NotDbField
	public List<LimitBuyGoods> getLimitBuyGoodsList() {
		return limitBuyGoodsList;
	}

	public void setLimitBuyGoodsList(List<LimitBuyGoods> limitBuyGoodsList) {
		this.limitBuyGoodsList = limitBuyGoodsList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStart_time() {
		return start_time;
	}

	public void setStart_time(long startTime) {
		start_time = startTime;
	}

	public long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(long endTime) {
		end_time = endTime;
	}

	public long getAdd_time() {
		return add_time;
	}

	public void setAdd_time(long addTime) {
		add_time = addTime;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	 //转换路径后的图片路径，非数据库字段
	@NotDbField
	public String getImage() {
		return UploadUtil.replacePath(img);
	}

	public int getIs_index() {
		return is_index;
	}

	public void setIs_index(int isIndex) {
		is_index = isIndex;
	}

 
}

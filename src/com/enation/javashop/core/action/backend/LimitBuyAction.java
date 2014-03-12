package com.enation.javashop.core.action.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.model.LimitBuy;
import com.enation.javashop.core.model.LimitBuyGoods;
import com.enation.javashop.core.service.ILimitBuyManager;

public class LimitBuyAction extends WWAction {
	private ILimitBuyManager limitBuyManager;
	private LimitBuy limitBuy;
	private Integer id;
	private boolean isEdit;
	private Integer[] goodsid;
	private Double[] price;
	private String start_time;
	private String end_time;
	private int start_hour;
	private int end_hour;
	private File imgFile;
	private String imgFileFileName;
	
	
	public String list() {
		this.webpage = this.limitBuyManager.list(this.getPage(), this
				.getPageSize());
		return "list";
	}

	public String add() {
		isEdit = false;
		return "input";
	}

	public String edit() {
		this.isEdit = true;
		this.limitBuy = this.limitBuyManager.get(id);
		 start_hour = new Date( limitBuy.getStart_time()    *1000).getHours();
		 this.end_hour= new Date( limitBuy.getEnd_time() *1000).getHours();
		return "input";
	}
	
	private int getDatelineLong(String date){
		return (int)(DateUtil.toDate(date, "yyyy-MM-dd HH").getTime()/1000);
	}
	

	public static void main(String args[]){
		int date_int  =(int) (DateUtil.toDate("2010-11-11 18", "yyyy-MM-dd HH").getTime()/1000);
		System.out.println("date int :" + date_int);
		long d = ((long)date_int)*1000;
		System.out.println(d);
		System.out.println(new Date(  d   ).getHours());
		
		
	}
	
	public String saveAdd() {
		try {
			
			if(imgFile!=null  && imgFileFileName!=null) {
				String img = UploadUtil.upload(imgFile, imgFileFileName, "goods");
				limitBuy.setImg(img);
				
			}
			
			limitBuy.setStart_time(getDatelineLong(start_time+" "+ start_hour));
			limitBuy.setEnd_time(getDatelineLong(end_time+" "+ end_hour));
			
			limitBuy.setLimitBuyGoodsList(this.createLimitBuyGoods());
			this.limitBuyManager.add(limitBuy);
			this.msgs.add("限时购买添加成功");
			this.urls.put("限时购买列表", "limitBuy!list.do");
		} catch (RuntimeException e) {
			this.logger.error(e.fillInStackTrace());
			this.msgs.add(e.getMessage());
			this.urls.put("返回", "javascript:back();;");
		}
		return this.MESSAGE;
	}

	public String saveEdit() {
		try {
			
			if(imgFile!=null  && imgFileFileName!=null) {
				String img = UploadUtil.upload(imgFile, imgFileFileName, "goods");
				limitBuy.setImg(img);
				
			}
			
			
			limitBuy.setStart_time(getDatelineLong(start_time+" "+ start_hour));
			limitBuy.setEnd_time(getDatelineLong(end_time+" "+ end_hour));
			
			limitBuy.setLimitBuyGoodsList(this.createLimitBuyGoods());
			this.limitBuyManager.edit(limitBuy);
			this.msgs.add("限时购买修改成功");
			this.urls.put("限时购买列表", "limitBuy!list.do");
		} catch (RuntimeException e) {
			this.logger.error(e);
			this.msgs.add(e.getMessage());
			this.urls.put("返回", "javascript:back();;");
		}
		return this.MESSAGE;
	}

	public String delete() {
		this.limitBuyManager.delete(id);
		this.msgs.add("限时购买删除成功");
		this.urls.put("限时购买列表", "limitBuy!list.do");
		return this.MESSAGE;
	}

	private List<LimitBuyGoods> createLimitBuyGoods() {
		if (goodsid == null)
			throw new RuntimeException("必须选择一个或更多商品");
		if (price == null)
			throw new RuntimeException("必须选择一个或更多商品");
		if (goodsid.length != price.length)
			throw new RuntimeException("商品价格不正确");

		List<LimitBuyGoods> goodsList = new ArrayList<LimitBuyGoods>();
		for (int i = 0; i < goodsid.length; i++) {
			LimitBuyGoods buyGoods = new LimitBuyGoods();
			buyGoods.setGoodsid(goodsid[i]);
			buyGoods.setPrice(price[i]);
			goodsList.add(buyGoods);
		}

		return goodsList;
	}

	public ILimitBuyManager getLimitBuyManager() {
		return limitBuyManager;
	}

	public void setLimitBuyManager(ILimitBuyManager limitBuyManager) {
		this.limitBuyManager = limitBuyManager;
	}

	public LimitBuy getLimitBuy() {
		return limitBuy;
	}

	public void setLimitBuy(LimitBuy limitBuy) {
		this.limitBuy = limitBuy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Integer[] getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer[] goodsid) {
		this.goodsid = goodsid;
	}

	public Double[] getPrice() {
		return price;
	}

	public void setPrice(Double[] price) {
		this.price = price;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String startTime) {
		start_time = startTime;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public int getStart_hour() {
		return start_hour;
	}

	public void setStart_hour(int startHour) {
		start_hour = startHour;
	}

	public int getEnd_hour() {
		return end_hour;
	}

	public void setEnd_hour(int endHour) {
		end_hour = endHour;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

}

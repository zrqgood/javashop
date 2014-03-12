package com.enation.javashop.widget.delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.ip.IPSeeker;
import com.enation.javashop.core.model.DlyType;
import com.enation.javashop.core.model.Regions;
import com.enation.javashop.core.service.IDlyTypeManager;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 配送地区价格挂件
 * @author kingapex
 *
 */
public class DeliveryPriceWidget extends AbstractWidget {

	private IRegionsManager regionsManager; 
	private IDlyTypeManager dlyTypeManager;
	private IGoodsManager goodsManager;
	@Override
	protected void config(Map<String, String> params) {

	}
	
	@Override
	protected void display(Map<String, String> params) {
		if("getPriceJson".equals(action)){
			HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
			Integer goodsid  = RequestUtil.getIntegerValue(request, "goodsid");
			Integer regionid = RequestUtil.getIntegerValue(request, "regionid");
			this.getPriceJson(regionid, goodsid);
		}else{
			this.showAreaPrice();
		}
		
	}
	
 
	
	/**
	 * 显示当前区域各种配送方式的价格
	 */
	private void showAreaPrice(){
		
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String ip  = request.getRemoteAddr();
		String area  = this.getIpArea(ip);
		
		if(this.logger.isDebugEnabled()){
			this.logger.debug(" area is " + area);
		}
		
		Regions region = this.regionsManager.getByName(area);
		
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		if(goods==null) throw new RuntimeException("参数显示挂件必须和商品详细显示挂件同时存在");
		
		List priceList  =null;
		
		if(region == null){
			priceList = this.getPriceList(goods,1);
		}else{
			priceList = this.getPriceList(goods, region.getRegion_id());
		}
		
		List provinceList= regionsManager.listProvince();
		
		this.putData("provinceList", provinceList);
		this.putData("priceList", priceList);
		this.putData("area", area);
	}
	
	
	/**
	 * 获取配置方式价格list
	 * @param goods
	 * @param regionid
	 * @return
	 */
	private List<Map> getPriceList(Map goods,Integer regionid){
		List<DlyType> dlytypeList  = dlyTypeManager.list();  
		List priceList  = new ArrayList();
		for(DlyType type  : dlytypeList){
			Integer typeid  = type.getType_id();
			Double goodsprice  =Double.valueOf( ""+goods.get("price") );
			double weight  = Double.valueOf( ""+goods.get("weight") );
			Double[] price= dlyTypeManager.countPrice(typeid, weight, goodsprice, ""+regionid, false);
			Map map  = new HashMap(2);
			map.put("name", type.getName());
			map.put("price", price[0]);
			priceList.add(map);
		}
		return priceList;
		
	}
	
	/**
	 * 根据ip地址获取地区
	 * @param ip
	 * @return
	 */
	private String getIpArea(String ip){
		if(ip.equals("127.0.0.1")) return "北京市"; 
			
	   	String country = new IPSeeker().getCountry(ip);
	   	int end = country.indexOf("省");
	   	if(end == -1){
	   		end = country.indexOf("市");	
	   	}
	   	if(end!=-1)
	   		country = country.substring(0,  end+1 );
	   	
	   	return country;		 
	}
	

	/**
	 * 显示价格json字串
	 * @param regionid
	 * @param goodsid
	 */
	private void getPriceJson(Integer regionid,Integer goodsid){
		Map goods = goodsManager.get(goodsid);
		List<Map> priceList  = this.getPriceList(goods, regionid);
		String json = JSONArray.fromObject(priceList).toString();
		this.showJson(json);
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

	public IDlyTypeManager getDlyTypeManager() {
		return dlyTypeManager;
	}

	public void setDlyTypeManager(IDlyTypeManager dlyTypeManager) {
		this.dlyTypeManager = dlyTypeManager;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

}

package com.enation.javashop.core.action.facade;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.utils.GoodsUtils;

/**
 * 商品相册action
 * 
 * @author kingapex 2010-4-27下午04:03:14
 */
public class GoodsAlbumAction extends WWAction {
	private Integer goodsid;
	private IGoodsManager goodsManager;
	private Goods goods;
	private StringBuffer albumXml;
	public String execute() {
		
		goods = goodsManager.getGoods(goodsid);
		
		String image_file = goods.getImage_file()==null?"":goods.getImage_file().toString();
		 albumXml =new StringBuffer("<products name='"+goods.getName()+"' shopname='Javashop'>");
		if(image_file!=null && !image_file.equals("")){
			
			image_file = UploadUtil.replacePath(image_file);
			String[] imgs= image_file.split(",");
		
			int i=0;
			for(String img : imgs){
				albumXml.append("<smallpic");
				if(i==0)
					albumXml.append(" selected='selected'");
				albumXml.append(">");
				albumXml.append(UploadUtil.getThumbPath(img, "_small"));
				albumXml.append("</smallpic>");
				
				
				albumXml.append("<bigpic ");
				if(i==0)
					albumXml.append(" selected='selected'");
				albumXml.append(">");
				albumXml.append(UploadUtil.getThumbPath(img, "_big")  +"</bigpic>");
				//albumXml.append("<link>goods-"+goods.getGoods_id()+".html</link>");
				i++;
			}
			
		}
		albumXml.append("</products>");
		
		 
		return this.SUCCESS;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public StringBuffer getAlbumXml() {
		return albumXml;
	}

	public void setAlbumXml(StringBuffer albumXml) {
		this.albumXml = albumXml;
	}

}

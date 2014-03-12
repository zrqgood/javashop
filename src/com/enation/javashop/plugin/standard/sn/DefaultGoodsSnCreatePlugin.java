package com.enation.javashop.plugin.standard.sn;

import java.util.Date;
import java.util.Map;

import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.plugin.goods.AbstractGoodsSnCreator;

/**
 * 默认商品货号生成插件
 * 
 * @author enation
 * 
 */
/**
 * @author kingapex
 * 2010-6-3下午02:52:56
 */
public class DefaultGoodsSnCreatePlugin extends AbstractGoodsSnCreator {

	private IDaoSupport baseDaoSupport;
	
 


	
	public IDaoSupport getBaseDaoSupport() {
		return baseDaoSupport;
	}


	public void setBaseDaoSupport(IDaoSupport baseDaoSupport) {
		this.baseDaoSupport = baseDaoSupport;
	}


	public void register() {
	 
		
	}


	/**
	 * 由系统时间得到一long数定生成货号
	 */
	
	public String createSn(Map goods) {

		if (goods.get("sn") != null && !goods.get("sn").equals("")) {
			
			if(goods.get("goods_id") == null){ //添加
			
				if(   checkSn( goods.get("sn").toString() ) ==1 ){
					throw new RuntimeException("商品货号" +goods.get("sn") +"已存在" );
				}
				
				
			}else{
				
					try{
						Integer goods_id  = Integer.valueOf( "" + goods.get("goods_id"));
						
						if(   checkSn( goods.get("sn").toString(),goods_id ) ==1 ){
							throw new RuntimeException("商品货号" +goods.get("sn") +"已存在" );
						}
						
					}
					catch(NumberFormatException e){
						throw new RuntimeException("商品id格式错误");
					}
				
			}
					
			return goods.get("sn").toString();
		 
		}
		
			
		String sn = "G" + DateUtil.toString( new Date(System.currentTimeMillis()) ,"yyyyMMddhhmmss" )+StringUtil.getRandStr(4);
		return sn;
	}

	
	/**
	 * 检测货号是否已经存在
	 * 
	 * @param sn
	 * @return 如果存在返回1 ，如果不存在返回0
	 */
	private int checkSn(String sn) {
		String sql = "select count(0) num from goods where sn='" + sn + "'";
		int count = this.baseDaoSupport.queryForInt(sql);
		count = count > 0 ? 1 : 0;
		return count;
	}

	/**
	 * 用于修改时检测
	 * 
	 * @param sn
	 * @param goods_id
	 * @return
	 */
	private int checkSn(String sn, int goods_id) {
		String sql = "select count(0) num from goods where sn='" + sn
				+ "' and goods_id!=" + goods_id;
		int count = this.baseDaoSupport.queryForInt(sql);
		count = count > 0 ? 1 : 0;
		return count;
	}

	public String getAuthor() {
		return "kingapex";
	}

	public String getId() {
		return "goods.sn_creator";
	}

	public String getName() {

		return "默认商品货号生成插件";
	}

	public String getType() {
		return "";
	}

	public String getVersion() {
		return "1.0";
	}

	public void perform(Object... params) {

	}

}

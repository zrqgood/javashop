/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.javashop.widget.goods.search;

import static com.enation.javashop.core.utils.UrlUtils.getParamStringValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.IFavoriteManager;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsSearchManager;
import com.enation.javashop.core.utils.UrlUtils;

/**
 * 商品搜索挂件
 * @author kingapex
 *2010-4-26上午10:53:49
 */
public class GoodsSearchWidget extends AbstractWidget {
	
	private IGoodsSearchManager goodsSearchManager;
	
	private IGoodsCatManager goodsCatManager;
	
	private IFavoriteManager favoriteManager ;

	private int cat_id;
	
	private String tagids;	//lzf add

	private String distype;

	private String order;

	private String brandStr;

	private String propStr; //商品自定义属性str
	private String attrStr; //商品属性str

	private String keyword;

	private String minPrice;

	private String maxPrice;

	int page=1;
	private List<Attribute> propList; // 属性过滤器

	private List brandList; // 品牌过滤器

	private List catList; // 分类

	
	
	
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> wparams) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		Nav nav = new Nav();
		nav.setTitle("首页");
		nav.setLink("index.html");
		nav.setTips("首页");
		this.putNav(nav);
		
		Nav nav1 = new Nav();
		nav1.setTitle("商品列表");
		nav1.setTips("商品列表");
		this.putNav(nav1);
		
		
		String uri = request.getServletPath();
		String p= UrlUtils.getParamStr(uri);
		
		
		initParam(p);
		
//		if (keyword != null)
//			keyword = StringUtil.toUTF8(keyword);

		distype = distype == null || distype.equals("") ? distype = "list"
				: distype;

		String cat_path = null;
	    int type_id=0;
		// 如果按类别了
		if (cat_id != 0) {
				
			Cat cat=  goodsCatManager.getById(cat_id);
			cat_path = cat.getCat_path();
			  type_id =cat.getType_id();
	


			// 读取这个类型的属性信息，如果为递近式搜索的话要计算商品数量数量。
			// 属性信息包括品牌及属性
			List[] props = goodsSearchManager.getPropListByCat(type_id, cat_path,
					brandStr, propStr,attrStr);
			propList = props[0];
			brandList = props[1];
			
		
			int att_index=0;
			for(Attribute attr: propList){
				Map[] opations = attr.getOptionMap();
				int j=0;
				for(Map op:opations){
					//首先构造一下除了属性以外的串
					op.put("url",  att_index+"_"+j);
					j++;
				}
				String value  = this.getProValue(att_index, propStr);
				attr.setValue(value);
				att_index++;
			}
			
			List catList  = this.goodsCatManager.listChildren(cat_id);
			catList=catList.size()==0|| catList.isEmpty()? catList=null:catList;
			this.putData("catList", catList);
			this.putData("propList", propList);
			this.putData("brandList", brandList);
			this.putData("cat", cat);
		}
		
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("cat_path", cat_path);
		params.put("order", order);
		params.put("brandStr", brandStr);
		params.put("propStr", propStr);
		params.put("keyword", keyword);
		params.put("minPrice", minPrice);
		params.put("maxPrice", maxPrice);
		params.put("tagids", tagids);
		params.put("attrStr", this.attrStr);
		params.put("typeid", ""+type_id);
		String pagesizes = wparams.get("pagesize");
		Integer pageSize = pagesizes == null ? 20 : Integer.valueOf(pagesizes);
		
		Page webpage = this.goodsSearchManager.search(page, pageSize, params);
		
		Member member  = UserServiceFactory.getUserService().getCurrentMember();
		if(member!=null){
			this.putData("favoriteList", 	favoriteManager.list() );
		}
		
		//设置页面变量
		this.putData("uri", uri);
		this.putData("webpage", webpage);
		this.putData("brandStr", brandStr);
		this.putData("order", order);
		this.putData("distype", distype);
		this.putData("pageno", page); //当分页页码
		this.putData("pagesize", pageSize); //分页大小
		this.putData("total", webpage.getTotalCount());
		this.putData("GoodsPic",new  GoodsPicDirectiveModel());
		this.putData("pager", new SearchPagerDirectiveModel());
		this.putData("searchUrl", new SearchUrlDirectiveModel());
		this.putData("catid", cat_id);
//		this.setPageFolder("/widgets/search");
		
	}
	
	/**
	 * 获取属性值
	 * 如有字串 0_1,2_测试
	 * index=1 则返回测试
	 * @param index 属性位置（第几个属性）
	 * @param proStr 地址栏上的属性字串
	 * @return 这个属性的值
	 */
	private String getProValue(int index ,String proStr){
		if(!StringUtil.isEmpty(proStr)){
			String[] proar =  proStr.split(",");

				for(int i=0;i<proar.length;i++){
					String str  =proar[i];
					String[] ar=str.split("_");
					if(ar!=null && ar.length==2){
						if(ar[0].equals(""+index)){
							return ar[1];
						}
					}
				}
				
		}
		return  "";
	}
	

 
	private void initParam(String p){
		String cat_str = getParamStringValue(p, "cat");
		
		if(cat_str!=null){
			this.cat_id = Integer.valueOf(cat_str);
			 
		}
		
		
		
		String page_str = UrlUtils.getParamStringValue(p, "page");
		if(page_str!=null && !page_str.equals("")){
			page= Integer.valueOf(page_str);
		}
		
		distype= getParamStringValue(p, "distype");
		distype=distype==null?distype="index":distype;
		order= getParamStringValue(p, "order");
		propStr= getParamStringValue(p, "prop");
		attrStr= getParamStringValue(p, "attr");
		HttpServletRequest request  =ThreadContextHolder.getHttpRequest(); 
		String encode = request.getCharacterEncoding();
		if(!"UTF-8".equals(encode)){ //如果非UTF-8编码则进行编码
			propStr=StringUtil.toUTF8(propStr);
		}
		
		keyword= getParamStringValue(p, "keyword"); 
		brandStr=getParamStringValue(p, "brand");
		minPrice=UrlUtils.getParamStringValue(p, "minprice");
		maxPrice =UrlUtils.getParamStringValue(p, "maxprice");
		tagids = getParamStringValue(p, "tag");
		 
//		
//		//生成无属性的字串
//		this.searchUrl = UrlUtils.getExParamUrl(p, "prop");
		
	}
	
	
	public IGoodsSearchManager getGoodsSearchManager() {
		return goodsSearchManager;
	}

	public void setGoodsSearchManager(IGoodsSearchManager goodsSearchManager) {
		this.goodsSearchManager = goodsSearchManager;
	}

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	public IFavoriteManager getFavoriteManager() {
		return favoriteManager;
	}

	public void setFavoriteManager(IFavoriteManager favoriteManager) {
		this.favoriteManager = favoriteManager;
	}
	
	
}

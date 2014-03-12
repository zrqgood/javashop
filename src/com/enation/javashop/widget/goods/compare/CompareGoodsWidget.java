package com.enation.javashop.widget.goods.compare;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.HttpUtil;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.GoodsParam;
import com.enation.javashop.core.model.support.GoodsView;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.core.utils.GoodsUtils;

/**
 * 对比商品挂件
 * @author kingapex
 *2010-4-28下午01:47:47
 */
public class CompareGoodsWidget extends AbstractWidget {
	 private IGoodsTypeManager goodsTypeManager;
	 
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		 
		String goodsStr = HttpUtil.getCookieValue(ThreadContextHolder.getHttpRequest(),"cmpgoods"); 
		
 		try {
			if(goodsStr!=null){
				goodsStr = URLDecoder.decode(goodsStr,"UTF-8");
				String[] goodsArray = goodsStr.split(",");
				String goodsidstr ="";
				for(String one:goodsArray){
					if(!goodsidstr.equals("")){
						goodsidstr+=",";
					}
					goodsidstr+=one.split("\\|")[0];
				}
				List goodsList  = this.getDiffGoodsList(goodsidstr);
				if(goodsList!=null){
					int goodsNum= goodsList.size();
					int width = 80/goodsNum;
					this.putData("width", width);
					this.putData("goodsNum", goodsNum);
				}
				
				ParamGroup[] paramGroups=   fillDiffParam(goodsList);
				List propList =  fillDiffProps(goodsList);
				
				this.putData("goodsList", goodsList);
				this.putData("propList", propList);
				this.putData("paramGroups", paramGroups);
				this.putData("GoodsPic",new  GoodsPicDirectiveModel());
//				this.setPageFolder("/widgets/compare");
			}
			
 		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
 		}		
	}

	
	private List getDiffGoodsList(String goods_ids) {
		String sql = "select * from goods where goods_id in(" + goods_ids + ")";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {

				GoodsView goods = new GoodsView();
				goods.setName(rs.getString("name"));
				goods.setSn(rs.getString("sn"));
				goods.setWeight(rs.getDouble("weight"));
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setImage_default(rs.getString("image_default"));
				goods.setImage_file(rs.getString("image_file"));
				goods.setMktprice(rs.getDouble("mktprice"));
				goods.setPrice(rs.getDouble("price"));
				goods.setCreate_time(rs.getLong("create_time"));
				goods.setLast_modify(rs.getLong("last_modify"));
				goods.setParams(rs.getString("params"));
				goods.setIntro(rs.getString("intro"));
				goods.setBrief(rs.getString("brief"));
				goods.setSpecs(rs.getString("specs"));
				List specList = GoodsUtils.getSpecList(goods.getSpecs());
				goods.setSpecList(specList);
				goods.setType_id(rs.getInt("type_id"));
				goods.setAdjuncts(rs.getString("adjuncts"));
				goods.setStore(rs.getInt("store"));

				Map propMap = new HashMap();

				for (int i = 0; i < 20; i++) {
					String value = rs.getString("p" + (i + 1));
					propMap.put("p" + i, value);
				}
				goods.setPropMap(propMap);
				return goods;
			}
		};
		List list = this.baseDaoSupport.queryForList(sql, mapper);
		return list;
	}
	
	public ParamGroup[] converFormString(String params) {

		if (params == null || params.equals("") || params.equals("[]"))
			return null;
		Map classMap = new HashMap();

		classMap.put("paramList", GoodsParam.class);
		JSONArray jsonArray = JSONArray.fromObject(params);

		Object obj = JSONArray.toArray(jsonArray, ParamGroup.class, classMap);

		if (obj == null)
			return null;

		return (ParamGroup[]) obj;
	}
	
	/**
	 * 用于商品比较时过滤
	 * 
	 * @param goodsList
	 * @return
	 */
	public ParamGroup[] fillDiffParam(List goodsList) {
		ParamGroup[] grou_params = null;

		for (int i = 0; i < goodsList.size(); i++) {
			Goods goods = (Goods) goodsList.get(i);
			String paramString = goods.getParams();
			ParamGroup[] temp_params = converFormString(paramString);
			if(temp_params== null) continue;
			
			// 用第一个参数做基准
			if (i == 0) {
				grou_params = temp_params;
			}

			addValueToParams(temp_params, grou_params);

		}

		return grou_params;
	}


	private void addValueToParams(ParamGroup[] temp_params,
			ParamGroup[] grou_params) {

		for (int i = 0; i < grou_params.length; i++) {

			ParamGroup pg = grou_params[i];
			ParamGroup temp_pg = temp_params[i];

			List<GoodsParam> paramList = pg.getParamList();
			List<GoodsParam> temp_paramList = temp_pg.getParamList();

			// 循环基准的参数列表
			for (int j = 0; j < paramList.size(); j++) {
				GoodsParam goodsParam = paramList.get(j);
				GoodsParam temp_goodsParam = temp_paramList.get(j);
				goodsParam.addValue(temp_goodsParam.getValue()); // 将这个要新对比的商品的这个参数值压到参数值列表中
			}

		}

	}

	/**
	 * 计算一组要对比的商品的属性，将同类的属性值放入到一个属性列表中。
	 * 
	 * @param goodsList
	 * @return
	 */
	public List fillDiffProps(List<GoodsView> goodsList) {
		if (goodsList == null)
			return null;

		// 读取这种类型的属性信息列表
		List propList = this.goodsTypeManager.getAttrListByTypeId(goodsList.get(0).getType_id());

		for (int i = 0; i < goodsList.size(); i++) {
			GoodsView goods = goodsList.get(i);
			addValueToProps(goods, propList);
		}

		return propList;
	}

	
 

/**
 * 将一个商品的属性值加入对比列表
 * 
 * @param goods
 * @param propList
 */
private void addValueToProps(GoodsView goods, List propList) {
	Map<String, String> propMap = goods.getPropMap();
	for (int i = 0; i < propList.size(); i++) {
		Attribute attribute = (Attribute) propList.get(i);
		String value = propMap.get("p" + i);
		attribute.setValue(value);
		attribute.addValue(attribute.getValStr());
	}

}

public IGoodsTypeManager getGoodsTypeManager() {
	return goodsTypeManager;
}

public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
	this.goodsTypeManager = goodsTypeManager;
}	
	
	
}

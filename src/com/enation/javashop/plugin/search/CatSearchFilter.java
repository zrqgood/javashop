package com.enation.javashop.plugin.search;

import java.util.List;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.SearchSelector;
import com.enation.javashop.core.service.IGoodsCatManager;

/**
 * 商品分类搜索过虑器
 * @author kingapex
 *
 */
public class CatSearchFilter extends AutoRegisterPlugin implements
		IGoodsSearchFilter {
	
	private IDBRouter baseDBRouter; 

	public List<SearchSelector> createSelectorList(Cat cat, String url,
			String urlFragment) {
		return null;
	}

	public void filter(StringBuffer sql,Cat cat, String urlFragment) {
		FreeMarkerPaser.getInstance().putData("cat",cat);
		if(! StringUtil.isEmpty(urlFragment) ){
			String cat_path  = cat.getCat_path();
			if (cat_path != null) {
				sql.append( " and  g.cat_id in(" ) ;
				sql.append("select c.cat_id from " + baseDBRouter.getTableName("goods_cat"));
				sql.append(" c where c.cat_path like '" + cat_path + "%')");
			}
		}
	}

	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}

	public String getFilterId() {		
		return "cat";
	}

	
	public String getAuthor() {
		return "kingapex";
	}

	public String getId() {		
		return "catSearchFilter";
	}
	
	public String getName() {	
		return "商品分类筛选器";
	}

	public String getType() {
		return "searchFilter";
	}

	public String getVersion() {
		return "1.0";
	}
	
	public void perform(Object... params) {		

	}	
	
	public void register() {

	}
}

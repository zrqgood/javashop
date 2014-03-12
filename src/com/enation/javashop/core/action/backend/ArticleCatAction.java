package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.ArticleCat;
import com.enation.javashop.core.service.IArticleCatManager;
import com.enation.javashop.core.service.impl.ArticleCatRuntimeException;

/**
 * 文章分类action
 * @author apexking
 *
 */
public class ArticleCatAction extends WWAction {
	
	private IArticleCatManager articleCatManager;
	private List catList;
	private ArticleCat cat;
	private int cat_id;
	private int[] cat_ids; //分类id 数组,用于保存排序
	private int[] cat_sorts; //分类排序值
	
	//显示列表
	public String cat_list(){
		catList = articleCatManager.listChildById(0);
		return "cat_list";
	}
	
	
	//到添加页面
	public String add(){
		catList = articleCatManager.listChildById(0);
		return "cat_add";
	}
	
	
	//保存编辑
	public String edit(){
		catList = articleCatManager.listChildById(0);
		cat = articleCatManager.getById(cat_id);
		return "cat_edit";
	}
	
	
	//保存添加
	public String saveAdd(){
		try{
		   articleCatManager.saveAdd(cat);
		}catch(ArticleCatRuntimeException ex){
		   this.msgs.add("同级文章栏目不能同名");
		   this.urls.put("分类列表", "articleCat!cat_list.do");
		   return this.MESSAGE; 
		}
		this.msgs.add("文章栏目添加成功");
		this.urls.put("分类列表", "articleCat!cat_list.do");
		return this.MESSAGE; 
	}

	
	
	//保存修改 
	public String saveEdit(){
		try{
		   this.articleCatManager.update(cat);
		}catch(ArticleCatRuntimeException ex){
			   this.msgs.add("同级文章栏目不能同名");
			   this.urls.put("分类列表", "articleCat!cat_list.do");
			   return this.MESSAGE; 
		}
		this.msgs.add("文章栏目修改成功");
		this.urls.put("分类列表", "articleCat!cat_list.do");
		return this.MESSAGE;
	}
	
	
	//删除
	public String delete(){
		
		int r  = this.articleCatManager.delete(cat_id);
		
		if(r==0){
			json= "{'result':0,'message':'删除成功'}";
		}else if(r==1){
			json= "{'result':1,'message':'此类别下存在子类别或者文章不能删除!'}";
		}		
				
		return this.JSON_MESSAGE;
	}
	
	
	public String saveSort(){
		this.articleCatManager.saveSort(cat_ids, cat_sorts);
		json= "{'result':0,'message':'保存成功'}";
		return this.JSON_MESSAGE;
	}
	
	
	public List getCatList() {
		return catList;
	}


	public void setCatList(List catList) {
		this.catList = catList;
	}


 
	public ArticleCat getCat() {
		return cat;
	}


	public void setCat(ArticleCat cat) {
		this.cat = cat;
	}



	public IArticleCatManager getArticleCatManager() {
		return articleCatManager;
	}


	public void setArticleCatManager(IArticleCatManager articleCatManager) {
		this.articleCatManager = articleCatManager;
	}


	public int getCat_id() {
		return cat_id;
	}


	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}


	public int[] getCat_ids() {
		return cat_ids;
	}


	public void setCat_ids(int[] cat_ids) {
		this.cat_ids = cat_ids;
	}


	public int[] getCat_sorts() {
		return cat_sorts;
	}


	public void setCat_sorts(int[] cat_sorts) {
		this.cat_sorts = cat_sorts;
	}


	
	
	
	
}

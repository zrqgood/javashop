package com.enation.javashop.core.action.backend;

import java.io.File;
import java.util.List;

import com.enation.eop.sdk.database.PermssionRuntimeException;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.framework.database.DBRuntimeException;
import com.enation.framework.util.FileUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.field.GoodsField;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsFieldManager;
import com.enation.javashop.core.service.IGoodsTypeManager;

/**
 * 商品分类action
 * @author apexking
 *
 */
public class CatAction extends WWAction {
	
	private IGoodsCatManager goodsCatManager;
	private IGoodsTypeManager goodsTypeManager;
	
	protected List catList;
	private List typeList;
	private Cat cat;
	private File image;
	private String imageFileName;
	protected int cat_id;
	private int[] cat_ids; //分类id 数组,用于保存排序
	private int[] cat_sorts; //分类排序值
	
	
	//检测类别是否重名
	public String checkname(){
		if( this.goodsCatManager.checkname(cat.getName(),cat.getCat_id() )){
			this.json="{result:1}";
		}else{
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}
	
	//显示列表
	public String list(){
		catList = goodsCatManager.listAllChildren(0);
		return "cat_list";
	}

	
	//到添加页面
	public String add(){
		typeList = goodsTypeManager.listAll();
		catList = goodsCatManager.listAllChildren(0);
		
		return "cat_add";
	}
	
	
	//编辑
	public String edit(){
		try{
			typeList = goodsTypeManager.listAll();
			catList = goodsCatManager.listAllChildren(0);
			cat = goodsCatManager.getById(cat_id);
		return "cat_edit";
		}catch(DBRuntimeException ex){
			this.msgs.add("您查询的商品不存在");
			return this.MESSAGE; 
		}
	}
	
	
	//保存添加
	public String saveAdd(){
		if (image != null) {
			if (FileUtil.isAllowUp(imageFileName)) {
				cat.setImage( UploadUtil.upload(image,imageFileName,"goodscat") );
				
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		cat.setGoods_count(0);
		goodsCatManager.saveAdd(cat);
		
		this.msgs.add("商品分类添加成功");
		this.urls.put("分类列表", "cat!list.do");
		return this.MESSAGE; 
	}

	
	
	//保存修改 
	public String saveEdit(){
		if (image != null) {
			if (FileUtil.isAllowUp(imageFileName)) {
				cat.setImage( UploadUtil.upload(image,imageFileName,"goodscat") );
				
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		try{
			if(cat.getParent_id().intValue()==0){
				this.goodsCatManager.update(cat);
				this.msgs.add("商品分类修改成功");
				this.urls.put("分类列表", "cat!list.do");
				return this.MESSAGE;
			}
			Cat targetCat = goodsCatManager.getById(cat.getParent_id());//将要修改为父分类的对象
			if(cat.getParent_id().intValue()==cat.getCat_id().intValue() || targetCat.getParent_id().intValue()==cat.getCat_id().intValue()){
				this.msgs.add("保存失败：上级分类不能选择当前分类或其子分类");
				this.urls.put("分类列表", "cat!list.do");
				return this.MESSAGE; 
			}else{
				this.goodsCatManager.update(cat);
				this.msgs.add("商品分类修改成功");
				this.urls.put("分类列表", "cat!list.do");
				return this.MESSAGE;
			}
		}catch(PermssionRuntimeException ex){
			this.msgs.add("非法操作");
			return this.MESSAGE; 
		}
		
	}
	
	
	
	//删除
	public String delete() {

		try {
			int r = this.goodsCatManager.delete(cat_id);

			if (r == 0) {
				json = "{'result':0,'message':'删除成功'}";
			} else if (r == 1) {
				json = "{'result':1,'message':'此类别下存在子类别不能删除!'}";
			} else if (r ==2) {
				json = "{'result':1,'message':'此类别下存在商品不能删除!'}";
			}
		} catch (PermssionRuntimeException ex) {
			json = "{'result':1,'message':'非法操作!'}";
			return JSON_MESSAGE;
		}
		return this.JSON_MESSAGE;
	}

 
	
	public String saveSort(){
		this.goodsCatManager.saveSort(cat_ids, cat_sorts);
		json= "{'result':0,'message':'保存成功'}";
		return this.JSON_MESSAGE;
	}
	
 
	
	public List getCatList() {
		return catList;
	}


	public void setCatList(List catList) {
		this.catList = catList;
	}


	public Cat getCat() {
		return cat;
	}


	public void setCat(Cat cat) {
		this.cat = cat;
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

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}


	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}



	public List getTypeList() {
		return typeList;
	}

 

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}



	public File getImage() {
		return image;
	}



	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	 
	
	
}

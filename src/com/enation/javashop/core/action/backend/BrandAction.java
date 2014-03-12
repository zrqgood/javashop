package com.enation.javashop.core.action.backend;

import java.io.File;

import com.enation.framework.action.WWAction;
import com.enation.framework.util.FileUtil;
import com.enation.javashop.core.model.Brand;
import com.enation.javashop.core.service.IBrandManager;


/**
 * 品牌action 负责品牌的添加和修改
 * 
 * @author apexking
 * 
 */
public class BrandAction extends WWAction {

	private IBrandManager brandManager;

	private Brand brand;

	private File logo;

	private String logoFileName;

	private String oldlogo;

	private String filePath;

	private String order;

	private Integer brand_id; // 读取详细时使用

	private String id; // 批量删除时用
	
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String checkUsed(){
		 if(this.brandManager.checkUsed(id) ){
			 this.json="{result:1}"; 
		 }else{
			 this.json="{result:0}";
		 }
		 return this.JSON_MESSAGE;
	}

	 public String checkname(){
		 if(this.brandManager.checkname(brand.getName(),brand.getBrand_id())){
			 this.json="{result:1}"; //存在返回1
		 }else{
			 this.json="{result:0}";
		 }
		 return this.JSON_MESSAGE;
	 }
	public String add() {

		return "add";
	}
	
	public String edit(){
		brand = this.brandManager.get(brand_id);
		return "edit";
	}
	
	// 后台品牌列表
	public String list() {
		
		this.webpage = brandManager.list(order, this.getPage(), this.getPageSize());
		return "list";
	}

	// 后台品牌回收站列表
	public String trash_list() {
	
		this.webpage = this.brandManager.listTrash(order, this.getPage(),this.getPageSize());
		return "trash_list";
	}

	
	
	public String save() {
		if (logo != null) {
			if (FileUtil.isAllowUp(logoFileName)) {
		
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		brand.setDisabled(0);
		brand.setFile(this.logo);
		brand.setFileFileName(this.logoFileName);
		brandManager.add(brand);
		this.msgs.add("品牌添加成功");
		this.urls.put("品牌列表", "brand!list.do");
		return this.MESSAGE;
	}

	//	
	public String saveEdit() {
		if (logo != null) {
			if (FileUtil.isAllowUp(logoFileName)) {


			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		
		brand.setFile(this.logo);
		brand.setFileFileName(this.logoFileName);
		brandManager.update(brand);
		this.msgs.add("品牌修改成功");
		this.urls.put("品牌列表", "brand!list.do");
		return this.MESSAGE;
	}

	/**
	 * 将品牌放入回收站
	 * 
	 * @return
	 */
	public String delete() {
		try {
			this.brandManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 将品牌从回收站中还原
	 * 
	 * @return
	 */
	public String revert() {
		try {
			brandManager.revert(id);
			this.json = "{'result':0,'message':'还原成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 清空回收站中的品牌
	 * 
	 * @return
	 */
	public String clean() {
		try{
			brandManager.clean(id);
			this.json = "{'result':0,'message':'删除成功'}";
		}catch(RuntimeException e){
			 this.json="{'result':1,'message':'删除失败'}";
		 }
		return this.JSON_MESSAGE;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}


	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public String getOldlogo() {
		return oldlogo;
	}

	public void setOldlogo(String oldlogo) {
		this.oldlogo = oldlogo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

 
	

}

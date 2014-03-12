package com.enation.javashop.core.action.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.model.GroupBuy;
import com.enation.javashop.core.model.GroupBuyCount;
import com.enation.javashop.core.service.IGroupBuyManager;

public class GroupBuyAction extends WWAction {
	
	private IGroupBuyManager groupBuyManager;
	private boolean  isEdit= false;
	private GroupBuy groupBuy;
	private int groupid;
	private int[] start;
	private int[] end;
	private int[] num;
	private String start_time;
	private String end_time;
	private String imgFileFileName;
	private File imgFile;

	
	private List<GroupBuyCount> ruleList;
	public String list(){
		this.webpage = this.groupBuyManager.list(this.getPage(),this.getPageSize());
		return "list";
	}
	
	
	public String add(){
		this.isEdit = false;
		
		return this.INPUT;
	}
	
	public String edit(){
		this.isEdit = true;
		this.groupBuy= this.groupBuyManager.get(groupid);
		ruleList =groupBuyManager.listRule(groupid);
		return this.INPUT;
	}
	
	public String saveAdd(){
		try{
			if(imgFile!=null && imgFileFileName!=null){
				String imgPath = UploadUtil.upload(imgFile, imgFileFileName, "goods");
				this.groupBuy.setImg(imgPath);
			}
			groupBuy.setStart_time(DateUtil.getDatelineLong(start_time));
			groupBuy.setEnd_time( DateUtil.getDatelineLong(end_time) );
			groupBuy.setCountRuleList(this.createRule());
			this.groupBuyManager.add(groupBuy);
			this.msgs.add("团购添加成功");
			this.urls.put("团购列表", "groupBuy!list.do");
		}catch(RuntimeException e){
			this.msgs.add(e.getMessage());
			this.urls.put("重新输入", "javascript:back();");
		}
		return this.MESSAGE;
	}
	
	public String saveEdit(){
		try{
			
			if(imgFile!=null && imgFileFileName!=null){
				String imgPath = UploadUtil.upload(imgFile, imgFileFileName, "goods");
				this.groupBuy.setImg(imgPath);
			}
			
			
			groupBuy.setStart_time(DateUtil.getDatelineLong(start_time));
			groupBuy.setEnd_time( DateUtil.getDatelineLong(end_time) );
			groupBuy.setCountRuleList(this.createRule());
			this.groupBuyManager.edit(groupBuy);
			this.msgs.add("团购修改成功");
			this.urls.put("团购列表", "groupBuy!list.do");
		}catch(RuntimeException e){
			e.printStackTrace();
			this.msgs.add(e.getMessage());
			this.urls.put("重新输入", "javascript:back();");
		}
		return this.MESSAGE;
	}
	
	private List<GroupBuyCount> createRule(){
		
		if(start==null || end==null || num==null) throw new RuntimeException("规则数据输入不正确");
		
		
		List<GroupBuyCount>  list  = new ArrayList<GroupBuyCount>();
		
		for(int i=0;i<start.length;i++){
			GroupBuyCount groupBuyCount = new GroupBuyCount();
			groupBuyCount.setStart_time(start[i]);
			groupBuyCount.setEnd_time(end[i]);
			groupBuyCount.setNum(num[i]);
			list.add(groupBuyCount);
		}
		
		return list;
	}
	
	public String delete(){
		this.groupBuyManager.delete(groupid);
		this.msgs.add("团购删除成功");
		this.urls.put("团购列表", "groupBuy!list.do");
		return this.MESSAGE;
	}


	public  IGroupBuyManager getGroupBuyManager() {
		return groupBuyManager;
	}


	public  void setGroupBuyManager(IGroupBuyManager groupBuyManager) {
		this.groupBuyManager = groupBuyManager;
	}


	public  boolean getIsEdit() {
		return isEdit;
	}


	public  void setIsEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}


	public  int[] getStart() {
		return start;
	}


	public  void setStart(int[] start) {
		this.start = start;
	}


	public  int[] getEnd() {
		return end;
	}


	public  void setEnd(int[] end) {
		this.end = end;
	}


	public  int[] getNum() {
		return num;
	}


	public  void setNum(int[] num) {
		this.num = num;
	}


	public  GroupBuy getGroupBuy() {
		return groupBuy;
	}


	public  void setGroupBuy(GroupBuy groupBuy) {
		this.groupBuy = groupBuy;
	}


	public  int getGroupid() {
		return groupid;
	}


	public  void setGroupid(int groupid) {
		this.groupid = groupid;
	}


	public  List<GroupBuyCount> getRuleList() {
		return ruleList;
	}


	public  void setRuleList(List<GroupBuyCount> ruleList) {
		this.ruleList = ruleList;
	}


	public  String getStart_time() {
		return start_time;
	}


	public  void setStart_time(String startTime) {
		start_time = startTime;
	}


	public  String getEnd_time() {
		return end_time;
	}


	public  void setEnd_time(String endTime) {
		end_time = endTime;
	}


	public  void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}


	public String getImgFileFileName() {
		return imgFileFileName;
	}


	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}


	public File getImgFile() {
		return imgFile;
	}


	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
	
	
}

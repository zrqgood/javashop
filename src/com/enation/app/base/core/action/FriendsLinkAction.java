package com.enation.app.base.core.action;

import java.io.File;
import java.util.List;

import com.enation.app.base.core.model.FriendsLink;
import com.enation.app.base.core.service.IFriendsLinkManager;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.FileUtil;

/**
 * 友情链接
 * 
 * @author lzf<br/>
 *         2010-4-8 下午06:27:11<br/>
 *         version 1.0<br/>
 */
public class FriendsLinkAction extends WWAction {
	
	private String id;
	private FriendsLink friendsLink;
	private IFriendsLinkManager friendsLinkManager;
	private List listLink;
	private File pic;
	private String picFileName;
	private String oldpic;
	private int link_id;
	
	public String list(){
		listLink = friendsLinkManager.listLink();
		return "list";
	}
	
	public String add(){
		return "add";
	}
	
	public String delete(){
		try {
			this.friendsLinkManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String addSave(){
		if (pic != null) {
			if (FileUtil.isAllowUp(picFileName)) {
				friendsLink.setLogo(UploadUtil.upload(pic, picFileName, "friendslink"));
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		try{
			friendsLinkManager.add(friendsLink);
			this.msgs.add("友情链接添加成功");
			this.urls.put("友情链接列表", "friendsLink!list.do");
		}catch(Exception e){
			e.printStackTrace();
			this.msgs.add("友情链接添加失败");
			this.urls.put("友情链接列表", "friendsLink!list.do");
		}
		return this.MESSAGE;
	}
	
	public String edit(){
		friendsLink = friendsLinkManager.get(link_id);
		return "edit";
	}
	public String editSave(){
		if (pic != null) {
			if (FileUtil.isAllowUp(picFileName)) {
				friendsLink.setLogo(UploadUtil.upload(pic, picFileName, "friendslink"));
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
				return this.MESSAGE;
			}
		}
		try{
			friendsLinkManager.update(friendsLink);
			this.msgs.add("友情链接修改成功");
			this.urls.put("友情链接列表", "friendsLink!list.do");
		}catch(Exception e){
			e.printStackTrace();
			this.msgs.add("友情链接修改失败");
			this.urls.put("友情链接列表", "!list.do");
		}
		return this.MESSAGE;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public FriendsLink getFriendsLink() {
		return friendsLink;
	}
	public void setFriendsLink(FriendsLink friendsLink) {
		this.friendsLink = friendsLink;
	}
	public IFriendsLinkManager getFriendsLinkManager() {
		return friendsLinkManager;
	}
	public void setFriendsLinkManager(IFriendsLinkManager friendsLinkManager) {
		this.friendsLinkManager = friendsLinkManager;
	}
	public List getListLink() {
		return listLink;
	}
	public void setListLink(List listLink) {
		this.listLink = listLink;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getOldpic() {
		return oldpic;
	}

	public void setOldpic(String oldpic) {
		this.oldpic = oldpic;
	}

	public int getLink_id() {
		return link_id;
	}

	public void setLink_id(int linkId) {
		link_id = linkId;
	}
}

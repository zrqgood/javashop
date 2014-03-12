package com.enation.app.base.core.action;

import com.enation.app.base.core.model.GuestBook;
import com.enation.app.base.core.service.IGuestBookManager;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;

public class GuestBookAction extends WWAction {
	private IGuestBookManager guestBookManager;
	private String keyword;
	private int parentid;
	private int id;
	private String content;
	private GuestBook book;
	private Integer[] idArray;
	
	public String list(){
		if(!StringUtil.isEmpty(keyword)) keyword = StringUtil.toUTF8(keyword);
		this.webpage = this.guestBookManager.list(keyword, this.getPage(), this.getPageSize());
		return "list";
	}
	
	public String reply(){
		GuestBook guestbook = new GuestBook();
		guestbook.setContent(content);
		guestbook.setParentid(parentid);
		this.guestBookManager.reply(guestbook);
		this.msgs.add("回复成功");
		this.urls.put("查看此条留言", "guestBook!detail.do?id="+parentid);
		return this.MESSAGE;
	}
	
	
	public String detail(){
		book  = this.guestBookManager.get(id);
		return "detail";
	}
	
	public String edit(){
		this.guestBookManager.edit(id, content);
		this.json ="{result:1}";
		return this.JSON_MESSAGE;
	}
	
	public String delete(){
		try{
			this.guestBookManager.delete(idArray);
			this.json ="{result:0,message:'删除成功'}";
		}catch(RuntimeException e){
			this.json="{result:1,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public IGuestBookManager getGuestBookManager() {
		return guestBookManager;
	}
	public void setGuestBookManager(IGuestBookManager guestBookManager) {
		this.guestBookManager = guestBookManager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public GuestBook getBook() {
		return book;
	}

	public void setBook(GuestBook book) {
		this.book = book;
	}

	public Integer[] getIdArray() {
		return idArray;
	}

	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}

 
	
}

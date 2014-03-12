package com.enation.app.base.core.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.DataLog;
import com.enation.app.base.core.model.GuestBook;
import com.enation.app.base.core.service.IGuestBookManager;
import com.enation.eop.resource.IDataLogManager;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;

/**
 * 留言板管理
 * @author kingapex
 * 2010-8-15下午05:21:10
 */
public class GuestBookManager extends BaseSupport<GuestBook> implements
		IGuestBookManager {

	private IDataLogManager dataLogManager;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(GuestBook guestbook) {
		if(guestbook== null ) throw new  IllegalArgumentException("param guestbook is NULL");
		guestbook.setDateline( DateUtil.getDatelineLong());
		guestbook.setIssubject(1);
		this.baseDaoSupport.insert("guestbook", guestbook);
		DataLog datalog = new DataLog();
		datalog.setContent("标题:"+guestbook.getTitle()+"<br>内容："+guestbook.getContent());
		datalog.setLogtype("留言");
		datalog.setOptype("添加");
		this.dataLogManager.add(datalog);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void delete(Integer[] idArray) {
		if(idArray== null || idArray.length==0) return ;
		String ids = StringUtil.arrayToString(idArray, ",");
		this.baseDaoSupport.execute("delete from guestbook where id in("+ids+")");
		this.baseDaoSupport.execute("delete from guestbook where parentid in("+ids+")");
	}

	
	public Page list(String keyword, int pageNo, int pageSize) {
		
		//首先查询出符合条件的主题
		String sql ="select * from guestbook where issubject=1";
		StringBuffer term  = new StringBuffer();
		
		//构造查询条件
		if(!StringUtil.isEmpty(keyword)){
			term.append(" and  (");
			term.append(" title like'%"+keyword+"%'");
			term.append(" or content like'%"+keyword+"%'");
			term.append(" or username like'%"+keyword+"%'");
			term.append(")");
		}
		
		sql= sql+term +" order by dateline desc";
		Page page = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize,GuestBook.class);
		List<GuestBook> subjectList  = (List<GuestBook>)page.getResult();
		
		//其次查询出这些主题的所有回复
		sql ="select * from guestbook where issubject=0 and parentid in (select id from "+this.getTableName("guestbook")+" where issubject =1    "+term+") order by dateline asc ";
		List<GuestBook> replyList  = this.baseDaoSupport.queryForList(sql,GuestBook.class);
		
		//对应主题和回复的关系
		for(GuestBook reply : replyList){
			this.addtoSubject(subjectList, reply);
		}
		
		return page;
	}

	
	/**
	 * 将某个回复对应到相应的主题中
	 * @param subjectList
	 * @param reply
	 */
	private void addtoSubject(List<GuestBook> subjectList, GuestBook reply){
		for(GuestBook subject: subjectList){
			if(subject.getId() ==  reply.getParentid()){ // 比对是否是此主题的回复
				subject.addReply(reply);
				break;
			}
		}
	}
	
	
	
	public void reply(GuestBook guestbook) {
		if(guestbook== null ) throw new  IllegalArgumentException("param guestbook is NULL");
		guestbook.setDateline( DateUtil.getDatelineLong());
		guestbook.setIssubject(0);
		this.baseDaoSupport.insert("guestbook", guestbook);

	}

	public void edit(int id,String content) {
		 this.baseDaoSupport.execute("update guestbook set content=? where id=?", content,id)	;
	}

	public GuestBook get(int id) {
		GuestBook guestbook  = this.baseDaoSupport.queryForObject("select * from guestbook where id=?", GuestBook.class, id);
		List replyList = this.baseDaoSupport.queryForList("select * from guestbook where parentid=? order by dateline asc", GuestBook.class,id);
		guestbook.setReplyList(replyList);
		return guestbook;
	}
	public IDataLogManager getDataLogManager() {
		return dataLogManager;
	}
	public void setDataLogManager(IDataLogManager dataLogManager) {
		this.dataLogManager = dataLogManager;
	}

}

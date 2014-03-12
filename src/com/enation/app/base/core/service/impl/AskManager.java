package com.enation.app.base.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.Ask;
import com.enation.app.base.core.model.Reply;
import com.enation.app.base.core.service.IAskManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopUser;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;

/**
 * 问答业务类
 * @author kingapex
 * 2010-8-6上午09:22:24
 */
public class AskManager extends BaseSupport<Ask> implements IAskManager {

	private ISiteManager siteManager; 
	private IUserManager userManager;
	
	public void add(Ask ask) {
		HttpServletRequest request  = EopContext.getHttpRequest(); 
		EopSite site  = EopContext.getContext().getCurrentSite();
		EopUser user  = userManager.get(site.getUserid());
		ask.setDateline(DateUtil.getDatelineLong()); //提问时间
		ask.setUserid(site.getUserid());//提问用户
		ask.setSiteid(site.getId()); 	//提问id
		ask.setIsreply(0); //新的提问标记为未回复
		ask.setSitename(site.getSitename()); //站点名称
		ask.setDomain(request.getServerName());  //域名
		ask.setUsername(user.getUsername());
		
		this.daoSupport.insert("eop_ask", ask);
	}

	
	@Transactional(propagation = Propagation.REQUIRED) 
	public void delete(Integer[] id) {
		if(id==null || id.length==0)return ;
		
		String idstr = StringUtil.arrayToString(id, ",");
		String sql  ="delete from eop_reply where askid in("+idstr+")";
		this.daoSupport.execute(sql);
		
		sql  ="delete from eop_ask where askid in("+idstr+")";
		this.daoSupport.execute(sql);
	}
	
	
	public void edit(Ask ask) {
		
	}
	
	public Page list() {
		
		return null;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}


	public void reply(Reply reply) {		
		if(reply.getUsername().equals("客服")){
			this.daoSupport.execute("update eop_ask set isreply=1 where askid=?", reply.getAskid());
		}else{
			this.daoSupport.execute("update eop_ask set isreply=0 where askid=?", reply.getAskid());
		}
		reply.setDateline(DateUtil.getDatelineLong());
		this.daoSupport.insert("eop_reply", reply);
	}


	/**
	 * 读取所有问题
	 */
	public Page listAllAsk(String keyword, Date startTime, Date endTime,
			int pageNo, int pageSize) {
		
		//强制使结束时间条件成立，为了方便构造sql
		endTime = endTime ==null?new Date():endTime;
		
		StringBuffer sql  =new StringBuffer();
		sql.append("select * from eop_ask where ");
		
		
		//结束时间
		sql.append("  dateline<=");
		sql.append( endTime.getTime()/1000  );
	
		
		//开始时间
		if(startTime!=null){
			sql.append(" and dateline>=");
			sql.append( startTime.getTime()/1000  );
		}
		
		
		//按关键字检索
		if(!StringUtil.isEmpty( keyword )){
			sql.append(" and  ");
			sql.append("(");
			
			//检索标题
			sql.append("title like '%");
			sql.append(keyword);
			sql.append("%'");
			
			//检索内容
			sql.append(" or content like '%");
			sql.append(keyword);
			sql.append("%'");
			
			//检索用户名
			sql.append(" or username like '%");
			sql.append(keyword);
			sql.append("%'");
			
			//检索站点名称
			sql.append(" or sitename like '%");
			sql.append(keyword);
			sql.append("%'");
			
			//检索域名
			sql.append(" or domain  like '%");
			sql.append(keyword);
			sql.append("%'");
			
			sql.append(")");
		}
		
		
		//按是否已读正序排序、按时间倒序排序
		sql.append(" order by isreply asc,dateline desc");
				
		return this.daoSupport.queryForPage(sql.toString(), pageNo, pageSize);
	}

	
	/**
	 * 读取我的问题
	 */
	public Page listMyAsk(String keyword, Date startTime, Date endTime,
			int pageNo, int pageSize) {
		StringBuffer sql  =new StringBuffer();
		EopSite site  = EopContext.getContext().getCurrentSite();
		sql.append("select * from eop_ask where userid =");
		sql.append(site.getUserid());
		sql.append(" and siteid=");
		sql.append(site.getId());
		
		//按关键字检索
		if(!StringUtil.isEmpty( keyword )){
			sql.append(" and  ");
			sql.append("(");
			
			//检索标题
			sql.append("title like '%");
			sql.append(keyword);
			sql.append("%'");
			
			//检索内容
			sql.append(" or content like '%");
			sql.append(keyword);
			sql.append("%'");
			
			
			sql.append(")");
		}
		//开始时间
		if(startTime!=null){
			sql.append(" and dateline>=");
			sql.append( startTime.getTime()/1000  );
		}
		
		//结束时间
		if(endTime!=null){
			sql.append(" and dateline<=");
			sql.append( endTime.getTime()/1000  );
		}		
		
		//按是否已读正序排序、按时间倒序排序
		sql.append(" order by isreply asc,dateline desc");
				
		return this.daoSupport.queryForPage(sql.toString(), pageNo, pageSize);
	}


	public Ask get(Integer askid) {
		//读取此问题的数据
		String sql  ="select * from eop_ask where askid=?";
		Ask ask  = this.daoSupport.queryForObject(sql, Ask.class, askid); 
		
		//读取此问题的回复列表
		sql ="select * from eop_reply where askid=? order by dateline asc";
		List replylist  = this.daoSupport.queryForList(sql, Reply.class, askid);
		ask.setReplyList(replylist);
		
		return ask;
	}


	public IUserManager getUserManager() {
		return userManager;
	}


	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
	

}

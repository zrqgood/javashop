package com.enation.javashop.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.Message;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IMessageManager;

public class MessageManager extends BaseSupport<Message> implements IMessageManager {

	
	public Page pageMessage(int pageNo, int pageSize, String folder) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select * from message where folder = ? ";
		if(folder.equals("inbox")){//收件箱
			sql += " and to_id = ? and del_status != '1'";
		}else{//发件箱
			sql += " and from_id = ? and del_status != '2'";
		}
		sql += " order by date_line desc";
		Page webpage = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize, folder, member.getMember_id());
		List<Map> list = (List<Map>) (webpage.getResult());
//		for (Map order : list) {
//			Long time = (Long)order.get("date_line");
//			order.put("date", (new Date(time)));
//		}
		return webpage;
	}

	
	public void addMessage(Message message) {
		this.baseDaoSupport.insert("message", message);
		
	}

	
	public void delinbox(String ids) {
		this.baseDaoSupport.execute("delete from message where msg_id in (" + ids + ") and del_status = '2'");
		this.baseDaoSupport.execute("update message set del_status = '1' where msg_id in (" + ids + ")");
	}

	
	public void deloutbox(String ids) {
		this.baseDaoSupport.execute("delete from message where msg_id in (" + ids + ") and del_status = '1'");
		this.baseDaoSupport.execute("update message set del_status = '2' where msg_id in (" + ids + ")");
	}

	
	public void setMessage_read(int msg_id) {
		this.daoSupport.execute("update " + this.getTableName("message") + " set unread = '1' where msg_id = ?", msg_id);
	}

}

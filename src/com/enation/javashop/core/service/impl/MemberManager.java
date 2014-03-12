package com.enation.javashop.core.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.EncryptionUtil1;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.plugin.member.MemberPluginBundle;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IMemberPointManger;

/**
 * 会员管理
 * @author kingapex
 *2010-4-30上午10:07:24
 */
public class MemberManager extends BaseSupport<Member> implements IMemberManager{
 
	protected IMemberLvManager memberLvManager;
	private IMemberPointManger memberPointManger;
	
	private MemberPluginBundle memberPluginBundle;
	
	public void logout(){
		  Member member = UserServiceFactory.getUserService().getCurrentMember();
		  member = this.get(member.getMember_id());
		  ThreadContextHolder.getSessionContext().removeAttribute(IUserService.CURRENT_MEMBER_KEY);
		  this.memberPluginBundle.onLogout(member);
		  
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public int register(Member member) {
		
		int result = add(member);
		this.memberPluginBundle.onRegister(member);
		
		return result;
	}
	@Transactional(propagation = Propagation.REQUIRED)  
	public int add(Member member) {
		
		if(member==null) throw new IllegalArgumentException("member is null");
		if(member.getUname()==null) throw new IllegalArgumentException("member' uname is null");
		if(member.getPassword() ==null) throw new IllegalArgumentException("member' password is null");
		if(member.getEmail()==null) throw new IllegalArgumentException("member'email is null");
		
		if(this.checkname( member.getUname()) ==1){
			return 0;
		}
		
		Integer lvid  = memberLvManager.getDefaultLv();
		member.setLv_id(lvid);
		
		member.setName(member.getUname());
		member.setSex(0);
		
		member.setProvince_id(0);
		member.setCity_id(0);
		member.setRegion_id(0);
		member.setPoint(0);
		member.setAdvance(0D);
		member.setRegtime(System.currentTimeMillis());//lzf add
		member.setLastlogin(DateUtil.getDateline());
		member.setLogincount(1);
		member.setPassword(StringUtil.md5(member.getPassword()));
		 
		
		this.baseDaoSupport.insert("member", member);
		int memberid  = this.baseDaoSupport.getLastId("member");
		member.setMember_id(memberid);

		
		return 1;
	}



	
	
	public Member checkEmail(String checkStr) {
		
		String str = EncryptionUtil1.authcode(checkStr, "DECODE","",0);
		String[] array = str.split(",");
		if(array.length!=2) throw new RuntimeException("验证字串不正确");
		int memberid  = Integer.valueOf(array[0]);
		long regtime = Long.valueOf(array[1]);
		
		Member member = this.get(memberid);
		if(member.getRegtime() != regtime){
			throw new RuntimeException("验证字串不正确");
		}
		if(member.getIs_cheked()==1){
			return member;
		}
		
		

		String sql = "update member set is_cheked = 1 where member_id =  "+memberid;
		this.baseDaoSupport.execute(sql);
		this.memberPluginBundle.onEmailCheck(member);
		return member;
		
	}

	public Member get(Integer memberId) {
		String sql = "select * from member where member_id=?";
	    Member m = this.baseDaoSupport.queryForObject(sql, Member.class, memberId);
	    return m;
	}

	
	public Member getMemberByUname(String uname) {
		String sql = "select * from member where uname=?";
	    List list = this.baseDaoSupport.queryForList(sql, Member.class, uname);
	    Member m = null;
	    if(list!=null && list.size()>0){
	    	m = (Member)list.get(0);
	    }
	    return m;
	} 
	
	public Member getMemberByEmail(String email){
		String sql = "select * from member where email=?";
	    List list = this.baseDaoSupport.queryForList(sql, Member.class, email);
	    Member m = null;
	    if(list!=null && list.size()>0){
	    	m = (Member)list.get(0);
	    }
	    return m;
	}

	
	public Member edit(Member member) {
		//前后台用到的是一个edit方法，请在action处理好
		this.baseDaoSupport.update("member", member, "member_id="
				+ member.getMember_id());
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		return null;
	}



	
	public int checkname(String name) {
		String sql = "select count(0) from member where uname=?";
		int count = this.baseDaoSupport.queryForInt(sql, name);
		count = count > 0 ? 1 : 0;
		return count;
	}
	
	public int checkemail(String email) {
		String sql = "select count(0) from member where email=?";
		int count = this.baseDaoSupport.queryForInt(sql, email);
		count = count > 0 ? 1 : 0;
		return count;
	}

	
	public Page list(String order, int page, int pageSize) {
		order = order == null ? " m.member_id desc" : order;
		String sql = "select m.*,lv.name as lv_name from "+this.getTableName("member")+" m left join " +this.getTableName("member_lv")+" lv on m.lv_id = lv.lv_id ";
		//sql += "  and lv.userid = "+this.getCurrentUserid()+" and lv.siteid="+this.getCurrentSiteid();
		sql += " order by  " + order;
		Page webpage = this.daoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}
	
	
    public void delete(String id){
		if (id == null || id.equals(""))
			return;
		String sql = "delete from member where member_id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
    }



 

	
	public void updatePassword(String password) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		this.updatePassword(member.getMember_id(), password);
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		
	}
	

	
	public void updatePassword(Integer memberid, String password) {
		password = password == null ? StringUtil.md5("") : StringUtil
				.md5(password);
		String sql = "update member set password = ? where member_id =? ";
		this.baseDaoSupport.execute(sql,password,memberid);
	}
	
	public void updateFindCode(Integer memberid,String code){
		String sql = "update member set find_code = ? where member_id =? ";
		this.baseDaoSupport.execute(sql,code,memberid);
	}

	@Transactional(propagation = Propagation.REQUIRED) 
	public int login(String username, String password) {
		String sql = "select m.*,l.name as lvname from "+this.getTableName("member")+" m left join "+this.getTableName("member_lv")+" l on m.lv_id = l.lv_id where m.uname=? and password=?";
		//用户名中包含@，说明是用邮箱登录的
		if(username.contains("@")){
			sql = "select m.*,l.name as lvname from "+this.getTableName("member")+" m left join "+this.getTableName("member_lv")+" l on m.lv_id = l.lv_id where m.email=? and password=?";
		}
		
		List<Member > list  =this.daoSupport.queryForList(sql, Member.class, username,com.enation.framework.util.StringUtil.md5(password));
		if(list==null || list.isEmpty()  ){
			return 0;
		}
		 
		Member member = list.get(0);
		long ldate = ((long)member.getLastlogin())*1000;
		Date date = new Date(ldate);
		Date today = new Date();
		int logincount = member.getLogincount();
		if(DateUtil.toString(date, "yyyy-MM").equals(DateUtil.toString(today, "yyyy-MM"))){//与上次登录在同一月内
			logincount++;
		}else{
			logincount = 1;
		}
		member.setLastlogin(DateUtil.getDateline());
		member.setLogincount(logincount);
		this.edit(member);
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		 
		
		this.memberPluginBundle.onLogin(member);
		
	
		
		return 1;
	}
	
	@Transactional(propagation = Propagation.REQUIRED) 
	public int loginWithCookie(String username, String password) {
		String sql = "select m.*,l.name as lvname from "+this.getTableName("member")+" m left join "+this.getTableName("member_lv")+" l on m.lv_id = l.lv_id where m.uname=? and password=?";
		//用户名中包含@，说明是用邮箱登录的
		if(username.contains("@")){
			sql = "select m.*,l.name as lvname from "+this.getTableName("member")+" m left join "+this.getTableName("member_lv")+" l on m.lv_id = l.lv_id where m.email=? and password=?";
		}
		List<Member > list  =this.daoSupport.queryForList(sql, Member.class, username,password);
		if(list==null || list.isEmpty()  ){
			return 0;
		}
		 
		Member member = list.get(0);
		long ldate = ((long)member.getLastlogin())*1000;
		Date date = new Date(ldate);
		Date today = new Date();
		int logincount = member.getLogincount();
		if(DateUtil.toString(date, "yyyy-MM").equals(DateUtil.toString(today, "yyyy-MM"))){//与上次登录在同一月内
			logincount++;
		}else{
			logincount = 1;
		}
		member.setLastlogin(DateUtil.getDateline());
		member.setLogincount(logincount);
		this.edit(member);
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		
		
		this.memberPluginBundle.onLogin(member);
 
		
		
		return 1;
	}

	
	/**
	 *系统管理员作为某个会员登录
	 */
	public int loginbysys(String username) {

		 IUserService userService = UserServiceFactory.getUserService();
		 if(!userService.isUserLoggedIn()){
			 throw new  RuntimeException("您无权进行此操作，或者您的登录已经超时");
		 }
		 
		String sql = "select m.*,l.name as lvname from "+this.getTableName("member")+" m left join "+this.getTableName("member_lv")+" l on m.lv_id = l.lv_id where m.uname=?";
		List<Member > list  =this.daoSupport.queryForList(sql, Member.class, username);
		if(list==null || list.isEmpty()  ){
			return 0;
		}
		 
		Member member = list.get(0);	
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		return 1;
	}

	
	
	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}

	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}

	
	public void addMoney(Integer memberid, Double num) {
		String sql  ="update member set advance=advance+? where member_id=?";
		this.baseDaoSupport.execute(sql, num,memberid);

	}

	
	
	
	public void cutMoney(Integer memberid, Double num) {
		Member member  = this.get(memberid);	
		if(member.getAdvance()<num){
			throw new  RuntimeException("预存款不足:需要["+num+"],剩余["+member.getAdvance()+"]");
		}
		String sql  ="update member set advance=advance-? where member_id=?";
		this.baseDaoSupport.execute(sql, num,memberid);
	}


	
	public Page list(String order, String name, String uname, int page, int pageSize) {
		order = order == null ? " m.member_id desc" : order;
		String sql = "select m.*,lv.name as lv_name from "+this.getTableName("member")+" m left join " +this.getTableName("member_lv")+" lv on m.lv_id = lv.lv_id where 1=1";
		if(name!=null && !name.equals("")){
			sql += " and m.name like '%" + name + "%'";
		}
		if(uname!=null && !uname.equals("")){
			sql += " and m.uname like '%" + uname + "%'";
		}
		sql += " order by  " + order;
		Page webpage = this.daoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}


	public IMemberPointManger getMemberPointManger() {
		return memberPointManger;
	}


	public void setMemberPointManger(IMemberPointManger memberPointManger) {
		this.memberPointManger = memberPointManger;
	}


	public static void main(String[] args){
		System.out.println(DateUtil.toDate("2011-05-27", "yyyy-MM-dd").getTime());
	}


	public void updateLv(int memberid, int lvid) {
		String sql ="update member set lv_id=? where member_id=?";
		this.baseDaoSupport.execute(sql, lvid,memberid);
	}
	public MemberPluginBundle getMemberPluginBundle() {
		return memberPluginBundle;
	}
	public void setMemberPluginBundle(MemberPluginBundle memberPluginBundle) {
		this.memberPluginBundle = memberPluginBundle;
	}
	
}

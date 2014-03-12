package com.enation.javashop.core.action.backend;

import java.util.Date;
import java.util.List;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.MemberLv;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopUser;
import com.enation.framework.action.WWAction;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.model.AdvanceLogs;
import com.enation.javashop.core.model.PointHistory;
import com.enation.javashop.core.service.IAdvanceLogsManager;
import com.enation.javashop.core.service.ICommentsManager;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPointHistoryManager;
import com.enation.javashop.core.service.IRegionsManager;

public class MemberAction extends WWAction {

	private IMemberManager memberManager;
	private IMemberLvManager memberLvManager;
	private IRegionsManager regionsManager;
	private IOrderManager orderManager;
	private IPointHistoryManager pointHistoryManager;
	private IAdvanceLogsManager advanceLogsManager;
	private ICommentsManager commentsManager;
	private IUserManager userManager ;
	private Member member;
	private MemberLv lv;
	private String birthday;
	private String order;
	private Integer lv_id;
	private Integer member_id;
	private String id;
	private List lvlist;
	private List provinceList;
	private List cityList;
	private List regionList;
	private List listOrder;
	private List listPointHistory;
	private List listAdvanceLogs;
	private List listComments;
	private int point;
	private Double modify_advance;
	private String modify_memo;
	private String object_type;
	private String name;
	private String uname;

	public String add_lv() {
		return "add_lv";
	}

	public String edit_lv() {
		lv = this.memberLvManager.get(lv_id);
		return "edit_lv";
	}

	public String list_lv() {
		this.webpage = memberLvManager.list(order, this.getPage(), this
				.getPageSize());
		return "list_lv";
	}

	public String saveAddLv() {
		memberLvManager.add(lv);
		this.msgs.add("会员等级添加成功");
		this.urls.put("会员等级列表", "member!list_lv.do");
		return this.MESSAGE;
	}

	public String saveEditLv() {
		memberLvManager.edit(lv);
		this.msgs.add("会员等级修改成功");
		this.urls.put("会员等级列表", "member!list_lv.do");
		return this.MESSAGE;
	}

	public String deletelv() {
		try {
			this.memberLvManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public String add_member() {
		lvlist = this.memberLvManager.list();
		provinceList = this.regionsManager.listProvince();
		return "add_member";
	}

	public String edit_member() {
		member = this.memberManager.get(member_id);
		lvlist = this.memberLvManager.list();
		return "edit_member";
	}

	public String memberlist() {
		this.webpage = this.memberManager.list(order, name, uname, this.getPage(),
				this.getPageSize());
		return "list_member";
	}

	public String saveEditMember() {
		Date br = DateUtil.toDate(birthday, "yyyy-MM-dd");
		member.setBirthday(br.getTime());
		try {
			this.memberManager.edit(member);
			this.json = "{'result':0,'message':'修改成功'}";
		} catch (Exception e) {
			this.json = "{'result':1,'message':'修改失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;

	}

	public String delete() {
		try {
			this.memberManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public String detail() {
		return "detail";
	}

	public String base() {
		this.member = this.memberManager.get(member_id);
		return "base";
	}

	public String edit() {
		member = this.memberManager.get(member_id);
		lvlist = this.memberLvManager.list();
		provinceList = this.regionsManager.listProvince();
		if (member.getProvince_id() != null) {
			cityList = this.regionsManager.listCity(member.getProvince_id());
		}
		if (member.getCity_id() != null) {
			regionList = this.regionsManager.listRegion(member.getCity_id());
		}
		return "edit";
	}

	public String orderLog() {
		listOrder = this.orderManager.listOrderByMemberId(member_id);
		return "orderLog";
	}

	public String editPoint() {
		member = this.memberManager.get(member_id);
		return "editPoint";
	}

	public String editSavePoint() {
		member = this.memberManager.get(member_id);
		member.setPoint(member.getPoint() + point);
		PointHistory pointHistory = new PointHistory();
		pointHistory.setMember_id(member_id);
		pointHistory.setOperator("管理员");
		pointHistory.setPoint(point);
		pointHistory.setReason("operator_adjust");
		pointHistory.setTime(System.currentTimeMillis());
		try {
			memberManager.edit(member);
			pointHistoryManager.addPointHistory(pointHistory);
			this.json = "{'result':0,'message':'会员积分修改成功'}";
		} catch (Exception e) {
			this.json = "{'result':1,'message':'修改失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;
	}

	public String pointLog() {
		listPointHistory = pointHistoryManager.listPointHistory(member_id);
		member = this.memberManager.get(member_id);
		return "pointLog";
	}

	public String advance() {
		member = this.memberManager.get(member_id);
		listAdvanceLogs = this.advanceLogsManager
				.listAdvanceLogsByMemberId(member_id);
		return "advance";
	}

	public String comments() {
		listComments = commentsManager.listComments(member_id, object_type);
		return "comments";
	}

	public String remark() {
		member = this.memberManager.get(member_id);
		return "remark";
	}

	public String remarkSave() {
		member = this.memberManager.get(member_id);
		member.setRemark(modify_memo);
		try {
			memberManager.edit(member);
			this.json = "{'result':0,'message':'会员备注修改成功'}";
		} catch (Exception e) {
			this.json = "{'result':1,'message':'修改失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 预存款充值
	 * 
	 * @return
	 */
	public String editSaveAdvance() {
		member = this.memberManager.get(member_id);
		member.setAdvance(member.getAdvance() + modify_advance);
		try {
			memberManager.edit(member);
		} catch (Exception e) {
			this.json = "{'result':1, 'message':'操作失败'}";
			e.printStackTrace();
		}

		AdvanceLogs advanceLogs = new AdvanceLogs();
		advanceLogs.setMember_id(member_id);
		advanceLogs.setDisabled("false");
		advanceLogs.setMtime(System.currentTimeMillis());
		advanceLogs.setImport_money(modify_advance);
		advanceLogs.setMember_advance(member.getAdvance());
		advanceLogs.setShop_advance(member.getAdvance());// 此字段很难理解
		advanceLogs.setMoney(modify_advance);
		advanceLogs.setMessage(modify_memo);
		advanceLogs.setMemo("admin代充值");
		try {
			advanceLogsManager.add(advanceLogs);
		} catch (Exception e) {
			this.json = "{'result':1, 'message':'操作失败'}";
			e.printStackTrace();
		}
		this.json = "{'result':0,'message':'操作成功'}";

		return this.JSON_MESSAGE;
	}

	private String message;

	/**
	 * 预存款充值
	 * 
	 * @return
	 */
	// public String addMoney(){
	// //this.memberManager.addMoney(member.getBiz_money(),
	// member.getMember_id(),message);
	// this.msgs.add("充值成功");
	// this.urls.put("返回此会员预存款信息", "member/member_money.jsp?member_id="+
	// member.getMember_id());
	// return this.MESSAGE;
	// }
	/**
	 * 保存添加会员
	 * 
	 * @return
	 */
	public String saveMember() {
		int result = memberManager.checkname(member.getUname());
		if (result == 1) {
			this.msgs.add("用户名已经存在");
			this.urls.put("查看会员列表", "member!memberlist.do");
			return this.MESSAGE;
		}
		if (member != null) {
			Date br = DateUtil.toDate(birthday, "yyyy-MM-dd");
			member.setBirthday(br.getTime());
			member.setPassword(member.getPassword());
			member.setRegtime(System.currentTimeMillis());// lzf add
			memberManager.add(member);
			this.msgs.add("保存添加会员成功");
			this.urls.put("查看会员列表", "member!memberlist.do");
		} //else

		return this.MESSAGE;
	}
	
	public String sysLogin(){
		try{
			int r  = this.memberManager.loginbysys(name);
			if(r==1){
				EopUser user  = this.userManager.get(name);
				if(user!=null){
					WebSessionContext<EopUser> sessonContext = ThreadContextHolder
					.getSessionContext();	
					sessonContext.setAttribute(IUserManager.USER_SESSION_KEY, user);
				}
				return "syslogin";
			}
			this.msgs.add("登录失败");
			return this.MESSAGE;
		}catch(RuntimeException e){
			this.msgs.add(e.getMessage());
			return this.MESSAGE;
		}
	}

	public MemberLv getLv() {
		return lv;
	}

	public void setLv(MemberLv lv) {
		this.lv = lv;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}

	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getLv_id() {
		return lv_id;
	}

	public void setLv_id(Integer lvId) {
		lv_id = lvId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer memberId) {
		member_id = memberId;
	}

	public List getLvlist() {
		return lvlist;
	}

	public void setLvlist(List lvlist) {
		this.lvlist = lvlist;
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

	public List getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List provinceList) {
		this.provinceList = provinceList;
	}

	public List getCityList() {
		return cityList;
	}

	public void setCityList(List cityList) {
		this.cityList = cityList;
	}

	public List getRegionList() {
		return regionList;
	}

	public void setRegionList(List regionList) {
		this.regionList = regionList;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public List getListOrder() {
		return listOrder;
	}

	public void setListOrder(List listOrder) {
		this.listOrder = listOrder;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public IPointHistoryManager getPointHistoryManager() {
		return pointHistoryManager;
	}

	public void setPointHistoryManager(IPointHistoryManager pointHistoryManager) {
		this.pointHistoryManager = pointHistoryManager;
	}

	public List getListPointHistory() {
		return listPointHistory;
	}

	public void setListPointHistory(List listPointHistory) {
		this.listPointHistory = listPointHistory;
	}

	public IAdvanceLogsManager getAdvanceLogsManager() {
		return advanceLogsManager;
	}

	public void setAdvanceLogsManager(IAdvanceLogsManager advanceLogsManager) {
		this.advanceLogsManager = advanceLogsManager;
	}

	public List getListAdvanceLogs() {
		return listAdvanceLogs;
	}

	public void setListAdvanceLogs(List listAdvanceLogs) {
		this.listAdvanceLogs = listAdvanceLogs;
	}

	public Double getModify_advance() {
		return modify_advance;
	}

	public void setModify_advance(Double modifyAdvance) {
		modify_advance = modifyAdvance;
	}

	public String getModify_memo() {
		return modify_memo;
	}

	public void setModify_memo(String modifyMemo) {
		modify_memo = modifyMemo;
	}

	public ICommentsManager getCommentsManager() {
		return commentsManager;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

	public List getListComments() {
		return listComments;
	}

	public void setListComments(List listComments) {
		this.listComments = listComments;
	}

	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String objectType) {
		object_type = objectType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

}

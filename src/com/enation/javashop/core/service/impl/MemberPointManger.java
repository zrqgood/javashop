package com.enation.javashop.core.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.MemberLv;
import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.PointHistory;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IMemberPointManger;
import com.enation.javashop.core.service.IPointHistoryManager;

/**
 * 会员积分管理
 * @author kingapex
 *
 */
public class MemberPointManger extends BaseSupport implements IMemberPointManger {
	private IPointHistoryManager pointHistoryManager;
	private IMemberManager memberManager;
	private IMemberLvManager memberLvManager;
	private ISettingService settingService ;
	public boolean checkIsOpen(String itemname) {
		String value = settingService.getSetting("point", itemname);
		
		return "1".equals(value);
	}

	 
	public int getItemPoint(String itemname) {
		String value = settingService.getSetting("point", itemname);
		if(StringUtil.isEmpty(value)){
			return 0;
		}
		return Integer.valueOf(value);
	}

	@Transactional(propagation = Propagation.REQUIRED)  
	public void useMarketPoint(int memberid,int point,String reson,Integer relatedId){
		PointHistory pointHistory = new  PointHistory();
		pointHistory.setMember_id(memberid);
		pointHistory.setOperator("member");
		pointHistory.setPoint(point);
		pointHistory.setReason(reson);
		pointHistory.setType(0);
		pointHistory.setPoint_type(1);
		pointHistory.setTime(System.currentTimeMillis());
		pointHistory.setRelated_id(relatedId);
		
		pointHistoryManager.addPointHistory(pointHistory);
		this.baseDaoSupport.execute("update member set mp=mp-? where member_id=?", point,memberid); 
	}
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void add(int memberid, int point, String itemname,Integer relatedId) {
		PointHistory pointHistory = new  PointHistory();
		pointHistory.setMember_id(memberid);
		pointHistory.setOperator("member");
		pointHistory.setPoint(point);
		pointHistory.setReason(itemname);
		pointHistory.setType(1);
		pointHistory.setPoint_type(0);
		pointHistory.setTime(System.currentTimeMillis());
		pointHistory.setRelated_id(relatedId);
		
		pointHistoryManager.addPointHistory(pointHistory);
		 
		Member member  = this.memberManager.get(memberid);
		Integer memberpoint  = member.getPoint();

		
		this.baseDaoSupport.execute("update member set point=point+? where member_id=?", point,memberid); 
		
		//改变会员等级
		if(memberpoint!=null ){
			MemberLv lv =  this.memberLvManager.getByPoint(memberpoint);
			if(lv!=null ){
				if(member.getLv_id()==null ||lv.getLv_id().intValue()!= member.getLv_id().intValue()){
					this.memberManager.updateLv(memberid, lv.getLv_id());
				}
			}
		}
	}


	public IPointHistoryManager getPointHistoryManager() {
		return pointHistoryManager;
	}


	public void setPointHistoryManager(IPointHistoryManager pointHistoryManager) {
		this.pointHistoryManager = pointHistoryManager;
	}


	public ISettingService getSettingService() {
		return settingService;
	}


	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}


	public IMemberManager getMemberManager() {
		return memberManager;
	}


	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}


	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}


	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}

}

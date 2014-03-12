package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 会员中心-用户信息挂件
 * @author lzf<br/>
 * 2010-3-16 上午10:11:49<br/>
 * version 1.0<br/>
 */
public class MemberInfoWidget extends AbstractMemberWidget {
	
	private IRegionsManager regionsManager;
	private IMemberManager memberManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("myinfo");
		
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if("save".equals(action)){//是保存动作
			String name = request.getParameter("member.name");
			member.setName(name);
			String sex = request.getParameter("member.sex");
			member.setSex(Integer.valueOf(sex));
			String birthday = request.getParameter("mybirthday");
			member.setBirthday(DateUtil.toDate(birthday, "yyyy-MM-dd").getTime());
			String province_id = request.getParameter("member.province_id");
			member.setProvince_id(Integer.valueOf(province_id));
			String city_id = request.getParameter("member.city_id");
			member.setCity_id(Integer.valueOf(city_id));
			String region_id = request.getParameter("member.region_id");
			member.setRegion_id(Integer.valueOf(region_id));
			String province = request.getParameter("member.province");
			member.setProvince(province);
			String city = request.getParameter("member.city");
			member.setCity(city);
			String region = request.getParameter("member.region");
			member.setRegion(region);
			String address = request.getParameter("member.address");
			member.setAddress(address);
			String zip = request.getParameter("member.zip");
			member.setZip(zip);
			String mobile = request.getParameter("member.mobile");
			member.setMobile(mobile);
			String tel = request.getParameter("member.tel");
			member.setTel(tel);
			String pw_question = request.getParameter("member.pw_question");
			member.setPw_question(pw_question);
			String pw_answer = request.getParameter("member.pw_answer");
			member.setPw_answer(pw_answer);
			try{
				memberManager.edit(member);
				this.showMenu(false);
				this.showSuccess("修改成功","个人信息", "member_info.html");
			
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showMenu(false);
				this.showError("修改失败", "个人信息", "member_info.html");
			}
		}else{
			Long mybirthday = (Long)member.getBirthday();
			List provinceList = this.regionsManager.listProvince();
			List cityList = this.regionsManager.listCity(member.getProvince_id());
			cityList = cityList == null ? new ArrayList() : cityList;
			List regionList = this.regionsManager.listRegion(member.getCity_id());
			regionList = regionList == null ? new ArrayList() : regionList;
			this.putData("member", member);
			this.putData("provinceList", provinceList);
			this.putData("cityList", cityList);
			this.putData("regionList", regionList);
			if(mybirthday==null){
				this.putData("mybirthday", DateUtil.toDate("1970-01-01", "yyyy-MM-dd"));
			}else
				this.putData("mybirthday", (new Date(mybirthday)));
		}

	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
}

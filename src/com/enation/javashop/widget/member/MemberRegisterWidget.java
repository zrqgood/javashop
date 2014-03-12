package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.utils.ValidCodeServlet;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Agreement;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 会员注册挂件
 * @author kingapex
 *2010-4-29下午03:55:46
 */
public class MemberRegisterWidget extends AbstractMemberWidget {
	private HttpServletRequest request;
	private IMemberManager memberManager;
	private IRegionsManager regionsManager;
	
	protected void config(Map<String, String> params) {
			
	}

	private Agreement getAgreement(String agreement){
		Agreement agree = new Agreement();
		int idx = agreement.indexOf(":");
		agree.setTitle(agreement.substring(0,idx));
		agree.setUrl(agreement.substring(idx+1));
		agree.setUrl(agree.getUrl().replaceAll("'",""));
		return agree;
	}
	
	protected List<Agreement> parseAgreement(String agreement){
		List<Agreement> result = new ArrayList<Agreement>();
		String[] agrees = agreement.split(",");
		for(int i=0;i<agrees.length;i++){
			Agreement agree = getAgreement(agrees[i]);
			result.add(agree);
		}		
		return result;
	}
	
	protected void display(Map<String, String> params) {
		request = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		this.showMenu(false);
		if("register".equals(action)){
			this.register();
		}else if("supply".equals(action)){
			this.supply();
		}else if("checkname".equals(action)){
			this.checkname();
		}else{
			String forward = request.getParameter("forward");
			String friendid  = request.getParameter("friendid");
			this.putData("forward", forward);
			this.putData("friendid",friendid);
			this.setPageName("register");
		}
		String agreement = params.get("agreement");
		if(agreement!=null)
			this.putData("agreement",parseAgreement(agreement));
	}

	//注册 
	private void register(){
		String license  = request.getParameter("license");
		String validcode = request.getParameter("validcode") ;
		if(this.validcode(validcode)==0){
			this.showError("验证码输入错误!");
			return ;
		}
/*	注册协议修改为可选	
		if(!"agree".equals(license)){
			this.showError("同意注册协议才可以注册!");
			return ;
		}
*/		
		Member member = new Member();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String friendid =  request.getParameter("friendid");
		String registerip = request.getRemoteAddr();
		
		//如果用户名为空，则使用email做为用户名
		if(StringUtil.isEmpty(username)){
			username = email; 
		}
		
		String mobile= request.getParameter("mobile");
		member.setMobile(mobile);
		member.setUname(username);
		member.setPassword(password);
		member.setEmail(email);
		member.setRegisterip(registerip);
		if(!StringUtil.isEmpty(friendid)){
			member.setParentid(Integer.valueOf(friendid));
		}
		
		int result  = memberManager.add(member);
		if(result==1){ //添加成功
			this.memberManager.login(username, password);
			String forward = request.getParameter("forward");
			if(forward!=null && !forward.equals("")){
				this.setPageFolder( this.getThemePath());
				String message = request.getParameter("message");
				this.showSuccess("注册成功", message, forward);
			}else{
				this.showSuccess("注册成功", "会员中心", "member_index.html");
			}
		}else{
			this.showError("用户名["+member.getUname()+"]已存在!");
		}	
	}
	
	/**
	 * 检测用户名是否存在，并生成json返回给客户端
	 */
	private void checkname(){
		String username = request.getParameter("username");
		int result = this.memberManager.checkname(username);
		String json = "{result:"+result+"}";
		this.showJson(json);
	}
	
	//补充资料
	private void supply(){
		String memberid= request.getParameter("memberid");
		String name = request.getParameter("name");
		String sex= request.getParameter("sex");
		String birthday= request.getParameter("birthday");
		String address= request.getParameter("address");
		String zip= request.getParameter("zip");
		String mobile= request.getParameter("mobile");
		String tel= request.getParameter("tel");
		String pw_question= request.getParameter("pw_question");
		String pw_answer= request.getParameter("pw_answer");
	 
		String province_id= request.getParameter("province_id");
		String province= request.getParameter("province");
		String city_id= request.getParameter("city_id");
		String city= request.getParameter("city");
		String region_id= request.getParameter("region_id");
		String region= request.getParameter("region");
		
		Member member = this.memberManager.get(Integer.valueOf(memberid));
		member.setName(name);
		member.setSex(Integer.valueOf(sex));
		member.setBirthday(  DateUtil.toDate(birthday, "yyyy-MM-dd").getTime() ) ;
		member.setAddress(address);
		member.setZip(zip);
		member.setMobile(mobile);
		member.setTel(tel);
		member.setPw_question(pw_question);
		member.setPw_answer(pw_answer);
		
		if(!StringUtil.isEmpty(province_id))
			member.setProvince_id(Integer.valueOf(province_id));
		member.setProvince(province);
		
		if(!StringUtil.isEmpty(city_id))
			member.setCity_id(Integer.valueOf(city_id));
		member.setCity(city);
		
		if(!StringUtil.isEmpty(region_id))
			member.setRegion_id(Integer.valueOf(region_id));
		member.setRegion(region);
		this.memberManager.edit(member);
		ThreadContextHolder.getSessionContext().setAttribute(IUserService.CURRENT_MEMBER_KEY, member);
		this.showSuccess("资料更新成功","会员中心首页", "member_index.html");	
	}

	/**
	 * 校验验证码
	 * @param validcode
	 * @return 1成功  0失败
	 */
	private int validcode(String validcode){
		if(validcode==null){
			return 0;
		}
		
		String code  = (String)ThreadContextHolder.getSessionContext().getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"memberreg");
		if(code==null){
			return 0 ;
		} else {
			if(!code.equals(validcode)){
				return 0;
			}
		}
		
		return 1;
	}	

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}
}

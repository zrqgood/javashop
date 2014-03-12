package com.enation.javashop.widget.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 会员中心-修改密码挂件
 * 
 * @author lzf<br/>
 *         2010-3-19 下午12:12:06<br
 *         / version 1.0<br/>
 */
public class MemberSecurityWidget extends AbstractMemberWidget {

	private IRegionsManager regionsManager;
	private IMemberManager memberManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("member_security");

		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String action = request.getParameter("action");
		action = action == null ? "" : action;
		if (action.equals("save")) {
			String oldPassword = request.getParameter("oldpassword");
			oldPassword = oldPassword == null ? "" : StringUtil
					.md5(oldPassword);
			//		+ member.getPassword());
			if (oldPassword.equals(member.getPassword())) {
				String password = request.getParameter("password");
				String passwd_re = request.getParameter("passwd_re");
		
				if (passwd_re.equals(password)) {
					try {
						memberManager.updatePassword(password);
						this.showSuccess("修改密码成功", "修改密码",
								"member_security.html");
					} catch (Exception e) {
						if (this.logger.isDebugEnabled()) {
							logger.error(e.getStackTrace());
						}
						this
								.showError("修改密码失败", "修改密码",
										"member_security.html");
					}
				} else {
					this.showError("修改失败！两次输入的密码不一致", "修改密码",
							"member_security.html");
				}
			} else {
				this.showError("修改失败！原始密码不符", "修改密码", "member_security.html");
			}
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

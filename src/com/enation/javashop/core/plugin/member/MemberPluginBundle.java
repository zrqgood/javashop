package com.enation.javashop.core.plugin.member;

import com.enation.app.base.core.model.Member;
import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;


/**
 * 会员插件桩 
 * @author kingapex
 * @date 2011-10-20 下午3:32:23 
 * @version V1.0
 */
public class MemberPluginBundle extends AutoRegisterPluginsBundle {
	
	

	
	/**
	 * 激发注册事件
	 */
	public void onLogout(Member member){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IMemberLogoutEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onLogout 开始...");
						}
						IMemberLogoutEvent event = (IMemberLogoutEvent) plugin;
						event.onLogout(member);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onLogout 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用会员插件注销事件错误", e);
			throw e;
		}
	}
	
	
	
	/**
	 * 激发注册事件
	 */
	public void onLogin(Member member){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IMemberLoginEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onLogin 开始...");
						}
						IMemberLoginEvent event = (IMemberLoginEvent) plugin;
						event.onLogin(member);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onLogin 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用会员插件登录事件错误", e);
			throw e;
		}
	}
	
	
	
	
	/**
	 * 激发注册事件
	 */
	public void onRegister(Member member){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IMemberRegisterEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onRegister 开始...");
						}
						IMemberRegisterEvent event = (IMemberRegisterEvent) plugin;
						event.onRegister(member);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onRegister 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用会员插件注册事件错误", e);
			throw e;
		}
	}
	
	

	
	/**
	 * 激发邮件校验事件
	 */
	public void onEmailCheck(Member member){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IMemberEmailCheckEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onRegister 开始...");
						}
						IMemberEmailCheckEvent event = (IMemberEmailCheckEvent) plugin;
						event.onEmailCheck(member);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onRegister 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用会员插件邮件验证事件错误", e);
			throw e;
		}
	}
	
	
	

	@Override
	public String getName() {
		return "会员插件桩";
	}
	
	
	
}

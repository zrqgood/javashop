package com.enation.app.base.core.action;

import com.enation.app.base.core.model.Help;
import com.enation.app.base.core.service.IHelpManager;
import com.enation.framework.action.WWAction;
import com.enation.framework.database.ObjectNotFoundException;


/**
 * 帮助action
 * @author kingapex
 * 2010-10-17下午10:07:10
 */
public class HelpAction extends WWAction {
	
	private IHelpManager helpManager;
	private String helpid;
	private Help help;
	
	public String execute(){
	 try{
		help = helpManager.get(helpid);
		if(help==null){
			 help = new Help();
			 help.setContent("此帮助未定义");
		}
	 }catch(ObjectNotFoundException e){
		 help = new Help();
		 help.setContent("此帮助未定义");
	 }
		return "content";
	}
	
	
	public String getHelpid() {
		return helpid;
	}
	public void setHelpid(String helpid) {
		this.helpid = helpid;
	}
	public IHelpManager getHelpManager() {
		return helpManager;
	}
	public void setHelpManager(IHelpManager helpManager) {
		this.helpManager = helpManager;
	}
	public Help getHelp() {
		return help;
	}
	public void setHelp(Help help) {
		this.help = help;
	}
	
}

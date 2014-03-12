package com.enation.app.base.core.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.service.IAccessRecorder;
import com.enation.eop.resource.model.Link;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.action.WWAction;

public class AccessAction extends WWAction {

	private IAccessRecorder accessRecorder;
	private int startday;
	private int endday;
	private String runmodel; // 运行模式
	private Map sData;

	public String list() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		String starttime = null;
		String endtime = null;

		if (startday != 0) {
			starttime = year + "-" + month + "-" + startday;
		}
		if (endday != 0) {
			endtime = year + "-" + month + "-" + endday;
		}
		// System.out.println(starttime +"--" + endtime);
		this.webpage = this.accessRecorder.list(starttime, endtime,
				this.getPage(), 50);

		// 得到站点流量统计信息
		this.sData = this.accessRecorder.census();
		runmodel = EopSetting.RUNMODE;
		return "list";
	}

	/**
	 * 查询列表
	 */
	private String daytime;
	private String ip;
	private List accessList;

	/**
	 * 流量详细列表
	 * 
	 * @return
	 */
	public String detaillist() {
		this.accessList = this.accessRecorder.detaillist(ip, daytime);
		runmodel = EopSetting.RUNMODE;
		return "detaillist";
	}

	private List<Link> linkList;

	// 流量历史
	public String history() {
		linkList = new ArrayList<Link>();
		String target = EopSetting.IMG_SERVER_PATH
				+ EopContext.getContext().getContextPath() + "/access";
		File file = new File(target);
		if (file.exists()) {
			String[] reportList = file.list();
			for (String name : reportList) {
				Link link = new Link();
				link.setLink(EopSetting.IMG_SERVER_DOMAIN
						+ EopContext.getContext().getContextPath() + "/access/"
						+ name);
				link.setText(name);
				linkList.add(link);
			}
		}
		return "history";
	}

	public IAccessRecorder getAccessRecorder() {
		return accessRecorder;
	}

	public void setAccessRecorder(IAccessRecorder accessRecorder) {
		this.accessRecorder = accessRecorder;
	}

	public int getStartday() {
		return startday;
	}

	public void setStartday(int startday) {
		this.startday = startday;
	}

	public int getEndday() {
		return endday;
	}

	public void setEndday(int endday) {
		this.endday = endday;
	}

	public List<Link> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<Link> linkList) {
		this.linkList = linkList;
	}

	public String getDaytime() {
		return daytime;
	}

	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List getAccessList() {
		return accessList;
	}

	public void setAccessList(List accessList) {
		this.accessList = accessList;
	}

	public String getRunmodel() {
		return runmodel;
	}

	public void setRunmodel(String runmodel) {
		this.runmodel = runmodel;
	}

	public Map getsData() {
		return sData;
	}

	public void setsData(Map sData) {
		this.sData = sData;
	}

}

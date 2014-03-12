package com.enation.javashop.core.model;

import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 团购数规则
 * 
 * @author kingapex
 * 
 */
public class GroupBuyCount {
	private Integer ruleid;
	private int groupid;
	private int start_time;
	private int end_time;
	private int num;
	public final Integer getRuleid() {
		return ruleid;
	}
	public final void setRuleid(Integer ruleid) {
		this.ruleid = ruleid;
	}
	public final int getGroupid() {
		return groupid;
	}
	public final void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public final int getStart_time() {
		return start_time;
	}
	public final void setStart_time(int startTime) {
		start_time = startTime;
	}
	public final int getEnd_time() {
		return end_time;
	}
	public final void setEnd_time(int endTime) {
		end_time = endTime;
	}
	public final int getNum() {
		return num;
	}
	public final void setNum(int num) {
		this.num = num;
	}
 

}

package com.enation.framework.taglib;

import java.util.Date;

import javax.servlet.jsp.JspException;

import com.enation.framework.util.DateUtil;

public class DateFormatTaglib extends EnationTagSupport {
	private Long time;
	private String times;
	private String pattern;
	
	public int doEndTag() throws JspException {
		
//		if( time==null || time.equals("") ){
//			this.print("");
//			return this.EVAL_BODY_INCLUDE;
//		}
		
		time = times== null?time:Long.valueOf(times);
		Date date = new Date(time);
		String str  = DateUtil.toString(date, pattern);
		this.print(str);
		return this.EVAL_BODY_INCLUDE;
	}

	
	public int doStartTag() throws JspException {
		return this.EVAL_PAGE;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
	
}

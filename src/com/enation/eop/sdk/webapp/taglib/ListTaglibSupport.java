package com.enation.eop.sdk.webapp.taglib;

import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;

import com.enation.eop.sdk.webapp.taglib.BaseTaglibSupport;

public abstract class ListTaglibSupport extends BaseTaglibSupport {

	private boolean isFirst = true;

	protected String item;

	protected IListTaglibParam param;

	protected IListTaglibProvider tagProvider;

	private Iterator listIterator;

	private boolean hasNext = false;

	private int index;

	protected String postStart(){
		
		return "";
	}
	
	protected String postEnd(){
		
		return "";
	}
	
	protected String postStartOnce(){
		return "";
	}
	
	protected String postEndOnce() {
		return "";
	}
	
	public int doStartTag() throws JspException {
		
		this.print(this.postStartOnce());
		
		init();

		List list = tagProvider.getData(this.param, this.pageContext);
		listIterator = list.iterator();

		if (listIterator.hasNext()) {
			hasNext = true;
			this.setSope();
		}
		
		if (this.hasNext) {
			this.print(this.postStart());
			return EVAL_BODY_INCLUDE;
		} else {
			this.print(this.postEndOnce());
			return SKIP_BODY;
		}
	}

	public void init() {
		index = 0;
		hasNext = false;

		// 加载标签数据提供者
		loadProvider();

	}

	protected abstract void loadProvider();

	protected void setSope() {
		Object row = listIterator.next();
		pageContext.setAttribute("index", index);
		pageContext.setAttribute(item, row);
		pageContext.getRequest().setAttribute(item, row);
		index++;
	}

	public int doAfterBody() {
		
		this.print(this.postEnd()) ;
	
		if (listIterator.hasNext()) {
			this.print(this.postStart());
			this.setSope();
			return this.EVAL_BODY_AGAIN;
		} else {
			pageContext.removeAttribute(item);
			this.print(this.postEndOnce());
			return this.EVAL_PAGE;
		}
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}

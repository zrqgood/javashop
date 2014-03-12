package com.enation.eop.processor;
import com.enation.eop.processor.core.Request;

/**
 * 抽象的前台页面解析器<br/>
 * 包含有一个FacadePage的属性，解析器根据此属性的信息解析页面。
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 18:12:27
 */
public abstract class AbstractFacadePagetParser extends AbstractParser {

	protected FacadePage page; 


	/**
	 * 强迫调用者传递FacadePage属性
	 * @param page
	 */
	public AbstractFacadePagetParser(FacadePage page,Request request){
		 super(request);
		 this.page= page;
	}
	
	public FacadePage getPage(){
		return this.page;
	}
  

}
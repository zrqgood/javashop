package com.enation.eop.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.RequestWrapper;
import com.enation.eop.processor.core.Response;


/**
 * 抽像Request解析器<br/>
 * 执行Request ，并得到Response供给子类解析 <br/>
 * 子类需要实现 Response parse(Response response);方法。
 * <b>其返回的Response可被其它Request解析器(parser)再次解析
 * 或被其它Request 装饰器(wrapper)再次装饰
 * @author kingapex
 *
 */
public abstract class AbstractParser extends RequestWrapper {
	protected String uri;
	protected HttpServletResponse httpResponse;
	protected HttpServletRequest httpRequest;
	
	
	public AbstractParser(Request request) {
		super(request);
	}
	
 
	
	/**
	 * 覆写了父类的execute方法，从而预留了 parse(Response response)抽像方法给子类<br/>
	 * 同时将uri,request,response等参数保存为类属性以供子类使用<br/>
	 * 也起到了简化调用接口的作用
	 *  
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	
	public Response execute(String uri, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		this.uri = uri;
		this.httpRequest =httpRequest;
		this.httpResponse=httpResponse;
		return parse();
	}
	
	/**
	 * 将Requst接口的 exceute方法重塑为传递 Response的形式，帮助子类执行了Excecute方法，并构建了Response<br/>
	 * 因为解析器的任务为解析Resposne<br/>
	 *  
	 * @return
	 */
	protected  Response parse(){
		Response response  = super.execute(uri, httpResponse, httpRequest);
		return parse(response);
	}
	
	/**
	 * Reqeust解析器实现类需要覆写的方法<br/>
	 * 
	 * @param response Request执行后返回的响应
	 * @return
	 */
	protected abstract Response parse(Response response);

	
}

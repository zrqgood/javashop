package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.PrintTmpl;

public interface IPrintTmplManager {
	
	public List list();
	
	public List trash();
	
	/**
	 * 启用模板列表
	 * @return
	 */
	public List listCanUse();
	
	public void add(PrintTmpl printTmpl);
	
	public void edit(PrintTmpl printTmpl);
	
	public PrintTmpl get(int prt_tmpl_id);
	
	public void delete(Integer[] id);
	
	public void revert(Integer[] id);
	
	public void clean(Integer[] id);

}

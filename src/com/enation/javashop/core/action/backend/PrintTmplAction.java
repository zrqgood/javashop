package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.PrintTmpl;
import com.enation.javashop.core.service.IPrintTmplManager;

/**
 * 打印模板
 * 
 * @author lzf<br/>
 *         2010-5-4上午11:10:46<br/>
 *         version 1.0
 */
public class PrintTmplAction extends WWAction {
	
	private IPrintTmplManager printTmplManager;
	private List list;
	private List trash;
	private List listCanUse;
	private Integer[] id;
	private Integer prt_tmpl_id;
	private PrintTmpl printTmpl;
	
	public String list(){
		list = printTmplManager.list();
		return "list";
	}
	
	public String trash(){
		trash = printTmplManager.trash();
		return "trash";
	}
	
	public String listCanUse(){
		listCanUse = printTmplManager.listCanUse();
		return "listCanUse";
	}
	
	public String add(){
		return "add";
	}
	
	public String saveAdd(){
		try {
			printTmplManager.add(printTmpl);
			this.msgs.add("模板添加成功");
		} catch (Exception e) {
			this.msgs.add("模板添加失败");
			e.printStackTrace();
		}
		this.urls.put("打印模板列表", "printTmpl!list.do");
		return this.MESSAGE;
	}
	
	public String edit(){
		printTmpl = printTmplManager.get(prt_tmpl_id);
		return "edit";
	}
	
	public String saveEdit(){
		try {
			printTmplManager.edit(printTmpl);
			this.msgs.add("模板修改成功");
		} catch (Exception e) {
			this.msgs.add("模板修改失败");
			e.printStackTrace();
		}
		this.urls.put("打印模板列表", "printTmpl!list.do");
		return this.MESSAGE;
	}
	
	public String delete(){
		try {
			this.printTmplManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (Exception e) {
			this.json = "{'result':1;'message':'删除失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;
	}
	
	public String revert(){
		try {
			this.printTmplManager.revert(id);
			this.json = "{'result':0,'message':'还原成功'}";
		} catch (Exception e) {
			this.json = "{'result':1;'message':'还原失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;
	}
	
	public String clean(){
		try {
			this.printTmplManager.clean(id);
			this.json = "{'result':0,'message':'清除成功'}";
		} catch (Exception e) {
			this.json = "{'result':1;'message':'清除失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;
	}

	public IPrintTmplManager getPrintTmplManager() {
		return printTmplManager;
	}

	public void setPrintTmplManager(IPrintTmplManager printTmplManager) {
		this.printTmplManager = printTmplManager;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getTrash() {
		return trash;
	}

	public void setTrash(List trash) {
		this.trash = trash;
	}

	public List getListCanUse() {
		return listCanUse;
	}

	public void setListCanUse(List listCanUse) {
		this.listCanUse = listCanUse;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public Integer getPrt_tmpl_id() {
		return prt_tmpl_id;
	}

	public void setPrt_tmpl_id(Integer prtTmplId) {
		prt_tmpl_id = prtTmplId;
	}

	public PrintTmpl getPrintTmpl() {
		return printTmpl;
	}

	public void setPrintTmpl(PrintTmpl printTmpl) {
		this.printTmpl = printTmpl;
	}

}

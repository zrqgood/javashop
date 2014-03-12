package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.InvoiceApply;

/**
 * 索取发票管理
 * @author kingapex
 *
 */
public interface IInvoiceApplyManager {
	
	public void add(InvoiceApply invoiceApply);
	
	public void edit(InvoiceApply invoiceApply);
	
	public void updateState(Integer id,int state);
	
	public InvoiceApply get(Integer id);
	
	public void delete(Integer id);
	
	public Page list(int page ,int pageSize);
	
	public List listMember();
	
	public void refuse(Integer id,String reson);
}


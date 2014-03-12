package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.LimitBuy;

public interface ILimitBuyManager {
	
	public void add(LimitBuy limitBuy );
	public void edit(LimitBuy limitBuy );
	public Page list(int pageNo,int pageSize);
	public   List<LimitBuy> listEnable();
	public List<Map>  listEnableGoods();
	public void delete(Integer id);
	public LimitBuy get(Integer id);
	
}

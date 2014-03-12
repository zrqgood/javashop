package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Tag;

/**
 * 标签管理
 * @author kingapex
 * 2010-1-17下午01:03:41
 */
public interface ITagManager {
	
	/**
	 * 检测标签名是否同名
	 * @param name 标签名
	 * @param tagid 要排除的标签id,编辑时判断重名所用
	 * @return 存在重名返回真，不存在返回假
	 */
	public boolean checkname(String name,Integer tagid);
	
	
	/**
	 * 检测某些标签是否已经关联商品
	 * @param tagids 标签id数组
	 * @return 有关联返回真，否则返回假
	 */
	public boolean checkJoinGoods(Integer[] tagids);
	
	
	public Tag getById(Integer tagid);
	
	public void add(Tag tag);
	
	public void update(Tag tag);
	
	public void delete(Integer[] tagids);
	
	public Page list(int pageNo,int pageSize);
	
	public List<Tag> list();
	
	
	/**
	 * 读取某个引用的标签id集合
	 * @param relid
	 * @return
	 */
	public List<Integer> list(Integer relid); 
	
	
	
	
	/**
	 * 某个引用设置标签
	 * @param relid
	 * @param tagids
	 */
	public void saveRels(Integer relid,Integer[] tagids);
	
 
	
	
}

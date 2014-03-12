package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Comments;
import com.enation.javashop.core.model.support.CommentDTO;

/**
 * 评论/咨询接口
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-26 下午03:35:35<br/>
 *         version 1.0<br/>
 * <br/>
 */
public interface ICommentsManager {

	/**
	 * 取得评论、咨询，包含其子列表
	 * 
	 * @param comment_id
	 * @return CommentDTO,
	 * @see com.enation.javashop.core.model.support.CommentDTO
	 */
	public CommentDTO getComments(Integer comment_id);

	/**
	 * 新增评论、咨询或其回复
	 * 
	 * @param comments
	 */
	public void addComments(Comments comments);

	/**
	 * 修改评论、咨询或其回复，完成如[显示在页面中][删除]等操作
	 * 
	 * @param comments
	 */
	public void updateComments(Comments comments);

	/**
	 * 删除到回收站
	 * 
	 * @param ids
	 */
	public void deleteComments(String ids);

	/**
	 * 将回收站清空
	 * 
	 * @param ids
	 */
	public void cleanComments(String ids);

	/**
	 * 从回收站中还原
	 * 
	 * @param ids
	 */
	public void revert(String ids);

	/**
	 * 分页显示对应object_id的评论（或咨询），用于前台显示
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param object_id
	 * @param object_type
	 * @return
	 */
	public Page pageComments_Display(int pageNo, int pageSize,
			Integer object_id, String object_type);
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param object_id
	 * @param object_type
	 * @param commentType
	 * @return
	 */
	public Page pageComments_Display(int pageNo, int pageSize,
			Integer object_id, String object_type, String commentType);
	
	/**
	 * 用户中心-评论或咨询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page pageComments_Display(int pageNo, int pageSize);
	
	/**
	 * 列表某一会员的评论或咨询
	 * 具体类型（评论或咨询）由object_type指定
	 * @param member_id
	 * @param object_type
	 * @return
	 */
	public List listComments(int member_id, String object_type);
	
	/**
	 * 分页显示未被删除的评论（或咨询），不针对指定的object_id
	 * @param pageNo
	 * @param pageSize
	 * @param object_type
	 * @return
	 */
	public Page pageComments(int pageNo, int pageSize,	String object_type);
	
	/**
	 * 分页显示回收站内的评论（或咨询）
	 * @param pageNo
	 * @param pageSize
	 * @param object_type
	 * @return
	 */
	public Page pageCommentsTrash(int pageNo, int pageSize, String object_type);

	
	/**
	 * 计算某种对象的评价分数
	 * @param commentType goods:商品 
	 * @param objectid 商品id或其他对象id
	 * @return key:grade ,value: the grade's num<br>
	 *         return empty map when not have grade
	 */
	public Map coutObjectGrade(String commentType,Integer objectid);
	
	
	public Page listAll(int pageNo, int pageSize,
			Integer object_id, String commenttype);

	
	public Map coutObejctNum(String commentType, Integer objectid) ;
	
}

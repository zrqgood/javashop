package com.enation.javashop.core.model.support;

import java.util.List;

import com.enation.javashop.core.model.Comments;

/**
 * 评论、咨询，包含子列表(回复列表)
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-26 下午03:37:13<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class CommentDTO {
	private Comments comments;
	private List<Comments> list;

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public List<Comments> getList() {
		return list;
	}

	public void setList(List<Comments> list) {
		this.list = list;
	}

}

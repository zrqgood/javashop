package com.enation.javashop.core.action.backend;

import java.util.Date;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.PromotionActivity;
import com.enation.javashop.core.service.IPromotionActivityManager;

/**
 * 促销活动
 * 
 * @author lzf<br/>
 *         2010-4-20下午05:34:37<br/>
 *         version 1.0
 */
public class ActivityAction extends WWAction {

	private Date begin_time;
	private Date end_time;
	private Integer[] id;
	private PromotionActivity activity;
	private IPromotionActivityManager promotionActivityManager;
	private int activity_id;
	
	public String list(){
		this.webpage = promotionActivityManager.list(this.getPage(), this.getPageSize());
		return "list";
	}
	
	public String add(){
		return "add";
	}
	
	public String edit(){
		activity = promotionActivityManager.get(activity_id);
		return "edit";
	}

	public String saveAdd() {
		activity.setBegin_time(begin_time.getTime());
		activity.setEnd_time(end_time.getTime());
		try {
			promotionActivityManager.add(activity);
			this.msgs.add("活动添加成功");
		} catch (Exception e) {
			this.msgs.add("活动添加失败");
			e.printStackTrace();
		}
		this.urls.put("促销活动列表", "activity!list.do");
		return this.MESSAGE;
	}

	public String saveEdit() {
		activity.setBegin_time(begin_time.getTime());
		activity.setEnd_time(end_time.getTime());
		try {
			promotionActivityManager.edit(activity);
			this.msgs.add("活动修改成功");
		} catch (Exception e) {
			this.msgs.add("活动修改失败");
			e.printStackTrace();
		}
		this.urls.put("促销活动列表", "activity!list.do");
		return this.MESSAGE;
	}

	public String delete() {
		try {
			this.promotionActivityManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (Exception e) {
			this.json = "{'result':1;'message':'删除失败'}";
			e.printStackTrace();
		}
		return this.JSON_MESSAGE;
	}

	public PromotionActivity getActivity() {
		return activity;
	}

	public void setActivity(PromotionActivity activity) {
		this.activity = activity;
	}

	public IPromotionActivityManager getPromotionActivityManager() {
		return promotionActivityManager;
	}

	public void setPromotionActivityManager(
			IPromotionActivityManager promotionActivityManager) {
		this.promotionActivityManager = promotionActivityManager;
	}

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activityId) {
		activity_id = activityId;
	}

}

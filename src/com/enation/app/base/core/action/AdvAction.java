package com.enation.app.base.core.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.model.Adv;
import com.enation.app.base.core.service.IAdColumnManager;
import com.enation.app.base.core.service.IAdvManager;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.FileUtil;

/**
 * @author lzf 2010-3-2 上午09:46:16 version 1.0
 */
public class AdvAction extends WWAction {

	private IAdColumnManager adColumnManager;
	private IAdvManager advManager;
	private List<AdColumn> adColumnList;
	private Adv adv;
	private Long acid;
	private String advname; //搜索的广告名
	private Long advid;
	private String id;
	private File pic;
	private String picFileName;
	private Date mstartdate;
	private Date menddate;
	private String order;

	public String list() {
		adColumnList = this.adColumnManager.listAllAdvPos();
		this.webpage = advManager.search(acid, advname, this.getPage(), this.getPageSize(),this.order);
		return "list";
	}

	public String detail() {
		adv = this.advManager.getAdvDetail(advid);
		return "detail";
	}

	public String click() {
		if (advid == 0) {
			this.getRequest().setAttribute("gourl", "/eop/shop/adv/zhaozu.jsp");
		} else {
			adv = this.advManager.getAdvDetail(advid);
			// 避免同一客户重复点击导致点击数被重复计算
			// 以客户的session做为依据。同一session生命期内只计一次对某一广告的点击数
			if (this.getRequest().getSession().getAttribute(
					"AD" + advid.toString()) == null) {
				this.getRequest().getSession().setAttribute(
						"AD" + advid.toString(), "clicked");
				adv.setClickcount(adv.getClickcount() + 1);
				this.advManager.updateAdv(adv);
			}
			// 不管此session生命期内是否已经计过点击数，点击后的页面还是要转的
			this.getRequest().setAttribute("gourl", adv.getUrl());
		}
		return "go";
	}

	public String delete() {
		try {
			this.advManager.delAdvs(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public String add() {
		adColumnList = this.adColumnManager.listAllAdvPos();
		return "add";
	}

	public String addSave() {
		if (pic != null) {

			if (FileUtil.isAllowUp(picFileName)) {
				String path = UploadUtil.upload(this.pic,this.picFileName, "adv");
				adv.setAtturl(path);
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp,swf格式文件。");
				return this.MESSAGE;
			}
		}
		adv.setBegintime(mstartdate.getTime());
		adv.setEndtime(menddate.getTime());
		adv.setDisabled("false");
		try {
			this.advManager.addAdv(adv);
			this.msgs.add("新增广告成功");
			this.urls.put("广告列表", "adv!list.do");
		} catch (RuntimeException e) {
			this.msgs.add("新增广告失败");
			this.urls.put("广告列表", "adv!list.do");
		}
		return this.MESSAGE;
	}

	public String edit() {
		adColumnList = this.adColumnManager.listAllAdvPos();
		adv = this.advManager.getAdvDetail(advid);
		return "edit";
	}

	public String editSave() {
		if (pic != null) {
			if (FileUtil.isAllowUp(picFileName)) {
				String path = UploadUtil.upload(this.pic,this.picFileName, "adv");
				adv.setAtturl(path);
			} else {
				this.msgs.add("不允许上传的文件格式，请上传gif,jpg,bmp,swf格式文件。");
				return this.MESSAGE;
			}
		}
		adv.setBegintime(mstartdate.getTime());
		adv.setEndtime(menddate.getTime());
		this.advManager.updateAdv(adv);
		this.msgs.add("修改广告成功");
		this.urls.put("广告列表", "adv!list.do");
		return this.MESSAGE;
	}

	public String stop() {
		adv = this.advManager.getAdvDetail(advid);
		adv.setIsclose(1);
		try {
			this.advManager.updateAdv(adv);
			this.json = "{'result':0,'message':'操作成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'操作失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public String start() {
		adv = this.advManager.getAdvDetail(advid);
		adv.setIsclose(0);
		try {
			this.advManager.updateAdv(adv);
			this.json = "{'result':0,'message':'操作成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'操作失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public IAdColumnManager getAdColumnManager() {
		return adColumnManager;
	}

	public void setAdColumnManager(IAdColumnManager adColumnManager) {
		this.adColumnManager = adColumnManager;
	}

	public IAdvManager getAdvManager() {
		return advManager;
	}

	public void setAdvManager(IAdvManager advManager) {
		this.advManager = advManager;
	}

	public Adv getAdv() {
		return adv;
	}

	public void setAdv(Adv adv) {
		this.adv = adv;
	}

	public Long getAcid() {
		return acid;
	}

	public void setAcid(Long acid) {
		this.acid = acid;
	}

	public Long getAdvid() {
		return advid;
	}

	public void setAdvid(Long advid) {
		this.advid = advid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AdColumn> getAdColumnList() {
		return adColumnList;
	}

	public void setAdColumnList(List<AdColumn> adColumnList) {
		this.adColumnList = adColumnList;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public Date getMstartdate() {
		return mstartdate;
	}

	public void setMstartdate(Date mstartdate) {
		this.mstartdate = mstartdate;
	}

	public Date getMenddate() {
		return menddate;
	}

	public void setMenddate(Date menddate) {
		this.menddate = menddate;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAdvname() {
		return advname;
	}

	public void setAdvname(String advname) {
		this.advname = advname;
	}

}

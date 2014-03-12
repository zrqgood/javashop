package com.enation.app.base.core.action;

import java.io.File;
import java.util.List;

import com.enation.app.base.core.model.MultiSite;
import com.enation.app.base.core.service.IMultiSiteManager;
import com.enation.app.base.core.service.solution.IProductService;
import com.enation.app.base.core.service.solution.ISolutionExporter;
import com.enation.app.base.core.service.solution.ISolutionImporter;
import com.enation.eop.resource.IDomainManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopSiteDomain;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserContext;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.eop.sdk.utils.ValidCodeServlet;
import com.enation.framework.action.WWAction;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-9 上午10:14:56
 *         </p>
 * @version 1.0
 */
public class UserSiteAction extends WWAction {

	private ISiteManager siteManager;

	private IDomainManager domainManager;
	private EopSite eopSite;
	private Integer id;
	private Integer domainid;
	private Integer statusid;
	private File cologo;
	private String cologoFileName;
	private File ico;
	private String icoFileName;
	private File bklogo;
	private String bklogoFileName;
	private File bkloginpic;
	private String bkloginpicFileName;
	private List<EopSiteDomain> siteDomainList;
	private EopSiteDomain eopSiteDomain;
	private String sitedomain;
	private IUserManager userManager;

	private ISolutionImporter solutionImporter;
	private ISolutionExporter solutionExporter;

	/*
	 * ============================= 以下为导入导出的变量 =============================
	 */
	// 1是导 0是不导
	private int exportData;
	private int exportTheme;
	private int exportProfile;
	private int exportAttr;
	private String name;
	private File zip;
	private String zipFileName;

	private Integer siteid;
	private String vcode;

	/*
	 * ============================= 导入导出 =============================
	 */
	public String toExport() {
		return "export";
	}

	public String toImport() {
		return "import";
	}

	/**
	 * 备份当前站点
	 * 
	 * @return
	 */
	public String backup() {
		try {
			name = "backup_" + System.currentTimeMillis();
			this.solutionExporter.export(name, true, true, true, true);
			this.json = "{result:1,name:'" + name + "'}";
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.json = "{result:0}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 还原
	 * 
	 * @return
	 */
	public String restore() {
		try {
			String productid = "temp_" + System.currentTimeMillis();

			String zipPath = EopSetting.IMG_SERVER_PATH
					+ EopContext.getContext().getContextPath() + "/backup/"
					+ name + ".zip";

			this.solutionImporter.imported(productid, zipPath, true);
			this.json = "{result:1}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{result:0}";
		}
		return this.JSON_MESSAGE;

	}

	public String export() {
		try {
			this.solutionExporter.export(name, exportData == 1,
					exportTheme == 1, exportProfile == 1, exportAttr == 1);
			this.json = "{result:1,path:'" + EopSetting.IMG_SERVER_DOMAIN
					+ EopContext.getContext().getContextPath() + "/backup/"
					+ name + ".zip" + "'}";
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
		}
		return this.JSON_MESSAGE;
	}

	public String imported() {
		try {
			String productid = "temp_" + System.currentTimeMillis();

			String zipPath = UploadUtil.upload(zip, zipFileName, "solution");
			zipPath = zipPath.replaceAll(EopSetting.FILE_STORE_PREFIX,
					EopSetting.IMG_SERVER_PATH
							+ EopContext.getContext().getContextPath());
			this.solutionImporter.imported(productid, zipPath, true);
			this.json = "{result:1}";

		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{result:0}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 显示初始页面
	 * 
	 * @return
	 */
	public String toInitData() {

		return "init_data";
	}

	/**
	 * 初始化为出厂设置
	 * 
	 * @return
	 */
	public String initData() {
		try {
			// 校验验证码
			WebSessionContext<UserContext> sessonContext = ThreadContextHolder
					.getSessionContext();
			Object realCode = sessonContext
					.getAttribute(ValidCodeServlet.SESSION_VALID_CODE
							+ "initdata");

			if (!vcode.equals(realCode)) {
				this.json = "{result:0,message:'验证码输入错误'}";
				return this.JSON_MESSAGE;
			}
			siteManager.initData();
			this.json = "{result:1}";
		} catch (RuntimeException e) {
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
		}
		return this.JSON_MESSAGE;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public EopSiteDomain getEopSiteDomain() {
		return eopSiteDomain;
	}

	public void setEopSiteDomain(EopSiteDomain eopSiteDomain) {
		this.eopSiteDomain = eopSiteDomain;
	}

	public List<EopSiteDomain> getSiteDomainList() {
		return siteDomainList;
	}

	public void setSiteDomainList(List<EopSiteDomain> siteDomainList) {
		this.siteDomainList = siteDomainList;
	}

	public File getIco() {
		return ico;
	}

	public void setIco(File ico) {
		this.ico = ico;
	}

	public String getIcoFileName() {
		return icoFileName;
	}

	public void setIcoFileName(String icoFileName) {
		this.icoFileName = icoFileName;
	}

	public String getCologoFileName() {
		return cologoFileName;
	}

	public void setCologoFileName(String cologoFileName) {
		this.cologoFileName = cologoFileName;
	}

	public File getCologo() {
		return cologo;
	}

	public void setCologo(File cologo) {
		this.cologo = cologo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EopSite getEopSite() {
		return eopSite;
	}

	public void setEopSite(EopSite eopSite) {
		this.eopSite = eopSite;
	}

	private String order;
	private String search;

	public String execute() {
		this.webpage = siteManager.list(this.getPage(), this.getPageSize(),
				order, search);
		return SUCCESS;
	}

	public String add() {
		return "add";
	}

	public String edit() {
		IUserService userService = UserServiceFactory.getUserService();
		eopSite = siteManager.get(userService.getCurrentSiteId());
		// siteDomainList =domainManager.listSiteDomain();
		return "edit";
	}

	public String edit_multi() {
		IUserService userService = UserServiceFactory.getUserService();
		eopSite = siteManager.get(userService.getCurrentSiteId());
		return "edit_multi";
	}

	public String domainlist() {
		siteDomainList = domainManager.listSiteDomain();
		return "domainlist";
	}

	public String domain() {
		IUserService userService = UserServiceFactory.getUserService();
		eopSite = siteManager.get(userService.getCurrentSiteId());
		siteDomainList = domainManager.listSiteDomain();
		return "domain";
	}

	public String deleteDomain() {
		try {
			this.siteManager.deleteDomain(domainid);
			this.json = "{result:1,message:'删除成功'}";
		} catch (RuntimeException e) {
			this.logger.error(e.getMessage(), e);
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
		}
		return this.JSON_MESSAGE;
	}

	public String editdomain() throws Exception {
		if (statusid == 0) {
			statusid = 1;
		} else {
			statusid = 0;
		}

		eopSiteDomain = new EopSiteDomain();
		eopSiteDomain.setStatus(statusid);
		eopSiteDomain.setId(domainid);

		try {
			this.domainManager.edit(eopSiteDomain);
			this.json = "{result:1,message:'修改成功'}";
		} catch (RuntimeException e) {
			this.logger.error(e.getStackTrace());
			this.json = "{result:0,message:'修改失败'}";
		}

		return this.JSON_MESSAGE;
	}

	/*
	 * public String addSave() throws Exception {
	 * 
	 * if (cologo != null) {
	 * 
	 * String logo = UploadUtil.upload(cologo, cologoFileName, "user");
	 * eopSite.setLogofile(logo);
	 * 
	 * }
	 * 
	 * if (ico != null) { String icoPath = UploadUtil.upload(ico, icoFileName,
	 * "user"); eopSite.setIcofile(icoPath);
	 * 
	 * } SiteDTO siteDTO = new SiteDTO(); eopSite.setThemeid(1);
	 * eopSite.setAdminthemeid(1); siteDTO.setSite(eopSite);
	 * 
	 * eopSiteDomain = new EopSiteDomain(); eopSiteDomain.setDomain(sitedomain);
	 * siteDTO.setDomain(eopSiteDomain);
	 * 
	 * EopSiteAdmin siteAdmin = new EopSiteAdmin();
	 * siteAdmin.setManagerid(managerid); siteDTO.setSiteAdmin(siteAdmin);
	 * 
	 * siteDTO.setUserId(userid); Integer result = siteManager.add(siteDTO);
	 * 
	 * // installService.setJdbcTemplate(this.jdbcTemplate); // lzy add
	 * //installService.install(userid, result); productService.install(userid,
	 * result, "base");
	 * 
	 * if (result > 0) { this.setMessage("新建站点成功！"); } else {
	 * this.setMessage("新建站点失败，请重试！"); } this.setBackUrl("userSite.do"); return
	 * FORM_RETURN_HTMLMESSAGE; }
	 */

	public String editSave() throws Exception {
		if (cologo != null) {
			String logo = UploadUtil.upload(cologo, cologoFileName, "user");
			eopSite.setLogofile(logo);
		}

		if (ico != null) {
			String icoPath = UploadUtil.upload(ico, icoFileName, "user");
			eopSite.setIcofile(icoPath);
		}

		if (bklogo != null) {
			String logo = UploadUtil.upload(bklogo, bklogoFileName, "user");
			eopSite.setBklogofile(logo);
		}

		if (bkloginpic != null) {
			String loginpic = UploadUtil.upload(bkloginpic, bkloginpicFileName,
					"user");
			eopSite.setBkloginpicfile(loginpic);
		}

		eopSite.setQq(eopSite.getQq() == null ? 0 : eopSite.getQq());
		eopSite.setMsn(eopSite.getMsn() == null ? 0 : eopSite.getMsn());
		eopSite.setWw(eopSite.getWw() == null ? 0 : eopSite.getWw());
		eopSite.setTel(eopSite.getTel() == null ? 0 : eopSite.getTel());
		eopSite.setWt(eopSite.getWt() == null ? 0 : eopSite.getWt());

		siteManager.edit(eopSite);

		this.msgs.add("修改成功");
		this.urls.put("我的站点", "userSite!edit.do");

		return this.MESSAGE;
	}

	public String delete() throws Exception {
		try {
			siteManager.delete(id);
			this.msgs.add("删除成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.msgs.add(e.getMessage());
		}
		this.urls.put("站点列表", "userSite.do");
		return this.MESSAGE;
	}

	public String adddomain() throws Exception {
		return "adddomain";
	}

	public String addDomainSave() throws Exception {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		eopSiteDomain = new EopSiteDomain();
		eopSiteDomain.setUserid(userid);
		eopSiteDomain.setDomain(sitedomain);
		eopSiteDomain.setSiteid(siteid);
		int result = -1;
		try {
			result = siteManager.addDomain(eopSiteDomain);
		} catch (RuntimeException e) {
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
			return this.JSON_MESSAGE;
		}

		if (result > 0) {
			this.json = "{result:1,message:'增加成功'}";
		} else {
			this.json = "{result:0,message:'新增域名失败'}";

		}
		return JSON_MESSAGE;
	}

	private Integer managerid;

	public String changeDefaultSite() throws Exception {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		userManager.changeDefaultSite(userid, managerid, id);

		return this.MESSAGE;
	}

	public Integer getDomainid() {
		return domainid;
	}

	public void setDomainid(Integer domainid) {
		this.domainid = domainid;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getSitedomain() {
		return sitedomain;
	}

	public void setSitedomain(String sitedomain) {
		this.sitedomain = sitedomain;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public IDomainManager getDomainManager() {
		return domainManager;
	}

	public void setDomainManager(IDomainManager domainManager) {
		this.domainManager = domainManager;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

	public File getBklogo() {
		return bklogo;
	}

	public void setBklogo(File bklogo) {
		this.bklogo = bklogo;
	}

	public String getBklogoFileName() {
		return bklogoFileName;
	}

	public void setBklogoFileName(String bklogoFileName) {
		this.bklogoFileName = bklogoFileName;
	}

	public File getBkloginpic() {
		return bkloginpic;
	}

	public void setBkloginpic(File bkloginpic) {
		this.bkloginpic = bkloginpic;
	}

	public String getBkloginpicFileName() {
		return bkloginpicFileName;
	}

	public void setBkloginpicFileName(String bkloginpicFileName) {
		this.bkloginpicFileName = bkloginpicFileName;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public int getExportData() {
		return exportData;
	}

	public void setExportData(int exportData) {
		this.exportData = exportData;
	}

	public int getExportTheme() {
		return exportTheme;
	}

	public void setExportTheme(int exportTheme) {
		this.exportTheme = exportTheme;
	}

	public int getExportProfile() {
		return exportProfile;
	}

	public void setExportProfile(int exportProfile) {
		this.exportProfile = exportProfile;
	}

	public int getExportAttr() {
		return exportAttr;
	}

	public void setExportAttr(int exportAttr) {
		this.exportAttr = exportAttr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getZip() {
		return zip;
	}

	public void setZip(File zip) {
		this.zip = zip;
	}

	public String getZipFileName() {
		return zipFileName;
	}

	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

	public ISolutionImporter getSolutionImporter() {
		return solutionImporter;
	}

	public void setSolutionImporter(ISolutionImporter solutionImporter) {
		this.solutionImporter = solutionImporter;
	}

	public ISolutionExporter getSolutionExporter() {
		return solutionExporter;
	}

	public void setSolutionExporter(ISolutionExporter solutionExporter) {
		this.solutionExporter = solutionExporter;
	}

}

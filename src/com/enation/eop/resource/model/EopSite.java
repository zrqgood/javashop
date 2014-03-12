package com.enation.eop.resource.model;

import java.io.Serializable;

import freemarker.template.utility.StringUtil;


/**
 * @author lzf
 *         <p>
 *         created_time 2009-11-27 下午01:40:20
 *         </p>
 * @version 1.0
 */
public class EopSite implements Serializable{
 
	private static final long serialVersionUID = 7525130003L;
	private Integer id;
	private Integer userid;
	//SEO
	private String sitename;
	private String title;
	private String keywords;
	private String descript;
	//end SEO
	//user
	private String username;
	private String usertel;
	private String usermobile;
	private String usertel1;
	private Integer usersex;
	private String useremail;
	//user end
	
	//在线客服
	private Integer state;//开启客服,0:on default 0
	private String qqlist;
	private String msnlist;
	private String wwlist;
	private String tellist;
	private String worktime;
	private Integer qq;
	private Integer msn;
	private Integer ww;
	private Integer tel;
	private Integer wt;
	//end of 在线客服
	
	//其它
	private Integer siteon;//网站是否开启,0:on default
	private String closereson;
	private String copyright;
	private String icp;
	private String address;
	private String zipcode;
	private String linkman;
	private String linktel;
	private String email;
	//end 其它
	
	private String productid;
	private Integer themeid;
	private String themepath;
	
	private Integer adminthemeid;
	private String icofile;
	private String logofile;
	
 
	private long createtime;
	private long lastlogin;
	private long lastgetpoint;
 
	private String bklogofile;
	private String bkloginpicfile;
 
	private int logincount;
	private int point ; //站点积分
	
	private String relid;
	private int sitestate;
	private  int isimported;
	private int imptype ;
	
	private Integer multi_site;//是否开启多站点 0:否,1:是 default 0
 
	
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Integer getThemeid() {
		return themeid;
	}

	public void setThemeid(Integer themeid) {
		this.themeid = themeid;
	}

	public Integer getAdminthemeid() {
		return adminthemeid;
	}

	public void setAdminthemeid(Integer adminthemeid) {
		this.adminthemeid = adminthemeid;
	}

	public String getIcofile() {
		return icofile;
	}

	public void setIcofile(String icofile) {
		this.icofile = icofile;
	}

	public String getLogofile() {
		return logofile;
	}

	public void setLogofile(String logofile) {
		this.logofile = logofile;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}


	public String getThemepath() {
		return themepath;
	}

	public void setThemepath(String themepath) {
		this.themepath = themepath;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	public String getBklogofile() {
		return bklogofile;
	}

	public void setBklogofile(String bklogofile) {
		this.bklogofile = bklogofile;
	}

	public String getBkloginpicfile() {
		return bkloginpicfile;
	}

	public void setBkloginpicfile(String bkloginpicfile) {
		this.bkloginpicfile = bkloginpicfile;
	}

	public EopSite clone() {  
		EopSite site = new EopSite();
		site.setAdminthemeid(adminthemeid);
		site.setDescript(descript);
		site.setIcofile(icofile);
		site.setLogofile(logofile);
		site.setId(this.id);
		site.setKeywords(keywords);
		site.setPoint(point);
		site.setProductid(productid);
		site.setThemeid(themeid);
		site.setThemepath(themepath);
		site.setSitename(sitename);
		site.setUserid(userid);
		
		return site;
		
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	
	public long getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(long lastlogin) {
		this.lastlogin = lastlogin;
	}

	public long getLastgetpoint() {
		return lastgetpoint;
	}

	public void setLastgetpoint(long lastgetpoint) {
		this.lastgetpoint = lastgetpoint;
	}

	public static void main(String[] args){
		System.out.println( System.currentTimeMillis() /1000  );
	}

	public int getLogincount() {
		return logincount;
	}

	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUsermobile() {
		return usermobile;
	}

	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}

	public String getUsertel1() {
		return usertel1;
	}

	public void setUsertel1(String usertel1) {
		this.usertel1 = usertel1;
	}

	public Integer getUsersex() {
		return usersex;
	}

	public void setUsersex(Integer usersex) {
		this.usersex = usersex;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSiteon() {
		return siteon;
	}

	public void setSiteon(Integer siteon) {
		this.siteon = siteon;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getQqlist() {
		return qqlist;
	}

	public void setQqlist(String qqlist) {
		this.qqlist = qqlist;
	}

	public String getMsnlist() {
		return msnlist;
	}

	public void setMsnlist(String msnlist) {
		this.msnlist = msnlist;
	}

	public String getWwlist() {
		return wwlist;
	}

	public void setWwlist(String wwlist) {
		this.wwlist = wwlist;
	}

	public String getTellist() {
		return tellist;
	}

	public void setTellist(String tellist) {
		this.tellist = tellist;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public Integer getMsn() {
		return msn;
	}

	public void setMsn(Integer msn) {
		this.msn = msn;
	}

	public Integer getWw() {
		return ww;
	}

	public void setWw(Integer ww) {
		this.ww = ww;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public Integer getWt() {
		return wt;
	}

	public void setWt(Integer wt) {
		this.wt = wt;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinktel() {
		return linktel;
	}

	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClosereson() {
		return closereson;
	}

	public void setClosereson(String closereson) {
		this.closereson = closereson;
	}

	public Integer getMulti_site() {
		multi_site =multi_site==null?multi_site=0:multi_site;
		return multi_site;
	}

	public void setMulti_site(Integer multiSite) {
		multi_site = multiSite;
	}

	public String getRelid() {
		return relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public int getSitestate() {
		return sitestate;
	}

	public void setSitestate(int sitestate) {
		this.sitestate = sitestate;
	}

	public int getIsimported() {
		return isimported;
	}

	public void setIsimported(int isimported) {
		this.isimported = isimported;
	}

	public int getImptype() {
		return imptype;
	}

	public void setImptype(int imptype) {
		this.imptype = imptype;
	}

 
	
}
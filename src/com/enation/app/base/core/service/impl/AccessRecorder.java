package com.enation.app.base.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.service.IAccessRecorder;
import com.enation.eop.resource.model.Access;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.ip.IPSeeker;

/**
 * 访问记录器
 * 
 * @author kingapex 2010-7-23下午03:47:25
 */
public class AccessRecorder extends BaseSupport implements IAccessRecorder {

	/**
	 * 每次都从session中得到上一次的访问，如果不为空则计算停留时间，并记录上一次访问信息<br/>
	 * 存在问题：无法记录最后一次访问
	 */
	public int record(ThemeUri themeUri) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();

		Access access = new Access();
		access.setAccess_time((int) (System.currentTimeMillis() / 1000));
		access.setIp(request.getRemoteAddr());
		access.setPage(themeUri.getPagename());
		access.setUrl(RequestUtil.getRequestUrl(request));
		access.setPoint(themeUri.getPoint());
		access.setArea(new IPSeeker().getCountry(access.getIp()));

		Member member = UserServiceFactory.getUserService().getCurrentMember();
		if (member != null)
			access.setMembername(member.getUname());

		Access last_access = (Access) ThreadContextHolder.getSessionContext()
				.getAttribute("user_access");
		if (last_access != null) {
			int stay_time = access.getAccess_time()
					- last_access.getAccess_time();
			last_access.setStay_time(stay_time);
			int last = (int) (System.currentTimeMillis() / 1000 - 3600); // 上一个小时的秒数
			String sql = "select count(0)  from access where ip=? and url=? and access_time>=?";
			// 记录一个小小时内不重复的ip
			int count = this.baseDaoSupport.queryForInt(sql,
					last_access.getIp(), last_access.getUrl(), last);
			if (count == 0) {
				EopSite site = EopContext.getContext().getCurrentSite();
				int point = site.getPoint();
				if (point == -1 || site.getIsimported() == 1) {// -1的点数表示不限制积分只记录访问记录
					this.baseDaoSupport.insert("access", last_access);
					return 1;
				}
				if (point > access.getPoint()) {
					// 更新当前站点各积分
					this.daoSupport.execute(
							"update eop_site set point=point-? where id=?",
							last_access.getPoint(), site.getId());
					this.baseDaoSupport.insert("access", last_access);
					site.setPoint(site.getPoint() - last_access.getPoint());
				} else {
					return 0;
				}

			}

		}
		ThreadContextHolder.getSessionContext().setAttribute("user_access",
				access);
		return 1;

	}

	public static void main(String args[]) {
		// int now = (int) (System.currentTimeMillis()/1000);
		//
		// int stime = (int) ( now - 3600 *24 *30 );
		// Calendar cal=Calendar.getInstance();
		// cal.setTime(new Date());
		// int year=cal.get(Calendar.YEAR);
		// System.out.println(year);

		int todaystart = (int) (DateUtil.toDate(
				DateUtil.toString(new Date(), "yyyy-MM-dd 00:00"),
				"yyyy-MM-dd mm:ss").getTime() / 1000);
		System.out.println((int) (DateUtil.toDate("2010-11-01 00:00",
				"yyyy-MM-dd mm:ss").getTime() / 1000));
		System.out.println(todaystart);
		System.out.println(System.currentTimeMillis() / 1000);
	}

	public Page list(String starttime, String endtime, int pageNo, int pageSize) {

		// 默认的结束时间为当前
		int now = (int) (System.currentTimeMillis() / 1000);

		// 默认开始时间为当前时间的前30天
		int stime = (int) (now - 3600 * 24 * 30);

		// 用户输入了开始时间，则以输入的时间为准
		if (starttime != null) {
			stime = (int) (DateUtil.toDate(starttime, "yyyy-MM-dd").getTime() / 1000);
		}

		// 用户输入了结束时间，则以输入的时间为准
		if (endtime != null) {
			now = (int) (DateUtil.toDate(endtime, "yyyy-MM-dd").getTime() / 1000);
		}

		String sql = "select access_time,max(membername) mname,floor(access_time/86400) daytime,count(0) count,sum(stay_time) sum_stay_time,max(access_time) maxtime,min(access_time) mintime,sum(point) point from access where access_time>=? and access_time<=? group by ip,floor(access_time/86400),access_time order by access_time desc";
		sql = baseDaoSupport.buildPageSql(sql, pageNo, pageSize);

		List list = baseDaoSupport.queryForList(sql, stime, now);
		sql = "select count(0) from (select access_time from access where access_time>=? and access_time<=? group by ip, floor(access_time/86400)) tb";
		
		int count = this.baseDaoSupport.queryForInt(sql, stime, now);
		Page page = new Page(0, count, pageSize, list);

		return page;
	}

	/**
	 * 读取某个ip，某天的详细流量
	 * 
	 * @param ip
	 * @param daytime
	 * @return
	 */
	public List detaillist(String ip, String daytime) {
		String sql = "select * from access where ip=? and floor(access_time/86400)=? order by access_time asc ";
		return this.baseDaoSupport.queryForList(sql, ip, daytime);
	}

	public void export() {

		// 读取所有站点信息
		String sql = "select * from eop_site ";
		List<Map> list = this.daoSupport.queryForList(sql);

		// 为每个用户开启一个线程导出上个月的流量数据
		for (Map map : list) {
			AccessExporter accessExporter = SpringContextHolder
					.getBean("accessExporter");
			accessExporter.setContext(
					Integer.valueOf(map.get("userid").toString()),
					Integer.valueOf(map.get("id").toString()));
			Thread thread = new Thread(accessExporter);
			thread.start();
		}

	}

	public Map<String, Long> census() {

		/** 日流量及日积分累计 **/
		// 今天开始秒数
		int todaystart = (int) (DateUtil.toDate(
				DateUtil.toString(new Date(), "yyyy-MM-dd 00:00"),
				"yyyy-MM-dd mm:ss").getTime() / 1000);
		// 今天 结束秒数
		int todayend = (int) (System.currentTimeMillis() / 1000);

		// 日访问量
		String sql = "select count(0) from access where access_time>=?  and access_time<=?";
		long todayaccess = this.baseDaoSupport.queryForLong(sql, todaystart,
				todayend);

		// 日累计消耗积分
		sql = "select sum(point) from access where access_time>=?  and access_time<=?";
		long todaypoint = this.baseDaoSupport.queryForLong(sql, todaystart,
				todayend);

		/** 月流量及月积分累计 **/
		String[] currentMonth = DateUtil.getCurrentMonth(); // 得到本月第一天和最后一天的字串数组
		int monthstart = (int) (DateUtil.toDate(currentMonth[0], "yyyy-MM-dd")
				.getTime() / 1000); // 本月第一天的秒数
		int monthend = (int) (DateUtil.toDate(currentMonth[1], "yyyy-MM-dd")
				.getTime() / 1000); // 本月最后一天的秒数

		// 月访问量
		sql = "select count(0) from access where access_time>=? and access_time<=? order by access_time";
		long monthaccess = this.baseDaoSupport.queryForLong(sql, monthstart,
				monthend);

		// 月消耗积分累计
		sql = "select sum(point) from access where access_time>=? and access_time<=? order by access_time";
		long monthpoint = this.baseDaoSupport.queryForLong(sql, monthstart,
				monthend);

		/** 年流量及年积分累计 **/

		// 查询历史（从开始至上个月）
		sql = "select sumpoint,sumaccess from eop_site where id=?";
		List<Map> list = this.daoSupport.queryForList(sql, EopContext
				.getContext().getCurrentSite().getId());
		if (list.isEmpty() || list == null || list.size() == 0) {
			throw new RuntimeException("站点["
					+ EopContext.getContext().getCurrentSite().getId() + "]不存在");
		}
		Map siteData = list.get(0);
		long sumaccess = Long.valueOf("" + siteData.get("sumaccess"));
		long sumpoint = Long.valueOf("" + siteData.get("sumpoint"));

		// 累加本月
		sumaccess += monthaccess;
		sumpoint += monthpoint;

		/** 压入统计数据map **/
		Map<String, Long> sData = new HashMap<String, Long>();
		sData.put("todayaccess", todayaccess); // 日访问量
		sData.put("todaypoint", todaypoint); // 日消费积分
		sData.put("monthaccess", monthaccess); // 月访问量
		sData.put("monthpoint", monthpoint); // 月消费积分
		sData.put("sumaccess", sumaccess); // 年访问量
		sData.put("sumpoint", sumpoint); // 年消费积分
		return sData;
	}

}

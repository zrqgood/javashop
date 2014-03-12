package com.enation.javashop.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.model.Coupons;
import com.enation.javashop.core.model.MemberCoupon;
import com.enation.javashop.core.model.PointHistory;
import com.enation.javashop.core.service.IMemberCouponsManager;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IPointHistoryManager;

public class MemberCouponsManager extends BaseSupport implements
		IMemberCouponsManager {

	private ISettingService settingService;
	private IMemberManager memberManager;
	private IPointHistoryManager pointHistoryManager;

	
	public Page pageMemberCoupons(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String str_mc_use_times = settingService.getSetting("coupons",
				"coupon.mc.use_times");
		int mc_use_times = str_mc_use_times == null ? 1 : Integer
				.valueOf(str_mc_use_times);
		String sql = "select * from " + this.getTableName("member_coupon")
				+ " as mc left join " + this.getTableName("coupons")
				+ " as c on c.cpns_id=mc.cpns_id  left join "
				+ this.getTableName("promotion")
				+ " as p on c.pmt_id=p.pmt_id";
		sql += " where member_id=?";
		sql += " order by mc.memc_gen_time desc";
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize, member
				.getMember_id());
		List<Map> list = (List<Map>) (page.getResult());
		for (Map map : list) {
			if (((String) map.get("cpns_status")).equals("1")) {
				if (this.isLevelAllowuse(((Integer) map.get("pmt_id"))
						.intValue(), member.getLv_id())) {
					Long curTime = (new Date()).getTime();
					if (((Long) map.get("pmt_time_begin")).longValue() <= curTime
							&& curTime < ((Long) map.get("pmt_time_end"))
									.longValue()) {
						if (((Integer) map.get("memc_used_times")).intValue() < mc_use_times) {
							if (((String) map.get("memc_enabled"))
									.equals("true")) {
								map.put("status", "可使用");
							} else {
								map.put("status", "本优惠券已作废");
							}
						} else {
							map.put("status", "本优惠券次数已用完");
						}
					} else {
						map.put("status", "还未开始或已过期");
					}
				} else {
					map.put("status", "本级别不准使用");
				}
			} else {
				map.put("status", "此种优惠券已取消");
			}
		}
		return page;
	}

	
	public Page pageExchangeCoupons(int pageNo, int pageSize) {
		Long curTime = (new Date()).getTime();
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select *,c.pmt_id as pmt_id from "
				+ this.getTableName("coupons")
				+ " as c left join "
				+ this.getTableName("promotion")
				+ " as p on c.pmt_id=p.pmt_id";
		sql += " where cpns_type='1' and cpns_point is not null";
		sql += " and cpns_status='1' AND pmt_time_begin <= " + curTime
		+ " and pmt_time_end >" + curTime;
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize);
		List<Map> list = (List<Map>) (page.getResult());
		for (Map map : list) {
			if (this.isLevelAllowuse(((Integer) map.get("pmt_id")).intValue(),
					member.getLv_id())) {
				map.put("use_status", 1);
			} else {
				map.put("use_status", 0);
			}
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.enation.javashop.core.service.IMemberCouponsManager#exchange(int)
	 */
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void exchange(int cpns_id) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select cpns_point from coupons where cpns_status='1' and cpns_type='1' and cpns_point is not null and cpns_id=?";
		int point = this.baseDaoSupport.queryForInt(sql, cpns_id);
		if (member.getPoint() >= point) {
			member.setPoint(member.getPoint() - point);// 改变session中的值
			memberManager.edit(member);
			this.generateCoupon(cpns_id, member.getMember_id());
			//加入积分变化日志
			PointHistory pointHistory = new PointHistory();
			pointHistory.setMember_id(member.getMember_id());
			pointHistory.setPoint(0-point);
			pointHistory.setReason("exchange_coupon");
			pointHistory.setTime((new Date()).getTime());
			pointHistory.setType(3);
			pointHistoryManager.addPointHistory(pointHistory);
		} else {
			throw new RuntimeException("积分扣除超过会员已有积分");
		}

	}

	/**
	 * 判断该用户所处的会员级别是否在可兑换的范围内
	 * 
	 * @param pmt_id
	 * @param lv_id
	 * @return
	 */
	private boolean isLevelAllowuse(int pmt_id, int lv_id) {
		int count = this.baseDaoSupport
				.queryForInt(
						"select count(lv_id) from pmt_member_lv where pmt_id = ? and lv_id = ?",
						pmt_id, lv_id);
		return (count > 0);
	}

	/**
	 * 生成优惠卡号
	 * 
	 * @param num
	 * @param prefix
	 * @param member_id
	 * @return
	 */
	private String makeCouponCode(int num, String prefix, int member_id) {
		return prefix + DateUtil.toString(new Date(), "yyyyMMddHHmmss")
				+ member_id + num;
	}

	/**
	 * 生成优惠卡
	 * 
	 * @param cpns_id
	 * @param member_id
	 */
	private void generateCoupon(int cpns_id, int member_id) {
		Long curTime = (new Date()).getTime();
		String sql = "select * from "
				+ this.getTableName("coupons")
				+ " as c left join "
				+ this.getTableName("promotion")
				+ " as p on c.pmt_id=p.pmt_id";
	 
				sql += " where cpns_status='1' and cpns_type='1' and c.cpns_id="
				+ cpns_id + " and pmt_time_begin <= " + curTime
				+ " and pmt_time_end >" + curTime;
		List<Map> list = (List) this.daoSupport.queryForList(sql);
		if (list != null && list.size() > 0) {
			Map map = list.get(0);
			String couponCode = this.makeCouponCode(((Long) map
					.get("cpns_gen_quantity")).intValue() + 1, (String) map
					.get("cpns_prefix"), member_id);
			MemberCoupon memberCoupon = new MemberCoupon();
			memberCoupon.setMemc_code(couponCode);
			memberCoupon.setCpns_id(cpns_id);
			memberCoupon.setMember_id(member_id);
			memberCoupon.setMemc_gen_time(curTime);
			this.baseDaoSupport.insert("member_coupon", memberCoupon);
			Coupons coupons = (Coupons) this.baseDaoSupport.queryForObject(
					"select * from coupons where cpns_id = ?", Coupons.class,
					cpns_id);
			coupons.setCpns_gen_quantity(coupons.getCpns_gen_quantity() + 1);
			this.baseDaoSupport.update("coupons", coupons, "cpns_id = "
					+ cpns_id);
		}
	}

	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public IPointHistoryManager getPointHistoryManager() {
		return pointHistoryManager;
	}

	public void setPointHistoryManager(IPointHistoryManager pointHistoryManager) {
		this.pointHistoryManager = pointHistoryManager;
	}

}

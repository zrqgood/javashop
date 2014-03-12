package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.MemberAddress;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.IMemberAddressManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 会员中心-收货地址
 * 
 * @author lzf<br/>
 *         2010-3-17 下午06:39:16<br/>
 *         version 1.0<br/>
 */
public class MemberAddressWidget extends AbstractMemberWidget {

	private IMemberAddressManager memberAddressManager;
	private IRegionsManager regionsManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		 
		action = action == null ? "" : action;
		if (action.equals("")) {
			this.setPageName("myaddress");
			List addressList = memberAddressManager.listAddress();
			addressList = addressList == null ? new ArrayList() : addressList;
			this.putData("addressList", addressList);
		} else if (action.equals("edit")) {
			this.setPageName("myaddress_edit");
			String addr_id = request.getParameter("addr_id");
			MemberAddress address = memberAddressManager.getAddress(Integer
					.valueOf(addr_id));
			List provinceList = this.regionsManager.listProvince();
			List cityList = this.regionsManager.listCity(address
					.getProvince_id());
			cityList = cityList == null ? new ArrayList() : cityList;
			List regionList = this.regionsManager.listRegion(address
					.getCity_id());
			regionList = regionList == null ? new ArrayList() : regionList;
			this.putData("address", address);
			this.putData("provinceList", provinceList);
			this.putData("cityList", cityList);
			this.putData("regionList", regionList);
		} else if (action.equals("editsave")) {
			String addr_id = request.getParameter("address.addr_id");
			MemberAddress address = memberAddressManager.getAddress(Integer
					.valueOf(addr_id));

			String def_addr = request.getParameter("address.def_addr");
			address.setDef_addr(Integer.valueOf(def_addr));

			String name = request.getParameter("address.name");
			address.setName(name);

			String tel = request.getParameter("address.tel");
			address.setTel(tel);

			String mobile = request.getParameter("address.mobile");
			address.setMobile(mobile);

			String province_id = request.getParameter("address.province_id");
			address.setProvince_id(Integer.valueOf(province_id));

			String city_id = request.getParameter("address.city_id");
			address.setCity_id(Integer.valueOf(city_id));

			String region_id = request.getParameter("address.region_id");
			address.setRegion_id(Integer.valueOf(region_id));

			String province = request.getParameter("address.province");
			address.setProvince(province);

			String city = request.getParameter("address.city");
			address.setCity(city);

			String region = request.getParameter("address.region");
			address.setRegion(region);

			String addr = request.getParameter("address.addr");
			address.setAddr(addr);

			String zip = request.getParameter("address.zip");
			address.setZip(zip);
			try{
				memberAddressManager.updateAddress(address);
				this.showSuccess("修改成功","收货地址", "member_address.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("修改失败", "收货地址", "member_address.html");
			}
		} else if (action.equals("add")) {
			this.setPageName("myaddress_add");
			List provinceList = this.regionsManager.listProvince();
			this.putData("provinceList", provinceList);
		} else if (action.equals("addsave")) {
			MemberAddress address = new MemberAddress();

			String def_addr = request.getParameter("address.def_addr");
			address.setDef_addr(Integer.valueOf(def_addr));

			String name = request.getParameter("address.name");
			address.setName(name);

			String tel = request.getParameter("address.tel");
			address.setTel(tel);

			String mobile = request.getParameter("address.mobile");
			address.setMobile(mobile);

			String province_id = request.getParameter("address.province_id");
			address.setProvince_id(Integer.valueOf(province_id));

			String city_id = request.getParameter("address.city_id");
			address.setCity_id(Integer.valueOf(city_id));

			String region_id = request.getParameter("address.region_id");
			address.setRegion_id(Integer.valueOf(region_id));

			String province = request.getParameter("address.province");
			address.setProvince(province);

			String city = request.getParameter("address.city");
			address.setCity(city);

			String region = request.getParameter("address.region");
			address.setRegion(region);

			String addr = request.getParameter("address.addr");
			address.setAddr(addr);

			String zip = request.getParameter("address.zip");
			address.setZip(zip);
			try{
				memberAddressManager.addAddress(address);
				this.showSuccess("添加成功","收货地址", "member_address.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("添加失败", "收货地址", "member_address.html");
			}
		} else if (action.equals("delete")) {
			String addr_id = request.getParameter("addr_id");
			try{
				memberAddressManager.deleteAddress(Integer.valueOf(addr_id));
				this.showSuccess("删除成功","收货地址", "member_address.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("删除失败", "收货地址", "member_address.html");
			}
		}
	}

	public IMemberAddressManager getMemberAddressManager() {
		return memberAddressManager;
	}

	public void setMemberAddressManager(
			IMemberAddressManager memberAddressManager) {
		this.memberAddressManager = memberAddressManager;
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}

}

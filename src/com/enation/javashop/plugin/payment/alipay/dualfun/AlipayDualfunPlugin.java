package com.enation.javashop.plugin.payment.alipay.dualfun;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.config.AlipayConfig;
import com.alipay.services.AlipayService;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.payment.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.payment.IPaymentEvent;

/**
 * 支付宝标准双接口
 * 
 * @author kingapex
 * 
 */
public class AlipayDualfunPlugin extends AbstractPaymentPlugin implements
		IPaymentEvent {

	@Override
	public String onPay(PayCfg payCfg, Order order) {
		Map<String, String> params = paymentManager.getConfigParams(this
				.getId());
		String seller_email = params.get("seller_email");
		String partner = params.get("partner");
		String key = params.get("key");

		String show_url = this.getShowUrl(order);
		String notify_url = this.getCallBackUrl(payCfg);
		String return_url = this.getReturnUrl(payCfg);

		// //////////////////////////////////请求参数//////////////////////////////////////

		// 必填参数//

		// 请与贵网站订单系统中的唯一订单号匹配
		String out_trade_no = "" + order.getSn();
		//订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
		String subject = "订单:" + order.getSn(); 
		//订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
		String body = "网店订单"; 
		String price = String.valueOf( order.getOrder_amount() );
		// 物流费用，即运费。
		String logistics_fee = "0.00";
		// 物流类型，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
		String logistics_type = "EXPRESS";
		// 物流支付方式，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
		String logistics_payment = "SELLER_PAY";

		// 商品数量，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品。
		String quantity = "1";

		// 扩展参数//

		// 买家收货信息（推荐作为必填）
		// 该功能作用在于买家已经在商户网站的下单流程中填过一次收货信息，而不需要买家在支付宝的付款流程中再次填写收货信息。
		// 若要使用该功能，请至少保证receive_name、receive_address有值
		String receive_name	= order.getShip_name();			//收货人姓名，如：张三
		String receive_address = order.getShip_addr();		//收货人地址，如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号
		String receive_zip = order.getShip_zip();				//收货人邮编，如：123456
		String receive_phone = order.getShip_tel();		//收货人电话号码，如：0571-81234567
		String receive_mobile =  order.getShip_email();		//收货人手机号码，如：13312341234
 

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("body", body);
		sParaTemp.put("price", price);
		sParaTemp.put("logistics_fee", logistics_fee);
		sParaTemp.put("logistics_type", logistics_type);
		sParaTemp.put("logistics_payment", logistics_payment);
		sParaTemp.put("quantity", quantity);
		sParaTemp.put("receive_name", receive_name);
		sParaTemp.put("receive_address", receive_address);
		sParaTemp.put("receive_zip", receive_zip);
		sParaTemp.put("receive_phone", receive_phone);
		sParaTemp.put("receive_mobile", receive_mobile);

        sParaTemp.put("service", "trade_create_by_buyer");
        sParaTemp.put("partner", partner);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("seller_email",seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        
		return AlipaySubmit.buildForm(sParaTemp, AlipayService.ALIPAY_GATEWAY_NEW, "get", key);
	}

	@Override
	public String onCallBack() {
		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		Map<String,String> configparams = this.getConfigParams();
		String partner = configparams.get("partner");  //partner合作伙伴id（必须填写）
		String key =  configparams.get("key"); //partner 的对应交易安全校验码（必须填写）
		String encoding= configparams.get("callback_encoding");
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			if(!StringUtil.isEmpty(encoding)){
				valueStr = StringUtil.to(valueStr, encoding);
			}
			params.put(name, valueStr);
		}


		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		String trade_no = request.getParameter("trade_no");				//支付宝交易号
		String order_no = request.getParameter("out_trade_no");	        //获取订单号
		String total_fee = request.getParameter("price");		        //获取总金额



		String subject =  request.getParameter("subject");//商品名称、订单名称
		if(!StringUtil.isEmpty(encoding)){
			subject= StringUtil.to(subject, encoding);
		}
		String body = "";
		if(request.getParameter("body") != null){
			body =  request.getParameter("body");//商品描述、订单备注、描述
			if(!StringUtil.isEmpty(encoding)){
				body=StringUtil.to(body, encoding);
			}
		}
		String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
		String receive_name = "";//收货人姓名
		if(request.getParameter("receive_name") != null){
			receive_name = request.getParameter("receive_name");
			if(!StringUtil.isEmpty(encoding)){
				receive_name=StringUtil.to(receive_name, encoding);
			}
		}
		String receive_address = "";//收货人地址
		if(request.getParameter("receive_address") != null){
			receive_address = request.getParameter("receive_address");
			if(!StringUtil.isEmpty(encoding)){
				receive_address=StringUtil.to(receive_address, encoding);
			}
		}
		
		String receive_zip = "";//收货人邮编
		if(request.getParameter("receive_zip") != null){
			receive_zip = request.getParameter("receive_zip");
			if(!StringUtil.isEmpty(encoding)){
				receive_zip=StringUtil.to(receive_zip, encoding);
			}
		}
		
		
		String receive_phone = "";//收货人电话
		if(request.getParameter("receive_phone") != null){
			receive_phone = request.getParameter("receive_phone");
			if(!StringUtil.isEmpty(encoding)){
				receive_phone=StringUtil.to(receive_phone, encoding);
			}
		}
		
		
		String receive_mobile = "";//收货人手机
		if(request.getParameter("receive_mobile") != null){
			receive_mobile = request.getParameter("receive_mobile");
			if(!StringUtil.isEmpty(encoding)){
				receive_mobile=StringUtil.to(receive_mobile, encoding);
			}
		}
		
		
		
		String trade_status = request.getParameter("trade_status");		//交易状态
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.callbackverify(params,key,partner)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			this.paySuccess(order_no);
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			this.logger.info("异步校验订单["+order_no+"]成功");
			if(trade_status.equals("WAIT_BUYER_PAY")){
				//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				
					//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					return ("success");	//请不要修改或删除
				} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
				//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
				
					//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					return("success");	//请不要修改或删除
				} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
				//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
				
					//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					return("success");	//请不要修改或删除
				} else if(trade_status.equals("TRADE_FINISHED")){
				//该判断表示买家已经确认收货，这笔交易完成
				
					//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					return("success");	//请不要修改或删除
				}
				else {
					return("success");	//请不要修改或删除
				}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return("fail");
		}
	}

	@Override
	public String onReturn() {
		
		Map<String,String> cfgparams = paymentManager.getConfigParams(this.getId());
		 HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		 this.logger.info("返回来的body"+request.getParameter("body"));
			String key =cfgparams.get("key");
			String partner =cfgparams.get("partner");
			String encoding= cfgparams.get("return_encoding");
			
			
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			if(!StringUtil.isEmpty(encoding)){
				valueStr = StringUtil.to(valueStr, encoding);
			}
			params.put(name, valueStr);
		}
		
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

		String trade_no = request.getParameter("trade_no");				//支付宝交易号
		String order_no = request.getParameter("out_trade_no");	        //获取订单号
		String total_fee = request.getParameter("total_fee");  		      	//获取总金额
		

		String subject =  request.getParameter("subject");//商品名称、订单名称
		if(!StringUtil.isEmpty(encoding)){
			subject= StringUtil.to(subject, encoding);
		}
		String body = "";
		if(request.getParameter("body") != null){
			body =  request.getParameter("body");//商品描述、订单备注、描述
			if(!StringUtil.isEmpty(encoding)){
				body=StringUtil.to(body, encoding);
			}
		}
		String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
		String receive_name = "";//收货人姓名
		if(request.getParameter("receive_name") != null){
			receive_name = request.getParameter("receive_name");
			if(!StringUtil.isEmpty(encoding)){
				receive_name=StringUtil.to(receive_name, encoding);
			}
		}
		String receive_address = "";//收货人地址
		if(request.getParameter("receive_address") != null){
			receive_address = request.getParameter("receive_address");
			if(!StringUtil.isEmpty(encoding)){
				receive_address=StringUtil.to(receive_address, encoding);
			}
		}
		
		String receive_zip = "";//收货人邮编
		if(request.getParameter("receive_zip") != null){
			receive_zip = request.getParameter("receive_zip");
			if(!StringUtil.isEmpty(encoding)){
				receive_zip=StringUtil.to(receive_zip, encoding);
			}
		}
		
		
		String receive_phone = "";//收货人电话
		if(request.getParameter("receive_phone") != null){
			receive_phone = request.getParameter("receive_phone");
			if(!StringUtil.isEmpty(encoding)){
				receive_phone=StringUtil.to(receive_phone, encoding);
			}
		}
		
		
		String receive_mobile = "";//收货人手机
		if(request.getParameter("receive_mobile") != null){
			receive_mobile = request.getParameter("receive_mobile");
			if(!StringUtil.isEmpty(encoding)){
				receive_mobile=StringUtil.to(receive_mobile, encoding);
			}
		}
		
		
		String trade_status = request.getParameter("trade_status");		//交易状态
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.returnverify(params,key,partner);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
				//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
			}
		
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
			}
			
			this.paySuccess(order_no);
			return order_no;
			
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			throw new RuntimeException("验证失败");  
		}
		
	}

	@Override
	public String getType() {

		return "payment";
	}

	@Override
	public String getId() {
		return "alipayDualfunPlugin";
	}

	@Override
	public String getName() {
		return "支付宝标准双接口";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "kingapex";
	}

	@Override
	public void perform(Object... params) {

	}

	@Override
	public void register() {

	}

}

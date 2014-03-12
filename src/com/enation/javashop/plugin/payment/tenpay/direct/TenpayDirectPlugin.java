package com.enation.javashop.plugin.payment.tenpay.direct;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.payment.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.payment.IPaymentEvent;
import com.tenpay.util.TenpayUtil;
import com.tenpay.PayRequestHandler;
import com.tenpay.PayResponseHandler;

public class TenpayDirectPlugin extends AbstractPaymentPlugin implements IPaymentEvent {

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String onCallBack() {
		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		HttpServletResponse response  = ThreadContextHolder.getHttpResponse();
		
		Map<String,String> params = this.getConfigParams();
		
		String key = params.get("tenpaykey");	//"8934e7d15453e97507ef794cf7b0519d";

		//创建PayResponseHandler实例
		PayResponseHandler resHandler = new PayResponseHandler(request, response);

		resHandler.setKey(key);

		//判断签名
		if(resHandler.isTenpaySign()) {
			//交易单号
			String transaction_id = resHandler.getParameter("transaction_id");
			
			//金额金额,以分为单位
			String total_fee = resHandler.getParameter("total_fee");
			
			//支付结果
			String pay_result = resHandler.getParameter("pay_result");
			
			if( "0".equals(pay_result) ) {
				//------------------------------
				//处理业务开始
				//------------------------------ 
				
				//注意交易单不要重复处理
				//注意判断返回金额
				
				//------------------------------
				//处理业务完毕
				//------------------------------
					
				//调用doShow, 打印meta值跟js代码,告诉财付通处理成功,并在用户浏览器显示$show页面.
				String strHtml = "<html><head>\r\n" +
				"<meta name=\"TENCENT_ONLINE_PAYMENT\" content=\"China TENCENT\">\r\n" +
				"</head><body></body></html>";
				return strHtml;
			} else {
				//当做不成功处理
				System.out.println("支付失败");
			}
			
		} else {
			System.out.println("认证签名失败");
			//String debugInfo = resHandler.getDebugInfo();
			//System.out.println("debugInfo:" + debugInfo);
		}

		return null;
	}

	@Override
	public String onPay(PayCfg payCfg, Order order) {
		Map<String,String> params = paymentManager.getConfigParams(this.getId());

		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		HttpServletResponse response = ThreadContextHolder.getHttpResponse();

		/*
		String show_url = this.getShowUrl(order);
		String notify_url = this.getCallBackUrl(payCfg);
		String return_url =this.getReturnUrl(payCfg);
		*/
		
		////////////////////////////////////请求参数//////////////////////////////////////

		//商户号
		String bargainor_id = params.get("tenpaybid");

		//密钥
		String key = params.get("tenpaykey");

		//回调通知URL
		String return_url = this.getCallBackUrl(payCfg);

		//当前时间 yyyyMMddHHmmss
		String currTime = TenpayUtil.getCurrTime();

		//8位日期
		String strDate = currTime.substring(0, 8);

		//6位时间
		String strTime = currTime.substring(8, currTime.length());

		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";

		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;

		//商家订单号,长度若超过32位，取前32位。财付通只记录商家订单号，不保证唯一。
		String sp_billno = strReq;

		//财付通交易单号，规则为：10位商户号+8位时间（YYYYmmdd)+10位流水号
		String transaction_id = bargainor_id + strDate + strReq;

		//创建PayRequestHandler实例
		PayRequestHandler reqHandler = new PayRequestHandler(request, response);

		//设置密钥
		reqHandler.setKey(key);

		//初始化
		reqHandler.init();

		//-----------------------------
		//设置支付参数
		//-----------------------------
		reqHandler.setParameter("cs", "utf-8");							//编码类型
		reqHandler.setParameter("bargainor_id", bargainor_id);			//商户号		
		reqHandler.setParameter("sp_billno", order.getSn());			//商家订单号
		reqHandler.setParameter("transaction_id", transaction_id);		//财付通交易单号
		reqHandler.setParameter("return_url", return_url);				//支付通知url
		reqHandler.setParameter("desc", "订单编号：" + order.getSn());					//商品名称
		reqHandler.setParameter("total_fee", formatPrice( order.getOrder_amount()*100 ));	//商品金额,以分为单位

		//用户ip,测试环境时不要加这个ip参数，正式环境再加此参数
		reqHandler.setParameter("spbill_create_ip",request.getRemoteAddr());

		//获取请求带参数的url
		String requestUrl = "";
		try {
			requestUrl = reqHandler.getRequestURL();
			reqHandler.doSend();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//获取debug信息
		String debuginfo = reqHandler.getDebugInfo();

		return requestUrl;
	}

	@Override
	public String onReturn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "Liuzy";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "tenpayDirectPlugin";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "财付通即时到账接口";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "payment";
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}

	@Override
	public void perform(Object... params) {
		// TODO Auto-generated method stub
		
	}

}

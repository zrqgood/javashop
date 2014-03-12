var OrderStatus={};

//订单状态
OrderStatus.ORDER_CANCEL_SHIP = -2; // 退货
OrderStatus.ORDER_CANCEL_PAY = -1; // 退款
OrderStatus.ORDER_NOT_PAY = 0; // 未付款
OrderStatus.ORDER_PAY = 1; // 已支付
OrderStatus.ORDER_SHIP = 2; // 已发货
OrderStatus.ORDER_COMPLETE = 3; // 已完成
OrderStatus.ORDER_CANCELLATION = 4; // 作废

//付款状态
OrderStatus.PAY_NO= 0;  
OrderStatus.PAY_YES= 1;
OrderStatus.PAY_CANCEL =2; //已经退款
OrderStatus.PAY_PARTIAL_REFUND = 3; //部分退款
OrderStatus.PAY_PARTIAL_PAYED =4 ;//部分付款

//货运状态
OrderStatus.SHIP_NO= 0;  //	0未发货
OrderStatus.SHIP_YES= 1;//	1已发货
OrderStatus.SHIP_CANCEL= 2;//	2.已退货
OrderStatus.SHIP_PARTIAL_SHIPED= 4; //	4 部分发货
OrderStatus.SHIP_PARTIAL_CANCEL= 3;// 3 部分退货		
OrderStatus.SHIP_PARTIAL_CHANGE= 5;// 5部分换货	
OrderStatus.SHIP_CHANED= 6;// 6已换货	


var OrderDetail ={
		orderid : undefined,
		orderStatus:undefined,
		payStatus:undefined,
		shipStatus:undefined,
		init:function(orderid,orderStatus,payStatus,shipStatus){
	
			//初始化订单的状态
			this.orderStatus = orderStatus;
			this.payStatus = payStatus;
			this.shipStatus = shipStatus;
			
			Eop.Dialog.init({id:"order_dialog",modal:false,title:"订单操作",width:"750px"});
			
			var self = this; 
			this.orderid = orderid;
			new Tab(".order_detail");
			this.bindFlowEvent();
			this.bindTabEvent();			
			self.showBase();
			
			
			
		},
		/**
		 * 绑定tab事件
		 */
		bindTabEvent:function(){
			var self = this;
			$("#base").click(function(){ self.showBase(); });
			$("#items").click(function(){ self.showItems(); });
			$("#payLog").click(function(){ self.showPayLog(); });
			$("#shipLog").click(function(){ self.showShipLog();   });
			$("#pmt").click(function(){ self.showPmt();   });
			$("#log").click(function(){ self.showLog();  });
			$("#remark").click(function(){ self.showRemark();  });
		},
		
		/**
		 * 绑定订单流程按钮事件
		 */
		bindFlowEvent:function(){
			var self =this;
			
			//付款事件绑定
			$("#pay").unbind("click");
			$("#pay").bind("click",function(){
				Eop.Dialog.open("order_dialog");
				$("#order_dialog .con").load(basePath+"payment!showPayDialog.do?ajax=yes&orderId="+self.orderid,function(){
					$("#order_dialog .submitBtn").unbind("click");
					$("#order_dialog .submitBtn").bind("click",function(){
						self.pay();
					});
				}); 
			});
			
			//退款事件绑定
			$("#refund").unbind("click");
			$("#refund").bind("click",function(){
				Eop.Dialog.open("order_dialog");
				$("#order_dialog .con").load(basePath+"payment!showRefundDialog.do?ajax=yes&orderId="+self.orderid,function(){
					$("#order_dialog .submitBtn").unbind("click");
					$("#order_dialog .submitBtn").bind("click",function(){
						self.refund();
					});
				}); 
			});

			//发货事件绑定
			$("#shipping").unbind("click");
			$("#shipping").bind("click",function(){
				Eop.Dialog.open("order_dialog");
				$("#order_dialog .con").load(basePath+"ship!showShipDialog.do?ajax=yes&orderId="+self.orderid,function(){
					initCity();
					$("#order_dialog .submitBtn").unbind("click");
					$("#order_dialog .submitBtn").bind("click",function(){
						 self.ship();
					});
				}); 
			});			
			
			//退货事件绑定
			$("#returned").unbind("click");
			$("#returned").bind("click",function(){
				Eop.Dialog.open("order_dialog");
				$("#order_dialog .con").load(basePath+"ship!showReturnDialog.do?ajax=yes&orderId="+self.orderid,function(){
					initCity();
					$("#order_dialog .submitBtn").unbind("click");
					$("#order_dialog .submitBtn").bind("click",function(){
						 self.returned();
					});
				}); 
			});	
			
			//换货事件绑定
			$("#changed").unbind("click");
			$("#changed").bind("click",function(){
				Eop.Dialog.open("order_dialog");
				$("#order_dialog .con").load(basePath+"ship!showChangeDialog.do?ajax=yes&orderId="+self.orderid,function(){
					initCity();
					$("#order_dialog .submitBtn").unbind("click");
					$("#order_dialog .submitBtn").bind("click",function(){
						 self.changed();
					});
				}); 
			});	
						
			

			//完成事件绑定
			$("#complete").unbind("click");
			$("#complete").bind("click",function(){
				if(confirm("完成 操作会使该订单归档且不允许再做任何操作，确定要执行吗？")){
					self.complete(self.orderid);
				}
			});	
			
			//作废事件绑定
			$("#cancel").unbind("click");
			$("#cancel").bind("click",function(){
				if(confirm("作废操作会使该订单归档且不允许再做任何操作，确定要执行吗？")){
					self.cancel(self.orderid);
				}
			});	
			
			this.initBtnStatus();
		},
		/**
		 * 初始化按钮状态
		 */
		initBtnStatus:function(){
			
			//未支付或部分支付使支付按钮可用，其它情况都禁用支付按钮
			if( this.payStatus == OrderStatus.PAY_NO ||this.payStatus == OrderStatus.PAY_PARTIAL_PAYED ){
				$("#pay").attr("disabled",false); 
			}else{
				$("#pay").attr("disabled",true); 
			}
			
			//已支付 或 部分已支付 或部分已退款退款按钮可用
			//其它状态都禁用(未支付、已退款)
			if(   this.payStatus == OrderStatus.PAY_YES 
				||this.payStatus == OrderStatus.PAY_PARTIAL_REFUND
				||this.payStatus == OrderStatus.PAY_PARTIAL_PAYED
			   )
			{ 
				$("#refund").attr("disabled",false); 
			}else
			{
				$("#refund").attr("disabled",true); 
			}
			
			//未发货或部分已发货发货按钮可用，其它情况（已发货、部分退货、已退货）发货按钮禁用
			if( this.shipStatus == OrderStatus.SHIP_NO || this.shipStatus == OrderStatus.SHIP_PARTIAL_SHIPED){ 
				$("#shipping").attr("disabled",false); 
			}else{
				$("#shipping").attr("disabled",true); 
			}
			
			//已发货 或 部分已发货 或部分已退货 退货按钮可用
			//其它状态都禁用(未发货、已换货)	禁用按钮		
			if(  this.shipStatus == OrderStatus.SHIP_YES || this.shipStatus == OrderStatus.SHIP_PARTIAL_SHIPED || this.shipStatus == OrderStatus.SHIP_PARTIAL_CANCEL){ 
				$("#returned").attr("disabled",false); 
			}else{
				$("#returned").attr("disabled",true); 
			}
			
			//已发货 或 部分已发货 或部分已退货 换货按钮可用
			//其它状态都禁用(未发货、已退货,已换货)	禁用按钮		
			if(  this.shipStatus == OrderStatus.SHIP_YES || this.shipStatus == OrderStatus.SHIP_PARTIAL_SHIPED || this.shipStatus == OrderStatus.SHIP_PARTIAL_CANCEL){ 
				$("#changed").attr("disabled",false); 
			}else{
				$("#changed").attr("disabled",true); 
			}
			
						
			
			//订单状态为完成或作废，则禁用 所有钮
			if(this.orderStatus == OrderStatus.ORDER_COMPLETE ||this.orderStatus == OrderStatus.ORDER_CANCELLATION ){
				$(".toolbar input").attr("disabled",true);  
			}else{
				$("#cancel").attr("disabled",false);  
				$("#complete").attr("disabled",false);  				
			}
		}
		,
		
		
		/**
		 * 支付
		 */
		pay:function(){
			var self= this;
			var options = {
					url:basePath+"payment!pay.do?ajax=yes",
					type:"post",
					dataType:"json",
					success: function(responseText) { 
						if(responseText.result==1){
							alert(responseText.message);
							Eop.Dialog.close("order_dialog");
							self.payStatus = responseText.payStatus;
							self.bindFlowEvent();
						}
						if(responseText.result==0){
							alert(responseText.message);
						}						
					},
					error:function(){
						alert("出错了:(");
					}
			};
			$('#order_form').ajaxSubmit(options); 
		},
		
		/**
		 * 退款
		 */
		refund:function(){
			var self= this;
			var options = {
					url:basePath+"payment!cancel_pay.do?ajax=yes",
					type:"post",
					dataType:"json",
					success: function(responseText) { 
						if(responseText.result==1){
							alert(responseText.message);
							Eop.Dialog.close("order_dialog");
							self.payStatus = responseText.payStatus;
							self.bindFlowEvent();
						}
						if(responseText.result==0){
							alert(responseText.message);
						}						
					},
					error:function(){
						alert("出错了:(");
					}
			};
			$('#order_form').ajaxSubmit(options); 
		},
		/**
		 * 发货
		 */
		ship:function(){
			var self= this;
			var options = {
					url:basePath+"ship!ship.do?ajax=yes",
					type:"post",
					dataType:"json",
					success: function(responseText) { 
						if(responseText.result==1){
							alert(responseText.message);
							Eop.Dialog.close("order_dialog");
							self.shipStatus = responseText.shipStatus;
							self.bindFlowEvent();
						}
						if(responseText.result==0){
							alert(responseText.message);
						}						
					},
					error:function(){
						alert("出错了:(");
					}
			};
			$('#order_form').ajaxSubmit(options); 
		},
		/**
		 * 退货
		 */
		returned:function(){
			var flag =true;
			$("input[name=numArray]").each(function(i,v){
				if($.trim( v.value )==''){
					flag =false;
				}else{
					if(!isdigit(v.value) ){
						flag=false;
					}else if(parseInt(v.value)<0){
						flag=false;
					}
				}
				
				
			});
			
			if(!flag){
				alert("请输入正确的退货数量");
				return;
			}
			
			var self= this;
			var options = {
					url:basePath+"ship!returned.do?ajax=yes",
					type:"post",
					dataType:"json",
					success: function(responseText) { 
						if(responseText.result==1){
							alert(responseText.message);
							Eop.Dialog.close("order_dialog");
							self.shipStatus = responseText.shipStatus;
							self.bindFlowEvent();
						}
						if(responseText.result==0){
							alert(responseText.message);
						}						
					},
					error:function(){
						alert("出错了:(");
					}
			};
			$('#order_form').ajaxSubmit(options); 
		},
		/**
		 * 换货
		 */
		changed:function(){
			var self= this;
			var options = {
					url:basePath+"ship!change.do?ajax=yes",
					type:"post",
					dataType:"json",
					success: function(responseText) { 
						if(responseText.result==1){
							alert(responseText.message);
							Eop.Dialog.close("order_dialog");
							self.shipStatus = responseText.shipStatus;
							self.bindFlowEvent();
						}
						if(responseText.result==0){
							alert(responseText.message);
						}						
					},
					error:function(){
						alert("出错了:(");
					}
			};
			$('#order_form').ajaxSubmit(options); 
		},
		/**
		 * 完成
		 */
		complete:function(orderId){
			var self = this;
			$.ajax({
				url:basePath+'order!complete.do?ajax=yes&orderId='+orderId,
				dataType:"json",
				success:function(responseText){
					if(responseText.result==1){
						alert(responseText.message);
						self.orderStatus = responseText.orderStatus;
						self.bindFlowEvent();
					}
					if(responseText.result==0){
						alert(responseText.message);
					}						
				},
				error:function(){
					alert("出错了:(");
				}
			});
		},
		/**
		 * 作废
		 */
		cancel:function(orderId){
			var self = this;
			$.ajax({
				url:basePath+'order!cancel.do?ajax=yes&orderId='+orderId,
				dataType:"json",
				success:function(responseText){
					if(responseText.result==1){
						alert(responseText.message);
						self.orderStatus = responseText.orderStatus;
						self.bindFlowEvent();
					}
					if(responseText.result==0){
						alert(responseText.message);
					}						
				},
				error:function(){
					alert("出错了:(");
				}
			});
		}
		,
		saveRemark:function(orderId){
			var self = this;
			$("#remark_form").ajaxSubmit({
				url:basePath+'order!saveRemark.do?ajax=yes',
				dataType:"json",
				type:'POST',
				success:function(responseText){
					if(responseText.result==1){
						alert(responseText.message);
					}
					if(responseText.result==0){
						alert(responseText.message);
						self.bindFlowEvent();
						self.showRemark(orderId);
					}						
				},
				error:function(){
					alert("出错了:(");
				}
			});
		}
		,
		/**
		 * 显示订单基本信息
		 */
		showBase:function(){
			var self = this;
			$("#baseTab").load(basePath+"order!base.do?ajax=yes&orderId="+this.orderid+"&rmd="+new Date().getTime(),function(){
			 
				$("#editPriceBtn").click(function(){
					self.showpriceinput($(this));
				});
			});
		},
		showpriceinput:function(btn){
			var self = this;
			var orderid = $("#orderid").val();
			var price_span =  $("#order_price_span");
			var price = price_span.html().replace('￥','');
			var price_input = $("<input type='text' style='width:60px' value='"+price+"' id='price_input' name='price' />");
			price_span.empty();
			price_span.append(price_input);
			btn.html("确定");
			btn.unbind("click");
			btn.bind("click",function(){
				self.savePrice(price_input.val(),orderid);
			});			
		}
		,
		savePrice:function(price,orderid){
			var self = this;
			$.ajax({
				url:basePath+'order!savePrice.do?ajax=yes',
				data:"price="+price+"&orderId="+orderid,
				type:'POST',
				dataType:'json',
				success:function(result){
					if(result.result==1){
						$("#order_price_span").html("￥"+price);
						$("#editPriceBtn").html("修改");
						$("#editPriceBtn").unbind("click");
						$("#editPriceBtn").bind("click",function(){
							self.showpriceinput($(this));
						});	
					}else{
						alert("保存订单价格出错");
					}
				},
				error:function(e){
					alert("保存订单价格出错"+e);
				}
				
			});
		}
		,
		/**
		 * 显示订单货物信息
		 */
		showItems:function(){
			$("#itemsTab").load(basePath+"order!items.do?ajax=yes&orderId="+this.orderid);
		},
		
		/**
		 * 显示支付日志
		 */
		showPayLog:function(){
			$("#payLogTab").load(basePath+"order!payLog.do?ajax=yes&orderId="+this.orderid);
		},
		
		/**
		 * 显示货运日志
		 */
		showShipLog:function(){
			$("#shipLogTab").load(basePath+"order!shipLog.do?ajax=yes&orderId="+this.orderid);
		},
		
		/**
		 * 显示优惠方案
		 */
		showPmt:function(){
			$("#pmtTab").load(basePath+"order!pmt.do?ajax=yes&orderId="+this.orderid);
		},
		
		/**
		 * 显示订单日志
		 */
		showLog:function(){
			$("#logTab").load(basePath+"order!log.do?ajax=yes&orderId="+this.orderid);
		},
		
		showRemark:function(){
			$("#remarkTab").load(basePath+"order!remark.do?ajax=yes&orderId="+this.orderid);
			
			$("#save_remark").unbind("click");
			$("#save_remark").bind("click",function(){
				this.saveRemark(this.orderid);
			});	
		}
};

function isdigit(s)
{
var r,re;
re = /\d*/i; //\d表示数字,*表示匹配多个数字
r = s.match(re);
return (r==s);
}
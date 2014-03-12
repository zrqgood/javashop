var CheckOut={
		init:function(){
			var self = this;
			$("#receiver input[name=addressId]").click(function(){
				if($(this).attr("id")=="otherAddress"){
					$("#addr").val("");
					$("#shipZip").val("");
					$("#name").val("");
					$("#tel").val("");
					$("#checkout-recaddr").show();
				}else{
					self.loadDlyType($(this).attr("regionid"));
					$("#addr").val("addr");
					$("#zip").val("100010");
					$("#name").val("mame");
					$("#tel").val("tel");
					$("#checkout-recaddr").hide();
				}
			
			});
			 
	 
			
			$("#receiver input[name=addressId]:first").click();
			if($("#receiver input[name=addressId]").size()==0){
				$("#otherAddress").click();
			}
			
			$("#submitBtn").click(function(){
				if(! $("#checkoutform").checkall()  ){
					return ;
				}
				
				if(!self.checkform()){
					return ;
				}
		 		$.blockUI({ message: '<h1>正在提交您的订单，请稍候...</h1>'}); 
				var options = {
						url : "widget.do?type=checkOutWidget&ajax=yes&action=createOrder",
						type : "POST",
						dataType : 'json',
						success : function(result) {
			 				if(result.result==1){
				 				location.href="member_orderdetail_"+result.ordersn+".html";
				 			}else{
				 				$.unblockUI(); 
				 				alert(result.message);
					 		} 
						},
						error : function(e) {
							alert("出现错误 ，请重试");
						}
				};

				$('#checkoutform').ajaxSubmit(options);
			});
	 	},
	 
	 	//显示配送方式
	 	loadDlyType:function(regionid){
	 		
	 		if(regionid){
		 		var self= this;
		 		$("#shipping").html("loding...").load("widget?type=checkOutWidget&action=showDlyType&ajax=yes&regionid="+regionid,function(){
					$("#shipping input[name=typeId]").click(function(){
						$("#shipping [name=isProtect]").removeAttr("checked");
						var isProtect = $(this).parents("tr").find("input[name=isProtect]").attr("checked")?1:0;
						self.loadOrderTotal($(this).val(),isProtect);
					});
					$("#shipping [name=isProtect]").click(function(){
						var current = this;
						$("#shipping [name=isProtect]").each(function(){
							if(current != this)
								$(this).removeAttr("checked");
						});
						var shippingType = $(this).parents("tr").find("input[name=typeId]");
						shippingType.attr("checked",true);
						self.loadOrderTotal(shippingType.val(),1);
					});
				});
	 		}
	 	},
	 	//加载订单总金额
	 	loadOrderTotal:function(typeId,isProtected){
	 		var regionid = $("#receiver input[checked!='']").attr("regionid");
	 		if( $("#otherAddress").attr("checked") ){
	 			regionid =$("#region_id").val();
	 		}
	 		$("#amountInfo").load
	 		(
	 		"widget?type=checkOutWidget&action=showOrderTotal&ajax=yes&typeId="+typeId+"&regionId="+regionid+"&isProtected="+isProtected
	 		);
	 	},
	 	checkform:function(){
	 		if( $("input[name=typeId][checked=='']").size()==0 ){
	 			alert('请选择配送方式');
	 			return false;
	 		} 
	 		if( $("input[name=paymentId][checked=='']").size()==0 ){
	 			alert('请选择支付方式');
	 			return false;
	 		} 	 		
	 	 
	 		if( $.trim( $("#tel").val() )  ==''
	 			&& $.trim(  $("#mobile").val() ) =='' ){ 
	 			alert('电话或手机必须填写一项');
	 			return false;
	 		}
	 		return true;
	 	}
};
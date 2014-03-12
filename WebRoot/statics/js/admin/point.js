var Eop=Eop||{};
Eop.Point  ={
	init:function(){
		this.initdialog();
		this.checkcanget();
	},
	initdialog:function(){
		$("body").append("<div id='pointget'><div>您本月免费的1000积分尚未领取<br><a href='javascript:;' id='getPointBtn'>点击此处领取积分</a></div></div>");
		Eop.Dialog.init({id:"pointget",modal:true,title:"领取积分",width:"300px",height:"85px"});
		
	}
	,
	checkcanget:function(){
		var self  = this;
		alert("ok");
		$.ajax({
			url:'../core/admin/site!cktpoint.do?ajax=yes',
			type:"get",
			dataType:'json',
			success:function(result){
				if(result.result==1){
					self.show();
				}
			},
			error:function(){
				alert("出错了:(");
			}
			
		});
	},
	show:function(){
		 var self  = this;
		$("#getPointBtn").click(function(){
			self.getpoint();
		});
		Eop.Dialog.open("pointget");
	},
	getpoint:function(){
		var self  = this;
		$.ajax({
			url:'../core/admin/site!getpoint.do?ajax=yes',
			type:"get",
			dataType:'json',
			success:function(result){
				if(result.result==1){
					Eop.Dialog.close("pointget");
					alert("免费1000积分领取成功，您在下个月仍可登录后台领取免费积分。");
				}else{
					alert(result.message);
				}
			},
			error:function(){
				alert("出错了:(");
			}
			
		});		
	}
	
};
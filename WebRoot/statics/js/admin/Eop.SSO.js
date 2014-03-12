var EOP={};
EOP.SSO={
	login_btn:undefined,
	domains:undefined,
	userid:undefined,
	siteid:undefined,
	adminid:undefined,
	loginedCount:0,
	defaults:{
		 
	},
	
	
	
	init:function(opations){
		$("input").attr("autocomplete","off");
		if(opations) this.defaults = opations;
		
		var that =this;

		$(document).keydown(function(event){
			if(event.keyCode==13){
				that.login();
			}
			
		});
			
		this.initValidCode();
		this.login_btn=$("#login_btn");
		this.login_btn.attr("disabled",false).val("确定"); 
		this.login_btn.click(function(){
			that.login();
		});
		
	},
	oneLogin:function(url){
	
		if(this.loginedCount<this.domains.length){
			this.appLogin(url); 
		}else if(this.loginedCount==this.domains.length){
			this.success();
		}
		this.loginedCount ++;
	}
	,
	/*
	 * 进行登录操作
	 */
	login:function(){
		this.login_btn.attr("disabled",true).val("正在登陆..."); 
		var that =this;
		var options = {
				url : "login.do",
				type : "POST",
				dataType : 'json',
				success : function(result) {				
					if(result.result==0){
/*						that.domains = result.domains;
						that.userid =result.userid;
						that.siteid = result.siteid;
						that.adminid = result.adminid;*/
						//that.oneLogin("login");
						that.success();
					}else{
						that.fail(result.message);
					}
				},
				error : function(e) {
					alert("出现错误 ，请重试");
				}
			};

			$('form').ajaxSubmit(options);		
	},
	
	
	/*
	 * 为每个应用登录
	 */
	appLogin:function(u){
	 
		this.debug("applogin -> "+this.loginedCount );
		var that  = this;
		try{
			var domain = this.domains[this.loginedCount] + "/eop/login";
			domain+="?userid="+this.userid+"&siteid="+ this.siteid +"&adminid="+this.adminid;
			this.debug( "<b>"+ u +" call login to "+ domain+"</b>");
			 
			var frm = $("<iframe style='display:none'>").appendTo($("body"));
			frm.load(function(){
				that.debug("login complete  url is:" + this.src);
				that.oneLogin(this.src);
			});
			frm.attr("src",domain);
//			$.getScript(
//				 domain,
//				 {userid:that.userid,siteid:that.siteid,adminid:that.siteid},
//			     function(){ alert("ok");}
//			 );
	 
		}catch(e){
			that.fail(e);
		}
		
	},
	success:function(){
		this.login_btn.attr("disabled",false).val("确定"); 
		if(typeof this.defaults.success) this.defaults.success();
		this.debug("登陆成功");
	}
	,
	/*
	 * 登录失败
	 */
	fail:function(e){
		this.login_btn.attr("disabled",false).val("确定"); 
		if(this.defaults.fail) this.defaults.fail(e);
		this.debug("登录失败"+e);
	}
	,
	
	/*
	 * 初始化验证码
	 */
	initValidCode:function(){
		//$("#valid_code").val('');
		$("#username").focus();
		
	    var that =this;
		$("#code_img").attr("src","../validcode.do?vtype=admin&rmd="+new Date().getTime())
		.click(function(){
			$(this).attr("src","../validcode.do?vtype=admin&rmd="+new Date().getTime() );
			
		});		
	}
	,debug:function(msg){
		//$("#log").html( $("#log").html()+"<br/>" +msg);		
	}
	
	
 
};

$(function(){
	EOP.SSO.init({
		success:function(){
		  location.href ="main.jsp";
		},
		fail:function(e){
			alert(e);
		}
		
	});
});
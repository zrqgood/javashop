var Core = {};
Core.AccountOperator = {
	content:undefined,
	init : function() {
	    this.content = $("#infocontent");
	    var self=this;
	    this.showbaseinfo("/eop/core/admin/user/baseInfo.do");
	    $("#baseinfo").click(function() {
	    	self.showbaseinfo("/eop/core/admin/user/baseInfo.do");
		}),
		
		$("#userdetail").click(function() {
			self.showbaseinfo("/eop/core/admin/user/userDetail.do");
		}),
		$("#changepassword").click(function() {
			self.showbaseinfo("/eop/core/admin/user/userAdmin!editPassword.do");
		});
	},
	showbaseinfo:function(url){
		$.Loading.show("正在读取内容，请稍候...");
		this.content.load(url, function(){$.Loading.hide();});
	}

};

$(function() {
	Core.AccountOperator.init();
});
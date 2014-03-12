var Core = {};
Core.AdminOperator = {
	content:undefined,
	init : function() {
	    this.content = $("#infocontent");
	    this.showinfo("/eop/core/admin/user/userAdmin.do");
	},
	showinfo:function(url){
		$.Loading.show("正在读取内容，请稍候...");
		this.content.load(url, function(){$.Loading.hide();});
	}

};

$(function() {
	Core.AdminOperator.init();
});
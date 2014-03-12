var Theme={
	 
	init:function(){
		var self = this;
		$(".all-themes a").click(function(){
			var themeid = $(this).attr("themeid");
			self.changeTheme(themeid);
		}); 
	},
	
	changeTheme:function(themeid){
		$.Loading.show("正则构建界面请稍后...");
		var self = this;
		$.ajax({
			 type: "POST",
			 url: "siteAdminTheme!change.do",
			 data:   "themeid="+themeid,
			 dataType :"html",
			 success: function(msg){
				parent.location.reload();
			 },
			 error:function(){
				 alert("请求失败，请重试!");
			 }
		}); 
	}

};
$(function(){
	Theme.init();
});
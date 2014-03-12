var Adv=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$(".stop").click(function(){var advid=$(this).attr("advid");self.doStop(advid);});
		$(".start").click(function(){var advid=$(this).attr("advid");self.doStart(advid);});
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的广告");
			return ;
		};
		if(!confirm("确认要删除选定的广告？")){	
			return ;
		}
		
		$.Loading.show("正在删除广告...");
		
		this.deletePost("adv!delete.do", "广告删除成功");
	},
	doStop:function(advid){
	    $.Loading.show('正在停用广告，请稍侯...');
		var that =this;
		var options = {
			url : "adv!stop.do?ajax=yes&advid="+advid,
			type : "POST",
			dataType : 'json',
			success : function(result) {	
				$.Loading.hide();
				if(result.result==0){
					alert(result.message);
					location.reload();
				}else{
					alert(result.message);
				}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
			}
		};
		$.ajax(options);
	},
	doStart:function(advid){
	    $.Loading.show('正在启用广告，请稍侯...');
		var that =this;
		var options = {
			url : "adv!start.do?ajax=yes&advid="+advid,
			type : "POST",
			dataType : 'json',
			success : function(result) {	
				$.Loading.hide();
				if(result.result==0){
					alert(result.message);
					location.reload();
				}else{
					alert(result.message);
				}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
			}
		};
		$.ajax(options);
	}
	
});

$(function(){
	Adv.opation("idChkName","id");
	Adv.init();
});
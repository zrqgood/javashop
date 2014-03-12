var Comments=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$(".chide").click(function(){var comment_id=$(this).attr("comment_id");self.doHide(comment_id);});
		$(".cshow").click(function(){var comment_id=$(this).attr("comment_id");self.doShow(comment_id);});
		$("#delBtn").click(function(){self.doDelete();});
		$("#cleanBtn").click(function(){self.doClean();	});
		$("#revertBtn").click(function(){self.doRevert();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的评论");
			return ;
		}
	 
		if(!confirm("确认要将这些评论放入回收站吗？")){	
			return ;
		}
		
		$.Loading.show("正在将评论放入回收站...");
		
		this.deletePost("comments!delete.do");
			
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的评论");
			return ;
		}
	 
		if(!confirm("确认要将这些评论彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("comments!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的评论");
			return ;
		}
	 
		this.deletePost("comments!revert.do","选择的评论已被成功还原至赠品列表中");		
	},
	doHide:function(comment_id){
	    $.Loading.show('正在设置信息，请稍侯...');
		var that =this;
		var options = {
			url : "comments!hide.do?ajax=yes&comment_id="+comment_id,
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
	doShow:function(comment_id){
	    $.Loading.show('正在设置信息，请稍侯...');
		var that =this;
		var options = {
				url : "comments!show.do?ajax=yes&comment_id="+comment_id,
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
	Comments.opation("idChkName","id");
	Comments.init();
});
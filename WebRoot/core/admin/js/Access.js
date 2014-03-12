var Access={
	init:function(){
		this.bindExpand();
		
	},
	bindExpand:function(){
		var self = this;
		$(".expand").unbind().bind("click",function(){
			self.expand($(this));
		});		
	},
	bindClose:function(){
		var self = this;
		$(".expand").unbind().bind("click",function(){
			self.close($(this));
		});		
	},
	
	expand:function(btn){
		var self  =this;
		var tr= btn.parents("tr");
		
		//已经加载过详细流量，则直接显示
		if( btn.attr("isload") ){
			tr.next().show();
			//更换为展开图标，并且标记为已经加载
			btn.children("img").attr("src","images/sitemapopened.gif");
			self.bindClose(); 
		}else{
			var ip = btn.attr("ip");
			var daytime = btn.attr("daytime");
			
			//异步获取详细流量信息
			$.ajax({
				url:"access!detaillist.do?ajax=yes",
				data:"ip="+ip+"&daytime="+daytime,
				type:"post",
				dataType:"html",
				success:function(html){
					tr.after(html);
					//更换为展开图标，并且标记为已经加载
					btn.children("img").attr("src","images/sitemapopened.gif");
					btn.attr("isload","1");
					
					self.bindClose(); 
				},
				error:function(){
					alert("读取流量信息出错");
				}
			});
		}
	},
	
	close:function(btn){
		
		var tr= btn.parents("tr");
		tr.next().hide();
		btn.children("img").attr("src","images/sitemapclosed.gif");
		this.bindExpand();
	}
	
};
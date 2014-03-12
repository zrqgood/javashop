var Regions = {
		init:function(){
			var self =this;
			$("#regionMain").load("region!listChildren.do?parentid=0&ajax=yes", function(){
				self.bindEvent();
			});
			$("#resetBtn").click(function(){
				if( confirm('确定要将地区初始化为默认地区吗？ 此操作会清除当前地区数据') ){
					self.reset();
				}
			});
		},
		reset:function(){
			$.Loading.show("正在重新初始化地区，需要花费 一些时间，请稍候...");
			$.ajax({
				url:"region!reset.do?ajax=yes",
				dataType:"json",
				success:function(result){
					if(result.result==0){
						alert("初始化完成");
						location.reload();
					}else{
						alert("初始化失败");
					}
					$.Loading.hide();	
				},
				error:function(){
					alert("初始化失败");
					$.Loading.hide();
					}
				});
		}
		,
		bindEvent:function(){
			var self = this;
			$(".delete").unbind("click");
			$(".delete").bind("click", function(){
				
				if(!confirm("确认要删除该地区及其所属地区吗？")){	
					return ;
				}
				
				$.Loading.show("正在删除该地区及其所属地区...");
				
				var self = this;
				$.ajax({
					url:"region!delete.do?region_id="+$(self).attr("region_id") + "&ajax=yes",
					dataType:"json",
					success:function(){
						$("tr[parentid="+$(self).attr("region_id")+"]").remove();
						$("tr[id="+$(self).attr("region_id")+"]").remove();
						$.Loading.hide();
						},
					error:function(){
						alert("删除失败");
						$.Loading.hide();
						}
					});
				});
			$(".imgTree").unbind("click");
			$(".imgTree").bind("click", function(){
				if($(this).attr("status")=="closed"){
					$.Loading.show("正在读取该地区所属地区...");
					self.addChildren($(this).parent("div").parent("td").parent("tr"), $(this).attr("id"));
					$(this).attr("status","opened");
					$(this).attr("src", "images/sitemapopened.gif");
					$.Loading.hide();
				}else{
					$("tr[parentid="+$("tr[parentid="+$(this).attr("id")+"]").attr("id")+"]").remove();//关闭第三重的子
					$("tr[parentid="+$(this).attr("id")+"]").remove();
					$(this).attr("status","closed");
					$(this).attr("src", "images/sitemapclosed.gif");
				}
			});
		},
		addChildren:function(mobject, parentid){
			var self =this;
			$.ajax({
				url:"region!listChildren.do?parentid=" + parentid + "&ajax=yes",
				dataType:"html",
				success:function(html){
					mobject.after(html);
					self.bindEvent();
				},
				error:function(){
					alert("error");
				}
			});
		}
};

$(function(){
	Regions.init();
});
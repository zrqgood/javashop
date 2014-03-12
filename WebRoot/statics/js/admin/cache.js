Eop.Cache={
		
	init:function(){
		this.btn = $("#cache_btn");
		this.checkState();
	},
	/**
	 * 检测缓存开启状态，并绑定事件
	 */
	checkState:function(){
		var self = this;
		$.ajax({
			url:'../core/admin/widgetCache!getState.do?ajax=yes',
			dataType:'json',
			success:function(result){
				if(result.result==1){
					self.setBtnState(result.state);
				}
			},
			error:function(){
				
			}
		});
	},
	
	/**
	 * 设计按钮状态，样式、事件
	 */
	setBtnState:function(state){
		var self = this;
		if(state=='open'){
			self.btn.removeClass("cache_close").addClass("cache_open")
			.html('关闭缓存');
		}else{
			self.btn.removeClass("cache_open").addClass("cache_close")
			.html('开启缓存');
		}
		self.bindEvent();
	}
	,
	/**
	 * 绑定事件
	 */
	bindEvent:function(){
		var self = this;
		 $("#cache_btn.cache_close").unbind("click").bind("click",function(){
			 self.openCache();
		 });
		 
		 $("#cache_btn.cache_open").unbind("click").bind("click",function(){
			 self.closeCache();
		 });		 
		 
	},
	
	/**
	 * 开启缓存
	 */
	openCache:function(){
		var self=this;
		$.ajax({
			url:'../core/admin/widgetCache!open.do?ajax=yes',
			dataType:'json',
			success:function(result){
				if(result.result==1){
					self.setBtnState('open');
					alert("缓存已开启，您发布的数据将会有一定延迟时间才会显示。");
				}
			},
			error:function(){
				alert("抱歉,开启缓存失败");
			}
		});
	},
	/**
	 * 关闭缓存
	 */
	closeCache:function(){
		var self = this;
		$.ajax({
			url:'../core/admin/widgetCache!close.do?ajax=yes',
			dataType:'json',
			success:function(result){
				if(result.result==1){
					self.setBtnState('close');
					alert("缓存已关闭。");
				}
			},
			error:function(){
				alert("抱歉,关闭缓存失败");
			}
		});
	}
	
};
$(function(){	
	if(mainpage){Eop.Cache.init();}
});
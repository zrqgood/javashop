var Eop=Eop||{};
Eop.Grid={
	defauts:{
		idChkName:"id", //id复选框name
		toggleChkId:"toggleChk"
	} 
	,
	opation:function(key,value){
		if(typeof(key)=='object'){
			this.defauts=$.extend({},this.defauts,key);
		}else if( typeof(key)=="string" ){
			this.defauts[key]=value;
		}
	}
	,
	deletePost:function(url,msg){
		var self =this;
		url=url.indexOf('?')>=0?url+"&":url+"?";
		url+="ajax=yes";
	//	url=basePath+url;
		var options = {
				url : url,
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
					if(result.result==0){
						self.deleteRows();
						if(msg)
							alert(msg);
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

			$('form').ajaxSubmit(options);		
	},
	deleteRows:function(){
		$("input[name="+this.defauts.idChkName+"]").each(function(){
			var checkbox= $(this);
			if( checkbox.attr("checked") ){
				 checkbox.parents("tr").remove();
			}
		});		
	}
	,
	/**
	 * 检测id是否有被选中的，如果一个也没有返回假
	 */
	checkIdSeled:function(){
		var r=false;
		$("input[name="+this.defauts.idChkName+"]").each(function(){
			if( $(this).attr("checked") ){
				r=true;
				return ;
			}
		});
		
		return r;
	},
	/**
	 * 切换全选
	 */
	toggleSelected:function(checked){
		$("input[name="+this.defauts.idChkName+"]").attr("checked",checked);
	}
		
};

/**
 * 异步分页jquery插件
 */
(function($) {
    $.fn.gridAjaxPager = function(options) {
    	
    	return this.each(function(){
    		bindEvent($(this));
    	});
    	
    	/**
    	 * 绑定分页事件
    	 */
    	function bindEvent(pager,grid){
    		var grid = pager.parent();
    		 pager.find("li>a").unbind(".click").bind("click",function(){
				 load($(this).attr("pageno"),grid);
			 }); 
    		 pager.find("a.selected").unbind("click");
    	}
    	
    	/**
    	 * 点击分页的加载事件
    	 */
    	function load(pageNo,grid){
    		var url = options;
    		url=url+"page="+pageNo;
    		$.ajax({
    			url:url,
    			success:function(html){
    				grid.empty().append( $(html).find(".gridbody").children() );
    				bindEvent(grid.children(".page"));
    			},
    			error:function(){
    				alert("加载页面出错:(");
    			}
    		});
    	}
    	
    };
})(jQuery);

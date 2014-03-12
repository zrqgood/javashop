(function($){

	function create(){
		var loadding=$("#loading");
		if(loadding.size()==0)
			loadding = $("<div id=\"loading\" class=\"loading\" style='z-index:3000' />").appendTo($("body")).hide();
		return loadding;
	}
	
	$.Loading = $.Loading || {};
	$.Loading.show=function(text){
		var loading = create();
		
		if(this.text){
			loading.html(this.text);	
		}else{
			if(text)
				loading.html(text);			
		}
		
		$("<div style=\"height: 100%; width: 100%; position: fixed; left: 0pt; top: 0pt; z-index: 2999; opacity: 0.4;\" class=\"jqmOverlay\"></div>").appendTo($("body"));
		loading.show();		
	};

	$.Loading.hide=function(){
		create().hide();		
		$(".jqmOverlay").remove();
	};

	
})(jQuery);
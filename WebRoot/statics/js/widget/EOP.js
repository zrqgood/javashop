$(function(){
	$("div[eop_type='widget']").each(function(){
		var widget = $(this);
		var fun =widget.children(".content").attr("loadfun");
		if(fun){
			try{
				eval( fun+"(widget)");
			}catch(e){}
		}
	});
});
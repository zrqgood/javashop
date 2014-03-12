$(function(){
	$(".titleBar ul li").mouseover(function(){
		$(".titleBar ul li").removeClass("current");								
		$(this).addClass("current");
		if($(this).attr("id")==2){
			$("#cont_1").hide();
			$("#cont_2").show();
		}else{
			$("#cont_2").hide();
			$("#cont_1").show();
		}
	});
});

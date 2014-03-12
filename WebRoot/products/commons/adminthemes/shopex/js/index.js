$(function(){
	Eop.AdminUI.init({wrapper:$("#right_content")});
	var defClick = "";
	var $html;
 	
	$("#leftToggler").toggle(function(){
		$("#left").width(0);
		$("#leftToggler").css("left",0);
		$("#right").css("left",8);
		$("#right_content").width(document.documentElement.clientWidth-8);
	},function(){
		$("#left").width(143);
		$("#leftToggler").css("left",143);
		$("#right").css("left",151);
		$("#right_content").width(document.documentElement.clientWidth-153);


	});

	$.each(menu.sys,function(k,v){
		
		var $li = $("<li></li>");
		$li.append( "<a  href='"+v.url+"' target='"+v.target+"'>" + v.text + "</a>" ); 			

		var html = submenu(v.children);
		if(html!="")
			$li.append($(html));

		$(".sysmenu").children("ul").append($li);
	});

	$("div.top a").click(function(){
		if($(this).next("ul").html()==null)
			Eop.AdminUI.load($(this));
		return false;
	});
	
	$.each(menu.app,function(k,v){
		if(v["default"])
			defClick = defClick+k;
		$html = $("<li><a onfocus='this.blur()' href='" + v.url + "' class='link' id='tab_" + k + "' target=\""+v.target+"\">" + v.text + "</a></li>");
		$html.children("a").click(function(){
			$(".appmenu li").removeClass("current");
			$(this).parent().addClass("current");
			refreshLeftMenu(this.id.substr(4));
			Eop.AdminUI.load($(this));
			return false;
		});
		$(".appmenu ul").append($html);	
	});
	if(defClick!="")
		$("#tab_" + defClick).click();

		$(window).resize(function(){
			$("#right_content").height(document.documentElement.clientHeight-115);
			$("#right_content").width(document.documentElement.clientWidth-153);
			$("#left").height(document.documentElement.clientHeight-87);
			$("#leftToggler").height(document.documentElement.clientHeight-87)
		});
		$(window).resize();

});

function refreshLeftMenu(parentId) {
	var html = "";
	var list = "";
	$("#extmenu").empty();
	$.each(menu.app[parentId].children,function(k,v){
		list = "<dt><a class='folder'>" + v.text + "</a></dt>";
		var c="";
		if (v.children)
		{
			c=c+"<dd><ul>"
			$.each(v.children,function(key,value){
				var tv = value.type || "html";	//	type 默认为html
				c = c + "<li><a href='" + value.url + "' type='" + tv + "' target=\""+value.target+"\">" + value.text + "</a></li>";
			});
			c=c+"</ul></dd>"
		};
		list=list+c;
		html = html + list;
	});
	$html = $("<dl>" + html + "</dl>");
	$("#extmenu").append($html);
	$("#extmenu dd a").click(function(){
		Eop.AdminUI.load($(this));
		return false;
	});
}
function submenu(children){
	var html = "<ul>";
	if(children && children.length>0){
		$.each(children,function(key,value){
			html += "<li><a href='" + value.url + "' target='"+value.target+"'>" + value.text + "</a>";
			var c=submenu(value.children);
			html=html+c;
			html +="</li>";
		});
		html +="</ul>";
	}else{
		html="";
	};
	return html;
}
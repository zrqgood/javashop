$(function(){
	Eop.AdminUI.init({wrapper:$("#dispanel")});
	
	var defClick;
	$.each(menu.sys,function(k,v){
		var $li = $("<li><a    target='"+v.target+"' href='"+ v.url +"' >" + v.text + "</a></li>");
		var html = submenu(v.children);
		if(html!="")
			$li.append($(html));
		$(".sysmenu").children("ul").append($li);
		
	});
	$("div.sysmenu a").click(function(){
		if($(this).next("ul").html()==null)
			Eop.AdminUI.load($(this));
		return false;
	});
	$.each(menu.app,function(k,v){
		if(v["default"])
			defClick = "" + k;
		$html = $("<li><a href='" + v.url + "'id='tab_" + k + "'  target='"+v.target+"' class='normal'>" + v.text + "</a></li>");
		$html.children("a").click(function(){
			$("#toptab li a").removeClass("selected");
			$(this).addClass("selected");
			refreshLeftMenu(this.id.substr(4));
			Eop.AdminUI.load($(this)); //lzf add
			return false;
		});
		$("#toptab").append($html);
	});

	$(window).resize(function(){
		$(".cclient").width(document.documentElement.clientWidth-178);
		$(".cclient").height(document.documentElement.clientHeight-104);
		$("#dispanel").height(document.documentElement.clientHeight-158);
	});
	$(window).resize();
	
	if(defClick)
		$("#tab_" + defClick).click();
});

function refreshLeftMenu(parentId) {
	var html = "";
	var list = "";
	$("#leftMenus").empty();
	$.each(menu.app[parentId].children,function(k,v){
		list = "<dt><a class='folder'>" + v.text + "</a></dt>";
		$.each(v.children,function(key,value){
			var tv = value.type || "html";	//	type 默认为html
			list = list + "<dd><a   target='"+value.target+"'  href='" + value.url + "' type='" + tv + "'>" + value.text + "</a></dd>";
		});
		html = html + list;
	});
	$html = $("<dl>" + html + "</dl>");
	$("#leftMenus").append($html);
	$("#leftMenus dd a").click(function(){
		Eop.AdminUI.load($(this));
		return false;
	});
}
function submenu(children){
	var html = "<ul>";
	if(children && children.length>0){
		$.each(children,function(key,value){
			html += "<li><a     target='"+value.target+"'  href='" + value.url + "' id='sys_" + key + "'  >" + value.text + "</a>";
			var c=submenu(value.children);
			html +=c;
			html +="</li>";
		});
		html +="</ul>";
	}else{
		html="";
	};
	return html;
}
$(function(){
	Eop.AdminUI.init({wrapper:"#right_content"});
	var defClick = "";
	var $html;
 	
	$.each(menu.sys,function(k,v){
		
		var $li = $("<li></li>");
		
		if(v.target && v.target=='_blank'){
			$li.append( "<a  href='"+v.url+"' target='"+v.target+"'>" + v.text + "</a>" ); 			
		}else{			
			$li.append( "<a onfocus='this.blur()' href='javascript:;' target='mainFrm' link='" + v.url + "'>" + v.text + "</a>" );
		}
		
		var html = submenu(v.children);
		if(html!="")
			$li.append($(html));

		$("#sysmenu").children("ul").append($li);
	});

	$("div.top a[target='mainFrm']").click(function(){
		if($(this).next("ul").html()==null)
			Eop.AdminUI.loadUrl($(this).attr("link"));
		return false;
	});
	
	$.each(menu.app,function(k,v){
		if(v["default"])
			defClick = defClick+k;
		$html = $("<li><a onfocus='this.blur()' href='" + v.url + "' class='link' id='tab_" + k + "'>" + v.text + "</a></li>");
		$html.children("a").click(function(){
			$("#appmenu li").removeClass("actived");
			$(this).parent().addClass("actived");
			refreshLeftMenu(this.id.substr(4));
			Eop.AdminUI.loadUrl(this.href);//lzf add
			return false;
		});
		$("#appmenu ul").append($html);	
	});
	if(defClick!="")
		$("#tab_" + defClick).click();

		$(window).resize(function(){
			$("#right_content").height(document.documentElement.clientHeight-115);
			$("#right_content").width(document.documentElement.clientWidth-210);
		});
		$(window).resize();
	
});

function refreshLeftMenu(parentId) {
	var html = "";
	var list = "";
	$("#extmenu ul").empty();
	$.each(menu.app[parentId].children,function(k,v){
		list = "<dt><a class='ico1'>" + v.text + "</a></dt>";
		$.each(v.children,function(key,value){
			var tv = value.type || "html";	//	type 默认为html
			list = list + "<dd><a href='" + value.url + "' type='" + tv + "'>" + value.text + "</a></dd>";
		});
		html = html + list;
	});
	$html = $("<li><dl>" + html + "</dl></li>");
	$("#extmenu ul").append($html);
	$("#extmenu dd a").click(function(){
		$("#nav_title").html($(this).text());
		//enation.menu.load("#right_content",this.href);
		Eop.AdminUI.loadUrl(this.href);
		return false;
	});

}
function submenu(children){
	var html = "<ul>";
	if(children && children.length>0){
		$.each(children,function(key,value){
			html += "<li><a href='javascript:;' link='" + value.url + "' target='mainFrm'>" + value.text + "</a>";
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
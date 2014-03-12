$(function(){
	Eop.AdminUI.init({wrapper:"#dispanel"});
	
	var defClick;
	$.each(menu.sys,function(k,v){
		var $li = $("<li><a onfocus='this.blur()' href='javascript:;' link='" + v.url + "'>" + v.text + "</a></li>");
		var html = submenu(v.children);
		if(html!="")
			$li.append($(html));
		$(".sysmenu").children("ul").append($li);
		
	});
	$("div.sysmenu a").click(function(){
		if($(this).next("ul").html()==null)
			Eop.AdminUI.loadUrl($(this).attr("link"));
	});
	$.each(menu.app,function(k,v){
		if(v["default"])
			defClick = "" + k;
		if(k == 0){
		     $html = $("<li> <div class='bg1' id='bg1_tab_" + k + "'></div><div class='bg3' id='bg3_tab_" + k + "'><a href='javascript:void(0);' id='tab_" + k + "' >" + v.text + "</a></div><div id='bg2_tab_" + k + "' class='bg2'></li>");
		}
		else 
             $html = $("<li> <div class='bg11' id='bg1_tab_" + k + "'></div><div class='bg33' id='bg3_tab_" + k + "'><a href='javascript:void(0);' id='tab_" + k + "' >" + v.text + "</a></div><div id='bg2_tab_" + k + "' class='bg22'></li>");
		$html.children(1).children("a").click(function(){
			//$("#toptab li a").removeClass("selected");
			var s = this.id.substr(4,5);
		 
            for( var i = 0;i<10;i++){
			 
					if(i == s){
					 $("#bg1_tab_"+i).removeClass("bg11");
					 $("#bg1_tab_"+i).removeClass("bg111");
					 $("#bg1_tab_"+i).addClass("bg1");
					 $("#bg3_tab_"+i).removeClass("bg33");
					 $("#bg3_tab_"+i).removeClass("bg333");
					 $("#bg3_tab_"+i).addClass("bg3");
					 $("#bg2_tab_"+i).removeClass("bg22");
					 $("#bg2_tab_"+i).removeClass("bg222");
					 $("#bg2_tab_"+i).addClass("bg2");
					}else{
					 $("#bg1_tab_"+i).removeClass("bg1");
					 $("#bg2_tab_"+i).removeClass("bg111");
					 $("#bg1_tab_"+i).addClass("bg11");
					 $("#bg3_tab_"+i).removeClass("bg3");
					 $("#bg2_tab_"+i).removeClass("bg333");
					 $("#bg3_tab_"+i).addClass("bg33");
					 $("#bg2_tab_"+i).removeClass("bg2");
					 $("#bg2_tab_"+i).removeClass("bg222");
					 $("#bg2_tab_"+i).addClass("bg22");

					}
				 
			}
			//$(this).addClass("selected");
			refreshLeftMenu(this.id.substr(4));
			Eop.AdminUI.loadUrl(this.href); //lzf add
			return false;
		});
		$html.children(1).children("a").mouseover(function(){
			  var s = this.id.substr(4,5);
            for( var i = 0;i<10;i++){ 
					if(i == s ){
						 if($("#bg1_tab_"+i).attr("class") == "bg11"){
								 $("#bg1_tab_"+i).removeClass("bg11");
								 $("#bg1_tab_"+i).addClass("bg111");
								 $("#bg3_tab_"+i).removeClass("bg33");
								 $("#bg3_tab_"+i).addClass("bg333");
								 $("#bg2_tab_"+i).removeClass("bg22");
								 $("#bg2_tab_"+i).addClass("bg222");
						 } 

					} 
				 
			}
	    });
		$html.children(1).children("a").mouseout(function(){
			  var s = this.id.substr(4,5);
            for( var i = 0;i<10;i++){ 
					if(i == s ){
						 if($("#bg1_tab_"+i).attr("class") == "bg111"){
								 $("#bg1_tab_"+i).removeClass("bg111");
								 $("#bg1_tab_"+i).addClass("bg11");
								 $("#bg3_tab_"+i).removeClass("bg333");
								 $("#bg3_tab_"+i).addClass("bg33");
								 $("#bg2_tab_"+i).removeClass("bg222");
								 $("#bg2_tab_"+i).addClass("bg22");
						 } 

					} 
				 
			}
	    });
		$("#toptab").append($html);
	});

	

	$(window).resize(function(){
		$(".cclient").width(document.documentElement.clientWidth-187);
		$(".cclient").height(document.documentElement.clientHeight-106);
		$("#dispanel").height(document.documentElement.clientHeight-164);
	});
	$(window).resize();
	
	if(defClick)
		$("#tab_" + defClick).click();

	refreshLeftMenu("0");
});

function refreshLeftMenu(parentId) {
	var html = "";
	var list = "";
	$("#leftMenus").empty();
	$.each(menu.app[parentId].children,function(k,v){
		
		list = "<dt class='show'><a href='#' class='folder'>" + v.text + "</a></dt>";
		$.each(v.children,function(key,value){
			var tv = value.type || "html";	//	type 默认为html
			list = list + "<dd class='show_no'><a href='" + value.url + "' type='" + tv + "'>" + value.text + "</a></dd>";
		}); 
		html = html + list;
	});
	$html = $("<dl>" + html + "</dl><div class='menuline'></div>");
	$("#leftMenus").append($html);
	$("#leftMenus dd a").click(function(){
		$("#leftMenus dd").removeClass("selected");
		$(this).parent().addClass("selected");
		Eop.AdminUI.loadUrl(this.href);
		return false;
	});
	$("#leftMenus dt a").click(function(){
		var p = $(this).parent();
		if(p.attr("class") == 'show'){
		    p.removeClass("show");
			p.next().show();
			p.next().next().show(); 
	    }else{
            p.addClass("show");
			p.next().hide();
			p.next().next().hide(); 
		}

	});
}
function submenu(children){
	var html = "<ul>";
	if(children && children.length>0){
		$.each(children,function(key,value){
			html += "<li><a href='javascript:;' link='" + value.url + "' id='sys_" + key + "'  >" + value.text + "</a>";
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
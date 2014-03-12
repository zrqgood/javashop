<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>${title }</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="image/x-icon" href="${ico}" rel="icon" />
<link type="image/x-icon" href="${ico}" rel="bookmark" />
<script type="text/javascript" src="menu"></script>
<script type="text/javascript" src="${staticserver }/js/common/common.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/eop-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/kindeditor-3.4/kindeditor.js"></script>
<link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/dialog.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/dmenu.css"  rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${context}/js/index.js"></script>
<script type="text/javascript">
(function(a){a.fn.dropMenu=function(g){var e=this.children("ul"),h=e.find("ul").parent(),f=this;this._default={overduration:350,outduration:100};a.extend(this._default,g);h.each(function(){var b=a(this),d=b.find("ul:eq(0)");this._dimensions={w:this.offsetWidth,h:this.offsetHeight,subulw:d.outerWidth(),subulh:d.outerHeight()};this.istopheader=b.parents("ul").length==1?true:false;d.css({top:this.istopheader?this._dimensions.h+"px":0});b.children("a:eq(0)").addClass(this.istopheader?"img_down":"img_right"); b.hover(function(){var i=a(this).children("ul:eq(0)");this._offsets={left:a(this).offset().left,top:a(this).offset().top};var c=this.istopheader?0:this._dimensions.w;c=this._offsets.left+c+this._dimensions.subulw>a(window).width()?this.istopheader?-this._dimensions.subulw+this._dimensions.w:-this._dimensions.w:c;i.css({left:c+"px"}).fadeIn(f._default.overduration)},function(){a(this).children("ul:eq(0)").fadeOut(f._default.outduration)})});e.find("ul").css({display:"none",visibility:"visible"})}})(jQuery);
	$(function(){
		$(".sysmenu").dropMenu();
	});
</script>
</head>
<body>
	<div id="head">
		<div class="top">
			<div class="logo"><a href="#"><img src="${logo}"/></a></div>
			<div class="sysmenu">
				<ul class="dmenu">
					
				</ul>
			</div>
		</div>
		<div class="appmenu">
			<ul></ul>

			<div class="borwsebutton">
				<span>
					<a href="../index.html" target="_blank">浏览商店</a>
				</span>
			</div>
		</div>
	</div>

	<div id="left">
		<div id="extmenu">
		</div>
	</div>

	<div title="开启或关闭侧边栏" id="leftToggler" style="height: 500px;">&nbsp;</div>

	<div id="right">
		<div id="navBar">
			<div id="nav_title">基本信息</div>
		</div>	
		<div id="right_content"></div>
	</div>
</body>
</html>
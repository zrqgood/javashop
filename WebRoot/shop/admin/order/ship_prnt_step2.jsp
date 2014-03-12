<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<style>
	.title{
	border-left:1px solid #FFFFFF;
	border-right:1px solid #BEC6CE;
	height:65px;
	padding:0 3px;
	float:left;
	}
  </style>
  <script type="text/javascript" src="js/swfobject.js"></script>
  <script type="text/javascript">
   var xmldata = "${printTmpl.prt_tmpl_data }";
	var swfVersionStr = "10.0.0";
    var xiSwfUrlStr = "/playerProductInstall.swf";
    var flashvars = {};
    flashvars.data = xmldata;
	flashvars.bg = "${printTmpl.bgimage}";

    var params = {};
    params.quality = "high";
    params.bgcolor = "#ffffff";
    params.allowscriptaccess = "always";
    params.allowfullscreen = "true";
    var attributes = {};
    attributes.id = "orderPrint";
    attributes.name = "orderPrint";
    swfobject.embedSWF(
        "js/OrderPrint.swf", "swf", 
        "100%", "100%", 
        swfVersionStr, xiSwfUrlStr, 
        flashvars, params, attributes);



  </script>

	<div id="flashwrap" style="height:567px;width:945px;border:1px solid #000;">
	<div id="swf"></div>
	<!--object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="orderDesign" width="100%" height="100%"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="OrderDesign.swf"/>
			<param name="quality" value="high" />
			<param name="bgcolor" value="#ffffff" />
			<param name="allowScriptAccess" value="always" />
			<param name="salign" value="left" />
			<embed src="OrderDesign.swf" quality="high" bgcolor="#ffffff"
				width="100%" height="100%" name="orderDesign" 
				play="true"
				loop="false"
				quality="high"
				allowScriptAccess="always" salign="left"
				type="application/x-shockwave-flash"
				pluginspage="http://www.adobe.com/go/getflashplayer">
			</embed>
	</object-->
	
	</div>
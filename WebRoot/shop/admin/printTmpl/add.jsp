<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${staticserver }/js/admin/jqModal1.1.3.1.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jqDnR.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>
<style>
.title {
	border-left: 1px solid #FFFFFF;
	border-right: 1px solid #BEC6CE;
	height: 65px;
	padding: 0 3px;
	float: left;
}
</style>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript">
   var xmldata = "";

	var swfVersionStr = "10.0.0";
    var xiSwfUrlStr = "playerProductInstall.swf";
    var flashvars = {};
    flashvars.data = xmldata;
	flashvars.bg = "";
	swfobject : undefined;
    var params = {};
    params.quality = "high";
    params.bgcolor = "#ffffff";
    params.allowscriptaccess = "always";
    params.allowfullscreen = "true";
    params.wmode = "opaque";
    var attributes = {};
    attributes.id = "orderDesign";
    attributes.name = "orderDesign";
    swfobject.embedSWF(
        "js/OrderDesign.swf", "swf", 
        "100%", "100%", 
        swfVersionStr, xiSwfUrlStr, 
        flashvars, params, attributes);



  //function body_load(){
	//document.getElementById("lockbgel").checked=true; 
  //}

  function getFlashObj(movieName) {
	if (navigator.appName.indexOf("Microsoft") != -1) {
		return window[movieName];
	} else {
		return document[movieName];
	}
  }

  function createItem(obj){
	if(obj.value != "--"){
		var spv = obj.value.split("|");
		getFlashObj("orderDesign").createItem(spv[0],spv[1]);
	}
  }

  function delItem(){
	getFlashObj("orderDesign").delItem();
  }

  function uploadOk(data){
		if(data.result==1){
			$("#bgimage").val(data.path);
			getFlashObj("orderDesign").setBG(data.path);
		}else{
			alert(data.message);
		}
		Eop.Dialog.close("upload");	
	}

  function setBG(){
	  Eop.Dialog.open("upload");
	  $("#upload").load('${ctx}/eop/upload.do?subFolder=printTmpl');
  }

  function lockbg(obj){
		getFlashObj("orderDesign").lockBg(obj.checked);
  }

  function setFontSize(obj){
	if(obj.value != "--"){
		getFlashObj("orderDesign").setFontSize(obj.value);
	}
  }

  function setFont(obj){
	if(obj.value != "--"){
		getFlashObj("orderDesign").setFontType(obj.value);
	}
  }

  function setFontSpace(obj){
	if(obj.value != "--"){
		getFlashObj("orderDesign").setFontSpace(obj.value);
	}
  }

  function setFontBold(obj){
	getFlashObj("orderDesign").setFontBold();
  }

  function setFontItalic(obj){
	getFlashObj("orderDesign").setFontItalic();
  }

  function setTextAlign(value){
	getFlashObj("orderDesign").setTextAlign(value);
  }

  function resizeDesign(type,obj){
	if(type == "width"){
		document.getElementById("flashwrap").style.width = obj.value * 3.2;
	}else if(type == "height"){
		document.getElementById("flashwrap").style.height = obj.value * 3.2;
	}
  }

  function saveDesign(){
	  $("#xmldata").val(getFlashObj("orderDesign").exportData());
	//alert(getFlashObj("orderDesign").exportData());
  }
  </script>
<div class="input">
<form method="post" action="printTmpl!saveAdd.do" class="validate"
	name="theForm" id="theForm">
	<div style="height:67px;width:1000px;border:1px solid #ff0000;">
		<div class="title">
			单据名称<br/>
			<input type="text" name="printTmpl.prt_tmpl_title"/><br/>
			<input type="radio" name="printTmpl.shortcut" value="true" checked/>启用&nbsp;
			<input type="radio" name="printTmpl.shortcut" value="false" />停用"
		</div>
		<div class="title">
			单据尺寸<br/>
			宽<input type="text" style="width:30px;" name="printTmpl.prt_tmpl_width" value="250" onchange="resizeDesign('width',this)"/>*高<input type="text" style="width:30px;" value="177" onchange="resizeDesign('height',this)"/>mm
		</div>
		<div class="title">
			单据背景<br/><input type="hidden" name="printTmpl.bgimage" id="bgimage"/>"
			<input type="checkbox" id="lockbgel" checked onchange="lockbg(this)"/>锁定
		</div>
		<div class="title">
			单据打印项<br/>
			<select onchange="createItem(this);">
				<option value="--">选择添加项</option>
				<option value="收货人-姓名|ship_name">收货人-姓名</option>
				<option value="收货人-地区|ship_area">收货人-地区</option>
				<option value="收货人-地址|ship_addr">收货人-地址</option>
				<option value="收货人-电话|ship_tel">收货人-电话</option>
				<option value="收货人-手机|ship_mobile">收货人-手机</option>
				<option value="收货人-邮编|ship_zip">收货人-邮编</option>
				<option value="发货人-姓名|dly_name">发货人-姓名</option>
				<option value="发货人-地区|dly_area">发货人-地区</option>
				<option value="发货人-地址|dly_address">发货人-地址</option>
				<option value="发货人-电话|dly_tel">发货人-电话</option>
				<option value="发货人-手机|dly_mobile">发货人-手机</option>
				<option value="发货人-邮编|dly_zip">发货人-邮编</option>
				<option value="当日日期-年|date_y">当日日期-年</option>
				<option value="当日日期-月|date_m">当日日期-月</option>
				<option value="当日日期-日|date_d">当日日期-日</option>
				<option value="订单-订单号|order_id">订单-订单号</option>
				<option value="订单总金额|order_price">订单总金额</option>
				<option value="订单费用金额|ship_price">订单费用金额</option>
				<option value="订单物品总重量|order_weight">订单物品总重量</option>
				<option value="订单-物品数量|order_count">订单-物品数量</option>
				<option value="订单-备注|order_memo">订单-备注</option>
				<option value="订单-送货时间|ship_time">订单-送货时间</option>
				<option value="网店名称|shop_name">网店名称</option>
				<option value="tick">对号 - √</option>
				<option value="自定义内容|text">自定义内容</option>
			</select>
			<button onclick="delItem();">删除</button>
		</div>
		<div class="title">
			<select onchange="setFontSize(this);">
				<option value="--">大小</option>
				<option value="10">10</option>
				<option value="12">12</option>
				<option value="14">14</option>
				<option value="18">18</option>

				<option value="20">20</option>
				<option value="24">24</option>
				<option value="27">27</option>
				<option value="30">30</option>
				<option value="36">36</option>
			</select>
			<select onchange="setFont(this);">
				<option value="--">字体</option>
				<option value="宋体">宋体</option>
				<option value="黑体">黑体</option>
				<option value="Arial">Arial</option>
				<option value="Verdana">Verdana</option>
				<option value="Serif">Serif</option>
				<option value="Cursive">Cursive</option>
				<option value="Fantasy">Fantasy</option>
				<option value="Sans-Serif">Sans-Serif</option>
			</select>
			<br/>
			<select name="jianju" id="jianju" style="height: 20px;" onchange="setFontSpace(this);">
			<option value="--" selected="selected">间距</option>
			<option value="-4">-4</option>
			<option value="-2">-2</option>
			<option value="0">0</option>
			<option value="2">2</option>

			<option value="4">4</option>
			<option value="6">6</option>
			<option value="8">8</option>
			<option value="10">10</option>
			<option value="12">12</option>
			<option value="14">14</option>

			<option value="16">16</option>
			<option value="18">18</option>
			<option value="20">20</option>
			<option value="22">22</option>
			<option value="24">24</option>
			<option value="26">26</option>

			<option value="28">28</option>
			<option value="30">30</option>
		  </select>
		  <button onclick="setFontBold();">&nbsp;B&nbsp;</button>
		  <button onclick="setFontItalic();">&nbsp;I&nbsp;</button>
		</div>
		<div class="title">
		  <button onclick="setTextAlign('left');">左对齐</button>
		  <button onclick="setTextAlign('center');">居中对齐</button>
		  <button onclick="setTextAlign('right');">右对齐</button>
		  <br><input type="button" value="设置背景" onclick="setBG()"/>
		  
		</div>
	</div>
	<input type="hidden" name="printTmpl.prt_tmpl_data" id="xmldata" />
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
<div class="submitlist" align="center">
<table>
	<tr>
		<td><input name="submit" type="submit" value=" 保存   "
			class="submitBtn" /></td>
	</tr>
</table>
</div>
</form>
</div>

<div id="upload"></div>

<script type="text/javascript">
$("form.validate").validate();
$(function(){
	Eop.Dialog.init({id:'upload', width:500,height:250,title:'上传模板背景图片'});
	$("form").submit(function(){
		saveDesign();
		});
});
</script>
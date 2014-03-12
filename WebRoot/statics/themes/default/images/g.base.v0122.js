/*
Version Number:    V-20100126-L1
*/
/* minicart */
$(
function(){
var miniCartServiceUrl="http://jd2008.360buy.com/purchase/minicartservice.aspx?callback=?";
refreshMiniCart();
function refreshMiniCart(){
$.login({
automatic:false,
complete:
function(result){
$.getJSON(
miniCartServiceUrl,{
method:"GetCart"},
function(result){
if(result.Cart!=null&&result.GetCart!=null){
$("#ProductNum").text(result.Cart.Num);
$("#MyCart").html(result.GetCart.process(result));}});}});}
$("#MyCart .MyCart_List a[id][name]").livequery(
"click",
function(){
var cartId=parseInt($(this).attr("id"));
var cartType=$(this).attr("name");
if(cartId>0&&cartType!=""){
$.getJSON(
miniCartServiceUrl,{
method:cartType,
cartId:cartId},
function(result){
if(result.Result){
refreshMiniCart();}});}});
$("#Product_Intro_Right img[id^=buy], #View_List img[id^=buy], #View_List :image[id^=buy], #p_Button img[id^=buy], :button[name^=buy], .productCompare img[id^=buy], #Fittings img[id^=pt]").livequery(
"click",
function(){
var productId=parseInt($(this).attr("id").replace("buy","").replace("pt",""));
if(isNaN(productId)||productId==0){
var productId=parseInt($(this).attr("name").replace("buy","").replace("pt",""));}
var count=parseInt($("#Product_Intro_Right .Pro_links :text").val(),10);
count=count>0?count:1;
if(productId>0&&count>0){
window.open("http://jd2008.360buy.com/purchase/InitCart.aspx?pid={0}&pcount={1}&ptype=1".format(productId,count),"_blank");}});
$("#saveCart").livequery(
"click",
function(){
$.login({
complete:
function(result){
if(result.IsAuthenticated){
$.getJSON(
miniCartServiceUrl,{
method:"SaveCart"},
function(result){
if(result.Result){
alert("寄存购物车成功");}});}}});});});
/* search_plug */
var $GLOBAL_VAR={
"_text":"",
"_arr":null,
"_count":0,
"_num":0,
"_select":null,
"_out_select":null,
"_flag":true
};
var $callback={
"hidden":function(){
if($GLOBAL_VAR._flag)
$dt._div.style.display = "none";
},
"input_event":function(event){
var _e = event || window.event;
if ($GLOBAL_VAR._text == $dt._tbox.value || _e.keyCode == "40" || _e.keyCode == "38") {
$o.move(_e);
} else {
$GLOBAL_VAR._count=0;
$GLOBAL_VAR._text = $dt._tbox.value;
if(_e.keyCode=="13"){
$callback.hidden();
}else{
$util.createElement($o.updatelist);
}}}};
var Operator=function(){
this.updatelist=function(){
if(text.length<1){
$dt._div.style.display = "none";
return;
}
$GLOBAL_VAR._arr = text.split("|");
var _t;
var _html = "";
for (var _i = 0; _i < $GLOBAL_VAR._arr.length-1; _i++) {
_t=$GLOBAL_VAR._arr[_i].split(",");
_html+=$pro._htmls[0]+_t[0]
+$pro._htmls[1]+(_i+1)
+$pro._htmls[2]+_t[1]
+$pro._htmls[3]+_t[0]
+$pro._htmls[4];
}
_html+=$pro._htmls[5];
$dt._div.innerHTML = _html;
$dt._div.style.display = "block";
$dt._div.onmouseover=function(){
$GLOBAL_VAR._flag=false;
};
$dt._div.onmouseout=function(){
$GLOBAL_VAR._flag=true;
};
$GLOBAL_VAR._num = $GLOBAL_VAR._arr.length - 1;
};
this.xg=function(){
$GLOBAL_VAR._arr = text.split("|");
var _t;
var _html = "";
for (var _i = 0; _i < $GLOBAL_VAR._arr.length-1; _i++) {
if(_i==10)
continue;
_t=$GLOBAL_VAR._arr[_i].split(",");
if(_t[0].length>10 || _t[0]==$dt._tbox.value)
continue;
_html+=$pro._xghtmls[0]+_t[0]
+$pro._xghtmls[1]+_t[0]
+$pro._xghtmls[2];
}
if(_html.length<1)
$util.get("CorrSearch").style.display="none";
return _html;
};
this.move=function(_e){
if (_e.keyCode == "40") {
$GLOBAL_VAR._count++;
if ($GLOBAL_VAR._count > $GLOBAL_VAR._num) {
	$GLOBAL_VAR._count = 1;
}
this.moveDown();
}
if (_e.keyCode == "38") {
$GLOBAL_VAR._count--;
if ($GLOBAL_VAR._count < 1) {
	$GLOBAL_VAR._count = $GLOBAL_VAR._num;
}
this.moveUp();
}
};
this.moveUp=function(){
$GLOBAL_VAR._select = $util.get("d_"+$GLOBAL_VAR._count);

if($GLOBAL_VAR._count==$GLOBAL_VAR._num)
$GLOBAL_VAR._out_select = $util.get("d_1");
else
$GLOBAL_VAR._out_select = $util.get("d_"+($GLOBAL_VAR._count+1));
$GLOBAL_VAR._out_select.style.backgroundColor="";
$GLOBAL_VAR._select.style.backgroundColor = "#efefef";
$dt._tbox.value = $GLOBAL_VAR._arr[$GLOBAL_VAR._count-1].split(",")[0];
};
this.moveDown=function(){
$GLOBAL_VAR._select = $util.get("d_"+$GLOBAL_VAR._count);

if($GLOBAL_VAR._count==1)
$GLOBAL_VAR._out_select = $util.get("d_"+$GLOBAL_VAR._num);
else
$GLOBAL_VAR._out_select = $util.get("d_"+($GLOBAL_VAR._count-1));
$GLOBAL_VAR._out_select.style.backgroundColor="";
$GLOBAL_VAR._select.style.backgroundColor = "#efefef";
$dt._tbox.value = $GLOBAL_VAR._arr[$GLOBAL_VAR._count-1].split(",")[0];
};
this.selectText=function(_d){
$GLOBAL_VAR._flag=true;
$dt._tbox.value=_d.title;
$callback.hidden();
window.location.href="http://search.360buy.com/Search?keyword="+_d.title;
};
this.over=function(_d){
_d.style.backgroundColor = "#efefef";
};
this.out=function(_d){
_d.style.backgroundColor = "";
};
};
var MyUtil=function(){
this.get=function(_id){
return document.getElementById(_id);
};
this.createElement=function(_callback){
if($dt._tbox.value.length<1){
$dt._div.style.display = "none";
return;
}
var flag=function(){if(_callback==null)	return;};
var _element=null;
var _e=this.get($pro._script_id);
if(_e)
_e.parentNode.removeChild(_e);
_element=document.createElement("script");
_element.id=$pro._script_id;
_element.src = $pro._action+($dt._tbox.value);
_element.type = "text/javascript";
document.getElementsByTagName("head")[0].appendChild(_element);
if(document.all){
_element.onreadystatechange = function(){
var state = _element.readyState;   
if (state == "loaded" || state == "interactive" || state == "complete") {
if(_callback==null){
return;
}
_callback(); 
}   
};   
} else {
flag();
_element.onload = _callback;//FF用   
}   
};
};
var Properties={
"_action" : "http://search.360buy.com/ks?keyword=",
"_input" : "keyword",
"_div" : "Se_tip",
"_script_id" : "_rs",
"_htmls": new Array(
"<div onmouseover=\"$o.over(this)\" onclick=\"$o.selectText(this)\" title=\"",
"\" onmouseout=\"$o.out(this)\" id=\"d_",
"\" class=\"Se_tip_item\"><em>约",
"条</em><span>",
"</span></div>",
"<div class=\"Se_tip_close\"><a href=\"javascript:void(0)\" onclick=\"javascript:this.parentNode.parentNode.style.display='none';\">关闭</a></div>"
),
"_xghtmls": new Array(
"<div><a href=\"Search?keyword=",
"\">",
"</a></div>"
)
};
var showList=function(){
var html="";
for(var i=0;i<list.length;i++){
html+="<li><dl>"
+"<dt><a target=\"_blank\" href=\"http://www.360buy.com/product/"+list[i].id+".html\"><img onerror=\"this.src='http://www.360buy.com/images/none/none_150.gif'\" src=\"http://img10.360buy.com/S2/"+list[i].img_url+"\" alt=\""+list[i].name+"\" /></a></dt>"
+"<dd class=\"p_Name\"><a target=\"_blank\" href=\"http://www.360buy.com/product/"+list[i].id+".html\">"+list[i].name+"</a></dd>"
+"<dd class=\"p_Price\">京东价格：<strong>￥"+list[i].price+"</strong></dd>"
+"</dl></li>"
;
}
document.getElementById("show_sale").innerHTML=html;
};
var Dt=function (t,d){
this._div=d;
this._tbox=t;
};
var $util=new MyUtil();
var $pro=Properties;
var $o=new Operator();
var $dt=new Dt($util.get($pro._input),$util.get($pro._div));
if(null!==$dt._tbox){
$dt._tbox.onkeyup=$callback.input_event;
$dt._tbox.onblur=$callback.hidden;
}
/* favorite */
$(
function(){
var homeServiceUrl="http://jd2008.360buy.com/homeservice.aspx?callback=?";
$("img[id^=coll], :image[id^=coll]").livequery(
"click",
function(){
var current=$(this);
$.login({
complete:
function(result){
if(result!=null&&result.IsAuthenticated!=null&&result.IsAuthenticated){
var productId=parseInt($(current).attr("id").replace("coll",""));
if(productId>0){
$.getJSON(
homeServiceUrl,{
method:"SaveFavorite",
productId:productId},
function(result){
if(result.SaveFavorite!=null){
$("#Collect_Tip").html(result.SaveFavorite.process(result));
showTip2("#Collect_Tip");}});}}}});});});
/* friend */
var jdFriendUrl='http://club.360buy.com/jdFriend/TuiJianService.aspx';
function FriendScript(){
var param=getparam();
if(param!=""){
var js=document.createElement('script');
js.type='text/javascript';
js.src=jdFriendUrl+'?roid='+Math.random()+param;
js.charset='GB2312';
document.getElementsByTagName('head')[0].appendChild(js);}}
window.onload=function(){
FriendScript();}
function getparam(){
var sid="";
var type="";
var args=new Object();
var query=location.search.substring(1);
var pairs=query.split("&");
for(var i=0;i<pairs.length;i++){
var pos=pairs[i].indexOf('=');
if(pos==-1)continue;
var argname=pairs[i].substring(0,pos);
if(pairs[i].substring(0,pos)=="sid"){
sid=unescape(pairs[i].substring(pos+1));}
if(pairs[i].substring(0,pos)=="t"){
type=unescape(pairs[i].substring(pos+1));}}
if(sid!=""||type!=""){
return "&sid="+escape(sid)+"&t="+escape(type);}
else{return "";}}
/* getrecent */
var jdRecent={element:$("#Recentness_View ul"),jsurl:"http://www.360buy.com/lishiset.aspx?id=",cookiename:"_recent",list:$.cookie("_recent"),url:location.href,init:function(){var _matchStr=this.url.match(/\/(\d+).html/);var _id=(_matchStr!=null&&_matchStr[0].indexOf("html")!=-1)?_matchStr[1]:"";if(!this.list||this.list==null||this.list==""){if(_id==""){return this.getData(0);}else{this.list=_id;}}else{if(_id==""||this.list.indexOf(_id)!=-1){this.list=this.list;}else{if(this.list.split(".").length>=10){this.list=this.list.replace(/.\d+$/,"");}this.list=_id+"."+this.list;}}$.cookie(this.cookiename,this.list,{expires:7,path:"/",domain:"360buy.com",secure:false});this.getData(this.list);},clear:function(){$.cookie(this.cookiename,"",{expires:7,path:"/",domain:"360buy.com",secure:false});},getData:function(list){if(list==0){this.element.html("暂无记录!");return;}var rec=list.split(".");for(i in rec){if (i==0){this.element.empty()};$.getJSONP(this.jsurl+rec[i],this.setData);}},setData:function(result){this.element.append("<li><dl><dt><a href='"+result.url+"'><img src='"+result.img+"' /></a></dt><dd class='p_Name'><a href='"+result.url+"'>"+decodeURI(result.name)+"</a></dd></dl></li>");}};if (jdRecent.element.length==1){jdRecent.init();}$("#clearRec").click(function(){jdRecent.clear();jdRecent.getData(0);})
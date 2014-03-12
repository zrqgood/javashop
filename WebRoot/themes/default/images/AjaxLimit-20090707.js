/*
Version Number:    V-20091214-001
      Modifier:    ljb
*/
function calculagraph(){
this._id=null;
this._cT=null;
this._eT=null;
this._lT=null;
this._gT=function(){
if(this._lT==null){
var _xT=(parseInt(this._eT.match(/-(\d+)\s/)[1])>=parseInt(this._cT.match(/-(\d+)\s/)[1]))?(parseInt(this._eT.match(/-(\d+)\s/)[1])-parseInt(this._cT.match(/-(\d+)\s/)[1])):0;
this._cT=parseInt(this._cT.match(/\s(\d+)\D/)[1]*3600)+parseInt(this._cT.split(":")[1]*60)+parseInt(this._cT.split(":")[2]);
this._eT=_xT*24*3600+parseInt(this._eT.match(/\s(\d+)\D/)[1]*3600)+parseInt(this._eT.split(":")[1]*60)+parseInt(this._eT.split(":")[2]);
this._lT=(this._eT-this._cT);}
if(this._lT>=0){
var _H=Math.floor(this._lT/3600);
var _M=Math.floor((this._lT-_H*3600)/60);
var _S=(this._lT-_H*3600)%60;
document.getElementById(this._id).innerHTML="剩余<strong>"+_H+"</strong>小时<strong>"+_M+"</strong>分<strong>"+_S+"</strong>秒";
this._lT--;}else{
document.getElementById(this._id).innerHTML="<strong style='font-size:14px;'>抢购结束</strong>";
clearInterval(this._interval);
switch(this._id){
case "time535":
GetHtmlStr(535);
break;
case "time536":
GetHtmlStr(536);
break;
case "time537":
GetHtmlStr(537);
break;
case "time538":
GetHtmlStr(538);
break;}}}
this._interval=function(){
var o=this;
this._interval=setInterval(function(){o._gT()},1000)}}
function GetHtmlStr(id){
$.ajax({
type:"get",
url:"http://www.360buy.com/limitBuy.aspx",
data:"id="+id,
cache:true,
success:function(result){
//$("#loading"+id).hide();
if(result==""){return false;}
else{
$("#paihang"+id).fadeOut("slow",function(){
$("#hasQiangGou"+id).fadeIn();});
eval(unescape(result).split('|')[0]);
$("#qianggou"+id).html(unescape(result).split('|')[1]);}}});}
GetHtmlStr(536);
GetHtmlStr(538);
GetHtmlStr(535);
GetHtmlStr(537);


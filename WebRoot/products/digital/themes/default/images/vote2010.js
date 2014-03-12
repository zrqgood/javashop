/*
Version Number:    V-20100122-L
*/
function setSuitShows(suitId,style){
if(g('txt'+suitId)==null){return;}
if(style==0){g('txt'+suitId).style.display='none';}
else{g('txt'+suitId).style.display='';}}
function g(nodeId){
return document.getElementById(nodeId);}
function questionSubmit(){
var i=0;
var j=0;
var x=0;
var y=0;
var z=0;
var n=0;
var messages='';
var radiov;
var radiovs;
var radio;
var radios;
var radioq;
var radioqs;
var checks;
var checkboxs;
var hischeck;
var hcountf;
var hischeckvalue;
var hcountfvalue;
var htitle;
var htitlecontent;
for(i=0;i<30;i++){
radiov='radiov'+i;
htitle='htitle'+i;
hischeck='hIsCheck'+i;
hcountf='hcountf'+i;
if(document.getElementById(hischeck)!=null){
hischeckvalue=document.getElementById(hischeck).value;
if(hischeckvalue!=''&&hischeckvalue=='1'){
if(document.getElementById(hcountf)!=null){
hcountfvalue=document.getElementById(hcountf).value;
radio='radio_option'+hcountfvalue;
if(document.getElementsByName(radio)!=null){
radios=document.getElementsByName(radio);
if(radios.length>0){
y=0;
y=y+1;
for(j=0;j<radios.length;j++){
if(radios[j]!=undefined){
if(radios[j].checked){
y=y-1;
break;}}}
if(y>0){
if(document.getElementsByName(htitle)!=null){
messages=messages+document.getElementById(htitle).value+'\n';}}}}}}}
if(document.getElementById(hischeck)!=null){
hischeckvalue=document.getElementById(hischeck).value;
if(hischeckvalue!=''&&hischeckvalue=='1'){
hcountfvalue=document.getElementById(hcountf).value;
checkboxs='checkbox'+hcountfvalue;
if(document.getElementsByName(checkboxs)!=null){
checks=document.getElementsByName(checkboxs);
if(checks.length>0){
x=0;
x=x+1;
for(n=0;n<checks.length;n++){
if(checks[n]!=undefined){
if(checks[n].checked){
x=x-1;
break;}}}
if(x>0){
messages=messages+document.getElementById(htitle).value+'\n';}}}}}
if(document.getElementById(hischeck)!=null){
hischeckvalue=document.getElementById(hischeck).value;
if(hischeckvalue!=''&&hischeckvalue=='1'){
hcountfvalue=document.getElementById(hcountf).value;
radioq='radioq'+hcountfvalue;
if(document.getElementById(radioq)!=null){
if(document.getElementById(radioq).value==''){
messages=messages+document.getElementById(htitle).value+'\n';}}}}}
if(messages!=''){
messages=messages+'Îª±ØÌîÏî';
alert(messages);
return;}
document.getElementById('voteform').submit();}
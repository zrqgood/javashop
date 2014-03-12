var Browser = new Object();

Browser.isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != - 1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != - 1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != - 1);

var Utils = new Object();

/**
检测某批checkbox 是否有选择
*/
Utils.chk_selected = function(chkname){
	 var checks = document.getElementsByName(chkname);	   
	 var flag = false;
	 for(var i=0;i<checks.length;i++){
      	if(checks[i].checked){
      		 	flag = true;
      		 	break;
      	}
	 }
	 
	 return flag;
}




Utils.sel_selected = function(sel_id,selected_value){
	var sel = document.getElementById(sel_id);
	var options = sel.options;
	for(var i=0;i<options.length;i++){
		if(options[i].value ==  selected_value ){
			options[i].selected = true;
		}
	}
}



Utils.chk_ctl_all=function(name,checked){
	 
	 var checks = document.getElementsByName(name);
	  for(var i=0;i<checks.length;i++){
		  checks[i].checked= checked;
		
	  }
}




Utils.htmlEncode = function(text)
{
  return text.replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

Utils.trim = function( text )
{
  if (typeof(text) == "string")
  {
    return text.replace(/^\s*|\s*$/g, "");
  }
  else
  {
    return text;
  }
}

Utils.isEmpty = function( val )
{
  switch (typeof(val))
  {
    case 'string':
      return Utils.trim(val).length == 0 ? true : false;
      break;
    case 'number':
      return val == 0;
      break;
    case 'object':
      return val == null;
      break;
    case 'array':
      return val.length == 0;
      break;
    default:
      return true;
  }
}

Utils.isNumber = function(val)
{
  var reg = /^[\d|\.|,]+$/;
  return reg.test(val);
}

Utils.isInt = function(val)
{
  if (val == "")
  {
    return false;
  }
  var reg = /\D+/;
  return !reg.test(val);
}

Utils.isEmail = function( email )
{
  var reg1 = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;

  return reg1.test( email );
}

Utils.isTel = function ( tel )
{
  var reg = /^[\d|\-|\s|\_]+$/; //只允许使用数字-空格等

  return reg.test( tel );
}	 


Utils.srcElement = function(e)
{
  if (typeof e == "undefined") e = window.event;
  var src = document.all ? e.srcElement : e.target;

  return src;
}



Utils.addRow=function(rowid)
{     
 	  var prop_row  = document.getElementById(rowid);
 	  
 	  var cells = prop_row.cells;
 	  
      var tbl  = prop_row.parentNode;
       
   	  var row  = tbl.insertRow( tbl.rows.length-1 );
	var a= $(row).find(':text');
	
   	  for(var i=0;i<cells.length;i++){
   	  	var cell = row.insertCell();
   	  
			if(i==3){
				var img =cells[i].getElementsByTagName("img")[0];
		 
			    img.style.display='block';
			}
			
		 	cell.innerHTML = cells[i].innerHTML;
			if(i==3){
				img.style.display='none';
			}
				
   	  }  	  
   	  $(row).find(':text').val('');
	  //$(row).find(':selected').get(0).selected=true;
    	
}

  
Utils.delRow=function(row){
  	 var tbl  = row.parentNode;
 	 tbl.removeChild(row);
}

function setTitle(title){
	document.getElementById("nav_title").innerHTML=title;
}



var process_request = "正在处理您的请求...";
/* *
 * 显示载入信息
 */
function showLoader()
{
  document.getElementsByTagName('body').item(0).style.cursor = "wait";

  if (top.frames['header-frame'])
  {
    top.frames['header-frame'].document.getElementById("load-div").style.display = "block";
  }
  else
  {
    var obj = document.getElementById('loader');

    if ( ! obj && process_request)
    {
      obj = document.createElement("DIV");
      obj.id = "loader";
      obj.innerHTML = process_request;

     document.body.appendChild(obj);
    }
  }
}

/* *
 * 隐藏载入信息
 */
function hideLoader()
{
  document.getElementsByTagName('body').item(0).style.cursor = "auto";
  if (top.frames['header-frame'])
  {
    setTimeout(function(){top.frames['header-frame'].document.getElementById("load-div").style.display = "none"}, 10);
  }
  else
  {
    try
    {
      var obj = document.getElementById("loader");
      obj.style.display = 'none';
      document.body.removeChild(obj);
    }
    catch (ex)
    {}
  }
}

function setLoderText(text){
   try
    {
      var obj = document.getElementById("loader");
	     obj.innerHTML = text;
    }
    catch (ex)
    {}
}

  

var grid_url;
var grid_id;
 
function goPage(page){
	showLoader();
	$.ajax({
	type: "get",
	url:grid_url,
	data:page,
	dataType:"html",
	success:function(result){
	   document.getElementById(grid_id).innerHTML = result;
	 hideLoader();
	 cell_color();
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
}


function goThemePage(post_data){
	
	showLoader();
	$.ajax({
	type: "get",
	url:grid_url+"?m=" + new Date().getTime(),
	data:post_data,
	dataType:"html",
	success:function(result){
	   document.getElementById(grid_id).innerHTML = result;
	 
	 hideLoader();
	 cell_color();
	 
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
	 
}



function reLoad(){
	//alert(grid_url);
	goPage(grid_url);
}


function proess(action,param,text){

  if (confirm(text)){
	showLoader();
	$.ajax({
		type: "get",
		url:action,
		data:param+"&rdm=" + new Date().getTime(),
		dataType:"json",
		success:function(result){
			 hideLoader();
		   
		   if(result.result==0){
			   reLoad();
		   }else{
  				alert(result.message);
  			}
		 
		},
		 error :function(res){alert("请求失败" + res);}
	});
	}
}


function gridpost(formid,_url){
 
	var options = { 
		 url:_url,
		 type:"post",
		 dataType: 'json',
		 success: function(result){
		 	
		   hideLoader();
		   
		   if(result.result==0){
			    reLoad();
		   }else if(result.result==1){ 
		   		reLoad();
		    	alert(result.message);
  			}else if(result.result==2){
  				alert(result.message);
  			}
  					 	
		 },
		 error:function(e){
		 	alert("出现错误11 " +e);
		 	 hideLoader();
		 }
     }; 


	showLoader();
	$('#' + formid).ajaxSubmit(options); 
 
	
}



function grid_query(formid,url){
 
	var options = { 
		 url:url,
		 dataType: 'html',
		 success: function(result){
		 	  document.getElementById(grid_id).innerHTML = result;
		    hideLoader();
  					 	
		 },
		 error:function(e){
		 	alert("出现错误 ");
		 }
     }; 


	showLoader();
	$('#' + formid).ajaxSubmit(options); 
 
	
}



function cell_color(){



if (document.getElementById("gridDiv"))
{
  document.getElementById("gridDiv").onmouseover = function(e)
  {
    obj = Utils.srcElement(e);

    if (obj)
    {
      if (obj.parentNode.tagName.toLowerCase() == "tr") row = obj.parentNode;
      else if (obj.parentNode.parentNode.tagName.toLowerCase() == "tr") row = obj.parentNode.parentNode;
      else return;

      for (i = 0; i < row.cells.length; i++)
      {
        if (row.cells[i].tagName != "TH") row.cells[i].style.backgroundColor = '#F4FAFB';
      }
    }

  }



  document.getElementById("gridDiv").onmouseout = function(e)
  {
    obj = Utils.srcElement(e);

    if (obj)
    {
      if (obj.parentNode.tagName.toLowerCase() == "tr") row = obj.parentNode;
      else if (obj.parentNode.parentNode.tagName.toLowerCase() == "tr") row = obj.parentNode.parentNode;
      else return;

      for (i = 0; i < row.cells.length; i++)
      {
          if (row.cells[i].tagName != "TH") row.cells[i].style.backgroundColor = '#FFF';
      }
    }
  }
 }
  
  
}



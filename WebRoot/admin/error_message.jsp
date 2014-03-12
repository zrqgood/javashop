<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<form id="errorform" action="http://www.enationsoft.com/eop/errorReport.do" method="post">
<div    class='success'  style="height:200px">
	 
	  <h4>哎呀，javashop出现了一个意外的错误:(<br/></h4>
	     
  <div style="clear:both;"> 
  
        <p>
        
   你愿意将这个错误提交给我们吗？ 这会使javashop更加完善、稳定！
  <input type="button" name="bu" id="sendBtn" value="报告此错误" />
 
    </p>
     <textarea style="width:400px;height:100px" name="info">在这里输入具体操作信息，会帮助我们更快的解决问题。</textarea>
   
  
   </div>
</div>
<input type="hidden" name="error"  value="<s:property value="exceptionStack" />"/>
 
</form>
<div>


<h4>Exception Name: <s:property value="exception" /> </h4>
<a href="javascript:;" id="showBtn">显示完整错误信息</a>
<div style="display:none;" id="errorbox">Exception Details: <s:property value="exceptionStack" /></div> 
</div>
<script>
$(function(){
	$("#showBtn").click(function(){
		$("#errorbox").show();
	});
	$("#sendBtn").click(function(){
		$.Loading.show("正在提交..");
		$("#errorform").ajaxSubmit({
				url :"<%=request.getContextPath() %>/core/admin/errorReport.do?ajax=yes",
					type : "POST",
					dataType : 'html',
					success : function(result) {
						$.Loading.hide();
						 alert("我们成功已经收到错误报告，感谢您的支持!");
					},
					error : function(e) {
						$.Loading.hide();
						 alert("出错啦:(");
					}			
		});
	});
});
</script>
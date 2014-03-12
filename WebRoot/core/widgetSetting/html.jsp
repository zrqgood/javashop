<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<br/>
<input type="hidden" id="html_id" name="html_id" eop_type="widget_params"></input>"
<textarea style="width:500px;height:330px" id="html_content" name="html_content">${html }</textarea>
<script type="text/javascript">
WidgetDialog.onOpen=function(){
	KE.init({
	    id : 'html_content'
	  
	});
	KE.create('html_content');	
};

WidgetDialog.onSubmit=function(){
	KE.util.setData("html_content");
};
</script>
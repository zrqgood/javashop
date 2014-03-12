<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<c:set var="fieldList" value="${fieldList}" />
<div class="grid">
	<form action="data!list.do" method="post">
		<input type="hidden" name="catid" value="${catid }"/>
		<div class="toolbar">
			<ul>
				<li><a href="data!add.do?catid=${catid}">添加</a></li>
				<li><a href="javascript:;" id="sortBtn">保存排序</a></li>
				<li>关键字：<input type="text" name="searchText" value="${searchTitle }" /></li>
				<li>
					<select name="searchField">
						<c:forEach items="${fieldList}" var="field">
							<option value="${field.english_name }">${field.china_name}</option>
						</c:forEach>
					</select>
				</li>
				<li><input type="submit"  name="submit" value="搜索"/></li>
				<c:if test="${site.multi_site == 1 }"><li><a href="javascript:;" id="importBtn">导入</a></li></c:if>
			</ul>
			<div style="clear:both"></div>
		</div>
	</form>
	<form id="sortForm" method="post">
		<input type="hidden" name="catid" value="${catid }"/>
		<grid:grid from="webpage">
			<grid:header>
				<grid:cell width="50px">id</grid:cell>
				<c:forEach items="${fieldList}" var="field">
					<th style="width:100px">${field.china_name}</th>
				</c:forEach>
				<th style="width:80px">子栏目</th>
				<th style="width:80px">排序</th>
				<th style="width:100px">添加时间</th>
				<th style="width:100px">修改</th>
				<th style="width:100px">删除</th>
			</grid:header> 	
	  		<grid:body item="article">
	  			<grid:cell>${article.id }<input type="hidden" name="ids" value="${article.id }" /> </grid:cell>
	  			<html:field></html:field>
  				<td><c:if test="${article.cat_id!=catid}">${article.cat_name }</c:if></td>
  				<td><input type="text" style="width:50px" name="sorts" value="${article.sort }" /></td>
  				<td><fmt:formatDate value="${article.add_time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
	    		<td><a href="data!edit.do?dataid=${article.id }&catid=${catid}"><img src="images/transparent.gif" class="modify"></a></td>
	    		<td>
	    			<c:if test="${article.sys_lock != 1 }">
	    				<a href="data!delete.do?dataid=${article.id }&catid=${catid}" onclick="javascript:return confirm('确定删除此文章吗?');"><img src="images/transparent.gif" class="delete"></a>
					</c:if>
				</td>
			</grid:body>
		</grid:grid>
	</form>
	<div style="clear:both;padding-top:5px;"></div>
	<div id="import_selected"></div>
</div>
<script type="text/javascript">
function updateSort(){
	$.Loading.show('正在保存排序，请稍侯...');
	var options = {
			url :"data!updateSort.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			success : function(result) {				
			 	if(result.result==1){
			 		$.Loading.hide();
			 		alert("更新成功");
			 		location.reload();
			 	}else{
			 		alert(result.message);
			 	}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出错啦:(");
				}
		};

	$("#sortForm").ajaxSubmit(options);		
}
$(function(){

	/**初始化导入数据对话框**/
	Eop.Dialog.init({id:"import_selected",modal:true,title:"导入数据", width:"600px"});
	
	/**排序**/
	$("#sortBtn").click(function(){
		updateSort();
	});

	/**导入按钮点击事件**/
	$("#importBtn").click(function(){

		//抓取导入对话框内容
		$.ajax({
			 type: "GET",
			 url: "${ctx}/cms/admin/data!implist.do?ajax=yes&catid=${catid }",
			 dataType:'html',
			 success: function(result){
				 $("#import_selected").empty().append(result);
		     },
		     error:function(){
				alert("数据列表获取失败");
			 }
		});

		//打开导入对话框
		Eop.Dialog.open("import_selected");
		
	});
});
</script>
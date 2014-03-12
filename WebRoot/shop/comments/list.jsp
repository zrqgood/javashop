<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<style>
td, th {
padding:1px 4px;
}

.division {
background:none repeat scroll 0 0 #FFFFFF;
border-color:#CCCCCC #BEC6CE #BEC6CE #CCCCCC;
border-style:solid;
border-width:1px 2px 2px 1px;
margin:10px;
padding:5px;
white-space:normal;
}
</style>
<ul>
<c:forEach var="comments" items="${webPage.result }">
	<li>${comments.title }<br/>${comments.comment }</li>
	<c:forEach var="reply" items="${comments.list }">
		<div>${reply.comment }</div>
	</c:forEach>
</c:forEach>
</ul>
<div class="division">
	<form  method="post" action="comments!add.do" class="validate">
	<table>
		<tr>
			<td class="label">
				<input type="hidden" name="comments.object_id" value="${object_id }" />
				<input type="hidden" name="comments.object_type" value="discuss" /><!-- 表示是评论 -->
				<input type="hidden" name="comments.author" value="当前用户" />
				<input type="hidden" name="comments.author_id" value="5" />
				<input type="hidden" name="direct_show" value="0" />
				<h5>发表评论：</h5>
				<h5>标题：</h5>
				<input type="text" name="comments.title" />
				<h5>联系方式：</h5>
				<input type="text" name="comments.contact" />
				<h5>评论内容：</h5>
				<textarea rows="7" cols="70" name="comments.comment" ></textarea>
			</td>
		</tr>
		<tr>
			<td >
				<input type="reset" value="重置" class="btn"/>&nbsp;
				<input type="submit" value="回复" class="btn"/>
			</td>
		</tr>
	</table>
	</form>
</div>

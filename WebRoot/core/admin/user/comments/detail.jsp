<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<style>
*{margin:0;padding:0;border:0;font-weight:inherit;font-style:inherit;font-size:100%;font-family:inherit;vertical-align:baseline;}
td, th {
padding:1px 4px;
}
.tableform {
background:none repeat scroll 0 0 #EFEFEF;
border-color:#DDDDDD #BEC6CE #BEC6CE #DDDDDD;
border-style:solid;
border-width:1px;
margin:10px;
padding:5px;
}

tableform h2 span{color:#777;font-weight:normal;}
.tableform h3 span{font-weight:normal;float:right;}
.tableform h3 .assis{font-weight:normal;color:#000;}
.tableform h3 span a{color:#777;font-weight:normal;}
.division {
background:none repeat scroll 0 0 #FFFFFF;
border-color:#CCCCCC #BEC6CE #BEC6CE #CCCCCC;
border-style:solid;
border-width:1px 2px 2px 1px;
line-height:150%;
margin:10px;
padding:5px;
white-space:normal;
}
.tableform .division td a{padding:2px 5px 0 5px;margin:0 3px;color:#000;line-height:20px;}
.tableform .intro{color:#369;padding-left:15px;}
</style>
<script type="text/javascript">

$.Validator.options={lang:{required:'必须!'}};
</script>
<div class="input">
<table>
	<tr>
		<td width="220px" style="vertical-align: top;">
			<div class="tableform">
			<ul>
				<li>评论人：${commentDTO.comments.author }</li>
				<li>评论商品：${commentDTO.comments.object_id }</li>
				<li>发表时间：[<html:dateformat pattern="yyyy-MM-dd" time="${commentDTO.comments.time }"></html:dateformat>]</li>
			</ul>
			</div>
		</td>
		<td class="tableform">
			<h5>评论内容：<span>[<c:if test="${commentDTO.comments.display == 'true' }"><a href="comments!hide.do?comment_id=${commentDTO.comments.comment_id }">隐藏</a></c:if><c:if test="${commentDTO.comments.display != 'true' }"><a href="comments!show.do?comment_id=${commentDTO.comments.comment_id }">显示</a></c:if>] [<a href="comments!deletealone.do?comment_id=${commentDTO.comments.comment_id }">删除</a>]</span></h5>
				<div class="division"><h5>${commentDTO.comments.title }</h5>
				${commentDTO.comments.comment }
				</div>
				
			<div class="division">
			<form  method="post" action="comments!add.do" class="validate">
			<table>
				<tr>
					<th>
						<input type="hidden" name="comments.for_comment_id" value="${commentDTO.comments.comment_id }" />
						<input type="hidden" name="comments.object_id" value="${commentDTO.comments.object_id }" />
						<input type="hidden" name="comments.object_type" value="${commentDTO.comments.object_type }" />
						<input type="hidden" name="comments.author" value="管理员" />
						<input type="hidden" name="comments.author_id" value="${managerid }" />
						<h5>回复用户评论：</h5>
						<textarea rows="7" cols="70" name="comments.comment" style="border: solid #069 1px;"></textarea>
					</th>
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
			<c:forEach var="reply" items="${commentDTO.list }">
			<div class="tableform">
				<h5>${reply.author } 于[<html:dateformat pattern="yyyy-MM-dd" time="${reply.time }"></html:dateformat>]回复：[<c:if test="${reply.display == 'true' }"><a href="comments!hide.do?comment_id=${reply.comment_id }">隐藏</a></c:if><c:if test="${reply.display != 'true' }"><a href="comments!show.do?comment_id=${reply.comment_id }">显示</a></c:if>] [<a href="comments!deletealone.do?comment_id=${reply.comment_id }">删除</a>]</h5>
				<div class="division"><h5>联系方式：${reply.contact }</h5>
				${reply.comment }
				</div>
			</div>
			</c:forEach>
		</td>
	</tr>
</table>
</div>

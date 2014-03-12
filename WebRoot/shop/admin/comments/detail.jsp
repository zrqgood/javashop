<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Comments.js"></script>
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
$(function(){
$("#comments_commit").click(function(){
	$("#comments_commit").attr("disabled",true); 
	$.Loading.show('正在回复留言，请稍侯...');
	var options = { 
			url : "comments!add.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			success : function(result) {
				$.Loading.hide();				
 				if(result.state==0){
					$("#comments_commit").attr("disabled",false); 
					$("#commentscontent").html("");
					location.reload();
		 		}else{
			 		alert(result.message);
			 		$("#comments_commit").attr("disabled",false); 
			 	}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
				$("#comments_commit").attr("disabled",false); 
			}
	};

	$('#addcomment').ajaxSubmit(options);	
});
});
</script>
<div class="input">
<table>
	<tr>
		<td width="220px" style="vertical-align: top;">
			<div class="tableform">
			<ul>
				<li>评论人：${commentDTO.comments.author }</li>
				<li>${target }</li>
				<li>发表时间：[<html:dateformat pattern="yyyy-MM-dd" time="${commentDTO.comments.time }"></html:dateformat>]</li>
			</ul>
			</div>
		</td>
		<td class="tableform">
			<h5>评论内容：
				<span>
					[<a href="comments!deletealone.do?comment_id=${commentDTO.comments.comment_id }">删除</a>]
					[<c:if test="${commentDTO.comments.display == 'true' }"><input type="button" class="chide" comment_id="${commentDTO.comments.comment_id }" value="隐藏" /></c:if>
        			<c:if test="${commentDTO.comments.display == 'false' }"><input type="button" class="cshow" comment_id="${commentDTO.comments.comment_id }" value="显示" /></c:if>]
				</span>
			</h5>
				<div class="division"><h5>${commentDTO.comments.title }</h5>
				${commentDTO.comments.comment }
				</div>
				
			<div class="division">
			<form  method="post" action="comments!add.do" class="validate" id="addcomment">
			<table>
				<tr>
					<th>
						<input type="hidden" name="comments.for_comment_id" value="${commentDTO.comments.comment_id }" />
						<input type="hidden" name="comments.object_id" value="${commentDTO.comments.object_id }" />
						<input type="hidden" name="comments.commenttype" value="${commentDTO.comments.commenttype }" />
						<input type="hidden" name="comments.object_type" value="${commentDTO.comments.object_type }" />
						<input type="hidden" name="managerid" value="${managerid }" />
						<input type="hidden" name="validcode" value="0" />
						<input type="hidden" name="direct_show" value="1" />
						<h5>回复用户评论：</h5>
						<textarea rows="7" cols="70" name="comments.comment" style="border: solid #069 1px;" id="commentscontent"></textarea>
					</th>
				</tr>
				
			</table>
			<div class="submitlist" align="center">
 <table>
 <tr><td >
  <!--input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" /-->
  <a href="javascript:;" id="comments_commit">回复</a>
   </td>
   </tr>
 </table>
</div>
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

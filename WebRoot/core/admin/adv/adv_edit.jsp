<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck" %>
<div class="input">
	<div class="toolbar">
		<ul>
			<li>广告编辑</li>
		</ul>
		<div style="clear: both;"></div>
	</div>
	<form class="validate" method="post" action="adv!editSave.do" name="theForm" id="theForm" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
			<tr>
				<th style="width:140px"><label class="text"><input type="hidden" name="adv.aid" value="${advid }"/>广告名称：</label></th>
				<td><input type="text" name="adv.aname" value="${adv.aname }" maxlength="60" dataType="string" required="true" />&nbsp;&nbsp;<span class="help_icon" helpid="ad_name"></span></td>
			</tr>
			<tr>
				<th style="width:140px"><label class="text">广告位置：</label></th>
				<td>
					<select name="adv.acid">
					<c:forEach var="item" items="${adColumnList }">
						<option value="${item.acid }"  <c:if test="${item.acid == adv.acid }">selected</c:if>>${item.cname }</option>
					</c:forEach>
					</select>&nbsp;&nbsp;<span class="help_icon" helpid="adc_name"></span>
				</td>
			</tr>
			<tr>
				<th style="width:140px"><label class="text">是否开启：</label></th>
				<td>
					<input type="radio" name="adv.isclose" value="0" <c:if test="${adv.isclose == 0 }">checked</c:if> /> 开启&nbsp;&nbsp;
					<input type="radio"	name="adv.isclose" value="1" <c:if test="${adv.isclose == 1 }">checked</c:if>/> 关闭&nbsp;&nbsp;
					<span class="help_icon" helpid="ad_isclose"></span>
				</td>
			</tr>
			<tr>
				<th style="width:140px"><label class="text">起始时间：</label></th>
				<td><input type="text" name="mstartdate" value="<html:dateformat pattern="yyyy-MM-dd" time="${adv.begintime }"></html:dateformat>" maxlength="60" dataType="date" required="true" class="dateinput"/></td>
	 		</tr>
			<tr>
				<th style="width:140px"><label class="text">终止时间：</label></th>
				<td><input type="text" name="menddate" value="<html:dateformat pattern="yyyy-MM-dd" time="${adv.endtime }"></html:dateformat>" maxlength="60"  dataType="date" required="true" class="dateinput"/></td>
			</tr>
			<tr>
				<th style="width:140px"><label class="text">广告链接：</label></th>
				<td><input type="text" name="adv.url" value="${adv.url }" maxlength="60" dataType="string"/></td>
			</tr>
			<tr>
				<th style="width:140px"><label class="text">上传广告文件：</label></th>
				<td><input type="file" name="pic" id="pic" size="45"/>&nbsp;&nbsp;<span class="help_icon" helpid="ad_file"></span></td>
			</tr>
			<c:if test="${adv.atturl !=null }">
			<tr>
				<th style="width:140px">&nbsp;</th>
				<td><img src="${adv.atturl  }"  /></td>
			</tr>
			</c:if>      
			<tr>
				<th style="width:140px"><label class="text">单位名称：</label></th>
				<td><input type="text" name="adv.company" value="${adv.company }" maxlength="60" dataType="string" />&nbsp;&nbsp;<span class="help_icon" helpid="ad_link"></span></td>
			</tr>
			<tr>
				<th style="width:140px"><label class="text">联系人：</label></th>
				<td><input type="text" name="adv.linkman" value="${adv.linkman }" maxlength="60" dataType="string" />&nbsp;&nbsp;<span class="help_icon" helpid="ad_link"></span></td>
			</tr>
			<tr>
				<th style="width:140px"><label class="text">联系方式：</label></th>
				<td><input type="text" name="adv.contact" value="${adv.contact }" maxlength="60" dataType="string" />&nbsp;&nbsp;<span class="help_icon" helpid="ad_link"></span></td>
			</tr>
		</table>
		<div class="submitlist" align="center">
		<table>
			<tr>
				<td>
					<input name="submit" type="submit" value=" 确    定   " class="submitBtn" />
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<script type="text/javascript">
	$("form.validate").validate();
	$(function(){
		if($("img").width()>600){$("img").attr("width","600");}
	});
</script>
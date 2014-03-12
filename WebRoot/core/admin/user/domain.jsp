<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>

<script type="text/javascript" src="../js/DomainOperator.js">
</script>

<div class="grid">
	<div class="toolbar">
		<ul>
			<li>
				<input type='hidden' name='siteid' id='siteid' value='${eopSite.id }'>
			请输入要添加的域名:<input type="text" name="domainname"  id="domainname"/>
			<input type="button" id="btnAdd" value="添加" />
			</li>
		</ul>
		<div style="clear:both"></div>
	</div>
 
	<div id="list_wrapper" style="font-size: 12px;"></div>

	</td>
 
</div>
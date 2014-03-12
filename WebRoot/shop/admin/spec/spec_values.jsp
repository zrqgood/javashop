<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div id="spec-tab-${spec.spec_id }" specid="${spec.spec_id}" specname="${spec.spec_name }" style="display: none" class="item">
<table>
	<tbody>
		<tr>
			<td width="20%" style="vertical-align: middle; text-align: center;">
			<span style="line-height: 18px;" class="fontcolorGray">请点击右侧<br>
			添加本商品需要的${spec.spec_name }</span></td>
			<td width="80%">
			<div>&nbsp; <span   class="add-spec-all" style="color:#000099;cursor:pointer;text-decoration:underline;">添加全部${spec.spec_name }</span>
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			</div>
			<ul spectype="text" specid="2" class="spec_box spec-name">

				<c:forEach items="${valueList}" var="val">
				
					<c:if test="${spec.spec_type==0}">
						<li class="specli"  specvid="${val.spec_value_id }"
							onmouseout="this.style.background='#ffffff';"
							onmouseover="this.style.background='#ff9900';"
							style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">
						<span>${val.spec_value }</span><span class="spec_add" specvid="${val.spec_value_id }"><label>添加</label></span>
						 </li>
					</c:if>
					
					<c:if test="${spec.spec_type==1}">
						<li class="specli"  specvid="${val.spec_value_id }"  onmouseout="this.style.background='#ffffff';" onmouseover="this.style.background='#ff9900';" style="background: none repeat scroll 0% 0% rgb(255, 255, 255);">
							<img height="20" width="20" alt="${ val.spec_value }" src="${val.spec_image}">
							<span class="spec_add" ><label>添加</label></span>
						</li>
				  	</c:if>				 
				</c:forEach>
			</ul>
			</td>
		</tr>
	</tbody>
</table>

<div class="tableform">
<div class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="finderInform">
	<thead>
		<tr>
			<th style="width: 15%;">系统规格</th>
			<th style="width: 25%;">关联商品相册图片</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody class="spec-body grid">

	</tbody>
</table>
</div>
</div>
</div>
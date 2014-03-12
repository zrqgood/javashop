<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
		<c:forEach items="${listRegion }" var="region">
		<tr parentid="${region.p_region_id }" id="${region.region_id }">
			<td style="text-align: left; width: 300px; overflow: hidden;">
			<div style="padding-left: ${region.region_grade * 15 + 30 }px;"><img status="closed" 
				src="images/<c:if test="${region.childnum>0 }">sitemapclosed.gif</c:if><c:if test="${region.childnum==0 }">sitemapopened.gif</c:if>" id="${region.region_id }" <c:if test="${region.childnum>0 }">class="imgTree"</c:if> > 
				<a href="region!edit.do?region_id=${region.region_id }&regiongrade=${region.region_grade }"
				style="font-weight: 700; color: rgb(0, 0, 0); text-decoration: none; padding-right: 15px;">${region.local_name }</a></div>
			</td>
			<td style="width: 100px;"><a
				href="region!add.do?parentid=${region.region_id }&regiongrade=${region.region_grade + 1 }">添加子地区</a></td>
			<td style="width: 60px;" align="center">
			<a	href="region!edit.do?region_id=${region.region_id }&regiongrade=${region.region_grade }"  >
				<img src="images/transparent.gif" class="modify"/>
				</a></td>
			<td style="width: 60px;" align="center">
			<a	href="javascript:;" >
				<img src="images/transparent.gif" class="delete"  region_id="${region.region_id }" />
			</a>
			</td>
			<td></td>
		</tr>
		</c:forEach>
<%
  String advurl = (String)request.getAttribute("gourl");
  response.sendRedirect(advurl);
%>
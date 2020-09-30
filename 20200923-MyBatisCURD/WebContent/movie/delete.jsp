<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies=request.getCookies();
	for(Cookie c:cookies)
	{
		if(c.getName().startsWith("m"))
		{
			c.setPath("/");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		
	}
	response.sendRedirect("../main/main.jsp");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
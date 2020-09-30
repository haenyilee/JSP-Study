<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
 <%
 	String no=request.getParameter("no");
 	MovieVO vo= MovieDAO.movieDetailData(Integer.parseInt(no));
	 // 쿠키 저장하기
    Cookie cookie = new Cookie("m"+no,vo.getPoster());
	cookie.setPath("/");
    cookie.setMaxAge(60*60*24);
    response.addCookie(cookie);
    // mode=8 : detail.jsp
    response.sendRedirect("../main/main.jsp?mode=8&no="+no);
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
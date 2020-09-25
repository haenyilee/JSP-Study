<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.temp.*"%>
<%
	request.setCharacterEncoding("utf-8");


%>
<jsp:useBean id="bean" class="com.sist.temp.MemberBean">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jsp액션태그로 받기</h1>
	이름:<%= bean.getName() %><br>
	성별:<%= bean.getSex() %><br>
	나이:<%= bean.getAge() %><br>
	주소:<%= bean.getAddr() %><br>
	번호:<%= bean.getTel() %><br>

</body>
</html>
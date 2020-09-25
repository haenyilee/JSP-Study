<%@page import="com.sist.temp.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 사용자가 보내준 데이터 받기
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String age=request.getParameter("age");
	String addr=request.getParameter("addr");
	String tel=request.getParameter("tel");
	
	MemberBean bean = new MemberBean();
	bean.setName(name);
	bean.setSex(sex);
	bean.setAge(Integer.parseInt(age));
	bean.setAddr(addr);
	bean.setTel(tel);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름:<%= bean.getName() %><br>
	성별:<%= bean.getSex() %><br>
	나이:<%= bean.getAge() %><br>
	주소:<%= bean.getAddr() %><br>
	번호:<%= bean.getTel() %><br>

</body>
</html>
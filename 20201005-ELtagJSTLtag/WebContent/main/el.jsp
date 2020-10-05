<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String name="홍길동";
	String sex="남자";
	
	request.setAttribute("name", name); // request에 새로운 데이터를 추가하는 방식
	request.setAttribute("sex", sex);
	
	String name1="심청이";
	String sex1="여자";
	
	session.setAttribute("name1", name); // session에 새로운 데이터를 추가하는 방식
	session.setAttribute("sex1", sex);
	
	String name2="헨리";
	String sex2="여자";	
	
	application.setAttribute("name2", name); // application에 새로운 데이터를 추가하는 방식
	application.setAttribute("sex2", sex);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>request에 있는 데이터 출력</h1>
	이름(방식1):<%=request.getAttribute("name") %> <br>
	이름(방식2):${requestScope.name} <br>
	이름(방식3):${name} <br>
	
	성별:${sex }<br>
	
	<h1>session에 있는 데이터 출력</h1>
	이름(방식2):${sessionScope.name1} <br>
	이름(방식3):${name1} <br>
	성별:${sex1 }<br>
	
	<h1>application에 있는 데이터 출력</h1>
	이름(방식2):${applicationScope.name2} <br>
	이름(방식3):${name2} <br>
	성별:${sex2 }<br>
	
</body>
</html>
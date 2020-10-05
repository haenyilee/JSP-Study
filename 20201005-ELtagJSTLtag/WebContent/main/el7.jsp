<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id="admin";
	String admin="y";
%>
<c:set var="id" value="<%=id %>"/>
<c:set var="admin" value="<%=admin %>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL 논리 연산자 (&& , || , !)</h1>
	<c:if test="${ id eq 'admin' and admin eq 'y' }">
		<b>관리자 입니다.</b>
	</c:if>
	<c:if test="${ not(id=='admin' && admin=='y') }">
		<b>일반 유저 입니다.</b>
	</c:if>
	<br>
	<c:set var="sex" value="1"/>
	${sex==1?"남자":"여자" }
	<br>
	<c:set var="msg1" value="Hello"/>
	<c:set var="msg2" value="JSP(JSTL,EL)"/>
	${msg1+=msg2 }
</body>
</html>
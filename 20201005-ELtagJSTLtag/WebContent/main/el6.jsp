<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today=sdf.format(date);
%>
<!-- 오늘 날짜 저장 -->
<c:set var="today" value="<%=today %>"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL에서 문자열 비교하기</h1>
	<%-- <c:if test="${today=='2020-10-05' }">
		<font color="red"><b>2020-10-05은 오늘 입니다.</b></font><br>
	</c:if>
	<c:if test="${today!='2020-10-04' }">
		<font color="red"><b>2020-10-04는 오늘 날짜가 아니다</b></font>
	</c:if> --%>
	<c:choose>
		<c:when test= "${today eq '2020-10-05'}">
			<font color="red"><b>오늘 날짜입니다.</b></font>
		</c:when>
		<c:otherwise>
			<font color="red"><b>오늘 날짜가 아닙니다.</b></font>
		</c:otherwise>
	</c:choose>

	<h1>EL연산자 사용하기 : + , - , * </h1>
	5+5=${5+5}<BR>
	5+"5"=${5+"5"}<BR>
	<%-- 5+"5 "=${5+"5 "}<BR> --%>
	null+5=${null+5 }
	
	<h1>EL연산자 사용하기 : / , % </h1>
	5/2=${5/2}<BR>
	5/"2"=${5/"2"}<BR>
	5 div "2"=${5 div "2"}<BR>
	null/5=${null/5 }<BR>
	
	5%2=${5%2}<BR>
	5 mod "2"=${5 mod "2"}<BR>
	null mod 5=${null mod 5 }<BR>
	
	<h1>1~10까지 출력하기(짝수만)</h1>
	<c:forEach var="i" begin="1" end="10">
		<c:if test="${i%2==0 }">${i }&nbsp;</c:if>
	</c:forEach>
	
	<h1>1~10까지 출력하기(홀수만)</h1>
	<c:forEach var="i" begin="1" end="10">
		<c:if test="${i%2 ne 0 }">
			${i }&nbsp;
		</c:if>
	</c:forEach>
</body>
</html>
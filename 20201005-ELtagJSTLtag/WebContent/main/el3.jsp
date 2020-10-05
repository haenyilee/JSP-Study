<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>자바로 반복문 사용하기</h1>
	<%
		for(int i=1;i<=10;i++)
		{
	%>
			<%=i %>&nbsp;
	<%
		}
	%>
	
	<h1>JSTL로 반복문 사용하기</h1>
	<c:forEach var="i" begin="1" end="10" step="1">
		${i }&nbsp;
	</c:forEach>
	
	<br>
	
	<h1>자바로 단어별 출력(StringTokenizer)</h1>
	<%
      String color="red,blue,yellow,green,white";
      StringTokenizer st=new StringTokenizer(color,",");
      while(st.hasMoreTokens())
      {
   %>
         <%=st.nextToken() %><br>
   <%
      }
   %>
	
	
	
	<h1>JSTL로 단어별 출력(StringTokenizer)</h1>
	<c:set var="color" value="red,blue,yellow,green,white"/>
	<c:forTokens var="s" items="${color}" delims=",">
		${s }
	</c:forTokens>
	
	<h1>JSTL => Java로 변경</h1>
	<%
		request.setAttribute("color", "red,blue,yellow,green,white");
		String color1=(String)request.getAttribute("color");
		StringTokenizer st1=new StringTokenizer(color1,",");
		while(st1.hasMoreTokens())
		{
	%>
			<%=st1.nextToken() %><br>
	<%
		}
	%>
	 <h1>Java로 forEach구문</h1>
	  <%
	      List<String> names=new ArrayList<String>();
	      names.add("심청이");
	      names.add("홍길동");
	      names.add("박문수");
	      names.add("이순신");
	      names.add("춘향이");
	      // for-each (향상된 for => 웹에서는 주로 사용)
	      for(String name:names)
	      {
	  %>
	         <%= name %>&nbsp;
	  <%
	      }
	  %>
	  <br>
	  <h1>JSTL를 이용한 for-each</h1>
	  <c:set var="names" value="<%=names %>"/>
	  <c:forEach var="name" items="${names }">
	      ${name }&nbsp;
	  </c:forEach>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>서버정보</h1>
		<ul>
			<li>서버정보:<%=application.getServerInfo() %></li>
			<li>메이저 버전:<%=application.getMajorVersion() %></li>
			<li>마이너 버전:<%=application.getMinorVersion() %></li>
		</ul>
		
		<h1>web.xml에 존재하는 데이터베이스 정보를 출력하기</h1>
		<ul>
			<li>데이터베이스(Driver):<%=application.getInitParameter("driver") %></li>
			<li>데이터베이스(url):<%=application.getInitParameter("url") %></li>
			<li>데이터베이스(user-name):<%=application.getInitParameter("username") %></li>
			<li>데이터베이스(password):<%=application.getInitParameter("password") %></li>
		</ul>
		
		<!-- 서버 로그파일 로그를 만드는 방식 : 콘솔에 출력됨 -->
		<%
			String driver=application.getInitParameter("driver");
			String url=application.getInitParameter("url");
			String username=application.getInitParameter("username");
			String password=application.getInitParameter("password");
			
			application.log("driver:"+driver);
			application.log("url:"+url);
			application.log("username:"+username);
			application.log("password:"+password);
		%>
		
		<!-- 실제 주소 확인하기
			C:\webDev\20200921-jsp2\WebContent\jsp\basic.jsp는 가상주소임
		 -->
		<h1>실제경로명</h1>
		<%=application.getRealPath("/jsp") %>
	</center>
</body>
</html>
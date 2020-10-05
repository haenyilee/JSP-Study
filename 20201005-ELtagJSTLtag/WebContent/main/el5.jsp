<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>

<!-- JSTL 사용(제어문) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	EmpDAO dao= new EmpDAO();
	List<EmpVO> list=dao.empListData();
	//request.setAttribute("list", list);
%>
<!-- request.setAttribute("list", list); -->
<c:set var="list" value="<%=list %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>사용 목록</h1>
		<table border=1 bordercolor=black width=700>
			<tr bgcolor=#ccccff>
				<th>사번</th>
				<th>이름</th>
				<th>직위</th>
				<th>입사일</th>
				<th>부서</th>
				<th>근무지</th>
				<th>급여</th>
			</tr>
			
			<%-- 
			1. 자바로 출력하기
			<%
				for(EmpVO vo:list)
				{
			%>
					<tr>
						<td><%=vo.getEmpno() %></td>
						<td><%=vo.getEname() %></td>
						<td><%=vo.getJob() %></td>
						<td><%=vo.getDbday() %></td>
						<td><%=vo.getDname() %></td>
						<td><%=vo.getLoc() %></td>
						<td><%=vo.getSal() %></td>
					</tr>
			
			<%
				}
			%> 
			--%>
			
			<!-- 2. EL형식으로 출력하기 -->
			<c:forEach var="vo" items="${list }">
				<tr>
					<%-- 
					<1. get메소드로 값 가져오기>
					<td>${vo.getEmpno()}</td>
					<td>${vo.getEname()}</td>
					<td>${vo.getJob()}</td>
					<td>${vo.getDbday()}</td>
					<td>${vo.getDname()}</td>
					<td>${vo.getLoc()}</td>
					<td>${vo.getSal()}</td> 
					--%>
					<td>${vo.empno}</td>
					<td>${vo.ename}</td>
					<td>${vo.job}</td>
					<td>${vo.dbday}</td>
					<td>${vo.dname}</td>
					<td>${vo.loc}</td>
					<td>${vo.sal}</td>
				</tr>
			</c:forEach>
			
			
		</table>
	</center>
</body>
</html>
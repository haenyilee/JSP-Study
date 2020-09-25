<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
    
<%
	String no=request.getParameter("no");
	MovieVO vo=MovieDAO.movieDetailData(Integer.parseInt(no));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<h1 class="text-center"><%=vo.getTitle() %>상세보기 </h1>
		<table class="table">
			<tr>
				<td>
					<iframe src="http://youtube.com/embed/<%=vo.getKey() %>" width=750 height=450></iframe>
				</td>
			</tr>
		</table>
		<table class="table">
			<tr>
				<td width=30% class="text-center" rowspan=7>
					<img src="<%=vo.getPoster() %>" width=100%>
				</td>
				<td colspan=2><font color=orange><%=vo.getTitle() %></font></td>
			</tr>
			<tr>
				<td width=10%>감독</td>
				<td width=60%><%=vo.getDirector() %></td>
			</tr>
			<tr>
				<td width=10%>출연 </td>
				<td width=60%><%=vo.getActor() %></td>
			</tr>
			<tr>
				<td width=10%>장르 </td>
				<td width=60%><%=vo.getGenre() %></td>
			</tr>
			<tr>
				<td width=10%>등급 </td>
				<td width=60%><%=vo.getGenre() %></td>
			</tr>
			<tr>
				<td width=10%>개봉일 </td>
				<td width=60%<%=vo.getRegdate() %>></td>
			</tr>
			<tr>
				<td width=10%>평점 </td>
				<td width=60%><%=vo.getScore() %></td>
			</tr>
			<tr>
				<td colspan=3>
					<pre style="white-space: pre-wrap;background-color:white;border:none"><%=vo.getStory() %></pre>
				</td>
			</tr>
		</table>
	</div>
	<div class="row">
		<div class="text-right">
			<a href="#" class="btn btn-lg btn-primary">예매하기 </a>
			<a href="#" class="btn btn-lg btn-danger">찜하기 </a>
			<a href="../main/main.jsp" class="btn btn-lg btn-warning">목록 </a>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	margin: 0px auto;
	width:300px;

}
</style>
</head>
<body>
	<div class="row row1">
		<h1 class="text-center">삭제하기</h1>
		<form method=post action="../board/delete_ok.jsp">
		<table class="table table-striped">
			<tr>
				<td>
				비밀번호:<input type=password name=pwd size=15 class="input-sm">
				<input type=hidden name=no value=<%=no %>>
				</td>
			</tr>
			<tr>
				<td class="text-center">
					<input type=submit value="삭제" class="btn btn-sm btn-danger">
					<input type=button value="취소" class="btn btn-sm btn=info"
						onclick="javascript:history.back()">
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>
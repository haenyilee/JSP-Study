<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	
	MemberVO vo=MemberDAO.memberLogin(id, pwd);
	
	if(vo.getMsg().equals("NOID"))
	{
%>
		<script>
		alert("ID가 존재하지 않습니다!")
		history.back();
		</script>
<%
		
	}
	else if(vo.getMsg().equals("NOPWD"))
	{
%>
		<script>
		alert("비밀번호가 일치하지 않습니다!")
		history.back();
		</script>
<%
	}
	else
	{
		session.setAttribute("id", vo.getId());
		session.setAttribute("name", vo.getName());
		session.setAttribute("admin", vo.getAdmin());
		response.sendRedirect("../main/main.jsp");
	}
%>
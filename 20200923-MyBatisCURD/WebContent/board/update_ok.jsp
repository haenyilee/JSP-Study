<%@page import="com.sist.dao.DataBoardDAO"%>
<%@page import="com.sist.dao.DataBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name");
	String no = request.getParameter("no");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	DataBoardVO vo= new DataBoardVO();
	vo.setNo(Integer.parseInt(no));
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	boolean bCheck = DataBoardDAO.boardUpdate(vo);
	
	if(bCheck==true)
	{
		response.sendRedirect("../main/main.jsp?mode=3&no="+no); // detail로 이동하기 
	}
	else
	{
%>
		<script>
		alert("비밀번호가 틀립니다.");
		history.back();
		</script>
<%		
	}
	
%>

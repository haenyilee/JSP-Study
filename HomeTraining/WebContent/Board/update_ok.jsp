<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
      try
      { 
    	  request.setCharacterEncoding("UTF-8");// 한글 변환 코드(디코딩)
      }catch(Exception ex){}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no");
		String strPage=request.getParameter("page");
		
		CmBoardVO vo=new CmBoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO연결 
		CmBoardDAO dao=new CmBoardDAO();
		boolean bCheck=dao.boardUpdate(vo);
		// 이동 (목록)
		if(bCheck==true)
		{
           response.sendRedirect("detail.jsp?no="+no+"&page="+strPage);
		}
		else
		{
%>
            <script>
            alert("비밀번호가 틀립니다!!");
            history.back();
            </script>
<%
		}
      
%>

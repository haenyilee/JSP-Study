<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,com.sist.dao.*"%>
<%
	String no = request.getParameter("no");
	String pwd = request.getParameter("pwd");
	
	DataBoardVO vo= DataBoardDAO.boardGetInfoData(Integer.parseInt(no));
	boolean bCheck=DataBoardDAO.boardDelete(Integer.parseInt(no), pwd);
	
	if(bCheck==true)
	{
		if(vo.getFilesize()>0) // 업로드가 없는 게시물 
		{
			File dir=new File("/Users/haeni/Documents/WebDev/WebDev1/download/");
			File[] files=dir.listFiles(); // download폴더 안에 있는 모든 정보 가져오기
			for(File f:files)
			{
				if(f.getName().equals(vo.getFilename()))
				{
					f.delete();
					break;
				}
			}
		}
		response.sendRedirect("../main/main.jsp?mode=1");
	}
	else
	{
%>
		<script>
		alert("Wrong Password\nPlease check your password!");
		history.back();
		</script>
<%
	}
	
%>
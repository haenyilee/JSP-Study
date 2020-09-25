<%@page import="com.sist.dao.DataBoardDAO"%>
<%@page import="com.sist.dao.DataBoardVO,java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.oreilly.servlet.*,com.oreilly.servlet.multipart.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	String path="/Users/haeni/Documents/WebDev/WebDev1/download/"; // 파일이 업로드되면 저장되는 폴더 
	String enctype="UTF-8"; //한글 파일명을 사용 여부 
	int size=1024*1024*1024; //파일 최대 크기 
	MultipartRequest mr = 
		new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
	
	// 사용자가 입력한 값 받기
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	
	
	DataBoardVO vo = new DataBoardVO();
	
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// filename,filesize없는 경우
	String filename=mr.getOriginalFileName("upload");
	if(filename==null)
	{
		vo.setFilename("");
		vo.setFilesize(0);
	} 
	else // 파일 올렸을 경
	{
		File file=new File("/Users/haeni/Documents/WebDev/WebDev1/download/"+filename);
		//업로드된 파일 정보 얻어오기 => 파일 크기 확인하기 => 다운로드할때 프로그래스바를 만들 수 있기 때문에 
		vo.setFilename(filename);
		vo.setFilesize((int)file.length());
	}
	
	//데이터베이스 연결하기 : dao호출해서 insert요청하는데 sql문장은 mapper에 있다.
	DataBoardDAO.boardInsert(vo);
	
	
	response.sendRedirect("../main/main.jsp?mode=1");
%>
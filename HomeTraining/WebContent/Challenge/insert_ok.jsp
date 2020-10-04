<%@page import="com.sist.dao.*,java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.oreilly.servlet.*,com.oreilly.servlet.multipart.*"%>

 <%
     request.setCharacterEncoding("utf-8");// 한글 디코딩
     String path=""; // 파일이 업로드가 되면 어디에 저장 폴더 
     String enctype="UTF-8"; //한글파일명을 사용 여부 
     int size=1024*1024*100;//파일의 최대크기 
     // 사용자가 보내준 데이터를 받는다 (request=>파일을 받을 수 없다 , 일반데이터만 받는다)
     MultipartRequest mr=
          new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
     
     String name=mr.getParameter("cate"); // 업로드시에만 사용
     String subject=mr.getParameter("start_day");
     String content=mr.getParameter("content");
     
     // 받은 데이터들을 DAO => DAO에서 오라클에 INSERT
     ChallengeVO vo=new ChallengeVO();
     vo.setCate(cate);
     vo.setStart_day(start_day);
     vo.setContent(content);
     
     // filename,filesize => 없는 경우 (파일을 올리지 않을 경우,파일 올릴 경우)
     String filename=mr.getOriginalFileName("poster");
     // 사용자가 보낸 파일명을 읽어 온다 
     // <input type=file name=upload size=20 class="input-sm">
     if(filename==null)//파일을 올리지 않을 경우
     {
    	 vo.setFilename("");
    	 vo.setFilesize(0);
     }
     else//파일 올릴 경우
     {
    	 File file=new File("c:\\download\\"+filename);
    	 // 업로드된 파일의 정보를 얻어온다 (파일 크기를 확인) => 다운로드 (프로그래바)
    	 vo.setFilename(filename);
    	 vo.setFilesize((int)file.length()); //length() => long
     }
     
     // DAO를 호출한 다음에 INSERT요청 => 저장하는 SQL (databoard-mapper.xml)
     ChallengeDAO.challengeInsert(vo); // 추가 
   
     response.sendRedirect("../main/main.jsp?mode=1");// list.jsp로 이동을 한다 
 %>
 
 
 
 
 
 
 

 
 
 
 
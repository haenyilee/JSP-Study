<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
	String strPage=request.getParameter("page");
    if(strPage==null) 
    	strPage="1";
    
    int curpage=Integer.parseInt(strPage);
 
    CmBoardDAO dao=new CmBoardDAO();
   
    ArrayList<CmBoardVO> list=dao.boardListData(curpage);
    
    int count=dao.boardRowCount();
   
    int totalpage=(int)(Math.ceil(count/10.0));
    count=count-((curpage*10)-10);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/table.css">
</head>
<body>
   <center>
     <h1>자유게시판</h1>
     <table class="table_content" width=700>
       <tr>
        <td>
          <a href="insert.jsp">글쓰기</a>
        </td>
       </tr>
     </table>
     <table class="table_content" width=700>
       <tr height=30>
         <th width=10%>번호</th>
         <th width=45%>제목</th>
         <th width=15%>이름</th>
         <th width=20%>작성일</th>
         <th width=10%>조회수</th>
       </tr>
       <%
       	for(CmBoardVO vo:list)
                  {
       %>
               <tr height="30">
		         <td width=10% align=center><%=count-- %></td>
		         <td width=45%>
		           <%
		               if(vo.getGroup_tab()>0)
		               {
		            	   for(int i=0;i<vo.getGroup_tab();i++)
		            	   {
		            		   out.println("&nbsp;&nbsp;");
		            	   }
		           %>
		                  <img src="image/icon_reply.gif" style="border:none">
		           <%
		               }
		           %>
		           <%-- 내용보기 이동 <a> --%>
		           <a href="detail.jsp?no=<%=vo.getNo()%>&page=<%=curpage%>"><%=vo.getSubject() %></a>
		           &nbsp;
		           <%
		              Date date=new Date();
		              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		              String today=sdf.format(date);
		              String dbday=vo.getRegdate().toString();
		              
		              if(today.equals(dbday))
		              {
		           %>
		                  <sup><img src="image/new.gif" style="border:none"></sup>
		           <%
		              }
		           %>
		         </td>
		         <td width=15% align=center><%=vo.getName() %></td>
		         <td width=20% align=center><%=vo.getRegdate().toString() %></td>
		         <td width=10% align=center><%=vo.getHit() %></td>
		       </tr>
       <%
           }
       %>
     </table>
     <table class="table_content" width=700 >
       <tr>
         <td align=left>
         <form method=post action="find.jsp">
          Search:
          <%--
               WHERE fd LIKE '%ss%'
           --%>
          
	          <select name=fs>
	            <option value="name">이름</option>
	            <option value="subject">제목</option>
	            <option value="content">내용</option>
	          </select>
	          <input type=text name=ss size=10>
	          <input type=submit value="찾기">
          </form>
         </td>
         <td align=right>
          <a href="main.jsp?page=<%= curpage>1?curpage-1:curpage%>">이전</a>
            <%=curpage %> page / <%=totalpage %> pages
          <a href="main.jsp?page=<%=curpage<totalpage?curpage+1:curpage%>">다음</a>
         </td>
       </tr>
     </table>
   </center>
</body>
</html>


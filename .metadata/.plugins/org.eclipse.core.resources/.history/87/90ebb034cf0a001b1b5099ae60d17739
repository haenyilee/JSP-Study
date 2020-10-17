<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String no=request.getParameter("no");
     String strPage=request.getParameter("page");
     
     CmBoardDAO dao=new CmBoardDAO();
     CmBoardVO vo=dao.boardDetail(Integer.parseInt(no), 2);
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
     <h1>수정하기</h1>
     <form method=post action=update_ok.jsp>
     <table class="table_content" width=500>
       <tr>
         <th width=15% align=right>이름</th>
         <td width=85%>
           <input type=text name=name size=15 required value=<%=vo.getName() %>>
           <input type=hidden name=no value="<%=no%>">
           <input type=hidden name=page value="<%=strPage%>">
         </td>
       </tr>
       <tr>
         <th width=15% align=right>제목</th>
         <td width=85%>
           <input type=text name=subject size=45 required value="<%=vo.getSubject()%>">
         </td>
       </tr>
       <tr>
         <th width=15% align=right>내용</th>
         <td width=85%>
           <textarea rows="7" cols="55" name="content" required><%=vo.getContent() %></textarea>
         </td>
       </tr>
       <tr>
         <th width=15% align=right>비밀번호</th>
         <td width=85%>
           <input type=password name=pwd size=10 required>
         </td>
       </tr>
       <tr>
         <td colspan="2" align=center>
          <input type=submit value="수정">
          <input type=button value="취소" onclick="javascript:history.back()">
         </td>
       </tr>
     </table>
     </form>
   </center>
</body>
</html>
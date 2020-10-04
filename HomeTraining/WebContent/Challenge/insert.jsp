<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="row">
   <h1 class="text-center">방만들기</h1>
   
   <form method="post" action="../challenge/insert_ok.jsp" enctype="multipart/form-data">
   <table class="table table-hover">
     <tr>
       <th class="danger text-right" width=15%>카테고리</th>
       <td width=85%>
         <input type=text name=cate size=15 class="input-sm">
       </td>
     </tr>
     <tr>
       <th class="danger text-right" width=15%>시작일</th>
       <td width=85%>
         <input type=date name=start_day max="2020-12-31" size=45 class="input-sm">
       </td>
     </tr>
     <tr>
       <th class="danger text-right" width=15%>도전 상세</th>
       <td width=85%>
         <textarea rows="10" cols="50" name=content></textarea>
       </td>
     </tr>
     <tr>
       <th class="danger text-right" width=15%>포스터</th>
       <td width=85%>
         <input type=file name=poster size=20 class="input-sm">
       </td>
     </tr>
     <tr>
       <td colspan="2" class="text-center">
         <input type=submit value=글쓰기 class="btn btn-sm btn-primary">
         <input type=button value=취소 class="btn btn-sm btn-primary"
           onclick="javascript:history.back()"
         >
       </td>
     </tr>
   </table>
   </form>
  </div>
</body>
</html>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
  <div class="container">
   <div class="row">
     <h1 class="text-center">자유게시판</h1>
     <table class="table">
       <tr>
         <td>
           <a href="insert.do" class="btn btn-sm btn-primary">새글</a>
         </td>
       </tr>
     </table>
     <table class="table table-striped">
       <tr class="danger">
         <th class="text-center" width=10%>번호</th>
         <th class="text-center" width=45%>제목</th>
         <th class="text-center" width=15%>이름</th>
         <th class="text-center" width=20%>작성일</th>
         <th class="text-center" width=10%>조회수</th>
       </tr>
       <c:forEach var="vo" items="${list }">
        <tr>
          <td class="text-center" width=10%>${vo.no }</td>
          
          <td class="text-left" width=45%>
           <a href="detail.do?no=${vo.no }">${vo.subject }</a>
           <%-- <c:if test="${today == vo.dbday}">
             <font color=red><sup>new</sup></font>    
          </c:if> --%>
          </td>
          <td class="text-center" width=15%>${vo.name }</td>
          <td class="text-center" width=20%>
           <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
           
          </td>
          <td class="text-center" width=10%>${vo.hit }</td>
        </tr>
       </c:forEach>
      </table>
      <table class="table table-striped">
        <tr>
          <td class="text-right">
            <a href="list.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-danger">이전</a>
              ${curpage } page / ${totalpage } pages
            <a href="list.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-primary">다음</a>
          </td>
        </tr>
      </table>
     </div>
    </div>
</body>
</html>



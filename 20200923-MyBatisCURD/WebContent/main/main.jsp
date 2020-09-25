<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
    
     템플릿 : 메뉴..footer..
 --%>
 

 <%
 	request.setCharacterEncoding("utf-8");
 	String mode=request.getParameter("mode");
 	if(mode==null)
 		mode="7";
 	
 	int index = Integer.parseInt(mode);
 	
 	String jsp="";
 	switch(index)
 	{
 	case 1:
 		jsp="../board/list.jsp";
 		break;
 	case 2:
 		jsp="../board/insert.jsp";
 		break;
 	case 3:
 		jsp="../board/detail.jsp";
 		break;
 	case 4:
 		jsp="../board/find.jsp";
 		break;
 		
 	case 5:
 		jsp="../board/delete.jsp";
 		break;
 	case 6:
 		jsp="../board/update.jsp";
 		break;
 		
 	case 7:
 		jsp="../movie/home.jsp";
 		break;
 	case 8:
 		jsp="../movie/detail.jsp";
 		break;
 	case 9:
 		jsp="../freeboard/list.jsp";
 		break;
 	}
 %>
 
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="container-fluid"><!-- full 화면 -->
  <div class="row">
    <div class="col-sm-3">
      <!-- 로그인 -->
      <jsp:include page="login.jsp"></jsp:include>
    </div>
    <div class="col-sm-9">
      <!-- 레시피 : 메뉴를 클릭시마다 변경 : 변수를 잡아서 => jsp파일명을 변경 -->
      <jsp:include page="<%=jsp %>"></jsp:include>
    </div>
  </div>
</div>

</body>
</html>
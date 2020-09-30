<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 외부 css파일을 읽기 -->
<link rel="stylesheet" href="style.css">
</head>
<body>
<nav id="topMenu" > 
	<ul> 
		<li class="topMenuLi"> 
     	<a class="menuLink" href="#">HOME</a>
		</li>
		<li>|</li>
		<li class="topMenuLi">
			<a class="menuLink" href="#">레시피</a>
			<ul class="submenu">
				<li><a href="#" class="submenuLink">최신순</a></li>
				<li><a href="#" class="submenuLink">추천순</a></li>
			</ul>
		</li>
		<li>|</li>
		<li class="topMenuLi">
			<a class="menuLink" href="#">커뮤니티</a>
			<ul class="submenu">
				<li><a href="#" class="submenuLink">자유게시판</a></li>
				<li><a href="#" class="submenuLink">자료실</a></li>
				<li><a href="#" class="submenuLink">묻고답하기</a></li>
			</ul>
		</li>
		<li>|</li>
		<li class="topMenuLi">
			<a class="menuLink" href="#">요리정보</a>
			<ul class="submenu">
				<li><a href="#" class="submenuLink">요리팁 & 노하우</a></li>
				<li><a href="#" class="submenuLink">재료</a></li>
				<li><a href="#" class="submenuLink">맛집</a></li>
			</ul>
		</li>
	</ul>
</nav>
<!-- 실제 화면 출력 부분 -->
<section>
	<div class="main">
		<div class="content">
			<div class="gallery">
				<!-- 이미지 출력 -->
				<%
					// include는 파일이 없는 경우에 오류를 발생시킴
					pageContext.include("board/list.jsp");
				
				%>
			</div>
		</div>
	</div>
</section>
</body>
</html>
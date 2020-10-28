<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/"></script>
<script type="text/javascript">
// function a(){document.write("a")}
// function b(){document.write("b")}
// function c(){document.write("c")}
// function d(){document.write("d")}
// function e(){document.write("e")}

// window.onload=function(){
// 	a();
// }
$(function(){
	// 태그를 읽어서 제어
	$('h1').css("color","red");
	$('#a').css("color","green");
	$('.b').css("color","blue");
	$('h1').css("color","red");
	$('div .b').css("color","blue");
	$('h1:eq(0)').css("color","red");
	$('.b:eq(3)').css("color","red");
})


</script>
</head>
<body>
	<h1 id="a">Java</h1>
	<h1>Oracle</h1>
	<h1>JSP</h1>
	<div>
		<h1 class="b">Spring</h1>
	</div>
	<h1 class="b">JavaScript</h1>
</body>
</html>


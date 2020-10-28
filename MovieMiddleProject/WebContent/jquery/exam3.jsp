<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#name').val("홍길동");
	$('#btn').click(function(){
		// 입력한 값 받아오기 : val()
		let name=$('#name').val();
		
		// div사이 값을 읽기
		// $('div').text();
		
		// div태그 사이에 값을 출력하기 : text()
		// $('div').text(name);
		
		// 입력 값 누적시키기
		$('div').append(name+"<br>");
	})
});
</script>
</head>
<body>
	이름:<input type=text id="name" size="15">
	<input type=button value="실행" id="btn"><br>
	<div></div>
</body>
</html>
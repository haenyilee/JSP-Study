<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.inwon').hover(function(){
		$(this).css("cursor","pointer");
	},function(){
		$(this).css("cursor","");
	})
	$('.inwon').click(function(){
		let inwon=$(this).text();
		let price=inwon*10000;
		// reserve.jsp에서 띄울 값을 채우기
		$('#movie_inwon').text(inwon+"명");
		$('#movie_price').text(price+"명");
		$('#okbtn').show();
		$('#inwon').val(inwon);
		$('#price').val(price);
	})
})
</script>


</head>
<body>
<span class="btn btn-xs btn-info inwon"></span>
<form method="post" action="../reserve/reserve_ok.do">
	<input type=hidden name="mno" id="mno">
	<input type=hidden name="tname" id="tname">
	<input type=hidden name="day" id="day">
	<input type=hidden name="inwon" id="inwon">
	<input type=hidden name="price" id="price">
	<input type=submit value="예매하기" class="btn btn-lg btn-primary">
</form>

</body>
</html>
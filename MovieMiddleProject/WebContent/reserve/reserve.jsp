<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	 $.ajax({
		 type:'post',
		 url:'../reserve/date.do',
		 success:function(result)
		 {
			 $('#reserve_date').html(result);
		 }
	 });
	 
	 $.ajax({
		 type:'post',
		 url:'../reserve/movie.do',
		 success:function(result)
		 {
			 $('#movie_info').html(result);
		 }
	 })
	 
});
</script>
</head>
<body>
	<div style="height: 30px"></div>
	<div class="row">
		<table class="table">
			<tr>
			<!---------------------------------영화 정보 #movie_info------------------------------->
				<td width=20% height=500>
					<table class="table">
						<caption class="text-center">
							<h3>영화정보</h3>
						</caption>
						<tr>
							<td>
								<div id="movie_info" style="overflow-y: auto; height: 450px"></div>
							</td>
						</tr>
					</table>
				</td>
			<!---------------------------------극장 정보 #theater_info------------------------------->
				<td width=15% height=500>
					<table class="table">
						<caption class="text-center">
							<h3>극장 정보</h3>
						</caption>
						<tr>
							<td>
								<div id="theater_info" style="overflow-y: auto; height: 450px"></div>
							</td>
						</tr>
					</table>
				</td>
			<!---------------------------------예매일 정보 #data_info------------------------------->
				<td width=35% height=500>
					<table class="table">
						<caption class="text-center">
							<h3>예매일 정보</h3>
						</caption>
						<tr>
							<td>
								<div id="data_info"></div>
							</td>
						</tr>
					</table>
				</td>
		  <!---------------------------------예매 선택 사항 실시간 반영------------------------------->	
				<td width=30% height=700 rowspan="2">
					<table class="table">
						<caption class="text-center">
							<h3>예매정보</h3>
						</caption>
						<tr>
							<td class="text-center" colspan="2">
								<img src="def.png" width=250 height=300 id="movie_poster">
							</td>
						</tr>
						<tr>
							<td class="text-right" width=25%>영화명:</td>
							<td width=80%><span id="movie_title"></span></td>
						</tr>
						<tr>
							<td class="text-right" width=25%>극장명:</td>
							<td width=75%><span id="movie_theater"></span></td>
						</tr>
						
						<tr>
							<td class="text-right" width=25%>예매일:</td>
							<td width=75%><span id="movie_reserve"></span></td>
						</tr>
						<tr>
							<td class="text-right" width=25%>시간:</td>
							<td width=75%><span id="movie_time"></span></td>
						</tr>
						<tr>
							<td class="text-right" width=25%>인원:</td>
							<td width=75%><span id="movie_inwon"></span></td>
						</tr>
						<tr>
							<td class="text-right" width=25%>금액:</td>
							<td width=75%><span id="movie_price"></span></td>
						</tr>
						<tr style="display: none" id="okbtn">
							<td colspan="2" class="text-center">
								<form method="post" action="../reserve/reserve_ok.do">
									<input type=hidden name="mno" id="mno"> 
									<input type=hidden name="tname" id="tname"> 
									<input type=hidden name="day" id="day"> 
									<input type=hidden name="time" id="time"> 
									<input type=hidden name="inwon" id="inwon"> 
									<input type=hidden name="price" id="price">
									<input type=submit value="예매하기" class="btn btn-lg btn-primary">
								</form>

							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" height=200>
					<table class="table">
						<caption class="text-center">
							<h3>시간 정보</h3>
						</caption>
						<tr>
							<td>
								<div id="time_info"></div>
							</td>
						</tr>
					</table>
				</td>
				<td width=15% height=200>
					<table class="table">
						<caption class="text-center">
							<h3>인원 정보</h3>
						</caption>
						<tr>
							<td>
								<div id="inwon_info"></div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
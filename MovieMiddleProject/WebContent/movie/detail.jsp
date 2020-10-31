<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.row1 {
	margin: 
}

</style>

<title>Insert title here</title>
</head>
<body>
	<div style="height: 30px"></div>
		<div class="row row1">
			<table class="table" >
			  <caption><h3>&lt;${vo.title}&gt; 상세보기</h3></caption>
				<tr>
					<td class="text-center">
						<iframe src="http://youtube.com/embed/${vo.key }" width=600 height=400></iframe>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td width=40% class="text-center" rowspan="7">
						<img src="${vo.poster }" width="280" height="250">
					</td>
					<td colspan="2" class="text-center"> ${vo.title }</td>
				</tr>
				<tr>
					<td class="text-right" width=10%>감독</td>
					<td width=50%>${vo.director }</td>
				</tr>
				<tr>
					<td class="text-right" width=10%>출연</td>
					<td width=50%>${vo.actor }</td>
				</tr>
				<tr>
					<td class="text-right" width=10%>등급</td>
					<td width=50%>${vo.grade }</td>
				</tr>
				<tr>
					<td class="text-right" width=10%>장르</td>
					<td width=50%>${vo.genre }</td>
				</tr>
				<tr>
					<td class="text-right" width=10%>평점</td>
					<td width=50%>${vo.score }</td>
				</tr>
				<tr>
					<td class="text-right" width=10%>개봉일</td>
					<td width=50%>${vo.regdate }</td>
				</tr>
				<tr>
					<td colspan="3">
						${vo.story }
					</tr>
				</tr>
				<tr> 
					<td colspan="3" class="text-right">
						<a href="../movie/like.do?no=${vo.no }" class="btn btn-sm btn-primary">좋아요</a>
						<c:if test="${count==0 }">
							<a href="../movie/jjim.do?no=${vo.no }" class="btn btn-sm btn-primary">찜하기</a>
						</c:if>
						<a href="../movie/total.do" class="btn btn-sm btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 영화 검색 기능
	$('#keyword').keyup(function(){
		let k=$('#keyword').val();
		$('#movie-table > tr').hide();
		let temp=$('#movie-table > tbody > tr > td:nth-child(2n+2):contains("'+k+'")');
		$(temp).parent().show();
	})
	
	// 영화 목록 중에서 영화 선택
	$('.movie').click(function(){
		let no=$(this).attr("data-value");
		//alert("no:"+no);
		let poster=$(this).attr("data-poster"); // this는 사용자가 클릭한 값임
		let title=$(this).attr("data-title");
		$('#movie_poster').attr("src",poster);
		$('#movie_title').text(title);
		
		//사용자가 클릭한 값
		$('#mno').val(no);
		
		//theater.do?no=1
		$.ajax({
			type:'post',
			url:'../reserve/theater.do',
			data:{"no":no},
			success:function(result)
			{
				$('#theater_info').html(result);
			}
		})
	});
});
</script>
</head>
<body>
	<!-- ----------------영화 검색 start-------------------- -->
   <table class="table">
   	<tr>
   		<td>
   		<input type=text id="keyword" size=15 placeholder="검색">
   		</td>
   	</tr>
   </table>
   <!-- ----------------영화 검색 end-------------------- -->
   <table class="table table-striped" id="movie-table">
   	<tbody>
     <c:forEach var="vo" items="${list }">
       <tr class="movie" data-value="${vo.no }"
           data-poster="${vo.poster }" data-title="${vo.title }">
         <td><img src="${vo.poster }" width=20 height=20></td>
         <td style="font-size:8pt">${vo.title }</td>
       </tr>
     </c:forEach>
    </tbody>
   </table>
</body>
</html>
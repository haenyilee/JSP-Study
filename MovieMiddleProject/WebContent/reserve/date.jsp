<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   margin:0px auto;
   width:350pt;
}
</style> -->

<!-- 날짜 클릭하기 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.rdays_ok').hover(function name() {
		$(this).css("cursor","pointer");
	}, function() {
		$(this).css("cursor","")		
	})
})


// 예약일 클릭했을 때 처리하는 문장
 $('.rdays_ok').click(function(){
	 let year=$(this).attr("data-year");
	 let month=$(this).attr("data-month");
	 let day=$(this).text();
	 let rday=year+"년도"+month+"월 "+day+"일";
	 $('#movie_reserve').text(rday);
	 $('#day').val(rday);
 })
 
 // 시간 출력하기
 //

</script>




</head>
<body>
 
      <h3 class="text-center">${year }년도 ${month }월</h3>
      <table class="table">
        <tr>
          <td>
            <select name="year">
             <c:forEach var="i" begin="2020" end="2030">
               <option ${i==year?"selected":"" }>${i }</option>
             </c:forEach>
            </select>년도&nbsp;
            
            <select name="month">
             <c:forEach var="i" begin="1" end="12">
               <option ${i==month?"selected":"" }>${i }</option>
             </c:forEach>
            </select>월
          </td>
        </tr>
      </table>
      <table class="table table-striped">
        <tr class="danger" style="height:40px">
          <c:forEach var="str" items="${strWeek }" varStatus="s">
            <c:choose>
              <c:when test="${s.index==0 }">
                <c:set var="color" value="red"/>
              </c:when>
              <c:when test="${s.index==6 }">
                <c:set var="color" value="blue"/>
              </c:when>
              <c:otherwise>
                <c:set var="color" value="black"/>
              </c:otherwise>
            </c:choose>
            <th class="text-center"><font color="${color }">${str }</font></th>
          </c:forEach>
        </tr>
        
        <!-- 선택된 날짜에 색깔 표기하기  : link O-->
        
        
        <c:set var="week" value="${week }"/>
        <c:forEach var="i" begin="1" end="${lastday }">
           <c:choose>
              <c:when test="${week==0 }">
                <c:set var="color" value="red"/>
              </c:when>
              <c:when test="${week==6 }">
                <c:set var="color" value="blue"/>
              </c:when>
              <c:otherwise>
                <c:set var="color" value="black"/>
              </c:otherwise>
            </c:choose>
           <c:if test="${i==1 }">
             <tr style="height:40px">
             <c:forEach var="j" begin="1" end="${week }">
               <td>&nbsp;</td>
             </c:forEach>
           </c:if>
           
           <c:if test="${i==day }">
             <c:set var="bg" value="text-center danger"/>
           </c:if>
           <c:if test="${i!=day }">
             <c:set var="bg" value="text-center"/>
           </c:if>
           
           <td class="${bg }"><font color="${color }">${i }</font></td>
           <c:set var="week" value="${week+1 }"/>
           <c:if test="${week>6 }">
             <c:set var="week" value="0"/>
             </tr>
             <tr style="height:40px">
           </c:if>
           
            <c:if test="${i==day }">
             <c:set var="bg" value="text-center danger"/>
           </c:if>
           <c:if test="${i!=day }">
             <c:set var="bg" value="text-center"/>
           </c:if>
           
            <!-- 선택된 날짜 오른쪽 화면에 표기하기 : 링크 X -->
        
           <td class="${bg }"><font color="${color }">${i }</font></td>
           <c:set var="week" value="${week+1 }"/>
           <c:if test="${week>6 }">
             <c:set var="week" value="0"/>
             </tr>
             <tr style="height:40px">
           </c:if>
        </c:forEach>
        </tr>
      </table>
  
</body>
</html>

















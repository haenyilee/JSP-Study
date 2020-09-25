<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    
    <!-- 1페이지 게시물 읽어오기 -->
<%
	// 사용자가요청한 페이지 받아오기
	String strPage=request.getParameter("page");
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	
	Map map = new HashMap();
	int rowSize =10;
	int start=(curpage*rowSize)-(rowSize-1);
	int end=curpage*rowSize;
	map.put("start", start);
	map.put("end",end); // key값은 mapper에서 설정한 문자를 넣어줘야한다.
	List<DataBoardVO> list = DataBoardDAO.boardListData(map);
	
	int totalpage=DataBoardDAO.boardTotalPage();
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function send()
{
	var f=document.frm;
	if(f.ss.value=="")
	{
		f.ss.focus();
		return;
	}
	f.submit();
		
}
</script>
</head>
<body>
	<div class="row">
		<h1 class="text-center">자료실</h1>
		<table class="table">
			<tr>
				<td>
					<a href="main.jsp?mode=2" class="btn btn-sm btn-success">새글</a>
					<!-- 메뉴 , 로그인 ,footer는 고정한 상태로.. -->
				</td>
			</tr>
		</table>
		<table class="table table-striped">
			<tr class="warning">
				<th class="text-center" width=10%>번호</th>
				<th class="text-center" width=45%>제목</th>
				<th class="text-center" width=15%>이름</th>
				<th class="text-center" width=20%>작성일</th>
				<th class="text-center" width=10%>조회수</th>
			</tr>
			
			<%
				for(DataBoardVO vo:list)
				{
			%>
			
					<tr>
						<td class="text-center width=10%"><%=vo.getNo() %></td>
						<td width=45%>
		          			<a href="../main/main.jsp?mode=3&no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
		          <%-- include된 모든 JSP는 main.jsp로 보낸 모든 데이터값을 사용할 수 있다 
		               main.jsp => mode=3 => 화면 변경
		               detail.jsp => no=1 => 해당되는 데이터를 받아서 출력 
		          --%>
		         		</td>
						<td class="text-center" width=15%><%=vo.getName() %></td>
						<td class="text-center" width=20%><%=vo.getDbday() %></td>
						<td class="text-center" width=10%><%=vo.getHit() %></td>
					</tr>
			<%
				}
			%>
		</table>
		<table class="table">
			<tr>
				<td class="text-left">
				<form method="post" action="../main/main.jsp" name=frm>
					Search:
					<select name="fd" class="input-sm">
						<option value="name">이름</option>
						<option value="subject">목</option>
						<option value="content">내용</option>
					</select>
					<input type=text name=ss class="input-sm">
					<input type=hidden name=mode value=4>
					<input type=button value="검색"class="btn btn-sm btn-danger"
						onclick="send()"> <!-- 클릭했을때 처리  -->
				</form>
				</td>
				<td class="text-right">
					<a href="#" class="btn btn-sm btn-primary">이전</a>
						<%=curpage %> page / <%=totalpage %> pages
					<a href="#" class="btn btn-sm btn-primary">다음</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<%@page import="java.util.List"%>
<%@page import="DTO.HistoryDTO"%>
<%@page import="DAO.HistoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<meta charset="EUC-KR">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		HistoryDAO historyDAO = new HistoryDAO();
		List<HistoryDTO> list = historyDAO.getHistoryList();
		String id = request.getParameter("id");
		if(id != null) {
			historyDAO.deleteHistory(id);
		}
	%>
	<div>
		<h1>위치 히스토리 목록</h1>
	</div>
	<div style="padding-bottom: 10px;">
		<a href="http://localhost:8080/">홈</a>
		<a>|</a>
		<a href="http://localhost:8080/main/history.jsp">위치 히스토리 목록</a>
		<a>|</a>
		<a href="http://localhost:8080/main/getAPIWifi.jsp">Open API 와이파이 정보 가져오기</a>
		<a>|</a>
		<a href="http://localhost:8080/bookmark/bookmark.jsp">즐겨 찾기 보기</a>
		<a>|</a>
		<a href="http://localhost:8080/bookmarkGrp/bookmarkGrp.jsp">즐겨 찾기 그룹 관리</a>
	</div>
	<table>
		<tr>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</tr>
		<!-- if문으로 아무 데이터 없을 때 아래 정보 출력 -->
		<% if(list.isEmpty()) { %>
			<tr>
				<td colspan='5'>위치를 조회한 내역이 없습니다.</td>
			</tr>
		<% 
		} else { 
			for(HistoryDTO historyDTO : list) {
		%>
		<tr>
			<td><%=historyDTO.getId()%></td>
			<td><%=historyDTO.getLat()%></td>
			<td><%=historyDTO.getLnt()%></td>
			<td><%=historyDTO.getSearchDate()%></td>
			<td><button onClick="deleteHistory(<%=historyDTO.getId()%>)">삭제</button></td>
		</tr>
			<% } %> 
		<% } %>
	</table>

<script>
	function deleteHistory(ID) {
		if(confirm("해당 조회 내역을 삭제하시겠습니까?")) {
			$.ajax({
				url: "http://localhost:8080/main/history.jsp",
				data: {id: ID},
				success: function(){
					alert("성공");
					location.reload();
				},
				error: function (request, status, error) {
                    alert("code: " + request.status + "\n"+ "message: " + request.responseText + "\n" + "error: " + error);
                }
			})
		}
	}
</script>
</body>
</html>
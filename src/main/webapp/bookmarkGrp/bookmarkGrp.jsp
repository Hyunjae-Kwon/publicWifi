<%@page import="java.util.List"%>
<%@page import="DTO.BookMarkGrpDTO"%>
<%@page import="DAO.BookMarkGrpDAO"%>
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
		BookMarkGrpDAO bookmarkGrpDAO = new BookMarkGrpDAO();
		List<BookMarkGrpDTO> list = bookmarkGrpDAO.getBookMarkGrpList();
	%>
	<div>
		<h1>즐겨 찾기 그룹 관리</h1>
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
	<div style="padding-bottom: 10px;">
		<button onclick="location.href='addBookmarkGrp.jsp'">북마크 그룹 이름 추가</button>
	</div>
	<table>
		<tr>
			<th>ID</th>
			<th>북마크 이름</th>
			<th>순서</th>
			<th>등록일자</th>
			<th>수정일자</th>
			<th>비고</th>
		</tr>
		<!-- if문으로 아무 데이터 없을 때 아래 정보 출력 -->
		<% if(list.isEmpty()) { %>
			<tr>
				<td colspan='6'>정보가 존재하지 않습니다.</td>
			</tr>
		<% 
		} else { 
			for(BookMarkGrpDTO bookmarkGrpDTO : list) {
		%>
		<tr>
			<td><%=bookmarkGrpDTO.getId()%></td>
			<td><%=bookmarkGrpDTO.getBookmarkGrpNm()%></td>
			<td><%=bookmarkGrpDTO.getOrderNo()%></td>
			<td><%=bookmarkGrpDTO.getRegistrationDate()%></td>
			<% if (bookmarkGrpDTO.getUpdateDate() != null) { %>
			<td><%=bookmarkGrpDTO.getUpdateDate()%></td>
			<% } else {%>
			<td></td>
			<% } %>
			<td><a href="http://localhost:8080/bookmarkGrp/updateBookmarkGrp.jsp?id=<%=bookmarkGrpDTO.getId()%>">수정</a> <a href="http://localhost:8080/bookmarkGrp/deleteBookmarkGrp.jsp?id=<%=bookmarkGrpDTO.getId()%>">삭제</a></td>
		</tr>
			<% } %> 
		<% } %>
	</table>
</body>
</html>
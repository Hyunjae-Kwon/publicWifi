<%@page import="DTO.BookMarkGrpDTO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.BookMarkGrpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<meta charset="EUC-KR">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		BookMarkGrpDAO bookmarkGrpDAO = new BookMarkGrpDAO();
		String id = request.getParameter("id");
		List<BookMarkGrpDTO> list = bookmarkGrpDAO.selectBookMarkGrp(id);
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
	<div style="padding-bottom: 10px;">북마크 그룹 이름을 삭제하시겠습니까?</div>
	<form action="deleteBookmarkGrpSubmit.jsp" id="bookMarkGrp">
		<table class="detail">
			<% for(BookMarkGrpDTO bookmarkGrpDTO : list) { %>
			<tr>
				<th>북마크 이름</th>
				<td>
					<input type="hidden" name="id" value="<%=id%>">
					<input type="text" name="bookmarkNm" value="<%=bookmarkGrpDTO.getBookmarkGrpNm()%>">
				</td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="text" name="orderNo" value="<%=bookmarkGrpDTO.getOrderNo()%>"></td>
			</tr>
			<tr>
				<th style="background-color: white;" colspan="2"><a href="http://localhost:8080/bookmarkGrp.jsp">돌아가기</a><a>|</a><button type="submit" form="bookMarkGrp">삭제</button></th>
			</tr>
			<% } %>
		</table>
	</form>
</body>
</html>
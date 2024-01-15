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
	<form action="addBookmarkGrpSubmit.jsp" id="bookMarkGrp">
		<table class="detail">
			<tr>
				<th>북마크 이름</th>
				<td><input type="text" name="bookmarkNm"></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="text" name="orderNo"></td>
			</tr>
			<tr>
				<th style="background-color: white;" colspan="2"><button type="submit" form="bookMarkGrp">추가</button></th>
			</tr>
		</table>
	</form>
</body>
</html>
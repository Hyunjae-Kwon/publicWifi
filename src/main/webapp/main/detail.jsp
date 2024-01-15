<%@page import="DTO.BookMarkGrpDTO"%>
<%@page import="DAO.BookMarkGrpDAO"%>
<%@page import="DTO.WifiDTO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.WifiDAO"%>
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
		WifiDAO wifiDAO = new WifiDAO();
		String distance = request.getParameter("distance");
		String mgrNo = request.getParameter("mgrNo");
		String wifiName = request.getParameter("wifiname");
		List<WifiDTO> wifi = wifiDAO.selectWifi(mgrNo, Double.parseDouble(distance));
		
		BookMarkGrpDAO bookmarkGrpDAO = new BookMarkGrpDAO();
		List<BookMarkGrpDTO> bookmarkList = bookmarkGrpDAO.getBookMarkGrpList();
	%>
	<div>
		<h1>와이파이 상세보기</h1>
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
		<form action="http://localhost:8080/bookmark/addBookmark.jsp" id="addBookmark">
			<select name="bookmarkGrpId" required="required">
				<option value="" selected disabled hidden>북마크 그룹 이름 선택</option>
				<% for(BookMarkGrpDTO bookmarkGrpDTO : bookmarkList) { %>
				<option value="<%=bookmarkGrpDTO.getId() %>"><%=bookmarkGrpDTO.getBookmarkGrpNm() %></option>
				<% } %>
			</select>
			<input type="hidden" name="mgrNo" value="<%=mgrNo%>">
			<button type="submit" form="addBookmark">즐겨찾기 추가하기</button>
		</form>
	</div>
	<table class="detail">
		<% for(WifiDTO wifiDTO : wifi) { %>
		<tr>
			<th>거리(Km)</th>
			<td><%=wifiDTO.getDistance() %></td>
		</tr>
		<tr>
			<th>관리번호</th>
			<td><%=wifiDTO.getXSwifiMgrNo() %></td>
		</tr>
		<tr>
			<th>자치구</th>
			<td><%=wifiDTO.getXSwifiWrdofc() %></td>
		</tr>
		<tr>
			<th>와이파이명</th>
			<td><%=wifiDTO.getXSwifiMainNm() %></td>
		</tr>
		<tr>
			<th>도로명주소</th>
			<td><%=wifiDTO.getXSwifiAdres1() %></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><%=wifiDTO.getXSwifiAdres2() %></td>
		</tr>
		<tr>
			<th>설치위치(층)</th>
			<td><%=wifiDTO.getXSwifiInstlFloor() %></td>
		</tr>
		<tr>
			<th>설치유형</th>
			<td><%=wifiDTO.getXSwifiInstlTy() %></td>
		</tr>
		<tr>
			<th>설치기관</th>
			<td><%=wifiDTO.getXSwifiInstlMby() %></td>
		</tr>
		<tr>
			<th>서비스구분</th>
			<td><%=wifiDTO.getXSwifiSvcSe() %></td>
		</tr>
		<tr>
			<th>망종류</th>
			<td><%=wifiDTO.getXSwifiCmcwr() %></td>
		</tr>
		<tr>
			<th>설치년도</th>
			<td><%=wifiDTO.getXSwifiCnstcYear() %></td>
		</tr>
		<tr>
			<th>실내외구분</th>
			<td><%=wifiDTO.getXSwifiInoutDoor() %></td>
		</tr>
		<tr>
			<th>WIFI접속환경</th>
			<td><%=wifiDTO.getXSwifiRemars3() %></td>
		</tr>
		<tr>
			<th>X좌표</th>
			<td><%=wifiDTO.getLat() %></td>
		</tr>
		<tr>
			<th>Y좌표</th>
			<td><%=wifiDTO.getLnt() %></td>
		</tr>
		<tr>
			<th>작업일자</th>
			<td><%=wifiDTO.getWorkDttm() %></td>
		</tr>
		<% } %>
	</table>
</body>
</html>
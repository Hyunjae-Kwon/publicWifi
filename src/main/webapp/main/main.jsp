<%@page import="java.util.List"%>
<%@page import="DTO.WifiDTO"%>
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
		String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
		String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
	%>
	<div>
		<h1>와이파이 정보 구하기</h1>
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
		<span>LAT : </span>
		<input type="text" id="lat" value="<%=lat%>"/>
		
		<span>, LNT : </span>
		<input type="text" id="lnt" value="<%=lnt%>"/>
		
		<button id="btn_myLocation"><span>내 위치 가져오기</span></button>
		<button id="btn_nearWifi"><span>근처 WIFI 정보 보기</span></button>
	</div>
	<table>
		<tr>
			<th>거리(Km)</th>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>
			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>WIFI 접속 환경</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>작업일자</th>
		</tr>
		<%
			if(!("0.0").equals(lat) && !("0.0").equals(lnt)) {
				WifiDAO wifiDAO = new WifiDAO();
				List<WifiDTO> list = wifiDAO.getNearWifi(lat, lnt);
				
				if(list != null) {
					for(WifiDTO wifiDTO : list) {
		%>
						<tr>
							<td><%=wifiDTO.getDistance()%></td>
							<td><%=wifiDTO.getXSwifiMgrNo()%></td>
							<td><%=wifiDTO.getXSwifiWrdofc()%></td>
							<td><a href="detail.jsp?distance=<%=wifiDTO.getDistance()%>&mgrNo=<%=wifiDTO.getXSwifiMgrNo()%>&wifiname=<%=wifiDTO.getXSwifiMainNm()%>"><%=wifiDTO.getXSwifiMainNm()%></a></td>
							<td><%=wifiDTO.getXSwifiAdres1()%></td>
							<td><%=wifiDTO.getXSwifiAdres2()%></td>
							<td><%=wifiDTO.getXSwifiInstlFloor()%></td>
							<td><%=wifiDTO.getXSwifiInstlMby()%></td>
							<td><%=wifiDTO.getXSwifiInstlTy()%></td>
							<td><%=wifiDTO.getXSwifiSvcSe()%></td>
							<td><%=wifiDTO.getXSwifiCmcwr()%></td>
							<td><%=wifiDTO.getXSwifiCnstcYear()%></td>
							<td><%=wifiDTO.getXSwifiInoutDoor()%></td>
							<td><%=wifiDTO.getXSwifiRemars3()%></td>
							<td><%=wifiDTO.getLat()%></td>
							<td><%=wifiDTO.getLnt()%></td>
							<td><%=wifiDTO.getWorkDttm()%></td>
						</tr>
					<% } %>			
				<% } else { %>
					<tr>
						<td colspan='17' style="padding: 20px;">와이파이 정보를 가져온 후에 조회해 주세요.</td>
					</tr>
				<% } %>
			<% } else { %>
				<tr>
					<td colspan='17' style="padding: 20px;">위치 정보를 입력한 후에 조회해 주세요.</td>
				</tr>
			<% } %>
	</table>
	
<script>
	let getMyLocation = document.getElementById("btn_myLocation");
	let getNearWifi = document.getElementById("btn_nearWifi");
	
	let lat = null;
	let lnt = null;
	
	window.onload = () => {
		lat = document.getElementById("lat").value;
		lnt = document.getElementById("lnt").value;
	}
	
	getMyLocation.addEventListener("click", function() {
		if('geolocation' in navigator) {
			navigator.geolocation.getCurrentPosition(function(position){
				let latitude = position.coords.latitude;
				let longitude = position.coords.longitude;
				document.getElementById("lat").value = latitude;
				document.getElementById("lnt").value = longitude;
			})
		} else {
			alert ("위치 정보를 조회할 수 없습니다.");
		}
	});
	
	getNearWifi.addEventListener("click", function() {
		let latitude = document.getElementById("lat").value;
		let longitude = document.getElementById("lnt").value;
		
		if(latitude !== "" || longitude !== "") {
			window.location.assign("http://localhost:8080/main/main.jsp?lat=" + latitude + "&lnt=" + longitude);
		} else {
			alert("위치 정보 입력 후 조회해주세요.");
		}
	})
</script>
</body>
</html>
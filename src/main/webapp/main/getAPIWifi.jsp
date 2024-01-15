<%@page import="DAO.WifiDAO"%>
<%@page import="Service.WifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
div{text-align: center;}
</style>
<meta charset="EUC-KR">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
		WifiDAO wifiDAO = new WifiDAO();
		int chk = wifiDAO.totalCntWifi();
		
		if(chk > 0) {
			wifiDAO.deleteWifi();
		}
		WifiService wifiService = new WifiService();
		int count = wifiService.getPublicWifiJson();
	%>
<div>
	<% if (count > 0) { %>
		<h1><%= count %> 개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
</div>
<div>
	<a href="http://localhost:8080/">홈으로 가기</a>
	<% } else { %>
		<h1>데이터 저장 실패</h1>
	<% } %>
</div>
</body>
</html>
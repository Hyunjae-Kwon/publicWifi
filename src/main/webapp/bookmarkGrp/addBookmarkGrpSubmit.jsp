<%@page import="DAO.BookMarkGrpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		BookMarkGrpDAO bookmarkGrpDAO = new BookMarkGrpDAO();
		String bookmarkGrpNm = request.getParameter("bookmarkNm");
		String orderNo = request.getParameter("orderNo");
		int result = bookmarkGrpDAO.insertBookMarkGrp(bookmarkGrpNm, orderNo);
	%>
</body>
<script>
	<%
		String comment = result > 0 ? "북마크 그룹 정보를 추가하였습니다." : "북마크 그룹 정보를 추가하지 못하였습니다.";
	%>
	alert("<%= comment %>");
	location.href = "bookmarkGrp.jsp";
</script>
</html>
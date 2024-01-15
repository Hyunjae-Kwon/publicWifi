<%@page import="DAO.BookMarkDAO"%>
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
		BookMarkDAO bookmarkDAO = new BookMarkDAO();
		String bookmarkGrpId = request.getParameter("bookmarkGrpId");
		String mgrNo = request.getParameter("mgrNo");
		int result = bookmarkDAO.insertBookMark(bookmarkGrpId, mgrNo);
	%>
</body>
<script>
	<%
		String comment = result > 0 ? "북마크 정보를 추가하였습니다." : "북마크 정보를 추가하지 못하였습니다.";
	%>
	alert("<%= comment %>");
	location.href = "bookmark.jsp";
</script>
</html>
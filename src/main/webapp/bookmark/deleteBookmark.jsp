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
		String id = request.getParameter("id");
		int result = bookmarkDAO.deleteBookMark(id);
	%>
</body>
<script>
	<%
		String comment = result > 0 ? "북마크 정보를 삭제하였습니다." : "북마크 정보를 삭제하지 못하였습니다.";
	%>
	alert("<%= comment %>");
	location.href = "bookmark.jsp";
</script>
</html>
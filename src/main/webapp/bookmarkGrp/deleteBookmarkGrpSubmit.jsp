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
		String id = request.getParameter("id");
		int result = bookmarkGrpDAO.deleteBookMarkGrp(id);
	%>
</body>
<script>
	<%
		String comment = result > 0 ? "북마크 그룹 정보를 삭제하였습니다." : "북마크 그룹 정보를 삭제하지 못하였습니다.";
	%>
	alert("<%= comment %>");
	location.href = "bookmarkGrp.jsp";
</script>
</html>
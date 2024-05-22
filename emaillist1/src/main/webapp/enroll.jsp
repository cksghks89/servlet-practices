<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="emaillist1.vo.EmaillistVo" %>
<%@ page import="emaillist1.dao.EmaillistDao" %>

<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String firstName = request.getParameter("fn");
		String lastName = request.getParameter("ln");
		String email = request.getParameter("email");
		
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		boolean result = new EmaillistDao().insert(vo);

		if (result) { 
		%>
			<h1>메일 리스트에 가입되었습니다.</h1>
		<%
		} else {
		%> 
			<h1>가입에 실패하였습니다.</h1>
		<%
		}
	%>
	
	<a href="/emaillist1">리스트로 돌아가기</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="emaillist1.vo.EmaillistVo"%>
<%@ page import="emaillist1.dao.EmaillistDao"%>

<%
	request.setCharacterEncoding("utf-8");
	String firstName = request.getParameter("fn");
	String lastName = request.getParameter("ln");
	String email = request.getParameter("email");
	
	EmaillistVo vo = new EmaillistVo();
	vo.setFirstName(firstName);
	vo.setLastName(lastName);
	vo.setEmail(email);
	
	boolean result = new EmaillistDao().insert(vo);
	
	if (result) {
		response.sendRedirect("/emaillist1");
	} else {
		response.sendRedirect("");
	}
%>
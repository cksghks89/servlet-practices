<%@page import="guestbook.dao.GuestbookDao"%>
<%@page import="guestbook.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");

String no = request.getParameter("no");
String password = request.getParameter("password");

GuestbookVo vo = new GuestbookVo();
vo.setNo(Long.parseLong(no));
vo.setPassword(password);

int result = new GuestbookDao().deleteByNo(vo);

response.sendRedirect("/guestbook1");
%>
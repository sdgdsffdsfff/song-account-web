<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.song.account.web.config.ConfigManager"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
</body>
</html>
<%
	ConfigManager cm = ConfigManager.getInstance();
	response.sendRedirect("login.html");
%>
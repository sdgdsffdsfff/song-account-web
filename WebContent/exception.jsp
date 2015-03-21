<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.log4j.Logger" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>您的访问出错啦</title>
</head>
<body>
<%
Logger log = Logger.getLogger(this.getClass());
Exception exception = (Exception)request.getAttribute("exception");
log.error("请求错误异常", exception);
%>

<table>
<tr>
<td height="30">异常信息</td>
<td>${exception.message}</td>
</tr>
<tr>
<td height="30">异常原因</td>
<td>${exception.cause}</td>
</tr>
<tr>
<td colspan="2">
	<div>${exception.stackTrace}</div>
</td>
</tr>
</table>

</body>
</html>
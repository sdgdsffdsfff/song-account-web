<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/mainNav_member.css?version=<%=cm.getWebsiteVersion()%>"/>
  </head>
  
  <body>
  <div class="header-box-out">
  <div class="win-990 header-box">
  <div class="header-logo"><a class="logo-link" href="<%=cm.getWebsiteAccountUri()%>" title="<s:text name="website.title.name"></s:text>"></a></div>
  <ul class="menu-list">
  <li><a href="<%=cm.getWebsiteDapeiUri()%>/index.html;jsessionid=<%=session.getId()%>">首页</a></li>
  <li><a href="${request.contextPath}/member/userBasic.html" class="selected">账号管理</a></li>
  <li><a href="<%=cm.getWebsiteDapeiUri()%>/my/devel.html;jsessionid=<%=session.getId()%>">我的衣美</a></li>
  <li><a href="<%=cm.getWebsiteDapeiUri()%>/home.html;jsessionid=<%=session.getId()%>?u=${currUser.userId}">我的主页</a></li>
  </ul>
  </div>
  </div>
  </body>
  
</html>

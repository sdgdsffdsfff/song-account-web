<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.song.account.web.config.*" %>
<%@ taglib uri="http://account.yimeipei.com/taglib/util" prefix="xzg"%>
<%ConfigManager cm = ConfigManager.getInstance();%>
<%String share = request.getParameter("share");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:wb=“http://open.weibo.com/wb”>
  <head>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/mainNav.css?version=<%=cm.getWebsiteVersion()%>"/>
	<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
  <div id="main-body" class="main-body">
  <div class="win-990 main-nav">
  <div class="pm-nav-text">
  <a href="<%=cm.getWebsiteDapeiUri()%>/index.html" style="display: inline-block;"><s:text name="website.title.name"></s:text></a>
  </div>
  <ul class="user-text-info">
  <s:if test="%{currUser == null}">
  <li class="left-css text-li"><a href="${request.contextPath}/login.html">登入</a></li>
  <li class="left-css text-li"><a href="${request.contextPath}/register.html">注册</a></li>
  </s:if>
  <s:else>
  <li class="left-css text-li"><a href="<%=cm.getWebsiteDapeiUri()%>/my/devel.html" class="text-blue">
  <span class="user-img"><img width="20" height="20" src="<xzg:imgUrl imgUrl="${currUser.photoPath}"/>"/></span>
  <span class="user-nick">${currUser.nickName}</span></a></li>
  <li class="left-css text-li"><a href="${request.contextPath}/logout.html">退出</a></li>
  </s:else>
  <li class="left-css text-li">|</li>
  <li class="left-css text-li"><a href="#" class="text-red">手机版</a></li>
  </ul>
  </div>
  </div>
  </body>
</html>

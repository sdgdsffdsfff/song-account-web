<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>注册 - <s:text name="website.title.name"></s:text></title>
    <jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/proSearch.css?version=<%=cm.getWebsiteVersion()%>"/>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/regSuccess.css?version=<%=cm.getWebsiteVersion()%>"/>
  </head>
  
  <body>
    <jsp:include page="/WEB-INF/jsp_account/mainNav.jsp"></jsp:include>

    <div class="win-970">
    <div class="register-main">
    <div class="m-up">
    <div class="m-u-login">
    <h2 class="m-bd left-css">恭喜您注册成功：</h2>
    <p class="m-u-p">您可以选择如下操作</p>
    <div class="clear"></div>
    </div>
    <a href="${request.contextPath}/member/userBasic.html" target="_blank" class="bc_butn_b"><span class="butn_b">资料完善</span></a>
    <a href="${request.contextPath}/member/email.html" target="_blank" class="bc_butn_b"><span class="butn_b">邮箱绑定</span></a>
    <!-- <a href="${request.contextPath}/member/bindSynch.html" target="_blank" class="bc_butn_b"><span class="butn_b">平台绑定</span></a> -->
    <a href="${request.contextPath}/index.html" target="_blank" class="bc_butn_b"><span class="butn_b">开始购物</span></a>
    <a href="${request.contextPath}/seller/storeApply.html" target="_blank" class="bc_butn_b"><span class="butn_b">申请店铺入住</span></a>
    </div>
    <div class="m-down">
    <h2 class="m-bd">宝贝推荐</h2>
    <div class="top-css">
    
    <div class="clear"></div>
    </div>
    </div>
    <div class="clear"></div>
    </div>
    </div>
    
    <jsp:include page="/WEB-INF/jsp_account/foot.jsp"></jsp:include>
  </body>
</html>

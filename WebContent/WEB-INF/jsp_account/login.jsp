<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>登入 - <s:text name="website.title.name"></s:text></title>
	<jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/login.css?version=<%=cm.getWebsiteVersion()%>"/>
	<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_account/login.js?version=<%=cm.getWebsiteVersion()%>"></script>
  </head>
  
  <body>
    <jsp:include page="/WEB-INF/jsp_account/mainNav.jsp"></jsp:include>
    
    <div class="win-970">
    <div class="login-main">
    <div class="m-left">
    <h2 class="m-bd">登入<s:text name="website.title.name"></s:text></h2>
    <form action="${request.contextPath}/checkLogin.html" method="post" name="loginform" id="loginform">
    <ul class="ul-hd">
        <li>
            <div class="rtable-t left-css"><span class="rt-b">*</span> 账号/邮箱：</div>
            <input name="account" class="li-input" id="account" type="text" value="${param.account}"/>
            <div class="li-prompt left-css" id="accountTip" style="display:none;"></div>
        </li>
        <li>
            <div class="rtable-t left-css"><span class="rt-b">*</span> 输入密码：</div>
            <input name="password" class="li-input" id="password" type="password"/>
            <div class="li-prompt left-css" id="passwordTip" style="display:none;"></div>
        </li>
        <li>
            <div class="rtable-t rtable-lnow"></div>
            <input name="agreement" id="s_hc" checked="checked" class="bc_check" type="checkbox"/>
            <label class="bc_lab" for="s_hc">一个月内保持登录</label>
        </li>
        <li>
            <div class="rtable-t rtable-lnow"></div>
            <div class="bc-dlu">
                <a href="javascript:void(0);" class="bc_butn_a left-css" onclick="submitLoginForm();return false;">
                <span class="butn_a">登 录</span></a>
                <!-- <a href="${request.contextPath}/recoverPassword.html" target="_blank" class="fgt-word">忘记密码？</a> -->
            </div>
        </li>
        <s:if test="%{#errInfo != null && #errInfo != ''}">
        <li><div class="rtable-t rtable-lnow"></div>
        <div class="li-info"><div class="box box-error">${errInfo}</div></div>
        </li>
        </s:if>
        <li class="mnone">
            <div class="rtable-t rtable-lnow"></div>
            <div class="bc-hz">
                <span class="bc-hz-p">您还可以使用以下合作网站账号登入<s:text name="website.title.name"></s:text>：</span>
                <div class="bc-other-logo">
                    <span class="bc-logo-all bc-logo-qq"><s></s><a href="${request.contextPath}/loginQq.html" target="_blank">QQ</a></span>
                    <span class="bc-logo-all bc-logo-sina"><s></s><a href="${request.contextPath}/loginSina.html" target="_blank">新浪微博</a></span>
                    <!-- 
                    <span class="bc-logo-all bc-logo-ren"><s></s><a href="${request.contextPath}/loginRenren.html" target="_blank" onclick="_gaq.push(['_trackEvent', 'SSOLogin', 'LoginPage', 'RenRen']);">人人网</a></span>
                    <span class="bc-logo-all bc-logo-kaixin"><s></s><a href="${request.contextPath}/loginKaixin.html" target="_blank" onclick="_gaq.push(['_trackEvent', 'SSOLogin', 'LoginPage', 'KaiXin']);">开心网</a></span>
                     -->
                </div>
            </div>
        </li>
    </ul>
    </form>
    </div>
    <div class="m-right">
    <p class="m-r-title">还没有<s:text name="website.title.name"></s:text>账号？<br/>10秒钟就能注册一个</p>
    <a href="${request.contextPath}/register.html" target="_blank" class="bc_butn_b"><span class="butn_b">立即点击注册</span></a>
    </div>
    <div class="clear"></div>
    </div>
    
    <%Calendar c = Calendar.getInstance();%>
    <!-- <div class="login-foot">Copyright © 2013 - <%=c.get(Calendar.YEAR)%> <s:text name="website.title.name"></s:text>. All Rights Reserved</div> -->
    </div>
    
    <jsp:include page="/WEB-INF/jsp_account/foot.jsp"></jsp:include>
  </body>
</html>

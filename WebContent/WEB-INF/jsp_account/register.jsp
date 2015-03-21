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
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/register.css?version=<%=cm.getWebsiteVersion()%>"/>
	<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_account/register.js?version=<%=cm.getWebsiteVersion()%>"></script>
  </head>
  
  <body>
    <jsp:include page="/WEB-INF/jsp_account/mainNav.jsp"></jsp:include>
    
    <div class="win-970">
    <div class="register-main">
    <div class="m-up">
    <div class="m-u-login">
    <h2 class="m-bd left-css">您可以使用如下方式快速登录：</h2>
    <p class="m-u-p">已经是注册用户了？ <a href="${request.contextPath}/login.html">直接登录</a></p>
    <div class="clear"></div>
    </div>
    <a href="${request.contextPath}/loginQq.html" target="_blank"><img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_5.png" height="38"/></a>
    <a href="${request.contextPath}/loginSina.html" target="_blank"><img src="<%=cm.getResAccountUri()%>/res_account/images/sina.gif" width="198" height="38"/></a>
    </div>
    <div class="m-down">
    <h2 class="m-bd">注册<s:text name="website.title.name"></s:text>账号</h2>
    <form action="${request.contextPath}/checkregister.html" method="post" name="registerform" id="registerform">
    <ul class="ul-hd">
        <li>
            <div class="rtable-t left-css"><span class="rt-b">*</span> 账号：</div>
            <input id="account" name="account" class="li-input" type="text" value="${param.account}"/>
            <div class="li-prompt left-css" id="accountTip">
            <span class="li-gray-span">英文字符、数字或者“_”组成，长度控制在5-50个字符，不区分大小写</span>
            </div>
        </li>
        <li>
            <div class="rtable-t left-css"><span class="rt-b">*</span> 昵称：</div>
            <input id="nickName" name="nickName" class="li-input" type="text" value="${param.nickName}"/>
            <div class="li-prompt left-css" id="nickNameTip">
            <span class="li-gray-span">长度控制在12个字符以内</span>
            </div>
        </li>
        <li>
            <div class="rtable-t left-css"><span class="rt-b">*</span> 密码：</div>
            <input id="password" name="password" class="li-input" maxlength="50" type="password"/>
            <div class="li-prompt left-css" id="passwordTip">
            <span class="li-gray-span">密码长度6～20位，字母区分大小写</span>
            </div>
        </li>
        <li>
            <div class="rtable-t left-css"><span class="rt-b">*</span> 确认密码：</div>
            <input id="password2" name="password2" class="li-input" maxlength="50" type="password"/>
            <div class="li-prompt left-css" id="password2Tip">
            <span class="li-gray-span">请再输入一次密码</span>
            </div>
        </li>
        <li>
            <div class="rtable-t left-css"><span class="rt-b">*</span> 验证码：</div>
            <input id="idcode" name="idcode" class="li-input" maxlength="50" type="text"/>
            <div class="li-prompt left-css" id="idcodeTip">
            <img src="${request.contextPath}/showValidateCode.html?t=<%=new java.util.Date()%>" onclick="Global.showValidateCode(this);" style="cursor: pointer;"/>
            <span class="li-gray-span">看不清请单击验证码图片</span>
            </div>
        </li>
        <li>
            <div class="rtable-t rtable-lnow"></div>
            <div class="bc-dx">
                <input name="agreement" id="agreement" checked="checked" class="bc_check" type="checkbox" value="ok"/>
                <label class="bc_lab" for="agreement">我同意 《<s:text name="website.title.name"></s:text>服务条款》</label>
            </div>
        </li>
        <li>
            <div class="rtable-t rtable-lnow"></div>
            <div class="bc-dlu">
                <a href="javascript:void(0);" class="bc_butn_a left-css" onclick="submitRegisterForm();return false;">
                <span class="butn_a">立即点击注册</span></a>
                <div class="li-prompt left-css" id="agreementTip">
                </div>
            </div>
        </li>
        <s:if test="%{#errInfo != null && #errInfo != ''}">
        <li><div class="rtable-t rtable-lnow"></div>
        <div class="li-err-info"><div class="box box-error">${errInfo}</div></div>
        </li>
        </s:if>
    </ul>
    </form>
    </div>
    <div class="clear"></div>
    </div>
    
    <%Calendar c = Calendar.getInstance();%>
    <!-- <div class="register-foot">Copyright © 2013 - <%=c.get(Calendar.YEAR)%> <s:text name="website.title.name"></s:text>. All Rights Reserved</div> -->
    </div>
    
    <jsp:include page="/WEB-INF/jsp_account/foot.jsp"></jsp:include>
  </body>
</html>

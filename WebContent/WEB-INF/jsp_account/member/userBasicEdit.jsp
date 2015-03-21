<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.song.account.web.config.*" %>
<%@ page import="com.song.account.web.bean.ConstantVal" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>基本信息修改 - <s:text name="website.title.name"></s:text></title>
	<jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/memberBasic.css?version=<%=cm.getWebsiteVersion()%>"/>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/userBasicEdit.css?version=<%=cm.getWebsiteVersion()%>"/>
	<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_account/userBasicEdit.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var result = "<%=session.getAttribute(ConstantVal.SESSION_OPERATION) %>";
		if (result == 'ok') {
			Dgbox.alert("修改完成！", function() {}, {title:"提示信息"});
		}
		<%session.setAttribute(ConstantVal.SESSION_OPERATION, null);%>
	});
	</script>
  </head>
  
  <body>
    <div>
    <jsp:include page="/WEB-INF/jsp_account/mainNav.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/jsp_account/member/mainNav_member.jsp"></jsp:include>
    
    <div class="win-990">
    <div class="top-css">
    
    <div class="user-basic-left">
    <jsp:include page="/WEB-INF/jsp_account/member/memberLeft.jsp">
    	<jsp:param value="1" name="item"/>
    </jsp:include>
    </div>
    <div class="user-basic-right">
    <div class="user-basic-main">
    <p class="model-title">基本信息</p>
    <form action="${request.contextPath}/member/editUserBasic.html" method="post" name="userBasicEditForm" id="userBasicEditForm">
    <div class="form-css">
		<p>
			<label>
				账号：<span class="red"><s:if test="#user.account!=null">${user.account}</s:if><s:else>第三方账号注册</s:else></span>
			</label>
		</p>
		<p>
			<label class="required">
				姓名：<span class="color-css">请填写您真实的姓名</span>
			</label>
			<br/>
			<input class="input-css" id="userName" name="userName" type="text" value="${user.userName}"/>
		</p>
		<p>
			<label class="required">
				昵称：<span class="color-css">长度控制在12个字符以内</span>
			</label>
			<br/>
			<input class="input-css" id="nickName" name="nickName" type="text" value="${user.nickName}"/>
		</p>
		<p>
			<label>
				性别：
			</label>
			<input id="sex2" name="sex" type="radio" value="2" <s:if test="#user.sex == 2">checked="checked"</s:if>/><label for="sex2">美女</label>
			<b style="display: inline-block;width: 10px;"></b>
			<input id="sex1" name="sex" type="radio" value="1" <s:if test="#user.sex == 1">checked="checked"</s:if>/><label for="sex1">帅哥</label>
		</p>
		<p>
			<label class="required">
				QQ号：<span class="color-css">腾讯QQ号</span>
			</label>
			<br/>
			<input class="input-css" id="qqId" name="qqId" type="text" value="${user.qqId}"/>
		</p>
		<p>
			<label>
				旺旺ID：<span class="color-css">淘宝旺旺ID</span>
			</label>
			<br/>
			<input class="input-css" id="wangwangId" name="wangwangId" type="text" value="${user.wangwangId}"/>
		</p>
		<p>
			<label class="required">
				生日：<span class="color-css">请填写公历生日</span>
			</label>
			<br/>
			<s:select list="#years" value="%{#user.birthdayYear}" id="birthdayYear" name="birthdayYear" theme="simple"></s:select>
            <label>年</label>
            <s:select list="#months" value="%{#user.birthdayMonth}" id="birthdayMonth" name="birthdayMonth" theme="simple"></s:select>
            <label>月</label>
            <s:select list="#days" value="%{#user.birthdayDay}" id="birthdayDay" name="birthdayDay" theme="simple"></s:select>
            <label>日</label>
		</p>
		<p>
			<label>
				简介：<span class="color-css">犀利地介绍一下自己</span>
			</label>
			<br/>
			<textarea class="input-css" id="summary" name="summary">${user.summary}</textarea>
		</p>
		<div class="top-css box box-error" id="errorInfo" style="width: 210px;display: none;"></div>
		<p>
			<a href="javascript:void(0);" class="btn_css btn_css_green" onclick="UserBasicEdit.editUserBasic();">完成修改</a>
		</p>
	</div>
	</form>
    </div>
    </div>
    <div class="clear"></div>
    </div>
    </div>
    
    <jsp:include page="/WEB-INF/jsp_account/foot.jsp"></jsp:include>
    </div>
  </body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://account.yimeipei.com/taglib/util" prefix="xzg"%>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>邮箱绑定 - <s:text name="website.title.name"></s:text></title>
	<jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/memberBasic.css?version=<%=cm.getWebsiteVersion()%>"/>
	<script type="text/javascript">
	function submitForm(result) {
		if (result == 'edit') {
			$('#userEmailEditForm').attr('action', '${request.contextPath}/member/editUserEmail.html?result=edit');
			$('#userEmailEditForm').submit();
		} else {
			$('#userEmailEditForm').submit();
		}
		
	}
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
    	<jsp:param value="4" name="item"/>
    </jsp:include>
    </div>
    <div class="user-basic-right">
    <div class="user-basic-main">
    <p class="model-title">邮箱绑定</p>
    <!-- 内容开始 -->
    <form action="${request.contextPath}/member/editUserEmail.html" method="post" name="userEmailEditForm" id="userEmailEditForm">
    <div class="form-css">
		<!-- <p>
			<label>
				账号：<span class="red">${currUser.account}</span>
			</label>
		</p> -->
		<s:if test="%{#emailState == 1}">
		<p>
			<label>
				电子邮箱：
			</label>
			<br/>
			<input class="input-css" id="email" name="email" type="text" value="${currUser.email}"/>
		</p>
		</s:if>
		<s:elseif test="#emailState == 2">
		<p>
			<label>
				邮箱激活验证链接已经发送至您的电子邮箱：<span class="red">${currUser.email}</span>，请查收。
				<input id="email" name="email" type="hidden" value="${currUser.email}"/>
			</label>
		</p>
		</s:elseif>
		<s:elseif test="#emailState == 3">
		<p>
			<label>
				您电子邮箱已经绑定成功：<span class="red">${currUser.enEmail}</span>
			</label>
		</p>
		</s:elseif>
		<p>
			1、绑定邮箱用于找回密码；
		</p>
		<p>
			2、绑定成功可以用邮箱登录；
		</p>
		<p>
			2、如果修改邮箱，老的邮箱将作废，新的邮箱将启用。
		</p>
		<p>
		<s:if test="#emailState == 1">
			<a href="javascript:void(0);" class="btn_css btn_css_green" onclick="submitForm();">绑定邮箱</a>
		</s:if>
		<s:elseif test="#emailState == 2">
			<a href="javascript:void(0);" class="btn_css btn_css_green" onclick="submitForm();">未收到邮件，重新发送验证邮件</a>
			<a href="javascript:void(0);" class="btn_css btn_css_green" onclick="submitForm('edit');">更换绑定邮箱</a>
		</s:elseif>
		<s:elseif test="#emailState == 3">
			<input type="hidden" value="edit" name="result"/>
			<a href="javascript:void(0);" class="btn_css btn_css_green" onclick="submitForm();">更换绑定邮箱</a>
		</s:elseif>
		</p>
	</div>
	</form>
    <!-- 内容结束 -->
    </div>
    </div>
    <div class="clear"></div>
    </div>
    </div>
    
    <jsp:include page="/WEB-INF/jsp_account/foot.jsp"></jsp:include>
    </div>
  </body>
</html>

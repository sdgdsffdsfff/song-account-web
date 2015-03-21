<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://account.yimeipei.com/taglib/util" prefix="xzg"%>
<%@ page import="com.song.account.web.config.*" %>
<%@ page import="com.song.account.web.bean.ConstantVal" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>密码修改 - <s:text name="website.title.name"></s:text></title>
	<jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/memberBasic.css?version=<%=cm.getWebsiteVersion()%>"/>
	<script type="text/javascript">
	function submitForm() {
		$('#userPaswEditForm').submit();
	}
	$(document).ready(function() {
		var result = "<%=session.getAttribute(ConstantVal.SESSION_OPERATION) %>";
		if (result == 'ok') {
			Dgbox.alert("密码修改成功，以后请使用新密码！", function() {}, {title:"提示信息"});
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
    	<jsp:param value="5" name="item"/>
    </jsp:include>
    </div>
    <div class="user-basic-right">
    <div class="user-basic-main">
    <p class="model-title">密码修改</p>
    <!-- 内容开始 -->
    <form action="${request.contextPath}/member/editUserPasw.html" method="post" name="userPaswEditForm" id="userPaswEditForm">
    <div class="form-css">
		<p>
			<label>
				账号：<span class="red"><s:if test="#currUser.account!=null">${currUser.account}</s:if><s:else>第三方账号注册，无需修改密码！</s:else></span>
			</label>
		</p>
		<p>
			<label>
				当前密码：
			</label>
			<br/>
			<input class="input-css" id="paswOld" name="paswOld" type="password" value=""/>
		</p>
		<p>
			<label>
				新密码：<span class="color-css">长度6～20位，字母区分大小写</span>
			</label>
			<br/>
			<input class="input-css" id="paswNew" name="paswNew" type="password" value=""/>
		</p>
		<p>
			<label>
				确认密码：<span class="color-css">请与新密码保持一致</span>
			</label>
			<br/>
			<input class="input-css" id="paswNew2" name="paswNew2" type="password" value=""/>
		</p>
		<s:if test="#errInfo != null">
		<div class="top-css box box-error" id="errorInfo" style="width: 210px;">${errInfo}</div>
		</s:if>
		<p>
			<a href="javascript:void(0);" class="btn_css btn_css_green" onclick="submitForm();">完成密码</a>
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

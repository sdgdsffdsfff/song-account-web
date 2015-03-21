<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>错误信息 - <s:text name="website.title.name"></s:text></title>
	<jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/regSuccess.css?version=<%=cm.getWebsiteVersion()%>"/>
  </head>
  
  <body>
    <jsp:include page="/WEB-INF/jsp_account/mainNav.jsp"></jsp:include>

    <div class="win-970" style="width: 480px;">
    <div class="register-main">
    <div class="m-up">
    <div class="m-u-login">
    <h2 class="m-bd">亲，</h2>
    <div class="clear"></div>
    </div>
    </div>
    <div class="m-down">
    <p style="font-size: 17px;line-height: 30px;" class="red">${errInfo}</p>
    <p style="margin-top: 10px;"><a href="javascript:void(0);" class="btn_css btn_css_green" onclick="javascript:history.go(-1);return false;">返回继续完成操作</a></p>
    </div>
    <div class="clear"></div>
    </div>
    
    </div>
    
    <jsp:include page="/WEB-INF/jsp_account/foot.jsp"></jsp:include>
  </body>
</html>

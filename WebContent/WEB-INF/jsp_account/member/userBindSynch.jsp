<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://account.yimeipei.com/taglib/util" prefix="xzg"%>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>平台绑定 - <s:text name="website.title.name"></s:text></title>
	<jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/memberBasic.css?version=<%=cm.getWebsiteVersion()%>"/>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/userBindSynch.css?version=<%=cm.getWebsiteVersion()%>"/>
  </head>
  
  <body>
    <div>
    <jsp:include page="/WEB-INF/jsp_account/mainNav.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/jsp_account/member/mainNav_member.jsp"></jsp:include>
    
    <div class="win-990">
    <div class="top-css">
    
    <div class="user-basic-left">
    <jsp:include page="/WEB-INF/jsp_account/member/memberLeft.jsp">
    	<jsp:param value="3" name="item"/>
    </jsp:include>
    </div>
    <div class="user-basic-right">
    <div class="user-basic-main">
    <p class="model-title">平台绑定</p>
    <!-- 内容开始 -->
    <div class="form-css">
    <p>
		<label>
			账号：<span class="red"><s:if test="#user.account!=null">${user.account}</s:if><s:else>第三方账号注册</s:else></span>
		</label>
	</p>
    
    <ul class="userBindSites">
    <s:if test="#bindSiteQq == null">
    <li>
    <p class="bp1"><a href="${request.contextPath}/loginQq.html"><img src="http://test.vlook.cn:9889/images/share/tencent/tencent_logo_32.png"/></a></p>
    <p class="bp2">未绑定</p>
    <p class="bp3">&nbsp;</p>
    <p class="bp4"><a href="${request.contextPath}/loginQq.html">绑定账号</a></p>
    </li>
    </s:if>
    <s:else>
    <li>
    <p class="bp1"><img src="http://test.vlook.cn:9889/images/share/tencent/tencent_logo_32.png"/></p>
    <p class="bp2 bind">已绑定</p>
    <p class="bp3">绑定帐号：${bindSiteQq.nickName} <!--（已经过期，<a href="${request.contextPath}/loginQq.html">重新绑定</a>）--></p>
    <p class="bp4"><a href="${request.contextPath}/member/delBindSite.html?bindSiteId=${bindSiteQq.bindSiteId}">取消绑定</a></p>
    </li>
    </s:else>
    
    <s:if test="#bindSiteSina == null">
    <li>
    <p class="bp1"><a href="${request.contextPath}/loginSina.html"><img src="http://test.vlook.cn:9889/images/logo1.jpg"/></a></p>
    <p class="bp2">未绑定</p>
    <p class="bp3">&nbsp;</p>
    <p class="bp4"><a href="${request.contextPath}/loginSina.html">绑定账号</a></p>
    </li>
    </s:if>
    <s:else>
    <li>
    <p class="bp1"><img src="http://test.vlook.cn:9889/images/logo1.jpg"/></p>
    <p class="bp2 bind">已绑定</p>
    <p class="bp3">绑定帐号： ${bindSiteSina.nickName}<!--（已经过期，<a href="${request.contextPath}/loginSina.html">重新绑定</a>）--></p>
    <p class="bp4"><a href="${request.contextPath}/member/delBindSite.html?bindSiteId=${bindSiteSina.bindSiteId}">取消绑定</a></p>
    </li>
    </s:else>
     
    </ul>
    
    <div class="clear"></div>
    </div>
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

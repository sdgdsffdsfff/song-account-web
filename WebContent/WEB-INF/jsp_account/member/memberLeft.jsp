<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.song.account.web.config.*" %>
<%@ taglib uri="http://account.yimeipei.com/taglib/util" prefix="acc"%>
<%ConfigManager cm = ConfigManager.getInstance();%>

<div class="user-img-left">
<img src="<acc:imgUrl imgUrl="${currUser.photoPath}"/>" alt="${currUser.nickName}" />
<div class="clear"></div>
</div>

<div class="user-operation">
<ul class="operation-list">
<li><a href="${request.contextPath}/member/userBasic.html" <%="1".equals(request.getParameter("item")) ? "class='fixed'" : ""%>>基本信息</a></li>
<li><a href="${request.contextPath}/member/photo.html" <%="2".equals(request.getParameter("item")) ? "class='fixed'" : ""%>>上传头像</a></li>
<li><a href="${request.contextPath}/member/bindSynch.html" <%="3".equals(request.getParameter("item")) ? "class='fixed'" : ""%>>平台绑定</a></li>
<li><a href="${request.contextPath}/member/email.html" <%="4".equals(request.getParameter("item")) ? "class='fixed'" : ""%>>邮箱绑定</a></li>
<li><a href="${request.contextPath}/member/password.html" <%="5".equals(request.getParameter("item")) ? "class='fixed'" : ""%>>密码修改</a></li>
</ul>
</div>

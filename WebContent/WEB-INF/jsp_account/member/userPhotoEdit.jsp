<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://account.yimeipei.com/taglib/util" prefix="xzg"%>
<%@ page import="com.song.account.web.config.*" %>
<%@ page import="com.song.account.entity.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>
<%
	User user = (User)request.getAttribute("currUser");
	String photoPath = user.getPhotoPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>头像上传 - <s:text name="website.title.name"></s:text></title>
	<jsp:include page="/WEB-INF/jsp_account/global.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/memberBasic.css?version=<%=cm.getWebsiteVersion()%>"/>
	<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/userPhotoEdit.css?version=<%=cm.getWebsiteVersion()%>"/>
	<script type="text/javascript">
	function selPhoto(obj) {
		$('.userPhotos ul li a').each(function() {
			$(this).removeClass();
		});
		$(obj).addClass("selected");
	}
	function submitForm() {
		var path = '';
		$('.userPhotos ul li a').each(function() {
			if ($(this).attr('class') == 'selected') {
				path = $(this).attr('photopath');
			}
		});
		var option = {
			type : 'get',
			dataType : 'json',
			url : Global.URI + Global.contextPath + '/member/editUserPhoto.html',
			data : {'photoPath' : path},
			success : function(d){
				if (d.result == 1) {
					Dgbox.alert("修改完成！", null, {title:"提示信息"});
				}
			}
		};
		jQuery.ajax(option);
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
    	<jsp:param value="2" name="item"/>
    </jsp:include>
    </div>
    <div class="user-basic-right">
    <div class="user-basic-main">
    <p class="model-title">头像上传</p>
    <!-- 内容开始 -->
    <div class="userPhotos">
    <ul>
    <%for(int i = 0; i < User.PHOTO_PATH_LIST.size(); i++) {
    String ppdb = User.PHOTO_PATH_LIST.get(i);
    request.setAttribute("ppdb", ppdb);%>
    <li>
    <a photopath="<%=ppdb%>" href="javascript:void(0);" onclick="selPhoto(this);" <%if (photoPath.endsWith(ppdb)) out.print("class='selected'");%>>
    <img src="<xzg:imgUrl imgUrl="${ppdb}"/>" alt="用户头像" width="140" height="140"/>
    </a>
    </li>
    <%}%>
    </ul>
    <div class="clear"></div>
    <p class="editPhotsBut">
	<a href="javascript:void(0);" onclick="submitForm();">开始切换为选中的头像</a>
	</p>
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

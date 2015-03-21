<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>
<div class="foot-css-1">
<div class="win-990">
<div class="content-1">
<div class="brand-ligth"><img src="<%=cm.getResAccountUri()%>/res_account/images/logo_foot.png"/></div>
<ul class="icon-group">
	<li class="weibo"><a href="#" target="_blank"><i></i>微博</a><div class="code-img" style="display: none;"><div class="erweima weibo-o"></div></div></li>
	<li class="weixin"><a href="#"><i></i>微信</a><div class="code-img" style="display: none;"><div class="erweima weixin-o"></div></div></li>
	<li class="laiwang"><a href="#"><i></i>来往</a><div class="code-img" style="display: none;"><div class="erweima laiwang-o"></div></div></li>
	<li class="weitao"><a href="#" target="_blank"><i></i>微淘</a><div class="code-img" style="display: none;"><div class="erweima weitao-o"></div></div></li>
</ul>
</div>
<div class="content-2">
<dl>
	<dt>下次怎么来？</dt>
	<dd>记住域名：<span class="typo-red">yimeipei.com</span></dd>
	<dd>收藏本站：<a href="javascript:addfavorite()" target="_self">加入收藏</a></dd>
	<dd><em>百度搜索：</em><a href="http://www.baidu.com/s?wd=%E5%96%9C%E6%8A%98%E8%B4%AD" target="_blank" class="icon-baidu">百度一下</a></dd>
	<dd><em>邮箱订阅：</em><a href="#" class="icon-qqmail">订阅到QQ邮箱</a></dd>
</dl>
<dl>
	<dt>商务合作</dt>
	<dd><a href="${request.contextPath}/doc/proEnter.html#hezuoqiatan" target="_blank">合作洽谈</a></dd>
	<dd><a href="${request.contextPath}/doc/proEnter.html#zizhubaoming" target="_blank">自助报名</a></dd>
</dl>
<div class="clear"></div>
</div>
</div>
<div class="clear"></div>
</div>
<script type="text/javascript">
$('.icon-group li a').mouseover(function() {
	$(this).parent().find('.code-img').show();
}).mouseout(function() {
	$(this).parent().find('.code-img').hide();
});
</script>
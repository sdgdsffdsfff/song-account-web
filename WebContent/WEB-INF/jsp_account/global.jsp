<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.song.account.web.config.*" %>
<%ConfigManager cm = ConfigManager.getInstance();%>

<meta name="baidu-site-verification" content="vyuari6KeH" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="guang,逛,逛街,优惠码,改价,减价,立即改价,喜折,衣美配,淘宝,导购,卖家,抱团,营销,促销,折扣,折扣网,打折密码,包邮,折折"/>
<meta http-equiv="description" content="衣美配 购物四部曲：抢优惠码、拍下成功、告知客服、立即改价；提供各种打折促销信息，打折暗号促销、9.9包邮商品、29.9包邮商品等活动信息，每天精彩不断！是您砍价购物居家生活好帮手。"/>
<meta property="qc:admins" content="4675324670576375" />

<script src="http://l.tbcdn.cn/apps/top/x/sdk.js?appkey=21736179"></script>
<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_common/jquery-1.7.1.min.js?version=<%=cm.getWebsiteVersion()%>"></script>
<script type="text/javascript">
var Global = {
	URI : "<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()%>",
	contextPath : "${request.contextPath}"
};
</script>
<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/js_common/dgbox/css/song.dgbox.css?version=<%=cm.getWebsiteVersion()%>"/>
<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_common/dgbox/js/song.dgbox.js?version=<%=cm.getWebsiteVersion()%>"></script>
<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/js_common/table/css/song.table.css?version=<%=cm.getWebsiteVersion()%>"/>
<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_common/table/js/song.table.js?version=<%=cm.getWebsiteVersion()%>"></script>
<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_common/watermark/jquery.watermark.js?version=<%=cm.getWebsiteVersion()%>"></script>
<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_common/jquery.wookmark.js?version=<%=cm.getWebsiteVersion()%>"></script>
<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/js_common/pagination/css/pagination.css?version=<%=cm.getWebsiteVersion()%>"/>

<link rel="shortcut icon" href="<%=cm.getResAccountUri()%>/res_account/images/favicon.ico?version=<%=cm.getWebsiteVersion()%>" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="<%=cm.getResAccountUri()%>/res_account/css/styles.css?version=<%=cm.getWebsiteVersion()%>"/>
<script type="text/javascript" src="<%=cm.getResAccountUri()%>/js_account/global.js?version=<%=cm.getWebsiteVersion()%>"></script>
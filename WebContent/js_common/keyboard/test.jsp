<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/song.keyboard.css"/>
	<script type="text/javascript" src="../jQuery/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
	//$(document).keydown(function(event){
		//alert(event.keyCode);
		//console.log(event.keyCode);
	//});
	$(document).ready(function() {
		$("#my-keyboard").click(function() {
			var sel = document.selection;
			var texRan = sel.createRange();
			alert(texRan);
		});
		$("#mytext").focus();
	});
	</script>
  </head>
  
  <body>
    <input type="text" id="mytext" name="mytext" value="zhangsong"/>
    <input type="button" value="键盘" id="my-keyboard"/>
    <div class="keyboard-css">
    <ul>
      <li><a href="">a</a></li>
      <li><a href="">b</a></li>
      <li><a href="">c</a></li>
      <li><a href="">d</a></li>
      <li><a href="">e</a></li>
    </ul>
    <div style="clear: both;"></div>
    </div>
    
  </body>
</html>

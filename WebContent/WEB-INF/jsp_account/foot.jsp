<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.util.Calendar" %>
<%Calendar c = Calendar.getInstance();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <body>
    
    <div class="foot-css">
    <p>Copyright © 2013 - <%=c.get(Calendar.YEAR)%> <s:text name="website.title.name"></s:text>. All Rights Reserved 浙ICP备13038001号 <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1000273314'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "v1.cnzz.com/z_stat.php%3Fid%3D1000273314%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script></p>
    </div>
  </body>
</html>

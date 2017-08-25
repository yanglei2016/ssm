<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>
<%@include file="/common/include.jsp" %>
</head>
<body>

<%@include file="header.jsp" %>
<div id="middle">
<jsp:include page="left.jsp" />
    <script type="text/javascript">
		function clickMenu(menuUrl, obj)
		{
			if (menuUrl == "") 
			{
				return;
			}
			if (menuUrl.indexOf("?") != -1)
			{
				menuUrl = menuUrl + "&randomParameter="+Math.random();
			}
			else
			{
				menuUrl = menuUrl + "?randomParameter="+Math.random();
			}
			
			menuForm.target = "systemContentIFrame";
			menuForm.action = menuUrl;
			menuForm.submit();
			$(obj).parent().parent().find("div").find("a").removeClass("current");
			$(obj).addClass("current");
			layer.load(2, {shade: [0.2, '#393D49']});
		}
	</script>
	<div class="right"  id="mainFrame">
		<iframe src="welcome.do" align="left" scrolling="no" width="100%"  height="100%" id="systemContentIFrame" name="systemContentIFrame" frameborder="0" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" noresize></iframe>
 	</div>
	<form name="menuForm" id="menuForm" method="post" target="systemContentIFrame" action="">
	</form>
<jsp:include page="footer.jsp" />
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线评测</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript">


</script>
</head>
<frameset rows="50px,*,30px" border="0" frameborder="0" framespacing="0" marginwidth="0" marginheight="0">
		<frame name="header" src="${pageContext.request.contextPath }/header.action" scrolling="no">
		<frame name="main"  src="${pageContext.request.contextPath }/welcome.action" scrolling="auto">
		<frame name="footer" src="${pageContext.request.contextPath }/footer.action" scrolling="no">
</frameset>
</html>
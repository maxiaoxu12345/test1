<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工测评登录</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
  $(function(){
	  $("#isOk").click(function(){
		  var list=$(".must");
  		  for (var i = 0; i < list.length; i++) {
  			// 判断文本框是否为空
  			if (list[i].value == "") {
  			  alert("用户名和密码不能为空");
  			   return false;
  			 }
  			} 
		  
	  });
	  
	  //防止页面后退
      history.pushState(null, null, document.URL);
       window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
  });
</script>

</head>
<body>
   <div style="margin:0 auto;margin-top:20px; width: 90%; background-color: #F0F0F0;box-shadow: 10px 10px 5px #888888;height: 70%">
     <form action="${pageContext.request.contextPath}/login.action" method="post" style="margin:100px 0px 0px 400px;padding-top: 69px;padding-bottom: 65px;" >
       <table class="table table-hover table-bordered" style="width: 50%">
         <tr>
           <td style="text-align: center;">用户：</td>
           <td><input type="text" id="f_user_Dn" name="empName"  class="must" onfocus="$(this).val('')"></td>
         </tr>
         <tr  >
           <td style="text-align: center;">密码：</td>
           <td>
             <input type="password" id="input_invisible" name="pwd"  class="must" onfocus="$(this).val('')">
           </td>
         </tr>
       </table>
      <input type="submit" value="登录" style="margin: 10px 0px 0px 148px;" id="isOk">
     </form>
    </div>
</body>
</html>
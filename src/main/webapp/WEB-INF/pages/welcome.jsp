<%@ page language="java" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>欢迎使用~！</title>
    <style type="text/css">
    #mou_head{
    position: fixed;
left: 20%;
top:20%;
    }
#Idiv{
 width:400px; height:200px
}</style>
  </head>
  <body style="padding-top:40px;">
	 <h1>${user.eName}欢迎您</h1>
	 <h4>第一次登陆建议修改密码 <button onclick="showDiv()">点我修改</button></h4>
	<h4> ${tips }</h4>
<!--弹出层开始-->

<div id="Idiv" style="display:none;position:absolute; z-index:1000; background:#FFFFFF;">
    <div id="mou_head" style="width=100px; height=10px;z-index:1001; position:absolute;">
    <form action="${pageContext.request.contextPath}/editpwd.action">	
    <input type="hidden" name="uname" value="${user.loginName}">	
  		新密码: <input type="text" name="lname" /><br>	
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="确定" />
    <input type="button" value="取消" onclick="closeDiv();" />
    </form>
   </div>
</div>
	 
  </body>
  <script type="text/javascript">
function showDiv()
{
var Idiv     = document.getElementById("Idiv");
var mou_head = document.getElementById('mou_head');
Idiv.style.display = "block";
//以下部分要将弹出层居中显示
Idiv.style.left=(document.documentElement.clientWidth-Idiv.clientWidth)/2+document.documentElement.scrollLeft+"px";
Idiv.style.top =(document.documentElement.clientHeight-Idiv.clientHeight)/2+document.documentElement.scrollTop+100+"px";
 
//以下部分使整个页面至灰不可点击
var procbg = document.createElement("div"); //首先创建一个div
procbg.setAttribute("id","mybg"); //定义该div的id
procbg.style.background = "#000000";
procbg.style.width = "100%";
procbg.style.height = "100%";
procbg.style.position = "fixed";
procbg.style.top = "0";
procbg.style.left = "0";
procbg.style.zIndex = "500";
procbg.style.opacity = "0.6";
procbg.style.filter = "Alpha(opacity=70)";
//背景层加入页面
document.body.appendChild(procbg);
document.body.style.overflow = "hidden"; //取消滚动条
 
//以下部分实现弹出层的拖拽效果
var posX;
var posY;
mou_head.onmousedown=function(e)
{
if(!e) e = window.event; //IE
posX = e.clientX - parseInt(Idiv.style.left);
posY = e.clientY - parseInt(Idiv.style.top);
document.onmousemove = mousemove;
}
document.onmouseup = function()
{
document.onmousemove = null;
}
function mousemove(ev)
{
if(ev==null) ev = window.event;//IE
Idiv.style.left = (ev.clientX - posX) + "px";
Idiv.style.top = (ev.clientY - posY) + "px";
}
}
function closeDiv() //关闭弹出层
{
var Idiv=document.getElementById("Idiv");
Idiv.style.display="none";
document.body.style.overflow = "auto"; //恢复页面滚动条
var body = document.getElementsByTagName("body");
var mybg = document.getElementById("mybg");
body[0].removeChild(mybg);
}
</script> 
</html>

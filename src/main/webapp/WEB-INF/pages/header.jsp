<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">

</style>
    <title></title>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	
	
  </head>
  <body style="border-bottom:solid 1px #666;">
 
       <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			    <div class="container-fluid">
			  <!--   <div class="navbar-header">
			      <a class="navbar-brand" href="#">晨砻信息科技有限公司</a>
			    </div> -->
			    <div>
			        <ul class="nav navbar-nav" >
			           <li class="navLi"> <a class="navbar-brand" href="#" id="firstJ">晨砻信息科技有限公司</a></li>
			           
			           <c:choose>
			              <c:when test="${user.eJobType eq '领导' }">
			                 <li class="navLi"><a class="navbar-brand" href="#" id="findTestResult">查询评测结果</a></li>
			                <!--  <li class="navLi"><a class="navbar-brand" href="#" id="exportTestResult">导出结果</a></li> -->
			              </c:when>
			               <c:when test="${user.eName eq '廉雪' }">
			                 <li class="navLi"><a class="navbar-brand" href="#" id="empTestExcep">其他部门评分（选填）</a></li>
			                 <li class="navLi"><a class="navbar-brand" href="#" id="directorTest">本部门评分（必填）</a></li>
			                 <li class="navLi"><a class="navbar-brand" href="#" id="findTestResult">查询评测结果</a></li>
			                 <li class="navLi"><a class="navbar-brand" href="#" id="js-export">导出Excel</a></li>
			              </c:when>
			              <c:when test="${user.eJobType eq '经理' }">
			                  <li class="navLi"><a class="navbar-brand" href="#" id="empTestExcep">其他部门评分（选填）</a></li>
			                  <li class="navLi"><a class="navbar-brand" href="#" id="directorTest">本部门评分（必填）</a></li>
			              </c:when>
			              <c:when test="${user.eJobType eq '副经理' }">
			                   <li class="navLi"><a class="navbar-brand" href="#" id="empTestExcep">其他部门评分（选填）</a></li>
			                  <li class="navLi"><a class="navbar-brand" href="#" id="directorTest">本部门评分（必填）</a></li>
			              </c:when>
			              <c:otherwise>
			                    <li class="navLi"><a class="navbar-brand" href="#" id="empTest">员工互评</a></li>
			              </c:otherwise>
			           
			           </c:choose>

			        </ul>
			    </div>
		        <div>
			        <ul class="nav navbar-nav" style="float: right">
			            
			            <li id="logout" class="navLi"><a href="${pageContext.request.contextPath}/logout.action" style="text-decoration:none;" id="logout_a">退出登录</a></li>
			        
			        </ul>
			    </div>
		    </div>

		</nav>

  </body>
  <script type="text/javascript">
 
		$(function(){
					$("#logout").click(function(){
						if(confirm("你确定要退出吗?")){
							var url = $("#logout_a").attr("href");
							window.parent.parent.location.href = url;
							return false;
						}
						return false;
					});
			});
			$("#firstJ").click(function(){
				/* alert("点击首页"); */
				
			    parent.main.location.href="${pageContext.request.contextPath}/welcome.action";
			    return false;
			});
			$("#empTest").click(function(){
				 parent.main.location.href="${pageContext.request.contextPath}/goTest.action";
				 return false;
			});
			$("#empTestExcep").click(function(){
				 parent.main.location.href="${pageContext.request.contextPath}/goTestExcep.action";
				 return false;
			});
			
			$("#directorTest").click(function(){
				//经理评分
				 parent.main.location.href="${pageContext.request.contextPath}/goDirectorTest.action";
				 return false;
			});
			
			$("#findTestResult").click(function(){
				/* alert("查询测评结果"); */
				parent.main.location.href="${pageContext.request.contextPath}/goTestResult.action";
				 return false;
			});	
			$('#js-export').click(function(){
				parent.main.location.href="${pageContext.request.contextPath}/exportBooksTable.action";
				 return false;
	});
		
		
	</script>
</html>

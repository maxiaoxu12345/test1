<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
  <div style="margin:0 auto; width: 90%; background-color: #F0F0F0;height: 70%;margin-top:30px">
  <form id="userForm"  action="${ctx}/search.action"   method="post"  enctype="multipart/form-data" >
     <!-- 列表数据 -->
		<c:if test="${!empty emps}">
			
			<nav class="navbar navbar-default navbar-fixed-top"  style="width: 92%;margin:0 auto; background-color:rgb(185, 184, 185)">
			  <div class="container">
			    <table   class="table table-bordered" id="t1"  >
			      <thead >
					<tr>
						<th class="data_title" style="text-align: center;" rowspan="2">
							姓名
						</th>
						<th class="data_title" style="text-align: center;" rowspan="2" >
							年度工作主要成果
						</th>
						<th class="data_title" style="text-align: center;" rowspan="2">
							工作成果完成情况评价
						</th>
						<th class="data_title" style="text-align: center;" rowspan="1" colspan="5">
							公司规章制度执行评价
						</th>
					</tr>
					<tr>
						<th class="data_title" style="text-align: center;" >
							遵守公司财务制度要求
						</th>
						<th class="data_title" style="text-align: center;">
							卫生、安全、着装
						</th>
						<th class="data_title" style="text-align: center;" >
							遵守公司考勤管理制度要求
						</th>
						
						<th class="data_title" style="text-align: center;">
							遵守公司员工行为规范要求
						</th>
						<th class="data_title" style="text-align: center;">
							工作计划完成情况
						</th>
					</tr>
				  </thead>
			    </table>
			  </div>
			</nav>
			
			<div class= "table-responsive " style="padding-top: 2px;"> 
			<table  class="table table-striped" style="margin:70px -10px 0 -7px;">
				<tbody>
				<c:forEach var="emp" items="${emps}">
				   <c:if test="${emp.eName != user.eName }">
				      <tr>
						<td class="data_cell" style="text-align: center;">${emp.eName}</td>
						<td class="data_cell" style="text-align: center;"><input type="number" name="score1" min="0" max="100" style="width:60px;margin-left:-50px"></td>				
                        <td class="data_cell" style="text-align: center;"><input type="number" name="score2" min="0" max="100" style="width:60px;margin-left:0px"></td>
                        <td class="data_cell" style="text-align: center;"><input type="number" name="score3" min="0" max="100" style="width:60px;margin-left:0px"></td>
                        <td class="data_cell" style="text-align: center;"><input type="number" name="score4" min="0" max="100" style="width:60px;margin-left:0px"></td>
                        <td class="data_cell" style="text-align: center;"><input type="number" name="score5" min="0" max="100" style="width:60px;margin-left:0px"></td>
                        <td class="data_cell" style="text-align: center;"><input type="number" name="score6" min="0" max="100" style="width:60px;margin-left:0px"></td>
                        <td class="data_cell" style="text-align: center;"><input type="number" name="score7" min="0" max="100" style="width:60px;margin-left:0px"></td>
					</tr>
				   </c:if>
				</c:forEach>
				</tbody>
			</table>
			</div>
		</c:if>
		<c:if test="${empty emps}">
			没有任何数据
		</c:if>
  </form>
  </div>
</body>
</html> --%>
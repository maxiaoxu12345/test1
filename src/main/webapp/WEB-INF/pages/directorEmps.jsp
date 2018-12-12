<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>经理评分</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
   <div style="margin:0 auto; width: 90%; background-color: #F0F0F0;height: 70%;margin-top:30px">
	  <form id="userForm"  action=""   method="post"  enctype="multipart/form-data" >
	    <!-- 列表数据 -->
		<c:if test="${!empty emps}">
			<div class= "table-responsive " style="padding-top: 2px;"> 
			<table  class="table table-striped" style="margin:10px -10px 0 -7px;">
			    <thead>
			          <tr>
			            <th class="data_title" style="text-align: center;">
						      姓名
					    </th>
					    <th class="data_title" style="text-align: center;">
						      测评
					    </th>
			          
			          </tr>
			    </thead>
				<tbody>
				<c:forEach var="emp" items="${emps}">
				   <c:if test="${emp.eName != user.eName }">
				      <tr>
						<td class="data_cell" style="text-align: center;">${emp.eName}</td>
						
						   <td class="data_cell" style="text-align: center;"><input type="button" value="测评" onclick="window.location.href='${pageContext.request.contextPath}/empScore.action?empName=${emp.eName}'"></td>
						
										
					</tr>
				   </c:if>
				</c:forEach>
				</tbody>
			</table>
			</div>
			
	    </c:if>
	    <c:if test="${empty emps}">
			张先生您不用参与此项评分。
		</c:if>
	  
	  
	  </form>
  </div>
</body>
</html> 
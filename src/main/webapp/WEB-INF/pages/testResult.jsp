<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评测结果</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
   
   <table  class="table table-striped" id="t1"  style="margin: 20px -10px 0 -7px;">
			  <thead >
				<tr>
					<th class="data_title" style="text-align: center;">
						姓名
					</th>
					<th class="data_title" style="text-align: center;">
						部门
					</th>
					<th class="data_title" style="text-align: center;">
						业务指标得分
					</th>
					<th class="data_title" style="text-align: center;">
						管理指标得分
					</th>
					<th class="data_title" style="text-align: center;">
						关键事件得分
					</th>
					<th class="data_title" style="text-align: center;">
						其他员工评价得分
					</th>
					<th class="data_title" style="text-align: center;">
						其他经理评价得分
					</th>
					<th class="data_title" style="text-align: center;">
						最终得分(保留两位)
					</th>
					<th class="data_title" style="text-align: center;">
						等级
					</th>
				</tr>
			  </thead>
			  <tbody>
			       <c:forEach items="${emps}" var="emp">
			         
			         <tr>
                        <td class="data_cell" style="text-align: center;">${emp.eName}</td>
                         
                        <td class="data_cell" style="text-align: center;">${emp.depart.dName}</td>
                        <td class="data_cell" style="text-align: center;">${emp.bussinessScore}</td>
                        <td class="data_cell" style="text-align: center;">${emp.managersScore}</td>
                        <td class="data_cell" style="text-align: center;">${emp.keyScore}</td>
                        <td class="data_cell" style="text-align: center;">${emp.empsScore}</td>
                         <td class="data_cell" style="text-align: center;">${emp.otherScore}</td>
                          <td class="data_cell" style="text-align: center;">${emp.finalScore}</td>
                          <td class="data_cell" style="text-align: center;">
                          <c:if test="${emp.finalScore >=9 && emp.finalScore<=10}">A&nbsp;优秀</c:if>
                          <c:if test="${emp.finalScore >=7 && emp.finalScore<9}">B&nbsp;良好</c:if>
                          <c:if test="${emp.finalScore >=5 && emp.finalScore<7}">C&nbsp;合格</c:if>
                          <c:if test="${emp.finalScore >=1 && emp.finalScore<5}">D&nbsp;不合格</c:if>
                          	
                          </td>
                     </tr>
   
                     
                   </c:forEach>
			  </tbody>
			  
   </table>
   
  
</body>
</html>
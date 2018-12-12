<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工互评</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	  $(function(){
		  $("#selDepart").change(function(){
			  var val=$("#selDepart").val();
		<!--	alert(val); -->
			  window.location.href="${pageContext.request.contextPath}/getEmpsByDepartName.action?departName="+val;
		  });
		  $(".saveTest").click(function(){
		   var score= $(this).prev().val();
			  if(score!="" && score>=0 && score <=10){
				 var name=  $(this).parent().prev().prev().text();
				
				 var url="${pageContext.request.contextPath}/saveTest.action";
				 function callback(data){
						if(data=='1'){
							 alert("已保存"); 
							return false;
						}else{
							alert("保存失败");
							return false;
						}
					};
					var type = "json"; 
					var obj={"name":name,"score":score};
					$.post(url,obj,callback,type);
					
					$(this).attr("disabled",true);
					$(this).prev().attr("disabled",true);
					return false;
			  }
			  
		  });
		    
	  });
	
	</script>
</head>
<body>
 <div style="margin:0 auto; width: 90%; background-color: #F0F0F0;height: 70%">
  <form id="userForm"  action="${ctx}/search.action"   method="post"  enctype="multipart/form-data">
     <!-- 列表数据 -->
		
			<div class= "table-responsive " style="padding-top: 2px;">
			<div class="navbar-fixed-top">
			   <font size="4">&nbsp;&nbsp;&nbsp;&nbsp;其他部门:</font>
			   <select name="ou" id="selDepart" >
					    
					    <c:forEach items="${departs}" var="deps">
						<c:if test="${deps.dName == dep}">
						<option selected="selected" value="${deps.dName}" >
							 ${deps.dName} </c:if>
						<c:if test="${deps.dName != dep}">
					        <option  value="${deps.dName}" >
							 ${deps.dName}</c:if>
						    
						  
					    </c:forEach>
				  </select>
			</div>
			  
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
						评分<br><br>(1-4分不合格,5-6分合格,7-8分良好,9-10分优秀)
					</th>
				</tr>
			  </thead>
				<tbody>
				<c:forEach var="emp" items="${emps}"><!-- Employee集合 -->
				   <c:if test="${emp.e_name != user.eName }">
				      <tr>
						<td class="data_cell" style="text-align: center;">${emp.e_name}</td>
						<td class="data_cell" style="text-align: center;">${emp.d_name}</td>				
                        <td class="data_cell" style="text-align: center;">
							<select  id="selScore" >
					  <c:forEach begin="1" end="10" var="score">
					  <c:if test="${emp.score==score }">
					   <option value="${score}" selected="selected">
							 ${score}
						    </option>
					  </c:if>
					  <c:if test="${emp.score!=score }"></c:if>
					        <option value="${score}">
							 ${score}
						    </option>
					    </c:forEach>
				  </select>
							<input type="button" value="保存" class="saveTest"><font color="red">每一项必须点保存，数据才有效</font>
						</td>
					</tr>
				   </c:if>
				</c:forEach>
				</tbody>
			</table>
			</div>
		
  </form>
  </div>
</body>
</html>
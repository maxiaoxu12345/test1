<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
   $(function(){
	   var isSave = true;
	   $("#save").click(function(){
		   //alert("保存");
		   if(!isSave){
			   alert("请注意填写正确的评分");
			   return false;
		   }
   		/* var list=$(".checkInput");
   		for (var i = 0; i < list.length; i++) {
   			// 判断文本框是否为空
   			if (list[i].value < 0 || list[i].value > 100) {
   			alert("评分范围为 0-100");
   			return false;
   			 }
   			}  */
   			$("#saveScoreForm").submit();  //请求数据保存----》f
   	  });
	   
	   $("#update").click(function(){
		  /*  alert("更新"); */
		   if(!isSave){
			   alert("请注意填写正确的评分");
			   return false;
		   }
		   var url="${pageContext.request.contextPath}/updateEmpScore.action";
		   $("#saveScoreForm").attr("action",url);
		   $("#saveScoreForm").submit();  //请求数据保存----》f
		   
	   });
	   
	   $(".checkInput").blur(function(){
		  /*  alert("失去光标"); */
		   var val = $(this).val();
		  /*  alert(val); */
		   if( val < 0 || val >100){
			   alert("评分范围为 0-100");
			   isSave = false;
	   			return false;
		   }
		   if(val == ''){
			   $(this).val(0.0);
			
		   }
		   isSave = true;
		   return false;
	   });
	   
	   $("#checkContribution").blur(function(){
		   var val_c = $(this).val();
		   if(val_c % 10 != 0 || val_c < 0){
			   alert("该项请填10的正整数倍");
			  /*  alert("false"); */
			   isSave = false;
		   }
		   if(val_c == ''){
			   $(this).val(0.0);
			   
		   }
		   isSave = true;
		   return false;
	   });
	   $("#checkFault").blur(function(){
		   var val_f = $(this).val();
		   var val_F = - val_f;
		   //alert(val_F);
		   if(val_F % 10 != 0 || val_f > 0){
			   alert("该项请填10的负整数倍");
			/*    alert("false"); */
			   isSave = false;
		   }
		   if(val_f == ''){
			   $(this).val(0.0);
			
		   }
		   isSave = true;
		   return false;
	   });
   });

</script>
</head>
<body>
    <div class="button_bar" style="margin: 10px 0 10px 20px;">
			<button class="common_button" onclick="javascript:history.back(-1);">返回</button>
			<c:if test="${empScore.scoreAchievement == 0.0 && empScore.scoreFinish == 0.0 && empScore.scoreFinance == 0.0
			 && empScore.scoreHygiene == 0.0 && empScore.scoreBehavior == 0.0 && empScore.scorePlan == 0.0 
			  && empScore.scoreAttendance == 0.0 && empScore.scoreContribution == 0.0 &&  empScore.scoreFault == 0.0}">
			 <button class="common_button" id="save">保存</button>
			</c:if>
			<c:if test="${empScore.scoreAchievement != 0.0 || empScore.scoreFinish != 0.0 || empScore.scoreFinance != 0.0
			 || empScore.scoreHygiene != 0.0 || empScore.scoreBehavior != 0.0 || empScore.scorePlan != 0.0 
			  || empScore.scoreAttendance != 0.0 ||  empScore.scoreContribution != 0.0 ||  empScore.scoreFault != 0.0}">
			 <button class="common_button" id="update">更新</button>
			</c:if>
			
	</div>
    
	
   <form:form id="saveScoreForm" action="${pageContext.request.contextPath}/saveEmpScore.action" method="post"  modelAttribute="empScore">
   
     <table class="table table-bordered" style="width: 90%; margin:0 auto">
			<tr>
				<th class="input_title" style="width: 50%;">姓名</th>
				<td class="input_content">
					<form:input path="empName" readonly="true"/> 
				</td>
				
			</tr>
			<tr>
			   <th class="input_title" style="width: 50%;">年度主要工作成果</th>
				<td class="input_content">
					<form:input path="scoreAchievement" class="checkInput" onfocus="$(this).val('')"/><span>(请填0-100的数)</span>
				</td>
			</tr>
			<tr>
				<th class="input_title" style="width: 30%;">工作成果完成情况评价</th>
				<td class="input_content">
					<form:input path="scoreFinish" class="checkInput" onfocus="$(this).val('')"/><span>(请填0-100的数)</span>
				</td>
		   </tr>
		    <tr>
				<th class="input_title" style="width: 30%;">遵守公司财务制度要求，准确填写报账单；报销、还款及时；发票的取得和保管无缺失；发票申请单信息填写准确、完整</th>
				<td class="input_content">
					<form:input path="scoreFinance" class="checkInput" onfocus="$(this).val('')"/><span>(请填0-100的数)</span>
				</td>
		   </tr>
		    <tr>
				<th class="input_title" style="width: 30%;">遵守公司办公卫生要求和住宿卫生要求；遵守公司安全管理制度要求；遵守公司员工着装规范</th>
				<td class="input_content">
					<form:input path="scoreHygiene" class="checkInput" onfocus="$(this).val('')"/><span>(请填0-100的数)</span>
				</td>
			</tr>
		    <tr>
				<th class="input_title" style="width: 30%;">遵守公司考勤管理制度要求，全年加班、请假、迟到早退、打卡签到按要求执行</th>
				<td class="input_content">
					<form:input path="scoreAttendance" class="checkInput" onfocus="$(this).val('')"/><span>(请填0-100的数)</span>
				</td>
			</tr>
		    <tr>
				<th class="input_title" style="width: 30%;">遵守公司员工行为规范要求，无违反员工行为规范事项发生</th>
				<td class="input_content">
					<form:input path="scoreBehavior" class="checkInput" onfocus="$(this).val('')"/><span>(请填0-100的数)</span>
				</td>
			</tr>
		    <tr>	
				<th class="input_title" style="width: 30%;">员工工作计划按时提交并完成，相关文档能够及时提交</th>
				<td class="input_content">
					<form:input path="scorePlan" class="checkInput" onfocus="$(this).val('')"/><span>(请填0-100的数)</span>
				</td>
			</tr>
		    <tr>
				<th class="input_title" style="width: 30%;">本部门的组织贡献度：特别明显的管理创新、合理化建议、赢得组织及社会荣誉等优秀事件    (每项+10)</th>
				<td class="input_content">
					<form:input path="scoreContribution"   id="checkContribution" onfocus="$(this).val('')"/><span>(请填10正整数倍)</span>
				</td>
			</tr>
		    <tr>
				<th class="input_title" style="width: 30%;">本部门的组织损害度：严重客户投诉、故意或严重违规违纪等影响公司声誉及利益的负面事件    (每项-10)</th>
				<td class="input_content">
					<form:input path="scoreFault"  id="checkFault" onfocus="$(this).val('')"/><span>(请填10负整数倍)</span>
				</td>
			</tr>
		</table> 
        
   </form:form>
</body>
</html>
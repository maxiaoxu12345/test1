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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
   $(function(){
	   var isSaveA = true;
	   var isSaveB = true;
	   var isSaveC = true;
	   $("#save").click(function(){
		  
		   if(!(isSaveA && isSaveB && isSaveC)){
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
	  
	   $(".checkInput").blur(function(){
		
		   var vals = $(this).val();
		
		   if( vals!='1' && vals!='4' && vals!='3' && vals!='5' && vals!='6' && vals!='7' && vals!='8'
			   && vals!='9' && vals!='10' && vals!='2'){
			  
			   alert("请填写正确数值");
			   $(this).val('');
			   isSaveA = false;
	   			return false;
		   }else {
			   isSaveA = true; 
		   }
		 
		 
	   });
	   
	   $("#checkContribution").blur(function(){
		   var val_c = $(this).val();
		   if(val_c != 0 && val_c != 1){
			   alert("请填写0或1");
			   $(this).val('');
			  /*  alert("false"); */
			   isSaveB = false;
			   return false;
		   }else {
			   isSaveB = true; 
		   }
		  
		   
	   });
	   $("#checkFault").blur(function(){
		   var val_f = $(this).val();
		   
		   //alert(val_F);
		   if(val_f  != 0 && val_f != -1){
			   alert("请填写0或-1");
			   $(this).val('');
			/*    alert("false"); */
			   isSaveC = false; 
			return false;
			
		   }else {
			   isSaveC = true; 
		   }
		 
	   });
   });

</script>
</head>
<body>
    <div class="button_bar" style="margin: 10px 0 10px 20px;">
			<button class="common_button" onclick="javascript:history.back(-1);">返回</button>
			
			 <button class="common_button" id="save">保存</button>
			
		
			
	</div>
    
	
   <form:form id="saveScoreForm" action="${pageContext.request.contextPath}/saveEmpScore.action" method="post"  modelAttribute="empScore">
   
     <table class="table table-bordered" style="width: 90%; margin:0 auto">
			<tr>
				<th  style="width: 10%;">姓名</th>
				<th  style="width: 12%;"></th>
				<th  style="width: 58%;"></th>
				<td class="input_content" style="width: 20%;">
					<form:input path="empName" readonly="true"/> 
				</td>
				
			</tr>
			<tr><th rowspan="2"  style="width:10%;">业务指标&nbsp;权重60%</th>
			   <th  style="width: 20%;"><font size="3">年度主要工作成果</font>&nbsp;&nbsp;权重36%</th>
			   <th  style="width: 60%;"><font size="4" >工作成果的内容（附个人年度业绩提报）</font></th>
				<td class="input_content">
					<form:input path="scoreAchievement" class="checkInput" onfocus="this.value=''"/> <span>(请填0-10的整数)</span>
				</td>
			</tr>
			<tr>
				<th  style="width: 30%;"><font size="3">工作成果完成情况评价</font>&nbsp;&nbsp;权重24%</th>
				<th  style="width: 30%;"><font size="4" >工作成果完成的工作效率、工作质量</font></th>
				<td class="input_content">
					<form:input path="scoreFinish" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
		   </tr>
		    <tr><th rowspan="8"  style="width:10%;">管理指标&nbsp;权重40%</th>
				<th  style="width: 30%;"><font size="3">专业知识、工作方法、
工作经验</font>&nbsp;&nbsp;权重4%</br></th><th><font size="4" >专业知识丰富，工作方法得当，具有正确解决问题的形式、途径；对知识或技能善于积累，阅历丰富，善于与他人分享</font></th>
				<td class="input_content">
					<form:input path="scoreFinance" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
		   </tr>
		    <tr>
				<th  style="width: 30%;"><font size="3">协调沟通、抗压应变能力</font>&nbsp;&nbsp;权重4%</br></th><th><font size="4" >善于沟通与协调，方法得当；工作能够持之以恒，有耐心和毅力；面对突发事件能够正确处理，临危不乱</font></th>
				<td class="input_content">
					<form:input path="scoreHygiene" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
			</tr>
		    <tr>
				<th  style="width: 30%;"><font size="3">主动性、责任感</font>&nbsp;&nbsp;权重6%</br></th><th><font size="4" >主动开展工作且具有计划性，工作具有责任心、勇于承担、具有成本节约意识</font></th>
				<td class="input_content">
					<form:input path="scoreAttendance" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
			</tr>
		    <tr>
				<th  style="width: 30%;"><font size="3">协作性、纪律性</font>&nbsp;&nbsp;权重4%</br></th><th><font size="4" >主动与他人协作、沟通、配合，方法得当；具有自我管理约束性，认真执行各项规章</font></th>
				<td class="input_content">
					<form:input path="scoreBehavior" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
			</tr>
		    <tr>	
				<th  style="width: 30%;"><font size="3">学习成长</font>&nbsp;&nbsp;权重4%</br></th><th><font size="4" >主动学习专业知识技能，积极参加专项资格学习考试，积极探索新知识，学以致用</font></th>
				<td class="input_content">
					<form:input path="scorePlan" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
			</tr>
			 <tr>	
				<th rowspan="3"  style="width: 30%;"><font size="3">规章制度执行</font>&nbsp;&nbsp;权重18%</br></th>
				<th><font size="4" >遵守财务制度要求，报销、还款、发票开具及时准确；发票的取得和保管无缺失；</font></th>
				<td class="input_content">
					<form:input path="scoreContribution" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td></tr>
				<tr>
				<th><font size="4" >遵守公司考勤制度，请假、加班、签到按要求执行；遵守公司安全管理制度；遵守员工行为规范、环境卫生、着装等按要求执行</font></th>
				
				<td class="input_content">
					<form:input path="scoreFault" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
			</tr>
			<tr><th><font size="4" >员工工作计划按时提交并完成，相关文档能够及时提交</font></th>
				
				<td class="input_content">
					<form:input path="scoreOntime" class="checkInput" onfocus="this.value=''"/><span>(请填0-10的整数)</span>
				</td>
			</tr>
			<c:if test="${userId eq '经理'}">
		    <tr><th rowspan="2"  style="width:10%;">关键事件</br>附加分<br>土1分</th>
		    <th  style="width: 30%;"><font size="3">突出贡献</font></th>
				<th  style="width: 30%;"><font size="4">本部门的组织贡献度：特别明显的管理创新、合理化建议、赢得组织及社会荣誉等优秀事件    (+1)</font></th>
				<td class="input_content">
					<form:input path="scoreAdd"   id="checkContribution" onfocus="this.value=''"/><span>(请填1或者0)</span>
				</td>
			</tr>
		    <tr><th  style="width: 30%;"><font size="3">重大过失</font></th>
				<th  style="width: 30%;"><font size="4">本部门的组织损害度：严重客户投诉、故意或严重违规违纪等影响公司声誉及利益的负面事件    (-1)</font></th>
				<td class="input_content">
					<form:input path="scoreSub"  id="checkFault" onfocus="this.value=''"/><span>(请填-1或者0)</span>
				</td>
			</tr></c:if>
			
		</table> 
        
   </form:form>
</body>
</html>
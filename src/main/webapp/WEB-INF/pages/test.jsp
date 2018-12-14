<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style type="text/css">
        th{
           vertical-align: middle !important;
           text-align: center !important;
        }
         td{
           vertical-align: middle !important;
           text-align: left !important;
        }
    </style>
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
		   if(val_c <0){
			   alert("请填写0或正数");
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
		   if(val_f  >0){
			   alert("请填写0或负数");
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
   
    
	
   <form:form id="saveScoreForm" action="${pageContext.request.contextPath}/saveEmpScore.action" method="post"  modelAttribute="empScore">
   		 <div class="button_bar" style="margin: 10px 0 10px 20px; max-width: 95.5%">
			<button class="common_button" onclick="javascript:history.back(-1);">返回</button>
			
			 <button class="common_button" id="save">保存</button><strong>评分标准(1-4分不合格,5-6分合格,7-8分良好,9-10分优秀)</strong>
			
		
			<font style="float: right;">姓名:&nbsp;<form:input path="empName" readonly="true"/> </font>
	</div>
     <table class="table table-bordered" style="width: 95%; margin:0 auto">
			
			<tr>
				<th width="10%">指标类</th>
				<th width="10%">指标内容</th>
				<th width="50%">指标描述</th>
				
				<th width="30%">评分</th>
			</tr>
			<tr><th  rowspan="2"  style="width:10%;">业务指标</th>
			   <th>年度主要工作成果</th>
			   <td><strong>工作成果的内容（附个人年度业绩提报）</strong></td>
				<td class="input_content">
					<form:input path="scoreAchievement" class="checkInput" onfocus="this.value=''"/> <br><span>(请填1-10的整数)</span>
				</td>
			</tr>
			<tr>
				<th>工作成果完成情况评价</th>
				
				 <td><strong>工作成果完成的工作效率、工作质量</strong></td>
				<td class="input_content">
					<form:input path="scoreFinish" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
		   </tr>
		    <tr><th  rowspan="8"  style="width:10%;">管理指标</th>
				<th>专业知识、工作方法、
工作经验</br></th>
 <td><strong>专业知识丰富，工作方法得当，具有正确解决问题的形式、途径；对知识或技能善于积累，阅历丰富，善于与他人分享</strong></td>
				<td class="input_content">
					<form:input path="scoreFinance" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
		   </tr>
		    <tr>
				<th>协调沟通、抗压应变能力</br></th>
				<td><strong>善于沟通与协调，方法得当；工作能够持之以恒，有耐心和毅力；面对突发事件能够正确处理，临危不乱</strong></td>
				<td class="input_content">
					<form:input path="scoreHygiene" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
			</tr>
		    <tr>
				<th>主动性、责任感</br></th>
				<td><strong>主动开展工作且具有计划性，工作具有责任心、勇于承担、具有成本节约意识</strong></td>
				<td class="input_content">
					<form:input path="scoreAttendance" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
			</tr>
		    <tr>
				<th  style="width: 20%;">协作性、纪律性</br></th>
				<td><strong>主动与他人协作、沟通、配合，方法得当；具有自我管理约束性，认真执行各项规章</strong></td>
				<td class="input_content">
					<form:input path="scoreBehavior" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
			</tr>
		    <tr>	
				<th  style="width: 20%;">学习成长</br></th>
				<td><strong>主动学习专业知识技能，积极参加专项资格学习考试，积极探索新知识，学以致用</strong></td>
				<td class="input_content">
					<form:input path="scorePlan" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
			</tr>
			 <tr>	
				<th rowspan="3"  style="width: 20%;">规章制度执行</br></th>
				<td><strong>遵守财务制度要求，报销、还款、发票开具及时准确；发票的取得和保管无缺失；</strong></td>
				<td class="input_content">
					<form:input path="scoreContribution" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td></tr>
				<tr>
				<td><strong>遵守公司考勤制度，请假、加班、签到按要求执行；遵守公司安全管理制度；遵守员工行为规范、环境卫生、着装等按要求执行</strong></td>
				<td class="input_content">
					<form:input path="scoreFault" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
			</tr>
			<tr>
				<td><strong>员工工作计划按时提交并完成，相关文档能够及时提交</strong></td>
				<td class="input_content">
					<form:input path="scoreOntime" class="checkInput" onfocus="this.value=''"/><br><span>(请填1-10的整数)</span>
				</td>
			</tr>
			<c:if test="${userId eq '经理'}">
		    <tr><th rowspan="2"  style="vertical-align: middle !important;text-align: center;width:10%;">关键事件</th>
		    <th  style="width: 20%;">突出贡献</th>
			<!-- <th  style="width: 30%;">本部门的组织贡献度：特别明显的管理创新、合理化建议、赢得组织及社会荣誉等优秀事件    <br>(每增加一项加1分)</th> -->
				<td><strong>本部门的组织贡献度：特别明显的管理创新、合理化建议、赢得组织及社会荣誉等优秀事件    <br>(每增加一项加1分)</strong></td>
				<td class="input_content">
					<form:input path="scoreAdd"   id="checkContribution" onfocus="this.value=''"/><br><span>(请填0或正数)</span>
				</td>
			</tr>
		    <tr><th  style="width: 20%;">重大过失</th>
				<td  style="width: 30%;"><strong>本部门的组织损害度：严重客户投诉、故意或严重违规违纪等影响公司声誉及利益的负面事件     <br>(每增加一项减1分)</strong></td>
				<td class="input_content">
					<form:input path="scoreSub"  id="checkFault" onfocus="this.value=''"/><br><span>(请填0或负数)</span>
				</td>
			</tr></c:if>
			
		</table> 
        
   </form:form>
</body>
</html>
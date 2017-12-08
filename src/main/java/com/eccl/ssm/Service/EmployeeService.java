/**
 * 
 */
package com.eccl.ssm.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eccl.ssm.Entity.Depart;
import com.eccl.ssm.Entity.EmpScore;
import com.eccl.ssm.Entity.Employee;
import com.eccl.ssm.Entity.Role;
import com.eccl.ssm.Mapper.EmployeeMapper;

/**
 * 员工测评Service
 *
 * @author wzc
 * @date 2017年12月4日 下午7:26:53
 *
 */
@Service
public class EmployeeService {
	@Autowired(required=true)
	private EmployeeMapper eMapper;
	//各项指标权重
    private final double businessRatio_one=0.6;  //业务指标一级权重
    private final double businessRatio_two=0.5;  //业务指标二级权重
    private final double manageRatio_one = 0.4;  //管理指标一级权重
    private final double rulesRatio_two = 0.1;   //公司规章制度 二级权重
    private final double testRatio_two = 0.5;    //人员互评   --- 二级权重
    private final double test_directorRatio = 0.6; //经理评测-- 三级权重
    private final double test_otherRirRatio = 0.3; // 其他经理评测 ---三级权重
    private final double test_empsRatio = 0.1;;    //员工互评     -----三级权重
	
	public List<Employee> getEmps(){
		 List<Employee> emps = eMapper.getEmps();
		 for (Employee employee : emps) {
			 System.out.println("开始");
			System.out.println("i"+employee);
		}
		 return emps;
				  
	}


	/**
	 * @param departName
	 * @return
	 */
	@Transactional
	public List<Employee> getEmpsByDepart(String departName) {
		List<Employee> lists= eMapper.getEmpsByDepart(departName);
		return lists;
	}


	/**
	 * @param empIn
	 * @return
	 */
	@Transactional
	public Employee Login(Map<String, String> empIn) {
		Employee employee=eMapper.goLogin(empIn.get("empName"));
		/*System.out.println("登录查询出来的人："+employee);*/
		if (employee!=null && employee.getPwd().equals(empIn.get("pwd"))) {
			return employee;
		}
		return null;
	}


	/**
	 * @return
	 */
	@Transactional
	public List<Depart> getAllDepart() {
		
		return eMapper.getAllDepart();
	}


	/**
	 * 保存评分
	 * @param map
	 * @return
	 */
	@Transactional
	public void saveScore(Map<String, Object> map) {
		eMapper.saveScore(map);
		
	}


	/**
	 * 判断对某个同事是否已经评测过
	 * @param map
	 * @return false 还没有评测记录 ， true 已评测完
	 */
	@Transactional
	public boolean isRecored(Map<String, Object> map) {
		int  record = eMapper.isRecored(map);
		if (record == 0) {
			return false;
		} 
		return true;
	}
	
	


	/**
	 * 更新评测
	 * @param map
	 */
	@Transactional
	public void updateScore(Map<String, Object> map) {
		eMapper.updateScore(map);
		
	}


	/**
	 * 获取经理下属员工
	 * @param getdId
	 * @return
	 */
	@Transactional
	public List<Employee> getOwnEmps(int getdId) {
		
		return eMapper.getOwnEmps(getdId);
	}


	/**
	 * 获取所评测的员工具体分数
	 * @param empName
	 * @return
	 */
	@Transactional
	public EmpScore getEmpScore(String empName) {
		System.out.println("dsf"+empName);
		return eMapper.getEmpScore(empName);
	}


	/**
	 * 保存经理对员工的评分记录
	 * @param empScore
	 */
	@Transactional
	public void saveScoreRecord(EmpScore empScore) {
		
		//计算出员工表中的other_score
		double scoreAchievement = empScore.getScoreAchievement();
		double scoreFinish = empScore.getScoreFinish();
		double scoreFinance = empScore.getScoreFinance();
		double scoreHygiene = empScore.getScoreHygiene();
		double scoreAttendance = empScore.getScoreAttendance();
		double scoreBehavior = empScore.getScoreBehavior();
		double scorePlan = empScore.getScorePlan();
		double scoreFault = empScore.getScoreFault();
		double scoreContribution = empScore.getScoreContribution();
		
		
		
		
		double businessScore = (scoreAchievement+scoreFinish)*businessRatio_two*businessRatio_one  ; //业务指标
		double rulesScore = (scoreFinance+scoreHygiene+scoreAttendance+scoreBehavior+scorePlan)* rulesRatio_two * manageRatio_one;   //公司规章制度评价
		double event_score = scoreContribution + scoreFault;   //贡献与过失
		
		
		
		double otherScore = businessScore + rulesScore + event_score;
		System.out.println("otherScore:"+otherScore); 
		
		
		Map<String, Object> scoresMap = new HashMap<>();
		scoresMap.put("businessScore",businessScore);
		scoresMap.put("rulesScore",rulesScore);
		scoresMap.put("eventScore",event_score);
		scoresMap.put("otherScore",otherScore);
		scoresMap.put("name", empScore.getEmpName());
		
		if(otherScore != 0.0){
			//将otherScore存到数据库
			eMapper.saveOtherScore(scoresMap);
			//将数据存到评分表中
			int countNum = eMapper.getIsTest(empScore);
			if (countNum == 0) {
				//还没有评测过就 新建 insert
				eMapper.saveScoreRecord(empScore);
			} else {
				//已经评测过 将数据更新评分表中
				eMapper.updateScoreRecord(empScore);
			}
			
		}
		
	}


	/**
	 * 
	 * 更新某员工评测
	 * @param empScore
	 */
	@Transactional
	public void updateScoreRecord(EmpScore empScore) {
		       //计算出员工表中的other_score
				double scoreAchievement = empScore.getScoreAchievement();
				double scoreFinish = empScore.getScoreFinish();
				double scoreFinance = empScore.getScoreFinance();
				double scoreHygiene = empScore.getScoreHygiene();
				double scoreAttendance = empScore.getScoreAttendance();
				double scoreBehavior = empScore.getScoreBehavior();
				double scorePlan = empScore.getScorePlan();
				double scoreFault = empScore.getScoreFault();
				double scoreContribution = empScore.getScoreContribution();
				
				
				
				double businessScore = (scoreAchievement+scoreFinish)*businessRatio_two*businessRatio_one  ; //业务指标
				double rulesScore = (scoreFinance+scoreHygiene+scoreAttendance+scoreBehavior+scorePlan)* rulesRatio_two * manageRatio_one;   //公司规章制度评价
				double event_score = scoreContribution + scoreFault;   //贡献与过失
				
				double otherScore = businessScore + rulesScore + event_score;
				System.out.println("otherScore:"+otherScore); 
				
				
				Map<String, Object> scoresMap = new HashMap<>();
				scoresMap.put("businessScore",businessScore);
				scoresMap.put("rulesScore",rulesScore);
				scoresMap.put("eventScore",event_score);
				scoresMap.put("otherScore",otherScore);
				scoresMap.put("name", empScore.getEmpName());
				
				if(otherScore != 0.0){
					//将otherScore存到数据库
					eMapper.saveOtherScore(scoresMap);
					//将数据存到评分表中
					eMapper.updateScoreRecord(empScore);
				}
		
	}


	/**
	 * 获取测评结果
	 * @return
	 */
	@Transactional
	public List<Employee> getEmpsTestResult() {
		//1.查出所有员工
		List<Employee> emps = eMapper.getEmps();
		for (Employee employee : emps) {
			//System.out.println(employee.geteName());
			//2.根据员工名计算出各个员工的经理评分
			eMapper.calculateDirectorScore(employee.geteName());
			
			//3.获取其他打分经理人数
			int otherDirectorScoreCount = eMapper.getOtherDirectorScoreCount(employee.geteName());
			//System.out.println("获取其他打分经理人数"+otherDirectorScoreCount);
			//4.计算其他经理的评分  (平均值)
			if (otherDirectorScoreCount > 0) {
				eMapper.calculateOtherDirectorScore(otherDirectorScoreCount,employee.geteName());
			}
			//5.查询员工互评个数
			int eTestTotal = eMapper.selectETestTotal(employee.geteName());
			int failCount = 0;  //舍弃 最高分/最低分   个数
			//6.计算员工互评得分
			if (0 < eTestTotal && eTestTotal <= 5) {
				/*System.out.println("<5");*/
				eMapper.calculateEmpsScoore(eTestTotal , employee.geteName() );  //计算5人(含5人)评分的平均值
			} else if( eTestTotal > 5) {
				/*System.out.println(">5");*/
                  if (eTestTotal%10 == 0) {
                	  failCount = eTestTotal/10;
				  }else {
					  failCount = eTestTotal/10 +1 ;
				  }
                 /* System.out.println("舍弃个数："+ failCount);*/
                  eMapper.calculateEmpsMorescore(failCount, eTestTotal-2*failCount , employee.geteName());  //计算大于5人（去掉最高、最低分） 评分的平均值
			}else {
				/*System.out.println("没有其他员工给他评分");*/
				continue;
			}
			//计算出tean_score 得分
			Employee empTableScore =  eMapper.getEmpTableScore(employee.geteName());
			
			double teamScore = (empTableScore.getDirectorScore()*test_directorRatio 
					+ empTableScore.getManagersScore()*test_otherRirRatio 
					+ empTableScore.getEmpsScore()*test_empsRatio)*testRatio_two*manageRatio_one;
			eMapper.calculateTeamScore(teamScore,employee.geteName());   //存储team_score
			
			//合计管理指标
			eMapper.calculateManage(teamScore+empTableScore.getRulesScore(),employee.geteName());
			
			//合计最终得分
			eMapper.calculateTotalScore(employee.geteName());
			
		}
		
		List<Employee> emps2 = eMapper.getTestResult();
		/*for (Employee employee : emps2) {
			System.out.println("测评结果："+ employee);
		}*/
		
		
		return emps2;
	}


	/**
	 * 获取角色列表
	 * @return
	 */
	public List<Role> getAllRole() {
		
		return eMapper.getAllRole();
	}


	

}

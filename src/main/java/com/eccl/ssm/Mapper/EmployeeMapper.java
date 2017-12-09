/**
 * 
 */
package com.eccl.ssm.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eccl.ssm.Entity.Depart;
import com.eccl.ssm.Entity.EmpScore;
import com.eccl.ssm.Entity.Employee;
import com.eccl.ssm.Entity.Role;

/**
 * 员工测评Mapper
 *
 * @author wzc
 * @date 2017年12月4日 下午7:25:06
 *
 */
public interface EmployeeMapper {
	
	//获取所有员工
	public List<Employee> getEmps();

	/**
	 * 按部门来获取员工表
	 * @param departId
	 * @return
	 */
	public List<Employee> getEmpsByDepart(int departId);

	/**
	 * 根据登录名查询数据库
	 * @param string
	 * @return
	 */
	public List<Employee> goLogin(Map<String, String> empIn);

	/**
	 * @return
	 */
	public List<Depart> getAllDepart();

	/**
	 * @param map
	 */
	public void saveScore(Map<String, Object> map);

	/**
	 * 判断是否存在一条已经评测过得记录
	 * @param map
	 * @return
	 */
	public int isRecored(Map<String, Object> map);

	/**
	 * 更新评测
	 * @param map
	 */
	public void updateScore(Map<String, Object> map);

	/**
	 * 获取经理下属员工
	 * @param getdId
	 * @return
	 */
	public List<Employee> getOwnEmps(int getdId);

	/**
	 * 获取员工具体评分
	 * @param empName
	 * @return
	 */
	public EmpScore getEmpScore(String empName);

	/**
	 * 保存经理对员工的评分记录
	 * @param empScore
	 */
	public void saveScoreRecord(EmpScore empScore);

	/**
	 * 将计算出来的otherScore存到数据库
	 * @param scoresMap
	 */
	public void saveOtherScore(Map<String, Object> scoresMap);

	/**
	 * 更新某员工评测
	 * @param empScore
	 */
	public void updateScoreRecord(EmpScore empScore);

	/**
	 * 计算员工的经理评分
	 * @param geteName
	 */
	public void calculateDirectorScore(String geteName);

	/**
	 * 获取角色列表
	 * @return
	 */
	public List<Role> getAllRole();

	/**
	 * 计算其他经理给该员工的评分
	 * @param geteName
	 */
	public void calculateOtherDirectorScore(String geteName);

	/**
	 * 计算其他经理打分人数
	 * @param geteName
	 * @return
	 */
	public int getOtherDirectorScoreCount(String geteName);

	/**
	 * @param otherDirectorScoreCount
	 * @param geteName
	 */
	public void calculateOtherDirectorScore(int otherDirectorScoreCount, String geteName);

	/**
	 * 查询出给该员工评分的其他员工个数
	 * @param geteName
	 * @return
	 */
	public int selectETestTotal(String geteName);

	/**
	 * 计算5人以下员工互评
	 * @param eTestTotal
	 * @param geteName
	 */
	public void calculateEmpsScoore(int eTestTotal, String geteName);


	

	/**
	 * 计算大于5人（去掉最高、最低分） 评分的平均值
	 * @param failCount   分别去掉最高分和最低分的个数
	 * @param i           去掉部分最高分和部分最低分后 给该员工的总的评分人数
	 * @param geteName    该员工的姓名
	 */
	public void calculateEmpsMorescore(int failCount, int i, String geteName);

	/**
	 * 合计最终得分
	 * @param geteName
	 */
	public void calculateTotalScore(String geteName);

	/**
	 * 获取最终评分结果
	 * @return
	 */
	public List<Employee> getTestResult();

	/**
	 * 获取员工表中的得分情况
	 * @param geteName
	 * @return
	 */
	public Employee getEmpTableScore(String geteName);

	/**
	 * 将team_score 存到数据库
	 * @param teamScore
	 * @param geteName
	 */
	public void calculateTeamScore(double teamScore, String geteName);



	/**
	 * 合计出管理指标
	 * @param d
	 * @param geteName
	 */
	public void calculateManage(double d, String geteName);

	/**
	 * 判断是否已经评测过
	 * @param empScore
	 * @return
	 */
	public int getIsTest(@Param("empScore")EmpScore empScore);

	/**
	 * 通过id获取部门
	 * @param departId
	 * @return
	 */
	public Depart getDepart(int departId);

	/**
	 * 根据id获取员工对象
	 * @param testId
	 * @return
	 */
	public Employee getEmpById(int testId);




	

	
     
	

}

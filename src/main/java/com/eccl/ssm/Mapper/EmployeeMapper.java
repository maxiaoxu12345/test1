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
import com.eccl.ssm.Entity.NewEmp;

/**
 * 员工测评Mapper
 *
 * @author wzc
 * @date 2017年12月4日 下午7:25:06
 *
 */
public interface EmployeeMapper {

	// 获取所有员工
	public List<Employee> getEmps();

	/**
	 * 建表用 A
	 * 
	 * @param map
	 * 
	 * @param
	 * @return
	 */
	public List<NewEmp> getMap(Map<String, Object> map);

	/**
	 * 按部门来获取员工表 A
	 * 
	 * @param departName
	 * @return
	 */
	public List<NewEmp> getEmpsByDepart(String eName);

	/**
	 * A
	 * 
	 * @param string
	 * @return
	 */
	public List<Employee> goLogin(String empName);

	// public Employee goLogin(String empName);
	/**
	 * A
	 * 
	 * @param i
	 * @return
	 */
	public List<Depart> getAllDepart(int i);

	/**
	 * A
	 * 
	 * @param map
	 */
	public void saveScore(Map<String, Object> map);

	/**
	 * 判断是否存在一条已经评测过得记录 A
	 * 
	 * @param map
	 * @return
	 */
	public int isRecored(Map<String, Object> map);

	/**
	 * 更新评测 A
	 * 
	 * @param map
	 */
	public void updateScore(Map<String, Object> map);

	/**
	 * 获取经理下属员工 A
	 * 
	 * @param getdId
	 * @return
	 */
	public List<Employee> getOwnEmps(int getdId);

	/**
	 * 获取员工具体评分 A
	 * 
	 * @param map
	 * @return
	 */
	public EmpScore getEmpScore(Map<String, Object> map);

	/**
	 * A 修改密码
	 * 
	 * @param umap
	 */
	public void editpwd(@Param("umap") Map<String, String> umap);

	/**
	 * A
	 * 
	 * @param departName
	 * @return
	 */
	public List<NewEmp> getEmpsByDepartName(String departName);

	/**
	 * A
	 * 
	 * @param name
	 * @return
	 */
	public Integer getDepartByName(String name);

	/**
	 * A
	 * 
	 * @param geteJobType
	 * @return
	 */
	public Integer getDepartByJobType(String geteJobType);

	/**
	 * 建表 A
	 * 
	 * @param map
	 */
	public void InsertA(Map<String, Object> map);

	/**
	 * A
	 * 
	 * @param map
	 * @return
	 */
	public List<Depart> getAllDepartExcept(Map<String, Object> map);

	/**
	 * A
	 * 
	 * @param empScore
	 */
	public void saveDirectorTestInEmp(EmpScore empScore);

	/**
	 * A
	 * 
	 * @param name
	 * 
	 * @param num2
	 * @param string
	 */
	public void updateEmpsScoreInEmps(Map<String, Object> map);

	/**
	 * A
	 * 
	 * @param map
	 */
	public void updateOtherScoreInEmps(Map<String, Object> map);

	/**
	 * A
	 * 
	 * @return
	 */
	public List<Employee> getEmpsTestResult();

	/**
	 * @param getdId
	 * @return
	 */
	public List<Depart> getAllDepartExc(int getdId);

	/**
	 * @param getdId
	 * @return
	 */
	public List<NewEmp> getEmpsByDepartExc(int getdId);

	/**
	 * @param map
	 * @return
	 */
	public int iskey(Map<String, Object> map);

	/**
	 * 不存在插入
	 * 
	 * @param empScore
	 */
	public void insertScoreRecord(EmpScore empScore);

	/**
	 * 保存经理对员工的评分记录 A
	 * 
	 * @param empScore
	 */
	public void saveScoreRecord(EmpScore empScore);

	/**
	 * @return
	 */
	public List<String> selectAllEmpName();

	/**
	 * @param string
	 */
	public void updateEmps(String string);

	/**
	 * @param string
	 * @return
	 */
	public int selectcount(String string);

	/**
	 * @param string
	 * @return
	 */
	public List<Integer> selectScores(String string);

	/**
	 * @param string
	 * @return
	 */
	public List<Integer> selectOtherScores(String string);

	/**
	 * @return
	 */
	public List<String> selectAllEs();

	/**
	 * @param map
	 * @return
	 */
	public Integer selectOneScore(Map<String, Object> map);

	/**
	 * @param geteName
	 * @return
	 */
	public List<NewEmp> getEmpsByDepartExcep(String geteName);

	/**
	 * @param map
	 * @return
	 */
	public Integer selectOneScore2(Map<String, Object> map);

}

/**
 * 
 */
package com.eccl.ssm.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eccl.ssm.Entity.Depart;
import com.eccl.ssm.Entity.EmpScore;
import com.eccl.ssm.Entity.Employee;
import com.eccl.ssm.Entity.NewEmp;
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
	@Autowired(required = true)
	private EmployeeMapper eMapper;

	/**
	 * 通过部门id获取部门员工 A
	 * 
	 * @param eName
	 * @return
	 */
	@Transactional
	public List<NewEmp> getEmpsByDepart(String eName) {
		List<NewEmp> lists = eMapper.getEmpsByDepart(eName);
		return lists;
	}

	/**
	 * 登录 A
	 * 
	 * @param empIn
	 * @return
	 */
	@Transactional
	public List<Employee> Login(Map<String, String> empIn) {
		List<Employee> employee = eMapper.goLogin(empIn.get("empName"));
		return employee;
	}

	/**
	 * A
	 * 
	 * @param i
	 * @return
	 */
	@Transactional
	public List<Depart> getAllDepart(int i) {

		return eMapper.getAllDepart(i);
	}

	/**
	 * A 保存评分
	 * 
	 * @param map
	 * @return
	 */
	@Transactional
	public void saveScore(Map<String, Object> map) {
		eMapper.saveScore(map);

	}

	/**
	 * A 判断对某个同事是否已经评测过
	 * 
	 * @param map
	 * @return false 还没有评测记录 ， true 已评测完
	 */
	@Transactional
	public boolean isRecored(Map<String, Object> map) {
		int record = eMapper.isRecored(map);
		if (record == 0) {
			return false;
		}
		return true;
	}

	/**
	 * A 更新评测
	 * 
	 * @param map
	 */
	@Transactional
	public void updateScore(Map<String, Object> map) {
		eMapper.updateScore(map);

	}

	/**
	 * A 获取经理下属员工
	 * 
	 * @param getdId
	 * @return
	 */
	@Transactional
	public List<Employee> getOwnEmps(int getdId) {
		return eMapper.getOwnEmps(getdId);
	}

	/**
	 * 建表 A
	 * 
	 * @param empName
	 * @return
	 */
	@Transactional
	public void InsertA(Map<String, Object> map) {
		List<NewEmp> list = eMapper.getMap(map);
		for (NewEmp string : list) {
			map.put("d", string.getE_name());
			eMapper.InsertA(map);
		}
	}

	/**
	 * 获取所评测的员工具体分数 A
	 * 
	 * @param map
	 * @return
	 */
	@Transactional
	public EmpScore getEmpScore(Map<String, Object> map) {
		return eMapper.getEmpScore(map);
	}

	/**
	 * 保存经理对员工的评分记录 A
	 * 
	 * @param empScore
	 */
	@Transactional
	public void saveScoreRecord(EmpScore empScore) {
		eMapper.saveScoreRecord(empScore);
	}

	/**
	 * 获取测评结果 A
	 * 
	 * @return
	 */
	@Transactional
	public List<Employee> getEmpsTestResult() {
		return eMapper.getEmpsTestResult();

	}

	/**
	 * A
	 * 
	 * @param umap
	 */
	@Transactional
	public void editpwd(Map<String, String> umap) {
		eMapper.editpwd(umap);
	}

	/**
	 * 通过Id获取当前部门 A
	 * 
	 * @param departId
	 *            当前部门id
	 * @return
	 */
	@Transactional
	public List<NewEmp> getEmpsByDepartName(String departName) {
		// TODO Auto-generated method stub
		return eMapper.getEmpsByDepartName(departName);
	}

	/**
	 * A
	 * 
	 * @param name
	 * @return
	 */
	@Transactional
	public Integer getDepartByName(String name) {
		// TODO Auto-generated method stub
		return eMapper.getDepartByName(name);
	}

	/**
	 * A
	 * 
	 * @param geteJobType
	 * @return
	 */
	@Transactional
	public Integer getDepartByJobType(String geteJobType) {
		// TODO Auto-generated method stub
		return eMapper.getDepartByJobType(geteJobType);
	}

	/**
	 * A
	 * 
	 * @param map
	 * @return
	 */
	@Transactional
	public List<Depart> getAllDepartExcept(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eMapper.getAllDepartExcept(map);
	}

	/**
	 * A
	 * 
	 * @param empScore
	 */
	@Transactional
	public void saveDirectorTestInEmp(EmpScore empScore) {
		eMapper.saveDirectorTestInEmp(empScore);

	}

	/**
	 * A
	 * 
	 * @param name
	 * 
	 * @param num2
	 * @param string
	 */
	@Transactional
	public void updateEmpsScoreInEmps(Map<String, Object> map) {
		eMapper.updateEmpsScoreInEmps(map);

	}

	/**
	 * A
	 * 
	 * @param map
	 */
	@Transactional
	public void updateOtherScoreInEmps(Map<String, Object> map) {
		eMapper.updateOtherScoreInEmps(map);

	}

	/**
	 * @param getdId
	 * @return
	 */
	@Transactional
	public List<Depart> getAllDepartExc(int getdId) {
		// TODO Auto-generated method stub
		return eMapper.getAllDepartExc(getdId);

	}

	/**
	 * @param getdId
	 * @return
	 */
	@Transactional
	public List<NewEmp> getEmpsByDepartExc(int getdId) {
		// TODO Auto-generated method stub
		return eMapper.getEmpsByDepartExc(getdId);

	}

	/**
	 * @param map
	 * @return
	 */
	@Transactional
	public boolean iskey(Map<String, Object> map) {
		if (1 == eMapper.iskey(map)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @param empScore
	 */
	@Transactional
	public void insertScoreRecord(EmpScore empScore) {
		eMapper.insertScoreRecord(empScore);

	}

	/**
	 * @return
	 * 
	 */
	public List<String> selectAllEmpName() {
		return eMapper.selectAllEmpName();
		// TODO Auto-generated method stub

	}

	/**
	 * @param string
	 */
	public void updateEmps(String string) {
		eMapper.updateEmps(string);

	}

	/**
	 * @param string
	 * @return
	 */
	public int selectcount(String string) {
		// TODO Auto-generated method stub
		return eMapper.selectcount(string);
	}

	/**
	 * @param string
	 * @return
	 */
	public List<Integer> selectScores(String string) {
		// TODO Auto-generated method stub
		return eMapper.selectScores(string);
	}

	/**
	 * @param string
	 * @return
	 */
	public List<Integer> selectOtherScores(String string) {
		// TODO Auto-generated method stub
		return eMapper.selectOtherScores(string);
	}

	/**
	 * @return
	 */
	public List<String> selectAllEs() {
		// TODO Auto-generated method stub
		return eMapper.selectAllEs();
	}

	/**
	 * @param newEmp
	 * @return
	 */
	public Integer selectOneScore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eMapper.selectOneScore(map);
	}

	/**
	 * @param geteName
	 * @return
	 */
	public List<NewEmp> getEmpsByDepartExcep(String geteName) {
		// TODO Auto-generated method stub
		return eMapper.getEmpsByDepartExcep(geteName);
	}

	/**
	 * @param map
	 * @return
	 */
	public Integer selectOneScore2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return eMapper.selectOneScore2(map);
	}
}

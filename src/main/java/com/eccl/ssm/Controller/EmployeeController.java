/**
 * 
 */
package com.eccl.ssm.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eccl.ssm.Entity.Depart;
import com.eccl.ssm.Entity.EmpScore;
import com.eccl.ssm.Entity.Employee;
import com.eccl.ssm.Entity.NewEmp;
import com.eccl.ssm.Service.EmployeeService;
import com.eccl.ssm.excelutil.ExcelUtil;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 * 
 * @author wzc
 * @date 2017年12月4日 下午5:27:33
 *qwert
 */
@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService eService;

	/**
	 * 导出表格
	 * 
	 * 
	 */
	@SuppressWarnings("null")
	@RequestMapping("/exportBooksTable.action")
	@ResponseBody
	public void exportBooksTable(HttpServletRequest request, HttpServletResponse response) {
		// 获取测评结果
		List<Employee> empsResult = eService.getEmpsTestResult();
		// excel文件名
		String fileName = "员工测评表" + System.currentTimeMillis() + ".xls";
		// excel标题
		String[] title = { "姓名", "部门", "业务指标", "管理指标", "关键事件得分", "其他员工评价得分", "其他经理评价得分", "最终得分", "等级" };
		// sheet名
		String sheetName = "员工测评表";
		String[][] content = new String[200][3];
		for (int i = 0; i < empsResult.size(); i++) {
			content[i] = new String[title.length];
			Employee obj = empsResult.get(i);
			content[i][0] = obj.geteName().toString();
			content[i][1] = obj.getDepart().getdName().toString();
			content[i][2] = obj.getBussinessScore() + "";
			content[i][3] = obj.getManagersScore() + "";
			content[i][4] = obj.getKeyScore() + "";
			content[i][5] = obj.getEmpsScore() + "";
			content[i][6] = obj.getOtherScore() + "";
			content[i][7] = obj.getFinalScore() + "";
			if (obj.getFinalScore() >= 9 && obj.getFinalScore() <= 10) {
				content[i][8] = "A 优秀";
			} else if (obj.getFinalScore() >= 7 && obj.getFinalScore() < 9) {
				content[i][8] = "B 良好";
			} else if (obj.getFinalScore() >= 5 && obj.getFinalScore() < 7) {
				content[i][8] = "C 合格";
			} else if (obj.getFinalScore() >= 1 && obj.getFinalScore() < 5) {
				content[i][8] = "D 不合格";
			}
		}
		// 创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 查询总结果
	 * 
	 * 
	 */

	@RequestMapping("/goTestResult.action")
	public ModelAndView showTestResult() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("testResult");
		List<String> empNames2 = eService.selectAllEs();
		for (String string : empNames2) {
			eService.updateEmps(string);
		}
		List<String> empNames = eService.selectAllEmpName();
		int num;
		int num2;
		int sum = 0;
		for (String string : empNames) {
			eService.updateEmps(string);
			num = eService.selectcount(string);// 3s num4
			List<Integer> list = eService.selectScores(string);
			List<Integer> list2 = eService.selectOtherScores(string);
			if (list2.size() < 6) {
				Map<String, Object> map = valu(list2, sum, string);
				eService.updateOtherScoreInEmps(map);

			} else if (list2.size() < 11) {
				list2.remove(0);
				list2.remove(list2.size() - 1);

				Map<String, Object> map = valu(list2, sum, string);
				eService.updateOtherScoreInEmps(map);

			} else if (list2.size() < 21) {

				list2.remove(0);// 123456
				list2.remove(list2.size() - 1);
				list2.remove(0);
				list2.remove(list2.size() - 1);
				Map<String, Object> map = valu(list2, sum, string);
				eService.updateOtherScoreInEmps(map);
			} else if (list2.size() < 31) {
				list2.remove(0);// 123456
				list2.remove(list2.size() - 1);
				list2.remove(0);
				list2.remove(list2.size() - 1);
				list2.remove(0);
				list2.remove(list2.size() - 1);
				Map<String, Object> map = valu(list2, sum, string);
				eService.updateOtherScoreInEmps(map);
			}
			if (list.size() < 6) {
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 11) {
				list.remove(0);
				list.remove(list.size() - 1);

				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);

			} else if (list.size() < 21) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);

				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 31) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 41) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 51) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 61) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
				Map<String, Object> map2 = valu(list2, sum, string);
				eService.updateOtherScoreInEmps(map2);
			} else if (list.size() < 71) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 81) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 91) {
				list.remove(0);// 1
				list.remove(list.size() - 1);
				list.remove(0);// 2
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 101) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 111) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else if (list.size() < 121) {
				list.remove(0);// 123456
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				list.remove(0);
				list.remove(list.size() - 1);
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			} else {
				Map<String, Object> map = valu(list, sum, string);
				eService.updateEmpsScoreInEmps(map);
			}
		}

		List<Employee> empsResult = eService.getEmpsTestResult();
		mView.addObject("emps", empsResult);

		// eService.updateOtherScoreInEmps();
		return mView;

	}

	/**
	 * 登录
	 * 
	 * @param empName
	 *            登录名
	 * @param pwd
	 *            登录密码
	 * @throws IOException
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(@RequestParam("empName") String empName, @RequestParam("pwd") String pwd,
			@RequestParam(value = "departId", required = false) String departId, HttpServletResponse response,
			HttpSession session, HttpServletRequest request, Model model) throws IOException {
		Map<String, String> empIn = new HashMap<String, String>();
		String returnUrl = "";
		empIn.put("empName", empName);
		empIn.put("pwd", pwd);
		List<Employee> loginEmp = eService.Login(empIn);
		switch (loginEmp.size()) {
		case 0:
			returnUrl = "login";
			request.setAttribute("tip", "用户不存在,重新输入");
			break; // 不存在当前用户
		case 1:
			if (loginEmp.get(0).getPwd().equals(pwd)) {
				returnUrl = "main";
				session.setAttribute("user", loginEmp.get(0));
			} else {
				request.setAttribute("tip", "密码错误，重新输入");
				returnUrl = "login";
			}
			break;
		}
		return returnUrl;
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return 登录页面
	 */
	@RequestMapping("/logout.action")
	public String LogOutputStream(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		session.removeAttribute("user");
		session.invalidate();
		return "login";
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/editpwd.action")
	public String editpwd(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws IOException {
		String uname = request.getParameter("uname");
		String lname = request.getParameter("lname");
		Map<String, String> umap = new HashMap<String, String>();
		umap.put(uname, lname);
		eService.editpwd(umap);
		request.setAttribute("tips", "密码修改成功,下次登陆请使用新密码");
		return "welcome";
	}

	/**
	 * 经理给员工测评
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/goTestExcep.action")
	public ModelAndView goTestExcep(HttpServletRequest request, HttpSession session) {
		Employee emp = (Employee) request.getSession().getAttribute("user");
		List<NewEmp> newEmps = eService.getEmpsByDepart(emp.geteName());
		for (NewEmp newEmp : newEmps) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name1", newEmp.getE_name());
			map.put("name2", emp.geteName());
			Integer scor = eService.selectOneScore(map);
			if (null == scor) {
				newEmp.setScore(1);
			} else {
				newEmp.setScore(scor);
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("content");
		mView.addObject("emps", null);
		List<Depart> departs = eService.getAllDepartExc(emp.getDepart().getdId());
		mView.addObject("departs", departs);

		return mView;
	}

	/**
	 * 按部门来获取员工
	 * 
	 * @return
	 */
	@RequestMapping("/goTest.action")
	public ModelAndView goTest(HttpServletRequest request, HttpSession session) {
		Employee emp = (Employee) request.getSession().getAttribute("user");
		List<NewEmp> newEmps = eService.getEmpsByDepart(emp.geteName());
		for (NewEmp newEmp : newEmps) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name1", newEmp.getE_name());
			map.put("name2", emp.geteName());
			Integer scor = eService.selectOneScore(map);
			if (null == scor) {
				newEmp.setScore(1);
			} else {
				newEmp.setScore(scor);
			}
		}

		ModelAndView mView = new ModelAndView();
		mView.setViewName("content");
		mView.addObject("emps", newEmps);
		mView.addObject("dep", emp.getDepart().getdName());
		List<Depart> departs = eService.getAllDepart(emp.getDepart().getdId());
		mView.addObject("departs", departs);

		return mView;
	}

	@RequestMapping("/getEmpsByDepartName.action")
	public ModelAndView getEmps(@RequestParam("departName") String departName, HttpServletRequest request,
			HttpSession session) {
		List<NewEmp> lists = eService.getEmpsByDepartName(departName);
		Employee emp = (Employee) request.getSession().getAttribute("user");

		for (NewEmp newEmp : lists) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name1", newEmp.getE_name());
			map.put("name2", emp.geteName());
			Integer scor = eService.selectOneScore2(map);
			if (null == scor) {
				newEmp.setScore(1);
			} else {
				newEmp.setScore(scor);
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("content");
		mView.addObject("emps", lists);
		mView.addObject("dep", lists.get(0).getD_name());

		List<Depart> departs = eService.getAllDepartExc(emp.getDepart().getdId());
		mView.addObject("departs", departs);
		return mView;
	}

	/**
	 * 保存互评分数
	 * 
	 * @param testId
	 * @param score
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveTest.action")
	@ResponseBody
	public String saveTest(@RequestParam("name") String name, @RequestParam("score") int score,
			HttpServletRequest request) {
		// TODO:
		// 服务器端验证

		if (0 > score || score > 10) {
			return "0";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Employee emp = (Employee) request.getSession().getAttribute("user");
		String username = emp.geteName();
		Integer jobType = eService.getDepartByJobType(emp.geteJobType());
		Date date = new Date();
		Integer e_d_id = eService.getDepartByName(name);
		map.put("name", name);// 当前被评价同事
		map.put("e_d_id", e_d_id);// 当前被评价同事所属部门
		map.put("username", username);// 当前登录用户
		map.put("jobType", jobType);// 当前登录用户职称
		map.put("score", score);// 分数
		map.put("date", date);// 当前时间
		boolean isRecored = eService.isRecored(map); // 判断是否已经对该同事评测过
		if (isRecored) {
			eService.updateScore(map);
		} else {
			eService.saveScore(map);
		}

		return "1";
	}

	/**
	 * 获取员工所属部门员工
	 * 
	 * @return
	 */
	@RequestMapping("/goDirectorTest.action")
	public ModelAndView goDirector(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("directorEmps");
		Employee admin = (Employee) request.getSession().getAttribute("user");
		List<Employee> employees = eService.getOwnEmps(admin.getDepart().getdId());
		mView.addObject("emps", employees);

		return mView;
	}

	/**
	 * 经理去部员测评详细页面
	 * 
	 * @param empName
	 * @return
	 */
	@RequestMapping("/empScore.action")
	public ModelAndView empScore(@RequestParam("empName") String name, HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("test");
		Map<String, Object> map = new HashMap<String, Object>();
		Employee user = (Employee) request.getSession().getAttribute("user");
		map.put("director", user.geteName());
		map.put("name", name);
		EmpScore empScore = eService.getEmpScore(map);
		EmpScore empScore2 = new EmpScore();
		if (null == empScore) {
			empScore2.setDirector(user.geteName());
			empScore2.setEmpName(name);
			mView.addObject("empScore", empScore2);
		} else {
			mView.addObject("empScore", empScore);
		}

		Employee employee = (Employee) request.getSession().getAttribute("user");
		mView.addObject("userId", employee.geteJobType());

		return mView;
	}

	/**
	 * 经理给某员工打完分后保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveEmpScore.action", method = RequestMethod.POST)
	public ModelAndView saveEmpScore(EmpScore empScore, HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("user");
		empScore.setDirector(employee.geteName());

		ModelAndView mView = new ModelAndView();
		mView.setViewName("redirect:/goDirectorTest.action");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dname", empScore.getDirector());
		map.put("ename", empScore.getEmpName());
		boolean isRecored = eService.iskey(map);
		if (isRecored) {
			eService.saveScoreRecord(empScore);

		} else {
			eService.insertScoreRecord(empScore);

		}

		return mView;

	}

	// 数据处理抽取

	Map<String, Object> valu(List<Integer> list, int sum, String name) {
		for (Integer integer : list) {

			sum += integer;
		}
		double sum2 = sum;
		double sum3 = list.size();
		double sum4 = 0.0;
		if (sum3 != 0.0) {
			sum4 = sum2 / sum3;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sum4", sum4);
		map.put("name", name);

		return map;
	}

}

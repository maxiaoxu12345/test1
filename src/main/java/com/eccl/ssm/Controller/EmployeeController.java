/**
 * 
 */
package com.eccl.ssm.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.eccl.ssm.Entity.Role;
import com.eccl.ssm.Service.EmployeeService;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2017年12月4日 下午5:27:33
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	private  EmployeeService eService;
	
	/**
	 * 测试用
	 * @return
	 */
	@RequestMapping("/getAllEmps.action")
	@ResponseBody
	public List<Employee> getAllEmps(){
		return eService.getEmps();
	}
	
	/**
	 * 登录
	 * @param empName  登录名
	 * @param pwd  登录密码
	 * @throws IOException 
	 */
	@RequestMapping(value="/login.action", method = RequestMethod.POST)
	
	public String login(@RequestParam("empName")String empName,
			            @RequestParam("pwd")String pwd,
			            @RequestParam(value = "departId" ,required=false)String departId,
			            HttpServletResponse response,
			            HttpSession session,
			            Model model) throws IOException{
		
		
		System.out.println(empName);
		Map<String, String> empIn= new HashMap<String, String>();

		String returnUrl = "";
		
		
		empIn.put("empName", empName);
		empIn.put("pwd", pwd);
		empIn.put("depart", departId);
		
		List<Employee>  loginEmp = eService.Login(empIn);
			
		/*for (Employee employee : loginEmp) {
			System.out.println("查出来的登录用户:"+employee);
		}*/
		
		switch (loginEmp.size()) {
		case 0:
			returnUrl = "login";
			break;   //不存在当前用户 
        case 1:
        	if (loginEmp.get(0).getPwd().equals(pwd)) {
				returnUrl = "main";
				
				session.setAttribute("user", loginEmp.get(0));
			} else {
                returnUrl = "login";
			}
			break;   

		default:
			List<Depart> allDepart = eService.getAllDepart();
			model.addAttribute("allDeparts", allDepart);
			returnUrl = "login";
			break;
		}
		
		return returnUrl;
	}
	
	
	/**
	 * 退出登录
	 * @param request
	 * @return  登录页面
	 */
	@RequestMapping("/logout.action")
	public String LogOutputStream(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		
		session.removeAttribute("user");
		
	    session.invalidate();
		return "login";
	}
	/**
	 * 员工测评
	 * @param request
	 * @return
	 */
	@RequestMapping("/goTest.action")
    public ModelAndView goTest(HttpServletRequest request, HttpSession session){
		Employee emp = (Employee) request.getSession().getAttribute("user");
		
	    List<Employee> empsByDepart = eService.getEmpsByDepart(emp.getDepart().getdId());
	   
	 
      /* request.setAttribute("emps", empsByDepart);*/
	   ModelAndView mView=new ModelAndView();
	   mView.setViewName("content");
	   mView.addObject("emps", empsByDepart);
	   mView.addObject("dep", emp.getDepart().getdName());
	   List<Depart> departs=eService.getAllDepart();
	   session.setAttribute("departs", departs);
	   return mView;
    }
	
	/**
	 * 按部门来获取员工
	 * @return
	 */
	@RequestMapping("/getEmpsByDepart.action")
	public ModelAndView getEmps(@RequestParam("departId")int departId){
		List<Employee> lists=eService.getEmpsByDepart(departId);
		ModelAndView mView = new ModelAndView();
		mView.setViewName("content");
		mView.addObject("emps", lists);
		
		//
		Depart depart = eService.getDepartById(departId);
		
		mView.addObject("dep", depart.getdName());
		return mView;
	}
	
	/**
	 * 保存互评分数
	 * @param testId
	 * @param score
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveTest.action")
	@ResponseBody
	public String saveTest(@RequestParam("eId")int testId,
			               @RequestParam("score")double score,
			               HttpServletRequest request){
		//TODO:
		//服务器端验证
		
		if ( 0 > score || score >100) {
			return "0" ;
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Employee emp = (Employee) request.getSession().getAttribute("user");
		String name=emp.geteName();
		
		String jobType = emp.geteJobType();
	
        Date date = new Date();
        
        Employee employee = eService.getEmpById(testId);
        String testName = employee.geteName();
		map.put("name", name);
		map.put("testName", testName);
		
		map.put("score", score);
		map.put("date", date);
		
	    map.put("departId", emp.getDepart().getdId());
		
		//还要将当前用户的职位类型存起来，便于统计计算
		List<Role> roles = eService.getAllRole();
		
	    for (Role role : roles) {
	   
			if (role.getrName().equals(jobType)) {
				map.put("jobType", role.getrId());  //放到map中
				
			}
		}
	  
		
		boolean isRecored = eService.isRecored(map); //判断是否已经对该同事评测过
		
		if (isRecored) {
			eService.updateScore(map);
		} else {
			eService.saveScore(map);
		}
		
		return "1";
	}
	
	/**
	 * 部门经理给部员打其他分
	 * @return
	 */
	@RequestMapping("/goDirectorTest.action")
	public  ModelAndView goDirector(HttpServletRequest request){
		ModelAndView mView=new ModelAndView();
		mView.setViewName("directorEmps");
		
		Employee admin = (Employee) request.getSession().getAttribute("user");
		System.out.println(admin);
		
		List<Employee> employees = eService.getOwnEmps(admin.getDepart().getdId());
		/*for (Employee employee : employees) {
			System.out.println(employee);
		}*/
		mView.addObject("emps", employees);
		
		return mView;
	}
	/**
	 * 经理去部员测评详细页面
	 * @param empName
	 * @return
	 */
	@RequestMapping("/empScore.action")
	public ModelAndView empScore(@RequestParam("empId")int empId,HttpServletRequest request){
	    ModelAndView mView=new ModelAndView();
	    mView.setViewName("test");
	    
	    System.out.println("测评："+empId);
	     Employee empById = eService.getEmpById(empId);
	     String empName = empById.geteName();
	    EmpScore empScore=eService.getEmpScore(empName);
	    if (empScore == null) {
			empScore = new EmpScore();
			empScore.setEmpName(empName);
		}
	   /* System.out.println(empScore);*/
	    mView.addObject("empScore", empScore);
	    return mView;
	}
	
	/**
	 * 经理给某员工打完分后保存
	 * @return
	 */
	@RequestMapping(value = "/saveEmpScore.action" , method = RequestMethod.POST)
	public ModelAndView saveEmpScore(EmpScore empScore,HttpServletRequest request){
		System.out.println("保存评测员工："+empScore);
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("redirect:/goDirectorTest.action");
		
		
		
		double scoreAchievement = empScore.getScoreAchievement();
		double scoreFinish = empScore.getScoreFinish();
		double scoreFinance = empScore.getScoreFinance();
		double scoreHygiene = empScore.getScoreHygiene();
		double scoreAttendance = empScore.getScoreAttendance();
		double scoreBehavior = empScore.getScoreBehavior();
		double scorePlan = empScore.getScorePlan();
		double scoreContribution = empScore.getScoreContribution();
		double scoreFault = empScore.getScoreFault();
		if (scoreAchievement < 0 || scoreAchievement>100 || 
				scoreFinish < 0 || scoreFinish>100 ||
				scoreFinance < 0 || scoreFinance>100 ||
				scoreHygiene < 0 || scoreHygiene>100 ||
				scoreAttendance < 0 || scoreAttendance>100 ||
				scoreBehavior < 0 || scoreBehavior>100 ||
				scorePlan < 0 || scorePlan>100 ||
				scoreContribution < 0 || scoreContribution % 10 !=0 ||
				scoreFault > 0 || scoreFault % 10 !=0 ) {
			System.err.println("评分格式不正确");
			return mView;
		}
		
		Employee emp = (Employee)request.getSession().getAttribute("user");
		empScore.setDirector(emp.geteName());
		
		
		//将评分记录保存
		eService.saveScoreRecord(empScore);
		
		
		
		return mView;
		
	}
	/**
	 * 经理更新某个员工的评分
	 */
	@RequestMapping("/updateEmpScore.action")
	public ModelAndView updateEmpScore(EmpScore empScore,HttpServletRequest request){
        System.out.println("更新评测员工："+empScore);
		
		Employee emp = (Employee)request.getSession().getAttribute("user");
		empScore.setDirector(emp.geteName());
		
		
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("redirect:/goDirectorTest.action");
		
		
		

		double scoreAchievement = empScore.getScoreAchievement();
		double scoreFinish = empScore.getScoreFinish();
		double scoreFinance = empScore.getScoreFinance();
		double scoreHygiene = empScore.getScoreHygiene();
		double scoreAttendance = empScore.getScoreAttendance();
		double scoreBehavior = empScore.getScoreBehavior();
		double scorePlan = empScore.getScorePlan();
		double scoreContribution = empScore.getScoreContribution();
		double scoreFault = empScore.getScoreFault();
		if (scoreAchievement < 0 || scoreAchievement>100 || 
				scoreFinish < 0 || scoreFinish>100 ||
				scoreFinance < 0 || scoreFinance>100 ||
				scoreHygiene < 0 || scoreHygiene>100 ||
				scoreAttendance < 0 || scoreAttendance>100 ||
				scoreBehavior < 0 || scoreBehavior>100 ||
				scorePlan < 0 || scorePlan>100 ||
				scoreContribution < 0 || scoreContribution % 10 !=0 ||
				scoreFault > 0 || scoreFault % 10 !=0 ) {
			System.err.println("评分格式不正确");
			return mView;
		}
		
		
		//将评分记录保存
	    eService.updateScoreRecord(empScore);

		return mView;
	}
	
	@RequestMapping("/goTestResult.action")
	public ModelAndView showTestResult(){
		System.out.println("显示测评结果");
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("testResult");
		//获取测评结果
		List<Employee> empsResult =  eService.getEmpsTestResult();
		
		mView.addObject("emps", empsResult);
		return mView ;
				
	}

}

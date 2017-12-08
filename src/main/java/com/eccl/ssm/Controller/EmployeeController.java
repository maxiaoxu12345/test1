/**
 * 
 */
package com.eccl.ssm.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private static final String Employee = null;
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
	@RequestMapping("/login.action")
	public String login(@RequestParam("empName")String empName,
			            @RequestParam("pwd")String pwd,
			            HttpServletResponse response,
			            HttpSession session) throws IOException{
		
		System.out.println("name:"+empName+":"+"pwd:"+pwd);
		Map<String, String> empIn= new HashMap<String, String>();
		empIn.put("empName", empName);
		empIn.put("pwd", pwd);
		Employee loginEmp = eService.Login(empIn);
		if (loginEmp==null) {
				return "login";
		} 
		/*List<Depart> departs=eService.getAllDepart();*/
		/*for (Depart depart : departs) {
			System.out.println(depart);
		}*/
		session.setAttribute("user", loginEmp);
		/*session.setAttribute("departs", departs);*/
		return "main";
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
	    List<Employee> empsByDepart = eService.getEmpsByDepart(emp.getDepart().getdName());
	   
	 
      /* request.setAttribute("emps", empsByDepart);*/
	   ModelAndView mView=new ModelAndView();
	   mView.setViewName("content");
	   mView.addObject("emps", empsByDepart);
	   List<Depart> departs=eService.getAllDepart();
	   session.setAttribute("departs", departs);
	   return mView;
    }
	
	/**
	 * 按部门来获取员工
	 * @return
	 */
	@RequestMapping("/getEmpsByDepart.action")
	public ModelAndView getEmps(@RequestParam("departName")String departName){
		List<Employee> lists=eService.getEmpsByDepart(departName);
		ModelAndView mView = new ModelAndView();
		mView.setViewName("content");
		mView.addObject("emps", lists);
		return mView;
	}
	
	/**
	 * 保存互评分数
	 * @param testName
	 * @param score
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveTest.action")
	@ResponseBody
	public String saveTest(@RequestParam("name")String testName,
			               @RequestParam("score")double score,
			               HttpServletRequest request){
		
		Map<String, Object> map = new HashMap<>();
		Employee emp = (Employee) request.getSession().getAttribute("user");
		String name=emp.geteName();
		String jobType = emp.geteJobType();
		System.out.println("hah"+name+"职位："+jobType+",name:"+testName+":分"+score);
        Date date = new Date();
        //System.out.println(date);
      
		map.put("name", name);
		map.put("testName", testName);
		map.put("score", score);
		map.put("date", date);
		/* System.out.println("部门："+emp.getDepart().getdId());*/
	    map.put("departId", emp.getDepart().getdId());
		
		//还要将当前用户的职位类型存起来，便于统计计算
		List<Role> roles = eService.getAllRole();
		
	    for (Role role : roles) {
	    /*	System.out.println(role);*/
			if (role.getrName().equals(jobType)) {
				map.put("jobType", role.getrId());  //放到map中
				/*System.out.println(map.get("jobType"));*/
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
	public ModelAndView empScore(@RequestParam("empName")String empName,HttpServletRequest request){
	    ModelAndView mView=new ModelAndView();
	    mView.setViewName("test");
	    
	    System.out.println("测评："+empName);
	    EmpScore empScore=eService.getEmpScore(empName);
	    if (empScore == null) {
			empScore = new EmpScore();
			empScore.setEmpName(empName);
		}
	    System.out.println(empScore);
	    mView.addObject("empScore", empScore);
	    return mView;
	}
	
	/**
	 * 经理给某员工打完分后保存
	 * @return
	 */
	@RequestMapping("/saveEmpScore.action")
	public ModelAndView saveEmpScore(EmpScore empScore,HttpServletRequest request){
		System.out.println("保存评测员工："+empScore);
		
		Employee emp = (Employee)request.getSession().getAttribute("user");
		empScore.setDirector(emp.geteName());
		
		//将评分记录保存
		eService.saveScoreRecord(empScore);
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("redirect:/goDirectorTest.action");
		
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
		
		//将评分记录保存
		eService.updateScoreRecord(empScore);
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("redirect:/goDirectorTest.action");
		
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

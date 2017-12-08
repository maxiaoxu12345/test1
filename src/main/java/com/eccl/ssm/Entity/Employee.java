/**
 * 
 */
package com.eccl.ssm.Entity;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2017年12月4日 下午5:29:37
 *
 */
public class Employee {
	private  String  eName;   
	private  Depart depart;
	private  String eJobType;   //职位类型
	private double directorScore; //经理打分
	private double managersScore;  //其他经理打分
	private double empsScore;     //其他员工打分
	private double otherScore;    //其他分
	private String pwd;
	
	private double totalScore;    //最终合计分数
	private double businessScore;  //业务指标得分
	private double rulesScore;     //公司规章制度 评分
    private double eventScore;    // 贡献与过失 得分
	private double teamScore ;    //员工互评+ 经理评分 + 其他经理评分
	private double manageScore;   //管理指标 得分
	
	
	public Employee() {
		super();
	}
	
	
	public Employee(String eName, Depart depart, String eJobType, double directorScore, double managersScore,
			double empsScore, double otherScore, String pwd, double totalScore, double businessScore, double rulesScore,
			double eventScore, double teamScore, double manageScore) {
		super();
		this.eName = eName;
		this.depart = depart;
		this.eJobType = eJobType;
		this.directorScore = directorScore;
		this.managersScore = managersScore;
		this.empsScore = empsScore;
		this.otherScore = otherScore;
		this.pwd = pwd;
		this.totalScore = totalScore;
		this.businessScore = businessScore;
		this.rulesScore = rulesScore;
		this.eventScore = eventScore;
		this.teamScore = teamScore;
		this.manageScore = manageScore;
	}


	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
    
	public double getTeamScore() {
		return teamScore;
	}


	public void setTeamScore(double teamScore) {
		this.teamScore = teamScore;
	}


	public double getManageScore() {
		return manageScore;
	}


	public void setManageScore(double manageScore) {
		this.manageScore = manageScore;
	}


	public double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	

	public double getBusinessScore() {
		return businessScore;
	}

	public void setBusinessScore(double businessScore) {
		this.businessScore = businessScore;
	}

	public double getRulesScore() {
		return rulesScore;
	}

	public void setRulesScore(double rulesScore) {
		this.rulesScore = rulesScore;
	}

	public double getEventScore() {
		return eventScore;
	}

	public void setEventScore(double eventScore) {
		this.eventScore = eventScore;
	}

	

	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public String geteJobType() {
		return eJobType;
	}
	public void seteJobType(String eJobType) {
		this.eJobType = eJobType;
	}
	public double getDirectorScore() {
		return directorScore;
	}
	public void setDirectorScore(double directorScore) {
		this.directorScore = directorScore;
	}
	public double getManagersScore() {
		return managersScore;
	}
	public void setManagersScore(double managersScore) {
		this.managersScore = managersScore;
	}
	public double getEmpsScore() {
		return empsScore;
	}
	public void setEmpsScore(double empsScore) {
		this.empsScore = empsScore;
	}
	public double getOtherScore() {
		return otherScore;
	}
	public void setOtherScore(double otherScore) {
		this.otherScore = otherScore;
	}

	@Override
	public String toString() {
		return "Employee [eName=" + eName + ", depart=" + depart + ", eJobType=" + eJobType + ", directorScore="
				+ directorScore + ", managersScore=" + managersScore + ", empsScore=" + empsScore + ", otherScore="
				+ otherScore + ", pwd=" + pwd + ", totalScore=" + totalScore + ", businessScore=" + businessScore
				+ ", rulesScore=" + rulesScore + ", eventScore=" + eventScore + "]";
	}

	
	
	
	

}

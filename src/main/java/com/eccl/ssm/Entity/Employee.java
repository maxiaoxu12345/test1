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
	private int eId;
	private String loginName;
	private String eName;
	private Depart depart;
	private String eJobType; // 职位类型
	private double bussinessScore;
	private double managersScore; // 其他经理打分
	private double empsScore; // 其他员工打分
	private double otherScore; // 其他分
	private double keyScore; // 其他分
	private String pwd;
	private double finalScore;

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public double getBussinessScore() {
		return bussinessScore;
	}

	public void setBussinessScore(double bussinessScore) {
		this.bussinessScore = bussinessScore;
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

	public double getKeyScore() {
		return keyScore;
	}

	public void setKeyScore(double keyScore) {
		this.keyScore = keyScore;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}

}

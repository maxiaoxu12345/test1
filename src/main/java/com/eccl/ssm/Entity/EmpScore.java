/**
 * 
 */
package com.eccl.ssm.Entity;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2017年12月6日 下午3:09:36
 *
 */

public class EmpScore {
   private int id;
   private String director;
   private String empName;
   private double scoreAchievement;
   private double scoreFinish;
   private double scoreFinance;
   private double scoreHygiene;
   private double scoreAttendance;
   private double scoreBehavior;
   
   private double scorePlan;
   private double scoreContribution;
   private double scoreFault;
public EmpScore() {
	super();
}
public EmpScore(int id, String director, String empName, double scoreAchievement, double scoreFinish,
		double scoreFinance, double scoreHygiene, double scoreAttendance, double scoreBehavior, double scorePlan,
		double scoreContribution, double scoreFault) {
	super();
	this.id = id;
	this.director = director;
	this.empName = empName;
	this.scoreAchievement = scoreAchievement;
	this.scoreFinish = scoreFinish;
	this.scoreFinance = scoreFinance;
	this.scoreHygiene = scoreHygiene;
	this.scoreAttendance = scoreAttendance;
	this.scoreBehavior = scoreBehavior;
	this.scorePlan = scorePlan;
	this.scoreContribution = scoreContribution;
	this.scoreFault = scoreFault;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}

public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public double getScoreAchievement() {
	return scoreAchievement;
}
public void setScoreAchievement(double scoreAchievement) {
	this.scoreAchievement = scoreAchievement;
}
public double getScoreFinish() {
	return scoreFinish;
}
public void setScoreFinish(double scoreFinish) {
	this.scoreFinish = scoreFinish;
}
public double getScoreFinance() {
	return scoreFinance;
}
public void setScoreFinance(double scoreFinance) {
	this.scoreFinance = scoreFinance;
}
public double getScoreHygiene() {
	return scoreHygiene;
}
public void setScoreHygiene(double scoreHygiene) {
	this.scoreHygiene = scoreHygiene;
}
public double getScoreAttendance() {
	return scoreAttendance;
}
public void setScoreAttendance(double scoreAttendance) {
	this.scoreAttendance = scoreAttendance;
}
public double getScoreBehavior() {
	return scoreBehavior;
}
public void setScoreBehavior(double scoreBehavior) {
	this.scoreBehavior = scoreBehavior;
}
public double getScorePlan() {
	return scorePlan;
}
public void setScorePlan(double scorePlan) {
	this.scorePlan = scorePlan;
}
public double getScoreContribution() {
	return scoreContribution;
}
public void setScoreContribution(double scoreContribution) {
	this.scoreContribution = scoreContribution;
}
public double getScoreFault() {
	return scoreFault;
}
public void setScoreFault(double scoreFault) {
	this.scoreFault = scoreFault;
}
@Override
public String toString() {
	return "EmpScore [id=" + id + ", director=" + director + ", empName=" + empName + ", scoreAchievement="
			+ scoreAchievement + ", scoreFinish=" + scoreFinish + ", scoreFinance=" + scoreFinance + ", scoreHygiene="
			+ scoreHygiene + ", scoreAttendance=" + scoreAttendance + ", scoreBehavior=" + scoreBehavior
			+ ", scorePlan=" + scorePlan + ", scoreContribution=" + scoreContribution + ", scoreFault=" + scoreFault
			+ "]";
}
   
   
   
   
   
   
   
}

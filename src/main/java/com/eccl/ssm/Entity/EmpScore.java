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
	private int scoreAchievement;
	private int scoreFinish;
	private int scoreFinance;
	private int scoreHygiene;
	private int scoreAttendance;
	private int scoreBehavior;
	private int scorePlan;
	private int scoreContribution;
	private int scoreFault;

	private int scoreOntime;
	private int scoreAdd;
	private int scoreSub;

	public int getScoreOntime() {
		return scoreOntime;
	}

	public void setScoreOntime(int scoreOntime) {
		this.scoreOntime = scoreOntime;
	}

	public int getScoreAdd() {
		return scoreAdd;
	}

	public void setScoreAdd(int scoreAdd) {
		this.scoreAdd = scoreAdd;
	}

	public int getScoreSub() {
		return scoreSub;
	}

	public void setScoreSub(int scoreSub) {
		this.scoreSub = scoreSub;
	}

	public int getScoreFault() {
		return scoreFault;
	}

	public void setScoreFault(int scoreFault) {
		this.scoreFault = scoreFault;
	}

	public int getScoreContribution() {
		return scoreContribution;
	}

	public void setScoreContribution(int scoreContribution) {
		this.scoreContribution = scoreContribution;
	}

	public int getScorePlan() {
		return scorePlan;
	}

	public void setScorePlan(int scorePlan) {
		this.scorePlan = scorePlan;
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

	public int getScoreAchievement() {
		return scoreAchievement;
	}

	public void setScoreAchievement(int scoreAchievement) {
		this.scoreAchievement = scoreAchievement;
	}

	public int getScoreFinish() {
		return scoreFinish;
	}

	public void setScoreFinish(int scoreFinish) {
		this.scoreFinish = scoreFinish;
	}

	public int getScoreFinance() {
		return scoreFinance;
	}

	public void setScoreFinance(int scoreFinance) {
		this.scoreFinance = scoreFinance;
	}

	public int getScoreHygiene() {
		return scoreHygiene;
	}

	public void setScoreHygiene(int scoreHygiene) {
		this.scoreHygiene = scoreHygiene;
	}

	public int getScoreAttendance() {
		return scoreAttendance;
	}

	public void setScoreAttendance(int scoreAttendance) {
		this.scoreAttendance = scoreAttendance;
	}

	public int getScoreBehavior() {
		return scoreBehavior;
	}

	public void setScoreBehavior(int scoreBehavior) {
		this.scoreBehavior = scoreBehavior;
	}

	@Override
	public String toString() {
		return "EmpScore [id=" + id + ", director=" + director + ", empName=" + empName + ", scoreAchievement="
				+ scoreAchievement + ", scoreFinish=" + scoreFinish + ", scoreFinance=" + scoreFinance
				+ ", scoreHygiene=" + scoreHygiene + ", scoreAttendance=" + scoreAttendance + ", scoreBehavior="
				+ scoreBehavior + ", scorePlan=" + scorePlan + ", scoreContribution=" + scoreContribution
				+ ", scoreFault=" + scoreFault + ", scoreOntime=" + scoreOntime + ", scoreAdd=" + scoreAdd
				+ ", scoreSub=" + scoreSub + "]";
	}

}

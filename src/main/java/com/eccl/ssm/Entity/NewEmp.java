/**
 * 
 */
package com.eccl.ssm.Entity;

/**
 * @author:
 * @time:2018年12月6日下午5:31:15
 * @description:
 */

public class NewEmp {
	private String e_name;
	private String d_name;
	private int e_job_type;
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getE_job_type() {
		return e_job_type;
	}

	public void setE_job_type(int e_job_type) {
		this.e_job_type = e_job_type;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

}

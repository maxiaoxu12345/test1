/**
 * 
 */
package com.eccl.ssm.Entity;

import java.io.Serializable;

/**
 * 部门
 *
 * @author wzc
 * @date 2017年12月4日 下午5:30:44
 *
 */
public class Depart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dId;
	private String dName;

	public Depart() {
		super();
	}

	public Depart(int dId, String dName) {
		super();
		this.dId = dId;
		this.dName = dName;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	@Override
	public String toString() {
		return "Depart [dId=" + dId + ", dName=" + dName + "]";
	}

}

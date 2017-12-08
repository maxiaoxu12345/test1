/**
 * 
 */
package com.eccl.ssm.Entity;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2017年12月7日 上午10:28:47
 *
 */
public class Role {
	 private int rId;
	 private String rName;
	public Role() {
		super();
	}
	public Role(int rId, String rName) {
		super();
		this.rId = rId;
		this.rName = rName;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	@Override
	public String toString() {
		return "Role [rId=" + rId + ", rName=" + rName + "]";
	}
	 
	
	 

}

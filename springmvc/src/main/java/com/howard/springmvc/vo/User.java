package com.howard.springmvc.vo;

import java.io.Serializable;
import java.util.Date;

public class User  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loginName;
	private Date birthday;
	//private String birthday;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "User [loginName=" + loginName + ", birthday=" + birthday + "]";
	}
	/*public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}*/
	
}

package com.howard.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.style.ToStringStyler;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -552981278075714501L;
	private Integer id;
	private String username;
	private String password;
	private Integer age;
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		//commons.lang3包
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id",getId())
				.append("username",getUsername())
				.append("password",getPassword())
				.append("age",getAge()).toString();
	}
	
	
}

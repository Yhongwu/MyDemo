package com.howard.shiro.vo;

public class SysRoles {
	private Integer id;
	private String role;
	private String description;
	private Boolean available;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "SysRoles [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
				+ "]";
	}
	
}

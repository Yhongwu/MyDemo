package com.howard.shiro.vo;

public class SysPermissions {
	private Integer id;
	private String permission;
	private String description;
	private Boolean available;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
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
		return "SysPermissions [id=" + id + ", permission=" + permission + ", description=" + description
				+ ", available=" + available + "]";
	} 
	
}

package com.howard.shiro.service;

import java.util.List;

import com.howard.shiro.vo.SysRoles;

public interface SysRolesService {
	List<SysRoles> getRoleByUserId(String name);
}

package com.howard.shiro.service;

import java.util.List;

import com.howard.shiro.vo.SysPermissions;
import com.howard.shiro.vo.SysRoles;

public interface SysPermissionsService {
	List<SysPermissions> getAllPermissionsByRoles(List<SysRoles> roles);
}

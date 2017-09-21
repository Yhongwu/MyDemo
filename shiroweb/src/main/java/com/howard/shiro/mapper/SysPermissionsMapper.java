package com.howard.shiro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.howard.shiro.vo.SysPermissions;

public interface SysPermissionsMapper {
	List<SysPermissions> getAllPermissionsByRoles(@Param("id") Integer id);
}

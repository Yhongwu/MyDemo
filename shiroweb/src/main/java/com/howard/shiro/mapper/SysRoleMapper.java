package com.howard.shiro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.howard.shiro.vo.SysRoles;

public interface SysRoleMapper {
	List<SysRoles> getRoleByUserId(@Param("name") String id);
}

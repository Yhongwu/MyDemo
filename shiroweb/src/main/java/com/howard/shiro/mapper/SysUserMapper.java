package com.howard.shiro.mapper;

import java.util.List;

import com.howard.shiro.vo.SysUser;

public interface SysUserMapper {
	SysUser getByUsername(String username);
	List<SysUser> getAll();
}

package com.howard.shiro.mapper;

import com.howard.shiro.vo.SysUser;

public interface SysUserMapper {
	SysUser getByUsername(String username);
}

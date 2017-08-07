package com.howard.shiro.service;

import com.howard.shiro.vo.SysUser;

public interface SysUsersService {
	SysUser getByUsername(String username);
}

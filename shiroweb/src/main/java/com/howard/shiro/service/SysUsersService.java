package com.howard.shiro.service;

import java.util.List;

import com.howard.shiro.vo.SysUser;

public interface SysUsersService {
	SysUser getByUsername(String username);
	List<SysUser> getAll();
}

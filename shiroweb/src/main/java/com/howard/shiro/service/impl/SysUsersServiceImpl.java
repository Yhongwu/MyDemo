package com.howard.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howard.shiro.mapper.SysUserMapper;
import com.howard.shiro.service.SysUsersService;
import com.howard.shiro.vo.SysUser;
@Service
public class SysUsersServiceImpl implements SysUsersService{
	@Autowired
	private SysUserMapper mapper;
	@Override
	public SysUser getByUsername(String username) {
		return mapper.getByUsername(username);
	}
	@Override
	public List<SysUser> getAll() {
		return mapper.getAll();
	}

}

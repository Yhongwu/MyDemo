package com.howard.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howard.shiro.mapper.SysRoleMapper;
import com.howard.shiro.service.SysRolesService;
import com.howard.shiro.vo.SysRoles;
@Service
public class SysRolesServiceImpl implements SysRolesService{
	
	@Autowired
	private SysRoleMapper mapper;
	
	@Override
	public List<SysRoles> getRoleByUserId(String name) {
		return mapper.getRoleByUserId(name);
		 
	}

}

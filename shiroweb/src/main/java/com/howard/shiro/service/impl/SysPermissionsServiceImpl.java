package com.howard.shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howard.shiro.mapper.SysPermissionsMapper;
import com.howard.shiro.service.SysPermissionsService;
import com.howard.shiro.vo.SysPermissions;
import com.howard.shiro.vo.SysRoles;
@Service
public class SysPermissionsServiceImpl implements SysPermissionsService{
	@Autowired
	private SysPermissionsMapper mapper;
	@Override
	public List<SysPermissions> getAllPermissionsByRoles(List<SysRoles> roles) {
		List<SysPermissions> list = new ArrayList<>();
		for(SysRoles role :roles) {
			List<SysPermissions> allPermissionsByRoles = mapper.getAllPermissionsByRoles(role.getId());
			list.addAll(allPermissionsByRoles);
		}
		return list;
	}
	
}

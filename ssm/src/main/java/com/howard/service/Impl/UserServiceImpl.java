package com.howard.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howard.dao.UserDao;
import com.howard.entity.User;
import com.howard.service.UserService;

@Service
//@Transactional 类级别注解 如果注释在类或者接口上 则其所有方法拥有同样的事务
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	//注解事务 readOnly默认为true
	//rollbackFor与noRollbackFor:可自定多个 如：rollbackFor={RuntimeException.class, Exception.class}
	//@Transactional(readOnly=true,rollbackFor=Exception.class)
	@Override
	public int create(User user) {
		if (user == null) {
			return -1;
		}
		int i = userDao.insert(user);
		//测试aop事务
		int j = 5/0;
		return i;
	}

	@Override
	public List<User> findUsers(User user) {
		List<User> users = userDao.findUsers(user);
		return users;
	}

	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}

}

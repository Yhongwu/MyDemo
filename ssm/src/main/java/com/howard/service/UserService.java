package com.howard.service;

import java.util.List;

import com.howard.entity.User;

public interface UserService {
	/**
	 * 插入user
	 * @param user
	 * @return 主键
	 */
	public int create(User user);
	/**
	 * 根据条件查找user
	 * @param user
	 * @return
	 */
	public List<User> findUsers(User user);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public User getById(int id);
	
}

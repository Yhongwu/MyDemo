package com.howard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.howard.entity.User;

public interface UserDao {
	public List<User> findUsers(@Param("user") User user);
	public int insert(User user);
	public User getById(int id);
}

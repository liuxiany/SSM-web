package com.ssm.demo.service;

import com.ssm.demo.entity.User;

import java.util.List;


public interface IDemoService {
	/**
	 * 增加用户
	 * 测试mysql + mybatis
	 * 测试事务
	 * 测试mybatis和spring 的整合
	 * @param name
	 */
    void addUser(String name) throws Exception;

	/**
	 * 获取所有用户
	 * @return
	 */
	List<User> getAllUsers() throws Exception;

    User getUserById(String userId) throws Exception;

    List<User> getUserByName(String name) throws Exception;
}

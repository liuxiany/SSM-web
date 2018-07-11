package com.ssm.demo.service;

import com.ssm.demo.entity.User;


public interface IDemoService {
	/**
	 * 增加用户
	 * 测试mysql + mybatis
	 * 测试事务
	 * 测试mybatis和spring 的整合
	 * @param name
	 */
    void addUser(String name) throws Exception;

}

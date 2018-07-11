package com.ssm.demo.mapper;


import com.ssm.demo.entity.User;

public interface UserMapper {
	void add(User user);

    User selectByPrimaryKey(String id);
}

package com.ssm.demo.mapper;


import com.ssm.demo.entity.User;

import java.util.List;

public interface UserMapper {
	void add(User user);

    User selectByPrimaryKey(String id);

    List<User> selectAll();
}

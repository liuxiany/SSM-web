package com.ssm.demo.service.impl;

import com.ssm.demo.entity.Account;
import com.ssm.demo.entity.User;
import com.ssm.demo.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ssm.demo.mapper.UserMapper;
import com.ssm.demo.service.IDemoService;

import java.util.List;

@Service("demoService")
@Scope("singleton")
public class DemoServiceImpl implements IDemoService {

	private final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public void addUser(String name) throws Exception{
		logger.info("enter service");

        User user = new User();
        user.setId(String.valueOf(System.currentTimeMillis()));
        user.setName(name);

        Account account = new Account();
        account.setId(String.valueOf(System.currentTimeMillis()));
        account.setUserId(user.getId());

		userMapper.add(user);
		accountMapper.add(account);


        /**
         * 测试事务，抛出异常时，事务回滚。
         */
        /*Account account2 = accountMapper.selectByUserId(String.valueOf(System.currentTimeMillis()));

        account2.setScroe(10.5);*/

	}

	@Override
	public List<User> getAllUsers() {
		return userMapper.selectAll();
	}
}

package com.ssm.demo.mapper;

import com.ssm.demo.entity.Account;

public interface AccountMapper {

    void add(Account account);

    Account selectByUserId(String userId);
}

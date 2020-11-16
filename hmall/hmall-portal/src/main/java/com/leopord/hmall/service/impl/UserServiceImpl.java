package com.leopord.hmall.service.impl;

import com.leopord.hmall.dao.UserMapper;
import com.leopord.hmall.entity.User;
import com.leopord.hmall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 用户服务
 * @author: Leopord
 * @time: 2020/11/16 14:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {

        return userMapper.selectByUsername(name);
    }


}

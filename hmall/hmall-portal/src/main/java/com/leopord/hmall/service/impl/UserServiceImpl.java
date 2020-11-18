package com.leopord.hmall.service.impl;

import com.leopord.hmall.dao.UserMapper;
import com.leopord.hmall.entity.User;
import com.leopord.hmall.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public void login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
    }

    @Override
    public void register(User user) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String password = new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(salt), 2).toString();
        user.setSalt(salt);
        user.setPassword(password);
        addUser(user);
    }
}

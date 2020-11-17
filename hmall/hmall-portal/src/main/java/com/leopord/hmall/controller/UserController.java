package com.leopord.hmall.controller;

import com.leopord.hmall.api.CommonResult;
import com.leopord.hmall.api.StatusCode;
import com.leopord.hmall.entity.User;
import com.leopord.hmall.exception.BusinessException;
import com.leopord.hmall.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Leopord
 * @time: 2020/11/16 15:49
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {
            subject.login(token);
            subject.getSession().setAttribute("aaa", "bbb");
            return CommonResult.success();
        } catch (UnknownAccountException e) {
            return CommonResult.error(StatusCode.ERROR, "用户不存在");
        } catch (AuthenticationException e) {
            CommonResult commonResult = CommonResult.error(StatusCode.ERROR, "用户名或密码错误");
            commonResult.setData(e.getMessage());
            return commonResult;
        }
    }

    @PostMapping("/register")
    public CommonResult register(@Validated @RequestBody User user) {
        if (userService.getUserByName(user.getUsername()) != null) {
            throw new BusinessException("用户名已存在！");
        }

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String password = new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(salt), 1).toString();
        user.setSalt(salt);
        user.setPassword(password);

        userService.addUser(user);

        return CommonResult.success();
    }
}

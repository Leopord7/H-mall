package com.leopord.hmall.controller;

import com.leopord.hmall.api.CommonResult;
import com.leopord.hmall.api.StatusCode;
import com.leopord.hmall.entity.User;
import com.leopord.hmall.exception.BusinessException;
import com.leopord.hmall.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
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
        try {
            userService.login(user);
            return CommonResult.success();
        } catch (UnknownAccountException e) {
            return CommonResult.error(StatusCode.ERROR, "用户不存在");
        } catch (AuthenticationException e) {
            return CommonResult.error(StatusCode.ERROR, "用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public CommonResult register(@Validated @RequestBody User user) {
        if (userService.getUserByName(user.getUsername()) != null) {
            throw new BusinessException("用户名已存在！");
        }
        userService.register(user);
        return CommonResult.success();
    }
}

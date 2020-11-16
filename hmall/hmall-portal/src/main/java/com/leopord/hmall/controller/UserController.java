package com.leopord.hmall.controller;

import com.leopord.hmall.api.CommonResult;
import com.leopord.hmall.api.StatusCode;
import com.leopord.hmall.entity.User;
import com.leopord.hmall.service.UserService;
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
        User search = userService.getUserByName(user.getUsername());
        if (search != null && search.equals(user)) {
            return CommonResult.success();
        }
        return CommonResult.error(StatusCode.ERROR, "用户名或密码错误");
    }


}

package com.leopord.hmall.controller;

import com.leopord.hmall.api.CommonResult;
import com.leopord.hmall.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/create")
    public CommonResult createOrder(
                                    @RequestParam("amount")Integer amount) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        System.out.println("入参：" +  amount);
        //orderService.creatOrder(username, productId, amount);

        return CommonResult.success();
    }
}

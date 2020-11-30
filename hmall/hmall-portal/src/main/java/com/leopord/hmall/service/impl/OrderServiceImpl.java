package com.leopord.hmall.service.impl;

import com.leopord.hmall.entity.Order;
import com.leopord.hmall.service.OrderService;
import com.leopord.hmall.service.UserService;

import javax.annotation.Resource;

public class OrderServiceImpl implements OrderService {

    @Resource
    private UserService userService;



    @Override
    public Order creatOrder(String username, int productId, int amount) {
        return null;
    }
}

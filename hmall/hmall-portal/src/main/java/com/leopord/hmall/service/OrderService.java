package com.leopord.hmall.service;

import com.leopord.hmall.entity.Order;

public interface OrderService {

    Order creatOrder(String username, Integer productId, Integer amount);
}

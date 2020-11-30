package com.leopord.hmall.service;

import com.leopord.hmall.entity.Order;

public interface OrderService {

    Order creatOrder(String username, int productId, int amount);
}

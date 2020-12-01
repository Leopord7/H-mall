package com.leopord.hmall.service;

public interface ProductStockService {

    boolean decreaseStock(Integer productId, Integer amount);
}

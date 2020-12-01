package com.leopord.hmall.service.impl;

import com.leopord.hmall.dao.ProductStockMapper;
import com.leopord.hmall.service.ProductStockService;

import javax.annotation.Resource;

public class ProductStockServiceImpl implements ProductStockService {

    @Resource
    private ProductStockMapper productStockMapper;

    @Override
    public boolean decreaseStock(Integer productId, Integer amount) {
        return productStockMapper.decreaseStock(productId, amount) > 0;
    }
}

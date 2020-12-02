package com.leopord.hmall.dao;

import com.leopord.hmall.entity.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> getAllProducts();

    int increaseSale(Integer productId, Integer amount);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}
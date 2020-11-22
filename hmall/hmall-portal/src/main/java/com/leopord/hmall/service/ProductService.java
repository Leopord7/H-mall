package com.leopord.hmall.service;

import com.leopord.hmall.domain.ProductDetail;
import com.leopord.hmall.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    ProductDetail getProductDetail(Integer id);
}

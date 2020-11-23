package com.leopord.hmall.controller;

import com.leopord.hmall.api.CommonResult;
import com.leopord.hmall.domain.ProductDetail;
import com.leopord.hmall.entity.Product;
import com.leopord.hmall.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: Leopord
 * @time: 2020/11/17 16:19
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public CommonResult getProduct() {
        List<Product> products = productService.getAllProducts();
        return CommonResult.success(products);
    }

    @GetMapping("/detail/{id}")
    public CommonResult getProductDetail(@PathVariable("id") Integer id) {
        ProductDetail productDetail = productService.getProductDetail(id);
        return CommonResult.success(productDetail);
    }

}

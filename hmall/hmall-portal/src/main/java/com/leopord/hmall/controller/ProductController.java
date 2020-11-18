package com.leopord.hmall.controller;

import com.leopord.hmall.api.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Leopord
 * @time: 2020/11/17 16:19
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @PostMapping("/id")
    public CommonResult getProduct() {

        return CommonResult.success();
    }

}

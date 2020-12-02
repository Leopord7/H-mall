package com.leopord.hmall.service.impl;

import com.leopord.hmall.dao.ProductMapper;
import com.leopord.hmall.dao.ProductStockMapper;
import com.leopord.hmall.dao.PromotionMapper;
import com.leopord.hmall.domain.ProductDetail;
import com.leopord.hmall.entity.Product;
import com.leopord.hmall.entity.ProductStock;
import com.leopord.hmall.entity.Promotion;
import com.leopord.hmall.service.ProductService;
import com.leopord.hmall.util.LocalDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductStockMapper productStockMapper;

    @Resource
    private PromotionMapper promotionMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public void increaseSale(Integer productId, Integer amount) {
        productMapper.increaseSale(productId, amount);
    }

    @Override
    public ProductDetail getProductDetail(Integer id) {

        ProductDetail productDetail = new ProductDetail();

        Product product = productMapper.selectByPrimaryKey(id);
        ProductStock stock = productStockMapper.selectByProductId(id);

        BeanUtils.copyProperties(product, productDetail);
        productDetail.setStock(stock.getStock());

        productDetail.setPromotionStatus(0);
        if (product.getPromotionId() != -1) {
            int mid = product.getPromotionId();
            Promotion promotion = promotionMapper.selectByPrimaryKey(mid);
            LocalDateTime startTime = LocalDateUtil.dateToLocalDateTime(promotion.getStartTime());
            LocalDateTime endTime = LocalDateUtil.dateToLocalDateTime(promotion.getEndTime());
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(endTime)) {
                //该活动已过期，标记活动位为-1
                Product uProduct = new Product();
                uProduct.setId(product.getId());
                uProduct.setPromotionId(-1);
                productMapper.updateByPrimaryKeySelective(uProduct);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                productDetail.setPromotionStatus(1);
                productDetail.setPromotionTitle(promotion.getPromotionTitle());
                productDetail.setStartTime(startTime.format(formatter));
                productDetail.setEndTime(endTime.format(formatter));
            }
        }
        return productDetail;
    }
}

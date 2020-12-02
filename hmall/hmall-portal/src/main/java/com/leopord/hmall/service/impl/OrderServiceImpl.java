package com.leopord.hmall.service.impl;

import com.leopord.hmall.api.StatusCode;
import com.leopord.hmall.dao.OrderMapper;
import com.leopord.hmall.dao.ProductMapper;
import com.leopord.hmall.dao.SequenceMapper;
import com.leopord.hmall.entity.Order;
import com.leopord.hmall.entity.Product;
import com.leopord.hmall.entity.Sequence;
import com.leopord.hmall.exception.BusinessException;
import com.leopord.hmall.service.OrderService;
import com.leopord.hmall.service.ProductStockService;
import com.leopord.hmall.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private UserService userService;

    @Resource
    private ProductStockService productStockService;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private SequenceMapper sequenceMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public void creatOrder(String username, Integer productId, Integer amount) {
        if (userService.getUserByName(username) == null) {
            throw new BusinessException(StatusCode.USER_NOT_EXISTED);
        }

        if (amount < 1 || amount > 10) {
            throw new BusinessException("违规的购买数量！");
        }

        boolean stockResult = productStockService.decreaseStock(productId, amount);
        if (!stockResult) {
            throw new BusinessException("库存不足！");
        }

        Product product = productMapper.selectByPrimaryKey(productId);
        BigDecimal price = (product.getPromotionId() != -1)?product.getPromotionPrice() : product.getPrice();

        Order order = new Order();
        order.setId(generateOrderId());
        order.setProductId(productId);
        order.setUsername(username);
        order.setPrice(price);
        order.setAmount(amount);
        order.setOrderPrice(price.multiply(new BigDecimal(amount)));

        orderMapper.insertSelective(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderId() {
        //时间戳
        long timeStamp = System.currentTimeMillis();
        String timePart = timeStamp + "000";
        //递增序列
        Sequence sequence = sequenceMapper.getSequenceByName("order_info");
        int value = sequence.getCurrentValue();
        StringBuilder builder = new StringBuilder(value);
        for (int i = 0; i < 8 - builder.length(); i++) {
            builder.append(0);
        }
        if (value == sequence.getMax() - 1) {
            sequence.setCurrentValue(0);
            sequenceMapper.updateByPrimaryKey(sequence);
        }
        String sequencePart = builder.toString();
        //随机数序列
        String randomPart = String.valueOf(ThreadLocalRandom.current().nextInt(10000000, 99999999));
        return timePart + sequencePart + randomPart;
    }

}

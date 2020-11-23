package com.leopord.hmall.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer sales;

    private Integer promotionId;

    private BigDecimal promotionPrice;

    private Integer stock;

    private Integer promotionStatus;

    private String promotionTitle;

    private String startTime;

    private String endTime;
}
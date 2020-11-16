package com.leopord.hmall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: mybatis配置
 * @author: Leopord
 * @time: 2020/11/16 13:51
 */

@Configuration
@MapperScan({"com.leopord.hmall.dao"})
public class MybatisConfig {

}

package com.wdf.tp.rest;

import com.wdf.tp.common.config.LocaleConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 项目启动类
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 13:41
 * @since 1.0.0
 */
@ComponentScan(basePackages = {"com.wdf.tp"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = LocaleConfig.class))
@MapperScan(basePackages = {"com.wdf.tp.**.model.dao", "com.wdf.tp.**.model.custom"})
@SpringBootApplication
public class TemplateRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateRestApplication.class, args);
    }
}

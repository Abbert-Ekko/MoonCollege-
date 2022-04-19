package com.app.eduService.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@MapperScan("com.mybatis.mapper.*")
public class MPConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // 这里就是分页插件的配置了，由于由@Configuration注解，所以是自动注入的，自动应用
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
//交给spring一个bean，这个bean作用是mybatisPlus的分页拦截器

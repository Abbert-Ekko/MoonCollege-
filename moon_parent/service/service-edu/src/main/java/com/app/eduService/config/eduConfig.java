package com.app.eduService.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.app.eduService.mapper")
@Configuration
public class eduConfig {


}

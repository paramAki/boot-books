package com.param.bootbooks.config;

import com.param.bootbooks.intecerptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器的放行路径
 * @author zhoujingyu（976944083@qq.com）
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/", "/css/**", "/fonts/**", "/images/**", "/js/**", "/static/**");
    }
}

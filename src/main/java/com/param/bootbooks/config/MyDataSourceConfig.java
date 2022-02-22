package com.param.bootbooks.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class MyDataSourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){

        return new DruidDataSource();
    }

    /**
     * 配置的druid的监控页
     * @return
     */
//    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();

        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");

        return registrationBean;
    }
}

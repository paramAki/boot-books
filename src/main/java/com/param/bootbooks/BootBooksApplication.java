package com.param.bootbooks;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 周靖宇（976944083@qq.com）
 */
@SpringBootApplication
//@MapperScan("com.param.bootbooks.mapper")
public class BootBooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBooksApplication.class, args);
    }

}

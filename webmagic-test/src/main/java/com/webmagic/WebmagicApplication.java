package com.webmagic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 * <b>WebmagicApplication</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/13
 */
@MapperScan("com.webmagic.mapper")
@SpringBootApplication
public class WebmagicApplication {

    public static void main(String[] args){
        SpringApplication.run(WebmagicApplication.class,args);
    }
}

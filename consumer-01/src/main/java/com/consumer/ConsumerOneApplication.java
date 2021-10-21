package com.consumer;

import com.consumer.mq.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * <p>
 * <b>ConsumerOneApplication</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/24
 */

@EnableBinding(MySink.class)
@SpringBootApplication
public class ConsumerOneApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConsumerOneApplication.class,args);
    }
}

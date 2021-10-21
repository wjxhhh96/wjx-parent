package com.consumer;

import com.consumer.mq.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * <p>
 * <b>ConsumerApplication</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/13
 */
@EnableBinding(MySource.class)
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}

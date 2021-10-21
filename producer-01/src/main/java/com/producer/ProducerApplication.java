package com.producer;

import com.producer.mq.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * <p>
 * <b>ProducrApplication</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/18
 */
@EnableBinding(MySource.class)
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class,args);
    }
}

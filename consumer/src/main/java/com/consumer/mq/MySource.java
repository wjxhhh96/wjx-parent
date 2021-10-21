package com.consumer.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.annotation.Router;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * <p>
 * <b>MySource</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/13
 */
public interface MySource {

    String TEST_ONE_OUTPUT = "testOne-output";



    @Output(MySource.TEST_ONE_OUTPUT)
    MessageChannel testOneOutput();




}

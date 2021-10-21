package com.consumer.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * <b>ConsumerOne</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/24
 */
@Slf4j
@Component
public class ConsumerOne {

    @StreamListener(value = MySink.Test_ONE_IN_PUT,condition = "headers['rocketmq_TAGS']=='two'")
    public void subscribeMessage(@Payload MeaageEntity meaageEntity, @Headers Map header){
        log.info("consumer-01 接受到消息");
        log.info("请求内容：{}", JSONObject.toJSONString(meaageEntity));
        log.info("请求头：{}",JSONObject.toJSONString(header));
    }
}

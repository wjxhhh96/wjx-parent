package com.consumer.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * <b>ConsumerTransactional</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/30
 */
@Slf4j
@Component
public class ConsumerTransactional {


    @StreamListener(value = MySink.TEST_TRANSACTIONAL_OUT_PUT)
    public void subscribeTransactionalMessage(@Payload MeaageEntity meaageEntity, @Headers Map header){
        log.info("consumer-lay 接受到消息:时间:{}", LocalDateTime.now());
        log.info("请求内容：{}", JSONObject.toJSONString(meaageEntity));
        log.info("请求头：{}",JSONObject.toJSONString(header));
    }


}

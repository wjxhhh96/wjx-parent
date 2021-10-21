package com.producer.controller;

import com.producer.mq.MessageEntity;
import com.producer.mq.MySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * <p>
 * <b>SendMessageController</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/24
 */
@Slf4j
@RestController
public class SendMessageController {

    @Resource
    private MySource mySource;


    @PostMapping("/send/message")
    public String sendMessage(@RequestBody String message){
        MessageEntity entity  = new MessageEntity();
        entity.setId(new Random().nextInt());
        entity.setMessage(message);
        entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Message<MessageEntity> entityMessage = MessageBuilder.withPayload(entity).setHeader(RocketMQHeaders.TAGS, "one").setHeader(RocketMQHeaders.KEYS,
                "producer-01").build();

        mySource.testOneOutPut().send(entityMessage);
        return "success";
    }





    @PostMapping("/send/lay/message")
    public String sendLayMessage(@RequestBody String message){
        int i = new Random().nextInt();
        MessageEntity entity  = new MessageEntity();
        entity.setId(i);
        entity.setMessage(message);
        entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        Message<MessageEntity> entityMessage = MessageBuilder.withPayload(entity).setHeader(RocketMQHeaders.TAGS,
                "two").setHeader(RocketMQHeaders.KEYS,
                i).setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL,3).build();
        mySource.testOneOutPut().send(entityMessage);
        log.info("消息发送成功：{}",LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return "success";
    }



    @PostMapping("/send/transactional/message")
    public String sendTransactionalMessage(@RequestBody String message){
        int i = new Random().nextInt();
        MessageEntity entity  = new MessageEntity();
        entity.setId(i);
        entity.setMessage(message);
        entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        Message<MessageEntity> entityMessage = MessageBuilder.withPayload(entity)
                .setHeader(RocketMQHeaders.TAGS, "one").setHeader(RocketMQHeaders.KEYS, i)
                .setHeader(RocketMQHeaders.TRANSACTION_ID,i)
                .build();
        mySource.testTransactionalOutPut().send(entityMessage);

        return "success";
    }





    @PostMapping("/send/sharding/message")
    public String sendShardingMessage(@RequestBody String message){
        //发送  id 相同 根据 properties 定义的shardingkey 相同
        new Thread(() -> {
            int i = 1;
            for(int j=0;j<3;j++){
                MessageEntity entity = new MessageEntity();
                entity.setId(i);
                entity.setMessage(message);
                entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

                Message<MessageEntity> entityMessage = MessageBuilder.withPayload(entity)
                        .setHeader(RocketMQHeaders.TAGS, "one")
                        .setHeader(RocketMQHeaders.KEYS, j)
                        .build();
                mySource.testShardingOutPut().send(entityMessage);
                log.info("消息发送queue:{},key:{}",i,j);
            }

        }).start();
        new Thread(() -> {
            int i = 2;
            for(int j=0;j<3;j++){
                MessageEntity entity = new MessageEntity();
                entity.setId(i);
                entity.setMessage(message);
                entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

                Message<MessageEntity> entityMessage = MessageBuilder.withPayload(entity)
                        .setHeader(RocketMQHeaders.TAGS, "one")
                        .setHeader(RocketMQHeaders.KEYS, j)
                        .build();
                mySource.testShardingOutPut().send(entityMessage);
                log.info("消息发送queue:{},key:{}",i,j);
            }
        }).start();
        return "success";
    }




}

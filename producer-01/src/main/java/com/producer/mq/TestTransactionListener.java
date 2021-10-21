package com.producer.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * <p>
 * <b>TestTransactionListener</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/30
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "TEST-TRANSACTIONAL-TOPIC")
public class TestTransactionListener implements RocketMQLocalTransactionListener {


    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        //上传半消息成功后 就会掉这里，这里可以执行本地事务，如果本地事务成功就传commit
        log.info("prepare is ok");
        log.info("message : {}", JSONObject.toJSONString(message));
        log.info("Object : {}",JSONObject.toJSONString(o));
        //如果是unkonw 状态的话 mq 会调用checkLocalTransaction方法去查询事务状态
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //如果半消息一直是unkonw状态的话，mq有个定时任务会默认来调用这个方法进行状态回查。最好能有一张表是用来存对应的事务关系的
        log.info("callback message");
        log.info("message:{}",JSONObject.toJSONString(message));
        return RocketMQLocalTransactionState.COMMIT;
    }
}

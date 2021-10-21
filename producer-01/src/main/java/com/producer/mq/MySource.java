package com.producer.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * <p>
 * <b>MySource</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/18
 */
public interface MySource {

    String Test_ONE_OUT_PUT = "test_one_out_put";

    String TEST_LAY_OUT_PUT = "test_lay_out_put";

    String TEST_TRANSACTIONAL_OUT_PUT = "test_transactional_out_put";

    String TEST_SHARDING_OUT_PUT = "test_sharding_out_put";


    /**
     * 普通消息
     * @return
     */
    @Output(MySource.Test_ONE_OUT_PUT)
    MessageChannel testOneOutPut();

    /**
     * 延时消息
     * @return
     */
    @Output(MySource.TEST_LAY_OUT_PUT)
    MessageChannel testLayOutPut();


    /**
     * 事务消息
     * @return
     */
    @Output(MySource.TEST_TRANSACTIONAL_OUT_PUT)
    MessageChannel testTransactionalOutPut();

    /**
     * 消息分区
     * @return
     */
    @Output(MySource.TEST_SHARDING_OUT_PUT)
    MessageChannel testShardingOutPut();

}

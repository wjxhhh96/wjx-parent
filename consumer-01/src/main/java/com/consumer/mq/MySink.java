package com.consumer.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * <p>
 * <b>MySink</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/19
 */
public interface MySink {


    String Test_ONE_IN_PUT = "test_one_in_put";

    String TEST_LAY_IN_PUT = "test_lay_in_put";

    String TEST_TRANSACTIONAL_OUT_PUT = "test_transactional_out_put";

    String TEST_SHARDING_IN_PUT = "test_sharding_in_put";

    @Input(Test_ONE_IN_PUT)
    SubscribableChannel getOneInput();


    @Input(TEST_LAY_IN_PUT)
    SubscribableChannel getLayInput();


    @Input(TEST_TRANSACTIONAL_OUT_PUT)
    SubscribableChannel getTransactional();


    @Input(TEST_SHARDING_IN_PUT)
    SubscribableChannel getSharding();

}

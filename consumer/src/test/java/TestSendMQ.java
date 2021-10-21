import com.consumer.ConsumerApplication;
import com.consumer.mq.MessageEntity;
import com.consumer.mq.MySource;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * <p>
 * <b>TestSendMQ</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/8/13
 */

@SpringBootTest(classes = ConsumerApplication.class)
public class TestSendMQ {


    @Autowired
    private MySource mySource;

    @Test
    public void testSendMessage(){
        MessageEntity entity = new MessageEntity();
        entity.setId(new Random().nextInt());
        entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        Message<MessageEntity> message =
                MessageBuilder.withPayload(entity).setHeader(RocketMQHeaders.TAGS,"one").setHeader(RocketMQHeaders.KEYS,"123456").build();

        mySource.testOneOutput().send(message);
    }
}

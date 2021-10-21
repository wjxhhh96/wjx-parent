import com.alibaba.fastjson.JSONObject;
import com.producer.ProducerApplication;
import com.producer.mq.MessageEntity;
import com.producer.mq.MySource;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
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
 * @since 2021/8/18
 */
@SpringBootTest(classes = ProducerApplication.class)
public class TestSendMQ {

    @Autowired
    private MySource mySource;



    @Test
    public void testSendMessage(){
        MessageEntity entity = new MessageEntity();
        entity.setId(new Random().nextInt());
        entity.setDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));


        Message<MessageEntity> message = MessageBuilder.withPayload(entity).setHeader(RocketMQHeaders.TAGS, "one").setHeader(RocketMQHeaders.KEYS,
                "producer-01").build();


        mySource.testOneOutPut().send(message);
        System.out.println("success");

    }


    @Test
    public void testTwo(){
        try{
            A();
        }catch (Exception e){
           /* StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            e.printStackTrace(pw);
            String stackTraceString = sw.getBuffer().toString();*/

            StringBuffer buffer = new StringBuffer();

            StackTraceElement[] stackTrace = e.getStackTrace();

            for(int i =0; i<stackTrace.length;i++){
                buffer.append(stackTrace[i]);
                buffer.append("\n");
                if(i == 2){
                    break;
                }
            }

            System.out.println(buffer.toString());
        }
    }




    public static void A(){
        AB();
    }


    public static void AB(){
        AC();
    }


    public static void AC(){
        AD();
    }

    public static void AD(){
        int i = 1/0;
    }




    @Test
    public void testThree() throws IllegalAccessException {
        MessageEntity entity = new MessageEntity();
    /*    entity.setId(11);
        entity.setMessage("dddd");
        entity.setDate("d");*/
        Field[] fields = MessageEntity.class.getDeclaredFields();
        for (Field item : fields) {
            item.setAccessible(true);
            System.out.println(item);
            Object o = item.get(entity);
            System.out.println(o);
        }
    }









}

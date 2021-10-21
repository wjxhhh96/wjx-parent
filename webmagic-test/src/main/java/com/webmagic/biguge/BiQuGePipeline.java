package com.webmagic.biguge;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * <p>
 * <b>BiQuGePipeline</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/14
 */
@Component
@Slf4j
public class BiQuGePipeline implements Pipeline {



    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("进入自定义输出逻辑");
        log.info("爬取的结果：{}", JSONObject.toJSONString(resultItems));
    }
}

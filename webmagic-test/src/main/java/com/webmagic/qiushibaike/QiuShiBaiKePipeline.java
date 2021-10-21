package com.webmagic.qiushibaike;

import com.alibaba.fastjson.JSONObject;
import com.webmagic.entity.ArticleData;
import com.webmagic.mapper.ArticleDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * <b>QiuShiBaiKePipeline</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/14
 */
@Slf4j
@Component
public class QiuShiBaiKePipeline implements Pipeline {
    @Autowired
    private ArticleDataMapper articleDataMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("进入自定义输出逻辑");
        log.info("爬取的结果：{}", JSONObject.toJSONString(resultItems));
        ArticleData articleData = new ArticleData();
        articleData.setTitle(resultItems.get(ContentKey.TITLE.name()));
        articleData.setAuthor(resultItems.get(ContentKey.AUTHOR.name()));
        articleData.setContent(resultItems.get(ContentKey.CONTENT.name()));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        articleData.setContentTime(LocalDateTime.parse(resultItems.get(ContentKey.TIME.name()).toString().trim(),df));
        articleData.setContentHot(Long.valueOf(resultItems.get(ContentKey.HOT_COUNT.name())));
        articleData.setCreateTime(LocalDateTime.now());
        articleData.setUrl(resultItems.get(ContentKey.URL.name()));
        if (StringUtils.isBlank(articleData.getContent())){
            return;
        }
        articleDataMapper.insert(articleData);
    }
}

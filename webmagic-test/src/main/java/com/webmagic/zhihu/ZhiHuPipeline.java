package com.webmagic.zhihu;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.webmagic.entity.ArticleData;
import com.webmagic.enumpack.ResourceEnum;
import com.webmagic.mapper.ArticleDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * <b>ZhiHuPipeline</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/28
 */
@Slf4j
@Component
public class ZhiHuPipeline implements Pipeline {

    @Autowired
    private ArticleDataMapper articleDataMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("进入知乎数据的输出逻辑");
        log.info("爬取的结果：{}", JSONObject.toJSONString(resultItems));

        ArticleData articleData = new ArticleData();
        if(StringUtils.isEmpty(resultItems.get(ContentKey.CONTENT.name()))){
            return;
        }
        articleData.setTitle(resultItems.get(ContentKey.TITLE.name()));
        articleData.setAuthor(resultItems.get(ContentKey.AUTHOR.name()));
        articleData.setContent(resultItems.get(ContentKey.CONTENT.name()));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String time = resultItems.get(ContentKey.TIME.name()).toString();
        String timeOld = time.substring(time.indexOf("于") + 1, time.length()).trim();
        LocalDateTime day =
                LocalDate.parse("2021-" + timeOld, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        articleData.setContentTime(LocalDateTime.parse(day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if(null != resultItems.get(ContentKey.HOT_COUNT.name())){
            String hot = resultItems.get(ContentKey.HOT_COUNT.name()) .toString();
            articleData.setContentHot(Long.valueOf(hot.substring(0,hot.indexOf("人")).replace(",","").trim()));
        }
        articleData.setCreateTime(LocalDateTime.now());
        String url = resultItems.get(ContentKey.URL.name()).toString();
        articleData.setUrl(url);
        articleData.setResource(ResourceEnum.ZHI_HU.name());
        articleData.setResourceId(Long.valueOf(url.substring(url.lastIndexOf("/")+1,url.length()).trim()));
        articleData.setImgs(resultItems.get(ContentKey.IMGS.name()));

        QueryWrapper<ArticleData> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(ArticleData::getResourceId,articleData.getResourceId())
                .eq(ArticleData::getResource,
                ResourceEnum.ZHI_HU.name());
        List<ArticleData> data = articleDataMapper.selectList(wrapper);
        if(!CollectionUtils.isEmpty(data)){
            return;
        }
        articleDataMapper.insert(articleData);
    }
}

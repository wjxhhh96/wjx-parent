package com.webmagic.qiushibaike;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * <b>QiuShiBaiKeProcess</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/14
 */
@Slf4j
@Component
public class QiuShiBaiKeProcess implements PageProcessor {


    public static final String FIRST_URL = "http://www\\.qiushibaike\\.com/\\w+";
    public static final String HELP_URL = "/article/\\d+";
    public static final String TARGET_URL = "/\\d+/\\d+/\\d+\\.html/";
    /**
     1.设置网站相关配置
     2.重试次数和抓取间隔
     3.可以加入浏览器配置setUserAgent
     */
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(10000);


    @Override
    public Site getSite() {
        return site;
    }


    @Override
    public void process(Page page) {
        log.info("开始爬虫");
        log.info("该次的Ip:{}",page.getHtml().css("center", "text").get());
        if(page.getUrl().regex(FIRST_URL).match()){
            List<String> urls = page.getHtml().links().regex(HELP_URL).all();
            Set<String> collectUrl = urls.stream().collect(Collectors.toSet());
            List<String> duplicationUrls = collectUrl.stream().collect(Collectors.toList());
            page.addTargetRequests(duplicationUrls);
            page.putField(ContentKey.TITLE.name(),page.getHtml().xpath("//h1[@class='article-title']/text()").get());
            if(page.getResultItems().get(ContentKey.TITLE
            .name()) == null){
                page.setSkip(Boolean.TRUE);
            }
            page.putField(ContentKey.TIME.name(),page.getHtml().xpath("//span[@class='stats-time']/text()").get());
            page.putField(ContentKey.HOT_COUNT.name(),
                    page.getHtml().xpath("//span[@class='stats-vote']/i/text()").get());
            page.putField(ContentKey.AUTHOR.name(),page.getHtml().xpath("//span[@class='side-user-name']/text()").get());
            page.putField(ContentKey.CONTENT.name(),page.getHtml().xpath("//div[@class='word']/div/text()").get());
            if(page.getResultItems().get(ContentKey.CONTENT
                    .name()) == null){
                page.setSkip(Boolean.TRUE);
            }
            page.putField(ContentKey.URL.name(),page.getUrl().get());
        }

        log.info("获取到的内容：{}", JSONObject.toJSONString(page.getResultItems()));
        log.info("结束爬虫");

    }


    public static void main(String[] args) {
        Spider.create(new QiuShiBaiKeProcess()).addUrl("http://www.qiushibaike.com/text/")
                .addPipeline(new QiuShiBaiKePipeline())
                .thread(1).run();
    }

}

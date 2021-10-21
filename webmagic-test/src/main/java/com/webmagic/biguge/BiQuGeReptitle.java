package com.webmagic.biguge;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * <p>
 * <b>BiQuGeReptitle</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/13
 */
@Slf4j
public class BiQuGeReptitle implements PageProcessor {

    //regex of URL:http://www.xbiquge.la/
    public static final String FIRST_URL = "http://www\\.xbiquge\\.la/\\w+";
    public static final String HELP_URL = "/\\d+/\\d+/";
    public static final String TARGET_URL = "/\\d+/\\d+/\\d+\\.html/";

    /**
     1.设置网站相关配置
     2.重试次数和抓取间隔
     3.可以加入浏览器配置setUserAgent
     */
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(1000);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        log.info("开始爬虫");
        if(page.getUrl().regex(FIRST_URL).match()){
            List<String> urls = page.getHtml().links().regex(HELP_URL).all();
            page.addTargetRequests(urls);
            //标题
            page.putField("title",page.getHtml().xpath("//div[@id='info']/h1/text()").get());
            if(page.getResultItems().get("title") == null){
                page.setSkip(true);
            }

            //作者
            page.putField("author",page.getHtml().xpath("//div[@id='info']/p/text()").get());
            //简介
            page.putField("info",page.getHtml().xpath("//div[@id='intro']/p[2]/text()").get());
            //首图
            page.putField("image",page.getHtml().xpath("//div[@id='fming']/img").get());


            if(page.getUrl().regex(HELP_URL).match()){
                log.info("进入爬取章节逻辑");
                List<String> links = page.getHtml().links().regex(TARGET_URL).all();
                page.addTargetRequests(links);

                //章节
                page.putField("chapter",page.getHtml().xpath("//div[@class='bookname']/h1/text()").get());

                //内容
                page.putField("content",page.getHtml().xpath("//div[@id='content']/text()").get());

            }

            log.info("结束爬虫");
        }
    }


    public static void main(String[] args) {
        Spider.create(new BiQuGeReptitle()).addUrl("http://www.xbiquge.la/xiaoshuodaquan/")
                //输出到控制台
                //.addPipeline(new ConsolePipeline())
                .addPipeline(new BiQuGePipeline())
                .thread(1)
                .run();
    }
}

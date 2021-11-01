package com.webmagic.zhihu;

import com.alibaba.fastjson.JSONObject;
import com.webmagic.enumpack.ResourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * <b>ZhiHuProcess</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/27
 */
@Slf4j
@Component
public class ZhiHuProcess implements PageProcessor {

    public static final String LIST = "https://www.zhihu.com/\\w+";
    public static final String TARGET_URL = "//zhuanlan.zhihu.com/p/\\d+";


    /**
     1.设置网站相关配置
     2.重试次数和抓取间隔
     3.可以加入浏览器配置setUserAgent
     */
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(10000);


    @Override
    public void process(Page page) {
        if (page.getUrl().regex(LIST).match()){
            List<String> urls = page.getHtml().links().regex(TARGET_URL).all();
            page.addTargetRequests(urls);
        }else {
            page.putField(ContentKey.TITLE.name(),page.getHtml().xpath("//h1[@class='Post-Title']/text()").get());
            page.putField(ContentKey.TIME.name(),page.getHtml().xpath("//div[@class='ContentItem-time']/text()").get());
            page.putField(ContentKey.AUTHOR.name(),page.getHtml().xpath("//div[@class='AuthorInfo-content']/div/span/div/div/a/text()").get());
            //jsoup 进行转换
            Document document = Jsoup.parse(page.getHtml().xpath("//div[@class='RichText ztext " +
                    "Post-RichText css-hnrfcf']").get());
            page.putField(ContentKey.CONTENT.name(), document.text());
            Elements img = document.getElementsByTag("img");
            Set<String> imgsUrl = new HashSet<>();
            for (Element itemImg : img) {
                String src = itemImg.attr("data-actualsrc");
                imgsUrl.add(src);
            }
            page.putField(ContentKey.IMGS.name(), JSONObject.toJSONString(imgsUrl));
            page.putField(ContentKey.HOT_COUNT.name(),page.getHtml().xpath("//button[@class='Button Button--plain']/text()").get());
            page.putField(ContentKey.RESOURCE.name(), ResourceEnum.ZHI_HU.name());
            page.putField(ContentKey.URL.name(),page.getUrl().get());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


}

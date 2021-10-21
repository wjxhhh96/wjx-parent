import com.webmagic.WebmagicApplication;
import com.webmagic.qiushibaike.ChromeDownloader;
import com.webmagic.qiushibaike.QiuShiBaiKePipeline;
import com.webmagic.qiushibaike.QiuShiBaiKeProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * <p>
 * <b>SpiderTest</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/15
 */
@SpringBootTest(classes = WebmagicApplication.class)
@RunWith(SpringRunner.class)
public class SpiderTest {
    @Autowired
    private ChromeDownloader chromeDownloader;
    @Autowired
    private QiuShiBaiKeProcess qiuShiBaiKeProcess;
    @Autowired
    private QiuShiBaiKePipeline qiuShiBaiKePipeline;


    @Test
    public void qiushidaquan() {
        //创建下载器
     /*   HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("60.191.11.249",3128 )
        ));*/
        Spider.create(qiuShiBaiKeProcess).addUrl("http://www.qiushibaike.com/text/")
                .addPipeline(qiuShiBaiKePipeline)
                .setDownloader(chromeDownloader)
                // 设置布隆过滤器去重操作（默认使用HashSet来进行去重，占用内存较大；使用BloomFilter来进行去重，占用内存较小，但是可能漏抓页面）
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
               // .setDownloader(httpClientDownloader)
                .thread(1).run();
    }

}

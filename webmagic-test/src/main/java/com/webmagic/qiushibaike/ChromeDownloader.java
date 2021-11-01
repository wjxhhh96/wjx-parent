package com.webmagic.qiushibaike;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * <p>
 * <b>ChromeDownloader</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/15
 */
@Component
public class ChromeDownloader implements Downloader {

    private RemoteWebDriver driver;


    public ChromeDownloader() {
        //第一个参数是使用哪种浏览器驱动(webdriver.chrome.driver 会被浏览器参数标记，有些网站防爬就是js识别这个字段进行反爬)
        //第二个参数是浏览器驱动的地址
        System.setProperty("webdriver.chrome.driver","F:\\soft\\chromedriver\\chromedriver.exe");


        //创建浏览器参数对象
        ChromeOptions chromeOptions = new ChromeOptions();

        // 设置为 headless 模式,上课演示,或者学习不要打开
        // chromeOptions.addArguments("--headless");

        chromeOptions.addArguments("--window-size=1280,700");
        //这个参数加上是在本地打开浏览器，让webdriver 去控制打开的这个浏览器（chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\selenum\AutomationProfile"），绕过反爬
        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        //创建驱动
        this.driver = new ChromeDriver(chromeOptions);
    }

    @Override
    public Page download(Request request, Task task) {
        try{
            driver.get(request.getUrl());
            Thread.sleep(1000);

            //无论是搜索页还是详情页，都滚动到页面底部，所有该加载的资源都加载
            //需要滚动到页面的底部，获取完整的页面数据
        /*    driver.executeScript("window.scrollTo(0,document.body.scrollHeight)");

            //由于有些网页是下拉后加载数据，所以要在这里判断是不是最底部
           Integer old = (Integer) driver.executeScript("return document.body.scrollHeight");

            driver.executeScript("window.scrollTo(0,document.body.scrollHeight)");*/
            pullDown();

            Thread.sleep(1000);

            //获取页面对象
            Page page = createPage(request.getUrl(), driver.getPageSource());

            return page;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public Boolean pullDown() throws InterruptedException {
        System.out.println("开始下滑页面");
        //无论是搜索页还是详情页，都滚动到页面底部，所有该加载的资源都加载
        //需要滚动到页面的底部，获取完整的页面数据
        driver.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1000);
        //由于有些网页是下拉后加载数据，所以要在这里判断是不是最底部
        Long oldHeight = (Long) driver.executeScript("return document.body.scrollHeight");
        driver.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1000);
        Long newHeight = (Long) driver.executeScript("return document.body.scrollHeight");
        if(oldHeight.equals(newHeight)){
            return true;
        }else {
            System.out.println("旧的底部位置："+oldHeight+"新的底部位置："+newHeight);
            pullDown();
        }
        return null;
    }


    @Override
    public void setThread(int i) {

    }



    private Page createPage(String url, String content){
        Page page = new Page();
        page.setRawText(content);
        page.setUrl(new PlainText(url));
        page.setRequest(new Request(url));
        page.setDownloadSuccess(true);

        return page;
    }


}

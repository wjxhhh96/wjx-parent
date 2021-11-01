import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.minidev.json.JSONUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.PlainText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * <b>MatchTest</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/28
 */
public class MatchTest {



    /**
     * 测试正则表达式
     */
    @Test
    public void match() {
        //String one = "http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html";
        //String two = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

        String one = "https://www.zhihu.com/search?type=content&q=外卖";
        String two = "https://www.\\zhihu.\\com/search?type=content&q=\\w+";


        PlainText plainText = new PlainText(one);
        Page page = new Page();
        page.setUrl(plainText);
        boolean flag = page.getUrl().regex(two).match();
        System.out.println("结果"+flag);
    }


    /**
     *
     */
    @Test
    public void gethtml(){
        String html = "<div class=\"RichText ztext Post-RichText css-hnrfcf\" options=\"[object Object]\">\n" +
                " <p></p>\n" +
                " <p>真的！！</p>\n" +
                " <p>当然是真的！！！</p>\n" +
                " <p><b>不信的话没关系，看下去你就明白了。</b></p>\n" +
                " <p>所幸最近意外的发现了一个宝藏外卖优惠渠道！</p>\n" +
                " <p>有一说一，用来点外卖真的<b>贼便宜</b>！</p>\n" +
                " <p>先来看看我今天点的外卖吧：</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"normal\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic1.zhimg.com/v2-cd0aaebe2a00f0b61662ddfd7368419c_b.jpg\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"1979\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-cd0aaebe2a00f0b61662ddfd7368419c_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='1979'></svg>\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"1979\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-cd0aaebe2a00f0b61662ddfd7368419c_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-cd0aaebe2a00f0b61662ddfd7368419c_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>大家都点过炸鸡的吧，但是<b>十块钱都不到</b>的炸鸡有谁点到过？？</p>\n" +
                " <p>只要8.8还要什么自行车！！</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>你也想要这样的优惠吗？</p>\n" +
                " <p>那快点击下面的小卡片领取外卖红包吧~</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <div class=\"RichText-ADLinkCardContainer\">\n" +
                "  <div></div>\n" +
                "  <div class=\"ecommerce-ad-box\">\n" +
                "   <div class=\"ecommerce-ad-arrow\" data-ad-type=\"ecommerce-ad-card\">\n" +
                "    <div class=\"ecommerce-ad-arrow-img\" style=\"background-image: url(&quot;https://pic1.zhimg.com/v2-b9f0a3d5f2ec69bbe8afeec94380874f.webp&quot;);\"></div>\n" +
                "    <div class=\"ecommerce-ad-arrow-main\">\n" +
                "     <div class=\"ecommerce-ad-arrow-main-content\">\n" +
                "      <div class=\"ecommerce-ad-arrow-main-content-des\">\n" +
                "       <span>点击关注【首尝有间外卖】每天领取饿了么/美团大额外卖优惠券</span>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"ecommerce-ad-arrow-icon\">\n" +
                "      <svg width=\"8px\" height=\"14px\" viewbox=\"0 0 16 28\">\n" +
                "       <g fill=\"none\" fill-rule=\"evenodd\" transform=\"translate(-16 -10)\">\n" +
                "        <rect width=\"48\" height=\"48\"></rect>\n" +
                "        <path d=\"M16.5815824,37.3821495 C17.3536131,38.2059502 18.6110874,38.2059502 19.3860425,37.3821495 L31.4187837,25.4877594 C32.1937388,24.6639587 32.1937388,23.3329674 31.4187837,22.5091668 L19.3860425,10.6178505 C18.6110874,9.79404984 17.3565375,9.79404984 16.5815824,10.6178505 C15.8066273,11.4416511 15.8066273,12.7726424 16.578658,13.5964431 L26.6666667,24 L16.5815824,34.4035569 C15.8066273,35.2273576 15.8066273,36.5583489 16.5815824,37.3821495 Z\"></path>\n" +
                "       </g>\n" +
                "      </svg>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p><b>上面那张截图的美团红包足足有10元！</b></p>\n" +
                " <p><b>这才是为什么最终价格只有8.8元的真实原因。</b></p>\n" +
                " <p>话不多说，我直接上外卖优惠券的公众号链接，姐妹们快快点击添加就好。</p>\n" +
                " <p>提醒：低价优惠属促销抢购福利，并非100%概率获得。</p>\n" +
                " <div class=\"RichText-ADLinkCardContainer\">\n" +
                "  <div></div>\n" +
                "  <div class=\"ecommerce-ad-box\">\n" +
                "   <div class=\"ecommerce-ad-arrow\" data-ad-type=\"ecommerce-ad-card\">\n" +
                "    <div class=\"ecommerce-ad-arrow-img\" style=\"background-image: url(&quot;https://pic1.zhimg.com/v2-b9f0a3d5f2ec69bbe8afeec94380874f.webp&quot;);\"></div>\n" +
                "    <div class=\"ecommerce-ad-arrow-main\">\n" +
                "     <div class=\"ecommerce-ad-arrow-main-content\">\n" +
                "      <div class=\"ecommerce-ad-arrow-main-content-des\">\n" +
                "       <span>点击关注【首尝有间外卖】每天领取饿了么/美团大额外卖优惠券</span>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"ecommerce-ad-arrow-icon\">\n" +
                "      <svg width=\"8px\" height=\"14px\" viewbox=\"0 0 16 28\">\n" +
                "       <g fill=\"none\" fill-rule=\"evenodd\" transform=\"translate(-16 -10)\">\n" +
                "        <rect width=\"48\" height=\"48\"></rect>\n" +
                "        <path d=\"M16.5815824,37.3821495 C17.3536131,38.2059502 18.6110874,38.2059502 19.3860425,37.3821495 L31.4187837,25.4877594 C32.1937388,24.6639587 32.1937388,23.3329674 31.4187837,22.5091668 L19.3860425,10.6178505 C18.6110874,9.79404984 17.3565375,9.79404984 16.5815824,10.6178505 C15.8066273,11.4416511 15.8066273,12.7726424 16.578658,13.5964431 L26.6666667,24 L16.5815824,34.4035569 C15.8066273,35.2273576 15.8066273,36.5583489 16.5815824,37.3821495 Z\"></path>\n" +
                "       </g>\n" +
                "      </svg>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <p>关注之后，点开就是这样的界面。</p>\n" +
                " <p>虽然看起来眼花缭乱，但是只要你稍微动动小指头。</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>点击下面的链接就可以领取<b>外卖红包</b>啦~。</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic3.zhimg.com/v2-4be0398dfafc87d70879ea379a63cb4e_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-4be0398dfafc87d70879ea379a63cb4e_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-4be0398dfafc87d70879ea379a63cb4e_r.jpg\" data-actualsrc=\"https://pic3.zhimg.com/v2-4be0398dfafc87d70879ea379a63cb4e_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>看见了吗！</p>\n" +
                " <p>全是链接，点哪个都行！！</p>\n" +
                " <p>下图是点完之后的样子：</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic1.zhimg.com/v2-da4c5c1fad34978a989704012227c560_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-da4c5c1fad34978a989704012227c560_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-da4c5c1fad34978a989704012227c560_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-da4c5c1fad34978a989704012227c560_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>没有仔细数过，好像一共有四五张红包吧，各种额度的都有。</p>\n" +
                " <p>下面提示我红包已经放进app了，我一开始还不信来着，于是本着求真的想法我打开了<b>”美团外卖app”</b></p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic3.zhimg.com/v2-43a1e0271b16eadba8f12c4e1a2f3c1a_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-43a1e0271b16eadba8f12c4e1a2f3c1a_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-43a1e0271b16eadba8f12c4e1a2f3c1a_r.jpg\" data-actualsrc=\"https://pic3.zhimg.com/v2-43a1e0271b16eadba8f12c4e1a2f3c1a_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>哇！是真的耶！</p>\n" +
                " <p>在美团红包一栏中真的能看到这些在公众号上领取到的优惠券！</p>\n" +
                " <p>这简直也太香了吧！！！</p>\n" +
                " <p>我吹爆好吗！！</p>\n" +
                " <p>看！</p>\n" +
                " <p>心不心动？</p>\n" +
                " <p>想不想要？</p>\n" +
                " <p>想要就快点击链接添加啊~~</p>\n" +
                " <p>还在等什么！！</p>\n" +
                " <div class=\"RichText-ADLinkCardContainer\">\n" +
                "  <div></div>\n" +
                "  <div class=\"ecommerce-ad-box\">\n" +
                "   <div class=\"ecommerce-ad-arrow\" data-ad-type=\"ecommerce-ad-card\">\n" +
                "    <div class=\"ecommerce-ad-arrow-img\" style=\"background-image: url(&quot;https://pic1.zhimg.com/v2-b9f0a3d5f2ec69bbe8afeec94380874f.webp&quot;);\"></div>\n" +
                "    <div class=\"ecommerce-ad-arrow-main\">\n" +
                "     <div class=\"ecommerce-ad-arrow-main-content\">\n" +
                "      <div class=\"ecommerce-ad-arrow-main-content-des\">\n" +
                "       <span>点击关注【首尝有间外卖】每天领取饿了么/美团大额外卖优惠券</span>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"ecommerce-ad-arrow-icon\">\n" +
                "      <svg width=\"8px\" height=\"14px\" viewbox=\"0 0 16 28\">\n" +
                "       <g fill=\"none\" fill-rule=\"evenodd\" transform=\"translate(-16 -10)\">\n" +
                "        <rect width=\"48\" height=\"48\"></rect>\n" +
                "        <path d=\"M16.5815824,37.3821495 C17.3536131,38.2059502 18.6110874,38.2059502 19.3860425,37.3821495 L31.4187837,25.4877594 C32.1937388,24.6639587 32.1937388,23.3329674 31.4187837,22.5091668 L19.3860425,10.6178505 C18.6110874,9.79404984 17.3565375,9.79404984 16.5815824,10.6178505 C15.8066273,11.4416511 15.8066273,12.7726424 16.578658,13.5964431 L26.6666667,24 L16.5815824,34.4035569 C15.8066273,35.2273576 15.8066273,36.5583489 16.5815824,37.3821495 Z\"></path>\n" +
                "       </g>\n" +
                "      </svg>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <p><b>对了我还想要说一点，就是你们不要被这个满减的“45元减7元”的45元吓到。</b></p>\n" +
                " <p><b>因为你们点过外卖的话也都知道，很多店铺本身就有满减，所以会把价格定的比较高，最后都是会满减下来达到一个比较合理的价位。</b></p>\n" +
                " <p>然后我们这边再去使用优惠券~</p>\n" +
                " <p>就是~~美滋滋~你们懂吧~</p>\n" +
                " <p>接着我们再来看我自己通过优惠券买的一些订单吧~</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic3.zhimg.com/v2-86b1249da00b0a8964b3880b8abbba22_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2218\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-86b1249da00b0a8964b3880b8abbba22_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2218'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2218\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-86b1249da00b0a8964b3880b8abbba22_r.jpg\" data-actualsrc=\"https://pic3.zhimg.com/v2-86b1249da00b0a8964b3880b8abbba22_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>作为夜宵爱好者，怎么会放过点烧烤的机会呢~</p>\n" +
                " <p>于是我通过店铺的满减和外卖优惠券的额度，<b>连续点了两次烧烤！</b></p>\n" +
                " <p>是的，连续点了两次都是差不多是十件商品，（后面那份订单中我点了鸡中翅，所以价格会比较贵一些。）</p>\n" +
                " <p>别问为什么这么做，问就是穷的……</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic1.zhimg.com/v2-61f38d7029cf63b9204a25a61eaebf00_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-61f38d7029cf63b9204a25a61eaebf00_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-61f38d7029cf63b9204a25a61eaebf00_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-61f38d7029cf63b9204a25a61eaebf00_b.jpg\">\n" +
                " </figure>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic1.zhimg.com/v2-90e4ec65a312c828b2e779c396dd1d10_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-90e4ec65a312c828b2e779c396dd1d10_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-90e4ec65a312c828b2e779c396dd1d10_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-90e4ec65a312c828b2e779c396dd1d10_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>这是详细的订单截图，最后的价格在满减优惠之后都是神一般的<b>6块多</b>！！！</p>\n" +
                " <p>就问你们心不心动！~</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>这你们还不添加【<b>小航外卖</b>】吗？？</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <div class=\"RichText-ADLinkCardContainer\">\n" +
                "  <div></div>\n" +
                "  <div class=\"ecommerce-ad-box\">\n" +
                "   <div class=\"ecommerce-ad-arrow\" data-ad-type=\"ecommerce-ad-card\">\n" +
                "    <div class=\"ecommerce-ad-arrow-img\" style=\"background-image: url(&quot;https://pic1.zhimg.com/v2-b9f0a3d5f2ec69bbe8afeec94380874f.webp&quot;);\"></div>\n" +
                "    <div class=\"ecommerce-ad-arrow-main\">\n" +
                "     <div class=\"ecommerce-ad-arrow-main-content\">\n" +
                "      <div class=\"ecommerce-ad-arrow-main-content-des\">\n" +
                "       <span>点击关注【首尝有间外卖】每天领取饿了么/美团大额外卖优惠券</span>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"ecommerce-ad-arrow-icon\">\n" +
                "      <svg width=\"8px\" height=\"14px\" viewbox=\"0 0 16 28\">\n" +
                "       <g fill=\"none\" fill-rule=\"evenodd\" transform=\"translate(-16 -10)\">\n" +
                "        <rect width=\"48\" height=\"48\"></rect>\n" +
                "        <path d=\"M16.5815824,37.3821495 C17.3536131,38.2059502 18.6110874,38.2059502 19.3860425,37.3821495 L31.4187837,25.4877594 C32.1937388,24.6639587 32.1937388,23.3329674 31.4187837,22.5091668 L19.3860425,10.6178505 C18.6110874,9.79404984 17.3565375,9.79404984 16.5815824,10.6178505 C15.8066273,11.4416511 15.8066273,12.7726424 16.578658,13.5964431 L26.6666667,24 L16.5815824,34.4035569 C15.8066273,35.2273576 15.8066273,36.5583489 16.5815824,37.3821495 Z\"></path>\n" +
                "       </g>\n" +
                "      </svg>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>还有这是比较之前下单购买的截图，可以看到在使用红包之后都是非常的便宜。</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic3.zhimg.com/v2-85bf7b1a7f844291a8d46d85f6063b7e_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-85bf7b1a7f844291a8d46d85f6063b7e_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-85bf7b1a7f844291a8d46d85f6063b7e_r.jpg\" data-actualsrc=\"https://pic3.zhimg.com/v2-85bf7b1a7f844291a8d46d85f6063b7e_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>这家店我当时在买的时候真的很绝，看看这一长串的优惠。</p>\n" +
                " <p>谁看了不得是妙妙妙啊！</p>\n" +
                " <p>像这种截图我这里海了去了：</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic4.zhimg.com/v2-ea4c70f561ea756c350df1e70230f2df_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic4.zhimg.com/v2-ea4c70f561ea756c350df1e70230f2df_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic4.zhimg.com/v2-ea4c70f561ea756c350df1e70230f2df_r.jpg\" data-actualsrc=\"https://pic4.zhimg.com/v2-ea4c70f561ea756c350df1e70230f2df_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>大盘鸡，你们知道有多大一碗吗！！</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"normal\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic1.zhimg.com/v2-d1a982bfc296c7235e68dbea36585afc_b.jpg\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"1315\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-d1a982bfc296c7235e68dbea36585afc_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='1315'></svg>\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"1315\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-d1a982bfc296c7235e68dbea36585afc_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-d1a982bfc296c7235e68dbea36585afc_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>满满的一大碗都是肉啊好吗！！</p>\n" +
                " <p>关键是只要十块钱左右，够优惠！</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic4.zhimg.com/v2-8efb09fa62a0334e0648e471d0962fcb_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic4.zhimg.com/v2-8efb09fa62a0334e0648e471d0962fcb_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic4.zhimg.com/v2-8efb09fa62a0334e0648e471d0962fcb_r.jpg\" data-actualsrc=\"https://pic4.zhimg.com/v2-8efb09fa62a0334e0648e471d0962fcb_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>这家店我在鼓楼逛街的时候去吃过，很香，真的超级的香！</p>\n" +
                " <p>汤也好好喝！</p>\n" +
                " <p>关键是鸭血，鸭肝什么的，料巨多！</p>\n" +
                " <p>这让平时经常吃拉面的我惊为天人啊~</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"normal\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic4.zhimg.com/v2-021b6426e567b92b40e6f3355357d473_b.jpg\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"1253\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic4.zhimg.com/v2-021b6426e567b92b40e6f3355357d473_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='1253'></svg>\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"1253\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic4.zhimg.com/v2-021b6426e567b92b40e6f3355357d473_r.jpg\" data-actualsrc=\"https://pic4.zhimg.com/v2-021b6426e567b92b40e6f3355357d473_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>没想到外卖的分量也很多，商家真是实在~</p>\n" +
                " <p>而且这家店我可以很负责的和各位讲：</p>\n" +
                " <p>（这店点外卖比在店里吃便宜~）</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <div class=\"RichText-ADLinkCardContainer\">\n" +
                "  <div></div>\n" +
                "  <div class=\"ecommerce-ad-box\">\n" +
                "   <div class=\"ecommerce-ad-arrow\" data-ad-type=\"ecommerce-ad-card\">\n" +
                "    <div class=\"ecommerce-ad-arrow-img\" style=\"background-image: url(&quot;https://pic1.zhimg.com/v2-b9f0a3d5f2ec69bbe8afeec94380874f.webp&quot;);\"></div>\n" +
                "    <div class=\"ecommerce-ad-arrow-main\">\n" +
                "     <div class=\"ecommerce-ad-arrow-main-content\">\n" +
                "      <div class=\"ecommerce-ad-arrow-main-content-des\">\n" +
                "       <span>点击关注【首尝有间外卖】每天领取饿了么/美团大额外卖优惠券</span>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"ecommerce-ad-arrow-icon\">\n" +
                "      <svg width=\"8px\" height=\"14px\" viewbox=\"0 0 16 28\">\n" +
                "       <g fill=\"none\" fill-rule=\"evenodd\" transform=\"translate(-16 -10)\">\n" +
                "        <rect width=\"48\" height=\"48\"></rect>\n" +
                "        <path d=\"M16.5815824,37.3821495 C17.3536131,38.2059502 18.6110874,38.2059502 19.3860425,37.3821495 L31.4187837,25.4877594 C32.1937388,24.6639587 32.1937388,23.3329674 31.4187837,22.5091668 L19.3860425,10.6178505 C18.6110874,9.79404984 17.3565375,9.79404984 16.5815824,10.6178505 C15.8066273,11.4416511 15.8066273,12.7726424 16.578658,13.5964431 L26.6666667,24 L16.5815824,34.4035569 C15.8066273,35.2273576 15.8066273,36.5583489 16.5815824,37.3821495 Z\"></path>\n" +
                "       </g>\n" +
                "      </svg>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>大家快点添加【<b>小航外卖</b>】啊！</p>\n" +
                " <p>点外卖绝对不会亏的好吗？</p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic2.zhimg.com/v2-71706d147cc76f08f501d9721ee28529_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic2.zhimg.com/v2-71706d147cc76f08f501d9721ee28529_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic2.zhimg.com/v2-71706d147cc76f08f501d9721ee28529_r.jpg\" data-actualsrc=\"https://pic2.zhimg.com/v2-71706d147cc76f08f501d9721ee28529_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>太多了，图都发不过来，我直接放一个界面吧。</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic1.zhimg.com/v2-8b19382f10ab91e3ac3c9077dae6f6f4_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-8b19382f10ab91e3ac3c9077dae6f6f4_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='2310'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"2310\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-8b19382f10ab91e3ac3c9077dae6f6f4_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-8b19382f10ab91e3ac3c9077dae6f6f4_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>当然这些<b>外卖优惠红包</b>都是我在公众号上面领取到的，大家千万不要错过啊。</p>\n" +
                " <p>使用优惠券之后点外卖，真的在吃饭这方面能省下不少的钱呢！</p>\n" +
                " <div class=\"RichText-ADLinkCardContainer\">\n" +
                "  <div></div>\n" +
                "  <div class=\"ecommerce-ad-box\">\n" +
                "   <div class=\"ecommerce-ad-arrow\" data-ad-type=\"ecommerce-ad-card\">\n" +
                "    <div class=\"ecommerce-ad-arrow-img\" style=\"background-image: url(&quot;https://pic1.zhimg.com/v2-b9f0a3d5f2ec69bbe8afeec94380874f.webp&quot;);\"></div>\n" +
                "    <div class=\"ecommerce-ad-arrow-main\">\n" +
                "     <div class=\"ecommerce-ad-arrow-main-content\">\n" +
                "      <div class=\"ecommerce-ad-arrow-main-content-des\">\n" +
                "       <span>点击关注【首尝有间外卖】每天领取饿了么/美团大额外卖优惠券</span>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"ecommerce-ad-arrow-icon\">\n" +
                "      <svg width=\"8px\" height=\"14px\" viewbox=\"0 0 16 28\">\n" +
                "       <g fill=\"none\" fill-rule=\"evenodd\" transform=\"translate(-16 -10)\">\n" +
                "        <rect width=\"48\" height=\"48\"></rect>\n" +
                "        <path d=\"M16.5815824,37.3821495 C17.3536131,38.2059502 18.6110874,38.2059502 19.3860425,37.3821495 L31.4187837,25.4877594 C32.1937388,24.6639587 32.1937388,23.3329674 31.4187837,22.5091668 L19.3860425,10.6178505 C18.6110874,9.79404984 17.3565375,9.79404984 16.5815824,10.6178505 C15.8066273,11.4416511 15.8066273,12.7726424 16.578658,13.5964431 L26.6666667,24 L16.5815824,34.4035569 C15.8066273,35.2273576 15.8066273,36.5583489 16.5815824,37.3821495 Z\"></path>\n" +
                "       </g>\n" +
                "      </svg>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <p>还有还有，下面这是前不久点的外卖吃的杂酱面：</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"normal\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic1.zhimg.com/v2-2469ef323e3390afa22fcc7d396555e0_b.jpg\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"658\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-2469ef323e3390afa22fcc7d396555e0_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='658'></svg>\" data-caption=\"\" data-size=\"normal\" data-rawwidth=\"1080\" data-rawheight=\"658\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic1.zhimg.com/v2-2469ef323e3390afa22fcc7d396555e0_r.jpg\" data-actualsrc=\"https://pic1.zhimg.com/v2-2469ef323e3390afa22fcc7d396555e0_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>看看这个价格只有六块多！！！</p>\n" +
                " <p>那不比店里还要便宜很多呐~</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <figure data-size=\"small\">\n" +
                "  <noscript>\n" +
                "   <img src=\"https://pic3.zhimg.com/v2-b439a56c3b3a85433fbf5575069b5dda_b.jpg\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"1604\" class=\"origin_image zh-lightbox-thumb\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-b439a56c3b3a85433fbf5575069b5dda_r.jpg\">\n" +
                "  </noscript>\n" +
                "  <img src=\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='1080' height='1604'></svg>\" data-caption=\"\" data-size=\"small\" data-rawwidth=\"1080\" data-rawheight=\"1604\" class=\"origin_image zh-lightbox-thumb lazy\" width=\"1080\" data-original=\"https://pic3.zhimg.com/v2-b439a56c3b3a85433fbf5575069b5dda_r.jpg\" data-actualsrc=\"https://pic3.zhimg.com/v2-b439a56c3b3a85433fbf5575069b5dda_b.jpg\">\n" +
                " </figure>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>那天是凌晨，一整个白天夜晚我都没有进过食，被窗外的雨声吵醒，醒来后超级饿，点的外卖。</p>\n" +
                " <p>我点的还是最大份的三两面加面汤，最后也只要这点钱，太优惠了实在是！！！！</p>\n" +
                " <p>关键原因就是那9元的美团红包啊！</p>\n" +
                " <p>想要优惠红包的姐妹们就直接点击下面的链接添加公众号就好。</p>\n" +
                " <p>相信我，它会告诉你应该怎么省钱的嘿嘿~</p>\n" +
                " <p class=\"ztext-empty-paragraph\"><br></p>\n" +
                " <p>看到这里，大家不要着急哈，先看一下我用了优惠券以后是怎样点外卖的——</p>\n" +
                " <p>打开链接，领取大额红包；</p>\n" +
                " <p>打开美团、饿了么，想吃什么就看什么，当然，不想吃的当然也可以看，收藏留着下次吃！在也不用掂量哪个店便宜了！</p>\n" +
                " <p>领个红包直接开干，想吃什么就点什么，肯德基、奶茶、鸡排、烧烤……打工人就是应该对自己好点！</p>\n" +
                " <p>付款，最多十块钱！一块、两块、几毛都是常有的事！</p>\n" +
                " <p>领完红包之后，不用退出，它能够直接进到美团和饿了么的小程序，然后你就选你想吃的东西，直接下单就好了。是不是简单粗暴！！！</p>\n" +
                " <p>最后再放上链接~</p>\n" +
                " <div class=\"RichText-ADLinkCardContainer\">\n" +
                "  <div></div>\n" +
                "  <div class=\"ecommerce-ad-box\">\n" +
                "   <div class=\"ecommerce-ad-arrow\" data-ad-type=\"ecommerce-ad-card\">\n" +
                "    <div class=\"ecommerce-ad-arrow-img\" style=\"background-image: url(&quot;https://pic1.zhimg.com/v2-b9f0a3d5f2ec69bbe8afeec94380874f.webp&quot;);\"></div>\n" +
                "    <div class=\"ecommerce-ad-arrow-main\">\n" +
                "     <div class=\"ecommerce-ad-arrow-main-content\">\n" +
                "      <div class=\"ecommerce-ad-arrow-main-content-des\">\n" +
                "       <span>点击关注【首尝有间外卖】每天领取饿了么/美团大额外卖优惠券</span>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"ecommerce-ad-arrow-icon\">\n" +
                "      <svg width=\"8px\" height=\"14px\" viewbox=\"0 0 16 28\">\n" +
                "       <g fill=\"none\" fill-rule=\"evenodd\" transform=\"translate(-16 -10)\">\n" +
                "        <rect width=\"48\" height=\"48\"></rect>\n" +
                "        <path d=\"M16.5815824,37.3821495 C17.3536131,38.2059502 18.6110874,38.2059502 19.3860425,37.3821495 L31.4187837,25.4877594 C32.1937388,24.6639587 32.1937388,23.3329674 31.4187837,22.5091668 L19.3860425,10.6178505 C18.6110874,9.79404984 17.3565375,9.79404984 16.5815824,10.6178505 C15.8066273,11.4416511 15.8066273,12.7726424 16.578658,13.5964431 L26.6666667,24 L16.5815824,34.4035569 C15.8066273,35.2273576 15.8066273,36.5583489 16.5815824,37.3821495 Z\"></path>\n" +
                "       </g>\n" +
                "      </svg>\n" +
                "     </div>\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <p></p>\n" +
                "</div>";


        Document parse = Jsoup.parse(html);
        String text = parse.text();
        System.out.println(text);


        Set<String> imgsUrl = new HashSet<>();
        Elements img = parse.getElementsByTag("img");
        System.out.println("图片个数："+img.size());
        for (Element item : img) {
            String src = item.attr("data-actualsrc");
            imgsUrl.add(src);
        }
        System.out.println("解析后图片个数："+imgsUrl.size());
        System.out.println("解析后的结果："+ JSONObject.toJSONString(imgsUrl));

    }






    @Test
    public void testString(){
        String testOne = "1,870 人赞同了该文章";
        System.out.println(Long.valueOf(testOne.substring(0,testOne.indexOf("人")).replace(",","").trim()));

        String testTwo = "https://zhuanlan.zhihu.com/p/366696886";
        System.out.println(Long.valueOf(testTwo.substring(testTwo.lastIndexOf("/")+1,testTwo.length()).trim()));
    }

    @Test
    public void testTime(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String time = "2021-10-20";
        LocalDateTime date = LocalDate.parse(time, df).atStartOfDay();
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

}

import com.webmagic.WebmagicApplication;
import com.webmagic.entity.ArticleData;
import com.webmagic.mapper.ArticleDataMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * <p>
 * <b>MybatisTest</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/15
 */
@SpringBootTest(classes = WebmagicApplication.class)
@RunWith(SpringRunner.class)
public class MybatisTest {

    @Autowired
    private ArticleDataMapper articleDataMapper;


    @Test
    public void add(){
        ArticleData data = new ArticleData();
        data.setAuthor("小三");
        data.setCreateTime(LocalDateTime.now());
        articleDataMapper.insert(data);
    }




}

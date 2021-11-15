package com.webmagic.pipixia;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webmagic.entity.ArticleData;
import com.webmagic.enumpack.ResourceEnum;
import com.webmagic.mapper.ArticleDataMapper;
import com.webmagic.utils.HttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * <b>Process</b>
 * </p>
 *
 * @author Wjx
 * @Description: 获取皮皮虾的数据
 * @since 2021/11/12
 */
@Component
public class Process {

    @Autowired
    private ArticleDataMapper articleDataMapper;

    public void getInfoByKeyWord(String keyWord) throws Exception {
        //因为各个网站是固定的，暂时在代码中写死查询的内容
        int offset = 0;
        String originalUrl = "https://i.snssdk.com/bds/search/?ac=wifi&aid=1319&app_name=super&channel=tengxun" +
                "&device_id" +
                "=1249460501102232&device_platform=android&device_type=SM-G973N&os_api=25&os_version=7.1.2&version_code=161&version_name=1.6.1&is_browser=1&show_all_user=1&type=1&search_id=&api_version=1.2";
        StringBuilder urlFormWork = new StringBuilder();
        urlFormWork.append(originalUrl);
        urlFormWork.append("&keyword=");
        urlFormWork.append(keyWord);
        urlFormWork.append("&offset=");
        while (offset <= 1000) {
            StringBuilder newUrl = new StringBuilder(urlFormWork);
            newUrl.append(offset);
            JSONObject object = HttpUtils.doGet(newUrl.toString(), null);
            dealResult(object,keyWord);
            offset = offset + 20;
        }
    }


    public  void dealResult(JSONObject jsonObject,String keyWord) {
        JSONArray array = ((JSONObject) jsonObject.get("data")).getJSONArray("data");
        List<ArticleData> dataList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject objectItem = JSON.parseObject(array.getString(i));
            if (!objectItem.containsKey("item")) {
                continue;
            }
            if (objectItem.get("item") == null) {
                continue;
            }
            ArticleData articleData = new ArticleData();
            //获取标题
            String title = ((JSONObject) objectItem.get("item")).get("content").toString();
            //获取作者
            String author = ((JSONObject) ((JSONObject) objectItem.get("item")).get("author")).get("name").toString();
            //内容
            String content = ((JSONObject) objectItem.get("item")).get("content").toString();
            //内容时间
            String contentTime = ((JSONObject) objectItem.get("item")).get("create_time").toString();
            //点赞
            String contentHot = ((JSONObject) ((JSONObject) objectItem.get("item")).get("stats")).get("like_count").toString();
            //视频地址
            String video = null;
            if (((JSONObject) objectItem.get("item")).get("origin_video_download") != null) {
                video =
                        ((JSONObject) ((JSONObject) ((JSONObject) objectItem.get("item")).get("origin_video_download")).getJSONArray("url_list").get(0)).get("url").toString();
            }
            //来源ID
            String resourceId = objectItem.get("cell_id_str").toString();
            //图片
            String imgs = null;
            List<String> imgsUrl = new ArrayList<>();
            if(((JSONObject) objectItem.get("item")).get("note") != null){
                JSONArray array1 = ((JSONObject) ((JSONObject) objectItem.get("item")).get("note")).getJSONArray("multi_image");
                for (int j=0;j<array1.size();j++){
                    JSONObject jsonObject1 = JSON.parseObject(array1.getString(j));
                    imgsUrl.add(((JSONObject)jsonObject1.getJSONArray("download_list").get(0)).get("url").toString());
                }
            }else {
                if(((JSONObject) objectItem.get("item")).get("cover") != null){
                    imgsUrl.add(((JSONObject) ((JSONObject) ((JSONObject) objectItem.get("item")).get("cover")).getJSONArray(
                            "download_list").get(0)).get("url").toString());
                }
            }

            articleData.setTitle(title);
            articleData.setAuthor(author);
            articleData.setContent(content);
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(Long.valueOf(contentTime), 0, ZoneOffset.ofHours(8));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            articleData.setContentTime(LocalDateTime.parse(dateTime.format(dateTimeFormatter), dateTimeFormatter));
            articleData.setContentHot(Long.valueOf(contentHot));
            articleData.setCreateTime(LocalDateTime.now());
            articleData.setVideo(video);
            articleData.setResource(ResourceEnum.PI_PI_XIA.name());
            articleData.setResourceId(Long.valueOf(resourceId));
            articleData.setImgs(JSONObject.toJSONString(imgsUrl));
            articleData.setKeyWord(keyWord);


            QueryWrapper<ArticleData> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .eq(ArticleData::getResourceId,articleData.getResourceId())
                    .eq(ArticleData::getResource,ResourceEnum.PI_PI_XIA.name());
            List<ArticleData> data = articleDataMapper.selectList(wrapper);
            if(!CollectionUtils.isEmpty(data)){
                return;
            }
            articleDataMapper.insert(articleData);
        }
    }


}

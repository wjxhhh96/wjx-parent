package com.webmagic.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.webmagic.entity.HttpBaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <b>HttpUtils</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/11/12
 */
public class HttpUtils {
    /**
     * JSON 格式的POST请求
     * @param url
     * @param encode
     * @param headers
     * @param body
     * @return
     * @throws Exception
     */
    public static JSONObject doPost(String url, String encode, Map<String, String> headers, String body) throws Exception {
        CloseableHttpClient httpClient = createClient();
        HttpPost request = new HttpPost(url);
        if( !headers.isEmpty() ){
            headers.forEach((k,v)->{
                request.addHeader(k,v);
            });
        }

        if (StringUtils.isEmpty(encode)) {
            encode = "UTF-8";
        }

        if(StringUtils.isNotBlank(body)){
            request.setEntity(new ByteArrayEntity(body.getBytes(encode)));
        }
        CloseableHttpResponse response = httpClient.execute(request);
        String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        //log.info("发起post请求url: {}, 参数：{},  返回结果：{}", url, body, result);
        if (result.startsWith("{")) {
            return JSONObject.parseObject(result);
        }
        return null;
    }



    /**
     * Form 表单提交
     * @param url
     * @param encode
     * @param headers
     * @param paramMaps
     * @return
     * @throws Exception
     */
    public static JSONObject doPostForm(String url, String encode, Map<String, String> headers, Map<String, String> paramMaps) throws Exception {
        CloseableHttpClient httpClient = createClient();
        HttpPost request = new HttpPost(url);
        if( !headers.isEmpty() ){
            headers.forEach((k,v)->{
                request.addHeader(k,v);
            });
        }

        if (StringUtils.isEmpty(encode)) {
            encode = "UTF-8";
        }
        List<NameValuePair> params = new ArrayList<>();
        paramMaps.forEach((k,v)->{
            params.add(new BasicNameValuePair(k,v));
        });

        request.setEntity(new UrlEncodedFormEntity(params,encode));
        CloseableHttpResponse response = httpClient.execute(request);
        String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        //log.info("发起post请求url: {}, 参数：{},  返回结果：{}", url, JSONObject.toJSONString(paramMaps),JSONObject.toJSONString(result));
        if (result.startsWith("{")) {
            return JSONObject.parseObject(result);
        }
        return null;
    }

    /**
     * 上传图片
     * @param url
     * @param encode
     * @param headers
     * @param paramMaps
     * @param files
     * @return
     * @throws Exception
     */
    public static JSONObject doPostFile(String url, String encode, Map<String, String> headers, Map<String, String> paramMaps, Map<String, File> files) throws Exception{
        CloseableHttpClient httpClient = createClient();
        HttpPost request = new HttpPost(url);
        if( !headers.isEmpty() ){
            headers.forEach((k,v)->{
                request.addHeader(k,v);
            });
        }
        if (StringUtils.isEmpty(encode)) {
            encode = "UTF-8";
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(StandardCharsets.UTF_8);

        ContentType contentType = ContentType.create("multipart/form-data", Consts.UTF_8);
        //上传文件 采用二进制
        for(Map.Entry<String,File> itemFile:files.entrySet()){
            builder.addBinaryBody(itemFile.getKey(),itemFile.getValue(),contentType,itemFile.getValue().getName());
        }

        //上传其他参数 采用 xxx-form

        for(Map.Entry<String,String> itemParam:paramMaps.entrySet()){
            builder.addTextBody(itemParam.getKey(),itemParam.getValue(),contentType);
        }

        request.setEntity(builder.build());
        CloseableHttpResponse response = httpClient.execute(request);
        String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        //log.info("发起post请求url: {}, 参数：{},  返回结果：{}", url, JSONObject.toJSONString(paramMaps),JSONObject.toJSONString(result));
        if (result.startsWith("{")) {
            return JSONObject.parseObject(result);
        }
        return null;
    }


    /**
     * 根据指定类型返回参数
     * @param url
     * @param encode
     * @param headers
     * @param paramMaps
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> HttpBaseResponse<T> doPostForm(String url, String encode, Map<String, String> headers, Map<String, String> paramMaps, Class<T> clazz) {
        try{
            JSONObject jsonObject = doPostForm(url, encode, headers, paramMaps);
            TypeReference<HttpBaseResponse<T>> typeReference = new TypeReference<HttpBaseResponse<T>>(clazz){};
            HttpBaseResponse<T> t = JSONObject.parseObject(JSONObject.toJSONString(jsonObject),typeReference);
            return t;
        }catch (Exception e){
            //log.error("http请求失败，{}",e);
            throw new RuntimeException("http请求失败");
        }
    }


    /**
     * 上传文件 根据指定类型返回参数
     * @param url
     * @param encode
     * @param headers
     * @param paramMaps
     * @param files
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> HttpBaseResponse<T> doPostFile(String url, String encode, Map<String, String> headers, Map<String, String> paramMaps, Map<String, File> files, Class<T> clazz){
        try{
            JSONObject jsonObject = doPostFile(url,encode,headers,paramMaps,files);
            TypeReference<HttpBaseResponse<T>> typeReference = new TypeReference<HttpBaseResponse<T>>(clazz){};
            HttpBaseResponse<T> t = JSONObject.parseObject(JSONObject.toJSONString(jsonObject),typeReference);
            return t;
        }catch (Exception e){
            //log.error("http请求失败，{}",e);
            throw new RuntimeException("http请求失败");
        }
    }


    /**
     *  根据指定类型返回集合
     * @param url
     * @param encode
     * @param headers
     * @param paramMaps
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> HttpBaseResponse<List<T>> doPostList(String url, String encode, Map<String, String> headers, Map<String, String> paramMaps, Class<T> clazz) {
        try{
            JSONObject jsonObject = doPostForm(url, encode, headers, paramMaps);
            HttpBaseResponse<List<T>> t = JSONObject.parseObject(JSONObject.toJSONString(jsonObject),buildType(HttpBaseResponse.class,List.class,clazz));
            return t;
        }catch (Exception e){
            //log.error("http请求失败，{}",e);
            throw new RuntimeException("http请求失败");
        }
    }


    /**
     * GET 请求
     * @param url
     * @param headers
     * @return
     * @throws Exception
     */
    public static JSONObject doGet(String url, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpClient = createClient();
        HttpGet httpGet = new HttpGet(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach((k, v) -> {
                httpGet.addHeader(k, v);
            });

        }
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
        //log.info("发起get请求url：{}， 返回结果：{}", url, result);
        if (result.startsWith("{")) {
            return JSONObject.parseObject(result);
        }
        return null;
    }

    /**
     * 根据指定单个类型返回参数
     * @param url
     * @param headers
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> HttpBaseResponse<T> doGet(String url, Map<String, String> headers, Class<T> clazz) {

        try {
            JSONObject jsonObject = doGet(url, headers);
            TypeReference<HttpBaseResponse<T>> typeReference = new TypeReference<HttpBaseResponse<T>>(clazz){};
            HttpBaseResponse<T> t = JSONObject.parseObject(JSONObject.toJSONString(jsonObject),typeReference);
            return t;
        }catch (Exception e){
            //log.error("http请求失败，{}",e);
            throw new RuntimeException("http请求失败");
        }
    }

    /**
     * 根据指定类型 返回集合类型返回参数
     * @param url
     * @param headers
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> HttpBaseResponse<List<T>> doGetList(String url,Map<String,String> headers,Class<T> clazz){
        try{
            JSONObject jsonObject = doGet(url, headers);
            HttpBaseResponse<List<T>> t = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), buildType(HttpBaseResponse.class,List.class,clazz));
            return t;
        }catch (Exception e){
            //log.error("http请求失败，{}",e);
            throw new RuntimeException("http请求失败");
        }
    }


    private static Type buildType(Type... types) {
        ParameterizedTypeImpl beforeType = null;
        if (types != null && types.length > 0) {
            for (int i = types.length - 1; i > 0; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
            }
        }
        return beforeType;
    }

    private static CloseableHttpClient createClient() {
        return HttpClients.custom()
                .disableAutomaticRetries() //禁用自动重试
                .setDefaultRequestConfig(createRequestConfig())
                .setDefaultSocketConfig(createSocketConfig())
                .build();
    }


    private static RequestConfig createRequestConfig() {
        return RequestConfig.custom()
                //建立连接超时时间设置
                .setConnectTimeout(60000)
                //从连接池获取连接超时时间
                .setConnectionRequestTimeout(6000)
                .build();
    }


    private static SocketConfig createSocketConfig() {
        return SocketConfig.custom()
                //socket 连接超时时间
                .setSoTimeout(10000)
                .build();
    }
}

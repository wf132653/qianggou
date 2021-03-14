package com.wf.qianggou.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 获取 TB 服务器时间
 *
 * @author wf
 * @date 2020/03/25
 */
@Slf4j
public class GetServerTimeOfTb {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetServerTimeOfTb.class);
    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送GET请求
     *
     * @return JSON或者字符串
     * @throws Exception 异常
     */
    public static Long getServiceTime() throws Exception {
        String url = "http://api.m.taobao.com/rest/api3.do?api=mtop.common.getTimestamp";
        CloseableHttpClient client;
        client = new SSLClient();
        CloseableHttpResponse response = null;
        Long t = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                Map map = (Map) JSONObject.parse(EntityUtils.toString(entity, "UTF-8"));
                Map data = (Map) map.get("data");
                t = Long.valueOf(data.get("t").toString());
            } else {
                LOGGER.error("HttpClientService-line: {}, errorMsg{}", 65, "GET请求失败！");
            }
        } catch (Exception e) {
            LOGGER.error("获取 TB 服务器时间失败，取本地时间{}", e);
            t = System.currentTimeMillis();
        } finally {
            if (response != null) {
                response.close();
            }
            client.close();
        }
        return t;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date date = new Date();
        log.info("now: {}", System.currentTimeMillis());
        long tb = getServiceTime();
        log.info("now: {}", System.currentTimeMillis());
        // 280 11
        // 238 23
        // 232 45
//        log.info("tb : {}", tb);
//        log.info("lo : {}", System.currentTimeMillis());
        date.setTime(tb);
        System.out.println(sdf.format(date));
    }

}
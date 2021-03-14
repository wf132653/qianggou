package com.wf.qianggou.util.xiaomi;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.config.SysConstants;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月14日 17:06
 */
@Slf4j
public class _2_GetToken {

    public static long needTime;
    /**
     * 2021-03-05 15:40:00 000
     */
    private static String buyDateTime;

    static {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        buyDateTime = sdf.format(date) + XiaoMiConstant.BUY_TIME;
        LocalDateTime parse = LocalDateTime.parse(buyDateTime, SysConstants.dateTimeFormatter);
        needTime = LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

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
    public static Map<String, Object> sendGet(String url) throws Exception {
//        System.out.println("GetToken的url = " + url);
        CloseableHttpClient client;
        client = new SSLClient();
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
            httpGet.setHeader("origin", "https://www.mi.com");
            httpGet.setHeader("referer", "https://www.mi.com/");
            httpGet.setHeader("cookie", XiaoMiConstant.COOKIE);
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity, "UTF-8");
                res = res.substring(10, res.length() - 1);
                return (Map) JSONObject.parse(res);
            } else {
                log.error("GET请求失败！");
            }
        } catch (Exception e) {
            log.error("请求错误信息 : {}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
            client.close();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        getToken();
    }

    public static String getToken() throws Exception {
        String storage = _1_GetStorage.getStorage();

        // 到点了再运行这里
        long sleep = needTime - System.currentTimeMillis();
        sleep -= 1000;
        if (sleep > 0) {
            log.info("sleep = {}", sleep);
            Thread.sleep(sleep);
        }

        String token = null;
        long s = System.currentTimeMillis();
        while (s - System.currentTimeMillis() < 3000) {
            try {
                _1_GetSalt getSalt = new _1_GetSalt();
                getSalt.getSlat();

                String url = "https://tp.hd.mi.com/hdget/cn?addcart=1&source=flashsale_bigtap&ans=&ap=24&ac=272&ad=2548&aa=2548017&jsonpcallback=hdcontrol";
                url += "&product=" + XiaoMiConstant.GOODS_IDS;
                url += "&storage=" + storage;
                url += "&m=" + XiaoMiConstant.M;
                url += "&salt=" + getSalt.getSalt();
                url += "&_=" + System.currentTimeMillis();

                Map map = sendGet(url);
                Map status = (Map) map.get("status");
                Map gMap = (Map) status.get(XiaoMiConstant.GOODS_IDS);
                token = gMap.get("hdurl").toString();
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return token;
    }
}

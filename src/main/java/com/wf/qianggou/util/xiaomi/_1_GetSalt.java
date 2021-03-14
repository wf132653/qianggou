package com.wf.qianggou.util.xiaomi;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.SSLClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月14日 16:09
 */
@Slf4j
@Data
public class _1_GetSalt {

    private String salt = "";
    private String startTime = "";
    private String endTime = "";

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
//        System.out.println("GetSalt的url = " + url);
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
                res = res.substring(7, res.length() - 1);
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
        _1_GetSalt a = new _1_GetSalt();
        a.getSlat();
        System.out.println(a.toString());
    }

    public void getSlat() throws Exception {

        String storage = _1_GetStorage.getStorage();
        String url = "https://tp.hd.mi.com/hdinfo/cn?source=flashsale_bigtap&ap=24&ac=272&ad=2548&aa=2548017&jsonpcallback=hdinfo";
        url += "&product=" + XiaoMiConstant.GOODS_IDS;
        url += "&storage=" + storage;
        url += "&m=" + XiaoMiConstant.M;
        url += "&_=" + System.currentTimeMillis();

        Map<String, Object> map = sendGet(url);
        Map status = (Map) map.get("status");
        Map gMap = (Map) status.get(XiaoMiConstant.GOODS_IDS);
        String salt = gMap.get("salt").toString();
        String startTime = gMap.get("starttime").toString();
        String endTime = gMap.get("endtime").toString();
        this.salt = salt;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}

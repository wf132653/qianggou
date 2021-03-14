package com.wf.qianggou.util.xiaomi;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.SSLClient;
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
 * @date 2021年03月14日 16:43
 */
@Slf4j
public class _1_GetStorage {

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
//        System.out.println("GetStorage的url = " + url);
        CloseableHttpClient client;
        client = new SSLClient();
        CloseableHttpResponse response = null;
        Long t = null;
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
                return (Map) JSONObject.parse(EntityUtils.toString(entity, "UTF-8"));
            } else {
                log.error("GET请求失败！");
            }
        } catch (Exception e) {
            log.error("请求错误信息 : {}", e.getMessage());
            t = System.currentTimeMillis();
        } finally {
            if (response != null) {
                response.close();
            }
            client.close();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        getStorage();
    }

    public static String getStorage() throws Exception {
        String url = "https://api2.order.mi.com/product/delivery?item_ids&province_id=24&city_id=272&district_id=2548&area=2548017";
        url += "&goods_ids=" + XiaoMiConstant.GOODS_IDS;
        url += "&t=" + System.currentTimeMillis() / 1000;

        Map<String, Object> map = sendGet(url);
        Map<String, Object> datas = (Map) ((Map) map.get("data")).get("datas");
        Map<String, Object> gMap = (Map) datas.get(XiaoMiConstant.GOODS_IDS);
        String storage = gMap.get("mihome_id").toString();
        // 还差一个 m ，
        return storage;
    }
}

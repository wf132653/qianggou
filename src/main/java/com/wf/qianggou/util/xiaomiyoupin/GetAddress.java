package com.wf.qianggou.util.xiaomiyoupin;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月17日 10:47
 */
@Slf4j
public class GetAddress {


    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送 https 的 POST请求
     *
     * @param url  url 地址
     * @param body
     * @return JSON或者字符串
     * @throws Exception
     */
    public static String sendPost(String url, String body, String cookie) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = new SSLClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(body, "UTF-8"));

            post.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("cookie", cookie);

            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                log.error("POST请求失败");
            }
        } catch (Exception e) {
            log.error("Exception：{}", e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        getAddressId("youpindistinct_id=1783e12bc5f9-0f727c4430a0018-4c3f227c; mijiasn=bdss_2021pc50; mjclient=PC; youpin_sessionid=1783e1319ed-06dd2a63ca16488-15bb; Hm_lvt_025702dcecee57b18ed6fb366754c1b8=1615949119; Hm_lpvt_025702dcecee57b18ed6fb366754c1b8=1615949143; b_auth=mijiayoupin; serviceToken=1tj+P5WMm5/QJMr2lTkj4OGNHZQxzjl3hk1OdmZWCaZqzjgwSzdFvYT0FjtPYdzJ5/XjLIuiHh9bTWVodg4AMIhhReYv7RT7nzzll/iq3JHbfYip6mKjQkSZvquV4eu2a4yvj7qXJiT/a0fffOHIzg==; cUserId=F6qkgYFDJbDCCefbvOIV05IbAEA; exchangeToken=_7JD0FCFFsLUfI4P2lkd8aWl7SifqWINdzoPwuRE-Cz-OCsTapXqv9V9mV0qEpDX2dmbP7wPPnpyy82BHQHwNAwTiKg6GXk33gLCbCOsvuZiQOsBbg_XPhkcieRHyaN7hQOPkoOSvpnZ1o9FAzwf6SsanaLB39csGojpkZeRad5LVwLLqAyN9HK7u0H69ucEdR746IjOSt7epWpi4MPtHLFkbQZbK6zPyrJrqT3yibxXxZeSmWLLmqyPbu-yl1IgnyjoSOKYE5jksRBmskXlfg==");
    }


    public static String getAddressId(String cookie) throws Exception {
        String url = "https://www.xiaomiyoupin.com/mtop/order/address/getList";
        String body = "[{},{\"hideTel\":true}]";
        String res = sendPost(url, body, cookie);
        Map map = JSONObject.parseObject(res, Map.class);
        return ((Map) ((List) ((Map) map.get("data")).get("address")).get(0)).get("addressId").toString();
    }
}

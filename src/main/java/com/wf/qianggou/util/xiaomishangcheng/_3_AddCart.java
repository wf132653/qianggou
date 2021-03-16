package com.wf.qianggou.util.xiaomishangcheng;

import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月14日 17:06
 */
@Slf4j
public class _3_AddCart {

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
    public static void sendGet(String url, String cookie) throws Exception {
//        System.out.println("AddCart的url = " + url);
        CloseableHttpClient client;
        client = new SSLClient();
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
            httpGet.setHeader("origin", "https://www.mi.com");
            httpGet.setHeader("referer", "https://www.mi.com/");
            httpGet.setHeader("cookie", cookie);
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                String res = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("添加购物车的返回:" + res);
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
    }

    public static void main(String[] args) throws Exception {
        addCart("");
    }

    public static void addCart(String cookie) throws Exception {

        long s = System.currentTimeMillis();
        while (System.currentTimeMillis() - s < 2000){
            try {
                String url = "https://api2.order.mi.com/cart/add/";
                url += XiaoMiConstant.GOODS_IDS + "?" + "gid=" + XiaoMiConstant.GOODS_IDS;
                url += "&callback=__jp4";
                url += "&source=normal_seckill";
                String token = _2_GetToken.getToken(cookie);
                url += "&token=" + token;
                sendGet(url, cookie);
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}

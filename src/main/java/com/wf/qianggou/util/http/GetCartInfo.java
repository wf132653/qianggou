package com.wf.qianggou.util.http;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.config.SysConstants;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * 获取购物车相关信息
 * 需要更换的是 cookie
 *
 * @author wf
 * @date 2021年03月05日 11:03
 */
@Slf4j
public class GetCartInfo {



    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送GET请求
     *
     * @param url 请求url
     * @return JSON或者字符串
     * @throws Exception 异常
     */
    public String sendGet(String url) throws Exception {
        // https
        CloseableHttpClient client = new SSLClient();
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // taobao 登录的 cookie
            httpGet.setHeader(new BasicHeader("Cookie", SysConstants.cookie));
            httpGet.setHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.72 Safari/537.36"));
            // 执行请求
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            } else {
                log.error("GET请求失败");
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

    public static void main(String[] args) throws Exception{
        new GetCartInfo().getCart();
    }


    public Map getCart() throws Exception {
        String url = "https://cart.taobao.com/cart.htm?spm=a10d.6639537.1997525049.1.36697484rhpf20&from=mini&ad_id&am_id&cm_id&pm_id=1501036000a02c5c3739";
        String res = sendGet(url);
        System.out.println(res);
        int firstDataIndex = res.indexOf("firstData");
        int successIndex = res.indexOf("\"success\":true");
        String re = res.substring(firstDataIndex + 12, successIndex + 15);
        log.info("-------------------------------");
        log.info("getCart = " + re);
        log.info("-------------------------------");



        return JSONObject.parseObject(re, Map.class);
    }
}

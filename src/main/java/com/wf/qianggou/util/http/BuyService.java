package com.wf.qianggou.util.http;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient发送GET、POST请求
 *
 * @author wf
 * @date 2020/03/25
 */
public class BuyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyService.class);
    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送POST请求
     *
     * @param url               url 地址
     * @param body
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPost(String url, String body) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 设置请求的内容
             */
            post.setEntity(new StringEntity(body, "UTF-8"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader(":authority", "buy.tmall.com"));
            post.setHeader(new BasicHeader(":method", "POST"));
            post.setHeader(new BasicHeader(":path", "/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined"));
            post.setHeader(new BasicHeader(":scheme", "https"));
            post.setHeader(new BasicHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"));
            post.setHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
            post.setHeader(new BasicHeader("cache-control", "max-age=0"));
            post.setHeader(new BasicHeader("content-type", "application/x-www-form-urlencoded"));
            post.setHeader(new BasicHeader("cookie", "cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; sm4=510100; _m_h5_tk=f408ae7cdb0a1b6a04dae73acfc8d805_1614050863176; _m_h5_tk_enc=a970bd8a4855f8e2cbde5648290f79a8; xlly_s=1; login=true; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&pas=0&cookie21=VT5L2FSpccLuJBreKQgf&cookie14=Uoe1hgbXafOgEg%3D%3D&existShop=true&cart_m=0&cookie15=VFC%2FuZ9ayeYq2g%3D%3D; uc3=lg2=U%2BGCWk%2F75gdr5Q%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&vt3=F8dCuASh7qCZTbI2u%2Fw%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOKY%2B5aUn&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMCI2LAuk; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100Izywjkc2GOFw5N23PoPRlTmqUpz%2FKg6keugoH7gc%2FHYUktpsmsOozRnU9d546RQVgJfoeX1RLVYYMxMIHydDxQ%3D%3D; sg=424; csg=74dd55bb; isg=BEdHojlw6-rBLFD619sSHTiY1vsRTBsuycNmlxk0WFb9iGdKIRt_fodJLkjWYPOm"));
            post.setHeader(new BasicHeader("origin", "https://cart.taobao.com"));
            post.setHeader(new BasicHeader("referer", "https://cart.taobao.com/"));
            post.setHeader(new BasicHeader("sec-fetch-dest", "document"));
            post.setHeader(new BasicHeader("sec-fetch-mode", "navigate"));
            post.setHeader(new BasicHeader("sec-fetch-site", "cross-site"));
            post.setHeader(new BasicHeader("sec-fetch-user", "?1"));
            post.setHeader(new BasicHeader("upgrade-insecure-requests", "1"));
            post.setHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                return result;
            } else {
                LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 146, "POST请求失败！");
            }
        } catch (Exception e) {
            LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    public static void main(String[] args)throws Exception{
        getOrderData();

    }

    public static String getOrderData() throws Exception{
        String url = "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined";

        Map<String, Object> body = new HashMap<>();
        body.put("hex", "n");
        body.put("cartId", "2759533220026");
        body.put("sellerid", "725677994");
        body.put("cart_param", "{\"items\":[{\"cartId\":\"2759533220026\",\"itemId\":\"582408570328\",\"skuId\":\"3902332244990\",\"quantity\":1,\"createTime\":1614005135000,\"attr\":\";\"}]}");
        body.put("unbalance", "");
        body.put("delCartIds", "2759533220026");
        body.put("use_cod", "false");
        body.put("buyer_from", "cart");
        body.put("page_from", "cart");
        body.put("source_time", "1614063649209");


        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> set : body.entrySet()){
            sb.append(set.getKey()).append("=").append(set.getValue()).append("&");
        }

        String bodyStr = sb.substring(0, sb.length() -1);
        Object str = sendPost(url, bodyStr);
        System.out.println(str.toString());
        return str.toString();
    }
}
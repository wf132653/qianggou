package com.wf.qianggou.util.http;

import com.wf.qianggou.config.SysConstants;
import com.wf.qianggou.util.GetServerTimeOfTb;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取购物车相关信息
 * 需要更换的是 cookie
 *
 * @author wf
 * @date 2021年03月05日 11:03
 */
@Slf4j
public class GetOrderData {

    private String bodyStr = null;

    public static long needTime;
    private long sendPostTime = 0;
    /**
     * 2021-03-05 15:40:00 000
     */
    private static String buyDateTime;

    static {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        buyDateTime = sdf.format(date) + SysConstants.buyTime;
        LocalDateTime parse = LocalDateTime.parse(buyDateTime, SysConstants.dateTimeFormatter);
        needTime = LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private String itemId = "";

    public String getItemId() {
        return itemId;
    }

    public long getSendPostTime() {
        return sendPostTime;
    }

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
    public static String sendPost(String url, String body) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = new SSLClient();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 设置请求的内容
             */
            post.setEntity(new StringEntity(body, "UTF-8"));
            /**
             * 设置请求的报文头部
             */
            // 能识别是程序下单了，03-08，并不是能识别，而是提交订单页面需要验证一下验证码
            post.addHeader(new BasicHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"));
            post.addHeader(new BasicHeader("accept-encoding", "deflate, br"));
            post.addHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
            post.addHeader(new BasicHeader("cache-control", "max-age=0"));
            post.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            post.addHeader(new BasicHeader("Cookie", SysConstants.cookie));
            post.addHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36"));
            post.addHeader(new BasicHeader("origin", "https://cart.taobao.com"));
            post.addHeader(new BasicHeader("referer", "https://cart.taobao.com/"));
            post.addHeader(new BasicHeader("sec-ch-ua", "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\""));
            post.addHeader(new BasicHeader("sec-ch-ua-mobile", "?0"));
            post.addHeader(new BasicHeader("sec-fetch-dest", "document"));
            post.addHeader(new BasicHeader("sec-fetch-mode", "navigate"));
            post.addHeader(new BasicHeader("sec-fetch-site", "cross-site"));
            post.addHeader(new BasicHeader("sec-fetch-user", "?1"));
            post.addHeader(new BasicHeader("upgrade-insecure-requests", "1"));
            post.addHeader(new BasicHeader("host", "buy.tmall.com"));

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
                HttpEntity httpEntity = response.getEntity();

                return EntityUtils.toString(httpEntity, "GBK");
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
        new GetOrderData().getOrderData();
    }


    public String getOrderData() throws Exception {
        if (bodyStr == null) {
            String createTime = "";
            String quantity = "";
            String skuId = "";
            String itemId = "";
            String cartId = "";
            String sellerId = "";
            GetCartInfo getCartInfo = new GetCartInfo();
            Map cart = getCartInfo.getCart();
            List<Map> lists = (List<Map>) cart.get("list");
            for (Map list : lists) {
                List bundles = (List) list.get("bundles");
                Map map = (Map) bundles.get(0);
                List orders = (List) map.get("orders");
                Map order = (Map) orders.get(0);
                if (order.get("title").toString().contains(SysConstants.key)) {
                    createTime = order.get("createTime").toString();
                    quantity = ((Map) order.get("amount")).get("now").toString();
                    skuId = order.get("skuId").toString();
                    itemId = order.get("itemId").toString();
                    cartId = order.get("cartId").toString();
                    sellerId = order.get("sellerId").toString();
                    break;
                }
            }

            this.itemId = itemId;
            Map<String, Object> map = initMap();
            StringBuilder cart_patam = new StringBuilder("{\"items\":[{\"cartId\":\"\",\"itemId\":\"\",\"skuId\":\"\",\"quantity\":\"\",\"createTime\":\"\",\"attr\":\";\"}]}");
            cart_patam.insert(74, createTime);
            cart_patam.insert(58, quantity);
            cart_patam.insert(44, skuId);
            cart_patam.insert(33, itemId);
            cart_patam.insert(21, cartId);
            map.put("cart_param", cart_patam);
            map.put("cartId", cartId);
            map.put("sellerid", sellerId);
            map.put("delCartIds", cartId);

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> set : map.entrySet()) {
                sb.append(set.getKey()).append("=").append(set.getValue()).append("&");
            }

            bodyStr = sb.substring(0, sb.length() - 1);


//            long start = System.currentTimeMillis();
//            long ld = GetServerTimeOfTb.getServiceTime();
            long ld = System.currentTimeMillis();
//            long end = System.currentTimeMillis();
//            long p = end - start;
//            log.info("p = {}", p);
            log.info("ld = {}", ld);
            long sleep = needTime - ld;
            // -018 不行 604可以
            // 19:59:59.027 可以
            // 19:59:59.018 19:59:59.617 19:59:59.903 不行 20:00:00.312 可以
            // 抢购
            sleep -= 1000;

            if (sleep > 0) {
                // 时间未到，休眠一段时间再抢购，休眠时间 = 定时抢购时间 - 服务器时间 - 获取服务器时间接口 / 3
//            long sleep = needTime - ld - p / 3;
                log.info("sleep = {}", sleep);
                Thread.sleep(sleep);
            }
            if (sendPostTime == 0) {
                sendPostTime = System.currentTimeMillis();
            }

        }

        String url = "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined";
        String res = sendPost(url, bodyStr);
        int firstDataIndex = res.indexOf("orderData");
        int successIndex = res.indexOf("\"reload\":true");
        String re = res.substring(firstDataIndex + 11, successIndex + 14);
        log.info("-------------------------------");
        log.info("getOrderData = " + re);
        log.info("-------------------------------");
        return re;
    }

    private static Map<String, Object> initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("hex", "n");
        map.put("unbalance", "");
        map.put("use_cod", "false");
        map.put("buyer_from", "cart");
        map.put("page_from", "cart");
        map.put("source_time", System.currentTimeMillis());
        return map;
    }
}

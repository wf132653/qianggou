package com.wf.qianggou.util.tb_cart;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.config.SysConstants;
import com.wf.qianggou.util.SSLClient;
import com.wf.qianggou.util.ThreadPoolUtil;
import com.wf.qianggou.util.http.ConfirmOrderService2;
import com.wf.qianggou.util.http.GetOrderData;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 茅台酒的抢购
 * 更换商品抢购需要更换的是
 * cookie、购物车关键字、
 *
 * @author wf
 * @date 2021年02月22日 16:32
 */
@Slf4j
public class MaoTaiJiu {

    private static volatile boolean ss = false;
    private Object obj = new Object();

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
    public static Object sendPost(String url, String body, String path) throws Exception {
        log.info("发起抢购");
        log.info("请求的url : {}", url);

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = new SSLClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(body, "UTF-8"));

            post.addHeader(new BasicHeader("Connection", "keep-alive"));
            post.addHeader(new BasicHeader(":authority", "buy.tmall.com"));
            post.addHeader(new BasicHeader(":method", "POST"));
            post.addHeader(new BasicHeader(":path", path));
            post.addHeader(new BasicHeader(":scheme", "https"));
            post.addHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"));
            post.addHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
            post.addHeader(new BasicHeader("Cache-Control", "max-age=0"));
            post.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            // cookie 是火狐里面复制出来的，orderData 是谷歌里面复制出来的
            post.addHeader(new BasicHeader("Cookie", SysConstants.cookie));
//            post.addHeader(new BasicHeader("Origin", "https://buy.tmall.com"));
//            post.addHeader(new BasicHeader("Referer", "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined"));
            post.addHeader(new BasicHeader("Sec-Fetch-Dest", "document"));
            post.addHeader(new BasicHeader("Sec-Fetch-Mode", "navigate"));
            post.addHeader(new BasicHeader("Sec-Fetch-Site", "same-origin"));
            post.addHeader(new BasicHeader("Sec-Fetch-User", "?1"));
            post.addHeader(new BasicHeader("Upgrade-Insecure-Requests", "1"));
            post.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36"));
            post.addHeader(new BasicHeader("x-requested-with", "XMLHttpRequest"));
            post.addHeader(new BasicHeader("host", "buy.tmall.com"));

            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {

                return null;
                // 获取结果也需要时间
//                HttpEntity httpEntity = response.getEntity();
//                String result = EntityUtils.toString(httpEntity, "GBK");
//                return result;
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
        confirmOrder();
    }

    private static void confirmOrder() throws Exception {
        String preUrl = "https://buy.tmall.com";
        StringBuilder url = new StringBuilder("/auction/confirm_order.htm?x-uid=2453833782&submitref=");
        GetOrderData getOrderData = new GetOrderData();
        String orderDataStr;
        boolean success = false;
        Map<String, Object> orderDataMap = null;
        while (!success) {
            try {
                orderDataStr = getOrderData.getOrderData();

                // 从选择地址接口的参数中获取 下订单接口的 url 参数
                orderDataMap = JSONObject.toJavaObject(JSONObject.parseObject(orderDataStr), Map.class);

                Map<String, Object> dataMap = (Map<String, Object>) orderDataMap.get("data");
                Map<String, Object> submitOrderPC_1 = (Map<String, Object>) dataMap.get("submitOrderPC_1");
                Map<String, Object> hidden = (Map<String, Object>) submitOrderPC_1.get("hidden");
                Map<String, Object> extensionMap = (Map<String, Object>) hidden.get("extensionMap");
                String secretValue = extensionMap.get("secretValue").toString();
                String sparam1 = extensionMap.get("sparam1").toString();

                url.append(secretValue)
                        .append("&x-itemid=").append(getOrderData.getItemId())
                        .append("&sparam1=").append(sparam1);

                // 清除掉多余的数据
                ConfirmOrderService2.clearMap(orderDataMap);

                String endpointStr = orderDataMap.get("endpoint").toString();
                String dataStr = orderDataMap.get("data").toString();
                String linkageStr = orderDataMap.get("linkage").toString();
                String hierarchyStr = orderDataMap.get("hierarchy").toString();

                String endpointUrl = URLEncoder.encode(endpointStr, "utf-8");
                String dataUrl = URLEncoder.encode(dataStr, "utf-8");
                String linkageUrl = URLEncoder.encode(linkageStr, "utf-8");
                String hierarchyUrl = URLEncoder.encode(hierarchyStr, "utf-8");

                orderDataMap.put("endpoint", URLEncoder.encode(endpointUrl, "utf-8"));
                orderDataMap.put("data", URLEncoder.encode(dataUrl, "utf-8"));
                orderDataMap.put("linkage", URLEncoder.encode(linkageUrl, "utf-8"));
                orderDataMap.put("hierarchy", URLEncoder.encode(hierarchyUrl, "utf-8"));

                orderDataMap.put("input_charset", "utf-8");
                orderDataMap.put("praper_alipay_cashier_domain", "cashierrz41");
                orderDataMap.put("event_submit_do_confirm", "1");
                orderDataMap.put("_tb_token_", "e983eee43be30");
                orderDataMap.put("action", "/order/multiTerminalSubmitOrderAction");

//                log.info("下订单接口请求参数(json)  : {}", JSONObject.toJSONString(orderDataMap));

                success = true;
            } catch (Exception e) {
                e.printStackTrace();
                // 重试时间超过3秒，退出
                if (System.currentTimeMillis() - getOrderData.getSendPostTime() > 3000) {
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> set : orderDataMap.entrySet()) {
            sb.append(set.getKey()).append("=").append(set.getValue()).append("&");
        }

        String bodyStr = sb.substring(0, sb.length() - 1);

        log.info("开始抢购:{}", System.currentTimeMillis());

        sendPost(preUrl, url, bodyStr);
//        Object result = sendPost(preUrl, url, bodyStr);
//        log.info("下订单接口请求参数 : {}", bodyStr);

//        System.out.println("result");
//        System.out.println(result);
        if (ss) {
            log.info("抢购成功");
        } else {
            log.info("抢购失败");
        }

    }


    private static void sendPost(String preUrl, StringBuilder url, String bodyStr) throws Exception {
        while (System.currentTimeMillis() - GetOrderData.needTime < 2500) {
            ThreadPoolUtil.executor.execute(() -> {
                try {
                    sendPost(preUrl + url, bodyStr, url.toString());
//                    Object result = sendPost(preUrl + url, bodyStr, url.toString());
//                    ss = result != null && (result.toString().contains("正在创建支付宝安全链接"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
//            if (ss) {
//                break;
//            }
            Thread.sleep(250);
        }
    }


}

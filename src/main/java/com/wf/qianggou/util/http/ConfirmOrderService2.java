package com.wf.qianggou.util.http;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.config.SysConstants;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下订单接口模拟，从buy.接口的返回直接取 orderData 编一下 url 码，作为下订单的参数
 * 当前已成功实现下单，更换 cookie 即可
 * 抢购《最生活毛巾》，暂时封存代码
 *
 * @author wf
 * @date 2021年02月22日 16:32
 */
@Slf4j
public class ConfirmOrderService2 {

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
        System.out.println("请求的url : " + url);

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
            post.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36"));
            post.addHeader(new BasicHeader("x-requested-with", "XMLHttpRequest"));
            post.addHeader(new BasicHeader("host", "buy.tmall.com"));

            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                HttpEntity httpEntity = response.getEntity();

                String result = EntityUtils.toString(httpEntity, "GBK");
                return result;
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
        String url = "/auction/confirm_order.htm?x-uid=2453833782&submitref=";
        GetOrderData getOrderData = new GetOrderData();
        String orderDataStr = getOrderData.getOrderData();

        // 从选择地址接口的参数中获取 下订单接口的 url 参数
        Map<String, Object> orderDataMap = JSONObject.toJavaObject(JSONObject.parseObject(orderDataStr), Map.class);

        Map<String, Object> dataMap = (Map<String, Object>) orderDataMap.get("data");
        Map<String, Object> submitOrderPC_1 = (Map<String, Object>) dataMap.get("submitOrderPC_1");
        Map<String, Object> hidden = (Map<String, Object>) submitOrderPC_1.get("hidden");
        Map<String, Object> extensionMap = (Map<String, Object>) hidden.get("extensionMap");
        String secretValue = extensionMap.get("secretValue").toString();
        String sparam1 = extensionMap.get("sparam1").toString();

        url += secretValue;
        url += "&x-itemid=" + getOrderData.getItemId();
        url += "&sparam1=" + sparam1;

        // 清除掉多余的数据
        clearMap(orderDataMap);

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

        log.info("下订单接口请求参数(json)  : {}", JSONObject.toJSONString(orderDataMap));

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> set : orderDataMap.entrySet()) {
            sb.append(set.getKey()).append("=").append(set.getValue()).append("&");
        }

        String bodyStr = sb.substring(0, sb.length() - 1);

        Object result = sendPost(preUrl + url, bodyStr, url);
        log.info("下订单接口请求参数 : {}", bodyStr);

        System.out.println("result");
        System.out.println(result);
    }

    public static void clearMap(Map<String, Object> map) {
        map.remove("container");
        map.remove("reload");

        // 1.linkage
        Map<String, Object> linkage = (Map<String, Object>) map.get("linkage");

        linkage.remove("input");
        linkage.remove("request");
        linkage.remove("url");
        Map<String, Object> common = (Map<String, Object>) linkage.get("common");
        common.remove("queryParams");

        // 2.hierarchy
        Map<String, Object> hierarchy = (Map<String, Object>) map.get("hierarchy");
        hierarchy.remove("baseType");
        hierarchy.remove("component");
        hierarchy.remove("root");

        // 3.data  frontTracePC_1
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        data.remove("frontTracePC_1");
        data.remove("menberBenefitsFlex_1");
        data.remove("orderDesc_orderDesc_1");
        data.remove("realPayPC_1");
        data.remove("riderClauseContentFlex_riderClause_content_1");
        data.remove("riderClausePC_riderClause_1");
        data.remove("stationTabPC_1");
        data.remove("stepbarPC_1");
        data.remove("urlTransferPC_1");

        List<String> removeKeys = new ArrayList<>();
        for (Map.Entry<String, Object> set : data.entrySet()) {
            if (set.getKey().contains("orderExtLeftPC_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("orderExtPC_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("orderExtRightPC_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("orderExtUpperPC_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("orderPayLayoutPC_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("orderPayPC_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("order_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("presellDescGroupPC_presellDescGroup_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("seller_")) {
                removeKeys.add(set.getKey());
            } else if (set.getKey().contains("itemInfoPC_")) {
                Map itemInfoPC_ = (Map) set.getValue();
                Map fields = (Map) itemInfoPC_.get("fields");
                Map priceInfo = (Map) fields.get("priceInfo");
                Map valueStyles = (Map) priceInfo.get("valueStyles");
                valueStyles.remove("bold", Boolean.TRUE);
            }
        }
        for (String key : removeKeys) {
            data.remove(key);
        }
    }

}

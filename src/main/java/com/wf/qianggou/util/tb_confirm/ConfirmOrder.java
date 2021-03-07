package com.wf.qianggou.util.tb_confirm;

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

import java.util.*;

/**
 * demo_class
 * 要更换的是 ua、umidToken、token、key
 *
 * @author wf
 * @date 2021年03月06日 20:42
 */
@Slf4j
public class ConfirmOrder {

    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    private static String submitref = "";

    /**
     * 发送 https 的 POST请求
     *
     * @param url  url 地址
     * @param body
     * @return JSON或者字符串
     * @throws Exception
     */
    public static String sendPost(String url, String body) throws Exception {
        System.out.println("请求的url = " + url);
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
            post.addHeader(new BasicHeader("Connection", "keep-alive"));
            post.addHeader(new BasicHeader(":authority", "h5api.m.taobao.com"));
            post.addHeader(new BasicHeader(":method", "POST"));
//            post.addHeader(new BasicHeader(":path", path));
            post.addHeader(new BasicHeader(":scheme", "https"));
            post.addHeader(new BasicHeader("Accept", "application/json"));
            post.addHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
            post.addHeader(new BasicHeader("Cache-Control", "max-age=0"));
            post.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            // cookie 是火狐里面复制出来的，orderData 是谷歌里面复制出来的
            post.addHeader(new BasicHeader("Cookie", "https://main.m.taobao.com"));
            post.addHeader(new BasicHeader("origin", SysConstants.cookie));
            post.addHeader(new BasicHeader("referer", "https://main.m.taobao.com/"));
            post.addHeader(new BasicHeader("sec-ch-ua", "\"Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\""));
            post.addHeader(new BasicHeader("sec-ch-ua-mobile", "?0"));
            post.addHeader(new BasicHeader("sec-fetch-dest", "empty"));
            post.addHeader(new BasicHeader("sec-fetch-mode", "cors"));
            post.addHeader(new BasicHeader("sec-fetch-site", "same-site"));
            post.addHeader(new BasicHeader("Upgrade-Insecure-Requests", "1"));
            post.addHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36"));
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
        confirmOrder();
    }

    public static void confirmOrder() throws Exception {
        GetParam getParam = new GetParam();
        Map param = getParam.getParam();

        // 清除 param
        Map data = cleanMap(param);

        String dataStr = JSONObject.toJSONString(data);
        System.out.println("dataStr = " + dataStr);
        String body = "data=" + dataStr;

        // 还有一个 sign 参数待解决
//        String url = "https://h5api.m.taobao.com/h5/mtop.trade.order.create.h5/4.0/?jsv=2.6.1&appKey=12574478&sign=53316375024dc6e3ae3c7986671d265d&v=4.0&post=1&type=originaljson&timeout=15000&dataType=json&isSec=1&ecode=1&api=mtop.trade.order.create.h5&ttid=%23t%23ip%23%23_h5_2019&H5Request=true&submitref=";
        String url = "https://h5api.m.taobao.com/h5/mtop.trade.order.create.h5/4.0/?jsv=2.6.1&appKey=12574478&sign=1dafd8b0d7422e81cdcd17d5a5957762&v=4.0&post=1&type=originaljson&timeout=15000&dataType=json&isSec=1&ecode=1&api=mtop.trade.order.create.h5&ttid=#t#ip##_h5_2019&H5Request=true&submitref=";
        url += submitref;
        url += "&t=" + System.currentTimeMillis();

        String result = sendPost(url, body);

        log.info("下订单接口请求参数 : {}", body);

        System.out.println("result");
        System.out.println(result);

    }

    private static Map cleanMap(Map map) {
        Map root = (Map) map.get("data");

        // 0.
        Map global = (Map) root.get("global");
        submitref = global.get("secretValue").toString();

        // 1.endpoint
        Map endpoint = (Map) root.get("endpoint");

        // 2.hierarchy
        Map hierarchy = (Map) root.get("hierarchy");
        hierarchy.remove("component");
        hierarchy.remove("root");
        hierarchy.remove("baseType");

        // 3.linkage
        Map linkage = (Map) root.get("linkage");
        linkage.remove("input");
        linkage.remove("request");
        Map common = (Map) linkage.get("common");
        common.remove("queryParams");
        common.remove("structures");

        // 4. data
        Map<String, Object> data = (Map<String, Object>) root.get("data");

        Map address_1 = (Map) data.get("address_1");
        address_1.put("id", "1");
        address_1.put("tag", "address");
        Map fields = (Map) address_1.get("fields");
        fields.put("cornerType", "both");

        Map anonymous_1 = (Map) data.get("anonymous_1");
        anonymous_1.put("id", "1");
        anonymous_1.put("tag", "anonymous");

        Map confirmOrder_1 = (Map) data.get("confirmOrder_1");
        confirmOrder_1.put("id", "1");
        confirmOrder_1.put("tag", "confirmOrder");

        Map ncCheckCode_ncCheckCode1 = (Map) data.get("ncCheckCode_ncCheckCode1");
        ncCheckCode_ncCheckCode1.put("id", "ncCheckCode1");
        ncCheckCode_ncCheckCode1.put("tag", "ncCheckCode");

        Map submitOrder_1 = (Map) data.get("submitOrder_1");
        submitOrder_1.put("id", "1");
        submitOrder_1.put("tag", "submitOrder");

        data.remove("sesameBlock_1");
        data.remove("cuntaoBlock_1");
        data.remove("topReminds_1");
        data.remove("submitBlock_1");
        data.remove("addressBlock_1");
        data.remove("confirmPromotionAndService_1");
        data.remove("agcPaySubmitBtnFloatImg_1");

        List<String> removeList = new ArrayList<>();
        for (Map.Entry<String, Object> set : data.entrySet()) {
            if (set.getKey().contains("item_")) {
                Map item_ = (Map) set.getValue();
                item_.put("id", set.getKey().substring(5));
                item_.put("tag", "item");
            } else if (set.getKey().contains("itemInfo_")) {
                Map itemInfo_ = (Map) set.getValue();
                itemInfo_.put("id", set.getKey().substring(9));
                itemInfo_.put("tag", "itemInfo");
            } else if (set.getKey().contains("invoice_")) {
                Map invoice_ = (Map) set.getValue();
                invoice_.put("id", set.getKey().substring(8));
                invoice_.put("tag", "invoice");
            } else if (set.getKey().contains("deliveryDate_")) {
                Map deliveryDate_ = (Map) set.getValue();
                deliveryDate_.put("id", set.getKey().substring(13));
                deliveryDate_.put("tag", "deliveryDate");
            } else if (set.getKey().contains("service_yfx_")) {
                Map service_yfx_ = (Map) set.getValue();
                service_yfx_.put("id", set.getKey().substring(8));
                service_yfx_.put("tag", "service");
            } else if (set.getKey().contains("order_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("maochaoModifyAddressLabel_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("quantity_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("homeDeliveryLabel_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("alicomItemBlock_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("orderInfo_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("serviceGroupRoot_yfx_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("orderPay_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("maochaoDeliveryLabel_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("deliveryMethod_")) {
                removeList.add(set.getKey());
            } else if (set.getKey().contains("serviceCOBlock_yfx_")) {
                removeList.add(set.getKey());
            }
        }

        for (String key : removeList) {
            data.remove(key);
        }

        Map<String, Object> newData = new HashMap<>();
        newData.put("data", data);
        newData.put("endpoint", endpoint);
        newData.put("linkage", linkage);
        newData.put("hierarchy", hierarchy);

        // 提交的body
        Map<String, Object> body = new HashMap<>();
        body.put("params", newData);
        String ua = "140#ggXr/BcBzzZTizo23xzF4pN8s7absGw9whDBLifd/+gZdBAXZTsIxXHrnuGaORGLcVbLe3hqzznTmifjZSuzzj8TIHoqlQzx2DD3VthqzFnI01qcU6bxzoObV2EqlazDCJLHxrvZrI7ZbYNctsyeShGxEgCrJw6dr+xsffNTY/b5fGB4Cd9XYaNgjtWmKxxeVuNYvyd4NY4fIsfiClX/o/YJKD7K2X02EnDqjZYu/8I7kmcPJ8qFt7t9fIu8kYrif46RNuqrMdY64tjM3Yt4wu9Tp2G8NQQ0TGp6QtHBWbydr6F12YH9rd4/OK4q/GWf9cUmJWUqTOj8ZiuqK0VTD5OW6GlyB6fT1lKX7Rk7+/El1ieZRrrdFjVe44gonn2Z0bMHNkIdNB3g/E02XpI0I6A6QFZzkkDDZhK6Fabis53DYUeVrpjSbHZt3Sk4txb7uF/Vo/gf/VKz1LSPLdJQ6P5ZPRxkJkSGRZQ7MQrPEoONjlninMKSaj4EasOyn07j8KtomE9rvKbRAt11cgWTssrg1uiJhSwPMM08NPabZHPIDkLDcWjZ6hsEdZSjvR2Mu4u9eZoVi/uJkSiVKfh599JG8S7L02DiKxja9YhgyCLHnHvAd7PEVb+MUbvFGRoRObamML3XisHhwrJdsVRDIxgqos9wBmJB4F82Prfwd67XoJf8i7a6Zwcm6p47+Zg2Jg+NMEgoN0jS8LTrd36+4uEm87KICiPSs7sAmIcumkHpJu1nk/GpNrkiJNJzATNzvtY6ukizOG2rBGjY7qWl5/2rlpJM3rzawu0Nq0CVdCnsbcavs2zy2z6FMVLBRYY65Y29UnygJzj7WUJ9JJ3QvXYjII4Wcs4hdxUVeSA0XsLOQUwQyl3Cn7KeOZmkeH+g2/y0HzBAOujt/FhfRcZjfI4uyffO0xIdMVa2mxMuHmy/T6vk/zP4dwm0VSnLBVtAn9ENNkKGZqjSgTeEiz41xQZ8EPID5jHw6NYaonXo2heUybal/tb5YtwmUB5saYyN1AZCOyVmSDcVojEa4P8dCSVV/LcI1VQ/0+wb51NEg8r/9DpzEu0lgHmGRsLkuH1d2g3vKSrKQg9HdZy5dp7XuCvfde86jFeTKYwVx28zgBtKq1DBJsnJ0TllzUln/agrNh6CUT/DGq0dpGmRItgYI9OMr5LkZGREpv8Pm0Ui8KIdyV9xpqLTlTgGtVDh5DQRgNaYOr+mToYVCCq1AJIkSaNupvvX+MhVlxMUkTtsxqVOgfWYjBmbCqKb8nxIR6y9ZDr7PlxUpU8AVHdnNxbggbvP0Kmvv/r7nQC/UDd6ML7e2KtDpxH7wuu2RGX3lQ2yww4lrRYCgrmTVcCPey5cyTMoXaCLObLV4KcdlglvSu1OTSb/YJEl6ZARyFJwVTB53yue4E33w8ElXx6CdhClCfPQgmt+kQnHIDvGxPZJ4ZGYIt+F8GRcYeJpnnkDJUl/QF7mkx4xpnYLYKup+Ddd24NPREWHw1DhrE37WAOmYBI39j9EzF==";
        String umidToken = "T2gAl9KZcjctP44Gb45BJRLUK6t-fb-fsLV9dS6QHo_l7PZT0zq1vgW9aOfdws1Vc7I=";
        body.put("ua", ua);
        body.put("umidToken", umidToken);

        return body;
    }


}

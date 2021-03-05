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
            post.setHeader(new BasicHeader("cookie", "_uab_collina=161413318980644604583123; ucn=center; _bl_uid=L5k60l9yh4daagnORyCsmR8xdeFa; _samesite_flag_=true; cookie2=19986533f8b4ab3a058294eb4e19e07e; t=a51b8b7d7a650d13d6143d0ceeabd60c; thw=cn; enc=xam7T7YitMXGKFwifcY%2BeTrgGeiSSFnblXZTBKYbCRaUsCYEqLnZARxWBeWWcw%2FJWAx7DAgxjWWwx4%2FwagTjrg%3D%3D; tk_trace=oTRxOWSBNwn9dPyorMJE%2FoPdY8zfvmw%2Fq5v21Uhul0tWM%2F3IFOmkAQHOu4HVDVXY6E6MTGC9i9lhsA1zqc2h%2BDOmYA6qd19S9MfdmNvNpkF1MVKjsJ6XNmR%2Bs1rLJig0RcUymar4MV7fIGFRtwH7sp8Nk4AG5ROICyTxWUD6sZlMIZQS10KUgCD7eoOvtTTOMi8GOg7%2BBTikjrMZZvuqDpGXatABqSOKBoK7dWEM1UZIBfwljlXwbcqle73wGBmqKCJqJ4z3eTZp%2Foa2q1QXT1qmZArjJw%3D%3D; cna=vswvGMk9ymUCAbaWP+aWkFr3; lgc=%5Cu5C0F%5Cu98DE546466464; dnk=%5Cu5C0F%5Cu98DE546466464; tracknick=%5Cu5C0F%5Cu98DE546466464; v=0; hng=CN%7Czh-CN%7CCNY%7C156; _tb_token_=e983eee43be30; ctoken=082MAFpGs5NZlIaO3jvPrhllor; mt=ci=1_1; _m_h5_tk=3f3861f5244f92f207c99bde97441b15_1614835312765; _m_h5_tk_enc=a0a807ac84177af555a7693bfdfa798b; xlly_s=1; unb=2453833782; uc3=id2=UUwVZ%2FVd30BdBQ%3D%3D&vt3=F8dCuAVnQZqWcnWqJ9c%3D&lg2=UtASsssmOIJ0bQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D; csg=65e6652d; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; sgcookie=E100y%2FkF1Lmtom5mY5S%2Bt5jA71CNJsjXyOsBcEgXlcyfnxZquSSy8N4JQ31840CJI3cL%2BKuRmr81sESVdwPqORLHaA%3D%3D; skt=de415e393d916bdd; existShop=MTYxNDg0NjA5Mg%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDMBKtv%2B3B&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDOD51Fqj%2B; _cc_=V32FPkk%2Fhw%3D%3D; _l_g_=Ug%3D%3D; sg=424; _nk_=%5Cu5C0F%5Cu98DE546466464; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; uc1=cookie14=Uoe1hg5UjPlGWQ%3D%3D&cookie16=UIHiLt3xCS3yM2h4eKHS9lpEOw%3D%3D&cookie15=WqG3DMC9VAQiUQ%3D%3D&existShop=true&pas=0&cookie21=U%2BGCWk%2F7p4mBoUyS4plD&cart_m=0; tfstk=cYeAByMhCabD-um8YSCk5-6otlIhZ2YtriiWBMjOAYulYq9OiT23pOJeG4YxegC..; l=eBScYSngOGNva6SCBOfZhurza779OIRA_uPzaNbMiOCPOufH5SyOW6NvqwLMCnGVh6fyR3Rj0APyBeYBqIccSQLrQLBHiKMmn; isg=BFpa8onZ7hUnTl1NcWbsRh8BqwB8i95l7OyrPGTTJ-241_oRTBrVdLUlo6PLAFb9"));
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
        // 总结，同一个商品，就三个地方的 cartId 不一样，source_time，createTime 不一样
        // 同一个商品，url的 spm 参数是一样的
        // 不同商品，除了上述以外，还有不一样的是：itemId、skuId、sellerId
        String url = "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined";

        Map<String, Object> body = new HashMap<>();
        body.put("hex", "n");
        body.put("cartId", "2792812663850");// 不一样
        body.put("sellerid", "725677994");// 同一个商品是一样的
        // 同一个商品，里面的cartId不一样
        body.put("cart_param", "{\"items\":[{\"cartId\":\"2792812663850\",\"itemId\":\"582408570328\",\"skuId\":\"3902332244990\",\"quantity\":1,\"createTime\":1614908068000,\"attr\":\";op:1900;dpbUpgrade:0;cityCode:510100;\"}]}");
        body.put("unbalance", "");
        body.put("delCartIds", "2792812663850");
        body.put("use_cod", "false");
        body.put("buyer_from", "cart");
        body.put("page_from", "cart");
        // 肯定都不一样，todo 暂时没找到来源
        body.put("source_time", "1614908523825");
        // spm a1z0d.6639537.1997525049.1.36697484rhpf20
        //     1z0d.6639537.1997525049.1.36697484rhpf20
        // pm_id 1501036000a02c5c3739
        //       1501036000a02c5c3739


        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> set : body.entrySet()){
            sb.append(set.getKey()).append("=").append(set.getValue()).append("&");
        }

        String bodyStr = sb.substring(0, sb.length() -1);
        String res = sendPost(url, bodyStr).toString();
        int orderDataIndex = res.indexOf("orderData");
        int reloadIndex = res.indexOf("\"reload\":true");
        String re = res.substring(orderDataIndex + 11, reloadIndex + 14);
        System.out.println("----------------------------------");
        System.out.println(re);
        System.out.println("----------------------------------");
        return re;
    }
}
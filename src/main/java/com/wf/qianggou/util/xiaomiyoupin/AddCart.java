package com.wf.qianggou.util.xiaomiyoupin;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月16日 15:56
 */
@Slf4j
public class AddCart {

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

        addCart("", "294129");

    }


    public static boolean addCart(String cookie, String pid) throws Exception {
        StringBuilder s = new StringBuilder("[{},{\"cartType\":1,\"products\":[{\"pid\":\"\",\"pigeon\":\"{\\\"spm\\\":{\\\"source\\\":\\\"PC\\\",\\\"session\\\":\\\"17839f351c0-0396463bd80327-2064\\\",\\\"addcart_time\\\":1615879955000,\\\"network\\\":\\\"unknown\\\",\\\"os\\\":\\\"Windows\\\",\\\"device\\\":\\\"Chrome\\\",\\\"uuid\\\":\\\"1782442911a545-09446ae2cfd82e-5771133\\\",\\\"did\\\":\\\"1782442911a545-09446ae2cfd82e-5771133\\\",\\\"pt\\\":\\\"PC\\\",\\\"version\\\":\\\"\\\",\\\"channel\\\":\\\"\\\",\\\"oaid\\\":\\\"\\\",\\\"spmref\\\":\\\"YouPinPC.$SearchFilter$1.search_list.11.5386887\\\",\\\"spm\\\":{\\\"b\\\":\\\"$Detail$_140819\\\",\\\"c\\\":\\\"buy\\\"},\\\"scm\\\":\\\"\\\",\\\"iid\\\":\\\"g=140819\\\"},\\\"fenxiao\\\":{\\\"source_id\\\":\\\"\\\",\\\"mijiasn\\\":\\\"\\\"}}\",\"cartSource\":\"common\",\"consumption\":1}]}]");
        // 下面这个时间是周五10点 todo 临时的，记得换成下面这个
//        StringBuilder s = new StringBuilder("[{},{\"cartType\":1,\"products\":[{\"pid\":\"\",\"pigeon\":\"{\\\"spm\\\":{\\\"source\\\":\\\"PC\\\",\\\"session\\\":\\\"17839f351c0-0396463bd80327-2064\\\",\\\"addcart_time\\\":1616119200000,\\\"network\\\":\\\"unknown\\\",\\\"os\\\":\\\"Windows\\\",\\\"device\\\":\\\"Chrome\\\",\\\"uuid\\\":\\\"1782442911a545-09446ae2cfd82e-5771133\\\",\\\"did\\\":\\\"1782442911a545-09446ae2cfd82e-5771133\\\",\\\"pt\\\":\\\"PC\\\",\\\"version\\\":\\\"\\\",\\\"channel\\\":\\\"\\\",\\\"oaid\\\":\\\"\\\",\\\"spmref\\\":\\\"YouPinPC.$SearchFilter$1.search_list.11.5386887\\\",\\\"spm\\\":{\\\"b\\\":\\\"$Detail$_140819\\\",\\\"c\\\":\\\"buy\\\"},\\\"scm\\\":\\\"\\\",\\\"iid\\\":\\\"g=140819\\\"},\\\"fenxiao\\\":{\\\"source_id\\\":\\\"\\\",\\\"mijiasn\\\":\\\"\\\"}}\",\"cartSource\":\"common\",\"consumption\":1}]}]");
        s.insert(38, pid);
        String url = "https://www.xiaomiyoupin.com/mtop/order/cart/add";
        String res = sendPost(url, s.toString(), cookie);
        Map map = JSONObject.parseObject(res, Map.class);
        return (Boolean) ((Map) ((List) ((Map) map.get("data")).get("result")).get(0)).get("result");
    }
}

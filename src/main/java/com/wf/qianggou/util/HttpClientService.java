package com.wf.qianggou.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * HttpClient发送GET、POST请求
 *
 * @author wf
 * @date 2020/03/25
 */
public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);
    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送GET请求
     *
     * @param url     请求url
     * @param isHttps 是否是 https 请求
     * @return JSON或者字符串
     * @throws Exception 异常
     */
    public static String sendGet(String url, boolean isHttps) throws Exception {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // http 和 https
        CloseableHttpClient client;
        if (isHttps) {
            client = new SSLClient();
        } else {
            client = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // taobao 登录的 cookie
            httpGet.setHeader(new BasicHeader("Cookie", "cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; dnk=%5Cu5C0F%5Cu98DE546466464; lgc=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; tk_trace=1; login=true; sm4=510114; xlly_s=1; uc1=cookie16=URm48syIJ1yk0MX2J7mAAEhTuw%3D%3D&existShop=true&cookie14=Uoe1gWCdKpY9eg%3D%3D&cookie15=VT5L2FSpMGV7TQ%3D%3D&pas=0&cart_m=0&cookie21=W5iHLLyFeYZ1WM9hVLhR; uc3=lg2=V32FPkk%2Fw0dUvg%3D%3D&vt3=F8dCuASkC4Z8qCFEJEQ%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLE6eTkfJED&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSEa8bPsRU%2B; _l_g_=Ug%3D%3D; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E1007tm019IKlHgf39K11cowFrXjgBaXHpIXn3hXP%2BNrAfhX%2FW1gDg4Ibq%2BAaDG57yEIs4w8Xa52a2TO63nF2en%2FoQ%3D%3D; sg=424; csg=f8b8e6b0; _m_h5_tk=ed80d80afba091b02c0aa5d5f0b6641c_1613738523298; _m_h5_tk_enc=d2f9c15b3a5dd3faff6e42a951a2baa6; tfstk=cIc1BRZyDGj_C39V_VTF_9BRIwFcZI87oOaS5Au1tffyBug1ijfzVBg_ni5Ley1..; l=eBP-ZQ7PO6NKKnT8KOfwourza77OSIRAguPzaNbMiOCPOG5H5HsRW6ihVWYMC3GVhs1kR3Rj0APzBeYBq3xonxvtUR-DrUDmn; isg=BFhY86wojBREV58XnIYVmCM9KYbqQbzLokZpepJJpBNGLfgXOlGMW24LZWUdPXSj"));
            // 执行请求
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            } else {
                LOGGER.error("HttpClientService-line: {}, errorMsg{}", 97, "GET请求失败！");
            }
        } catch (Exception e) {
            LOGGER.error("HttpClientService-line: {}, Exception: {}", 100, e);
        } finally {
            if (response != null) {
                response.close();
            }
            client.close();
        }
        return null;
    }

    /**
     * 发送POST请求
     *
     * @param url               url 地址
     * @param nameValuePairList
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPost(String url, List<NameValuePair> nameValuePairList) throws Exception {
        JSONObject jsonObject = null;
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
             * 包装成一个Entity对象
             */
            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
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
                try {
                    jsonObject = JSONObject.parseObject(result);
                    return jsonObject;
                } catch (Exception e) {
                    return result;
                }
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
}
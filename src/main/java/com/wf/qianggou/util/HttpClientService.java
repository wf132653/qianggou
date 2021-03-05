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
            httpGet.setHeader(new BasicHeader("Cookie", "_samesite_flag_=true; cookie2=19986533f8b4ab3a058294eb4e19e07e; t=a51b8b7d7a650d13d6143d0ceeabd60c; enc=xam7T7YitMXGKFwifcY%2BeTrgGeiSSFnblXZTBKYbCRaUsCYEqLnZARxWBeWWcw%2FJWAx7DAgxjWWwx4%2FwagTjrg%3D%3D; tk_trace=oTRxOWSBNwn9dPyorMJE%2FoPdY8zfvmw%2Fq5v21Uhul0tWM%2F3IFOmkAQHOu4HVDVXY6E6MTGC9i9lhsA1zqc2h%2BDOmYA6qd19S9MfdmNvNpkF1MVKjsJ6XNmR%2Bs1rLJig0RcUymar4MV7fIGFRtwH7sp8Nk4AG5ROICyTxWUD6sZlMIZQS10KUgCD7eoOvtTTOMi8GOg7%2BBTikjrMZZvuqDpGXatABqSOKBoK7dWEM1UZIBfwljlXwbcqle73wGBmqKCJqJ4z3eTZp%2Foa2q1QXT1qmZArjJw%3D%3D; cna=vswvGMk9ymUCAbaWP+aWkFr3; lgc=%5Cu5C0F%5Cu98DE546466464; dnk=%5Cu5C0F%5Cu98DE546466464; tracknick=%5Cu5C0F%5Cu98DE546466464; mt=ci=1_1; hng=CN%7Czh-CN%7CCNY%7C156; _tb_token_=e983eee43be30; _m_h5_tk=7c0dfbff15449a5bc9e1de71ce79f325_1614571954036; _m_h5_tk_enc=86ec0524195f1be1c1b7655fba6e8100; xlly_s=1; sgcookie=E100vzyyYznz7zQbs7U4VNk4%2BztYsrOXeQr6K%2BwFfBCVz%2Fb1EExQVSABBIUW2fU6HXuB9MeqRFkx1CaPbiwTWx%2FCSg%3D%3D; unb=2453833782; uc3=id2=UUwVZ%2FVd30BdBQ%3D%3D&lg2=U%2BGCWk%2F75gdr5Q%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&vt3=F8dCuAViyO%2BY4%2Fk9g1A%3D; csg=336fe856; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; skt=cf65d2ab44b16d12; existShop=MTYxNDU2MjYwMQ%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDPXQexSmk&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDNQHWvgh4; _cc_=WqG3DMC9EA%3D%3D; _l_g_=Ug%3D%3D; sg=424; _nk_=%5Cu5C0F%5Cu98DE546466464; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; uc1=cookie14=Uoe1hgOyQCZLZQ%3D%3D&pas=0&cart_m=0&existShop=true&cookie15=URm48syIIVrSKA%3D%3D&cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&cookie21=V32FPkk%2FgihF%2FS5nrepr; isg=BAoK42BzXnnheu1dIZY8Vu_xW_Cs-45VPJz7zJRDtd3pR6oBfIrjZlu9V7Obtwbt"));
            httpGet.setHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36"));
            httpGet.setHeader(new BasicHeader("referer", "https://detail.tmall.com/"));
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
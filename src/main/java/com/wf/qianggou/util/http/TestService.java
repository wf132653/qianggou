package com.wf.qianggou.util.http;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.HttpClientService;
import com.wf.qianggou.util.SSLClient;
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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下订单页面-选择地址接口
 *
 * @author wf
 * @date 2021年02月22日 10:19
 */
public class TestService {

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
            httpGet.addHeader(new BasicHeader("Cookie", "cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _m_h5_tk=fec3a6d5b3784dcf6378583603a69857_1613919958057; _m_h5_tk_enc=a042d808838ac0656aeee279e1084cdc; xlly_s=1; login=true; uc1=cookie15=V32FPkk%2Fw0dUvg%3D%3D&cookie21=UtASsssmeW6lpyd%2BBROh&cookie14=Uoe1gW4o7ht7LA%3D%3D&pas=0&cookie16=UtASsssmPlP%2Ff1IHDsDaPRu%2BPw%3D%3D&cart_m=0&existShop=true; uc3=vt3=F8dCuASiek0qC45jnk8%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLE5%2FTr9KO0&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSEZeQTXnch; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100Bs1xaiIgmi%2BfsSe95re%2Bv1bEmYmPPnsBGubTJ7zAE5Ay%2BrsiF%2BR1k6FWNTsHm%2B%2FE7Fv1IGDzgyA6Is8fxoE6QQ%3D%3D; sg=424; csg=1de83e78; sm4=510114; l=eBP-ZQ7PO6NKKWWjBO5B-urza779XQdXflVzaNbMiInca6wRHF1obNCIom2H2dtjgtfx2eKyGmkaaR3XWb4LRx6CKvtgjqf1zV965; tfstk=crt5BNfZ97V7HeuU4LM44nbKIKQfaBM1Kz66FUSMCDtaBROhMsX3b1iXFwVUDNBf.; isg=BLq6zuiKjgbwjT1xKpzXpp0HC-Dcaz5FDAxL3MSwlM0Yt1XxrflAVWGBB0NrHLbd"));
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
     * 发送 https 的 POST请求
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
            client = new SSLClient();
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
             * 设置请求的报文头部
             */
            post.setHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ3ZiIsImF1dGhvcml0eSI6WyJ2YWNjaW5lQ29uZmlnOnBhZ2UiLCIxIiwibmV3czp1cGRhdGUiLCJhbGxvY2F0aW9uUnVsZTp1cGRhdGUiLCJ2YWNjaW5lRGljOnBhZ2UiLCJkaWNDb25maWc6dXBkYXRlIiwiZmlybTphZGRBbmRVcGRhdGVTeXNGaXJtIiwib3JnOnNhdmUiLCIxIiwidmFjY2luZU5lZWQ6c2F2ZSIsIm1lbnU6c2F2ZSIsImZpcm06ZGVsZXRlU3lzRmlybSIsImFsbG9jYXRpb25SdWxlOnF1ZXJ5IiwicGFyYW1Hcm91cDpzYXZlUGFyYW1Hcm91cCIsIm5ld3M6c2F2ZSIsInZhY2NpbmVEaWM6c2F2ZSIsInZhY2NpbmVOZWVkOmVuYWJsZSIsInZhY2NpbmVDb25maWc6ZW5hYmxlIiwicm9sZTpzYXZlIiwicGFyYW06c2F2ZVBhcmFtIiwib3JkZXI6Y2FuY2VsIiwiYWxsb2NhdGlvbk1hbmFnZTpzYXZlIiwiYWxsb2NhdGlvbkluZm86ZW5hYmxlIiwiZGljQ29uZmlnOmRlbGV0ZSIsInVzZXI6YWRkIiwiaG9saWRheTphZGRIb2xpZGF5IiwiYWxsb2NhdGlvbk1hbmFnZTpyZW1vdmUiLCJ2YWNjaW5lQ29uZmlnOmxpc3QiLCJvcmc6ZGVsZXRlIiwidmFjY2luZURpYzplbmFibGUiLCJkaWNDb25maWc6c2F2ZSIsImFsbG9jYXRpb25JbmZvOmVkaXQiLCJwYXJhbUdyb3VwOnVwZGF0ZVBhcmFtR3JvdXAiLCJob2xpZGF5OmRlbGV0ZUhvbGlkYXkiLCJmaXJtOnVwU3RhdGUiLCJyb2xlOmVuYWJsZSIsInBhcmFtOnVwZGF0ZVBhcmFtIiwidmFjY2luZU5lZWQ6cmVtb3ZlIiwibmV3czpyZW1vdmUiLCJtZW51OmRldGFpbCIsImFyZWE6c2F2ZUFyZWEiLCJ2YWNjaW5lQ29uZmlnOmFkZCIsInBhcmFtOmRlbGVjdFBhcmFtIiwiMiIsIm1lbnU6cmVtb3ZlIiwidXNlcjpyZW1vdmUiLCJyb2xlOnJlbW92ZSIsIm9yZzplbmFibGUiLCJwYXJhbUdyb3VwOmRlbGVjdFBhcmFtR3JvdXAiLCJ1c2VyOnVwZGF0ZSIsImFyZWE6dXBkYXRlQXJlYSIsInZhY2NpbmVEaWM6cmVtb3ZlIiwiaG9saWRheTppc09wZW5lZCIsInJvbGU6ZGV0YWlsIiwibWVudTplbmFibGUiLCJhcmVhOmlzT3BlbmVkIiwicGFyYW06aXNPcGVuZWQiLCJvcmc6ZGV0YWlsIiwicGFyYW1Hcm91cDppc09wZW5lZCIsInZhY2NpbmVEaWM6ZGV0YWlsIiwidmFjY2luZUNvbmZpZzpyZW1vdmUiLCJyb2xlOmFsbFJvbGVzIl0sImV4cCI6MTYxMzk4MjExOSwiaWF0IjoxNjEzOTU2OTE5LCJzdWIiOiJ3ZiJ9.UwE9fj4VBBP96CXKEGf0OrysO-qkLJRpPfdaHykUxAo");
            post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");


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


    public static void main(String[] args) throws Exception{
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("boa", "bob");
        BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("boc", "bod");
        List<NameValuePair> list = new ArrayList<>();
        list.add(basicNameValuePair);
        list.add(basicNameValuePair1);
        String url = "http://localhost:8081/vaccine/role/page";
        JSONObject str = (JSONObject) sendPost(url, list);
        Map re = str.toJavaObject(Map.class);
        System.out.println(re.toString());


    }
}

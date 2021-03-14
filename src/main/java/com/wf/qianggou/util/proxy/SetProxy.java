package com.wf.qianggou.util.proxy;

import com.wf.qianggou.util.HttpClientService;
import com.wf.qianggou.util.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月11日 17:20
 */
public class SetProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetProxy.class);
    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送GET请求
     *
     * @return JSON或者字符串
     * @throws Exception 异常
     */
    public static String sendGet() throws Exception {
        String url = "https://gwgp-kk6owjrbujz.i.bdcloudapi.com/aladdin/ip/query";
        CloseableHttpClient client = new SSLClient();
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // taobao 登录的 cookie
            httpGet.setHeader(new BasicHeader("timestamp", "2021-03-12T01:33:27Z@cf63b3599edfca028c50c95816377d28"));
            // 执行请求
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();

            if (200 == statusCode) {
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

    public static void main(String[] args) throws Exception {

        InetSocketAddress addr = new InetSocketAddress("192.168.0.254", 8080);
        // Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr); // Socket 代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

        System.setProperty("http.proxyHost", "101.206.166.95");
        System.setProperty("https.proxyHost", "101.206.166.95");
        System.setProperty("http.proxyPort", "443");
        System.setProperty("https.proxyPort", "443");

        String res = sendGet();
        System.out.println(res);


    }
}

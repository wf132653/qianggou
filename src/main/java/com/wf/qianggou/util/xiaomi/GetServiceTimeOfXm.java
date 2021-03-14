package com.wf.qianggou.util.xiaomi;

import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月14日 18:02
 */
@Slf4j
public class GetServiceTimeOfXm {

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
    public static Long sendGet(String url) throws Exception {
//        System.out.println("GetSalt的url = " + url);
        CloseableHttpClient client;
        client = new SSLClient();
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
            httpGet.setHeader("origin", "https://www.mi.com");
            httpGet.setHeader("referer", "https://www.mi.com/");
            httpGet.setHeader("cookie", XiaoMiConstant.COOKIE);
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity, "UTF-8");
                return Long.parseLong(res.substring(15));
            } else {
                log.error("GET请求失败！");
            }
        } catch (Exception e) {
            log.error("请求错误信息 : {}", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
            client.close();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        getServiceTime();
    }

    public static String getServiceTime() throws Exception {
        String url = "https://time.hd.mi.com/gettimestamp";
        long time = sendGet(url) * 1000;
        System.out.println(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(time);
        System.out.println(sdf.format(date));

        return null;
    }
}

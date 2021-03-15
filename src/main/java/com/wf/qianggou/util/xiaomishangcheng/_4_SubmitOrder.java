package com.wf.qianggou.util.xiaomishangcheng;

import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月14日 17:43
 */
@Slf4j
public class _4_SubmitOrder {

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
        log.info("发起抢购");
        log.info("请求的url : {}", url);

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = new SSLClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(body, "UTF-8"));

            post.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
            post.setHeader("origin", "https://www.mi.com");
            post.setHeader("referer", "https://www.mi.com/");
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("cookie", cookie);

            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                String res = EntityUtils.toString(response.getEntity(), "UTF-8");
                return res;
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
        submitOrder("");
    }

    public static void submitOrder(String cookie) throws Exception {
        long s = System.currentTimeMillis();
        while (System.currentTimeMillis() - s < 2000){
            try {
                _3_AddCart.addCart(cookie);
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String url = "https://api2.order.mi.com/buy/submit";
        StringBuilder sb = new StringBuilder();
        sb.append("quick_order=")
                .append("&riskToken=")
                .append("&address_id=11000000169758167")
                .append("&pay_id=")
                .append("&invoice_type=4")
                .append("&shipment_id=2")
                .append("&coupons_value=")
                .append("&coupons_type=")
                .append("&invoice_title=个人")
                .append("&invoice_tel=")
                .append("&invoice_email=")
                .append("&use_red_packet=1")
                .append("&use_shopping_coupon=false");
        String res = sendPost(url, sb.toString(), cookie);
        System.out.println("提交订单的返回 = " + res);
    }

}

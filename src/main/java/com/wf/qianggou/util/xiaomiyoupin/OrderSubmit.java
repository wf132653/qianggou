package com.wf.qianggou.util.xiaomiyoupin;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.config.SysConstants;
import com.wf.qianggou.util.SSLClient;
import com.wf.qianggou.util.xiaomishangcheng.GetServiceTimeOfXm;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月16日 11:02
 */
@Slf4j
public class OrderSubmit {

    private static Map<Integer, String> k = new HashMap<>();

    public static long needTime;
    /**
     * 2021-03-05 15:40:00 000
     */
    private static String buyDateTime;


    static {
        k.put(0, "Redmi 9 碳素黑 6GB+128GB");
        k.put(1, "Redmi 9 墨黛青 6GB+128GB");
        k.put(2, "Redmi 9 霓虹蓝 6GB+128GB");

//        k.put(0, "Redmi K40 幻境 12GB+256GB");
//        k.put(1, "Redmi K40 亮黑 12GB+256GB");
//        k.put(2, "Redmi K40 晴雪 12GB+256GB");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        buyDateTime = sdf.format(date) + XMYPConstant.BUY_TIME;
        LocalDateTime parse = LocalDateTime.parse(buyDateTime, SysConstants.dateTimeFormatter);
        needTime = LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

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
    public static String sendPost(String url, String body, String cookie, String referer) throws Exception {
        log.info("发起抢购");
        log.info("请求的url : {}", url);

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = new SSLClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(body, "UTF-8"));

            post.setHeader("origin", "https://trade.xiaomiyoupin.com");
            post.setHeader("referer", referer);
            post.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
            post.setHeader("x-user-agent", "channel/youpin platform/youpin.pc");
            post.setHeader("x-yp-app-source", "front-PC");
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

    public static void main(String[] args) {
        OrderSubmit orderSubmit = new OrderSubmit();
        orderSubmit.getBody("11000000169758167", "59.90", "2715");


    }

    public void orderSubmit(String cookie, String addressId, String price, String key, String gId, String name) throws Exception {
        String body = getBody(addressId, price, key);
        String url = "https://trade.xiaomiyoupin.com/mtop/order/cart/submit";
        StringBuilder referer = new StringBuilder("https://trade.xiaomiyoupin.com/tr/checkout?quickOrder=1&checkType=2&sourceType=2&pid=&count=1&_iid=g=");
        String buy = getBuy();
        referer.append(gId).append("&spmref=YouPinPC.$Detail$_").append(gId).append(".buy.").append(buy);

        // 1.等待
        long sleep = needTime - GetServiceTimeOfXm.getServiceTime();
        sleep -= 1000;
        if (sleep > 0) {
            log.info("sleep = {}", sleep);
            Thread.sleep(sleep);
        }


        boolean reBuy = true;
        Object code = null;
        int i = 0;
        String pid;
        List<String> pids = getPids();
        while (reBuy && i < pids.size()) {
            pid = pids.get(i);
            i++;
            // 2.加入虚拟购物车
            long s = System.currentTimeMillis();
            while (System.currentTimeMillis() - s < 3000) {
                boolean success = AddCart.addCart(cookie, pid);
                if (success || code != null) {
                    break;
                }
            }

            // 3.抢购
            if(i != 0){
                Thread.sleep(i * 500);
            }
            String res = sendPost(url, body, cookie, referer.toString());
            log.info("{} 下订单结果 : {}", name, res);
            Map map = JSONObject.parseObject(res, Map.class);
            code = map.get("code");
            if (code != null && code.equals("0")) {
                reBuy = false;
            }
        }

        if(!reBuy){
            log.info("抢购结果 : {}抢到了一台 {}", name, k.get(i));
        }else {
            log.info("抢购结果 : {} 很遗憾，没有抢到", name);
        }
    }


    private String getBody(String addressId, String price, String key) {
        List<Map> list = new ArrayList<>(2);
        Map n = new HashMap(1, 1.0f);
        list.add(n);
        Map<String, Object> map = new HashMap<>(12);
        map.put("addressId", addressId);
        map.put("bank", "nopay");
        map.put("cartType", 1);
        map.put("checkoutPrice", price);
        map.put("couponId", "0");

        Map<String, Object> invoiceDescrs = new HashMap<>(1, 1.0f);

        Map<String, Object> e = new HashMap<>(16);
        e.put("descr", "");
        e.put("invoiceBank", "");
        e.put("invoiceBankCode", "");
        e.put("invoiceCompanyAddress", "");
        e.put("invoiceCompanyCode", "");
        e.put("invoiceCompanyTel", "");
        e.put("invoiceEmail", "");
        e.put("invoiceTel", "");
        e.put("invoiceTitle", "");
        e.put("invoiceType", 0);
        invoiceDescrs.put(key, e);
        map.put("invoiceDescrs", invoiceDescrs);
        map.put("totalRedpacketAmount", "0");
        map.put("usableRedpacketAmount", "0");
        map.put("useRedpacket", -1);
        list.add(map);
        String res = JSONObject.toJSONString(list);
        System.out.println(res);
        return res;
    }

    private String getBuy() {
        int buy = RandomUtil.randomInt(1000, 9999);
        return String.valueOf(buy);
    }

    private List<String> getPids() {
        List<String> list = new ArrayList<>();
        // 幻境
        list.add("271169");
        // 亮黑
        list.add("271170");
        // 晴雪
        list.add("271171");
        return list;

        // todo 临时的，记得换成下面这个
//        List<String> list = new ArrayList<>();
//        // 幻境
//        list.add("294129");
//        // 亮黑
//        list.add("294128");
//        // 晴雪
//        list.add("294130");
//        return list;
    }
}

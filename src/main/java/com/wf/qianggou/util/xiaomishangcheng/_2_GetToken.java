package com.wf.qianggou.util.xiaomishangcheng;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.config.SysConstants;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月14日 17:06
 */
@Slf4j
public class _2_GetToken {

    public static long needTime;
    /**
     * 2021-03-05 15:40:00 000
     */
    private static String buyDateTime;

    static {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        buyDateTime = sdf.format(date) + XiaoMiConstant.BUY_TIME;
        LocalDateTime parse = LocalDateTime.parse(buyDateTime, SysConstants.dateTimeFormatter);
        needTime = LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

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
    public static Map<String, Object> sendGet(String url, String cookie) throws Exception {
//        System.out.println("GetToken的url = " + url);
        CloseableHttpClient client;
        client = new SSLClient();
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
            httpGet.setHeader("origin", "https://www.mi.com");
            httpGet.setHeader("referer", "https://www.mi.com/");
            httpGet.setHeader("cookie", cookie);
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity, "UTF-8");
                res = res.substring(10, res.length() - 1);
                return (Map) JSONObject.parse(res);
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
        getToken("xmuuid=XMGUEST-72247000-674F-11EB-A4F9-41CAF6BAC859; mstuid=1612487648244_8527; XM_2516410949_UN=cc; Hm_lvt_c3e3e8b3ea48955284516b186acf0f4e=1615190532,1615514157,1615516997,1615599575; deviceId=xmdevice_vgoacudjk3f0pbmc; neoTransfer=1; lastsource=s1.mi.com; XM_agreement_sure=1; XM_agreement=0; xm_user_www_num=0; XM_2478708258_UN=1318388; api_order_www_mi_com.minicart.xm_user_www_num=0.000000; spm_userid=zxPgCf8suTwP7t82m7mJbw==; euid=ic%2BTros3YwH2IPDV3JBrAw%3D%3D; pageid=0e5519feb6002210; userId=1239448881; cUserId=lwVCGihmVNpEfqpxLF-yBVshDDQ; xm_order_btauth=5206ec58c34226cc525eb83968831715; xm_link_history=%2BGr1rplQlZUZ1yeK6VPvcyFhp5bmgMcoKV%2F13VtdVG0%3D; axmuid=a0%2FNP%2B2d4w0Ld0CctvFRTjmBTMw28tH7z5S4O2VxcPc%3D; mishopServiceToken=KYNlOvsDR9HYhjoUfznVpgz5nwm8zdINiIuOaiIwvZGV%2FxIt9%2Fh4VghC5Z7XIIAfgUw2eTuydaF4zRbxxE815YXMW8n9gCZfYn5MqgBuHpIUL%2BQsufEGioKjFbmr%2FTucuO%2BrWhZea%2F977ERZbKRboJc%2FRrUT448r4oTb%2B6%2F56%2Bs%3D; serviceToken=KYNlOvsDR9HYhjoUfznVpgz5nwm8zdINiIuOaiIwvZGV%2FxIt9%2Fh4VghC5Z7XIIAfgUw2eTuydaF4zRbxxE815YXMW8n9gCZfYn5MqgBuHpIUL%2BQsufEGioKjFbmr%2FTucuO%2BrWhZea%2F977ERZbKRboJc%2FRrUT448r4oTb%2B6%2F56%2Bs%3D; xm_vistor=1612487648244_8527_1615772259881-1615773366078; mstz=||65830754.6581|||; XM_1239448881_UN=%E6%89%A7%E5%89%91%E5%87%8C%E8%99%9A; Hm_lpvt_c3e3e8b3ea48955284516b186acf0f4e=1615773366; mUserId=a0%2FNP%2B2d4w0Ld0CctvFRTgA7E6xDRlvK4p20rq%2BHJ0E%3D");
    }

    public static String getToken(String cookie) throws Exception {
        String storage = _1_GetStorage.getStorage(cookie);

        // 到点了再运行这里
        long sleep = needTime - System.currentTimeMillis();
        sleep -= 1000;
        if (sleep > 0) {
            log.info("sleep = {}", sleep);
            Thread.sleep(sleep);
        }

        String token = null;
        long s = System.currentTimeMillis();
        while (System.currentTimeMillis() - s < 3000) {
            try {
                _1_GetSalt getSalt = new _1_GetSalt(cookie);
                getSalt.getSlat();

                String url = "https://tp.hd.mi.com/hdget/cn?addcart=1&source=bigtap&ans=&ap=24&ac=272&ad=2548&aa=2548017&jsonpcallback=hdcontrol";
                url += "&product=" + XiaoMiConstant.GOODS_IDS;
                url += "&storage=" + storage;
                url += "&m=" + XiaoMiConstant.M;
                url += "&salt=" + getSalt.getSalt();
                url += "&_=" + System.currentTimeMillis();

                Map map = sendGet(url, cookie);
                Map status = (Map) map.get("status");
                Map gMap = (Map) status.get(XiaoMiConstant.GOODS_IDS);
                token = gMap.get("hdurl").toString();
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return token;
    }
}

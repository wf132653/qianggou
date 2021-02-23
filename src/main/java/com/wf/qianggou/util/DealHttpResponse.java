package com.wf.qianggou.util;

import cn.hutool.json.JSONUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年02月19日 17:30
 */
public class DealHttpResponse {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");

    public static Map getMapRes(String str) {
        int s, e;
        s = str.indexOf("(");
        e = str.lastIndexOf(")");
        if (s != -1 && e != -1) {
            return JSONUtil.toBean(str.substring(s + 1, e), Map.class);
        }
        return JSONUtil.toBean(str, Map.class);
    }

    public static void main(String[] args) throws Exception {
        String url = "https://h5api.m.tmall.com/h5/mtop.taobao.aladdin.service.aldrecommendservice.chaoshi.recommend/1.0/?jsv=2.4.2&appKey=12574478&t=1613728450431&sign=f8ef2f177a9db2a3b013468db97785f7&api=mtop.taobao.aladdin.service.AldRecommendService.chaoshi.recommend&v=1.0&AntiCreep=true&type=jsonp&dataType=jsonp&callback=mtopjsonp5&data=%7B%22smAreaId%22%3A510114%2C%22logica%22%3A%22HD%22%2C%22appId%22%3A%222015101319%22%2C%22isbackup%22%3Atrue%2C%22backupParams%22%3A%22smAreaId%22%7D";
        String res = HttpClientService.sendGet(url, true);

        Map map = getMapRes(res);


        String serverTime = ((Map) ((Map) map.get("data")).get("resultValue")).get("serverTime").toString();
        Date date = new Date(Long.valueOf(serverTime));
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        String serverDateTime = dateTimeFormatter.format(localDateTime);
        System.out.println("服务器时间 ：" + serverDateTime);
        String local = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println("本地的时间 ：" + local);



        Date date2 = new Date(1613728450431L);
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(date2.toInstant(), ZoneId.systemDefault());
        String serverDateTime2 = dateTimeFormatter.format(localDateTime2);
        System.out.println("时间2 ：" + serverDateTime2);

    }
}

package com.wf.qianggou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QiangGouApplication {

    public static void main(String[] args) {
        SpringApplication.run(QiangGouApplication.class, args);

        // HTTPS 代理，只能代理 HTTPS 请求
//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyPort", "9876");

// 同时支持代理 HTTP/HTTPS 请求
//        System.setProperty("proxyHost", "127.0.0.1");
//        System.setProperty("proxyPort", "9876");
    }

}

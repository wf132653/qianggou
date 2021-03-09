package com.wf.qianggou.util.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.remote.FileDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年02月26日 11:02
 */
@Component
public class OpenLastWebDriver {

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String CHROME_DRIVER_NAME = "webdriver.firefox.bin";
    private final static String CHROME_DRIVER_87_PATH = "D:\\soft\\FireFox\\install\\firefox.exe";

    public void save(){
        System.setProperty(CHROME_DRIVER_NAME, CHROME_DRIVER_87_PATH);
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    public static void main(String[] args){
        // 88955468

        long time = (2 * 24 * + 7) * 60 * 60 * 1000 ;
        System.out.println(time);


    }

}

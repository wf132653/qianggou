package com.wf.qianggou.controller;

import com.wf.qianggou.util.selenium.OpenLastWebDriver;
import com.wf.qianggou.util.selenium.RunnerOfShoppingDetail;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年02月26日 10:43
 */
@RestController
@RequestMapping("/order")
public class AutoConfirmOrderController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RunnerOfShoppingDetail runnerOfShoppingDetail;
    @Autowired
    private OpenLastWebDriver openLastWebDriver;

    @GetMapping
    public void order() throws Exception{
        runnerOfShoppingDetail.run();
    }

    @GetMapping("/save")
    public void save(){
        openLastWebDriver.save();
    }

    @GetMapping("/test")
    public void test(){
        Object taobao = redisTemplate.opsForValue().get("taobao");
        ChromeDriver driver = (ChromeDriver)taobao;
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

}

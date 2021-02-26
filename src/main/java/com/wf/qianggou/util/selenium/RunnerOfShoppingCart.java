package com.wf.qianggou.util.selenium;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.*;

/**
 * 登录 -> 跳转购物车 -> 勾选所有商品 -> 点击结算 -> 提交订单
 *
 * @author wf
 * @date 2021年02月24日 11:22
 */
@Slf4j
public class RunnerOfShoppingCart {

    private static long needTime;

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss SSS");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss SSS");

    static {
        System.setProperty("webdriver.firefox.bin", "D:\\soft\\FireFox\\install\\firefox.exe");
        String buyTime = "2021/02/26-16:55:00 000";
        try {
            needTime = sdf.parse(buyTime).getTime();
        } catch (ParseException e) {
            log.error("初始化格式异常{}", e);
        }
    }

    public static void main(String[] args) throws Exception {
        /**
         * 要调起新版本的firefox，需要geckodriver驱动（未设置时java.lang.IllegalStateException报错）
         * System.setProperty("webdriver.gecko.driver", "D:\\soft\\FireFox\\install\\geckodriver.exe");
         * 若无法打开Firefox浏览器，可设定Firefox浏览器的安装路径(未设置路径时path报错)
         */
        String taoBao = "https://www.taobao.com";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server-127.0.0.2:8080");
        //去掉chrome 正受到自动测试软件的控制
        options.addArguments("disable-infobars");
        //开启开发者模式
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        WebDriver webDriver = new ChromeDriver(options);
        Navigation navigation = webDriver.navigate();

        // 放大浏览器
        WebDriver.Window window = webDriver.manage().window();
        window.maximize();

        navigation.to(taoBao);
        ((JavascriptExecutor) webDriver).executeScript("Object.defineProperties(navigator,{ webdriver:{ get: () => false } })");

        Thread.sleep(1000);
//        webDriver.findElement(By.linkText("亲，请登录")).click();
        RunnerOfShoppingDetail.moveToClick(webDriver, 601, 0, true);
        passwordLogin(webDriver);
//        smsLogin(webDriver);
        Thread.sleep(1000);
        // 点击登录
        webDriver.findElement(By.className("fm-button")).click();
        Thread.sleep(2000);
        navigation.to("https://cart.taobao.com/cart.htm");
        Thread.sleep(1000);
        // 勾选购物车内所有商品
        webDriver.findElement(By.className("cart-checkbox")).click();
        Thread.sleep(1000);
        WebElement settlement = webDriver.findElement(By.linkText("结 算"));

        // 开始抢
        log.info("本地时间 : {}", formatter.format(LocalDateTime.now()));
        long start = System.currentTimeMillis();
        long ld = RunnerOfShoppingDetail.getSiteDate();
        long end = System.currentTimeMillis();
        long p = end - start;

        if (needTime > ld) {
            // 时间未到，休眠一段时间再抢购，休眠时间 = 定时抢购时间 - 服务器时间 - 获取服务器时间接口 / 3
            long sleep = needTime - ld - p / 3;
            System.out.println("sleep = " + sleep);
            Thread.sleep(sleep);
        }
        // 结算
        settlement.click();
        // 提交订单
        confirmOrder(webDriver);
    }

    private static void passwordLogin(WebDriver webDriver) throws Exception {
        Thread.sleep(1000);
//        webDriver.findElement(By.linkText("密码登录")).click();
        RunnerOfShoppingDetail.moveToClick(webDriver, 1335, 248, true);
        Thread.sleep(1000);
        webDriver.findElement(By.id("fm-login-id")).sendKeys("小飞546466464");
        Thread.sleep(1000);
        webDriver.findElement(By.name("fm-login-password")).sendKeys("wf132653");
        Thread.sleep(2000);

        By iframeBy = By.xpath("//iframe");


        WebElement iframe = webDriver.findElement(iframeBy);
        if (iframe != null) {
            webDriver.switchTo().frame(iframe);
            WebElement moveElemet = null;
            try {
                By moveBtn = By.id("nc_1_n1z");
                if ((moveElemet = webDriver.findElement(moveBtn)) == null) {
                    System.out.println("没有找到1 元素 nc_1_n1z");
                }
            } catch (Exception e) {
                System.out.println("没有找到2 元素 nc_1_n1z");
            }
            webDriver.switchTo().parentFrame();
            if (moveElemet != null) {
                Thread.sleep(1000);
                webDriver.findElement(By.linkText("短信登录")).click();
                passwordLogin(webDriver);
            }
        }
    }

    private static boolean confirmOrder(WebDriver webDriver) {
        log.info("confirmOrder1 : {}", formatter.format(LocalDateTime.now()));
        int times = 0;
        long start = 0,end = 0;
        boolean success = false;
        // 点击立即购买
        // 点击提交订单
        while (times++ < 40) {
            start = System.currentTimeMillis();
            try {
                Thread.sleep(end > 0 && (end - start) < 50 ? end - start : 0);
                log.info("提交订单1 : {}", formatter.format(LocalDateTime.now()));
//                WebElement element = webDriver.findElement(By.linkText("提交订单"));
                // 提交订单 :(1424, 1087)
                RunnerOfShoppingDetail.moveToClick(webDriver, 1424, 1087, false);
                log.info("提交订单2 : {}", formatter.format(LocalDateTime.now()));
//                element.click();
                success = true;
//                System.out.println("提交订单 : " + element.getLocation().toString());
                break;
            } catch (Exception e) {
                if (times == 20) {
                    log.info("提交订单没找到 : {}", e.getMessage());
                } else {
                    log.info("提交订单的错误 : {}", e);
                }
            }
            end = System.currentTimeMillis();
        }
        log.info("times2 = " + times);
        log.info("confirmOrder2 : {}", formatter.format(LocalDateTime.now()));
        System.out.println("success = " + success);
        return success;
    }

    private static void smsLogin(WebDriver webDriver) throws Exception {
        Thread.sleep(1000);
        webDriver.findElement(By.linkText("短信登录")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.id("fm-sms-login-id")).sendKeys("13281835775");
        Thread.sleep(20000);
    }

}

package com.wf.qianggou.util.selenium;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

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
public class RunnerOfShoppingCart {

    public static void main(String[] args) throws Exception {
        /**
         * 要调起新版本的firefox，需要geckodriver驱动（未设置时java.lang.IllegalStateException报错）
         * System.setProperty("webdriver.gecko.driver", "D:\\soft\\FireFox\\install\\geckodriver.exe");
         * 若无法打开Firefox浏览器，可设定Firefox浏览器的安装路径(未设置路径时path报错)
         */
        System.setProperty("webdriver.firefox.bin", "D:\\soft\\FireFox\\install\\firefox.exe");
        String taoBao = "https://www.taobao.com";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server-127.0.0.2:8080");
        //去掉chrome 正受到自动测试软件的控制
        options.addArguments("disable-infobars");
        //开启开发者模式
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        WebDriver webDriver = new ChromeDriver(options);
        Navigation navigation = webDriver.navigate();

        navigation.to(taoBao);
        ((JavascriptExecutor) webDriver).executeScript("Object.defineProperties(navigator,{ webdriver:{ get: () => false } })");

        Thread.sleep(1000);
        webDriver.findElement(By.linkText("亲，请登录")).click();
        passwordLogin(webDriver);
//        smsLogin(webDriver);

        // 放大浏览器
//        Window window = webDriver.manage().window();
//        window.maximize();
        try {

//
            Thread.sleep(1000);
//
            // 点击登录
            webDriver.findElement(By.className("fm-button")).click();
            Thread.sleep(5000);
            navigation.to("https://cart.taobao.com/cart.htm");
            Thread.sleep(1000);
            // 勾选购物车内所有商品
            webDriver.findElement(By.className("cart-checkbox")).click();
            Thread.sleep(3000);
            while (true) {
                URL url = new URL(taoBao);
                URLConnection uc = url.openConnection();
                uc.connect();
                // 读取网站日期时间
                long ld = uc.getDate();
                Date dateNow = new Date(ld);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
                String buyTime = "2021/01/01-00:00:00";
                Date dateNeed = sdf.parse(buyTime);
                if (dateNeed.equals(dateNow) || dateNeed.before(dateNow)) {
                    webDriver.findElement(By.linkText("结 算")).click();
                    System.out.println(sdf.format(dateNow));


                    try {
                        Thread.sleep(1000);
                        WebElement confirmOrder = webDriver.findElement(By.linkText("提交订单"));
                        System.out.println(confirmOrder.getLocation().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("提交订单--------------------------------");
//                    webDriver.findElement(By.linkText("提交订单")).click();

                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void passwordLogin(WebDriver webDriver) throws Exception {
        Thread.sleep(1000);
        webDriver.findElement(By.linkText("密码登录")).click();
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
                moveElemet = webDriver.findElement(moveBtn);
            } catch (Exception e) {
                System.out.println("没有找到 元素 nc_1_n1z");
            }
            webDriver.switchTo().parentFrame();
            if (moveElemet != null) {
                Thread.sleep(1000);
                webDriver.findElement(By.linkText("短信登录")).click();
                passwordLogin(webDriver);
            }
        }
    }

    private static void smsLogin(WebDriver webDriver) throws Exception {
        Thread.sleep(1000);
        webDriver.findElement(By.linkText("短信登录")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.id("fm-sms-login-id")).sendKeys("13281835775");
        Thread.sleep(20000);
    }

}

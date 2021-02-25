package com.wf.qianggou.util.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 登录 -> 跳转购物车 -> 商品详情页 -> 立即抢购 -> 提交订单
 * todo 优化，找元素，修改为 直接定点 -> 点击
 *
 * @author wf
 * @date 2021年02月24日 11:22
 */
@Slf4j
public class RunnerOfShoppingDetail {

    private static long needTime;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss SSS");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss SSS");

    static {
        System.setProperty("webdriver.firefox.bin", "D:\\soft\\FireFox\\install\\firefox.exe");
        String buyTime = "2021/02/25-16:16:00 000";
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
        WebDriver.Window window = webDriver.manage().window();
        window.maximize();
        // 设置页码超时时间 1.5秒
//        webDriver.manage().timeouts().pageLoadTimeout(60000, TimeUnit.MILLISECONDS);
        Navigation navigation = webDriver.navigate();


        navigation.to(taoBao);
        ((JavascriptExecutor) webDriver).executeScript("Object.defineProperties(navigator,{ webdriver:{ get: () => false } })");

        Thread.sleep(1000);
        WebElement element = webDriver.findElement(By.linkText("亲，请登录"));
        System.out.println("亲，请登录" + element.getLocation().toString());
        element.click();


        passwordLogin(webDriver, false);
//        smsLogin(webDriver);

        // 放大浏览器

        try {
            Thread.sleep(1000);
//
            // 点击登录
            webDriver.findElement(By.className("fm-button")).click();
            Thread.sleep(5000);
            navigation.to("https://cart.taobao.com/cart.htm");
            Thread.sleep(1000);

            // 开始抢
            log.info("本地时间 : {}", formatter.format(LocalDateTime.now()));
            long start = System.currentTimeMillis();

            long end = System.currentTimeMillis();
            long p = end - start;

            long ld = getSiteDate();
            if (needTime > ld) {

                // 时间未到，休眠一段时间再抢购，休眠时间 = 定时抢购时间 - 服务器时间 - 获取服务器时间接口 / 3
                long sleep = needTime - ld - p / 3;
                System.out.println("sleep = " + sleep);
                Thread.sleep(sleep);
            }
            // 时间已到，跳转到商品详情页
            switchToShoppingDetail(webDriver);
            // 立即抢购
            boolean success = confirmOrder(webDriver);
            if (success) {
                log.info("抢购成功");
            } else {
                log.info("抢购失败");
            }

            log.info("网站时间 : {}", sdf.format(new Date(ld)));
            log.info("抢购时间 : {}", sdf.format(new Date(needTime)));
            log.info("本地时间 : {}", formatter.format(LocalDateTime.now()));

        } catch (Exception e) {
            e.printStackTrace();
        }
//        webDriver.quit();
    }

    private static boolean confirmOrder(WebDriver webDriver) {
        log.info("confirmOrder1 : {}", formatter.format(LocalDateTime.now()));
        int times = 0;
        char success = 0;
        // 点击立即购买
//        webDriver.navigate().refresh();
        selectColor(webDriver);
        while (times++ < 20) {
            try {
                Thread.sleep(50);
                log.info("立即购买1 : {}", formatter.format(LocalDateTime.now()));
                WebElement element = webDriver.findElement(By.id("J_LinkBuy"));
                log.info("立即购买2 : {}", formatter.format(LocalDateTime.now()));
                element.click();
                System.out.println("立即购买 : " + element.getLocation().toString());
                // 立即购买 : (536, 948)
                success = 1;
                break;
            } catch (Exception e) {
                if (times == 20) {
                    log.info("立即购买没找到 : {}", e.getMessage());
                } else {
                    log.info("立即购买的错误 : {}", e.getMessage());
                }
            }
        }
        log.info("times1 = " + times);
        log.info("confirmOrder2 : {}", formatter.format(LocalDateTime.now()));
        // 点击提交订单
        if (success == 1) {
            times = 0;
            while (times++ < 20) {
                try {
                    Thread.sleep(50);
                    log.info("提交订单1 : {}", formatter.format(LocalDateTime.now()));
                    WebElement element = webDriver.findElement(By.linkText("提交订单"));
                    log.info("提交订单2 : {}", formatter.format(LocalDateTime.now()));
//                    element.click();
                    System.out.println("提交订单 : " + element.getLocation().toString());
                    // 提交订单 : (901, 1078)
                    success = 2;
                    break;
                } catch (Exception e) {
                    if (times == 20) {
                        log.info("提交订单没找到 : {}", e.getMessage());
                    } else {
                        log.info("提交订单的错误 : {}", e.getMessage());
                    }
                }
            }
        }
        log.info("times2 = " + times);
        log.info("confirmOrder3 : {}", formatter.format(LocalDateTime.now()));
        System.out.println("success = " + (byte) success);
        return success == 2;
    }

    private static void passwordLogin(WebDriver webDriver, boolean move) throws Exception {
        if (move) {
            Thread.sleep(1000);

            Actions actions = new Actions(webDriver);
            actions.moveByOffset(600, 248);
            actions.build().perform();
            actions.moveByOffset(735, 0);
            actions.click().build().perform();
            Thread.sleep(2000);
        }


        // (815, 248)
        // 全屏 (1335, 248)
//        webDriver.findElement(By.linkText("密码登录")).click();
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
                passwordLogin(webDriver, true);
            }
        }
    }

    private static void selectColor(WebDriver webDriver) {
        WebElement element1 = webDriver.findElement(By.linkText("黑色9000"));
        System.out.println(element1.getLocation().toString());
        // (599, 704)
        element1.click();
        WebElement element2 = webDriver.findElement(By.linkText("170/74A/M"));
        System.out.println(element2.getLocation().toString());
        // (635, 626)
        element2.click();
    }

    private static void switchToShoppingDetail(WebDriver webDriver) {
        // 抢购 贵州茅台酒
//        webDriver.findElement(By.partialLinkText("贵州茅台酒")).click();
//        webDriver.findElement(By.partialLinkText("最生活小米毛巾")).click();
        log.info("switchToShoppingDetail11 : {}", formatter.format(LocalDateTime.now()));
        webDriver.findElement(By.partialLinkText("森马休闲长裤")).click();
        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        String secondHandle = null;
        for (String handle : windowHandles) {
            if (!currentWindowHandle.equals(handle)) {
                secondHandle = handle;
                break;
            }
        }
        webDriver.close();
        webDriver.switchTo().window(secondHandle);
        log.info("switchToShoppingDetail22 : {}", formatter.format(LocalDateTime.now()));
    }

    private static long getSiteDate() {
//        URL url = new URL(taoBao);
//        URLConnection uc = url.openConnection();
//        uc.connect();
//         读取网站日期时间
//        long ld = uc.getDate();
        return System.currentTimeMillis();
    }


    private static void smsLogin(WebDriver webDriver) throws Exception {
        Thread.sleep(1000);
        webDriver.findElement(By.linkText("短信登录")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.id("fm-sms-login-id")).sendKeys("13281835775");
        Thread.sleep(20000);
    }

}

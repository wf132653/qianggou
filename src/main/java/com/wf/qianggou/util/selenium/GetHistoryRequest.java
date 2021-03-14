package com.wf.qianggou.util.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月11日 16:17
 */
public class GetHistoryRequest {

    static {
        System.setProperty("webdriver.firefox.bin", "D:\\soft\\FireFox\\install\\firefox.exe");
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
        webDriver.manage().getCookies();
        WebDriver.Navigation navigation = webDriver.navigate();

        // 放大浏览器
        WebDriver.Window window = webDriver.manage().window();
        window.maximize();

        navigation.to(taoBao);
        ((JavascriptExecutor) webDriver).executeScript("Object.defineProperties(navigator,{ webdriver:{ get: () => false } })");

        Thread.sleep(1000);
        webDriver.findElement(By.linkText("亲，请登录")).click();

        Thread.sleep(1000);
        WebElement element1 = webDriver.findElement(By.className("icon-qrcode"));
        element1.click();
        Thread.sleep(1000);
        WebElement element = webDriver.findElement(By.className("qrcode-img"));
        screenShotForElement(webDriver, element, "C:\\Users\\wf\\Desktop\\1.png", 1, 1);

        Thread.sleep(100010000);


        passwordLogin(webDriver);
//        smsLogin(webDriver);
        Thread.sleep(1000);
        // 点击登录
        webDriver.findElement(By.className("fm-button")).click();


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


    public static void screenShotForElement(WebDriver driver, WebElement element, String path, int x, int y) {
        //截取整个页面的图片
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            //获取元素在所处frame中位置对象
            Point p = element.getLocation();
            //获取元素的宽与高
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            //矩形图像对象
            java.awt.Rectangle rect = new java.awt.Rectangle(width, height);
            BufferedImage img = ImageIO.read(scrFile);
            //x、y表示加上当前frame的左边距,上边距
            BufferedImage dest = img.getSubimage(p.getX() + x, p.getY() + y, rect.width, rect.height);
            ImageIO.write(dest, "png", scrFile);
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void snapshot(TakesScreenshot drivername, String filename) {
//        // this method will take screen shot ,require two parameters ,one is driver name, another is file name
//
//        String currentPath = System.getProperty("user.dir"); //get current work folder
//        System.out.println(currentPath);
//        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
//        // Now you can do whatever you need to do with it, for example copy somewhere
//        try {
//            System.out.println("save snapshot path is:" + currentPath + "/" + filename);
//            FileUtils.copyFile(scrFile, new File(currentPath + "\\" + filename));
//        } catch (IOException e) {
//            System.out.println("Can't save screenshot");
//            e.printStackTrace();
//        } finally {
//            System.out.println("screen shot finished");
//        }
//    }

}

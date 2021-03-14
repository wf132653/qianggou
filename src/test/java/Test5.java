import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月11日 11:26
 */
public class Test5 {

//    @Test
    public void test() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String ip = "182.150.63.230";
        int port = 80;
        map.put("waybillNo", "DD1838768852");
        long a = System.currentTimeMillis();
        //爬取的目标网站，url记得换下。。。！！！
        Document doc = Jsoup.connect("http://trace.yto.net.cn:8022/TraceSimple.aspx")
                .timeout(5000)
                .proxy(ip, port)
                .data(map)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0")
                .header("referer", "http://trace.yto.net.cn:8022/gw/index/index.html")//这个来源记得换..
                .post();
        System.out.println(ip + ":" + port + "访问时间:" + (System.currentTimeMillis() - a) + "   访问结果: " + doc.text());
    }

//    @Test
    public void test1(){
        System.out.println(System.currentTimeMillis());
    }

}

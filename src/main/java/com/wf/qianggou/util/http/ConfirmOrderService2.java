package com.wf.qianggou.util.http;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.HttpClientService;
import com.wf.qianggou.util.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下订单接口模拟，从buy.接口的返回直接取 orderData 编一下 url 码，作为下订单的参数
 *
 * @author wf
 * @date 2021年02月22日 16:32
 */
@Slf4j
public class ConfirmOrderService2 {

    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送 https 的 POST请求
     *
     * @param url               url 地址
     * @param body
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPost(String url, String body, String path) throws Exception {

        System.out.println("请求的url : " + url);

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = new SSLClient();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 设置请求的内容
             */
            post.setEntity(new StringEntity(body, "UTF-8"));
            /**
             * 设置请求的报文头部
             */
            post.addHeader(new BasicHeader("authority", "buy.tmall.com"));
            post.addHeader(new BasicHeader("method", "POST"));
            post.addHeader(new BasicHeader("path", path));
            post.addHeader(new BasicHeader("scheme", "https"));
            post.addHeader(new BasicHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"));
            post.addHeader(new BasicHeader("accept-encoding", "gzip, deflate, br"));
            post.addHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
            post.addHeader(new BasicHeader("cache-control", "max-age=0"));
            post.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            post.addHeader(new BasicHeader("Cookie", "lid=%E5%B0%8F%E9%A3%9E546466464; t=87c2d13949cdfc7b98d64a12bb61717b; tracknick=%5Cu5C0F%5Cu98DE546466464; enc=cadXxgs0CtFTDVT37E%2BS%2FT8lgFaBbPSL5ZZYP9Slsl3QdCcdLzhuPnGYbNGp7RLhrRWYQdY5W5hwrnrI2NW1bQ%3D%3D; _tb_token_=5be0e367bb4b1; cookie2=18e70c59727c5e7e512b368537ca53e6; cna=8p66F6s6R0QCAbaWP+YyYMfF; dnk=%5Cu5C0F%5Cu98DE546466464; lgc=%5Cu5C0F%5Cu98DE546466464; login=true; tk_trace=1; hng=CN%7Czh-CN%7CCNY%7C156; sm4=510114; ubn=p; ucn=center; _bl_uid=pwkj3ldwgvFiend3zzhwaws4zgms; uc1=existShop=true&cookie21=URm48syIYB3rzvI4DJOx&pas=0&cookie16=U%2BGCWk%2F74Mx5tgzv3dWpnhjPaQ%3D%3D&cookie15=UtASsssmOIJ0bQ%3D%3D&cookie14=Uoe1gW4gba4WcA%3D%3D&cart_m=0; uc3=vt3=F8dCuASicrrC0XfNvXw%3D&lg2=Vq8l%2BKCLz3%2F65A%3D%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLE5%2Fy7frB8&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSEZeyaZ7jM; _l_g_=Ug%3D%3D; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E1004CS%2BPhSQGTfLRMqPoyFd%2F92pInneBuLPr5jvN6%2BhU099YCeLya0bCLbsm%2FjrIDY1G7ucRppSDMVl7rH%2B0hZgfQ%3D%3D; sg=424; csg=30a4a5b2; _m_h5_tk=20d415c5f5a91e51557d1c502f62b0ce_1614090946147; _m_h5_tk_enc=680e7403f61946fd7ef869157a68407e; xlly_s=1; tfstk=cht5BVXJe7V7-W3U4LM44ix4R4SCZny1d41kFUSD8_cLc_95isEN1tyk-WEAWt1..; l=eBS9UcGIq1RQLopkBOfwhurza77tOIRAguPzaNbMiOCP99fpSY8GW6gSD-Y9CnGVh65DR3SodYDwBeYBqImRXtWLuZ2pBeMmn; isg=BPr6EvteTkA1Qf9sgsHlBUW9SyAcq36FKpX20ATzgw1a95ox7DqLl-8FQ4Mr5_Yd"));
            post.addHeader(new BasicHeader("Origin", "https://buy.tmall.com"));
            post.addHeader(new BasicHeader("Referer", "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined"));
            post.addHeader(new BasicHeader("sec-fetch-dest", "document"));
            post.addHeader(new BasicHeader("sec-fetch-mode", "navigate"));
            post.addHeader(new BasicHeader("sec-fetch-site", "same-origin"));
            post.addHeader(new BasicHeader("sec-fetch-user", "?1"));
            post.addHeader(new BasicHeader("upgrade-insecure-requests", "1"));
            post.addHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36"));
            //
//            client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);


            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode) {
                /**
                 * 通过EntityUitls获取返回内容
                 */
                HttpEntity httpEntity = response.getEntity();

                String result = EntityUtils.toString(httpEntity, "GBK");
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                return result;
            } else {
                log.error("POST请求失败");
            }
        } catch (Exception e) {
            log.error("Exception：{}", e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**
     * 只需替换一个 orderDataStr 即可
     */
    public static void main(String[] args) throws Exception {
        String preUrl = "https://buy.tmall.com";
        String url = "/auction/confirm_order.htm?x-itemid=582408570328&x-uid=2453833782&submitref=";
        // buy 接口返回的 orderData ，记得去掉结尾的  ;
        String orderDataStr = "{\"endpoint\":{\"mode\":\"pc\",\"osVersion\":\"PC\",\"protocolVersion\":\"3.0\",\"ultronage\":\"true\"},\"data\":{\"addressPC_1\":{\"fields\":{\"addrMakerUrl\":\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\",\"defaultAddressAPI\":\"/auction/update_address_selected_status.htm?_input_charset=utf-8\",\"disableCurrentSelected\":false,\"lat\":\"30.773273\",\"linkAddressId\":\"0\",\"lng\":\"104.125916\",\"managerAddressUrl\":\"//member1.taobao.com/member/fresh/deliver_address.htm\",\"optStr\":\"[{\\\"addrMakerUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=12198497492&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"addressDetail\\\":\\\"天河大道148号流溪苑\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"addressUrl\\\":{\\\"addAddressAPI\\\":\\\"/auction/add_buyer_address.htm?_input_charset=utf-8\\\",\\\"addressUrlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=12198497492&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"defaultAddressAPI\\\":\\\"/auction/update_address_selected_status.htm?_input_charset=utf-8\\\",\\\"managerAddressUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address.htm\\\",\\\"postCodeAPI\\\":\\\"/auction/json/get_postcode.do?_input_charset=utf-8\\\",\\\"updateAddressAPI\\\":\\\"/auction/update_buyer_address.htm?_input_charset=utf-8\\\",\\\"urlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\\\"},\\\"areaName\\\":\\\"新都\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":true,\\\"deliveryAddressId\\\":12198497492,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510114\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"4366527\\\",\\\"lat\\\":\\\"30.773273\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"104.125916\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510114003,\\\"townName\\\":\\\"三河\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addressDetail\\\":\\\"清溪雅筑13栋\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"青羊\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":8809318602,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510105\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"4366528\\\",\\\"lat\\\":\\\"30.667978\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"103.974563\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510105013,\\\"townName\\\":\\\"苏坡\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addressDetail\\\":\\\"四川省内江市东兴区百合镇农贸市场\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"东兴\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"内江\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":6471596766,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"511011\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"4366529\\\",\\\"lat\\\":\\\"29.686443\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"105.291406\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"641100\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":511011103,\\\"townName\\\":\\\"白合\\\",\\\"updateAddressTip\\\":\\\"\\\"}]\",\"options\":[{\"deliveryAddressId\":\"12198497492\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"成都\",\"addressDetail\":\"天河大道148号流溪苑\",\"needUpdate4Address\":false,\"divisionCode\":\"510114\",\"enforceUpdate4Address\":true,\"areaName\":\"新都\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"000000\",\"townDivisionId\":\"510114003\",\"defaultAddress\":true,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"三河\",\"enableMDZT\":false},{\"deliveryAddressId\":\"8809318602\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"成都\",\"addressDetail\":\"清溪雅筑13栋\",\"needUpdate4Address\":false,\"divisionCode\":\"510105\",\"enforceUpdate4Address\":true,\"areaName\":\"青羊\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"000000\",\"townDivisionId\":\"510105013\",\"defaultAddress\":false,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"苏坡\",\"enableMDZT\":false},{\"deliveryAddressId\":\"6471596766\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"内江\",\"addressDetail\":\"四川省内江市东兴区百合镇农贸市场\",\"needUpdate4Address\":false,\"divisionCode\":\"511011\",\"enforceUpdate4Address\":true,\"areaName\":\"东兴\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"641100\",\"townDivisionId\":\"511011103\",\"defaultAddress\":false,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"白合\",\"enableMDZT\":false}],\"receiveMethodType\":\"normal\",\"selectedId\":\"12198497492\",\"sites\":\"[]\",\"tips\":{},\"title\":\"选择收货地址\",\"updateAddressAPI\":\"/auction/update_buyer_address.htm?_input_charset=utf-8\",\"useMDZT\":false,\"useStation\":false},\"id\":\"1\",\"ref\":\"0394592\",\"submit\":true,\"tag\":\"addressPC\",\"type\":\"native$null$address\"},\"agencyPayPC_1\":{\"fields\":{\"checked\":false,\"disabled\":false,\"label\":\"朋友代付\"},\"id\":\"1\",\"ref\":\"c287c09\",\"submit\":true,\"tag\":\"agencyPayPC\",\"type\":\"native$null$checkbox\"},\"anonymousPC_1\":{\"fields\":{\"checked\":true,\"disabled\":false,\"label\":\"匿名购买\"},\"id\":\"1\",\"ref\":\"907bd01\",\"submit\":true,\"tag\":\"anonymousPC\",\"type\":\"native$null$checkbox\"},\"confirmOrder_1\":{\"fields\":{},\"hidden\":{\"extensionMap\":{\"joinId\":\"ffb6f117987962fa1dded529a6eafe75\",\"pageType\":\"TMALL\",\"sid\":\"7c5364ae2e4976b104e4b9c7358a3dc9\",\"umidToken\":\"T6961e12314e889d555abc5c1dc2ad18a\"}},\"id\":\"1\",\"ref\":\"8318d7a\",\"submit\":true,\"tag\":\"confirmOrder\",\"type\":\"block$null$emptyBlock\"},\"deliveryMethodPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"checked\":true,\"hasCod\":false,\"options\":[{\"serviceIcon\":\"//img.alicdn.com/tfs/TB1No7XbvxNTKJjy0FjXXX6yVXa-145-28.png\",\"fare\":\"6.00\",\"serviceType\":\"5\",\"message\":\"快递￥6.00\",\"datePicker\":{\"headTip\":\"不可抗力等因素可致延误 <a href=\\\"//service.tmall.com/support/tmall/knowledge-5811867.htm\\\">详情</a>\",\"periods\":[\"09:00-14:00\",\"09:00-21:00\",\"14:00-19:00\"],\"selectedPeriods\":\"09:00-14:00\",\"endDate\":\"2021-03-02\",\"payTimeTip\":\"请在23:59前付款\",\"useDefault\":false,\"enableCancel\":true,\"disable\":[\"0_0\",\"0_1\",\"0_2\"],\"beginDate\":\"2021-02-24\"},\"signText\":\"23:59前付款，承诺2月24日送达\",\"extra\":\"\",\"titleText\":\"普通配送\",\"hasOption\":false,\"appointPromotionTip\":\"\",\"id\":\"2_5_0\",\"userChooseLogisticsInfo\":\"{\\\"childServiceType\\\":5,\\\"cp\\\":\\\"default\\\",\\\"logisticsType\\\":0,\\\"serviceType\\\":5}\",\"fareCent\":\"600\"}],\"secondOption\":false,\"selectedId\":\"2_5_0\"},\"hidden\":{\"extensionMap\":{\"deliveryId\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"options\":\"[{\\\"datePicker\\\":{\\\"beginDate\\\":\\\"2021-02-24\\\",\\\"disable\\\":[\\\"0_0\\\",\\\"0_1\\\",\\\"0_2\\\"],\\\"enableCancel\\\":true,\\\"endDate\\\":\\\"2021-03-02\\\",\\\"headTip\\\":\\\"不可抗力等因素可致延误 <a href=\\\\\\\"//service.tmall.com/support/tmall/knowledge-5811867.htm\\\\\\\">详情</a>\\\",\\\"payTimeTip\\\":\\\"请在23:59前付款\\\",\\\"periods\\\":[\\\"09:00-14:00\\\",\\\"09:00-21:00\\\",\\\"14:00-19:00\\\"],\\\"selectedPeriods\\\":\\\"09:00-14:00\\\",\\\"useDefault\\\":false},\\\"fareCent\\\":600,\\\"id\\\":\\\"2_5_0\\\",\\\"serviceType\\\":\\\"5\\\"}]\"}},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"2312f91\",\"submit\":true,\"tag\":\"deliveryMethodPC\",\"type\":\"native$null$deliveryMethod\"},\"frontTracePC_1\":{\"fields\":{\"joinId\":\"2678190121271\"},\"id\":\"1\",\"ref\":\"e61fd52\",\"tag\":\"frontTracePC\",\"type\":\"native$null$frontTrace\"},\"invoicePC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"canModifyTitle\":true,\"category\":[{\"id\":\"0\",\"text\":\"电子发票\",\"type\":\"1\"}],\"checked\":false,\"defaultAddressName\":\"个人\",\"einvoice\":true,\"enotice\":\"1、发票金额不包括点券、积分或其他类似折扣券抵减的金额；<br>2、电子发票可以作为售后维权的有效凭证，财务报销规定请参照 <a href='//service.taobao.com/support/knowledge-6502401.htm'>帮助中心。</a>\",\"invoiceMust\":false,\"options\":[{\"id\":\"0\",\"text\":\"明细\"}],\"selectedId\":\"0_0\",\"taxNo\":\"\",\"title\":\"个人\",\"uses\":[{\"id\":\"0\",\"text\":\"个人\",\"type\":\"1\"},{\"id\":\"1\",\"text\":\"企业\",\"type\":\"2\"}],\"usesSelectedId\":\"0\",\"valueAddTax\":true},\"hidden\":{\"extensionMap\":{\"categorySize\":\"1\"}},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"7f3cd76\",\"submit\":true,\"tag\":\"invoicePC\",\"type\":\"native$null$invoice\"},\"itemInfoPC_ffb6f117987962fa1dded529a6eafe75\":{\"fields\":{\"icons\":[{\"title\":\"消费者保障服务，卖家承诺7天退换\",\"text\":\"\",\"image\":\"//img.alicdn.com/tps/i3/T1Vyl6FCBlXXaSQP_X-16-16.png\",\"link\":\"//pages.tmall.com/wow/seller/act/seven-day\",\"sortNo\":\"10\",\"iconType\":\"main\"},{\"title\":\"消费者保障服务，卖家承诺如实描述\",\"text\":\"\",\"image\":\"//img.alicdn.com/tps/i4/T1BCidFrNlXXaSQP_X-16-16.png\",\"link\":\"//www.taobao.com/go/act/315/xfzbz_rsms.php?ad_id=&am_id=130011830696bce9eda3&cm_id=&pm_id=\",\"sortNo\":\"11\",\"iconType\":\"main\"},{\"title\":\"支持信用卡支付\",\"text\":\"\",\"image\":\"//assets.alicdn.com/sys/common/icon/trade/xcard.png\",\"iconType\":\"main\"}],\"isGift\":false,\"itemInfoId\":\"582408570328\",\"itemUrl\":\"//detail.tmall.com/item.htm?id=582408570328\",\"pic\":\"//img.alicdn.com/imgextra/i1/725677994/O1CN01G9VbQ128vIcySKQYw_!!725677994.jpg\",\"price\":\"19.00\",\"priceInfo\":{\"valueStyles\":{\"color\":\"#FF0036\"},\"bottomText\":\"66g\",\"value\":\"19.00\"},\"promotionList\":{\"bottomText\":\"\",\"selectedId\":\"\"},\"quantity\":{\"min\":\"1\",\"quantity\":\"1\",\"title\":\"购买数量\",\"step\":\"1\",\"editable\":false,\"max\":\"34\"},\"sellerNick\":\"天猫超市\",\"shopUrl\":\"//store.taobao.com/shop/view_shop.htm?shop_id=67597230\",\"skuId\":\"3902332244990\",\"skuInfo\":[{\"name\":\"颜色分类\",\"forOld\":false,\"value\":\"橘色\"}],\"subtitles\":[],\"title\":\"最生活小米毛巾方巾青春系列纯棉全棉家居吸水擦手巾洗脸1条装\"},\"hidden\":{\"extensionMap\":{\"orderOutId\":\"\",\"outId\":\"\",\"promotionType\":\"\",\"villagerOrderId\":\"0\"}},\"id\":\"ffb6f117987962fa1dded529a6eafe75\",\"ref\":\"1035de9\",\"submit\":true,\"tag\":\"itemInfoPC\",\"type\":\"native$null$itemRow\"},\"item_ffb6f117987962fa1dded529a6eafe75\":{\"fields\":{\"bgColor\":\"#fbfcff\",\"borderBottom\":\"1px dotted #ddd\",\"direction\":\"column\"},\"hidden\":{\"extensionMap\":{\"bizCode\":\"ali.china.tmall.supermarket\",\"cartId\":\"2760306201780\",\"itemId\":\"582408570328\",\"shoppingOrderId\":\"0\",\"skuId\":\"3902332244990\",\"valid\":\"true\",\"villagerId\":\"0\"}},\"id\":\"ffb6f117987962fa1dded529a6eafe75\",\"ref\":\"b72dcaa\",\"submit\":true,\"tag\":\"item\",\"type\":\"native$null$flex\"},\"menberBenefitsFlex_1\":{\"fields\":{\"direction\":\"column\",\"subDirection\":\"end\"},\"id\":\"1\",\"ref\":\"656e6af\",\"tag\":\"menberBenefitsFlex\",\"type\":\"native$null$flex\"},\"ncCheckCode_ncCheckCode1\":{\"fields\":{\"nc\":\"1\",\"token\":\"2de9ac25fd01b5a8246b8c81861a2511bc4c7788\"},\"id\":\"ncCheckCode1\",\"ref\":\"fc57d42\",\"submit\":true,\"tag\":\"ncCheckCode\",\"type\":\"native$null$ncCheckCode\"},\"orderDesc_orderDesc_1\":{\"fields\":{\"headers\":[\"店铺宝贝\",\"商品属性\",\"单价\",\"数量\",\"优惠方式\",\"小计\"],\"title\":\"确认订单信息\"},\"id\":\"orderDesc_1\",\"ref\":\"622cdf6\",\"tag\":\"orderDesc\",\"type\":\"native$null$itemHeader\"},\"orderExtLeftPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"column\",\"gap\":\"10\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"start\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"6f00335\",\"tag\":\"orderExtLeftPC\",\"type\":\"native$null$flex\"},\"orderExtPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"bgColor\":\"#f2f7ff\",\"borderBottom\":\"1px dotted #80b2ff\",\"direction\":\"column\",\"gridBorderColor\":\"#fff\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"98a96fa\",\"tag\":\"orderExtPC\",\"type\":\"native$null$flex\"},\"orderExtRightPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"column\",\"gap\":\"10\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"start\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"1dbade8\",\"tag\":\"orderExtRightPC\",\"type\":\"native$null$flex\"},\"orderExtUpperPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"row\",\"equality\":\"2\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"spaceBetween\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"dd37e71\",\"tag\":\"orderExtUpperPC\",\"type\":\"native$null$flex\"},\"orderPayLayoutPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"column\",\"mainDirection\":\"end\",\"padding\":\"10px 0\",\"subDirection\":\"end\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"0315aad\",\"tag\":\"orderPayLayoutPC\",\"type\":\"native$null$flex\"},\"orderPayPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"header\":\"店铺合计(含运费)\",\"value\":\"￥25.00\",\"valueStyles\":{\"color\":\"#FF0036\",\"bold\":true}},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"35419b9\",\"tag\":\"orderPayPC\",\"type\":\"native$null$label\"},\"order_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"cardGroup\":\"true\",\"fields\":{},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"a9dc6bd\",\"tag\":\"order\",\"type\":\"block$null$emptyBlock\"},\"postageInsurancePC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"amount\":\"0\",\"checkBoxOptions\":[{\"bizType\":\"1\",\"request\":true,\"tips\":\"退换货可赔付11元\",\"url\":\"https://render.alipay.com/p/h5/insscene/www/insureProtocol.html?1=1&buyerId=2453833782&sellerId=725677994&serviceId=1064\",\"viewPrice\":\"0.00\",\"storeId\":\"0\",\"useGroup\":\"yfx\",\"readonly\":false,\"text\":\"￥0.25购买\",\"checked\":false,\"price\":\"25\",\"value\":\"0.25\",\"serviceId\":\"1064\",\"supportOtherAddrees\":false}],\"checked\":false,\"choicePrice\":\"0.00\",\"groupTitle\":\"运费险\",\"outId\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"required\":false,\"supportOtherAddress\":false,\"vertical\":false},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"28becd6\",\"submit\":true,\"tag\":\"postageInsurancePC\",\"type\":\"native$null$postageInsurance\"},\"presellDescGroupPC_presellDescGroup_presellDescGroup1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{},\"id\":\"presellDescGroup_presellDescGroup1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"c315959\",\"tag\":\"presellDescGroupPC\",\"type\":\"native$null$descriptionGroup\"},\"realPayPC_1\":{\"fields\":{\"addressText\":\" 四川 成都 新都 三河 天河大道148号流溪苑\",\"fullName\":\"王飞\",\"mallTotalPrice\":\"2500\",\"microPurchaseTotalPrice\":\"0\",\"mobile\":\"13281835775\",\"needUpdate4Address\":false,\"originPrice\":\"25.00\",\"price\":\"25.00\",\"quantity\":\"1\",\"receiveMethodType\":\"normal\",\"selectedId\":\"12198497492\",\"tmallHkTotalPrice\":\"0\",\"weight\":\"66\"},\"hidden\":{\"extensionMap\":{\"timestamp\":\"1614091748782\"}},\"id\":\"1\",\"ref\":\"c601752\",\"tag\":\"realPayPC\",\"type\":\"native$null$realPay\"},\"riderClauseContentFlex_riderClause_content_1\":{\"fields\":{\"direction\":\"row\",\"gap\":\"30\",\"mainDirection\":\"end\"},\"id\":\"riderClause_content_1\",\"ref\":\"510e227\",\"tag\":\"riderClauseContentFlex\",\"type\":\"native$null$flex\"},\"riderClausePC_riderClause_1\":{\"fields\":{\"borderBottom\":\"1px dotted #ddd\",\"direction\":\"column\",\"mainDirection\":\"end\",\"padding\":\"0\",\"subDirection\":\"end\"},\"id\":\"riderClause_1\",\"ref\":\"4b36852\",\"tag\":\"riderClausePC\",\"type\":\"native$null$flex\"},\"seller_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"icon\":\"//img.alicdn.com/tfs/TB1wIDCJhTpK1RjSZFKXXa2wXXa-180-148.png\",\"isShowWangWang\":true,\"sellerUrl\":\"//member1.taobao.com/member/user_profile.jhtml?encUserNumId=IDX1Jz9fH5s29MFqSKaKlU6Ru_QjIoTAO6VuHvekguJTwYBsEZnbONXr7V_DsRDqJGdP&sign=6aeb81bbf4236df5c89427d2e61e6197\",\"shopLabel\":\"店铺\",\"shopName\":\"天猫超市\",\"shopUrl\":\"//store.taobao.com/shop/view_shop.htm?shop_id=67597230\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"7b734da\",\"tag\":\"seller\",\"type\":\"native$null$seller\"},\"stationAgencyService_1\":{\"fields\":{\"agencyId\":\"0\",\"agencyReceive\":\"1\",\"disable\":false,\"fullName\":\"王飞\",\"hasService\":true,\"helpLink\":\"//www.cainiao.com/markets/cnwww/userhelpdoc\",\"linkAddressId\":\"12198497492\",\"linkDivisionCode\":\"510114\",\"linkTownDivisionCode\":\"510114003\",\"mobile\":\"13281835775\",\"tip\":\"免费\",\"title\":\"菜鸟驿站代收服务\",\"useAgencyType\":\"0\"},\"id\":\"1\",\"ref\":\"7057e18\",\"submit\":true,\"tag\":\"stationAgencyService\",\"type\":\"native$null$stationAgencyService\"},\"stationTabPC_1\":{\"fields\":{},\"id\":\"1\",\"ref\":\"2ac3f2e\",\"tag\":\"stationTabPC\",\"type\":\"native$null$tab\"},\"stepbarPC_1\":{\"fields\":{\"current\":\"1\",\"isShowVillager\":false,\"options\":[\"查看购物车\",\"拍下商品\",\"付款到支付宝\",\"确认收货\",\"评价\"],\"stepBarType\":\"MALL_CART\"},\"id\":\"1\",\"ref\":\"e2d54b5\",\"tag\":\"stepbarPC\",\"type\":\"native$null$stepbar\"},\"submitOrderPC_1\":{\"fields\":{\"cartText\":\"返回购物车\",\"cartUrl\":\"//cart.taobao.com/cart.htm\",\"fromCart\":true,\"fromMallYaoCart\":false,\"isTmallHKPresellOrder\":false,\"isTmallHKPresellSelf\":false,\"needUpdate4Address\":false,\"priceTips\":\"\",\"showPrice\":\"25.00\",\"submitOrderType\":\"UNITY\",\"submitTitle\":\"提交订单\",\"tmallPointStatus\":\"0\"},\"hidden\":{\"extensionMap\":{\"action\":\"/order/multiTerminalSubmitOrderAction\",\"event_submit_do_confirm\":\"1\",\"input_charset\":\"utf-8\",\"pcSubmitUrl\":\"/auction/confirm_order.htm?x-itemid=582408570328&x-uid=2453833782\",\"secretKey\":\"submitref\",\"secretValue\":\"6d7ed324795973eeb2f5941e8d20316af37d2c6ffab5d881d5e08cee7703e99d\",\"sparam1\":\"eyJsaXN0Ijp7IjU2NjQ3MCI6eyJidXlhbW91bnQiOjEsImRpc2NvdW50VG90YWxGZWUiOjI1MDAsInNlbGxlcklkIjo3MjU2Nzc5OTQsInRvdGFsRmVlIjoyNTAwfX0sImxvZ2lzUmVjdkFkZHIiOiLlpKnmsrPlpKfpgZMxNDjlj7fmtYHmuqroi5EiLCJsb2dpc1JlY3ZNb2JsIjoiMTMyODE4MzU3NzUiLCJvcmRlcmZyb213aGVyZSI6InRtYWxsX3BjIn0=\",\"unitSuffix\":\"rz41\"}},\"id\":\"1\",\"ref\":\"35c4cbb\",\"submit\":true,\"tag\":\"submitOrderPC\",\"type\":\"native$null$submitOrder\"},\"urlTransferPC_1\":{\"fields\":{},\"id\":\"1\",\"ref\":\"65a4400\",\"tag\":\"urlTransferPC\",\"type\":\"native$null$urlTransfer\"}},\"linkage\":{\"common\":{\"compress\":true,\"queryParams\":\"^^$$Z2c8639e93078d873def1bfd4068f5001b|null{$_$}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\",\"submitParams\":\"^^$$Z272989a505445611f9b12a86009a3a5c0|null{$_$}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\",\"validateParams\":\"^^$$d0f150e9a660d7843376ebb4cd3eec78{$_$}H4sIAAAAAAAAAIVQy0oDQRD8lzmHIbvG7Ca3ze4KQgwLRsGTdGY6yZJ5MTNBY8gf+BlexO8Sf8NegxgP4q2r6K6qrj1betD4YP2mAZrCpXaKjffMKYhL6/V857DDQkEIM1plYyas5qDaBSyARw8SuVAtmsi1lah4sFsvkDcnCrcp6zFzvG5Kmq2XrQFySg495mCFf/lEsAuwnCydNZ2H3JFOK7jGCCd0t6DwkU9u7lLefAsetSly1KAU+UoMguD7y+vH8xthDX6DkZiffPOrYjr9FTGljMoKUN3zT+v7csaI2Qb0RQjtyvzfT1iDR8mdx0A/QGyt4a7rm1cQ4ZbKlF/1H3tClOUaxaakOtl4CSpgj6ERfufidfStWVGOUZYVadFP87Iuh6Pz/uQinQz71fAsT/JRUlR5lU3quhhkeVEnaTVgh8MnS7xYf+0BAAA=\"},\"input\":[\"invoicePC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"submitOrderPC_1\"],\"request\":[\"deliveryMethodPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"addressPC_1\",\"postageInsurancePC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"submitOrderPC_1\",\"stationAgencyService_1\",\"agencyPayPC_1\",\"itemInfoPC_ffb6f117987962fa1dded529a6eafe75\"],\"signature\":\"2cc4168d3fc941e761f2c8fe65fc4d40\",\"url\":\"/auction/json/async_linkage.do?_input_charset=utf-8\"},\"hierarchy\":{\"baseType\":[\"native$null$descriptionGroup\",\"native$null$label\",\"native$null$itemRow\",\"native$null$stepbar\",\"block$null$emptyBlock\",\"native$null$flex\",\"native$null$frontTrace\",\"native$null$urlTransfer\",\"native$null$seller\",\"native$null$invoice\",\"native$null$postageInsurance\",\"native$null$address\",\"native$null$ncCheckCode\",\"native$null$stationAgencyService\",\"native$null$itemHeader\",\"native$null$realPay\",\"native$null$submitOrder\",\"native$null$deliveryMethod\",\"native$null$tab\",\"native$null$checkbox\"],\"component\":[\"seller\",\"stationTabPC\",\"riderClausePC\",\"addressPC\",\"frontTracePC\",\"stationAgencyService\",\"orderExtPC\",\"postageInsurancePC\",\"orderExtLeftPC\",\"orderPayPC\",\"riderClauseContentFlex\",\"deliveryMethodPC\",\"agencyPayPC\",\"itemInfoPC\",\"orderPayLayoutPC\",\"orderDesc\",\"order\",\"item\",\"presellDescGroupPC\",\"menberBenefitsFlex\",\"anonymousPC\",\"orderExtRightPC\",\"realPayPC\",\"confirmOrder\",\"invoicePC\",\"submitOrderPC\",\"stepbarPC\",\"ncCheckCode\",\"orderExtUpperPC\",\"urlTransferPC\"],\"root\":\"confirmOrder_1\",\"structure\":{\"confirmOrder_1\":[\"stepbarPC_1\",\"addressPC_1\",\"stationTabPC_1\",\"orderDesc_orderDesc_1\",\"order_1b223073f1ff46ed9e2a17b89a33ddd2\",\"menberBenefitsFlex_1\",\"riderClausePC_riderClause_1\",\"realPayPC_1\",\"submitOrderPC_1\",\"frontTracePC_1\",\"urlTransferPC_1\",\"ncCheckCode_ncCheckCode1\"],\"item_ffb6f117987962fa1dded529a6eafe75\":[\"itemInfoPC_ffb6f117987962fa1dded529a6eafe75\"],\"orderExtLeftPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"invoicePC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderExtPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"orderExtUpperPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"orderPayLayoutPC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderExtRightPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"deliveryMethodPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"postageInsurancePC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderExtUpperPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"orderExtLeftPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"orderExtRightPC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderPayLayoutPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"orderPayPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"presellDescGroupPC_presellDescGroup_presellDescGroup1b223073f1ff46ed9e2a17b89a33ddd2\"],\"order_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"seller_1b223073f1ff46ed9e2a17b89a33ddd2\",\"item_ffb6f117987962fa1dded529a6eafe75\",\"orderExtPC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"riderClauseContentFlex_riderClause_content_1\":[\"agencyPayPC_1\",\"anonymousPC_1\"],\"riderClausePC_riderClause_1\":[\"riderClauseContentFlex_riderClause_content_1\"],\"stationTabPC_1\":[\"stationAgencyService_1\"]}},\"container\":{\"data\":[]},\"reload\":true}";
//        String orderDataStr = BuyService.getOrderData();

        // 从选择地址接口的参数中获取 下订单接口的 url 参数
        Map<String, Object> orderDataMap = JSONObject.toJavaObject(JSONObject.parseObject(orderDataStr), Map.class);



        Map<String, Object> dataMap = (Map<String, Object>)orderDataMap.get("data");
        Map<String, Object> submitOrderPC_1 = (Map<String, Object>)dataMap.get("submitOrderPC_1");
        Map<String, Object> hidden = (Map<String, Object>)submitOrderPC_1.get("hidden");
        Map<String, Object> extensionMap = (Map<String, Object>)hidden.get("extensionMap");
        String secretValue = extensionMap.get("secretValue").toString();
        String sparam1 = extensionMap.get("sparam1").toString();

        url += secretValue;
        url += "&sparam1=" + sparam1;

        // 清除掉多余的数据
        clearMap(orderDataMap);

        String endpointStr = orderDataMap.get("endpoint").toString();
        String dataStr = orderDataMap.get("data").toString();
        String linkageStr = orderDataMap.get("linkage").toString();
        String hierarchyStr = orderDataMap.get("hierarchy").toString();

        String endpointUrl = URLEncoder.encode(endpointStr, "utf-8");
        String dataUrl = URLEncoder.encode(dataStr, "utf-8");
        String linkageUrl = URLEncoder.encode(linkageStr, "utf-8");
        String hierarchyUrl = URLEncoder.encode(hierarchyStr, "utf-8");

        orderDataMap.put("endpoint", endpointUrl);
        orderDataMap.put("data", dataUrl);
        orderDataMap.put("linkage", linkageUrl);
        orderDataMap.put("hierarchy", hierarchyUrl);


        orderDataMap.put("input_charset", "utf-8");
        orderDataMap.put("praper_alipay_cashier_domain", "cashierrz41");
        orderDataMap.put("event_submit_do_confirm", "1");
        orderDataMap.put("_tb_token_", "fbe5b7348e1db");
        orderDataMap.put("action", "/order/multiTerminalSubmitOrderAction");

        log.info("下订单接口请求参数(json)  : {}", JSONObject.toJSONString(orderDataMap));

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> set : orderDataMap.entrySet()){
            sb.append(set.getKey()).append("=").append(set.getValue()).append("&");
        }

        String bodyStr = sb.substring(0, sb.length() -1);
        Object result = sendPost(preUrl + url, bodyStr, url);
        log.info("下订单接口请求参数 : {}", bodyStr);

        System.out.println("result");
        System.out.println(result);


    }

    private static void clearMap(Map<String, Object> map){
        // 0.container
        map.remove("container");
        map.remove("reload");

        // 1.linkage
        Map<String, Object> linkage = (Map<String, Object>)map.get("linkage");

        linkage.remove("input");
        linkage.remove("request");
        linkage.remove("url");
        Map<String, Object> common = (Map<String, Object>)linkage.get("common");
        common.remove("queryParams");

        // 2.hierarchy
        Map<String, Object> hierarchy = (Map<String, Object>)map.get("hierarchy");
        hierarchy.remove("baseType");
        hierarchy.remove("component");
        hierarchy.remove("root");

        // 3.data  frontTracePC_1
        Map<String, Object> data = (Map<String, Object>)map.get("data");
        data.remove("frontTracePC_1");
        data.remove("menberBenefitsFlex_1");
        data.remove("orderDesc_orderDesc_1");
        data.remove("realPayPC_1");
        data.remove("riderClauseContentFlex_riderClause_content_1");
        data.remove("riderClausePC_riderClause_1");
        data.remove("stationTabPC_1");
        data.remove("stepbarPC_1");
        data.remove("urlTransferPC_1");

        List<String> removeKeys = new ArrayList<>();
        for(Map.Entry<String, Object> set : data.entrySet()){
            if(set.getKey().contains("orderExtLeftPC_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("orderExtPC_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("orderExtRightPC_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("orderExtUpperPC_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("orderPayLayoutPC_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("orderPayPC_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("order_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("presellDescGroupPC_presellDescGroup_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("seller_")){
                removeKeys.add(set.getKey());
            }else if(set.getKey().contains("itemInfoPC_")){
                Map itemInfoPC_ = (Map)set.getValue();
                Map fields = (Map)itemInfoPC_.get("fields");
                Map priceInfo = (Map)fields.get("priceInfo");
                Map valueStyles = (Map)priceInfo.get("valueStyles");
                valueStyles.remove("bold", Boolean.TRUE);
            }
        }
        for(String key : removeKeys){
            data.remove(key);
        }
    }


}

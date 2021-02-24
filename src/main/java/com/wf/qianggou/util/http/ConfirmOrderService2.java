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
//            post.addHeader(new BasicHeader("accept-encoding", "gzip, deflate, br"));
            post.addHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
            post.addHeader(new BasicHeader("cache-control", "max-age=0"));
            post.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            post.addHeader(new BasicHeader("Cookie", "cq=ccp%3D0; ubn=p; ucn=center; _bl_uid=Cpkkvl7OhhzahIm9Uzts580q5yXO; t=a51b8b7d7a650d13d6143d0ceeabd60c; lid=%E5%B0%8F%E9%A3%9E546466464; _tb_token_=34a3ed0165387; cookie2=19986533f8b4ab3a058294eb4e19e07e; cna=vswvGMk9ymUCAbaWP+aWkFr3; _m_h5_tk=a64a976ff643ea9a1b23770c73732b74_1614140873935; _m_h5_tk_enc=45a447b9ced4c3bc739c3cda8beda569; xlly_s=1; sm4=510100; dnk=%5Cu5C0F%5Cu98DE546466464; uc1=cookie14=Uoe1hgfmeqMWkQ%3D%3D&cookie16=VFC%2FuZ9az08KUQ56dCrZDlbNdA%3D%3D&cookie15=U%2BGCWk%2F75gdr5Q%3D%3D&existShop=true&pas=0&cookie21=VT5L2FSpccLuJBreKQgf; uc3=nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=UIHiLt3xD8xYTw%3D%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&vt3=F8dCuASgNtjn%2BY4s29g%3D; tracknick=%5Cu5C0F%5Cu98DE546466464; uc4=nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMaJT0uD9&id4=0%40U27KCxnkg%2BYg1C7JnsLDOZdQFiX4; _l_g_=Ug%3D%3D; unb=2453833782; lgc=%5Cu5C0F%5Cu98DE546466464; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; login=true; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100iqrYEwsJsqzp9NT28Id3yF9R0JuTJC9FbeFAMppo7BUXLQkHny1OWQRSK3PYx2qvccXpgW4hcClROUYX1mjUoA%3D%3D; sg=424; csg=5d623f87; l=eBP-ZQ7PO6NKKMK2BOfZourza7798IRf_uPzaNbMiOCPOz5p583PW6gWyET9CnGVnsDwR3Rj0APzBVT8qyzHhJc0Pario1_zqdTh.; tfstk=cKxhBbjeCe7IpHbMlDsB3zm9lRuOZxnPGg7kbhEU5A3ytI-NiV4au6rQItOH2z1..; isg=BBAQzkAiRKYH0Sc_ZG7NwDsV4V5i2fQjql6xEgrhgGs-RbHvsu0Csud3HQ2lkqz7; x5sec=7b22627579323b32223a2236663839666261353039323962623862363236313461373739636463663532324350714331344547454f766e725a4377344c75652b774561444449304e544d344d7a4d334f4449374d5367434d4d75622b366348227d"));
            post.addHeader(new BasicHeader("Origin", "https://buy.tmall.com"));
            post.addHeader(new BasicHeader("Referer", "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined"));
            post.addHeader(new BasicHeader("sec-fetch-dest", "document"));
            post.addHeader(new BasicHeader("sec-fetch-mode", "navigate"));
            post.addHeader(new BasicHeader("sec-fetch-site", "same-origin"));
            post.addHeader(new BasicHeader("sec-fetch-user", "?1"));
            post.addHeader(new BasicHeader("upgrade-insecure-requests", "1"));
            post.addHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36"));
            post.addHeader(new BasicHeader("x-requested-with", "XMLHttpRequest"));
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
        String orderDataStr = "{\"endpoint\":{\"mode\":\"pc\",\"osVersion\":\"PC\",\"protocolVersion\":\"3.0\",\"ultronage\":\"true\"},\"data\":{\"addressPC_1\":{\"fields\":{\"addrMakerUrl\":\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\",\"defaultAddressAPI\":\"/auction/update_address_selected_status.htm?_input_charset=utf-8\",\"disableCurrentSelected\":false,\"lat\":\"30.773273\",\"linkAddressId\":\"0\",\"lng\":\"104.125916\",\"managerAddressUrl\":\"//member1.taobao.com/member/fresh/deliver_address.htm\",\"optStr\":\"[{\\\"addrMakerUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=12198497492&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"addressDetail\\\":\\\"天河大道148号流溪苑\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"addressUrl\\\":{\\\"addAddressAPI\\\":\\\"/auction/add_buyer_address.htm?_input_charset=utf-8\\\",\\\"addressUrlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=12198497492&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"defaultAddressAPI\\\":\\\"/auction/update_address_selected_status.htm?_input_charset=utf-8\\\",\\\"managerAddressUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address.htm\\\",\\\"postCodeAPI\\\":\\\"/auction/json/get_postcode.do?_input_charset=utf-8\\\",\\\"updateAddressAPI\\\":\\\"/auction/update_buyer_address.htm?_input_charset=utf-8\\\",\\\"urlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\\\"},\\\"areaName\\\":\\\"新都\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":true,\\\"deliveryAddressId\\\":12198497492,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510114\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"5976933\\\",\\\"lat\\\":\\\"30.773273\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"104.125916\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510114003,\\\"townName\\\":\\\"三河\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addressDetail\\\":\\\"清溪雅筑13栋\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"青羊\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":8809318602,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510105\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"5976934\\\",\\\"lat\\\":\\\"30.667978\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"103.974563\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510105013,\\\"townName\\\":\\\"苏坡\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addressDetail\\\":\\\"四川省内江市东兴区百合镇农贸市场\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"东兴\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"内江\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":6471596766,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"511011\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"5976935\\\",\\\"lat\\\":\\\"29.686443\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"105.291406\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"641100\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":511011103,\\\"townName\\\":\\\"白合\\\",\\\"updateAddressTip\\\":\\\"\\\"}]\",\"options\":[{\"deliveryAddressId\":\"12198497492\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"成都\",\"addressDetail\":\"天河大道148号流溪苑\",\"needUpdate4Address\":false,\"divisionCode\":\"510114\",\"enforceUpdate4Address\":true,\"areaName\":\"新都\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"000000\",\"townDivisionId\":\"510114003\",\"defaultAddress\":true,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"三河\",\"enableMDZT\":false},{\"deliveryAddressId\":\"8809318602\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"成都\",\"addressDetail\":\"清溪雅筑13栋\",\"needUpdate4Address\":false,\"divisionCode\":\"510105\",\"enforceUpdate4Address\":true,\"areaName\":\"青羊\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"000000\",\"townDivisionId\":\"510105013\",\"defaultAddress\":false,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"苏坡\",\"enableMDZT\":false},{\"deliveryAddressId\":\"6471596766\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"内江\",\"addressDetail\":\"四川省内江市东兴区百合镇农贸市场\",\"needUpdate4Address\":false,\"divisionCode\":\"511011\",\"enforceUpdate4Address\":true,\"areaName\":\"东兴\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"641100\",\"townDivisionId\":\"511011103\",\"defaultAddress\":false,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"白合\",\"enableMDZT\":false}],\"receiveMethodType\":\"normal\",\"selectedId\":\"12198497492\",\"sites\":\"[]\",\"tips\":{},\"title\":\"选择收货地址\",\"updateAddressAPI\":\"/auction/update_buyer_address.htm?_input_charset=utf-8\",\"useMDZT\":false,\"useStation\":false},\"id\":\"1\",\"ref\":\"0394592\",\"submit\":true,\"tag\":\"addressPC\",\"type\":\"native$null$address\"},\"agencyPayPC_1\":{\"fields\":{\"checked\":false,\"disabled\":false,\"label\":\"朋友代付\"},\"id\":\"1\",\"ref\":\"c287c09\",\"submit\":true,\"tag\":\"agencyPayPC\",\"type\":\"native$null$checkbox\"},\"anonymousPC_1\":{\"fields\":{\"checked\":true,\"disabled\":false,\"label\":\"匿名购买\"},\"id\":\"1\",\"ref\":\"907bd01\",\"submit\":true,\"tag\":\"anonymousPC\",\"type\":\"native$null$checkbox\"},\"confirmOrder_1\":{\"fields\":{},\"hidden\":{\"extensionMap\":{\"joinId\":\"ffb6f117987962fa1dded529a6eafe75\",\"pageType\":\"TMALL\",\"sid\":\"5d783a304eebc61604fd73a6a30c5323\",\"umidToken\":\"Taa40f77b9e7bbe8bd070ad652b3c7218\"}},\"id\":\"1\",\"ref\":\"8318d7a\",\"submit\":true,\"tag\":\"confirmOrder\",\"type\":\"block$null$emptyBlock\"},\"deliveryMethodPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"checked\":true,\"hasCod\":false,\"options\":[{\"serviceIcon\":\"//img.alicdn.com/tfs/TB1No7XbvxNTKJjy0FjXXX6yVXa-145-28.png\",\"fare\":\"6.00\",\"serviceType\":\"5\",\"message\":\"快递￥6.00\",\"datePicker\":{\"headTip\":\"不可抗力等因素可致延误 <a href=\\\"//service.tmall.com/support/tmall/knowledge-5811867.htm\\\">详情</a>\",\"periods\":[\"09:00-14:00\",\"09:00-21:00\",\"14:00-19:00\"],\"selectedPeriods\":\"09:00-14:00\",\"endDate\":\"2021-03-03\",\"payTimeTip\":\"请在23:59前付款\",\"useDefault\":false,\"enableCancel\":true,\"disable\":[\"0_0\",\"0_1\",\"0_2\"],\"beginDate\":\"2021-02-25\"},\"signText\":\"23:59前付款，承诺2月25日送达\",\"extra\":\"\",\"titleText\":\"普通配送\",\"hasOption\":false,\"appointPromotionTip\":\"\",\"id\":\"2_5_0\",\"userChooseLogisticsInfo\":\"{\\\"childServiceType\\\":5,\\\"cp\\\":\\\"default\\\",\\\"logisticsType\\\":0,\\\"serviceType\\\":5}\",\"fareCent\":\"600\"}],\"secondOption\":false,\"selectedId\":\"2_5_0\"},\"hidden\":{\"extensionMap\":{\"deliveryId\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"options\":\"[{\\\"datePicker\\\":{\\\"beginDate\\\":\\\"2021-02-25\\\",\\\"disable\\\":[\\\"0_0\\\",\\\"0_1\\\",\\\"0_2\\\"],\\\"enableCancel\\\":true,\\\"endDate\\\":\\\"2021-03-03\\\",\\\"headTip\\\":\\\"不可抗力等因素可致延误 <a href=\\\\\\\"//service.tmall.com/support/tmall/knowledge-5811867.htm\\\\\\\">详情</a>\\\",\\\"payTimeTip\\\":\\\"请在23:59前付款\\\",\\\"periods\\\":[\\\"09:00-14:00\\\",\\\"09:00-21:00\\\",\\\"14:00-19:00\\\"],\\\"selectedPeriods\\\":\\\"09:00-14:00\\\",\\\"useDefault\\\":false},\\\"fareCent\\\":600,\\\"id\\\":\\\"2_5_0\\\",\\\"serviceType\\\":\\\"5\\\"}]\"}},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"2312f91\",\"submit\":true,\"tag\":\"deliveryMethodPC\",\"type\":\"native$null$deliveryMethod\"},\"frontTracePC_1\":{\"fields\":{\"joinId\":\"2680121666166\"},\"id\":\"1\",\"ref\":\"e61fd52\",\"tag\":\"frontTracePC\",\"type\":\"native$null$frontTrace\"},\"invoicePC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"canModifyTitle\":true,\"category\":[{\"id\":\"0\",\"text\":\"电子发票\",\"type\":\"1\"}],\"checked\":false,\"defaultAddressName\":\"个人\",\"einvoice\":true,\"enotice\":\"1、发票金额不包括点券、积分或其他类似折扣券抵减的金额；<br>2、电子发票可以作为售后维权的有效凭证，财务报销规定请参照 <a href='//service.taobao.com/support/knowledge-6502401.htm'>帮助中心。</a>\",\"invoiceMust\":false,\"options\":[{\"id\":\"0\",\"text\":\"明细\"}],\"selectedId\":\"0_0\",\"taxNo\":\"\",\"title\":\"个人\",\"uses\":[{\"id\":\"0\",\"text\":\"个人\",\"type\":\"1\"},{\"id\":\"1\",\"text\":\"企业\",\"type\":\"2\"}],\"usesSelectedId\":\"0\",\"valueAddTax\":true},\"hidden\":{\"extensionMap\":{\"categorySize\":\"1\"}},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"7f3cd76\",\"submit\":true,\"tag\":\"invoicePC\",\"type\":\"native$null$invoice\"},\"itemInfoPC_ffb6f117987962fa1dded529a6eafe75\":{\"fields\":{\"icons\":[{\"title\":\"消费者保障服务，卖家承诺7天退换\",\"text\":\"\",\"image\":\"//img.alicdn.com/tps/i3/T1Vyl6FCBlXXaSQP_X-16-16.png\",\"link\":\"//pages.tmall.com/wow/seller/act/seven-day\",\"sortNo\":\"10\",\"iconType\":\"main\"},{\"title\":\"消费者保障服务，卖家承诺如实描述\",\"text\":\"\",\"image\":\"//img.alicdn.com/tps/i4/T1BCidFrNlXXaSQP_X-16-16.png\",\"link\":\"//www.taobao.com/go/act/315/xfzbz_rsms.php?ad_id=&am_id=130011830696bce9eda3&cm_id=&pm_id=\",\"sortNo\":\"11\",\"iconType\":\"main\"},{\"title\":\"支持信用卡支付\",\"text\":\"\",\"image\":\"//assets.alicdn.com/sys/common/icon/trade/xcard.png\",\"iconType\":\"main\"}],\"isGift\":false,\"itemInfoId\":\"582408570328\",\"itemUrl\":\"//detail.tmall.com/item.htm?id=582408570328\",\"pic\":\"//img.alicdn.com/imgextra/i1/725677994/O1CN01G9VbQ128vIcySKQYw_!!725677994.jpg\",\"price\":\"19.00\",\"priceInfo\":{\"valueStyles\":{\"color\":\"#FF0036\"},\"bottomText\":\"66g\",\"value\":\"19.00\"},\"promotionList\":{\"bottomText\":\"\",\"selectedId\":\"\"},\"quantity\":{\"min\":\"1\",\"quantity\":\"1\",\"title\":\"购买数量\",\"step\":\"1\",\"editable\":false,\"max\":\"34\"},\"sellerNick\":\"天猫超市\",\"shopUrl\":\"//store.taobao.com/shop/view_shop.htm?shop_id=67597230\",\"skuId\":\"3902332244990\",\"skuInfo\":[{\"name\":\"颜色分类\",\"forOld\":false,\"value\":\"橘色\"}],\"subtitles\":[],\"title\":\"最生活小米毛巾方巾青春系列纯棉全棉家居吸水擦手巾洗脸1条装\"},\"hidden\":{\"extensionMap\":{\"orderOutId\":\"\",\"outId\":\"\",\"promotionType\":\"\",\"villagerOrderId\":\"0\"}},\"id\":\"ffb6f117987962fa1dded529a6eafe75\",\"ref\":\"1035de9\",\"submit\":true,\"tag\":\"itemInfoPC\",\"type\":\"native$null$itemRow\"},\"item_ffb6f117987962fa1dded529a6eafe75\":{\"fields\":{\"bgColor\":\"#fbfcff\",\"borderBottom\":\"1px dotted #ddd\",\"direction\":\"column\"},\"hidden\":{\"extensionMap\":{\"bizCode\":\"ali.china.tmall.supermarket\",\"cartId\":\"2760306201780\",\"itemId\":\"582408570328\",\"shoppingOrderId\":\"0\",\"skuId\":\"3902332244990\",\"valid\":\"true\",\"villagerId\":\"0\"}},\"id\":\"ffb6f117987962fa1dded529a6eafe75\",\"ref\":\"b72dcaa\",\"submit\":true,\"tag\":\"item\",\"type\":\"native$null$flex\"},\"menberBenefitsFlex_1\":{\"fields\":{\"direction\":\"column\",\"subDirection\":\"end\"},\"id\":\"1\",\"ref\":\"656e6af\",\"tag\":\"menberBenefitsFlex\",\"type\":\"native$null$flex\"},\"ncCheckCode_ncCheckCode1\":{\"fields\":{\"nc\":\"1\",\"token\":\"03a395ad86b5092b1fcf48b8356e44c6c5cf1b92\"},\"id\":\"ncCheckCode1\",\"ref\":\"fc57d42\",\"submit\":true,\"tag\":\"ncCheckCode\",\"type\":\"native$null$ncCheckCode\"},\"orderDesc_orderDesc_1\":{\"fields\":{\"headers\":[\"店铺宝贝\",\"商品属性\",\"单价\",\"数量\",\"优惠方式\",\"小计\"],\"title\":\"确认订单信息\"},\"id\":\"orderDesc_1\",\"ref\":\"622cdf6\",\"tag\":\"orderDesc\",\"type\":\"native$null$itemHeader\"},\"orderExtLeftPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"column\",\"gap\":\"10\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"start\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"6f00335\",\"tag\":\"orderExtLeftPC\",\"type\":\"native$null$flex\"},\"orderExtPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"bgColor\":\"#f2f7ff\",\"borderBottom\":\"1px dotted #80b2ff\",\"direction\":\"column\",\"gridBorderColor\":\"#fff\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"98a96fa\",\"tag\":\"orderExtPC\",\"type\":\"native$null$flex\"},\"orderExtRightPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"column\",\"gap\":\"10\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"start\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"1dbade8\",\"tag\":\"orderExtRightPC\",\"type\":\"native$null$flex\"},\"orderExtUpperPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"row\",\"equality\":\"2\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"spaceBetween\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"dd37e71\",\"tag\":\"orderExtUpperPC\",\"type\":\"native$null$flex\"},\"orderPayLayoutPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"direction\":\"column\",\"mainDirection\":\"end\",\"padding\":\"10px 0\",\"subDirection\":\"end\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"0315aad\",\"tag\":\"orderPayLayoutPC\",\"type\":\"native$null$flex\"},\"orderPayPC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"header\":\"店铺合计(含运费)\",\"value\":\"￥25.00\",\"valueStyles\":{\"color\":\"#FF0036\",\"bold\":true}},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"35419b9\",\"tag\":\"orderPayPC\",\"type\":\"native$null$label\"},\"order_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"cardGroup\":\"true\",\"fields\":{},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"a9dc6bd\",\"tag\":\"order\",\"type\":\"block$null$emptyBlock\"},\"postageInsurancePC_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"amount\":\"0\",\"checkBoxOptions\":[{\"bizType\":\"1\",\"request\":true,\"tips\":\"退换货可赔付11元\",\"url\":\"https://render.alipay.com/p/h5/insscene/www/insureProtocol.html?1=1&buyerId=2453833782&sellerId=725677994&serviceId=1064\",\"viewPrice\":\"0.00\",\"storeId\":\"0\",\"useGroup\":\"yfx\",\"readonly\":false,\"text\":\"￥0.21购买\",\"checked\":false,\"price\":\"21\",\"value\":\"0.21\",\"serviceId\":\"1064\",\"supportOtherAddrees\":false}],\"checked\":false,\"choicePrice\":\"0.00\",\"groupTitle\":\"运费险\",\"outId\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"required\":false,\"supportOtherAddress\":false,\"vertical\":false},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"28becd6\",\"submit\":true,\"tag\":\"postageInsurancePC\",\"type\":\"native$null$postageInsurance\"},\"presellDescGroupPC_presellDescGroup_presellDescGroup1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{},\"id\":\"presellDescGroup_presellDescGroup1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"c315959\",\"tag\":\"presellDescGroupPC\",\"type\":\"native$null$descriptionGroup\"},\"realPayPC_1\":{\"fields\":{\"addressText\":\" 四川 成都 新都 三河 天河大道148号流溪苑\",\"fullName\":\"王飞\",\"mallTotalPrice\":\"2500\",\"microPurchaseTotalPrice\":\"0\",\"mobile\":\"13281835775\",\"needUpdate4Address\":false,\"originPrice\":\"25.00\",\"price\":\"25.00\",\"quantity\":\"1\",\"receiveMethodType\":\"normal\",\"selectedId\":\"12198497492\",\"tmallHkTotalPrice\":\"0\",\"weight\":\"66\"},\"hidden\":{\"extensionMap\":{\"timestamp\":\"1614134847041\"}},\"id\":\"1\",\"ref\":\"c601752\",\"tag\":\"realPayPC\",\"type\":\"native$null$realPay\"},\"riderClauseContentFlex_riderClause_content_1\":{\"fields\":{\"direction\":\"row\",\"gap\":\"30\",\"mainDirection\":\"end\"},\"id\":\"riderClause_content_1\",\"ref\":\"510e227\",\"tag\":\"riderClauseContentFlex\",\"type\":\"native$null$flex\"},\"riderClausePC_riderClause_1\":{\"fields\":{\"borderBottom\":\"1px dotted #ddd\",\"direction\":\"column\",\"mainDirection\":\"end\",\"padding\":\"0\",\"subDirection\":\"end\"},\"id\":\"riderClause_1\",\"ref\":\"4b36852\",\"tag\":\"riderClausePC\",\"type\":\"native$null$flex\"},\"seller_1b223073f1ff46ed9e2a17b89a33ddd2\":{\"fields\":{\"icon\":\"//img.alicdn.com/tfs/TB1wIDCJhTpK1RjSZFKXXa2wXXa-180-148.png\",\"isShowWangWang\":true,\"sellerUrl\":\"//member1.taobao.com/member/user_profile.jhtml?encUserNumId=IDX1OPZ9tRVjrqe_T1JzteZZclw1xYrZ-5E4xYMPvKMm-XBFVdUJssnwBVYruUg9Dj0W&sign=55695a8465c07c349964b39d57d9d0cc\",\"shopLabel\":\"店铺\",\"shopName\":\"天猫超市\",\"shopUrl\":\"//store.taobao.com/shop/view_shop.htm?shop_id=67597230\"},\"id\":\"1b223073f1ff46ed9e2a17b89a33ddd2\",\"ref\":\"7b734da\",\"tag\":\"seller\",\"type\":\"native$null$seller\"},\"stationAgencyService_1\":{\"fields\":{\"agencyId\":\"0\",\"agencyReceive\":\"1\",\"disable\":false,\"fullName\":\"王飞\",\"hasService\":true,\"helpLink\":\"//www.cainiao.com/markets/cnwww/userhelpdoc\",\"linkAddressId\":\"12198497492\",\"linkDivisionCode\":\"510114\",\"linkTownDivisionCode\":\"510114003\",\"mobile\":\"13281835775\",\"tip\":\"免费\",\"title\":\"菜鸟驿站代收服务\",\"useAgencyType\":\"0\"},\"id\":\"1\",\"ref\":\"7057e18\",\"submit\":true,\"tag\":\"stationAgencyService\",\"type\":\"native$null$stationAgencyService\"},\"stationTabPC_1\":{\"fields\":{},\"id\":\"1\",\"ref\":\"2ac3f2e\",\"tag\":\"stationTabPC\",\"type\":\"native$null$tab\"},\"stepbarPC_1\":{\"fields\":{\"current\":\"1\",\"isShowVillager\":false,\"options\":[\"查看购物车\",\"拍下商品\",\"付款到支付宝\",\"确认收货\",\"评价\"],\"stepBarType\":\"MALL_CART\"},\"id\":\"1\",\"ref\":\"e2d54b5\",\"tag\":\"stepbarPC\",\"type\":\"native$null$stepbar\"},\"submitOrderPC_1\":{\"fields\":{\"cartText\":\"返回购物车\",\"cartUrl\":\"//cart.taobao.com/cart.htm\",\"fromCart\":true,\"fromMallYaoCart\":false,\"isTmallHKPresellOrder\":false,\"isTmallHKPresellSelf\":false,\"needUpdate4Address\":false,\"priceTips\":\"\",\"showPrice\":\"25.00\",\"submitOrderType\":\"UNITY\",\"submitTitle\":\"提交订单\",\"tmallPointStatus\":\"0\"},\"hidden\":{\"extensionMap\":{\"action\":\"/order/multiTerminalSubmitOrderAction\",\"event_submit_do_confirm\":\"1\",\"input_charset\":\"utf-8\",\"pcSubmitUrl\":\"/auction/confirm_order.htm?x-itemid=582408570328&x-uid=2453833782\",\"secretKey\":\"submitref\",\"secretValue\":\"6e920b86add61fdd2ee414945ed6f8f632bb0b230cd4c56943c40c81165cb3be\",\"sparam1\":\"eyJsaXN0Ijp7Ijc2NzMzNCI6eyJidXlhbW91bnQiOjEsImRpc2NvdW50VG90YWxGZWUiOjI1MDAsInNlbGxlcklkIjo3MjU2Nzc5OTQsInRvdGFsRmVlIjoyNTAwfX0sImxvZ2lzUmVjdkFkZHIiOiLlpKnmsrPlpKfpgZMxNDjlj7fmtYHmuqroi5EiLCJsb2dpc1JlY3ZNb2JsIjoiMTMyODE4MzU3NzUiLCJvcmRlcmZyb213aGVyZSI6InRtYWxsX3BjIn0=\",\"unitSuffix\":\"rz41\"}},\"id\":\"1\",\"ref\":\"35c4cbb\",\"submit\":true,\"tag\":\"submitOrderPC\",\"type\":\"native$null$submitOrder\"},\"urlTransferPC_1\":{\"fields\":{},\"id\":\"1\",\"ref\":\"65a4400\",\"tag\":\"urlTransferPC\",\"type\":\"native$null$urlTransfer\"}},\"linkage\":{\"common\":{\"compress\":true,\"queryParams\":\"^^$$Z27580c4cac9a7837f7fec284a6984f865|null{$_$}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\",\"submitParams\":\"^^$$Z233dd137634a7ec8243f0fab73f161cb2|null{$_$}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\",\"validateParams\":\"^^$$c7f6628523c0cbfd87c7ecf63bc28416{$_$}H4sIAAAAAAAAAIVQy0oDQRD8lzmHYd1snrfdzQpCDAvGgCfpzHSSJfNiZoLGkD/wM7yI3yX+hr0GMR7EW1fRXVVdB7byoPHB+m0NNIUr7RQbH5hTEFfW6/neYYuFghBmtMrGTFjNQTVLWAKPHiRyoRo0kWsrUfFgd14gr88UFinrMHO6rkuarZeNAXK6OHaYgzX+5RPBLsFysnTWtB5yTzqN4BojnNHtgsJHXtzepbz+FjxpU+SoQSnylRgEwfeX14/nN8Ia/BYjMT/55tf5dPorYkoZlRWg2uefNvfljBGzC+jzEJq1+b+fsAGPkjuPgX6A2FjDXds3n0CEBZUpv+o/9YQoyw2KbUl1svEKVMAOQyP83sWb6BuzphyjwSBP8yQdllXZH/WSIqmKyzTLkqrK82HaL0a9apANu3kvq7qToseOx08RaLPN7QEAAA==\"},\"input\":[\"invoicePC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"submitOrderPC_1\"],\"request\":[\"deliveryMethodPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"addressPC_1\",\"postageInsurancePC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"submitOrderPC_1\",\"stationAgencyService_1\",\"agencyPayPC_1\",\"itemInfoPC_ffb6f117987962fa1dded529a6eafe75\"],\"signature\":\"2cc4168d3fc941e761f2c8fe65fc4d40\",\"url\":\"/auction/json/async_linkage.do?_input_charset=utf-8\"},\"hierarchy\":{\"baseType\":[\"native$null$descriptionGroup\",\"native$null$label\",\"native$null$itemRow\",\"native$null$stepbar\",\"block$null$emptyBlock\",\"native$null$flex\",\"native$null$frontTrace\",\"native$null$urlTransfer\",\"native$null$seller\",\"native$null$invoice\",\"native$null$postageInsurance\",\"native$null$address\",\"native$null$ncCheckCode\",\"native$null$stationAgencyService\",\"native$null$itemHeader\",\"native$null$realPay\",\"native$null$submitOrder\",\"native$null$deliveryMethod\",\"native$null$tab\",\"native$null$checkbox\"],\"component\":[\"seller\",\"stationTabPC\",\"riderClausePC\",\"addressPC\",\"frontTracePC\",\"stationAgencyService\",\"orderExtPC\",\"postageInsurancePC\",\"orderExtLeftPC\",\"orderPayPC\",\"riderClauseContentFlex\",\"deliveryMethodPC\",\"agencyPayPC\",\"itemInfoPC\",\"orderPayLayoutPC\",\"orderDesc\",\"order\",\"item\",\"presellDescGroupPC\",\"menberBenefitsFlex\",\"anonymousPC\",\"orderExtRightPC\",\"realPayPC\",\"confirmOrder\",\"invoicePC\",\"submitOrderPC\",\"stepbarPC\",\"ncCheckCode\",\"orderExtUpperPC\",\"urlTransferPC\"],\"root\":\"confirmOrder_1\",\"structure\":{\"confirmOrder_1\":[\"stepbarPC_1\",\"addressPC_1\",\"stationTabPC_1\",\"orderDesc_orderDesc_1\",\"order_1b223073f1ff46ed9e2a17b89a33ddd2\",\"menberBenefitsFlex_1\",\"riderClausePC_riderClause_1\",\"realPayPC_1\",\"submitOrderPC_1\",\"frontTracePC_1\",\"urlTransferPC_1\",\"ncCheckCode_ncCheckCode1\"],\"item_ffb6f117987962fa1dded529a6eafe75\":[\"itemInfoPC_ffb6f117987962fa1dded529a6eafe75\"],\"orderExtLeftPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"invoicePC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderExtPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"orderExtUpperPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"orderPayLayoutPC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderExtRightPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"deliveryMethodPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"postageInsurancePC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderExtUpperPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"orderExtLeftPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"orderExtRightPC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"orderPayLayoutPC_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"orderPayPC_1b223073f1ff46ed9e2a17b89a33ddd2\",\"presellDescGroupPC_presellDescGroup_presellDescGroup1b223073f1ff46ed9e2a17b89a33ddd2\"],\"order_1b223073f1ff46ed9e2a17b89a33ddd2\":[\"seller_1b223073f1ff46ed9e2a17b89a33ddd2\",\"item_ffb6f117987962fa1dded529a6eafe75\",\"orderExtPC_1b223073f1ff46ed9e2a17b89a33ddd2\"],\"riderClauseContentFlex_riderClause_content_1\":[\"agencyPayPC_1\",\"anonymousPC_1\"],\"riderClausePC_riderClause_1\":[\"riderClauseContentFlex_riderClause_content_1\"],\"stationTabPC_1\":[\"stationAgencyService_1\"]}},\"container\":{\"data\":[]},\"reload\":true}";
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

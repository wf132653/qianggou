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


        JSONObject jsonObject = null;
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
            post.addHeader(new BasicHeader("Cookie", "cq=ccp%3D0; cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _bl_uid=Cpkkvl7OhhzahIm9Uzts580q5yXO; _m_h5_tk=f408ae7cdb0a1b6a04dae73acfc8d805_1614050863176; _m_h5_tk_enc=a970bd8a4855f8e2cbde5648290f79a8; xlly_s=1; login=true; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&pas=0&cookie21=VT5L2FSpccLuJBreKQgf&cookie14=Uoe1hgbXafOgEg%3D%3D&existShop=true&cart_m=0&cookie15=VFC%2FuZ9ayeYq2g%3D%3D; uc3=lg2=U%2BGCWk%2F75gdr5Q%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&vt3=F8dCuASh7qCZTbI2u%2Fw%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOKY%2B5aUn&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMCI2LAuk; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100Izywjkc2GOFw5N23PoPRlTmqUpz%2FKg6keugoH7gc%2FHYUktpsmsOozRnU9d546RQVgJfoeX1RLVYYMxMIHydDxQ%3D%3D; sg=424; csg=74dd55bb; sm4=510114; isg=BHd3GlW_23qr8WBqZ-vCbUiIBmvBPEuemfM2J8kkk8ateJe60Qzb7jVaWtgmiyMW; l=eBP-ZQ7PO6NKK5zSBOfanurza77OSIRYYuPzaNbMiOCPO_fB5j_AW6gR_2L6C3GVh6SMR3Rj0APyBeYBqQAonxvTuZ2pBdHmn; tfstk=cfHlBQ2rcbP7ZYFm1LwSbt3n6ivOwI9U5JPn3xC_uIKF_1fcis5azTs0f4DdO"));
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
        String orderDataStr = "{\"endpoint\":{\"mode\":\"pc\",\"osVersion\":\"PC\",\"protocolVersion\":\"3.0\",\"ultronage\":\"true\"},\"data\":{\"addressPC_1\":{\"fields\":{\"addrMakerUrl\":\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\",\"defaultAddressAPI\":\"/auction/update_address_selected_status.htm?_input_charset=utf-8\",\"disableCurrentSelected\":false,\"lat\":\"30.773273\",\"linkAddressId\":\"0\",\"lng\":\"104.125916\",\"managerAddressUrl\":\"//member1.taobao.com/member/fresh/deliver_address.htm\",\"optStr\":\"[{\\\"addrMakerUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=12198497492&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"addressDetail\\\":\\\"天河大道148号流溪苑\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"addressUrl\\\":{\\\"addAddressAPI\\\":\\\"/auction/add_buyer_address.htm?_input_charset=utf-8\\\",\\\"addressUrlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=12198497492&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"defaultAddressAPI\\\":\\\"/auction/update_address_selected_status.htm?_input_charset=utf-8\\\",\\\"managerAddressUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address.htm\\\",\\\"postCodeAPI\\\":\\\"/auction/json/get_postcode.do?_input_charset=utf-8\\\",\\\"updateAddressAPI\\\":\\\"/auction/update_buyer_address.htm?_input_charset=utf-8\\\",\\\"urlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\\\"},\\\"areaName\\\":\\\"新都\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":true,\\\"deliveryAddressId\\\":12198497492,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510114\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"2781106\\\",\\\"lat\\\":\\\"30.773273\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"104.125916\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510114003,\\\"townName\\\":\\\"三河\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addressDetail\\\":\\\"清溪雅筑13栋\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"青羊\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":8809318602,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510105\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"2781107\\\",\\\"lat\\\":\\\"30.667978\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"103.974563\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510105013,\\\"townName\\\":\\\"苏坡\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addressDetail\\\":\\\"四川省内江市东兴区百合镇农贸市场\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"东兴\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"内江\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":6471596766,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"511011\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"2781108\\\",\\\"lat\\\":\\\"29.686443\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"105.291406\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"641100\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":511011103,\\\"townName\\\":\\\"白合\\\",\\\"updateAddressTip\\\":\\\"\\\"}]\",\"options\":[{\"deliveryAddressId\":\"12198497492\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"成都\",\"addressDetail\":\"天河大道148号流溪苑\",\"needUpdate4Address\":false,\"divisionCode\":\"510114\",\"enforceUpdate4Address\":true,\"areaName\":\"新都\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"000000\",\"townDivisionId\":\"510114003\",\"defaultAddress\":true,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"三河\",\"enableMDZT\":false},{\"deliveryAddressId\":\"8809318602\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"成都\",\"addressDetail\":\"清溪雅筑13栋\",\"needUpdate4Address\":false,\"divisionCode\":\"510105\",\"enforceUpdate4Address\":true,\"areaName\":\"青羊\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"000000\",\"townDivisionId\":\"510105013\",\"defaultAddress\":false,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"苏坡\",\"enableMDZT\":false},{\"deliveryAddressId\":\"6471596766\",\"fullName\":\"王飞\",\"mobile\":\"13281835775\",\"cityName\":\"内江\",\"addressDetail\":\"四川省内江市东兴区百合镇农贸市场\",\"needUpdate4Address\":false,\"divisionCode\":\"511011\",\"enforceUpdate4Address\":true,\"areaName\":\"东兴\",\"storeAddress\":true,\"enableStation\":false,\"countryName\":\"\",\"provinceName\":\"四川\",\"postCode\":\"641100\",\"townDivisionId\":\"511011103\",\"defaultAddress\":false,\"stationId\":\"0\",\"lgShopId\":\"0\",\"editable\":true,\"townName\":\"白合\",\"enableMDZT\":false}],\"receiveMethodType\":\"normal\",\"selectedId\":\"12198497492\",\"sites\":\"[]\",\"tips\":{},\"title\":\"选择收货地址\",\"updateAddressAPI\":\"/auction/update_buyer_address.htm?_input_charset=utf-8\",\"useMDZT\":false,\"useStation\":false},\"id\":\"1\",\"ref\":\"0394592\",\"submit\":true,\"tag\":\"addressPC\",\"type\":\"native$null$address\"},\"agencyPayPC_1\":{\"fields\":{\"checked\":false,\"disabled\":false,\"label\":\"朋友代付\"},\"id\":\"1\",\"ref\":\"c287c09\",\"submit\":true,\"tag\":\"agencyPayPC\",\"type\":\"native$null$checkbox\"},\"anonymousPC_1\":{\"fields\":{\"checked\":true,\"disabled\":false,\"label\":\"匿名购买\"},\"id\":\"1\",\"ref\":\"907bd01\",\"submit\":true,\"tag\":\"anonymousPC\",\"type\":\"native$null$checkbox\"},\"confirmOrder_1\":{\"fields\":{},\"hidden\":{\"extensionMap\":{\"joinId\":\"6b5964c77863127064d2521299ce3a98\",\"pageType\":\"TMALL\",\"sid\":\"358d9c76f494e4b02643f93155b79921\",\"umidToken\":\"Tc317e0203244f006a304aa672667e953\"}},\"id\":\"1\",\"ref\":\"8318d7a\",\"submit\":true,\"tag\":\"confirmOrder\",\"type\":\"block$null$emptyBlock\"},\"deliveryMethodPC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"checked\":true,\"hasCod\":false,\"options\":[{\"serviceIcon\":\"//img.alicdn.com/tfs/TB1No7XbvxNTKJjy0FjXXX6yVXa-145-28.png\",\"fare\":\"6.00\",\"serviceType\":\"5\",\"message\":\"快递￥6.00\",\"datePicker\":{\"headTip\":\"不可抗力等因素可致延误 <a href=\\\"//service.tmall.com/support/tmall/knowledge-5811867.htm\\\">详情</a>\",\"periods\":[\"09:00-14:00\",\"09:00-21:00\",\"14:00-19:00\"],\"selectedPeriods\":\"09:00-14:00\",\"endDate\":\"2021-03-02\",\"payTimeTip\":\"请在23:59前付款\",\"useDefault\":false,\"enableCancel\":true,\"disable\":[\"0_0\",\"0_1\",\"0_2\"],\"beginDate\":\"2021-02-24\"},\"signText\":\"23:59前付款，承诺2月24日送达\",\"extra\":\"\",\"titleText\":\"普通配送\",\"hasOption\":false,\"appointPromotionTip\":\"\",\"id\":\"2_5_0\",\"userChooseLogisticsInfo\":\"{\\\"childServiceType\\\":5,\\\"cp\\\":\\\"default\\\",\\\"logisticsType\\\":0,\\\"serviceType\\\":5}\",\"fareCent\":\"600\"}],\"secondOption\":false,\"selectedId\":\"2_5_0\"},\"hidden\":{\"extensionMap\":{\"deliveryId\":\"b311c4f06b51839bdbf00a3d781a6952\",\"options\":\"[{\\\"datePicker\\\":{\\\"beginDate\\\":\\\"2021-02-24\\\",\\\"disable\\\":[\\\"0_0\\\",\\\"0_1\\\",\\\"0_2\\\"],\\\"enableCancel\\\":true,\\\"endDate\\\":\\\"2021-03-02\\\",\\\"headTip\\\":\\\"不可抗力等因素可致延误 <a href=\\\\\\\"//service.tmall.com/support/tmall/knowledge-5811867.htm\\\\\\\">详情</a>\\\",\\\"payTimeTip\\\":\\\"请在23:59前付款\\\",\\\"periods\\\":[\\\"09:00-14:00\\\",\\\"09:00-21:00\\\",\\\"14:00-19:00\\\"],\\\"selectedPeriods\\\":\\\"09:00-14:00\\\",\\\"useDefault\\\":false},\\\"fareCent\\\":600,\\\"id\\\":\\\"2_5_0\\\",\\\"serviceType\\\":\\\"5\\\"}]\"}},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"2312f91\",\"submit\":true,\"tag\":\"deliveryMethodPC\",\"type\":\"native$null$deliveryMethod\"},\"frontTracePC_1\":{\"fields\":{\"joinId\":\"2677155913760\"},\"id\":\"1\",\"ref\":\"e61fd52\",\"tag\":\"frontTracePC\",\"type\":\"native$null$frontTrace\"},\"invoicePC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"canModifyTitle\":true,\"category\":[{\"id\":\"0\",\"text\":\"电子发票\",\"type\":\"1\"}],\"checked\":false,\"defaultAddressName\":\"个人\",\"einvoice\":true,\"enotice\":\"1、发票金额不包括点券、积分或其他类似折扣券抵减的金额；<br>2、电子发票可以作为售后维权的有效凭证，财务报销规定请参照 <a href='//service.taobao.com/support/knowledge-6502401.htm'>帮助中心。</a>\",\"invoiceMust\":false,\"options\":[{\"id\":\"0\",\"text\":\"明细\"}],\"selectedId\":\"0_0\",\"taxNo\":\"\",\"title\":\"个人\",\"uses\":[{\"id\":\"0\",\"text\":\"个人\",\"type\":\"1\"},{\"id\":\"1\",\"text\":\"企业\",\"type\":\"2\"}],\"usesSelectedId\":\"0\",\"valueAddTax\":true},\"hidden\":{\"extensionMap\":{\"categorySize\":\"1\"}},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"7f3cd76\",\"submit\":true,\"tag\":\"invoicePC\",\"type\":\"native$null$invoice\"},\"itemInfoPC_6b5964c77863127064d2521299ce3a98\":{\"fields\":{\"icons\":[{\"title\":\"消费者保障服务，卖家承诺7天退换\",\"text\":\"\",\"image\":\"//img.alicdn.com/tps/i3/T1Vyl6FCBlXXaSQP_X-16-16.png\",\"link\":\"//pages.tmall.com/wow/seller/act/seven-day\",\"sortNo\":\"10\",\"iconType\":\"main\"},{\"title\":\"消费者保障服务，卖家承诺如实描述\",\"text\":\"\",\"image\":\"//img.alicdn.com/tps/i4/T1BCidFrNlXXaSQP_X-16-16.png\",\"link\":\"//www.taobao.com/go/act/315/xfzbz_rsms.php?ad_id=&am_id=130011830696bce9eda3&cm_id=&pm_id=\",\"sortNo\":\"11\",\"iconType\":\"main\"},{\"title\":\"支持信用卡支付\",\"text\":\"\",\"image\":\"//assets.alicdn.com/sys/common/icon/trade/xcard.png\",\"iconType\":\"main\"}],\"isGift\":false,\"itemInfoId\":\"582408570328\",\"itemUrl\":\"//detail.tmall.com/item.htm?id=582408570328\",\"pic\":\"//img.alicdn.com/imgextra/i1/725677994/O1CN01G9VbQ128vIcySKQYw_!!725677994.jpg\",\"price\":\"19.00\",\"priceInfo\":{\"valueStyles\":{\"color\":\"#FF0036\"},\"bottomText\":\"66g\",\"value\":\"19.00\"},\"promotionList\":{\"bottomText\":\"\",\"selectedId\":\"\"},\"quantity\":{\"min\":\"1\",\"quantity\":\"1\",\"title\":\"购买数量\",\"step\":\"1\",\"editable\":false,\"max\":\"35\"},\"sellerNick\":\"天猫超市\",\"shopUrl\":\"//store.taobao.com/shop/view_shop.htm?shop_id=67597230\",\"skuId\":\"3902332244990\",\"skuInfo\":[{\"name\":\"颜色分类\",\"forOld\":false,\"value\":\"橘色\"}],\"subtitles\":[],\"title\":\"最生活小米毛巾方巾青春系列纯棉全棉家居吸水擦手巾洗脸1条装\"},\"hidden\":{\"extensionMap\":{\"orderOutId\":\"\",\"outId\":\"\",\"promotionType\":\"\",\"villagerOrderId\":\"0\"}},\"id\":\"6b5964c77863127064d2521299ce3a98\",\"ref\":\"1035de9\",\"submit\":true,\"tag\":\"itemInfoPC\",\"type\":\"native$null$itemRow\"},\"item_6b5964c77863127064d2521299ce3a98\":{\"fields\":{\"bgColor\":\"#fbfcff\",\"borderBottom\":\"1px dotted #ddd\",\"direction\":\"column\"},\"hidden\":{\"extensionMap\":{\"bizCode\":\"ali.china.tmall.supermarket\",\"cartId\":\"2760787450304\",\"itemId\":\"582408570328\",\"shoppingOrderId\":\"0\",\"skuId\":\"3902332244990\",\"valid\":\"true\",\"villagerId\":\"0\"}},\"id\":\"6b5964c77863127064d2521299ce3a98\",\"ref\":\"b72dcaa\",\"submit\":true,\"tag\":\"item\",\"type\":\"native$null$flex\"},\"menberBenefitsFlex_1\":{\"fields\":{\"direction\":\"column\",\"subDirection\":\"end\"},\"id\":\"1\",\"ref\":\"656e6af\",\"tag\":\"menberBenefitsFlex\",\"type\":\"native$null$flex\"},\"ncCheckCode_ncCheckCode1\":{\"fields\":{\"nc\":\"1\",\"token\":\"3598728318b88b7e54e0a538a3e09de0777a55c1\"},\"id\":\"ncCheckCode1\",\"ref\":\"fc57d42\",\"submit\":true,\"tag\":\"ncCheckCode\",\"type\":\"native$null$ncCheckCode\"},\"orderDesc_orderDesc_1\":{\"fields\":{\"headers\":[\"店铺宝贝\",\"商品属性\",\"单价\",\"数量\",\"优惠方式\",\"小计\"],\"title\":\"确认订单信息\"},\"id\":\"orderDesc_1\",\"ref\":\"622cdf6\",\"tag\":\"orderDesc\",\"type\":\"native$null$itemHeader\"},\"orderExtLeftPC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"direction\":\"column\",\"gap\":\"10\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"start\"},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"6f00335\",\"tag\":\"orderExtLeftPC\",\"type\":\"native$null$flex\"},\"orderExtPC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"bgColor\":\"#f2f7ff\",\"borderBottom\":\"1px dotted #80b2ff\",\"direction\":\"column\",\"gridBorderColor\":\"#fff\"},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"98a96fa\",\"tag\":\"orderExtPC\",\"type\":\"native$null$flex\"},\"orderExtRightPC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"direction\":\"column\",\"gap\":\"10\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"start\"},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"1dbade8\",\"tag\":\"orderExtRightPC\",\"type\":\"native$null$flex\"},\"orderExtUpperPC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"direction\":\"row\",\"equality\":\"2\",\"gridBorderColor\":\"#fff\",\"mainDirection\":\"spaceBetween\"},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"dd37e71\",\"tag\":\"orderExtUpperPC\",\"type\":\"native$null$flex\"},\"orderPayLayoutPC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"direction\":\"column\",\"mainDirection\":\"end\",\"padding\":\"10px 0\",\"subDirection\":\"end\"},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"0315aad\",\"tag\":\"orderPayLayoutPC\",\"type\":\"native$null$flex\"},\"orderPayPC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"header\":\"店铺合计(含运费)\",\"value\":\"￥25.00\",\"valueStyles\":{\"color\":\"#FF0036\",\"bold\":true}},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"35419b9\",\"tag\":\"orderPayPC\",\"type\":\"native$null$label\"},\"order_b311c4f06b51839bdbf00a3d781a6952\":{\"cardGroup\":\"true\",\"fields\":{},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"a9dc6bd\",\"tag\":\"order\",\"type\":\"block$null$emptyBlock\"},\"postageInsurancePC_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"amount\":\"0\",\"checkBoxOptions\":[{\"bizType\":\"1\",\"request\":true,\"tips\":\"退换货可赔付11元\",\"url\":\"https://render.alipay.com/p/h5/insscene/www/insureProtocol.html?1=1&buyerId=2453833782&sellerId=725677994&serviceId=1064\",\"viewPrice\":\"0.00\",\"storeId\":\"0\",\"useGroup\":\"yfx\",\"readonly\":false,\"text\":\"￥0.25购买\",\"checked\":false,\"price\":\"25\",\"value\":\"0.25\",\"serviceId\":\"1064\",\"supportOtherAddrees\":false}],\"checked\":false,\"choicePrice\":\"0.00\",\"groupTitle\":\"运费险\",\"outId\":\"b311c4f06b51839bdbf00a3d781a6952\",\"required\":false,\"supportOtherAddress\":false,\"vertical\":false},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"28becd6\",\"submit\":true,\"tag\":\"postageInsurancePC\",\"type\":\"native$null$postageInsurance\"},\"presellDescGroupPC_presellDescGroup_presellDescGroupb311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{},\"id\":\"presellDescGroup_presellDescGroupb311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"c315959\",\"tag\":\"presellDescGroupPC\",\"type\":\"native$null$descriptionGroup\"},\"realPayPC_1\":{\"fields\":{\"addressText\":\" 四川 成都 新都 三河 天河大道148号流溪苑\",\"fullName\":\"王飞\",\"mallTotalPrice\":\"2500\",\"microPurchaseTotalPrice\":\"0\",\"mobile\":\"13281835775\",\"needUpdate4Address\":false,\"originPrice\":\"25.00\",\"price\":\"25.00\",\"quantity\":\"1\",\"receiveMethodType\":\"normal\",\"selectedId\":\"12198497492\",\"tmallHkTotalPrice\":\"0\",\"weight\":\"66\"},\"hidden\":{\"extensionMap\":{\"timestamp\":\"1614072261764\"}},\"id\":\"1\",\"ref\":\"c601752\",\"tag\":\"realPayPC\",\"type\":\"native$null$realPay\"},\"riderClauseContentFlex_riderClause_content_1\":{\"fields\":{\"direction\":\"row\",\"gap\":\"30\",\"mainDirection\":\"end\"},\"id\":\"riderClause_content_1\",\"ref\":\"510e227\",\"tag\":\"riderClauseContentFlex\",\"type\":\"native$null$flex\"},\"riderClausePC_riderClause_1\":{\"fields\":{\"borderBottom\":\"1px dotted #ddd\",\"direction\":\"column\",\"mainDirection\":\"end\",\"padding\":\"0\",\"subDirection\":\"end\"},\"id\":\"riderClause_1\",\"ref\":\"4b36852\",\"tag\":\"riderClausePC\",\"type\":\"native$null$flex\"},\"seller_b311c4f06b51839bdbf00a3d781a6952\":{\"fields\":{\"icon\":\"//img.alicdn.com/tfs/TB1wIDCJhTpK1RjSZFKXXa2wXXa-180-148.png\",\"isShowWangWang\":true,\"sellerUrl\":\"//member1.taobao.com/member/user_profile.jhtml?encUserNumId=IDX1Gy44vOsp1eq6vyFcwZ01shNdtAv8NFUUNMrf_6dNhyikxySTKLwICaYsYW8Yr2MG&sign=a2f9df832998d9dbf71becb38f1ca990\",\"shopLabel\":\"店铺\",\"shopName\":\"天猫超市\",\"shopUrl\":\"//store.taobao.com/shop/view_shop.htm?shop_id=67597230\"},\"id\":\"b311c4f06b51839bdbf00a3d781a6952\",\"ref\":\"7b734da\",\"tag\":\"seller\",\"type\":\"native$null$seller\"},\"stationAgencyService_1\":{\"fields\":{\"agencyId\":\"0\",\"agencyReceive\":\"1\",\"disable\":false,\"fullName\":\"王飞\",\"hasService\":true,\"helpLink\":\"//www.cainiao.com/markets/cnwww/userhelpdoc\",\"linkAddressId\":\"12198497492\",\"linkDivisionCode\":\"510114\",\"linkTownDivisionCode\":\"510114003\",\"mobile\":\"13281835775\",\"tip\":\"免费\",\"title\":\"菜鸟驿站代收服务\",\"useAgencyType\":\"0\"},\"id\":\"1\",\"ref\":\"7057e18\",\"submit\":true,\"tag\":\"stationAgencyService\",\"type\":\"native$null$stationAgencyService\"},\"stationTabPC_1\":{\"fields\":{},\"id\":\"1\",\"ref\":\"2ac3f2e\",\"tag\":\"stationTabPC\",\"type\":\"native$null$tab\"},\"stepbarPC_1\":{\"fields\":{\"current\":\"1\",\"isShowVillager\":false,\"options\":[\"查看购物车\",\"拍下商品\",\"付款到支付宝\",\"确认收货\",\"评价\"],\"stepBarType\":\"MALL_CART\"},\"id\":\"1\",\"ref\":\"e2d54b5\",\"tag\":\"stepbarPC\",\"type\":\"native$null$stepbar\"},\"submitOrderPC_1\":{\"fields\":{\"cartText\":\"返回购物车\",\"cartUrl\":\"//cart.taobao.com/cart.htm\",\"fromCart\":true,\"fromMallYaoCart\":false,\"isTmallHKPresellOrder\":false,\"isTmallHKPresellSelf\":false,\"needUpdate4Address\":false,\"priceTips\":\"\",\"showPrice\":\"25.00\",\"submitOrderType\":\"UNITY\",\"submitTitle\":\"提交订单\",\"tmallPointStatus\":\"0\"},\"hidden\":{\"extensionMap\":{\"action\":\"/order/multiTerminalSubmitOrderAction\",\"event_submit_do_confirm\":\"1\",\"input_charset\":\"utf-8\",\"pcSubmitUrl\":\"/auction/confirm_order.htm?x-itemid=582408570328&x-uid=2453833782\",\"secretKey\":\"submitref\",\"secretValue\":\"331ff2c431c52a5aa625d76e77b8b0c8c3d86573cf9a931d3aeb4e72c98b5226\",\"sparam1\":\"eyJsaXN0Ijp7IjM2MzczMCI6eyJidXlhbW91bnQiOjEsImRpc2NvdW50VG90YWxGZWUiOjI1MDAsInNlbGxlcklkIjo3MjU2Nzc5OTQsInRvdGFsRmVlIjoyNTAwfX0sImxvZ2lzUmVjdkFkZHIiOiLlpKnmsrPlpKfpgZMxNDjlj7fmtYHmuqroi5EiLCJsb2dpc1JlY3ZNb2JsIjoiMTMyODE4MzU3NzUiLCJvcmRlcmZyb213aGVyZSI6InRtYWxsX3BjIn0=\",\"unitSuffix\":\"rz41\"}},\"id\":\"1\",\"ref\":\"35c4cbb\",\"submit\":true,\"tag\":\"submitOrderPC\",\"type\":\"native$null$submitOrder\"},\"urlTransferPC_1\":{\"fields\":{},\"id\":\"1\",\"ref\":\"65a4400\",\"tag\":\"urlTransferPC\",\"type\":\"native$null$urlTransfer\"}},\"linkage\":{\"common\":{\"compress\":true,\"queryParams\":\"^^$$Z275b79765cdb23e2ce70f2f158c4e6106|null{$_$}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\",\"submitParams\":\"^^$$Z21cb0d92365e8f4bd0ee21ad2d488f378|null{$_$}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\",\"validateParams\":\"^^$$131075ebadcb6cea6d4691195db295a5{$_$}H4sIAAAAAAAAAIVQy0oDQRD8lzmHIbuSzW5um90IQgwLxoAn6cx0kiXzYmaCxpA/8DO8iN8l/oa9BjEexFtXU11VXQe28qDxwfptAzSFK+0UGx2YUxBX1uv53mGHhYIQZkRlIyas5qDaJSyBRw8SuVAtmsi1lah4sDsvkDdnCouU9Zg5XTcVzdbL1gA5Jccec7DGv3wi2CVYTpbOms5D7kmnFVxjhLN1R1D4yMe3dylvvgVP2hQ5alCKfCUGQfD95fXj+Y2wBr/FSJuffPPrcjr9FTGljMoKUN3zT5v7asZoswvoyxDatfm/n7ABj5I7j4F+gNhaw13XN68hwoLKlF/1n3pClNUGxbaiOtloBSpgj6ERfu/iTfStWVOOYjgs07Kf5tWkyopBf3yZjrN+nV3kSV4kZV0nw0E+KYoqzcbEKdnx+AlqQeGf7QEAAA==\"},\"input\":[\"submitOrderPC_1\",\"invoicePC_b311c4f06b51839bdbf00a3d781a6952\"],\"request\":[\"postageInsurancePC_b311c4f06b51839bdbf00a3d781a6952\",\"itemInfoPC_6b5964c77863127064d2521299ce3a98\",\"addressPC_1\",\"submitOrderPC_1\",\"stationAgencyService_1\",\"agencyPayPC_1\",\"deliveryMethodPC_b311c4f06b51839bdbf00a3d781a6952\"],\"signature\":\"f8cf48319db9dc6c7db0492aaf47a3bb\",\"url\":\"/auction/json/async_linkage.do?_input_charset=utf-8\"},\"hierarchy\":{\"baseType\":[\"native$null$descriptionGroup\",\"native$null$label\",\"native$null$itemRow\",\"native$null$stepbar\",\"block$null$emptyBlock\",\"native$null$flex\",\"native$null$frontTrace\",\"native$null$urlTransfer\",\"native$null$seller\",\"native$null$invoice\",\"native$null$postageInsurance\",\"native$null$address\",\"native$null$ncCheckCode\",\"native$null$stationAgencyService\",\"native$null$itemHeader\",\"native$null$realPay\",\"native$null$submitOrder\",\"native$null$deliveryMethod\",\"native$null$tab\",\"native$null$checkbox\"],\"component\":[\"seller\",\"stationTabPC\",\"riderClausePC\",\"addressPC\",\"frontTracePC\",\"stationAgencyService\",\"orderExtPC\",\"postageInsurancePC\",\"orderExtLeftPC\",\"orderPayPC\",\"riderClauseContentFlex\",\"deliveryMethodPC\",\"agencyPayPC\",\"itemInfoPC\",\"orderPayLayoutPC\",\"orderDesc\",\"order\",\"item\",\"presellDescGroupPC\",\"menberBenefitsFlex\",\"anonymousPC\",\"orderExtRightPC\",\"realPayPC\",\"confirmOrder\",\"invoicePC\",\"submitOrderPC\",\"stepbarPC\",\"ncCheckCode\",\"orderExtUpperPC\",\"urlTransferPC\"],\"root\":\"confirmOrder_1\",\"structure\":{\"confirmOrder_1\":[\"stepbarPC_1\",\"addressPC_1\",\"stationTabPC_1\",\"orderDesc_orderDesc_1\",\"order_b311c4f06b51839bdbf00a3d781a6952\",\"menberBenefitsFlex_1\",\"riderClausePC_riderClause_1\",\"realPayPC_1\",\"submitOrderPC_1\",\"frontTracePC_1\",\"urlTransferPC_1\",\"ncCheckCode_ncCheckCode1\"],\"item_6b5964c77863127064d2521299ce3a98\":[\"itemInfoPC_6b5964c77863127064d2521299ce3a98\"],\"orderExtLeftPC_b311c4f06b51839bdbf00a3d781a6952\":[\"invoicePC_b311c4f06b51839bdbf00a3d781a6952\"],\"orderExtPC_b311c4f06b51839bdbf00a3d781a6952\":[\"orderExtUpperPC_b311c4f06b51839bdbf00a3d781a6952\",\"orderPayLayoutPC_b311c4f06b51839bdbf00a3d781a6952\"],\"orderExtRightPC_b311c4f06b51839bdbf00a3d781a6952\":[\"deliveryMethodPC_b311c4f06b51839bdbf00a3d781a6952\",\"postageInsurancePC_b311c4f06b51839bdbf00a3d781a6952\"],\"orderExtUpperPC_b311c4f06b51839bdbf00a3d781a6952\":[\"orderExtLeftPC_b311c4f06b51839bdbf00a3d781a6952\",\"orderExtRightPC_b311c4f06b51839bdbf00a3d781a6952\"],\"orderPayLayoutPC_b311c4f06b51839bdbf00a3d781a6952\":[\"orderPayPC_b311c4f06b51839bdbf00a3d781a6952\",\"presellDescGroupPC_presellDescGroup_presellDescGroupb311c4f06b51839bdbf00a3d781a6952\"],\"order_b311c4f06b51839bdbf00a3d781a6952\":[\"seller_b311c4f06b51839bdbf00a3d781a6952\",\"item_6b5964c77863127064d2521299ce3a98\",\"orderExtPC_b311c4f06b51839bdbf00a3d781a6952\"],\"riderClauseContentFlex_riderClause_content_1\":[\"agencyPayPC_1\",\"anonymousPC_1\"],\"riderClausePC_riderClause_1\":[\"riderClauseContentFlex_riderClause_content_1\"],\"stationTabPC_1\":[\"stationAgencyService_1\"]}},\"container\":{\"data\":[]},\"reload\":true}";
//        String orderDataStr = BuyService.getOrderData();

        // 从选择地址接口的参数中获取 下订单接口的 url 参数
        Map<String, Object> orderDataMap = JSONObject.toJavaObject(JSONObject.parseObject(orderDataStr), Map.class);

        Object obj = orderDataMap.get("linkage");
        System.out.println(obj.toString());
        Map aaa = (Map)obj;


        Map<String, Object> dataMap = (Map<String, Object>)orderDataMap.get("data");
        Map<String, Object> submitOrderPC_1 = (Map<String, Object>)dataMap.get("submitOrderPC_1");
        Map<String, Object> hidden = (Map<String, Object>)submitOrderPC_1.get("hidden");
        Map<String, Object> extensionMap = (Map<String, Object>)hidden.get("extensionMap");
        String secretValue = extensionMap.get("secretValue").toString();
        String sparam1 = extensionMap.get("sparam1").toString();

        url += secretValue;
        url += "&sparam1=" + sparam1;

        // 清楚掉多余的数据
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
            }
        }
        for(String key : removeKeys){
            data.remove(key);
        }
    }


}

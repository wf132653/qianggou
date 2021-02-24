package com.wf.qianggou.util.http;

import com.alibaba.fastjson.JSONObject;
import com.wf.qianggou.util.HttpClientService;
import com.wf.qianggou.util.SSLClient;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下订单页面-选择地址接口
 *
 * @author wf
 * @date 2021年02月22日 10:19
 */
public class ChooseAddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);
    /**
     * 返回成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 发送GET请求
     *
     * @param url     请求url
     * @param isHttps 是否是 https 请求
     * @return JSON或者字符串
     * @throws Exception 异常
     */
    public static String sendGet(String url, boolean isHttps) throws Exception {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // http 和 https
        CloseableHttpClient client;
        if (isHttps) {
            client = new SSLClient();
        } else {
            client = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // taobao 登录的 cookie
            httpGet.addHeader(new BasicHeader("Cookie", "cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _bl_uid=Cpkkvl7OhhzahIm9Uzts580q5yXO; sm4=510100; _m_h5_tk=f408ae7cdb0a1b6a04dae73acfc8d805_1614050863176; _m_h5_tk_enc=a970bd8a4855f8e2cbde5648290f79a8; xlly_s=1; uc1=cookie16=U%2BGCWk%2F74Mx5tgzv3dWpnhjPaQ%3D%3D&cart_m=0&cookie14=Uoe1hgbVDJzhHg%3D%3D&pas=0&cookie21=U%2BGCWk%2F7p4mBoUyS4plD&cookie15=V32FPkk%2Fw0dUvg%3D%3D&existShop=true; uc3=lg2=VFC%2FuZ9ayeYq2g%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&vt3=F8dCuASh6IKULyrTAXE%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D; _l_g_=Ug%3D%3D; uc4=nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMCCnuEhj&id4=0%40U27KCxnkg%2BYg1C7JnsLDOKTgtvEM; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; login=true; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100ya%2BcAgZc%2BKoa41XSWHGL1MmSNaQR%2FRroFrL30CaQaSpkCuo7dB6pUMUcE23GSPYDKk7FQGzidL6Lz4R3jmGGpA%3D%3D; sg=424; csg=b805cfe9; l=eBP-ZQ7PO6NKKD6FBO5anurza77OVIRbzsPzaNbMiInca6dhTFi0ONCIyW8H7dtjgtfxXetyGmkaaRUMSkU38x6CKvtgjqf1zSp6-; tfstk=cv3OBd4klpvg_HVKacKhhQ5ld-3OaWXYKCNCkQYAR_F5XOXAhsYnr4LVzGwhBydd.; isg=BBgYsuzfzM_0J9_X3EZVWGP96UaqAXyLYoapOlIJy9MG7brX-hJIGiSLJSVdfTRj"));
            httpGet.addHeader(new BasicHeader("Referer", "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined"));
            httpGet.addHeader(new BasicHeader("Origin", "https://buy.tmall.com"));
            httpGet.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
            httpGet.addHeader(new BasicHeader(":path", "/auction/json/async_linkage.do?_input_charset=utf-8"));
            httpGet.addHeader(new BasicHeader(":authority", "buy.tmall.com"));
            httpGet.addHeader(new BasicHeader(":method", "POST"));
            httpGet.addHeader(new BasicHeader(":scheme", "https"));
            httpGet.addHeader(new BasicHeader("accept", "application/json, text/javascript, */*; q=0.01"));
            httpGet.addHeader(new BasicHeader("accept-encoding", "gzip, deflate, br"));
            httpGet.addHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
            httpGet.addHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36"));
            httpGet.addHeader(new BasicHeader("x-requested-with", "XMLHttpRequest"));
            // 执行请求
            response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS_CODE == statusCode) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            } else {
                LOGGER.error("HttpClientService-line: {}, errorMsg{}", 97, "GET请求失败！");
            }
        } catch (Exception e) {
            LOGGER.error("HttpClientService-line: {}, Exception: {}", 100, e);
        } finally {
            if (response != null) {
                response.close();
            }
            client.close();
        }
        return null;
    }


    /**
     * 发送 https 的 POST请求
     *
     * @param url               url 地址
     * @param nameValuePairList
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPost(String url, List<NameValuePair> nameValuePairList) throws Exception {


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
             * 包装成一个Entity对象
             */
            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求的报文头部
             */
//            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
//            post.addHeader(new BasicHeader("Cookie", "cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _m_h5_tk=fec3a6d5b3784dcf6378583603a69857_1613919958057; _m_h5_tk_enc=a042d808838ac0656aeee279e1084cdc; xlly_s=1; login=true; uc1=cookie15=V32FPkk%2Fw0dUvg%3D%3D&cookie21=UtASsssmeW6lpyd%2BBROh&cookie14=Uoe1gW4o7ht7LA%3D%3D&pas=0&cookie16=UtASsssmPlP%2Ff1IHDsDaPRu%2BPw%3D%3D&cart_m=0&existShop=true; uc3=vt3=F8dCuASiek0qC45jnk8%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLE5%2FTr9KO0&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSEZeQTXnch; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100Bs1xaiIgmi%2BfsSe95re%2Bv1bEmYmPPnsBGubTJ7zAE5Ay%2BrsiF%2BR1k6FWNTsHm%2B%2FE7Fv1IGDzgyA6Is8fxoE6QQ%3D%3D; sg=424; csg=1de83e78; sm4=510114; l=eBP-ZQ7PO6NKKWWjBO5B-urza779XQdXflVzaNbMiInca6wRHF1obNCIom2H2dtjgtfx2eKyGmkaaR3XWb4LRx6CKvtgjqf1zV965; tfstk=crt5BNfZ97V7HeuU4LM44nbKIKQfaBM1Kz66FUSMCDtaBROhMsX3b1iXFwVUDNBf.; isg=BLq6zuiKjgbwjT1xKpzXpp0HC-Dcaz5FDAxL3MSwlM0Yt1XxrflAVWGBB0NrHLbd"));
//            post.addHeader(new BasicHeader("Cookie", "cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100Bs1xaiIgmi%2BfsSe95re%2Bv1bEmYmPPnsBGubTJ7zAE5Ay%2BrsiF%2BR1k6FWNTsHm%2B%2FE7Fv1IGDzgyA6Is8fxoE6QQ%3D%3D; sg=424; csg=1de83e78; sm4=510114; l=eBP-ZQ7PO6NKKWWjBO5B-urza779XQdXflVzaNbMiInca6wRHF1obNCIom2H2dtjgtfx2eKyGmkaaR3XWb4LRx6CKvtgjqf1zV965"));
//            post.addHeader(new BasicHeader("Referer", "https://buy.tmall.com/order/confirm_order.htm?spm=a1z0d.6639537.0.0.undefined"));
//            post.addHeader(new BasicHeader("Origin", "https://buy.tmall.com"));
//            post.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
//            post.addHeader(new BasicHeader(":path", "/auction/json/async_linkage.do?_input_charset=utf-8"));
//            post.addHeader(new BasicHeader(":authority", "buy.tmall.com"));
//            post.addHeader(new BasicHeader(":method", "POST"));
//            post.addHeader(new BasicHeader(":scheme", "https"));
//            post.addHeader(new BasicHeader("accept", "application/json, text/javascript, */*; q=0.01"));
//            post.addHeader(new BasicHeader("accept-encoding", "gzip, deflate, br"));
//            post.addHeader(new BasicHeader("accept-language", "zh-CN,zh;q=0.9"));
//            post.addHeader(new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36"));
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
                try {
                    jsonObject = JSONObject.parseObject(result);
                    return jsonObject;
                } catch (Exception e) {
                    e.printStackTrace();
                    return result;
                }
            } else {
                LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 146, "POST请求失败！");
            }
        } catch (Exception e) {
            LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
        } finally {
            response.close();
            client.close();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        sendChooseAddress(null, null);


    }

    public static Map sendChooseAddress(String dataV, String hierarchyV) throws Exception{
        String url = "https://buy.tmall.com/auction/json/async_linkage.do?_input_charset=utf-8";
        BasicNameValuePair endpoint = new BasicNameValuePair("endpoint", "{\"features\":\"0\",\"mode\":\"pc\",\"osVersion\":\"PC\",\"protocolVersion\":\"3.0\",\"ultronage\":\"true\"}");
        BasicNameValuePair operator = new BasicNameValuePair("endpoint", "addressPC_1");
        BasicNameValuePair linkage = new BasicNameValuePair("linkage", "{\"common\":{\"compress\":true,\"queryParams\":\"^^$$Z2f2ab91def31ab8d4be322e7e308ceaee|null{$_$}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\",\"validateParams\":\"^^$$cb910ce249cd211c9ae7b62d64b07eea{$_$}H4sIAAAAAAAAAIVQy04CQRD8lzmTybKwwHJbliUxQUIikngyzUwDG+aVmSGKhD/wM7wYv8v4G/ZKjHgw3roq3VXVdWRrDxofrN/NgaZwpZ1iwyNzCuLaer04OGywUBDCjFbZkAmrOah6BSvg0YNELlSNJnJtJSoe7N4L5PMLhWXKWsycr+clzdbL2gA5tU8t5mCDf/lEsCuwnCydNY2HPJBOLbjGCBd0s6DwkY9u71I+/xY8a1PkqEEp8pUYBMH3l9eP5zfCGvwOIzE/+RbXxXT6K2JKGZUVoJrnn7b35YwRsw/oixDqjfm/n7AFj5I7j4F+gFhbw13TNx9DhCWVKb/qP/eEKMstil1JdbLhGlTAFkMj/MHFm+hrs6Eceb9fpEWSDsqq7OVZkubZKOm1s0mV5XnazduTftGtup0iqQbjzihhp9Mnmnk+oe0BAAA=\"},\"signature\":\"9ad9128b8a4c316f96098bb6fecd1f49\"}");
        BasicNameValuePair lifecycle = new BasicNameValuePair("lifecycle", "null");

        if(dataV == null){
            dataV = "{\"addressPC_1\":{\"fields\":{\"addrMakerUrl\":\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\",\"defaultAddressAPI\":\"/auction/update_address_selected_status.htm?_input_charset=utf-8\",\"disableCurrentSelected\":false,\"lat\":\"30.667978\",\"linkAddressId\":\"0\",\"lng\":\"103.974563\",\"managerAddressUrl\":\"//member1.taobao.com/member/fresh/deliver_address.htm\",\"optStr\":\"[{\\\"addressDetail\\\":\\\"天河大道148号流溪苑\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"新都\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":true,\\\"deliveryAddressId\\\":12198497492,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510114\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"7165589\\\",\\\"lat\\\":\\\"30.773273\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"104.125916\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510114003,\\\"townName\\\":\\\"三河\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addrMakerUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=8809318602&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"addressDetail\\\":\\\"清溪雅筑13栋\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"addressUrl\\\":{\\\"addAddressAPI\\\":\\\"/auction/add_buyer_address.htm?_input_charset=utf-8\\\",\\\"addressUrlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&id=8809318602&version=1.0.10&sign_type=TEP&tid=2453833782\\\",\\\"defaultAddressAPI\\\":\\\"/auction/update_address_selected_status.htm?_input_charset=utf-8\\\",\\\"managerAddressUrl\\\":\\\"//member1.taobao.com/member/fresh/deliver_address.htm\\\",\\\"postCodeAPI\\\":\\\"/auction/json/get_postcode.do?_input_charset=utf-8\\\",\\\"updateAddressAPI\\\":\\\"/auction/update_buyer_address.htm?_input_charset=utf-8\\\",\\\"urlCommon\\\":\\\"//member1.taobao.com/member/fresh/deliver_address_frame.htm?sign=_a2_wr_qwv6w_f_rd_zu8cp_i_w_zj9_x_jms_k_g_z_y7_z_d_l0v_p_ec_v_ai_i%252F0_dd0_j_g_u4v_a%253D%253D&from=tmall&reurl=%2F%2Fbuy.tmall.com%2Forder%2FaddressProxy.htm&version=1.0.10&sign_type=TEP&tid=2453833782\\\"},\\\"areaName\\\":\\\"青羊\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"成都\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":8809318602,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"510105\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"7165590\\\",\\\"lat\\\":\\\"30.667978\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"103.974563\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"000000\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":510105013,\\\"townName\\\":\\\"苏坡\\\",\\\"updateAddressTip\\\":\\\"\\\"},{\\\"addressDetail\\\":\\\"四川省内江市东兴区百合镇农贸市场\\\",\\\"addressIconUrl\\\":\\\"\\\",\\\"areaName\\\":\\\"东兴\\\",\\\"attributes\\\":[],\\\"checked\\\":false,\\\"cityName\\\":\\\"内江\\\",\\\"consolidationGuide\\\":false,\\\"countryName\\\":\\\"\\\",\\\"defaultAddress\\\":false,\\\"deliveryAddressId\\\":6471596766,\\\"disable\\\":false,\\\"divisionCode\\\":\\\"511011\\\",\\\"editable\\\":true,\\\"enableMDZT\\\":false,\\\"enableStation\\\":false,\\\"enforceUpdate4Address\\\":true,\\\"fullName\\\":\\\"王飞\\\",\\\"hidden\\\":false,\\\"id\\\":\\\"7165591\\\",\\\"lat\\\":\\\"29.686443\\\",\\\"lgShopId\\\":0,\\\"lng\\\":\\\"105.291406\\\",\\\"mainland\\\":true,\\\"mobile\\\":\\\"13281835775\\\",\\\"name\\\":\\\"\\\",\\\"needUpdate4Address\\\":false,\\\"needUpgradeAddress\\\":false,\\\"platformType\\\":\\\"PC\\\",\\\"postCode\\\":\\\"641100\\\",\\\"provinceName\\\":\\\"四川\\\",\\\"stationId\\\":0,\\\"status\\\":\\\"normal\\\",\\\"storeAddress\\\":true,\\\"townDivisionId\\\":511011103,\\\"townName\\\":\\\"白合\\\",\\\"updateAddressTip\\\":\\\"\\\"}]\",\"options\":[{\"addressDetail\":\"天河大道148号流溪苑\",\"areaName\":\"新都\",\"cityName\":\"成都\",\"countryName\":\"\",\"defaultAddress\":true,\"deliveryAddressId\":\"12198497492\",\"divisionCode\":\"510114\",\"editable\":true,\"enableMDZT\":false,\"enableStation\":false,\"enforceUpdate4Address\":true,\"fullName\":\"王飞\",\"lgShopId\":\"0\",\"mobile\":\"13281835775\",\"needUpdate4Address\":false,\"postCode\":\"000000\",\"provinceName\":\"四川\",\"stationId\":\"0\",\"storeAddress\":true,\"townDivisionId\":\"510114003\",\"townName\":\"三河\"},{\"addressDetail\":\"清溪雅筑13栋\",\"areaName\":\"青羊\",\"cityName\":\"成都\",\"countryName\":\"\",\"defaultAddress\":false,\"deliveryAddressId\":\"8809318602\",\"divisionCode\":\"510105\",\"editable\":true,\"enableMDZT\":false,\"enableStation\":false,\"enforceUpdate4Address\":true,\"fullName\":\"王飞\",\"lgShopId\":\"0\",\"mobile\":\"13281835775\",\"needUpdate4Address\":false,\"postCode\":\"000000\",\"provinceName\":\"四川\",\"stationId\":\"0\",\"storeAddress\":true,\"townDivisionId\":\"510105013\",\"townName\":\"苏坡\"},{\"addressDetail\":\"四川省内江市东兴区百合镇农贸市场\",\"areaName\":\"东兴\",\"cityName\":\"内江\",\"countryName\":\"\",\"defaultAddress\":false,\"deliveryAddressId\":\"6471596766\",\"divisionCode\":\"511011\",\"editable\":true,\"enableMDZT\":false,\"enableStation\":false,\"enforceUpdate4Address\":true,\"fullName\":\"王飞\",\"lgShopId\":\"0\",\"mobile\":\"13281835775\",\"needUpdate4Address\":false,\"postCode\":\"641100\",\"provinceName\":\"四川\",\"stationId\":\"0\",\"storeAddress\":true,\"townDivisionId\":\"511011103\",\"townName\":\"白合\"}],\"receiveMethodType\":\"normal\",\"selectedId\":\"12198497492\",\"sites\":\"[]\",\"tips\":{},\"title\":\"选择收货地址\",\"updateAddressAPI\":\"/auction/update_buyer_address.htm?_input_charset=utf-8\",\"useMDZT\":false,\"useStation\":false},\"id\":\"1\",\"ref\":\"0394592\",\"submit\":true,\"tag\":\"addressPC\",\"type\":\"native$null$address\"},\"invoicePC_6eca7cbc8c666a4ace665d02ae5bde3c\":{\"fields\":{\"canModifyTitle\":true,\"category\":[{\"id\":\"0\",\"text\":\"电子发票\",\"type\":\"1\"}],\"checked\":false,\"defaultAddressName\":\"个人\",\"einvoice\":true,\"enotice\":\"1、发票金额不包括点券、积分或其他类似折扣券抵减的金额；<br>2、电子发票可以作为售后维权的有效凭证，财务报销规定请参照 <a href='//service.taobao.com/support/knowledge-6502401.htm'>帮助中心。</a>\",\"invoiceMust\":false,\"options\":[{\"id\":\"0\",\"text\":\"明细\"}],\"selectedId\":\"0_0\",\"taxNo\":\"\",\"title\":\"个人\",\"uses\":[{\"id\":\"0\",\"text\":\"个人\",\"type\":\"1\"},{\"id\":\"1\",\"text\":\"企业\",\"type\":\"2\"}],\"usesSelectedId\":\"0\",\"valueAddTax\":true},\"hidden\":{\"extensionMap\":{\"categorySize\":\"1\"}},\"id\":\"6eca7cbc8c666a4ace665d02ae5bde3c\",\"ref\":\"7f3cd76\",\"submit\":true,\"tag\":\"invoicePC\",\"type\":\"native$null$invoice\"},\"submitOrderPC_1\":{\"fields\":{\"cartText\":\"返回购物车\",\"cartUrl\":\"//cart.taobao.com/cart.htm\",\"fromCart\":true,\"fromMallYaoCart\":false,\"isTmallHKPresellOrder\":false,\"isTmallHKPresellSelf\":false,\"needUpdate4Address\":false,\"priceTips\":\"\",\"showPrice\":\"25.00\",\"submitOrderType\":\"UNITY\",\"submitTitle\":\"提交订单\",\"tmallPointStatus\":\"0\"},\"hidden\":{\"extensionMap\":{\"action\":\"/order/multiTerminalSubmitOrderAction\",\"event_submit_do_confirm\":\"1\",\"input_charset\":\"utf-8\",\"pcSubmitUrl\":\"/auction/confirm_order.htm?x-itemid=582408570328&x-uid=2453833782\",\"secretKey\":\"submitref\",\"secretValue\":\"df7ceaa053e1e921dc4805b3e6009138163c08abdbb1aa4102b3ce78f5336030\",\"sparam1\":\"eyJsaXN0Ijp7Ijk0MDc3NCI6eyJidXlhbW91bnQiOjEsImRpc2NvdW50VG90YWxGZWUiOjI1MDAsInNlbGxlcklkIjo3MjU2Nzc5OTQsInRvdGFsRmVlIjoyNTAwfX0sImxvZ2lzUmVjdkFkZHIiOiLmuIXmuqrpm4XnrZExM+agiyIsImxvZ2lzUmVjdk1vYmwiOiIxMzI4MTgzNTc3NSIsIm9yZGVyZnJvbXdoZXJlIjoidG1hbGxfcGMiLCJ1bWlkIjoiIn0=\",\"unitSuffix\":\"rz41\"}},\"id\":\"1\",\"ref\":\"35c4cbb\",\"submit\":true,\"tag\":\"submitOrderPC\",\"type\":\"native$null$submitOrder\"}}";
        }
        if(hierarchyV == null){
            hierarchyV = "{\"structure\":{\"confirmOrder_1\":[\"stepbarPC_1\",\"addressPC_1\",\"stationTabPC_1\",\"orderDesc_orderDesc_1\",\"order_6eca7cbc8c666a4ace665d02ae5bde3c\",\"menberBenefitsFlex_1\",\"riderClausePC_riderClause_1\",\"realPayPC_1\",\"submitOrderPC_1\",\"frontTracePC_1\",\"urlTransferPC_1\",\"ncCheckCode_ncCheckCode1\"],\"item_57fca718e742ddd7d1e9002b7859c1e0\":[\"itemInfoPC_57fca718e742ddd7d1e9002b7859c1e0\"],\"orderExtLeftPC_6eca7cbc8c666a4ace665d02ae5bde3c\":[\"invoicePC_6eca7cbc8c666a4ace665d02ae5bde3c\"],\"orderExtPC_6eca7cbc8c666a4ace665d02ae5bde3c\":[\"orderExtUpperPC_6eca7cbc8c666a4ace665d02ae5bde3c\",\"orderPayLayoutPC_6eca7cbc8c666a4ace665d02ae5bde3c\"],\"orderExtRightPC_6eca7cbc8c666a4ace665d02ae5bde3c\":[\"deliveryMethodPC_6eca7cbc8c666a4ace665d02ae5bde3c\",\"postageInsurancePC_6eca7cbc8c666a4ace665d02ae5bde3c\"],\"orderExtUpperPC_6eca7cbc8c666a4ace665d02ae5bde3c\":[\"orderExtLeftPC_6eca7cbc8c666a4ace665d02ae5bde3c\",\"orderExtRightPC_6eca7cbc8c666a4ace665d02ae5bde3c\"],\"orderPayLayoutPC_6eca7cbc8c666a4ace665d02ae5bde3c\":[\"orderPayPC_6eca7cbc8c666a4ace665d02ae5bde3c\",\"presellDescGroupPC_presellDescGroup_presellDescGroup6eca7cbc8c666a4ace665d02ae5bde3c\"],\"order_6eca7cbc8c666a4ace665d02ae5bde3c\":[\"seller_6eca7cbc8c666a4ace665d02ae5bde3c\",\"item_57fca718e742ddd7d1e9002b7859c1e0\",\"orderExtPC_6eca7cbc8c666a4ace665d02ae5bde3c\"],\"riderClauseContentFlex_riderClause_content_1\":[\"agencyPayPC_1\",\"anonymousPC_1\"],\"riderClausePC_riderClause_1\":[\"riderClauseContentFlex_riderClause_content_1\"],\"stationTabPC_1\":[\"stationAgencyService_1\"]}}";
        }
        BasicNameValuePair data = new BasicNameValuePair("data", dataV);
        BasicNameValuePair hierarchy = new BasicNameValuePair("hierarchy", hierarchyV);
        List<NameValuePair> list = new ArrayList<>();
        list.add(endpoint);
        list.add(operator);
        list.add(linkage);
        list.add(lifecycle);
        list.add(hierarchy);
        list.add(data);

        Object obj = sendPost(url, list);
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println("jsonObject = " + jsonObject);
        Map map = jsonObject.toJavaObject(Map.class);
        System.out.println(map.toString());
        return map;
    }


}

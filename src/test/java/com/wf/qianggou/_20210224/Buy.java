package com.wf.qianggou._20210224;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年02月24日 09:12
 */
public class Buy {

    // 提交 buy 接口的 cookie
    // cq=ccp%3D0; cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _bl_uid=Cpkkvl7OhhzahIm9Uzts580q5yXO; login=true; sm4=510114; xlly_s=1; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=true&cookie21=Vq8l%2BKCLjhS4UhJVbCU7&cookie14=Uoe1hgfnDpGpag%3D%3D&pas=0&cookie15=UIHiLt3xD8xYTw%3D%3D&cart_m=0; uc3=vt3=F8dCuASgNtu9fsDahtM%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOZZvyPeX&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMaNgmcB%2B; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100nxr6Y2lq%2FGqqNxfC5Nr1DkLHmMwUeaHBT81Dpol7NCmFGM2zSfku6qmjUdj0IRk1GTVvK0OjRN7UnFxRxWe97A%3D%3D; sg=424; csg=abe6654a; _m_h5_tk=c95fdb39ad7d567a673d852d0f0c4c70_1614138093615; _m_h5_tk_enc=c22c50a97a55d8e4ef89134c62fbf830; isg=BC4udUCrwiyTqAlVVtiLOsmLf4TwL_IpuBBfOFj3mjHsO86VwL9COdQ586fX-OpB; l=eBP-ZQ7PO6NKKiYbBOfanurza77OSIRYYuPzaNbMiOCPObfB5GrNW6g53786C3GVh6oMR3Rj0APzBeYBqQAonxvTuZ2pBdHmn; tfstk=cjbcBATVNi-fXE-lArTfTcDkMmzdwDzwdF-Gza341ExEjc5mx0oe3qGkOtbtf
    // 下订单接口的 cookie
    // cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _bl_uid=Cpkkvl7OhhzahIm9Uzts580q5yXO; login=true; sm4=510114; xlly_s=1; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=true&cookie21=Vq8l%2BKCLjhS4UhJVbCU7&cookie14=Uoe1hgfnDpGpag%3D%3D&pas=0&cookie15=UIHiLt3xD8xYTw%3D%3D&cart_m=0; uc3=vt3=F8dCuASgNtu9fsDahtM%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOZZvyPeX&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMaNgmcB%2B; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100nxr6Y2lq%2FGqqNxfC5Nr1DkLHmMwUeaHBT81Dpol7NCmFGM2zSfku6qmjUdj0IRk1GTVvK0OjRN7UnFxRxWe97A%3D%3D; sg=424; csg=abe6654a; _m_h5_tk=c95fdb39ad7d567a673d852d0f0c4c70_1614138093615; _m_h5_tk_enc=c22c50a97a55d8e4ef89134c62fbf830; tfstk=c7-5BKfZ97VWxV0U4LM44ZVUxRSFaIM1Kz66FU7IbuEYgAJAMsX3b1iXFwVUDNBf.; l=eBP-ZQ7PO6NKKeitBO5anurza77OmIOb81PzaNbMiInca1zcteVidNCIP966SdtjgtfVgetyGmkaaRHkzA4U-xN0Glnujqf1z796-; isg=BFtbapFrv9fxYPw-43dmkbR86r_FMG8y1afK002ZJtpXLHoO1QUXg4TuxoyiC8cq

    public static void main(String[] args){
        String buyCookie = "cq=ccp%3D0; cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _bl_uid=Cpkkvl7OhhzahIm9Uzts580q5yXO; login=true; sm4=510114; xlly_s=1; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=true&cookie21=Vq8l%2BKCLjhS4UhJVbCU7&cookie14=Uoe1hgfnDpGpag%3D%3D&pas=0&cookie15=UIHiLt3xD8xYTw%3D%3D&cart_m=0; uc3=vt3=F8dCuASgNtu9fsDahtM%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOZZvyPeX&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMaNgmcB%2B; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100nxr6Y2lq%2FGqqNxfC5Nr1DkLHmMwUeaHBT81Dpol7NCmFGM2zSfku6qmjUdj0IRk1GTVvK0OjRN7UnFxRxWe97A%3D%3D; sg=424; csg=abe6654a; _m_h5_tk=c95fdb39ad7d567a673d852d0f0c4c70_1614138093615; _m_h5_tk_enc=c22c50a97a55d8e4ef89134c62fbf830; isg=BC4udUCrwiyTqAlVVtiLOsmLf4TwL_IpuBBfOFj3mjHsO86VwL9COdQ586fX-OpB; l=eBP-ZQ7PO6NKKiYbBOfanurza77OSIRYYuPzaNbMiOCPObfB5GrNW6g53786C3GVh6oMR3Rj0APzBeYBqQAonxvTuZ2pBdHmn; tfstk=cjbcBATVNi-fXE-lArTfTcDkMmzdwDzwdF-Gza341ExEjc5mx0oe3qGkOtbtf";
        String confirmOrderCookie = "cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; csa=12198497492_0_30.773273.104.125916_0_0_0; ubn=p; ucn=center; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; _bl_uid=Cpkkvl7OhhzahIm9Uzts580q5yXO; login=true; sm4=510114; xlly_s=1; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=true&cookie21=Vq8l%2BKCLjhS4UhJVbCU7&cookie14=Uoe1hgfnDpGpag%3D%3D&pas=0&cookie15=UIHiLt3xD8xYTw%3D%3D&cart_m=0; uc3=vt3=F8dCuASgNtu9fsDahtM%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOZZvyPeX&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMaNgmcB%2B; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100nxr6Y2lq%2FGqqNxfC5Nr1DkLHmMwUeaHBT81Dpol7NCmFGM2zSfku6qmjUdj0IRk1GTVvK0OjRN7UnFxRxWe97A%3D%3D; sg=424; csg=abe6654a; _m_h5_tk=c95fdb39ad7d567a673d852d0f0c4c70_1614138093615; _m_h5_tk_enc=c22c50a97a55d8e4ef89134c62fbf830; tfstk=c7-5BKfZ97VWxV0U4LM44ZVUxRSFaIM1Kz66FU7IbuEYgAJAMsX3b1iXFwVUDNBf.; l=eBP-ZQ7PO6NKKeitBO5anurza77OmIOb81PzaNbMiInca1zcteVidNCIP966SdtjgtfVgetyGmkaaRHkzA4U-xN0Glnujqf1z796-; isg=BFtbapFrv9fxYPw-43dmkbR86r_FMG8y1afK002ZJtpXLHoO1QUXg4TuxoyiC8cq";
        String[] buyCookies = buyCookie.split(";");
        String[] confirmOrderCookies = confirmOrderCookie.split(";");
        List<String> buyList = new ArrayList<>();
        List<String> confirmOrderList = new ArrayList<>();
        for(int i = 0; i < buyCookies.length; i++){
            for(int j = 0; j < confirmOrderCookies.length; j++){
                if(buyCookies[i].equals(confirmOrderCookies[j])){
                    break;
                }
                if(j == confirmOrderCookies.length -1){
                    buyList.add(buyCookies[i]);
                }
            }
        }

        for(int i = 0; i < confirmOrderCookies.length; i++){
            for(int j = 0; j < buyCookies.length; j++){
                if(buyCookies[j].equals(confirmOrderCookies[i])){
                    break;
                }
                if(j == buyCookies.length -1){
                    confirmOrderList.add(confirmOrderCookies[i]);
                }
            }
        }

        buyList.forEach(System.out::println);
        System.out.println("-----------------------------------");
        confirmOrderList.forEach(System.out::println);

    }

    @Test
    public void test01(){
        String buyCookie = "youpindistinct_id=1782442911a545-09446ae2cfd82e-5771133; mjclient=PC; Hm_lvt_025702dcecee57b18ed6fb366754c1b8=1615516047,1615778597; b_auth=mijiayoupin; serviceToken=dsUBMPUQiQ+7+w5F3AqKA0EGKpkaOAt7XRJjdzSYP9G7KU9lGlTEXmznETDwSUafMAxLAcZ9mUsnF4r4BzRt010K/c7dIpTPhbqx/ULCa1w3mkTioDuAG/36IPM/jcIa9zRMlsNwmk0OqIe1nNZOKw==; cUserId=_BbRsNJ_dUUL8kr4s6mDlXtaObw; youpin_sessionid=17834c110ef-0fc6caf5951b71-2064; Hm_lpvt_025702dcecee57b18ed6fb366754c1b8=1615792771";
        String confirmOrderCookie = "youpindistinct_id=1782442911a545-09446ae2cfd82e-5771133; mjclient=PC; Hm_lvt_025702dcecee57b18ed6fb366754c1b8=1615516047,1615778597; b_auth=mijiayoupin; serviceToken=dsUBMPUQiQ+7+w5F3AqKA0EGKpkaOAt7XRJjdzSYP9G7KU9lGlTEXmznETDwSUafMAxLAcZ9mUsnF4r4BzRt010K/c7dIpTPhbqx/ULCa1w3mkTioDuAG/36IPM/jcIa9zRMlsNwmk0OqIe1nNZOKw==; cUserId=_BbRsNJ_dUUL8kr4s6mDlXtaObw; youpin_sessionid=17834c2075f-040fda7c3832f2-2064; Hm_lpvt_025702dcecee57b18ed6fb366754c1b8=1615792835";
        String[] buyCookies = buyCookie.split(";");
        String[] confirmOrderCookies = confirmOrderCookie.split(";");
        List<String> buyList = new ArrayList<>();
        List<String> confirmOrderList = new ArrayList<>();
        for(int i = 0; i < buyCookies.length; i++){
            for(int j = 0; j < confirmOrderCookies.length; j++){
                if(buyCookies[i].equals(confirmOrderCookies[j])){
                    break;
                }
                if(j == confirmOrderCookies.length -1){
                    buyList.add(buyCookies[i]);
                }
            }
        }

        for(int i = 0; i < confirmOrderCookies.length; i++){
            for(int j = 0; j < buyCookies.length; j++){
                if(buyCookies[j].equals(confirmOrderCookies[i])){
                    break;
                }
                if(j == buyCookies.length -1){
                    confirmOrderList.add(confirmOrderCookies[i]);
                }
            }
        }

        buyList.forEach(System.out::println);
        System.out.println("-----------------------------------");
        confirmOrderList.forEach(System.out::println);
        // tfstk  l   isg
        String s1 = "https://trade.xiaomiyoupin.com/tr/checkout?quickOrder=1&checkType=2&sourceType=2&pid=&count=1&_iid=g=140734&spmref=YouPinPC.$Detail$_140734.buy.0.24754051";
        String s2 = "https://trade.xiaomiyoupin.com/tr/checkout?quickOrder=1&checkType=2&sourceType=2&pid=&count=1&_iid=g=140734&spmref=YouPinPC.$Detail$_140734.buy.0.21753533";
        String s3 = "https://trade.xiaomiyoupin.com/tr/checkout?quickOrder=1&checkType=2&sourceType=2&pid=&count=1&_iid=g=129867&spmref=YouPinPC.$Detail$_129867.buy.0.89832816";
        String s4 = "https://trade.xiaomiyoupin.com/tr/checkout?quickOrder=1&checkType=2&sourceType=2&pid=&count=1&_iid=g=105996&spmref=YouPinPC.$Detail$_105996.buy.0.82827151";
        String s5 = "https://trade.xiaomiyoupin.com/tr/checkout?quickOrder=1&checkType=2&sourceType=2&pid=&count=1&_iid=g=105996&spmref=YouPinPC.$Detail$_105996.buy.0.64445536";
    }


}

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
        // 先后两次的 cookie
        // cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; login=true; sm4=510114; xlly_s=1; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=true&cookie21=Vq8l%2BKCLjhS4UhJVbCU7&cookie14=Uoe1hgfnDpGpag%3D%3D&pas=0&cookie15=UIHiLt3xD8xYTw%3D%3D&cart_m=0; uc3=vt3=F8dCuASgNtu9fsDahtM%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOZZvyPeX&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMaNgmcB%2B; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100nxr6Y2lq%2FGqqNxfC5Nr1DkLHmMwUeaHBT81Dpol7NCmFGM2zSfku6qmjUdj0IRk1GTVvK0OjRN7UnFxRxWe97A%3D%3D; sg=424; csg=abe6654a; _m_h5_tk=c95fdb39ad7d567a673d852d0f0c4c70_1614138093615; _m_h5_tk_enc=c22c50a97a55d8e4ef89134c62fbf830; tfstk=c9llBRNrlYyWm-emCQNS79fqfCvAZ1OUfWyngjCyCeR0DoGVitXVQu55oPio991..; l=eBP-ZQ7PO6NKKS4EBOfZourza779jIRA_uPzaNbMiOCPOBfp5liFW6g5QsT9CnGVh6VDR3Rj0APyBeYBqImRXtWLuZ2pBdDmn; isg=BHR0oCUWqJIlbQNDYHrxdOdJRTLmTZg3ZpLVTg7VA_-CeRTDNly6xZo7_bGhhtCP
        // cna=vswvGMk9ymUCAbaWP+aWkFr3; lid=%E5%B0%8F%E9%A3%9E546466464; enc=xH08HkVNFnMiMJezzBT2YuOeTIopL1QU9gW8Kyoo%2FShx%2FV7w%2B4NSJMHYDO7EzDpv7h1XglEQP9QKvINN0u9Hzg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; t=d85da4d42ac8fea5bf4b3432ef3933bc; tracknick=%5Cu5C0F%5Cu98DE546466464; cookie2=1250a92a2efa69ec26ac92b7f208ed85; lgc=%5Cu5C0F%5Cu98DE546466464; dnk=%5Cu5C0F%5Cu98DE546466464; _tb_token_=fbe5b7348e1db; login=true; sm4=510114; xlly_s=1; uc1=cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=true&cookie21=Vq8l%2BKCLjhS4UhJVbCU7&cookie14=Uoe1hgfnDpGpag%3D%3D&pas=0&cookie15=UIHiLt3xD8xYTw%3D%3D&cart_m=0; uc3=vt3=F8dCuASgNtu9fsDahtM%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=W5iHLLyFOGW7aA%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDOZZvyPeX&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDMaNgmcB%2B; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100nxr6Y2lq%2FGqqNxfC5Nr1DkLHmMwUeaHBT81Dpol7NCmFGM2zSfku6qmjUdj0IRk1GTVvK0OjRN7UnFxRxWe97A%3D%3D; sg=424; csg=abe6654a; _m_h5_tk=c95fdb39ad7d567a673d852d0f0c4c70_1614138093615; _m_h5_tk_enc=c22c50a97a55d8e4ef89134c62fbf830; isg=BBERTFr1FRUwskacvek8k7JqIB2rfoXwkxHQMfOmDVj3mjHsO86VwL_4PG58iR0o; l=eBP-ZQ7PO6NKKdQbBOfanurza77OSIRYYuPzaNbMiOCP_B1B5g8GW6g5Q9T6C3GVh6oMR3Rj0APzBeYBqQAonxvTuZ2pBdHmn; tfstk=cKBRBy9gdr4utSxAb_FmRdqI_JUGwsupoutjplVbExxW4E1mIOXTLAlWo4pYD

        String buyCookie = "t=a51b8b7d7a650d13d6143d0ceeabd60c; lid=%E5%B0%8F%E9%A3%9E546466464; cookie2=19986533f8b4ab3a058294eb4e19e07e; cna=vswvGMk9ymUCAbaWP+aWkFr3; dnk=%5Cu5C0F%5Cu98DE546466464; tracknick=%5Cu5C0F%5Cu98DE546466464; lgc=%5Cu5C0F%5Cu98DE546466464; enc=xam7T7YitMXGKFwifcY%2BeTrgGeiSSFnblXZTBKYbCRaUsCYEqLnZARxWBeWWcw%2FJWAx7DAgxjWWwx4%2FwagTjrg%3D%3D; login=true; hng=CN%7Czh-CN%7CCNY%7C156; sm4=510114; _m_h5_tk=b832517fb8839dace322aa87278919cc_1614590589672; _m_h5_tk_enc=003ca9ac3071458484811212493b2ed7; xlly_s=1; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&existShop=true&cookie21=V32FPkk%2FgihF%2FS5nrepr&cart_m=0&cookie14=Uoe1hgCx68WyaA%3D%3D&cookie15=V32FPkk%2Fw0dUvg%3D%3D&pas=0; uc3=vt3=F8dCuAVh2HJ0eXy2RHk%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=VFC%2FuZ9ayeYq2g%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDPpaL%2FlaM&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDNlEYCXfY; _l_g_=Ug%3D%3D; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100m2gVRpr1t1SrgN1nVOX9oNPsZznXVuV%2BcfTBiNUndT1mYTWmhQn%2B%2BWNDlq4tWTpyd3zpU4Fo1CwS5yif2ReEZw%3D%3D; sg=424; csg=bafcf068; _tb_token_=e983eee43be30; tfstk=c80RBPaMRKvkAWVx_0Kcdu_hReOcZX6LnQNfJCAfNgE5ZJ_diF0iWe7NV-X8kIC..; l=eBP-ZQ7PO6NKKWnzBOfanurza77tbIRYmuPzaNbMiOCP9MCp550lW6gBL7T9CnGVh6SMR3Rj0APyBeYBq_fonxvTuZ2pBdHmn; isg=BBcXOcZMO1NWqIBKh8tijejopothXOu-edOWB2lEJOZNmDfacSryDtb6_jiGdMM2";
        String confirmOrderCookie = "t=a51b8b7d7a650d13d6143d0ceeabd60c; lid=%E5%B0%8F%E9%A3%9E546466464; cookie2=19986533f8b4ab3a058294eb4e19e07e; cna=vswvGMk9ymUCAbaWP+aWkFr3; dnk=%5Cu5C0F%5Cu98DE546466464; tracknick=%5Cu5C0F%5Cu98DE546466464; lgc=%5Cu5C0F%5Cu98DE546466464; enc=xam7T7YitMXGKFwifcY%2BeTrgGeiSSFnblXZTBKYbCRaUsCYEqLnZARxWBeWWcw%2FJWAx7DAgxjWWwx4%2FwagTjrg%3D%3D; login=true; hng=CN%7Czh-CN%7CCNY%7C156; sm4=510114; _m_h5_tk=b832517fb8839dace322aa87278919cc_1614590589672; _m_h5_tk_enc=003ca9ac3071458484811212493b2ed7; xlly_s=1; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&existShop=true&cookie21=V32FPkk%2FgihF%2FS5nrepr&cart_m=0&cookie14=Uoe1hgCx68WyaA%3D%3D&cookie15=V32FPkk%2Fw0dUvg%3D%3D&pas=0; uc3=vt3=F8dCuAVh2HJ0eXy2RHk%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&lg2=VFC%2FuZ9ayeYq2g%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDPpaL%2FlaM&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDNlEYCXfY; _l_g_=Ug%3D%3D; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100m2gVRpr1t1SrgN1nVOX9oNPsZznXVuV%2BcfTBiNUndT1mYTWmhQn%2B%2BWNDlq4tWTpyd3zpU4Fo1CwS5yif2ReEZw%3D%3D; sg=424; csg=bafcf068; _tb_token_=e983eee43be30; tfstk=cFHhBR2EfXPBgvViGpwI0gxwCvROayJzl8PoQAQFoUwL5H0_YsfG_szFdGqj-YC5.; l=eBP-ZQ7PO6NKKH9CBOfanurza77O7IRYmuPzaNbMiOCPOX5p5A_AW6gBBrL9CnGVh6oDR3Rj0APzBeYBqC0uZtXTuZ2pBCHmn; isg=BL29S81pgeGOwxoYMQ0o32ZGzBm3WvGsR22stX8C65RDtt3oR6-hfYlsYOrwMglk";
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
    }


}

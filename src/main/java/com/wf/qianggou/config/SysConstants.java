package com.wf.qianggou.config;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年03月05日 11:15
 */
public class SysConstants {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss SSS");
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");

    public static String buyTime = " 20:00:00 000";

    public static final String cookie = "ubn=p; ucn=center; _bl_uid=p0kX0lgylLhwLmygO8Xk8gkiRyaw; _samesite_flag_=true; cookie2=1f42a912c65da63a826aa6d7bc93b244; t=476f0f828fc9daf6a7e9cc2b5a1ff712; _tb_token_=eb7ee398763be; thw=cn; enc=h0M8WKmJEjURCAOdp3ki3VyXZrTNZuOpKjxIQr206G%2Fq%2BTzqVMNOcHbVkRZ6u5gnctYktr8sVDXVwuqKvUwJTw%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; cna=vswvGMk9ymUCAbaWP+aWkFr3; lgc=%5Cu5C0F%5Cu98DE546466464; dnk=%5Cu5C0F%5Cu98DE546466464; tracknick=%5Cu5C0F%5Cu98DE546466464; xlly_s=1; mt=ci=2_1; _m_h5_tk=6888a86a88c6bfecb1e68ff3fb8c8316_1615903744953; _m_h5_tk_enc=5e5020f5926da7f0ae1461f790b7f65b; sgcookie=E100%2BqJjGyO15dsmPq676h%2BALAXikdVc8P5VSCX3pQEEog6viFg4bPa3bdJ9USTkZAK4a2o5TCsQ4cFk1Ymg4WluXg%3D%3D; unb=2453833782; uc3=lg2=WqG3DMC9VAQiUQ%3D%3D&vt3=F8dCuAorHSZXKHa5e5c%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D&id2=UUwVZ%2FVd30BdBQ%3D%3D; csg=7e67fab2; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; skt=16bb17eeaf702d07; existShop=MTYxNTg5NTQ4OA%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLCbEvSk8oS&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSCFu4ojtPR; _cc_=U%2BGCWk%2F7og%3D%3D; _l_g_=Ug%3D%3D; sg=424; _nk_=%5Cu5C0F%5Cu98DE546466464; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; uc1=cookie16=V32FPkk%2FxXMk5UvIbNtImtMfJQ%3D%3D&existShop=true&cart_m=0&cookie21=WqG3DMC9Fb5mPLIQo9kR&cookie15=U%2BGCWk%2F75gdr5Q%3D%3D&cookie14=Uoe1hxHAKaPwQw%3D%3D&pas=0; isg=BDAwb1KUJGN9j8eDP1R24PmTAf6CeRTDyUG5OyqB_Ate5dCP0onkU4bXPe2F7syb; l=eBScYSngOGNvauVkBOfanurza77OSIRYYuPzaNbMiOCPO55B5Qg1W6wvsWY6C3GVh63DR35VLnkHBeYBqQAonxvOUKaOYMkmn; tfstk=cicPBg_KtQdyInVBB7NFAwATFv3RwXauRIruExcCgezx1zfcVMU0g9gqmld0E";
    public static final String key = "飞天53度";
//    public static final String key = "Redmi K40";
//    public static final String key = "最生活小米";
//    public static final String key = "Nike耐克官方";
//    public static final String key = "Jordan官方";
//    public static final String key = "SoC旗舰芯片";

    public static final long oneDay = 1000L * 60 * 60 * 24;

}

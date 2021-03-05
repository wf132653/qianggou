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

    public static final String cookie = "ubn=p; ucn=center; t=a51b8b7d7a650d13d6143d0ceeabd60c; lid=%E5%B0%8F%E9%A3%9E546466464; cookie2=19986533f8b4ab3a058294eb4e19e07e; cna=vswvGMk9ymUCAbaWP+aWkFr3; dnk=%5Cu5C0F%5Cu98DE546466464; tracknick=%5Cu5C0F%5Cu98DE546466464; lgc=%5Cu5C0F%5Cu98DE546466464; tk_trace=1; enc=xam7T7YitMXGKFwifcY%2BeTrgGeiSSFnblXZTBKYbCRaUsCYEqLnZARxWBeWWcw%2FJWAx7DAgxjWWwx4%2FwagTjrg%3D%3D; hng=CN%7Czh-CN%7CCNY%7C156; sm4=510114; _tb_token_=e983eee43be30; _m_h5_tk=3bcbb092fabfce70366f469030fc1d12_1614935710862; _m_h5_tk_enc=ca88cca65643fed73cb5360e6e671a95; xlly_s=1; uc1=cookie21=UIHiLt3xThH8t7YQouiW&existShop=true&cookie15=URm48syIIVrSKA%3D%3D&pas=0&cookie16=WqG3DMC9UpAPBHGz5QBErFxlCA%3D%3D&cookie14=Uoe1hg%2BGuNBeLw%3D%3D&cart_m=0; uc3=id2=UUwVZ%2FVd30BdBQ%3D%3D&lg2=VT5L2FSpMGV7TQ%3D%3D&vt3=F8dCuAVmbSXyjhboNNA%3D&nk2=synFqM53%2B34OOhN8lQ%3D%3D; _l_g_=Ug%3D%3D; uc4=id4=0%40U27KCxnkg%2BYg1C7JnsLDMWWtIx%2Fc&nk4=0%40sVZlAh7wOoLIYyo1%2BRQD2VSDOTjChpiN; unb=2453833782; cookie1=BYlsb5eBt59VpQOOC2%2BctMOI%2FdeOecij6kZgsgiprqE%3D; login=true; cookie17=UUwVZ%2FVd30BdBQ%3D%3D; _nk_=%5Cu5C0F%5Cu98DE546466464; sgcookie=E100xa6cDN5E06VtU0S6cUicrCNczba%2FeWi29PCv%2B1uTk8PlA%2FTd2Qfn07%2B8CAjldQ3TlkDAG8j13BEwy8YD1Pt5xQ%3D%3D; sg=424; csg=4535c1da; tfstk=chBdBNv0FP4HcOxO06FiVB-TRWrGZEgJSktsekBw3uIKFFMRijXcH8kqOF0pBlC..; l=eBP-ZQ7PO6NKKk3zBOfwourza77OSIRA_uPzaNbMiOCP9B5p5fUfW6N4LdT9C3GVhsH2R3rvbb0UBeYBqI0vqxPEQZqbXlHmn; isg=BLCw7UMkpPF6mUcfhE5t4Ft1gX4C-ZRDq1ccWqoBfIveZVAPUglk0wZXvW0Fckwb";
    public static final String key = "飞天53度";
//    public static final String key = "最生活小米";

    public static final long oneDay = 1000L * 60 * 60 * 24;

}

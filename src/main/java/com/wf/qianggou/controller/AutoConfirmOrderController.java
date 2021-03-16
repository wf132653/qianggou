package com.wf.qianggou.controller;

import com.wf.qianggou.util.ThreadPoolUtil;
import com.wf.qianggou.util.selenium.RunnerOfShoppingDetail;
import com.wf.qianggou.util.xiaomishangcheng._1_GetSalt;
import com.wf.qianggou.util.xiaomishangcheng._4_SubmitOrder;
import com.wf.qianggou.util.xiaomiyoupin.OrderSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * demo_class
 *
 * @author wf
 * @date 2021年02月26日 10:43
 */
@RestController
@RequestMapping("/order")
public class AutoConfirmOrderController {

    @Autowired
    private RunnerOfShoppingDetail runnerOfShoppingDetail;

    @GetMapping
    public void order() throws Exception {
        runnerOfShoppingDetail.run();
    }

    @PostMapping("/xmscK40")
    public void xmscK40(@RequestBody Map map) {
        String cookie = map.get("cookie").toString();
        ThreadPoolUtil.executor.execute(() -> {
            try {
                _4_SubmitOrder.submitOrder(cookie);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("/xmypK40")
    public void xmypK40(@RequestBody Map map) {
        String cookie = map.get("cookie").toString();
        String addressId = map.get("addressId").toString();
        String price = map.get("price").toString();
        String key = map.get("key").toString();
        String gId = map.get("gId").toString();
        String name = map.get("name").toString();
        ThreadPoolUtil.executor.execute(() -> {
            try {
                OrderSubmit orderSubmit = new OrderSubmit();
                orderSubmit.orderSubmit(cookie, addressId, price, key, gId, name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}

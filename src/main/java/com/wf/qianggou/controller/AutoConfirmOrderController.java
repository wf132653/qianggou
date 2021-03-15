package com.wf.qianggou.controller;

import com.wf.qianggou.util.ThreadPoolUtil;
import com.wf.qianggou.util.selenium.RunnerOfShoppingDetail;
import com.wf.qianggou.util.xiaomishangcheng._1_GetSalt;
import com.wf.qianggou.util.xiaomishangcheng._4_SubmitOrder;
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

    @PostMapping("/k40")
    public void k40(@RequestBody Map map) {
        String cookie = map.get("cookie").toString();
        ThreadPoolUtil.executor.execute(() -> {
            try {
                _4_SubmitOrder.submitOrder(cookie);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("/k401")
    public void k40_test(@RequestBody Map map) {
        String cookie = map.get("cookie").toString();
        ThreadPoolUtil.executor.execute(() -> {
            try {
                _1_GetSalt getSalt = new _1_GetSalt(cookie);
                getSalt.getSlat();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}

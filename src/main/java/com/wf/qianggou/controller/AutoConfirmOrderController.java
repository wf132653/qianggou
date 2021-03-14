package com.wf.qianggou.controller;

import com.wf.qianggou.util.ThreadPoolUtil;
import com.wf.qianggou.util.selenium.RunnerOfShoppingDetail;
import com.wf.qianggou.util.xiaomi._4_SubmitOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/k40")
    public void k40() {
        ThreadPoolUtil.executor.execute(() -> {
            try {
                _4_SubmitOrder.submitOrder();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}

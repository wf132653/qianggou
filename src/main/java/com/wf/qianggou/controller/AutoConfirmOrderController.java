package com.wf.qianggou.controller;

import com.wf.qianggou.util.selenium.OpenLastWebDriver;
import com.wf.qianggou.util.selenium.RunnerOfShoppingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

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
    @Autowired
    private OpenLastWebDriver openLastWebDriver;

    @GetMapping
    public void order() throws Exception{
        runnerOfShoppingDetail.run();
    }

    @GetMapping("/save")
    public void save(){
        openLastWebDriver.save();
    }

    @PostMapping("/test")
    public void test(@RequestBody String body, HttpServletRequest request){
//        System.out.println("body = " + body);
//        if(body != null){
//            String[] strs = body.split("&");
//            Arrays.stream(strs).forEach(System.out::println);
//        }
//        Enumeration headerNames =  request.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            Object name = headerNames.nextElement();
//            System.out.println("name = " + name);
//            System.out.println("value = " + request.getHeader(name.toString()));
//        }
//        Enumeration<String> pStringEnumeration = request.getParameterNames();
//        while (pStringEnumeration.hasMoreElements()){
//            Object name = pStringEnumeration.nextElement();
//            System.out.println("name2 = " + name);
//            System.out.println("value2 = " + request.getParameter(name.toString()));
//        }
        System.out.println("request.getSession().getId() = " + request.getSession().getId());
    }

}

package com.hbh.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hbh on 2016/7/11.
 */

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value="go")
    public String test(){
        return "test/index" ;
    }

    @RequestMapping(value="body")
    public Map body(){
        Map map = new HashMap();
        map.put("aa","qq");
        return map;
    }
}

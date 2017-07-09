package com.hbh.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hbh on 2016/7/11.
 */

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        return "index";
    }

    @RequestMapping(value="main")
    public String test(){
        return "main" ;
    }

    @RequestMapping(value="body")
    public Map body(){
        Map map = new HashMap();
        map.put("aa","qq");
        return map;
    }
}

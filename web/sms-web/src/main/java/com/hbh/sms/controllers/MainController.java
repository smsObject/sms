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
@RequestMapping("/sms")
public class MainController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        return "index";
    }
}

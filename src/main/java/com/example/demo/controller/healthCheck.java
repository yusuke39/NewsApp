package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class healthCheck {


    @RequestMapping("/")
    public int health(HttpServletResponse http){
        return http.getStatus();
    }
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * ログイン画面の表示をする.
     *
     * @return ログイン画面
     */
    @RequestMapping("/login")
    public String login(){
        return"/admin/adminLogin";
    }

}

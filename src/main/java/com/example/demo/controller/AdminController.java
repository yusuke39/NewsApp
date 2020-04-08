package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * ログイン画面の表示をする.
     *
     * @return 管理者ログイン画面
     */
    @RequestMapping("/login")
    public String login(){
        return"/admin/adminLogin";
    }


    /**
     * 管理者新規登録画面を表示する
     *
     * @return 管理者新規登録画面
     */
    @RequestMapping("/register")
    public String register(){
        return "/admin/adminRegister";
    }

}

package com.example.demo.controller;

import com.example.demo.form.AdminRegisterForm;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * ログイン画面の表示をする.
     *
     * @return 管理者ログイン画面
     */
    @RequestMapping("/adminLogin")
    public String login(){

        return"/admin/adminLogin";
    }


    /**
     * 管理者新規登録画面を表示する.
     *
     * @return 管理者新規登録画面
     */
    @RequestMapping("/adminRegisterPage")
    public String register(){

        return "/admin/adminRegister";
    }

    /**
     * 管理者のTOP画面を表示する.
     *
     * @return 管理者TOP画面の表示.
     */
    @RequestMapping("/adminTop")
    public String adminTop(){
        return "/admin/adminTop";
    }


    /**
     * 管理者登録後に管理者のTOPページへ繊維する.
     *
     * @param adminRegisterForm
     * @return /adminTOPへリダイレクト.
     */
    @RequestMapping("/registerAdmin")
    public String registerAdmin(AdminRegisterForm adminRegisterForm){

        adminService.insertAdmin(adminRegisterForm);

        return "redirect:/admin/adminTop";
    }


}
package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.form.AdminRegisterForm;
import com.example.demo.form.AdminRegisterGenreForm;
import com.example.demo.security.LoginAdmin;
import com.example.demo.service.AdminService;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminService adminService;

    @Autowired
    ArticleService articleService;



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
    public String adminRegisterPage(){

        return "/admin/adminRegister";
    }

    /**
     * 管理者のTOP画面を表示する.
     *
     * @return 管理者TOP画面の表示.
     */
    @RequestMapping("/adminTop")
    public String adminTop(Model model, @AuthenticationPrincipal LoginAdmin loginAdmin){

        /*ログインしたユーザーの名前を渡す*/
        model.addAttribute("userName",loginAdmin.getAdmin().getName());

        /*この管理者が登録した記事を検索し、全て取得する*/
        List<Article> articleList = articleService.findArticlesByAdminId(12);
        model.addAttribute("articleList", articleList);

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

        if(!adminRegisterForm.getConfirmPassword().equals(adminRegisterForm.getPassword())){
            return "/admin/adminTop";
        }

        adminService.insertAdmin(adminRegisterForm);

        return "redirect:/admin/adminTop";
    }

    /**
     * ジャンル登録画面表示.
     * @return
     */
    @RequestMapping("/adminRegisterGenre")
    public String adminRegisterGenre(){
        return "/admin/adminGenre";
    }


    /**
     * ジャンルを登録する.
     * @param adminRegisterGenreFrom
     * @return
     */
    @RequestMapping("/registerGenre")
    public String registerGenre(AdminRegisterGenreForm adminRegisterGenreFrom){

        adminService.insertGenre(adminRegisterGenreFrom);

        return "redirect:/admin/adminTop";
    }


}
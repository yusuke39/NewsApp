package com.example.demo.controller;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Article;

import com.example.demo.form.AdminEditForm;
import com.example.demo.form.AdminRegisterForm;
import com.example.demo.form.AdminRegisterGenreForm;
import com.example.demo.security.LoginAdmin;
import com.example.demo.service.AdminService;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ArticleService articleService;


    @Autowired
    HttpSession session;

    @Autowired
    private StringRedisTemplate redisTemplate;



    /**
     * ログイン画面の表示をする.
     *
     * @return 管理者ログイン画面
     */
    @RequestMapping("/adminLogin")
    public String login(Model model, @AuthenticationPrincipal LoginAdmin loginAdmin){

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
    public String adminTop(Integer pageCount, @AuthenticationPrincipal LoginAdmin loginAdmin,  Model model){

        int adminId = loginAdmin.getAdmin().getId();

        /*ログインしたユーザーの名前を渡す*/
        session.setAttribute("adminName",loginAdmin.getAdmin().getName());

        /*管理者IDを取得してセッションに入れる*/
        session.setAttribute("adminId",adminId);

        /*ジャンルを取得してheaderに表示する*/
        model.addAttribute("genreList", articleService.findAllGenre());

        /*この管理者が登録した記事を検索し、全て取得する*/
        List<Article> articleList = articleService.findArticlesByAdminId(adminId, pageCount);

        /*ページネーション (下のcountArticlesメソッド呼び出してます。）*/
       Integer countArticles =  countArticles(articleList.size());

        if(countArticles == 0){
             model.addAttribute("countArticles", countArticles);
        } else {
             model.addAttribute("countArticles", countArticles);
        }


        model.addAttribute("articleList", articleList);


        return "/admin/adminTop";
    }


    /**
     * 管理者登録後に管理者のログインページへ繊維する.
     *
     * @param adminRegisterForm
     * @return /adminTOPへリダイレクト.
     */
    @RequestMapping("/registerAdmin")
    public String registerAdmin(AdminRegisterForm adminRegisterForm){

        if(!adminRegisterForm.getConfirmPassword().equals(adminRegisterForm.getPassword())){
            return "redirect:/admin/registerAdmin";
        }

        adminService.insertAdmin(adminRegisterForm);

        return "redirect:/admin/adminLogin";
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


    /**
     * 管理者情報変更画面を表示.
     * @param model
     * @param loginAdmin
     * @return
     */
    @RequestMapping("/editAdminPage")
    public String editAdminPage(Model model,  @AuthenticationPrincipal LoginAdmin loginAdmin){

        Admin admin = adminService.findAdminByEmail(loginAdmin.getAdmin().getEmail());

        model.addAttribute("admin",admin);

        return"/admin/editAdmin";
    }


    /**
     * 管理者情報を編集する.
     * @param adminEditForm
     * @param loginAdmin
     * @return
     */
    @RequestMapping("/editAdmin")
    public String updateAdmin(AdminEditForm adminEditForm,@AuthenticationPrincipal LoginAdmin loginAdmin){

        adminService.updateAdmin(adminEditForm,loginAdmin.getAdmin().getId());

        Admin admin = adminService.findAdminById(loginAdmin.getAdmin().getId());

        session.setAttribute("adminName", admin.getName());

        return "redirect:/admin/adminTop";
    }



    /**
     * ページネーション用の計算ロジック.
     * @param articleListSize
     * @return ページネーションに表示させる数字
     */
    public Integer countArticles(int articleListSize){

//        Integer countArticles = articleService.getCount(adminId);

        if(articleListSize % 5 == 0){
            return articleListSize / 5;
        } else {
            return articleListSize / 5 + 1;
        }


    }

}
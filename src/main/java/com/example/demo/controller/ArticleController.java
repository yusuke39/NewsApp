package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.domain.Genre;
import com.example.demo.form.ArticleRegisterForm;
import com.example.demo.security.LoginAdmin;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    HttpSession session;


    /**
     * 記事投稿画面の表示.
     * @return 記事投稿画面
     */
    @RequestMapping("/createArticle")
    public String createArticle(Model model,@AuthenticationPrincipal LoginAdmin loginAdmin){

        int adminId = (int) session.getAttribute("adminId");
        System.out.println(adminId);

        //ジャンル名をプルダウンに表示させる.
        List<Genre> genreList = articleService.findAllGenre();
        model.addAttribute("genreList", genreList);

        return "admin/createArticle";
    }


    /**
     * 記事を投稿する.
     * @return 管理者TOP画面へ遷移する.
     *
     */
    @RequestMapping("/registerArticle")
    public String registerArticle(ArticleRegisterForm articleRegisterForm,
                                  HttpServletRequest req, HttpServletResponse resp,
                                  Model model) throws IOException, ServletException {


        int adminId = (int) session.getAttribute("adminId");
        System.out.println(adminId);


        /*ファイルをCloudStorageへアップロードする*/
        Part filePart = req.getPart("imageFile");

        final String fileName = filePart.getSubmittedFileName();


        if(fileName != null && !fileName.isEmpty() && fileName.contains(".")){
            final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
            for(String s : allowedExt){
                if(extension.equals(s)){
                    String imageUrl =  this.articleService.uploadFile(filePart, "artifacts.newsapp-273606.appspot.com");
                    /*記事を登録するサービスへ値を渡す*/
                    articleService.registerArticle(articleRegisterForm, adminId,imageUrl);
                }
            }
        }
        return "redirect:/admin/adminTop";
    }


    /**
     * 記事詳細を表示する.
     * @param model
     * @param articleId
     * @return
     */
    @RequestMapping("/articleDetail")
    public String articleDetail(Model model, @RequestParam("articleId") int articleId, @AuthenticationPrincipal LoginAdmin loginAdmin){

        int adminId = (int) session.getAttribute("adminId");

        Article article = articleService.finsArticleByAdminIdArticleId(adminId, articleId);

        model.addAttribute("article", article);

        return "/common/newsDetail";
    }


    /**
     * 記事編集ページを表示する.
     * @return
     */
    @RequestMapping("/editArticlePage")
    public String editArticlePage(){
        return "/admin/editArticle";
    }



}

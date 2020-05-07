package com.example.demo.controller;

import com.example.demo.form.ArticleRegisterForm;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    /**
     * 記事投稿画面の表示.
     * @return 記事投稿画面
     */
    @RequestMapping("/createArticle")
    public String createArticle(){
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
                    articleService.registerArticle(articleRegisterForm, imageUrl);
                }
            }
        }


        return "redirect:/admin/adminTop";
    }


}

//package com.example.demo.controller;
//
//import com.example.demo.domain.Article;
//import com.example.demo.security.LoginAdmin;
//import com.example.demo.service.ArticleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/article")
//public class SearchController {
//
//    @Autowired
//    ArticleService articleService;
//
//    /**
//     * 記事をジャンルIDと管理者IDで検索する.
//     * @param genreId
//     * @param adminId
//     * @return
//     */
//    @RequestMapping("/searchArticlesByGenreId")
//    public List<Article> searchArticles(@RequestParam("genreId") int genreId, @RequestParam("adminId") int adminId){
//
////        if(genreId == 0){
////            List<Article> articleList = articleService.findArticlesByAdminId(adminId);
////        }
//
//        List<Article> articleList = articleService.findArticlesByGenreId(genreId, adminId);
//
//        return articleList;
//
//    }
//
//
//    /**
//     * 記事をタイトルで曖昧検索する.
//     * @param titleName
//     * @param loginAdmin
//     * @return
//     */
//    @RequestMapping("/searchArticlesByTitleName")
//    public List<Article> searchArticleByTitleName(@RequestParam("titleName") String titleName, @AuthenticationPrincipal LoginAdmin loginAdmin){
//
//        System.out.println(articleService.findArticlesByLikeTitleName(titleName, loginAdmin.getAdmin().getId()));
//
//        return articleService.findArticlesByLikeTitleName(titleName, loginAdmin.getAdmin().getId());
//    }
//
//}

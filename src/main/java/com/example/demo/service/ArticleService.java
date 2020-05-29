package com.example.demo.service;

import com.example.demo.controller.AdminController;
import com.example.demo.domain.Article;
import com.example.demo.domain.Genre;
import com.example.demo.form.ArticleRegisterForm;
import com.example.demo.form.ArticleSearchFrom;
import com.example.demo.mapper.ArticleMapper;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ArticleService {


    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    AdminController adminController;

    @Autowired
    HttpSession session;



    /**
     * 記事をDBへ登録する.
     * @param articleRegisterForm
     */
    public void registerArticle(ArticleRegisterForm articleRegisterForm, int adminId, String imageUrl){

        Article article = new Article();
        article.setTitle(articleRegisterForm.getTitle());
        article.setImage(imageUrl);
        article.setContent(articleRegisterForm.getContent());
        article.setGenre_id(articleRegisterForm.getGenre_id());
        article.setAdmin_id(adminId);

        articleMapper.insertArticle(article);

    }

    /**
     * CloudStorage認証@local
     */
    private static Storage storage = null;

    //start init
    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }


    /**
     *ファイルをuploadしてURlを返す.
     * @param filePart
     * @param bucketName
     * @return
     * @throws IOException
     */
    public String uploadFile(Part filePart, final String bucketName) throws IOException {

        final String fileName = filePart.getSubmittedFileName();

        BlobInfo blobInfo =
                storage.create(
                        BlobInfo
                                .newBuilder(bucketName,fileName)
                                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER)))).build(),
                        filePart.getInputStream());

        return blobInfo.getMediaLink();
    }


    /**
     * 記事を管理者IDで取得する.
     * @param adminId
     * @return
     */
    public List<Article> findArticlesByAdminId(int adminId, Integer pageCount){

        /*もしnullなら0で０以外は計算して、SQLのoffsetに渡す*/
        if(pageCount == null){
            pageCount = 0;
        } else {
            pageCount = 5 * (pageCount - 1);
        }


        return articleMapper.findArticlesByAdminId(adminId, pageCount);

    }


    /**
     * ジャンル名を全件検索する.
     * @return
     */
    public List<Genre> findAllGenre (){

        List<Genre> genreList = articleMapper.findAllGenre();

        return genreList;
    }


    /**
     * 記事を管理者IDと記事IDで検索する.
     * @param adminId
     * @param articleId
     * @return
     */
    public Article findArticleByAdminIdArticleId(int adminId, int articleId){

        return articleMapper.findArticleByAdminIdArticleId(adminId, articleId);
    }


    /**
     * 記事を編集する.
     * @param articleRegisterForm
     */
    public void editArticle(ArticleRegisterForm articleRegisterForm, String imageUrl, int adminId){

        String image = (String) session.getAttribute("image");

        Article article = new Article();
        article.setId(articleRegisterForm.getArticleId());
        article.setTitle(articleRegisterForm.getTitle());

        //もし、画像を変更していたら、新しい画像をsetしてなければ古いのをset
        if(!articleRegisterForm.getImageFile().isEmpty()){
            article.setImage(imageUrl);
        } else {
            article.setImage(image);
        }
        article.setContent(articleRegisterForm.getContent());
        article.setGenre_id(articleRegisterForm.getGenre_id());
        article.setAdmin_id(adminId);

        articleMapper.updateArticle(article);
    }


    /**
     * 管理者IDで該当する記事の件数を取得する.
     * @param adminId
     * @return 検索した記事の件数.
     */
    public Integer getCount(int adminId) {

        return articleMapper.countArticlesByAdminId(adminId);

    }


    /**
     * 記事を削除する.
     * @param adminId
     */
    public void deleteArticle(int adminId){

        articleMapper.deleteArticle(adminId);
    }


    /**
     * 検索系の処理.
     * @param articleSearchFrom
     * @param pageCount
     * @param adminId
     * @param genreId
     * @return
     */
    public List<Article> findArticlesBySearch(ArticleSearchFrom articleSearchFrom, Integer pageCount, int adminId, int genreId){

        session.setAttribute("genreId",articleSearchFrom.getGenreId());
        session.setAttribute("adminId",articleSearchFrom.getAdminId());


        List<Article> articleList = null;

        //genreIDもtitleNameもnullなら検索させない。
        if(articleSearchFrom == null || articleSearchFrom.getGenreId() == 0 && articleSearchFrom.getTitleName() == ""){
            return null;
        }

        //genreIDのみ入力があった場合。
        if(articleSearchFrom.getGenreId() != 0 && articleSearchFrom.getTitleName() == "") {

            //titleNameなしで検索かける.
            articleList = articleMapper.findArticlesByGenreId(genreId, adminId);

            return articleList;
        }

        //タイトル名のみ入力があった場合.
        if(articleSearchFrom.getGenreId() == 0 && articleSearchFrom.getTitleName() != ""){

           articleList = articleMapper.findArticlesByLikeTitleName(articleSearchFrom.getTitleName(), articleSearchFrom.getAdminId());

           return articleList;
        }

        //genreIDもtitleNameも両方共に入力があった場合。
         articleList = articleMapper.findArticlesByLikeTitleNameAndGenreId(articleSearchFrom.getTitleName(),articleSearchFrom.getGenreId(),
                                                                            articleSearchFrom.getAdminId());

        return articleList;

    }




//    public Integer getCount(String name){
//
//        if(name == null || "".equals(name) || "null".equals(name)) {
//            return articleMapper.countArticlesByAdminId();
//        }
//
//        return articleMapper.searchLikeArticles(name);
//    }

}

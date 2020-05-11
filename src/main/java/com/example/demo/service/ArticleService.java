package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.domain.Genre;
import com.example.demo.form.ArticleRegisterForm;
import com.example.demo.mapper.ArticleMapper;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    HttpSession session;



    /**
     * 記事をDBへ登録する.
     * @param articleRegisterForm
     */
    public void registerArticle(ArticleRegisterForm articleRegisterForm, String imageUrl){

        int adminId = (int) session.getAttribute("adminId");


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
    public List<Article> findArticlesByAdminId(int adminId){

        List<Article> articleList = articleMapper.findArticlesByAdminId(adminId);

        return articleList;
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
     * 記事を検索して、Adminドメインを返す.
     * @param adminId
     * @param articleId
     * @return
     */
    public Article finsArticleByAdminIdArticleId(int adminId, int articleId){

        Article article = articleMapper.finsArticleByAdminIdArticleId(adminId, articleId);

        return article;
    }
}

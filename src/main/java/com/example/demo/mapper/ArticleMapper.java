package com.example.demo.mapper;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 記事を登録する
     * @param article
     * @return
     */
    void insertArticle(Article article);

    /**
     * 管理者IDを使って、管理者rが投稿した記事を取得する.
     * @param adminId
     * @return
     */
    List<Article> findArticlesByAdminId(int adminId);

}

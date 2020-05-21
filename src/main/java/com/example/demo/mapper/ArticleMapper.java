package com.example.demo.mapper;

import com.example.demo.domain.Article;
import com.example.demo.domain.Genre;
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
    List<Article> findArticlesByAdminId(int adminId, int pageCount);



    /**
     * ジャンルを全権取得する.
     * @return
     */
    List<Genre> findAllGenre ();


    /**
     * 記事を管理者IDと記事IDで検索する.
     * @return
     */
     Article findArticleByAdminIdArticleId(int adminId, int articleId);


    /**
     * 記事内容を更新する.
     * @param article
     */
    void updateArticle(Article article);


    /**
     * 管理者IDを使って該当する記事の件数を検索する.
     * @param adminId
     * @return
     */
    Integer countArticlesByAdminId(int adminId);


    /**
     * 記事を削除する.
     * @param articleId
     */
    void deleteArticle(int articleId);

}

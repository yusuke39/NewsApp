package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

public class ArticleRegisterForm {

    /**見出し*/
    private String title;
    /**画像ファイル*/
    private MultipartFile imageFile;
    /**記事内容*/
    private String content;
    /**ジャンルID*/
    private Integer genre_id;
    /**記事ID*/
    private int articleId;

    public ArticleRegisterForm(String title, MultipartFile imageFile, String content, Integer genre_id, int articleId) {
        this.title = title;
        this.imageFile = imageFile;
        this.content = content;
        this.genre_id = genre_id;
        this.articleId = articleId;
    }

    public ArticleRegisterForm() {
    }

    @Override
    public String toString() {
        return "ArticleRegisterForm{" +
                "title='" + title + '\'' +
                ", imageFile=" + imageFile +
                ", content='" + content + '\'' +
                ", genre_id=" + genre_id +
                ", articleId=" + articleId +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
}

package com.example.demo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Article {

    /**記事ID*/
    private int id;
    /**見出し（タイトル）*/
    private String title;
    /**画像*/
    private String image;
    /**記事内容*/
    private String content;
    /**ジャンルID*/
    private int genre_id;
    /**管理者ID*/
    private int admin_id;
    /**管理者ドメイン*/
    private Admin admin;
    /**ジャンルドメイン*/
    private Genre genre;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", genre_id=" + genre_id +
                ", admin_id=" + admin_id +
                ", admin=" + admin +
                ", genre=" + genre +
                '}';
    }

    public Article(int id, String title, String image, String content, int genre_id, int admin_id, Admin admin, Genre genre) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.genre_id = genre_id;
        this.admin_id = admin_id;
        this.admin = admin;
        this.genre = genre;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

package com.example.demo.form;

public class ArticleSearchFrom {

    /**管理者ID*/
    private int adminId;
    /**ジャンルID*/
    private int genreId;
    /**タイトルネーム*/
    private String titleName;

    public ArticleSearchFrom(int adminId, int genreId, String titleName) {
        this.adminId = adminId;
        this.genreId = genreId;
        this.titleName = titleName;
    }

    public ArticleSearchFrom() {
    }

    @Override
    public String toString() {
        return "ArticleSearchFrom{" +
                "adminId=" + adminId +
                ", genreId=" + genreId +
                ", titleName='" + titleName + '\'' +
                '}';
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}

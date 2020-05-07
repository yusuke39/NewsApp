package com.example.demo.domain;

public class Genre {

    /**ジャンルID*/
    private int id;
    /**ジャンル名*/
    private String genre_name;

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre_name='" + genre_name + '\'' +
                '}';
    }

    public Genre(int id, String genre_name) {
        this.id = id;
        this.genre_name = genre_name;
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}

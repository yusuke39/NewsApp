package com.example.demo.form;

public class AdminRegisterGenreForm {

    private String genreName;

    public AdminRegisterGenreForm(String genreName) {
        this.genreName = genreName;
    }

    public AdminRegisterGenreForm() {
    }

    @Override
    public String toString() {
        return "AdminRegisterGenreFrom{" +
                "genreName='" + genreName + '\'' +
                '}';
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}

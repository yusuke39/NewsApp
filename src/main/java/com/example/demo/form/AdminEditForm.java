package com.example.demo.form;

public class AdminEditForm {

    private int id;
    private String name;
    private String email;

    public AdminEditForm(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public AdminEditForm() {
    }

    @Override
    public String toString() {
        return "AdminEditForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

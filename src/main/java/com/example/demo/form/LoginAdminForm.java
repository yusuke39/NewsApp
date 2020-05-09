package com.example.demo.form;

public class LoginAdminForm {

    private String email;
    private String password;

    public LoginAdminForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginAdminForm() {
    }

    @Override
    public String toString() {
        return "LoginAdminForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

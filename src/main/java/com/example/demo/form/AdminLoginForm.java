package com.example.demo.form;

public class AdminLoginForm {

    /**メールアドレス*/
    private String email;
    /**パスワード*/
    private String password;

    public AdminLoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AdminLoginForm() {
    }

    @Override
    public String toString() {
        return "AdminLogin{" +
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

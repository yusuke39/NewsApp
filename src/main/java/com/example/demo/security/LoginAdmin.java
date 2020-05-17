package com.example.demo.security;

import com.example.demo.domain.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoginAdmin extends User {

    private final Admin admin;

    public LoginAdmin(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getEmail(), admin.getPassword(), authorities);
        this.admin = admin;
    }

    /*認証処理成功後の処理でアカウント情報にアクセスできるようにするためにgetterメソッドを用意する*/
    public Admin getAdmin(){
        return admin;
    }
}
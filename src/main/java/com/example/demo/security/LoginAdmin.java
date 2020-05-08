package com.example.demo.security;

import com.example.demo.domain.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoginAdmin extends User {

    private final com.example.demo.domain.Admin Admin;


    public LoginAdmin(Admin admin , Collection<GrantedAuthority> authorityList) {
        super(admin.getEmail(), admin.getPassword(), authorityList);
        this.Admin = admin;
    }

    public com.example.demo.domain.Admin getAdmin() {
        return Admin;
    }
}
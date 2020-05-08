package com.example.demo.security;

import com.example.demo.domain.Admin;
import com.example.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("ADMIN")
class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public LoginAdmin loadUserByUsername(String email) throws UsernameNotFoundException {

        if(adminMapper.findAdmin(email).size() == 0){
            throw new UsernameNotFoundException("メールアドレスかパスワードが不正です");
        }

        List<Admin> adminList =  adminMapper.findAdmin(email);
        Admin admin = adminList.get(0);

        Collection<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ADMIN"));
        return new LoginAdmin(admin,  authorityList);
    }
}
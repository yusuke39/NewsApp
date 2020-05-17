package com.example.demo.security;

import com.example.demo.domain.Admin;
import com.example.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class AdminDetailService implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        /*DBから管理者情報を検索して、見つからなければ、UsernameNotFoundExceptionをスローする*/
        Admin admin = Optional.ofNullable(adminMapper.findAdmin(email))
                .orElseThrow(() -> new UsernameNotFoundException("管理者が見つかりませんでした"));
        /*アカウうんと情報が見つかった場合はLoginAdmin2を生成する*/
        return new LoginAdmin(admin, getAuthorities(admin));
    }

    private Collection<GrantedAuthority> getAuthorities(Admin admin) {
            return AuthorityUtils.createAuthorityList("ADMIN");
    }


}

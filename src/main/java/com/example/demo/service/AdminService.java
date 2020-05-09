package com.example.demo.service;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Genre;
import com.example.demo.form.AdminRegisterForm;
import com.example.demo.form.AdminRegisterGenreForm;
import com.example.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * 管理者登録情報をAdminMapperのinsertAdminメソッドに渡す.
     * @param adminRegisterForm
     */
    public void insertAdmin(AdminRegisterForm adminRegisterForm){

        Admin admin = new Admin();
        admin.setName(adminRegisterForm.getName());
        admin.setEmail(adminRegisterForm.getEmail());
        admin.setPassword(passwordEncoder.encode(adminRegisterForm.getPassword()));

        adminMapper.insertAdmin(admin);
    }


    /**
     * ジャンルを登録する.
     * @param adminRegisterGenreForm
     */
    public void insertGenre(AdminRegisterGenreForm adminRegisterGenreForm){

        Genre genre = new Genre();
        genre.setGenre_name(adminRegisterGenreForm.getGenreName());

        adminMapper.insertGenre(genre);
    }
}

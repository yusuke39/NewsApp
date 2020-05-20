package com.example.demo.service;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Genre;
import com.example.demo.form.AdminEditForm;
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


    /**
     * メールアドレスで管理者を１件検索する.
     * @param email
     * @return
     */
    public Admin findAdminByEmail(String email){

        Admin admin = adminMapper.findAdmin(email);
        return admin;
    }

    /**
     * 管理者情報をIDで検索する.
     * @param adminId
     * @return
     */
    public Admin findAdminById (int adminId){

        Admin admin = adminMapper.findAdminById(adminId);

        return admin;
    }


    /**
     * 管理者情報を変更する.
     * @param adminEditForm
     * @param adminId
     */
    public void updateAdmin(AdminEditForm adminEditForm, int adminId){

        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setName(adminEditForm.getName());
        admin.setEmail(adminEditForm.getEmail());
        adminMapper.updateAdmin(admin);
    }


}

package com.example.demo.mapper;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Genre;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {


    /**
     * 管理者登録する.
     * @param admin
     * @return
     */
    void  insertAdmin(Admin admin);


    /**
     * ジャンルを登録する.
     * @param genre
     */
    void insertGenre(Genre genre);


    /**
     * 管理情報をメールアドレスで１件検索する.
     * @param email
     * @return
     */
    Admin findAdmin(String email);


    /**
     * 管理者情報をIDで検索する.
     * @param adminId
     * @return
     */
    Admin findAdminById(int adminId);


    /**
     * 管理者情報更新.
     * @param admin
     */
    void updateAdmin(Admin admin);

}

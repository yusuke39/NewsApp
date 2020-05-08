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
    int  insertAdmin(Admin admin);


    /**
     * ジャンルを登録する.
     * @param genre
     */
    void insertGenre(Genre genre);


    /**
     * 管理者を１件検索する
     * @param email
     * @return
     */
    List<Admin> findAdmin(String email);

}

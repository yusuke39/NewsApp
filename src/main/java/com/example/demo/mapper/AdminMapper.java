package com.example.demo.mapper;

import com.example.demo.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {


    /**
     * @param admin
     * @return
     */
    int  insertAdmin(Admin admin);


}

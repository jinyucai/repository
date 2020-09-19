package com.cai.auth;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyRBACMapper {

    @Select("select m.url from sys_menu m\n" +
            "left join sys_role_menu rm on rm.menu_id = m.id\n" +
            "left join sys_role r on r.id = rm.role_id\n" +
            "left join sys_user_role ur on ur.role_id = r.id\n" +
            "left join sys_user u on u.id = ur.user_id\n" +
            "where u.username = #{username}\n")
    public List<String> findMenuByUsername(@Param("username") String username);

}

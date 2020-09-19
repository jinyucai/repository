package com.cai.auth;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MyUserDetailsServiceMapper {

    @Select("select username, password, enabled from sys_user where username = #{username} or phone = #{username}")
    MyUserDetails findByUsername(@Param("username") String username);

    @Select("select r.role_code from sys_role r\n" +
            "left join sys_user_role ur on ur.role_id = r.id\n" +
            "left join sys_user u on u.id = ur.user_id\n" +
            "where u.username = #{username} or u.phone = #{username}")
    List<String> findRoleByUsername(@Param("username") String username);

    //根据用户角色查询用户权限
    @Select({
            "<script>",
            "SELECT url " ,
            "FROM sys_menu m " ,
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id " ,
            "LEFT JOIN sys_role r ON r.id = rm.role_id ",
            "WHERE r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
            "#{roleCode}",
            "</foreach>",
            "</script>"
    })
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

}

package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper{

    @Insert("insert into user (username,password) values(#{username},#{password})")
    void save(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Select("select * from user where username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Update("update user set token = #{token} where username = #{username}")
    void update(User user);
}

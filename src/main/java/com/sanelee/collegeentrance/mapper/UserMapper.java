package com.sanelee.collegeentrance.mapper;

import com.sanelee.collegeentrance.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper{

    @Insert("insert into user (username,password,userPhone) values(#{username},#{password},#{userPhone})")
    void save(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Select("select * from user where username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Update("update user set token = #{token} where username = #{username}")
    void update(User user);

    @Select("select * from user")
    User user();

    @Select("select * from user where userRealname = #{userRealname} and userphone=#{userPhone}")
    User findByUserNameAndUserPhone(@Param("userRealname") String userRealname, @Param("userPhone")String userPhone);

    @Insert("UPDATE USER SET userRealname=#{userRealname},usergender=#{userGender},userwechat=#{userWechat},userscore=#{userScore},userarea=#{userArea},usersort=#{userSort},userrank=#{userRank} where userPhone=#{userPhone}")
    void saveUser(User user);

    @Select("select * from user where userPhone = #{userPhone}")
    User findByUserPhone(@Param("userPhone")String userPhone);

    @Select("select * from user where username = #{username}")
    User selectUserInfoByUsername(@Param("username") String username);
}
